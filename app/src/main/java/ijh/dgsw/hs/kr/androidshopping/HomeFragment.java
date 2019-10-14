package ijh.dgsw.hs.kr.androidshopping;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import java.util.ArrayList;

import ijh.dgsw.hs.kr.androidshopping.Database.ProductBean;
import ijh.dgsw.hs.kr.androidshopping.Database.ProductDBHelper;
import ijh.dgsw.hs.kr.androidshopping.Home.ItemClickListener;
import ijh.dgsw.hs.kr.androidshopping.Home.RecyclerAdapter;

public class HomeFragment extends Fragment implements ItemClickListener {
    private static final int INTERVAL_TIME = 3800;

    private View rootView;
    private ViewFlipper viewFlipper;
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private ArrayList<ProductBean> data;
    private ProductDBHelper dbHelper;
    private SQLiteDatabase db;

    int images[] = {
            R.drawable.slide_image_1,
            R.drawable.slide_image_2,
            R.drawable.slide_image_3,
            R.drawable.slide_image_4
    };

    // 메인. 슬라이드 형식 화면 절반치 광고, 랜덤 상품 아래에 6개 정도 보여주기
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_home_fragment, container, false);
        viewFlipper = rootView.findViewById(R.id.imageSlide);

        for(int image : images)
            flipperImages(image);


        showProduct();

        return rootView;
    }

    private void flipperImages(int image){
        ImageView imageView = new ImageView(getContext());
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(INTERVAL_TIME);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(getContext(), R.anim.slide_in_anim);
        viewFlipper.setOutAnimation(getContext(), R.anim.slide_out_anim);
    }

    private void showProduct(){
        dbHelper = ProductDBHelper.getInstance(getContext());
        data = dbHelper.getAllProduct();
        db = dbHelper.getWritableDatabase();

        initProduct();

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),3);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerAdapter(data, this);
        recyclerView.setAdapter(adapter);
    }

    private void initProduct(){
        init("product", 1, "청자켓", 73000);
        init("product", 2,"청바지", 56000);
        init("product", 3, "무스탕", 130000);
        init("product", 4, "신발", 69000);
        init("product", 5, "팔찌", 12900);
        init("product", 6, "맨투맨", 25900);
    }

    @Override
    public void onItemClick(View v, int position) {
        // click 했을 때 일어날 일. ex) 상품 상세화면
    }

    private void init(String tableName, int id, String pName, int pPrice){
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("name", pName);
        values.put("price", pPrice);
        db.insert("product", null, values);
    }
}