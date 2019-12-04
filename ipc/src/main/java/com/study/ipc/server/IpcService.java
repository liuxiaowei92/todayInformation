package com.study.ipc.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.study.ipc.IClientInterface;
import com.study.ipc.IServerInterface;

/**
 * @authour lxw
 * @function
 * @date 2019/11/21
 */
public class IpcService extends Service {

    private IClientInterface mClientInterface;

    @Override
    public IBinder onBind(Intent intent) {
        return new IServerInterface.Stub() {
            @Override
            public void excuteAsyn(String requestKey, String requestParms) throws RemoteException {
                switch (requestKey) {
                    case "shanghai_detail":
                        if(mClientInterface!=null){
                            mClientInterface.callBack(requestKey,"来自远方的祝福");
                        }
                        break;
                    default:
                        break;
                }
            }

            @Override
            public String excuteSync(String requestKey, String requestParms) throws RemoteException {
                String result="";
                switch (requestKey) {
                    case "shanghai_detail":
                            result="来自远方的祝福";
                        break;
                    default:
                        break;
                }
                return result;
            }

            @Override
            public void registerCallBack(IClientInterface iClient) throws RemoteException {
                mClientInterface = iClient;
            }
        };
    }
}
