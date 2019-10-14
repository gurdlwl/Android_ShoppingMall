package ijh.dgsw.hs.kr.androidshopping;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class HomeFragment extends Fragment {

    private ViewFlipper viewFlipper;
    private static final int INTERVAL_TIME = 3500;

    // 메인. 슬라이드 형식 화면 절반치 광고, 랜덤 상품 아래에 6개 정도 보여주기
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int images[] = {
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_foreground
        };

        View rootView = inflater.inflate(R.layout.activity_home_fragment, container, false);

        viewFlipper = rootView.findViewById(R.id.imageSlide);

        for(int image : images){
            flipperImages(image);
        }

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
}