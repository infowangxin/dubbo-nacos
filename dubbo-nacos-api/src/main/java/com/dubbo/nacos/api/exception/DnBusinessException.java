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
public class DnBusinessException extends RuntimeException {

    private Integer errorCode = -1;
    private String errorMsg;
    private Throwable cause;

    public DnBusinessException(Integer errorCode, String errorMsg, Exception cause) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.cause = cause;
    }

    public DnBusinessException(Integer errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public DnBusinessException(String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        this.errorMsg = errorMsg;
    }

    public DnBusinessException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public DnBusinessException(Throwable cause) {
        super(cause);
    }


}
