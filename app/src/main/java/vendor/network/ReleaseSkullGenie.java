package vendor.network;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.heylyn.jackson.JacksonUtil;



import java.io.IOException;

import javakit.network.HttpClientResponse;
import okhttp3.ResponseBody;

public class ReleaseSkullGenie {
    public static void ParseRelease(String url,VersionCallback callback){
        HttpClientResponse.String(url, new HttpClientResponse.ResultCallback<String>() {

            @Override
            public void success(String res) throws IOException {
                try {
                    JsonNode result = JacksonUtil.readTree(res).get("result");
                    String title = result.get("title").asText();
                    String pubdate = result.get("pubdate").asText();
                    String websites = result.get("websites").asText();
                    String content = result.get("content").asText();
                    String name = result.get("name").asText();
                    String code = result.get("code").asText();
                    String url = result.get("url").asText();
                    callback.call(title,pubdate,websites,content,name,code,url);

                } catch (JsonParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (JsonMappingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(Exception e) {

            }
        });
    }
    public interface VersionCallback{
         void call(String title,String pubdate,String websites,String content,String name,String code,String url);
    }
}
