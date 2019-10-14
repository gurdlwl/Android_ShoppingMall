package ijh.dgsw.hs.kr.androidshopping;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ijh.dgsw.hs.kr.androidshopping.Database.UserDBHelper;

public class MyFragment extends Fragment {
    // 이름, 아이디, 이메일, 성별, 나이 알려주기
    // 정보수정? 시간 남으면 하기 -> 정보수정 글씨를 누르면 팝업화면으로 수정하고 확인하면 refresh
    private TextView userName;
    private TextView userName2;
    private TextView userId;
    private TextView userEmail;
    private TextView userGender;
    private TextView userYears;
    private UserDBHelper dbHelper;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_my_fragment, container, false);

        userName = rootView.findViewById(R.id.usernameTv);
        userName2 = rootView.findViewById(R.id.usernameTv2);
        userId = rootView.findViewById(R.id.useridTv);
        userEmail = rootView.findViewById(R.id.useremailTv);
        userGender = rootView.findViewById(R.id.usergenderTv);
        userYears = rootView.findViewById(R.id.useryearsTv);

        dbHelper = UserDBHelper.getInstance(getContext());

        return rootView;
    }
}