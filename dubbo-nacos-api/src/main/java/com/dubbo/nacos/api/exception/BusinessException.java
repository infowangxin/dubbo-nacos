package com.dubbo.nacos.api.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Business Exception
 *
 * @author 胡桃夹子
 * @date 2019-08-04 12:34
 */
@Setter
@Getter
public class BusinessException extends RuntimeException {

    private Integer errorCode = -1;
    private String errorMsg;
    private Throwable cause;

    public BusinessException(Integer errorCode, String errorMsg, Exception cause) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.cause = cause;
    }

    public BusinessException(Integer errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BusinessException(String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        this.errorMsg = errorMsg;
    }

    public BusinessException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }


}
