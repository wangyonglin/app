package com.kernel.network;

import javakit.result.ResultCallback;

public  interface HttpUserInterface {
    public  void  Jumbotron(ResultCallback<String> result);
    public  void Time(String user ,ResultCallback<Integer> resultCallback);
}
