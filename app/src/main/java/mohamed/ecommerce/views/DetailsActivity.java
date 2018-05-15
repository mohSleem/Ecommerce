package mohamed.ecommerce.views;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;
import mohamed.ecommerce.R;
import mohamed.ecommerce.adapters.DetailsAdapter;

public class DetailsActivity extends AppCompatActivity {
    private static ViewPager mPager;
    private static final Integer[] CLOTHING_MEN= {R.drawable.image1,R.drawable.image2,R.drawable.image3,R.drawable.image4,R.drawable.image5};
    private ArrayList<Integer> clothingMenArray = new ArrayList<Integer>();
    private ToggleButton fav;
    private TextView tv;
    private CircleIndicator indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        init();
    }
    @SuppressLint("NewApi")
    private void init() {
        for(int i=0;i<CLOTHING_MEN.length;i++)
            clothingMenArray.add(CLOTHING_MEN[i]);

        //init var
        tv = findViewById(R.id.product_price);
        mPager = findViewById(R.id.pager);
        fav = findViewById(R.id.button_fav);
        indicator = findViewById(R.id.indicator);

        tv.setPaintFlags(tv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        fav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("NewApi")
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    fav.setBackgroundDrawable(getDrawable(R.drawable.ic_like));
                }else {
                    fav.setBackgroundDrawable(getDrawable(R.drawable.ic_unlike));

                }
            }
        });

        mPager.setAdapter(new DetailsAdapter(DetailsActivity.this, clothingMenArray));
        indicator.configureIndicator(20,20,9);
        indicator.setViewPager(mPager);

        //tool bar
        getSupportActionBar().setTitle("Product Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}