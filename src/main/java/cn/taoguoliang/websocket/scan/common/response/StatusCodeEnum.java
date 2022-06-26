package cn.taoguoliang.websocket.scan.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 状态码枚举
 *
 * @author taogl
 * @date 2022/6/26 15:47
 */
@AllArgsConstructor
@Getter
public enum StatusCodeEnum {

    /**
     * 服务器状态码枚举
     */
    SUCCESS(200, "成功"),
    SEVER_ERROR(500, "服务器错误"),

    /**
     * 二维码枚举
     */
    UUID_EXPIRED(4001, "二维码已过期"),
    LOGIN_FAIL(4002, "登录失败，账号密码错误"),
    QRCODE_SCANNED(4003, "二维码已扫描");

    private final Integer code;

    private final String desc;
}
