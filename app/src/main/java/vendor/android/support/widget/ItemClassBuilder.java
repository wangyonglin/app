package vendor.android.support.widget;


import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.heylyn.callback.ResultLoader;
import com.khbd.app.R;

import com.wangyonglin.app.domain.ItemClass;
import com.wangyonglin.design.widget.ItemClassAdapter;

import java.util.List;

public class ItemClassBuilder  {
    public  static void create(Context context, ListView listView, List<ItemClass>itemClasses, ResultLoader<ItemClass> callback){


        ItemClassAdapter itemClassAdapter = new ItemClassAdapter(context, R.layout.itemclass_item,itemClasses);

        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ItemClass itemClass = itemClasses.get(i);
                callback.to(itemClass);
            }
        };
        listView.setOnItemClickListener(listener);

        listView.setAdapter(itemClassAdapter);

    }
}
