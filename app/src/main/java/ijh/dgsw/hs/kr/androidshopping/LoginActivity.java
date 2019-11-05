package ijh.dgsw.hs.kr.androidshopping;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import ijh.dgsw.hs.kr.androidshopping.Data.PreferenceManager;
import ijh.dgsw.hs.kr.androidshopping.Data.UserDBHelper;

public class LoginActivity extends AppCompatActivity {
    private Animation logoAni; // 위치 이동
    private Animation loginFormAni; // 투명도 조절
    private ImageView imgView;
    private LinearLayout loginForm;
    private UserDBHelper dbHelper;
    private PreferenceManager pManager;

    private EditText id;
    private EditText pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = UserDBHelper.getInstance(getApplicationContext());
        pManager = new PreferenceManager();

        imgView = findViewById(R.id.logoImg);
        loginForm = findViewById(R.id.loginfrom_view);
        id = findViewById(R.id.idEt);
        pw = findViewById(R.id.pwEt);

        loginCheck();
        startAnime();
    }

    private void startAnime() {
        logoAni = new TranslateAnimation(0, 0, 0, -250); // from 어디서 to 어디까지 이동할건지. 가운데를 중심으로 위, 왼쪽: - 아래, 오른쪽: +
        logoAni.setDuration(2000); // 지속시간
        logoAni.setFillAfter(true); // 이동 후 이동한 자리에 남아있을건지
        logoAni.setStartOffset(1500); // 딜레이
        logoAni.setInterpolator(new AccelerateDecelerateInterpolator()); // interpolator 설정. AccelerteDecelerate : 시작지점에 가속했다 종료시점에 감속

        loginFormAni = new AlphaAnimation(0, 1);
        loginFormAni.setDuration(1000);
        loginFormAni.setStartOffset(3000);

        imgView.setAnimation(logoAni); // 애니메이션 세팅
        loginForm.setAnimation(loginFormAni);
    }

    public void loginCheck() {
        String loginId = pManager.getString(this, "user_id");

        if(loginId.length() != 0) { // preference가 비어있지 않으면 바로 Main실행.
            startMainActivity();
        }
    }

    public void onLogin(View v) {
        // 로그인 id, pw 확인 후 일치 확인, 이후 main Activity로 이동
        if(!accountCheck()) {
            return;
        }

        pManager.setString(this, "user_id", String.valueOf(id.getText()));

        setEmptyEt();
        startMainActivity();
    }

    public void onRegister(View v) {
        // register Activity로 이동
        setEmptyEt();

        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private boolean accountCheck() {
        //db에 접근해서 id, pw 확인 후 일치 시 true, 불일치시 false return.
        String idValue = String.valueOf(id.getText());
        String pwValue = String.valueOf(pw.getText());

        if(idValue.equals("") || pwValue.equals("")) {
            return false;
        }

        String dbId = dbHelper.getUserId(idValue);
        String dbPw = dbHelper.getUserPw(idValue);

        if(dbId.equals(idValue)) {
            if(dbPw.equals(pwValue)) {
                Toast.makeText(this, "환영합니다.", Toast.LENGTH_SHORT).show();
                return true;
            }
            Toast.makeText(this, "비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
            return false;
        }

        setEmptyEt();
        Toast.makeText(this, "존재하지 않는 아이디입니다.", Toast.LENGTH_SHORT).show();
        return false;
    }

    private void setEmptyEt() {
        id.setText("");
        pw.setText("");
    }
}