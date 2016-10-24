package phatnvm.nytimessearch.Model;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Administrator on 10/20/2016.
 */

public class BindingUtils {

    @BindingAdapter("imageUrl")
    public void imageUrl(ImageView imageView, String url){
        Glide.with(imageView.getContext())
                .load(url)
                .into(imageView);
    }
}