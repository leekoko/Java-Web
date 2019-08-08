package org.apache.dubbo.rpc.listener;

import org.apache.dubbo.common.extension.SPI;

/**
 * @author daofeng.xjf
 */
@SPI
public interface CallbackListener {

    void receiveServerMsg(String msg);

}
