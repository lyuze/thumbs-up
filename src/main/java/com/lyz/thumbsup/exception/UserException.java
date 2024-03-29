package com.lyz.thumbsup.exception;

import com.lyz.thumbsup.enums.ResultEnum;
import lombok.Getter;

/**
 * 自定义异常
 */
@Getter
public class UserException extends RuntimeException {

    private Integer code;

    public UserException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public UserException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }
}
