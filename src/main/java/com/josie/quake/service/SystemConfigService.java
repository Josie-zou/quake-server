package com.josie.quake.service;

/**
 * Created by Glacier on 16/5/24.
 */
public interface SystemConfigService {

    boolean isSystemStart();

    boolean isExamineStart();

    void shutdownExamine();

    void shutdownSystem();

    void startExamine();

    void startSystem();

}
