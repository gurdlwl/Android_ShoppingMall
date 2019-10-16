package ijh.dgsw.hs.kr.androidshopping.CartRecycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ijh.dgsw.hs.kr.androidshopping.R;

public class CartViewHolder extends RecyclerView.ViewHolder {
    ImageView productImage;
    TextView productName;
    TextView productPrice;

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);

        productImage = itemView.findViewById(R.id.imageView);
        productName = itemView.findViewById(R.id.productNameTv);
        productPrice = itemView.findViewById(R.id.productPriceTv);
    }
}
