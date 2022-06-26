package cn.taoguoliang.websocket.scan.config;

import cn.taoguoliang.websocket.scan.common.exception.ResourceNotFoundException;
import cn.taoguoliang.websocket.scan.common.response.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 *
 * @author taogl
 * @date 2022/6/26 16:07
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public Result<String> resourceNotFoundException(ResourceNotFoundException ex) {
        return Result.error(ex.getCode(), ex.getMessage());
    }
}
