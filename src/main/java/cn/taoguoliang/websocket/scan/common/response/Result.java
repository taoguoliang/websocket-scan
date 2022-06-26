package cn.taoguoliang.websocket.scan.common.response;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 结果
 *
 * @author taogl
 * @date 2022/6/26 15:16
 */
@Data
@Accessors(chain = true)
public class Result<T> {

    private Integer code;

    private String msg;

    private T data;

    public Result() {
    }

    public Result(T data) {
        this.data = data;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(data).setCode(StatusCodeEnum.SUCCESS.getCode()).setMsg(StatusCodeEnum.SUCCESS.getDesc());
    }

    public static <T> Result<T> error(Integer code, String msg) {
        final Result<T> result = new Result<>();
        return result.setMsg(msg).setCode(code);
    }

    public static <T> Result<T> error(StatusCodeEnum statusCodeEnum) {
        final Result<T> result = new Result<>();
        return result.setMsg(statusCodeEnum.getDesc()).setCode(statusCodeEnum.getCode());
    }
}
