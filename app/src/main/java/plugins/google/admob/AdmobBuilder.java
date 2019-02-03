package plugins.google.admob;

import android.content.Context;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.khbd.app.R;
import com.khbd.app.SplashActivity;

import javakit.result.Result;
import javakit.result.ResultCallback;


public class AdmobBuilder {


    public static void   onCreateAdView(Context context, AdView adView, ResultCallback<AdView> callback){

        MobileAds.initialize(context, context.getResources().getString(R.string.google_admob_app_id));
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        adView.loadAd(adRequest);

        adView.setAdListener(new AdListener(){
            @Override
            public void onAdFailedToLoad(int errorCode) {

                switch (errorCode){
                    case AdRequest.ERROR_CODE_INTERNAL_ERROR:
                        callback.reject(new RuntimeException("AdView.ERROR_CODE_INTERNAL_ERROR"));
                        break;
                    case AdRequest.ERROR_CODE_INVALID_REQUEST :
                        callback.reject(new RuntimeException("AdView.ERROR_CODE_INVALID_REQUEST"));
                        break;
                    case AdRequest.ERROR_CODE_NETWORK_ERROR :
                        callback.reject(new RuntimeException("AdView.ERROR_CODE_NETWORK_ERROR"));
                        break;
                    case AdRequest.ERROR_CODE_NO_FILL :
                        callback.reject(new RuntimeException("AdView.ERROR_CODE_NO_FILL"));
                        break;
                }
                super.onAdFailedToLoad(errorCode);
            }
        });

        callback.resove(adView);
   }



}
