package ijh.dgsw.hs.kr.androidshopping.Fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ijh.dgsw.hs.kr.androidshopping.Recycler.ItemClickListener;
import ijh.dgsw.hs.kr.androidshopping.R;
import ijh.dgsw.hs.kr.androidshopping.Recycler.CartRecyclerAdapter;
import ijh.dgsw.hs.kr.androidshopping.Data.ProductBean;
import ijh.dgsw.hs.kr.androidshopping.Data.ProductDBHelper;

public class CartFragment extends Fragment implements ItemClickListener {
    private View view;
    private RecyclerView recyclerView;
    private CartRecyclerAdapter adapter;
    private ArrayList<ProductBean> data;
    private ProductDBHelper dbHelper;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_cart_fragment, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showProduct();
    }

    private void showProduct() {
        dbHelper = ProductDBHelper.getInstance(getContext());
        data = dbHelper.getAllProduct();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CartRecyclerAdapter(data, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View v, int position) {

    }
}
