package com.cmcc.iot.service;

public interface IdempotentStorage {

    void save(String idempotentId);

    boolean delete(String idempotentId);
}