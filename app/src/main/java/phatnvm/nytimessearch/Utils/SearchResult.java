package phatnvm.nytimessearch.Utils;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import phatnvm.nytimessearch.Model.Article;

/**
 * Created by Administrator on 10/21/2016.
 */
public class SearchResult {

    @SerializedName("docs")
    private List<Article> articles;

    public List<Article> getArticles () {
        return articles;
    }
}
