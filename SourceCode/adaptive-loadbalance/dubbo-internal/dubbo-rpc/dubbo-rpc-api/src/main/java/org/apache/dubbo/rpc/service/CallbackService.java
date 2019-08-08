package org.apache.dubbo.rpc.service;

import org.apache.dubbo.common.extension.SPI;
import org.apache.dubbo.rpc.listener.CallbackListener;

/**
 * @author daofeng.xjf
 */
@SPI
public interface CallbackService {

    void addListener(String key, CallbackListener listener);

}
