package ijh.dgsw.hs.kr.androidshopping.ShopRecycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ijh.dgsw.hs.kr.androidshopping.ItemClickListener;
import ijh.dgsw.hs.kr.androidshopping.R;

public class SelectRecyclerAdapter extends RecyclerView.Adapter<SelectRecyclerAdapter.SelectViewHolder> {
    private String[] data;
    private ItemClickListener listener;

    public SelectRecyclerAdapter(String[] data, ItemClickListener listener){
        this.data = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SelectViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_select_type_card, viewGroup, false);
        return new SelectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectViewHolder selectViewHolder, int i) {
        selectViewHolder.typeSelect.setText(data[i]);
    }

    @Override
    public int getItemCount() {
        if(data == null)
            return 0;
        else
            return data.length;
    }

    public class SelectViewHolder extends RecyclerView.ViewHolder{
        TextView typeSelect;

        public SelectViewHolder(@NonNull View itemView) {
            super(itemView);

            typeSelect = itemView.findViewById(R.id.typeSelectTv);
        }
    }
}
