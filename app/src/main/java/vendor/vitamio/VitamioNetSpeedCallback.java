package vendor.vitamio;

import android.view.View;
import android.widget.TextView;

public interface VitamioNetSpeedCallback {
    public void PercentVisibility(int visibility);
    public void NetSpeedVisibility(int visibility);
    public void toString(String netSpeed);
}
