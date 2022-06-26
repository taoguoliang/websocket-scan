package cn.taoguoliang.websocket.scan.common.exception;

import cn.taoguoliang.websocket.scan.common.response.StatusCodeEnum;
import lombok.Getter;

/**
 * 基础异常
 *
 * @author taogl
 * @date 2022/6/26 15:57
 */
@Getter
public abstract class BaseException extends RuntimeException {

    protected Integer code;

    public BaseException() {
    }

    public BaseException(StatusCodeEnum statusCodeEnum) {
        super(statusCodeEnum.getDesc());
        this.code = statusCodeEnum.getCode();
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
