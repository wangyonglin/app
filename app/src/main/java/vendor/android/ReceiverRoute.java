package vendor.android;

import android.app.Activity;

import android.content.Intent;

import com.heylyn.network.ResultCallback;
import com.wangyonglin.app.domain.ItemClass;


import java.util.ArrayList;
import java.util.List;


public class ReceiverRoute {
    public static void itemClasses(Activity activity, ResultCallback< List<ItemClass>> callback) {
        Intent intent = activity.getIntent();
        List<ItemClass> lists = intent.getParcelableArrayListExtra("itemClasses");
        if (lists != null){

            callback.success(lists);
        }else{
            callback.failure(new RuntimeException("没有数据"));
        }

    }
}
