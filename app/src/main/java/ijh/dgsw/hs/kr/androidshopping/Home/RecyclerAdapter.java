package ijh.dgsw.hs.kr.androidshopping.Home;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ijh.dgsw.hs.kr.androidshopping.Database.ProductBean;
import ijh.dgsw.hs.kr.androidshopping.R;

public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder>{
    private ArrayList<ProductBean> data;
    private ItemClickListener listener;

    public RecyclerAdapter(ArrayList<ProductBean> data, ItemClickListener listener){
        this.data = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_item_card, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ProductBean productBean = data.get(i);
        viewHolder.productName.setText(productBean.getName());
        viewHolder.productPrice.setText(String.valueOf(productBean.getPrice()));

        final int index = i;
        viewHolder.itemView.setOnClickListener(v->{
            listener.onItemClick(v, index);
        });
    }

    @Override
    public int getItemCount() {
        if(data == null)
            return 0;
        else
            return data.size();
    }
}
