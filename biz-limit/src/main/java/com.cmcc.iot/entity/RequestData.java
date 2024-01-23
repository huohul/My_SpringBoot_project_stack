package com.cmcc.iot.entity;

import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class RequestData<T> {

    private Header header;

    private T body;

}