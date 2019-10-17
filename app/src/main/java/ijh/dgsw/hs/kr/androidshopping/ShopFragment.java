package ijh.dgsw.hs.kr.androidshopping;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ijh.dgsw.hs.kr.androidshopping.CartRecycler.CartRecyclerAdapter;
import ijh.dgsw.hs.kr.androidshopping.Data.ProductBean;
import ijh.dgsw.hs.kr.androidshopping.Data.ProductDBHelper;
import ijh.dgsw.hs.kr.androidshopping.ShopRecycler.SelectRecyclerAdapter;
import ijh.dgsw.hs.kr.androidshopping.ShopRecycler.ShopRecyclerAdapter;

public class ShopFragment extends Fragment implements ItemClickListener{
    private View rootView;
    private RecyclerView recyclerView;
    private SelectRecyclerAdapter tAdapter;
    private ShopRecyclerAdapter pAdapter;
    private ArrayList<ProductBean> pData;
    private String[] tData;
    private ProductDBHelper dbHelper;
    private SQLiteDatabase db;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_shop_fragment, container, false);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showTypeSelecter();
        showProduct();
    }

    private void showTypeSelecter(){
        tData = getContext().getResources().getStringArray(R.array.type);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView = rootView.findViewById(R.id.typeSelectRecycler);
        recyclerView.setLayoutManager(layoutManager);
        tAdapter = new SelectRecyclerAdapter(tData, this);
        recyclerView.setAdapter(tAdapter);
    }

    private void showProduct(){
        dbHelper = ProductDBHelper.getInstance(getContext());
        pData = dbHelper.getAllProduct();

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView = rootView.findViewById(R.id.productRecycler);
        recyclerView.setLayoutManager(layoutManager);
        pAdapter = new ShopRecyclerAdapter(pData, this);
        recyclerView.setAdapter(pAdapter);
    }

    @Override
    public void onItemClick(View v, int position) {

    }
}