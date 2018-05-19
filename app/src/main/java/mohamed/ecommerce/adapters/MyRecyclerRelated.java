package mohamed.ecommerce.adapters;

/**
 * Created by mohamed mesalm on 19/05/18.
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


import com.squareup.picasso.Picasso;


import java.util.List;

import mohamed.ecommerce.R;
import mohamed.ecommerce.modules.Detail;
import mohamed.ecommerce.views.OnItemClickListener;


public class MyRecyclerRelated extends RecyclerView.Adapter<MyRecyclerRelated.CustomViewHolder> implements OnItemClickListener {
    private List<Detail> detailList;
    private List<Detail> feedItemListFiltered;
    private Context mContext;
    private OnItemClickListener onItemClickListener;
    private int AD_TYPE = 0;
    private int CONTENT_TYPE = 1;

    public MyRecyclerRelated(Context context, List<Detail> detailList, OnItemClickListener onItemClickListener) {
        this.detailList = detailList;
        this.feedItemListFiltered = detailList;
        this.mContext = context;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return (null != detailList ? detailList.size() : 0);
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 12 == 10)
            return AD_TYPE;
        return CONTENT_TYPE;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view;
        CustomViewHolder viewHolder;
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.related_one_item, viewGroup, false);
        viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        final Detail detail = detailList.get(i);

        if (!TextUtils.isEmpty(detail.getImage())) {
            Picasso.with(mContext).load(detail.getImage())
                    .into(customViewHolder.rvProductImage);
        }
        if (detail.getIsFeatured().equals("true")) {
            customViewHolder.featured.setVisibility(View.VISIBLE);
        }
        if (detail.getIsSale().equals("true")) {
            customViewHolder.sale.setVisibility(View.VISIBLE);
        }

        //Setting text view title
        customViewHolder.rvProductName.setText(detail.getTitle());
        customViewHolder.price.setText(detail.getPrice());
        customViewHolder.overPrice.setText(detail.getOverPrice());

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(detail);
            }
        };

        customViewHolder.rvProductImage.setOnClickListener(listener);
        customViewHolder.rvProductName.setOnClickListener(listener);
        customViewHolder.price.setOnClickListener(listener);
        customViewHolder.overPrice.setOnClickListener(listener);
        customViewHolder.rvAddToFav.setOnClickListener(listener);
    }

    @Override
    public void onItemClick(Detail item) {

    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        private ImageView rvProductImage;
        private TextView rvProductName;
        private TextView price;
        private TextView overPrice;
        private ToggleButton rvAddToFav;
        Button sale, featured;

        public CustomViewHolder(View view) {

            super(view);

            rvProductImage = view.findViewById(R.id.rv_product_image);
            rvProductName = view.findViewById(R.id.rv_product_name);
            price = view.findViewById(R.id.price);
            overPrice = view.findViewById(R.id.over_price);
            rvAddToFav = view.findViewById(R.id.rv_add_to_fav);
            sale = view.findViewById(R.id.btn_sale);
            featured = view.findViewById(R.id.btn_featured);


            view.findViewById(R.id.rv_add_to_cart).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //لم يكتمل لعدم وجود Add cart activity
                    // من المفترض انه عند الضغط ينتقل الي add cart activity اذا كان المستخدم قام بتسجيل الدخول
                    // اما ان لم يكن قد سجل الخول ينتقل الي Log in activity
                    Toast.makeText(mContext, "added to cart", Toast.LENGTH_SHORT).show();
                }
            });


            price.setPaintFlags(price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            rvAddToFav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @SuppressLint("NewApi")
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        rvAddToFav.setBackgroundDrawable(mContext.getDrawable(R.drawable.fav));
                    } else {
                        rvAddToFav.setBackgroundDrawable(mContext.getDrawable(R.drawable.un_fav));

                    }
                }
            });
        }

    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
