package ijh.dgsw.hs.kr.androidshopping.Home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ijh.dgsw.hs.kr.androidshopping.R;

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView productName;
    TextView productPrice;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        productName = itemView.findViewById(R.id.productNameTv);
        productPrice = itemView.findViewById(R.id.productPriceTv);
    }
}
