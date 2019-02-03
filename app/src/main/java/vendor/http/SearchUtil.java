package vendor.http;


import com.heylyn.exception.ResultException;


import com.heylyn.network.HttpClient;
import com.wangyonglin.app.network.ParseUtil;
import com.heylyn.network.ResultCallback;

import java.util.List;


public class SearchUtil {
//
    public static void create(final String search,final String genre, final ResultCallback<List> callback){
        search.trim();
        String url="http://data.redname.com/kushan/television/search?search="+search+"&genre="+genre+"&page=0&size=18";
        HttpClient.urlResultString(url, new ResultCallback<String>() {
            @Override
            public void success(String s) {
                ParseUtil.toTelevisions(s,callback);
            }

            @Override
            public void failure(RuntimeException e) {
                callback.failure(e);
            }
        });

    }

    public void chose(final String genre, final ResultCallback<List> callback){
        String url="http://data.redname.com/kushan/television/chose?&genre="+genre+"&page=0&size=18";
        HttpClient.urlResultString(url, new ResultCallback<String>() {
            @Override
            public void success(String s) {
                ParseUtil.toTelevisions(s,callback);
            }

            @Override
            public void failure(RuntimeException e) {
                callback.failure(e);
            }
        });

    }
}
