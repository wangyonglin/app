package vendor.setup;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;

import vendor.version.VersionBuilder;

public class UpdateView {
    public interface OnClickCallback{
        public void update();
        public void cancel();
    }

    public static class Show extends UpdateView{
        private MaterialDialog.Builder builder;
        private MaterialDialog window;
        public Show(Context context,String title,String content){
            builder = new  MaterialDialog.Builder(context);
            builder.title(title);
            builder.content(content);
            builder.titleGravity(GravityEnum.CENTER);
            builder.titleColor(Color.parseColor("#000000"));
            builder.autoDismiss(false);
            builder.widgetColor(Color.RED);
            builder.positiveText("立即更新");
            builder.titleGravity(GravityEnum.CENTER);
            builder.buttonsGravity(GravityEnum.START);
            builder.negativeText("以后再说");
            window=builder.build();
        }

        public void start(OnClickCallback callback){
            MaterialDialog.SingleButtonCallback any= new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                    if (which == DialogAction.POSITIVE) {
                        window.dismiss();
                        callback.update();
                    } else if (which == DialogAction.NEGATIVE) {
                        window.dismiss();
                        callback.cancel();
                    }
                }
            };
            builder.onAny(any);
            window.show();
        }

    }
}
