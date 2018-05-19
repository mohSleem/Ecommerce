package mohamed.ecommerce.views;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import mohamed.ecommerce.R;
import mohamed.ecommerce.adapters.MyRecyclerRelated;
import mohamed.ecommerce.adapters.SlidAdapter;
import mohamed.ecommerce.modules.Detail;

import static android.support.constraint.Constraints.TAG;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {
    private static ViewPager mPager;
    private static final Integer[] CLOTHING_MEN = {R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.image5};

    private ArrayList<Integer> clothingMenArray = new ArrayList<Integer>();
    private ToggleButton fav;
    private TextView tv;
    private CircleIndicator indicator;
    private TextView quantity, totalPrice;
    private Button add, odd;
    private List<Detail> details;
    private MyRecyclerRelated adapter;


    Button btnFeatured;
    private ToggleButton relatedFav;
    private TextView productPrice;
    private TextView productOverPrice;
    private TextView productName;
    private TextView productDetails;
    private RatingBar ratingBar;
    private TextView reviewRating;
    private TextView quantities;
    private TextView productDescription;


    int PRICE = 15;
    int TOTAL_PRICE;
    int QUANTITY = 1;
    private Dialog dialog;
    private RecyclerView recyclerView;
    private MyRecyclerRelated mAdapter;
    private List<Detail> Details = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //methods
        init();
        toolBar();
        getUrl();

        for (int i = 0; i < CLOTHING_MEN.length; i++)
            clothingMenArray.add(CLOTHING_MEN[i]);


        recyclerView = findViewById(R.id.rv);
        mAdapter = new MyRecyclerRelated(getApplicationContext(), Details, null);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        mPager.setAdapter(new SlidAdapter(DetailsActivity.this, clothingMenArray));
        mPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.MATCH_PARENT;
                dialog.getWindow().setAttributes(lp);
                Log.i(TAG, "This image was clicked: ");
            }
        });
//        dialog.cancel();

        indicator.configureIndicator(20, 20, 9);
        indicator.setViewPager(mPager);

        tv.setPaintFlags(tv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        fav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("NewApi")
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    fav.setBackgroundDrawable(getDrawable(R.drawable.fav));
                } else {
                    fav.setBackgroundDrawable(getDrawable(R.drawable.un_fav));

                }
            }
        });


    }

    private void init() {
        quantity = findViewById(R.id.quantities);
        add = findViewById(R.id.add_1);
        odd = findViewById(R.id.odd_1);
        tv = findViewById(R.id.product_price);
        mPager = findViewById(R.id.pager);
        fav = findViewById(R.id.button_fav);
        indicator = findViewById(R.id.indicator);
        totalPrice = findViewById(R.id.total_price);
        productOverPrice = findViewById(R.id.product_over_price);
        productName = findViewById(R.id.product_name);
        productDetails = findViewById(R.id.product_details);
        ratingBar = findViewById(R.id.ratingBar);
        reviewRating = findViewById(R.id.review_rating);
        totalPrice = findViewById(R.id.total_price);
        productDescription = findViewById(R.id.product_description);
        btnFeatured = findViewById(R.id.btn_featured);

        findViewById(R.id.add_to_cart).setOnClickListener(this);
        findViewById(R.id.share).setOnClickListener(this);

        add.setOnClickListener(this);
        odd.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.share:
                //TODO implement
                //هذا يقوم بمشاركة رابط المنتج
                //لم اقم باكماله لاني لم اعرف روابط للمنتحات
                Intent sendIntent = new Intent();
                Toast.makeText(this, "لم اقم باكماله", Toast.LENGTH_SHORT).show();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        "product url");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;
            case R.id.odd_1:
                //TODO implement
                oddQuantities(PRICE);
                break;
            case R.id.add_1:
                //TODO implement
                addQuantities(PRICE);
                break;
            case R.id.add_to_cart:
                //TODO implement
                //لم يكتمل لعدم وجود Add cart activity
                // من المفترض انه عند الضغط ينتقل الي add cart activity اذا كان المستخدم قام بتسجيل الدخول
                // اما ان لم يكن قد سجل الدخول ينتقل الي Log in activity
                Toast.makeText(getApplicationContext(), "added to cart", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void toolBar() {
        getSupportActionBar().setTitle("Product Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void addQuantities(int PRICE) {
        QUANTITY++;
        TOTAL_PRICE = PRICE * QUANTITY;
        quantity.setText("" + QUANTITY);
        totalPrice.setText("$" + TOTAL_PRICE);
        if (QUANTITY <= 1) {
            TOTAL_PRICE = PRICE;
            QUANTITY = 1;
            quantity.setText("1");
            totalPrice.setText("$" + PRICE);
        }
    }

    private void oddQuantities(int PRICE) {

        QUANTITY--;
        TOTAL_PRICE = PRICE * QUANTITY;
        quantity.setText("" + QUANTITY);
        totalPrice.setText("$" + TOTAL_PRICE);
        if (QUANTITY <= 0) {
            TOTAL_PRICE = PRICE;
            QUANTITY = 1;
            quantity.setText("1");
            totalPrice.setText("$" + PRICE);
        }

    }

    // قمت بانشاء قاعده بيانات بسيطة من الفايربيز للتجريب فقط
    private void getUrl() {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference("products/");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                new DownloadTask().execute(ref + ".json");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    public class DownloadTask extends AsyncTask<String, Void, Integer> {

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected Integer doInBackground(String... params) {
            Integer result = 0;
            HttpURLConnection urlConnection;
            try {
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                int statusCode = urlConnection.getResponseCode();
                // 200 represents HTTP OK
                if (statusCode == 200) {
                    BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null) {
                        response.append(line);
                    }
                    parseResult(response.toString());
                    result = 1; // Successful
                } else {
                    result = 0; //"Failed to fetch data!";
                }
            } catch (Exception e) {
                Log.d(TAG, e.getLocalizedMessage());
            }
            return result; //"Failed to fetch data!";
        }

        @Override
        protected void onPostExecute(Integer result) {


            if (result == 1) {
                adapter = new MyRecyclerRelated(getApplicationContext(), details, null);
                recyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(Detail item) {
                        // do something
                        Toast.makeText(DetailsActivity.this, "سيقوم بعرض المنتج", Toast.LENGTH_SHORT).show();
                    }
                });

            } else {
                Toast.makeText(getApplicationContext(), "Failed to fetch data!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void parseResult(String result) {
        try {
            JSONObject response = new JSONObject(result);
            JSONArray posts = response.optJSONArray("product");
            details = new ArrayList<>();

            for (int i = 0; i < posts.length(); i++) {
                JSONObject post = posts.optJSONObject(i);

                Detail item = new Detail();
                item.setTitle(post.optString("productName"));
                item.setImage(post.optString("productImage"));
                item.setPrice(post.optString("productPrice"));
                item.setOverPrice(post.optString("productOverPrice"));
                item.setIsSale(post.optString("isSale"));
                item.setIsFeatured(post.optString("isFeatured"));
                item.setIsNew(post.optString("isNew"));


                details.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}