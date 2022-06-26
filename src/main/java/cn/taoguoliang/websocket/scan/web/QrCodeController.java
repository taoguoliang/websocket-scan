package cn.taoguoliang.websocket.scan.web;

import cn.hutool.core.lang.UUID;
import cn.taoguoliang.websocket.scan.common.consts.QrCodeConsts;
import cn.taoguoliang.websocket.scan.common.consts.QrCodeStatus;
import cn.taoguoliang.websocket.scan.common.exception.ResourceNotFoundException;
import cn.taoguoliang.websocket.scan.common.response.Result;
import cn.taoguoliang.websocket.scan.common.response.StatusCodeEnum;
import cn.taoguoliang.websocket.scan.pojo.QrCodeVO;
import cn.taoguoliang.websocket.scan.util.QrCodeGenerate;
import cn.taoguoliang.websocket.scan.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Optional;

/**
 * 二维码接口
 *
 * @author taogl
 * @date 2022/6/26 15:15
 */
@RestController
@RequestMapping("api/v1/qrCode")
@Slf4j
public class QrCodeController {

    @PostMapping
    public Result<QrCodeVO> createQrCode() {
        String uuid = UUID.fastUUID().toString();
        String qrCode = QrCodeGenerate.createQrCode(uuid, 200, 200);
        RedisUtil.hPut(QrCodeConsts.UUID, uuid, QrCodeStatus.NOT_SCAN.toString());
        return Result.success(new QrCodeVO().setImage(qrCode).setUuid(uuid));
    }

    @GetMapping
    public Result<String> queryIsScannedOrVerified(@RequestParam("uuid")String uuid){
        String status = Objects.toString(RedisUtil.hGet(QrCodeConsts.UUID, uuid), null);
        return Result.success(status);
    }

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("/actions/scan")
    public Result<String> doAppScanQrCode(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("uuid") String uuid) {
        final QrCodeStatus qrCodeStatus = Optional.ofNullable(RedisUtil.hGet(QrCodeConsts.UUID, uuid))
                .map(Objects::toString)
                .map(QrCodeStatus::valueOf)
                .orElseThrow(() -> new ResourceNotFoundException(StatusCodeEnum.UUID_EXPIRED));
        switch (qrCodeStatus) {
            case NOT_SCAN:
                //等待确认 todo
                if (username.equals("dzou") && password.equals("1234")) {
                    RedisUtil.hPut(QrCodeConsts.UUID, uuid, QrCodeStatus.SCANNED.toString());
                    simpMessagingTemplate.convertAndSend("/topic/ws","请确认");
                    return Result.success("请手机确认");
                } else {
                    return Result.error(StatusCodeEnum.LOGIN_FAIL);
                }
            case SCANNED:
                return Result.error(StatusCodeEnum.QRCODE_SCANNED);
            case VERIFIED:
                return Result.success("你已经确认过了");
        }
        return Result.error(StatusCodeEnum.SEVER_ERROR);
    }

    @PostMapping("/actions/verify")
    public Result<String> verifyQrCode(@RequestParam("uuid") String uuid) {
        final QrCodeStatus qrCodeStatus = Optional.ofNullable(RedisUtil.hGet(QrCodeConsts.UUID, uuid))
                .map(Objects::toString)
                .map(QrCodeStatus::valueOf)
                .orElseThrow(() -> new ResourceNotFoundException(StatusCodeEnum.UUID_EXPIRED));
        RedisUtil.hPut(QrCodeConsts.UUID, uuid, QrCodeStatus.VERIFIED.toString());
        simpMessagingTemplate.convertAndSend("/topic/ws","已经确认");
        return Result.success("确认成功");
    }

}
