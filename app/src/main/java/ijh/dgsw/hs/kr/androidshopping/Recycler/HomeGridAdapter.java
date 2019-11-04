package ijh.dgsw.hs.kr.androidshopping.Recycler;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ijh.dgsw.hs.kr.androidshopping.Data.ProductBean;
import ijh.dgsw.hs.kr.androidshopping.R;

public class HomeGridAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ProductBean> data;

    public HomeGridAdapter(Context context, ArrayList<ProductBean> data){
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HomeViewHolder viewHolder;

        if(convertView == null){
            viewHolder = new HomeViewHolder();

            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.custom_item_card, null);
            viewHolder.productImage = convertView.findViewById(R.id.imageView);
            viewHolder.productName = convertView.findViewById(R.id.productNameTv);
            viewHolder.productPrice = convertView.findViewById(R.id.productPriceTv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (HomeViewHolder) convertView.getTag();
        }

        viewHolder.productImage.setImageDrawable(getImage(data.get(position).getImage()));
        viewHolder.productName.setText(data.get(position).getName());
        viewHolder.productPrice.setText(String.valueOf(data.get(position).getPrice()));

        return convertView;
    }

    public class HomeViewHolder {
        ImageView productImage;
        TextView productName;
        TextView productPrice;
    }

    public Drawable getImage(byte[] bytes){
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        Drawable drawable = new BitmapDrawable(null, bitmap);
        return drawable;
    }

    /*

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_item_card, viewGroup, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder homeViewHolder, int i) {
        ProductBean productBean = data.get(i);

        homeViewHolder.productImage.setImageDrawable(getImage(productBean.getImage()));
        homeViewHolder.productName.setText(productBean.getName());
        homeViewHolder.productPrice.setText(String.valueOf(productBean.getPrice()));
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

    public class HomeViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName;
        TextView productPrice;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.imageView);
            productName = itemView.findViewById(R.id.productNameTv);
            productPrice = itemView.findViewById(R.id.productPriceTv);
        }
    }
    */

}
