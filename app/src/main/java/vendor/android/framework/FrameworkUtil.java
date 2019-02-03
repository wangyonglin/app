package vendor.android.framework;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

public class FrameworkUtil {
    public static int Row(Context context){
       // DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        //activity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        DisplayMetrics localDisplayMetrics = context.getResources().getDisplayMetrics();
        return localDisplayMetrics.widthPixels;
    }
    public static int Span(Context context,int weight){
        if(weight>100)
            throw new RuntimeException("Weight > 100");
        DisplayMetrics localDisplayMetrics = context.getResources().getDisplayMetrics();
        return localDisplayMetrics.widthPixels/100*weight;
    }

    public static int Span(Context context,int row,int weight){
        if(weight>row)
            throw new RuntimeException("Weight > "+row);
        DisplayMetrics localDisplayMetrics = context.getResources().getDisplayMetrics();
        return localDisplayMetrics.widthPixels/row*weight;
    }
}
