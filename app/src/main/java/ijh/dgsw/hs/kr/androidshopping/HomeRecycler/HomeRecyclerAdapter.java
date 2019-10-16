package ijh.dgsw.hs.kr.androidshopping.HomeRecycler;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

import ijh.dgsw.hs.kr.androidshopping.Data.ProductBean;
import ijh.dgsw.hs.kr.androidshopping.ItemClickListener;
import ijh.dgsw.hs.kr.androidshopping.R;

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeViewHolder> {
    private ArrayList<ProductBean> data;
    private ItemClickListener listener;

    public HomeRecyclerAdapter(ArrayList<ProductBean> data, ItemClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_item_card, viewGroup, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder viewHolder, int i) {
        ProductBean productBean = data.get(i);

        viewHolder.productImage.setImageDrawable(getImage(productBean.getImage()));
        viewHolder.productName.setText(productBean.getName());
        viewHolder.productPrice.setText(String.valueOf(productBean.getPrice()));

        /*
        final int index = i;
        viewHolder.itemView.setOnClickListener(v->{
            listener.onItemClick(v, index);
        });
        */
    }

    @Override
    public int getItemCount() {
        if(data == null)
            return 0;
        else
            return data.size();
    }

    public Drawable getImage(byte[] bytes){
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        Drawable drawable = new BitmapDrawable(null, bitmap);
        return drawable;
    }
}
