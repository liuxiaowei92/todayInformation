package com.study.ipc;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;

import com.study.ipc.request.IRequest;
import com.study.ipc.result.IResult;
import com.study.ipc.result.IpcResult;
import com.study.ipc.server.IpcService;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @authour lxw
 * @function 1 步
 * @date 2019/11/21
 */
public class IpcManager {
    private static IpcManager mInstance;
    private final Context mContext;
    //有序 不重复的集合 只针对异步请求------------------
    private Set<IRequest> mRequest = new TreeSet<IRequest>();
    private Set<IRequest> mWaitRequest = new TreeSet<IRequest>();
    //--------------------------------------------
    private int mConnetStatus = IConnectStatus.STATUS_UNBIND;
    private ServiceConnection mConnection;
    private IServerInterface mServer;
    private IBinder.DeathRecipient mDeathRecipient;
    private IClientInterface.Stub mClientInterface;

    interface IConnectStatus {
        int STATUS_UNBIND = 0;
        int STATUS_BINDING = 1;
        int STATUS_BIND = 2;
    }

    private IpcManager(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public synchronized static IpcManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new IpcManager(context);
        }
        return mInstance;
    }

    //同步  待完善  必须链接成功后 才能返回数据 目前是错误的
    public IResult excuteSync(IRequest request) {
        if (TextUtils.isEmpty(request.getRequestKey()) || mRequest.contains(request)) {
            return IpcResult.getErrorResult();
        }
        //判断服务是否链接成功
        if (mConnetStatus != IConnectStatus.STATUS_BIND) {
            //链接
            connectService();
            return IpcResult.getErrorResult();
        }

        return excute(request);
    }
    //提供给客户端 建立链接的一个方法  回调成功 在返回数据


    //异步跨进程通信
    public void excuteAsyn(IRequest request, CallBack callBack) {
        if (TextUtils.isEmpty(request.getRequestKey()) || mRequest.contains(request)) {
            callBack.callBack(IpcResult.getErrorResult());
            return;
        }
        //保存这个request
        request.addCallBack(callBack);
        mRequest.add(request);
        //判断服务是否链接成功
        if (mConnetStatus != IConnectStatus.STATUS_BIND) {
            //链接
            connectService();
            mWaitRequest.add(request);
            return;
        }
        excute(request);
    }

    //建立IPC 通信链接
    private void connectService() {
        if (mConnection == null) {
            mConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    mConnetStatus = IConnectStatus.STATUS_BIND;
                    mServer = IServerInterface.Stub.asInterface(service);
                    //往服务端注入接口
                    try {
                        mServer.registerCallBack(mClientInterface);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    try {
                        //IBinder 通信的死亡通知 有重启逻辑
                        service.linkToDeath(mDeathRecipient, 0);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    //链接成功之后 去把等待的数据请求发送
                    for (IRequest request : mWaitRequest) {
                        excute(request);
                    }
                    mWaitRequest.clear();
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    mConnetStatus = IConnectStatus.STATUS_UNBIND;
                }
            };
        }
        if (mDeathRecipient == null) {
            mDeathRecipient = new IBinder.DeathRecipient() {
                @Override
                public void binderDied() {
                    mConnetStatus = IConnectStatus.STATUS_UNBIND;
                    //针对异步请求做callback处理
                    for (IRequest request : mRequest) {
                        request.getCallBack().callBack(IpcResult.getErrorResult());
                    }
                }
            };
        }

        if (mClientInterface == null) {
            mClientInterface = new IClientInterface.Stub() {
                @Override
                public void callBack(String requestKey, String resultStr) throws RemoteException {
                    Iterator<IRequest> iterator = mRequest.iterator();
                    while (iterator.hasNext()) {
                        IRequest next = iterator.next();
                        if (TextUtils.equals(next.getRequestKey(), requestKey)) {
                            next.getCallBack().callBack(IpcResult.getSuccessResult(resultStr));
                            return;
                        }
                    }
                }
            };
        }

        Intent intent = new Intent(mContext, IpcService.class);
        mContext.bindService(intent, mConnection, Service.BIND_AUTO_CREATE);
        mConnetStatus = IConnectStatus.STATUS_BINDING;
    }

    //实际 跨进程的方法
    private IResult excute(IRequest request) {
        if (request.getCallBack() != null) {
            try {//异步
                mServer.excuteAsyn(request.getRequestKey(), request.getParams());
            } catch (RemoteException e) {
                e.printStackTrace();
                request.getCallBack().callBack(IpcResult.getErrorResult());
            }
        } else {
            try {//同步
                String result = mServer.excuteSync(request.getRequestKey(), request.getParams());
                return IpcResult.getSuccessResult(result);
            } catch (RemoteException e) {
                e.printStackTrace();
                return IpcResult.getErrorResult();
            }
        }
        return IpcResult.getErrorResult();
    }

}
