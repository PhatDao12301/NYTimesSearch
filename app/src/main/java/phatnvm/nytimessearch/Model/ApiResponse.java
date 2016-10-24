package phatnvm.nytimessearch.Model;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;



/**
 * Created by Administrator on 10/20/2016.
 */

public class ApiResponse {

    @SerializedName("response")
    private JsonObject response;

    @SerializedName("status")
    private String status;

    public JsonObject getResponse() {
        if (response == null)
        {
            return new JsonObject();
        }
        return response;
    }

    public String getStatus() {
        return status;
    }

//    public class Response{
//        @SerializedName("docs")
//        private Docs docs;
//
//        public Docs getDocs(){
//            return this.docs;
//        }
//
//        public class Docs{
//            @SerializedName("web_url")
//            private String webUrl;
//
//            @SerializedName("snippet")
//            private String snippet;
//
//            @SerializedName("headline")
//            private String headline;
//
//            @SerializedName("multimedia")
//            private String mutimedia;
//
//            public String getWebUrl() {
//                return webUrl;
//            }
//
//            public String getSnippet() {
//                return snippet;
//            }
//
//
//
//            public class Multimedia{
//@SerializedName("")
//                private String url;
//
//                public String getUrl() {
//                    return url;
//                }
//            }
//        }
//    }

}
