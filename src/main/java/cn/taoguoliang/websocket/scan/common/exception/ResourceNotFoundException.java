package cn.taoguoliang.websocket.scan.common.exception;

import cn.taoguoliang.websocket.scan.common.response.StatusCodeEnum;
import lombok.Getter;

/**
 * 资源未找到
 *
 * @author taogl
 * @date 2022/6/26 15:53
 */
@Getter
public class ResourceNotFoundException extends BaseException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(StatusCodeEnum statusCodeEnum) {
        super(statusCodeEnum);
    }

}
