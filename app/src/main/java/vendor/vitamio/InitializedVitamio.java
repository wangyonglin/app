package vendor.vitamio;

import android.content.Context;

import io.vov.vitamio.Vitamio;

public class InitializedVitamio {
    public  InitializedVitamio(Context context){
        Vitamio.isInitialized(context);
    }
}
