package ijh.dgsw.hs.kr.androidshopping;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import ijh.dgsw.hs.kr.androidshopping.Database.DBHelper;

public class LoginActivity extends AppCompatActivity {

    private Animation logoAni; // 위치 이동
    private Animation loginFormAni; // 투명도 조절
    private ImageView imgView;
    private LinearLayout loginForm;
    private DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DBHelper(this, "userdb", null, 1);

        imgView = findViewById(R.id.logoImg);
        loginForm = findViewById(R.id.loginfrom_view);

        startAnime();
    }

    private void startAnime(){
        logoAni = new TranslateAnimation(0, 0, 0, -250); // from 어디서 to 어디까지 이동할건지. 가운데를 중심으로 위, 왼쪽: - 아래, 오른쪽: +
        logoAni.setDuration(2000); // 지속시간
        logoAni.setFillAfter(true); // 이동 후 이동한 자리에 남아있을건지
        logoAni.setStartOffset(1500); // 딜레이
        logoAni.setInterpolator(new AccelerateDecelerateInterpolator()); // interpolator 설정. AccelerteDecelerate : 시작지점에 가속했다 종료시점에 감속

        loginFormAni = new AlphaAnimation(0, 1);
        loginFormAni.setDuration(1000);
        loginFormAni.setStartOffset(3000);

        imgView.setAnimation(logoAni); // 애니메이션을 세팅해줌
        loginForm.setAnimation(loginFormAni);
    }

    public void onLogin(View v){
        // 로그인 id, pw 확인 후 일치 확인, 이후 main Activity로 이동
        if(!accountCheck()){
            Toast.makeText(this, "아이디와 비밀번호를 확인 해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onRegister(View v){
        // register Activity로 이동
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private boolean accountCheck(){
        //db에 접근해서 id, pw 확인 후 일치 시 true, 불일치시 false return.

        return false;
    }

    // 만약 로그인 되어있는 경우라면, 2초동안 로고만 보여준 후 바로 메인화면으로 이동
}