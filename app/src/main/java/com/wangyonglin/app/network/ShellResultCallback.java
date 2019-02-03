package com.wangyonglin.app.network;

import java.util.List;

public interface ShellResultCallback<T>{
    public void data(Shell shell, List<T> json2list);
}

