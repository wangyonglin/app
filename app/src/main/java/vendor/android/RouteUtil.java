package vendor.android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.vendor.design.Atom;


import java.util.ArrayList;
import java.util.List;

public class RouteUtil extends  ReceiverRoute{
    public static void JumpWhenCanClick(Context packageContext, Class<?> cls,BuilderCallback callback) {
        Bundle bundle = new Bundle();
        Intent intent = new Intent(packageContext,cls);
        callback.put(bundle);
        //把bundle放入intent里
        intent.putExtra("Message",bundle);
        packageContext.startActivity(intent);
    }
    public static void JumpWhenCanClick(Context packageContext, Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(packageContext, cls);
        packageContext.startActivity(intent);
    }
    public static void JumpWhenCanClick(Context packageContext, Class<?> cls,String uuid) {
        Intent intent = new Intent();
        intent.setClass(packageContext, cls);
        //用数据捆传递数据
        Bundle bundle = new Bundle();
        bundle.putString("uuid", uuid);  //把数据捆设置改意图
        intent.putExtra("data", bundle);  //激活意图
        packageContext.startActivity(intent);
    }

    /*
    public static void JumpWhenCanClick(Context packageContext, Class<?> cls,List<Atom> atoms ){
        Intent intent = new Intent();
        intent.setClass(packageContext, cls);
        ArrayList<Atom> list = new ArrayList<>();
        list.addAll(atoms);
        intent.putParcelableArrayListExtra("Atoms", (ArrayList<? extends Parcelable>) list);
        packageContext.startActivity(intent);
    }*/
    public interface BuilderCallback{
        public void put(Bundle bundle);
    }
}
