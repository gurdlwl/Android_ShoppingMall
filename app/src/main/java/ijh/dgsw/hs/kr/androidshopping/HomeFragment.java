package ijh.dgsw.hs.kr.androidshopping;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import ijh.dgsw.hs.kr.androidshopping.Data.ProductBean;
import ijh.dgsw.hs.kr.androidshopping.Data.ProductDBHelper;
import ijh.dgsw.hs.kr.androidshopping.HomeRecycler.HomeRecyclerAdapter;

public class HomeFragment extends Fragment implements ItemClickListener {
    private static final int INTERVAL_TIME = 3800;

    private View rootView;
    private ViewFlipper viewFlipper;
    private RecyclerView recyclerView;
    private HomeRecyclerAdapter adapter;
    private ArrayList<ProductBean> data;
    private ProductDBHelper dbHelper;
    private SQLiteDatabase db;

    int images[] = {
            R.drawable.slide_image_1,
            R.drawable.slide_image_2,
            R.drawable.slide_image_3,
            R.drawable.slide_image_4
    };

    // 메인. 슬라이드 형식 화면 절반치 광고, 아래에 상품 6개 정도 보여주기
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_home_fragment, container, false);
        viewFlipper = rootView.findViewById(R.id.imageSlide);

        for(int image : images)
            flipperImages(image);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showProduct();
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

        dbHelper.deleteAllProduct();
        initProduct();

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),3);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new HomeRecyclerAdapter(data, this);
        recyclerView.setAdapter(adapter);
    }

    private void initProduct(){
        init("product", 1, "청자켓", 73000, getByteArrayFromDrawable(getResources().getDrawable(R.drawable.top_blue_jacket, null)), "top");
        init("product", 2,"진청바지", 56000, getByteArrayFromDrawable(getResources().getDrawable(R.drawable.bottom_deepblue_jean, null)), "bottom");
        init("product", 3, "무스탕", 130000, getByteArrayFromDrawable(getResources().getDrawable(R.drawable.top_black_mustang, null)), "top");
        init("product", 4, "신발", 69000, getByteArrayFromDrawable(getResources().getDrawable(R.drawable.black_shoes, null)), "acc");
        init("product", 5, "팔찌", 12900, getByteArrayFromDrawable(getResources().getDrawable(R.drawable.black_bracelet, null)), "acc");
        init("product", 6, "맨투맨", 25900, getByteArrayFromDrawable(getResources().getDrawable(R.drawable.top_beige_mantoman, null)), "top");
    }

    private void init(String tableName, int id, String pName, int pPrice, byte[] pImage, String type){
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("name", pName);
        values.put("price", pPrice);
        values.put("image", pImage);
        values.put("type", type);
        db.insert(tableName, null, values);
    }

    // drawable 이미지를 sqlite에 넣기 위해 byteArray로 변환하는 함수
    private byte[] getByteArrayFromDrawable(Drawable drawable){
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] dataByte = stream.toByteArray();

        return dataByte;
    }

    @Override
    public void onItemClick(View v, int position) {
        // click 했을 때 일어날 일. ex) 상품 상세화면
    }
}