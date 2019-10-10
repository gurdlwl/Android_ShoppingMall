package ijh.dgsw.hs.kr.androidshopping;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import ijh.dgsw.hs.kr.androidshopping.Database.DBHelper;
import ijh.dgsw.hs.kr.androidshopping.Database.UserBean;

public class RegisterActivity extends AppCompatActivity {

    private static final String EMPTY_STRING = "";

    private Spinner yearsSpinner;
    private ArrayAdapter adapter;

    private EditText name;
    private EditText email;
    private EditText id;
    private EditText pw;
    private EditText pwChk;
    private RadioGroup gender;
    private RadioButton male;
    private RadioButton female;

    private String selectYears = "";
    private String selectGender = "";
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHelper = new DBHelper(this, "userdb", null, 1);

        name = findViewById(R.id.nameEt);
        email = findViewById(R.id.emailEt);
        id = findViewById(R.id.idEt); // DB에 중복되는 id값 있는지 확인
        pw = findViewById(R.id.pwEt);
        pwChk = findViewById(R.id.pwChkEt); // pwEt와 값이 같은지 확인
        gender = findViewById(R.id.genderRg);
        male = findViewById(R.id.maleRb);
        female = findViewById(R.id.femaleRb);

        RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == male.getId()){
                    selectGender = String.valueOf(male.getText());
                } else if (checkedId == female.getId()){
                    selectGender = String.valueOf(female.getText());
                }
            }
        };
        gender.setOnCheckedChangeListener(listener);

        yearsSpinner = (Spinner) findViewById(R.id.yearsSppiner);
        adapter = ArrayAdapter.createFromResource(this, R.array.yearsArray, android.R.layout.simple_spinner_dropdown_item);
        yearsSpinner.setAdapter(adapter);

        yearsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectYears = yearsSpinner.getSelectedItem().toString();
                if(selectYears.equals("선택")){
                    selectYears = EMPTY_STRING;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    public void onOkBtnClick(View v){
        if(!isEmpty()) {
            // DB에 값 집어넣기
            UserBean userBean = new UserBean();

            userBean.setName(String.valueOf(name.getText()));
            userBean.setEmail(String.valueOf(email.getText()));
            userBean.setId(String.valueOf(id.getText()));
            userBean.setPassword(String.valueOf(pw.getText()));
            userBean.setGender(String.valueOf(selectGender));
            userBean.setYears(String.valueOf(selectYears));

            dbHelper.insert(userBean);
            showUsers();

            //alertDialog 띄워주기
            onBackPressed();
        }
    }

    public void onCancelBtnClick(View v){
        // 진짜 뒤로갈건지 확인하기
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
                new DialogInterface.OnClickListener() {@Override public void onClick(DialogInterface dialog, int which) { }});
        builder.show();
    }

    private boolean isEmpty(){
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
        else if(TextUtils.isEmpty(selectGender)){
            showDialog("성별");
            return true;
        }
        else if(TextUtils.isEmpty(selectYears)){
            showDialog("연령");
            return true;
        }

        return false;
    }

    private void showDialog(String str){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("알림");
        builder.setMessage( str + "을 선택해주세요.");
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) { }});
        builder.show();
    }

    private void showDialog(String str, final EditText et){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("알림");
        builder.setMessage( str + "을(를) 기입해주세요.");

        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
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

    private void showUsers() {
        ArrayList<UserBean> userBean = dbHelper.getAll();

        for(UserBean u : userBean){
            String msg = "[ " + u.getSerialNumber() + ", " + u.getId() + ", " + u.getName() + ", " + u.getEmail() + " ]";
            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }
}
