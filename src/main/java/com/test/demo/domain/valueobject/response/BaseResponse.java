package com.test.demo.domain.valueobject.response;

/**
 * Created by Ryan Miao on 12/14/17.
 */
public class BaseResponse<T> implements IApiResponse<T> {
    private final T value;
    private final boolean successful;
    private final ErrorMsg errorMsg;

    public BaseResponse(T value) {
        this.value = value;
        this.errorMsg = null;
        this.successful = true;
    }

    public BaseResponse(ErrorMsg errorMsg) {
        this.errorMsg = errorMsg;
        this.value = null;
        this.successful = false;
    }

    @Override
    public T getValue() {
        return this.value;
    }

    @Override
    public boolean isSuccessful() {
        return this.errorMsg == null;
    }

    @Override
    public ErrorMsg getErrorMsg() {
        return this.errorMsg;
    }
}
