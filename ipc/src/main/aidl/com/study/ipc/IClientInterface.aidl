// IClientInterface.aidl
package com.study.ipc;

// Declare any non-default types here with import statements

interface IClientInterface {
   void callBack(String requestKey,String resultStr);
}
