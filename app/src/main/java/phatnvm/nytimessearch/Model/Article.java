package phatnvm.nytimessearch.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import phatnvm.nytimessearch.Utils.Constant;

/**
 * Created by Administrator on 10/20/2016.
 */

public class Article implements Parcelable
{
    @SerializedName("web_url")
    private String webUrl;

    @SerializedName("snippet")
    private String snippet;

    @SerializedName("multimedia")
    List<Media> multimedia;

    protected Article(Parcel in) {
        webUrl = in.readString();
        snippet = in.readString();
        multimedia = in.createTypedArrayList(Media.CREATOR);
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(webUrl);
        dest.writeString(snippet);
        dest.writeTypedList(multimedia);
    }

    public static class Media implements Parcelable {
        private String url;
        private String type;
        private int width;
        private int height;

        protected Media(Parcel in) {
            url = in.readString();
            type = in.readString();
            width = in.readInt();
            height = in.readInt();
        }

        public static final Creator<Media> CREATOR = new Creator<Media>() {
            @Override
            public Media createFromParcel(Parcel in) {
                return new Media(in);
            }

            @Override
            public Media[] newArray(int size) {
                return new Media[size];
            }
        };

        public String getUrl() {
            return Constant.IMAGE_URL + url;
        }

        public String getType() {
            return type;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(url);
            dest.writeString(type);
            dest.writeInt(width);
            dest.writeInt(height);
        }
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getSnippet() {
        return snippet;
    }

    public List<Media> getMultimedia() {
        return multimedia;
    }
}
