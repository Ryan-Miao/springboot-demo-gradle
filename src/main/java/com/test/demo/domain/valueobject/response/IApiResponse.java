package com.test.demo.domain.valueobject.response;

/**
 * Created by Ryan Miao on 12/14/17.
 */
public interface IApiResponse<T> {

    T getValue();

    /**
     * Make sure the getErrorMsg is not null when isSuccessful return false.
     * @return
     */
    boolean isSuccessful();
    ErrorMsg getErrorMsg();

}
