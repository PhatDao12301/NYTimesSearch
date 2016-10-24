package phatnvm.nytimessearch.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import phatnvm.nytimessearch.Model.Article;
import phatnvm.nytimessearch.R;

/**
 * Created by Administrator on 10/19/2016.
 */

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int NORMAL = 0;
    private final int NO_IMAGE = 1;

    List<Article> mArticles;
    private Listener mListener;

    public interface Listener {
        void OnLoadMore();
    }

    public Adapter() {
        this.mArticles = new ArrayList<>();
    }

    public void setListener(Listener listener) {
        mListener = listener;
    }

    public void setArticles(List<Article> articles){
        mArticles.clear();
        mArticles.addAll(articles);
        notifyDataSetChanged();
    }
    public void addArticles(List<Article> articles) {
        int startPosition = mArticles.size();
        mArticles.addAll(articles);
        notifyItemRangeInserted(startPosition,articles.size());
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        switch (viewType){
            case NO_IMAGE:
                itemView = LayoutInflater.from(parent.getContext()).
                        inflate(R.layout.item_no_image,parent,false);
                return new NoImageViewHolder(itemView);
            default:
                itemView = LayoutInflater.from(parent.getContext()).
                        inflate(R.layout.item_with_image,parent,false);
                return new ViewHolder(itemView);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Article article = mArticles.get(position);
        if(holder instanceof NoImageViewHolder){
            bindNoImage(article, (NoImageViewHolder) holder);
        } else {
            bindNormal(article,(ViewHolder) holder);
        }
        if((position == mArticles.size()-1) && mListener != null)
            mListener.OnLoadMore();
    }

    private void bindNormal (Article article, ViewHolder holder){
        holder.tvSnippet.setText(article.getSnippet());
        Glide.with(holder.itemView.getContext())
                .load(article.getMultimedia().get(0).getUrl())
                .into(holder.ivImage);
    }
    private void bindNoImage(Article article, NoImageViewHolder holder){
        holder.tvSnippet.setText(article.getSnippet());
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    @Override
    public int getItemViewType(int position) {
        Article article = mArticles.get(position);
        if (article.getMultimedia() == null || article.getMultimedia().isEmpty()) {
            return NO_IMAGE;
        }
        return NORMAL;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivImage)
        ImageView ivImage;
        @BindView(R.id.tvSnippet)
        TextView tvSnippet;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public class NoImageViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvSnippet)
        TextView tvSnippet;

        public NoImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
