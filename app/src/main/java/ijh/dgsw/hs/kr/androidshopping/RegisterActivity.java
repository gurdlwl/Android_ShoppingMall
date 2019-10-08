package ijh.dgsw.hs.kr.androidshopping;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    Spinner yearsSpinner;
    ArrayAdapter adapter;

    EditText name;
    EditText email;
    EditText id;
    EditText pw;
    EditText pwChk;
    RadioGroup gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.nameEt);
        email = findViewById(R.id.emailEt);
        id = findViewById(R.id.idEt); // DB에 중복되는 id값 있는지 확인
        pw = findViewById(R.id.pwEt);
        pwChk = findViewById(R.id.pwChkEt); // pwEt와 값이 같은지 확인
        gender = findViewById(R.id.genderRg);

        RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

            }
        };
        gender.setOnCheckedChangeListener(listener);

        yearsSpinner = (Spinner)findViewById(R.id.yearsSppiner);
        adapter = ArrayAdapter.createFromResource(this, R.array.yearsArray, android.R.layout.simple_spinner_dropdown_item);
        yearsSpinner.setAdapter(adapter);

        yearsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void onOkBtnClick(View v){
        if(!emptyCheck()) {
            // DB에 값 집어넣기

        }
    }

    public void onCancelBtnClick(View v){
        // 진짜 뒤로갈거냐 확인하기
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("알림");
        builder.setMessage("회원가입을 취소하시겠습니까?");
        builder.setPositiveButton("예",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();
                    }
                });
        builder.setNegativeButton("아니오",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.show();
    }

    private boolean emptyCheck(){
        if(TextUtils.isEmpty(name.getText())){
            showDialog("이름", name);
            return true;
        }
        else if(TextUtils.isEmpty(email.getText())){
            showDialog("이메일", email);
            return true;
        }
        else if(TextUtils.isEmpty(id.getText())){
            showDialog("아이디", id);
            return true;
        }
        else if(TextUtils.isEmpty(pw.getText())){
            showDialog("비밀번호", pw);
            return true;
        }
        else if(TextUtils.isEmpty(pwChk.getText())){
            showDialog("비밀번호 확인", pwChk);
            return true;
        }

        return false;
    }

    private void showDialog(String str, final EditText et){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("알림");

        if(str.equals("성별") || str.equals("연령")){
            builder.setMessage( str + "을 선택해주세요.");
        } else {
            builder.setMessage( str + "을(를) 기입해주세요.");
        }

        builder.setPositiveButton("확인",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 해당 position으로 focus 이동 후 키보드 올리기
                        et.requestFocus();
                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                    }
                });
        builder.show();
    }
}
