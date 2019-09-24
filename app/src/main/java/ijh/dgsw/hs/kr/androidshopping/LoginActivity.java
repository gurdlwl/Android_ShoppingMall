package ijh.dgsw.hs.kr.androidshopping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class LoginActivity extends AppCompatActivity {

    Animation logoAni; // 위치 이동
    Animation loginFormAni; // 투명도 조절
    ImageView imgView;
    LinearLayout loginForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        imgView = findViewById(R.id.logoImg);
        loginForm = findViewById(R.id.loginfrom_view);

        logoAni = new TranslateAnimation(0, 0, 0, -250); // from 어디서 to 어디까지 이동할건지. 가운데를 중심으로 위, 왼쪽: - 아래, 오른쪽: +
        logoAni.setDuration(2000); // 지속시간
        logoAni.setFillAfter(true); // 이동 후 이동한 자리에 남아있을건지
        logoAni.setStartOffset(1500); // 딜레이

        loginFormAni = new AlphaAnimation(0, 1);
        loginFormAni.setDuration(1000);
        loginFormAni.setStartOffset(2500);

        imgView.setAnimation(logoAni); // 애니메이션을 세팅해줌
        loginForm.setAnimation(loginFormAni);
    }

    public void onLogin(View v){
        // 로그인 id, pw 확인 후 일치 시
        // main Activity로 이동
    }

    public void onRegister(View v){
        // register Activity로 이동
    }
}