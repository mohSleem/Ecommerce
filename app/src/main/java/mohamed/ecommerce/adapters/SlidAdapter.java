package mohamed.ecommerce.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import mohamed.ecommerce.R;

/**
 * Created by mohamed mesalm on 14/05/18.
 */

public class SlidAdapter extends PagerAdapter {

    private ArrayList<Integer> images;
    private LayoutInflater inflater;
    public Context mContext;

    public SlidAdapter(Context context, ArrayList<Integer> images) {
        this.mContext = context;
        this.images=images;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, final int position) {
        View myImageLayout = inflater.inflate(R.layout.slide, view, false);
        final ImageView myImage = myImageLayout
                .findViewById(R.id.image);

        Picasso.with(mContext)
                .load(images.get(position))
                .noFade().resize(250, 250)
                .centerCrop().into(myImage);

        //handle click listener
        view.addView(myImageLayout, 0);
        return myImageLayout;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view.equals(object);
    }
}