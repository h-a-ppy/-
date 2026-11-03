package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.model.Dish;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private List<Dish> cartList;
    private Context context;
    private OnRemoveClickListener listener;

    // 定义点击移除的接口
    public interface OnRemoveClickListener {
        void onRemove(Dish dish);
    }

    public CartAdapter(Context context, List<Dish> cartList, OnRemoveClickListener listener) {
        this.context = context;
        this.cartList = cartList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Dish dish = cartList.get(position);
        holder.tvName.setText(dish.getName() + " x" + dish.getQuantity());
        holder.tvPrice.setText("¥" + (dish.getPrice() * dish.getQuantity()));

        // 删除按钮点击事件
        holder.btnRemove.setOnClickListener(v -> listener.onRemove(dish));
    }

    @Override
    public int getItemCount() { return cartList.size(); }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPrice;
        Button btnRemove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_cart_name);
            tvPrice = itemView.findViewById(R.id.tv_cart_price);
            btnRemove = itemView.findViewById(R.id.btn_remove);
        }
    }
}