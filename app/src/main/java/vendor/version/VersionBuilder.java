package vendor.version;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;


public class VersionBuilder {
    public interface DownloadDialogCallback{
        public void update();
    }
    public interface OnClickCallback{
        public void update();
        public void cancel();
    }

    public static class UpdateDialog extends VersionBuilder{
        private MaterialDialog.Builder builder;
        private MaterialDialog materialDialog;
        private Context context;
        private DownloadDialogCallback downloadDialogCallback;
        public UpdateDialog(final Context context,DownloadDialogCallback dialogCallback){
            this.context=context;
            downloadDialogCallback=dialogCallback;
            builder = new  MaterialDialog.Builder(context);
            builder.title("检测到新版本");
            builder.content("Budddddddddddddddddddddddddddddddddddddddddddddddd");
            builder.titleGravity(GravityEnum.CENTER);
            builder.titleColor(Color.parseColor("#000000"));
            builder.autoDismiss(false);
            builder.widgetColor(Color.RED);
            builder.positiveText("立即更新");
            builder.titleGravity(GravityEnum.CENTER);
            builder.buttonsGravity(GravityEnum.START);
            builder.negativeText("以后再说");
            materialDialog=builder.build();

            builder.onAny(any);
        }
        private MaterialDialog.SingleButtonCallback any= new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                if (which == DialogAction.POSITIVE) {
                    materialDialog.dismiss();
                    downloadDialogCallback.update();
                } else if (which == DialogAction.NEGATIVE) {
                    Toast.makeText(context, "请升级哦，有新功能哦！", Toast.LENGTH_LONG).show();
                    materialDialog.dismiss();
                }
            }
        };
        public void show(){
            materialDialog.show();
        }
        public void hide(){
            materialDialog.hide();
        }

    }
}
