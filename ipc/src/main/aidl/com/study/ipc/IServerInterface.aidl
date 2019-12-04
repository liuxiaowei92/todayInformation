// IServerInterface.aidl
package com.study.ipc;

import com.study.ipc.IClientInterface;
// Declare any non-default types here with import statements

interface IServerInterface {
    void excuteAsyn(String requestKey,String requestParms);

    String excuteSync(String requestKey,String requestParms);

    void registerCallBack(IClientInterface iClient);
}
