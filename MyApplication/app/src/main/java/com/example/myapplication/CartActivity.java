package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.adapter.CartAdapter;
import com.example.myapplication.model.Dish;
import com.example.myapplication.util.CartManager;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private CartAdapter adapter;
    private TextView tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        RecyclerView recyclerView = findViewById(R.id.rv_cart);
        tvTotal = findViewById(R.id.tv_cart_total);
        Button btnGoPay = findViewById(R.id.btn_go_pay);

        List<Dish> cartList = CartManager.getInstance().getCartList();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CartAdapter(this, cartList, dish -> {
            // 触发移除逻辑
            CartManager.getInstance().removeDish(dish);
            adapter.notifyDataSetChanged();
            updateTotal();
            Toast.makeText(this, "已移除", Toast.LENGTH_SHORT).show();
        });
        recyclerView.setAdapter(adapter);

        updateTotal();

        // 去支付按钮
        btnGoPay.setOnClickListener(v -> {
            if (CartManager.getInstance().getCartList().isEmpty()) {
                Toast.makeText(this, "购物车为空，无法支付", Toast.LENGTH_SHORT).show();
            } else {
                startActivity(new Intent(CartActivity.this, CheckoutActivity.class));
            }
        });
    }

    private void updateTotal() {
        double total = CartManager.getInstance().getTotalPrice();
        tvTotal.setText("合计：¥" + total);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 从支付页返回时，如果支付成功清空了购物车，这里需要刷新界面
        adapter.notifyDataSetChanged();
        updateTotal();
    }
}