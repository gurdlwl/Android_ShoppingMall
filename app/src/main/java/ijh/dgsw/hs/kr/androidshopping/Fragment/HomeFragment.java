package ijh.dgsw.hs.kr.androidshopping.Fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import java.util.ArrayList;

import ijh.dgsw.hs.kr.androidshopping.Data.ProductBean;
import ijh.dgsw.hs.kr.androidshopping.Data.ProductDBHelper;
import ijh.dgsw.hs.kr.androidshopping.Recycler.HomeGridAdapter;
import ijh.dgsw.hs.kr.androidshopping.R;

public class HomeFragment extends Fragment {
    private static final int INTERVAL_TIME = 3800;

    private View view;
    private ViewFlipper viewFlipper;
    private GridView gridView;
    private HomeGridAdapter adapter;
    private ArrayList<ProductBean> data;
    private ProductDBHelper dbHelper;

    int images[] = {
            R.drawable.slide_image_1,
            R.drawable.slide_image_2,
            R.drawable.slide_image_3,
            R.drawable.slide_image_4
    };

    // 메인. 슬라이드 형식 화면 절반치 광고, 아래에 상품 6개 정도 보여주기
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_home_fragment, container, false);
        viewFlipper = view.findViewById(R.id.imageSlide);

        for(int image : images)
            flipperImages(image);

        showProduct();

        return view;
    }

    private void flipperImages(int image) {
        ImageView imageView = new ImageView(getContext());
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(INTERVAL_TIME);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(getContext(), R.anim.slide_in_anim);
        viewFlipper.setOutAnimation(getContext(), R.anim.slide_out_anim);
    }

    private void showProduct() {
        dbHelper = ProductDBHelper.getInstance(getContext());
        data = dbHelper.getRandomProduct();

        gridView = view.findViewById(R.id.gridView);
        adapter = new HomeGridAdapter(getContext(), data);
        gridView.setAdapter(adapter);
    }
}