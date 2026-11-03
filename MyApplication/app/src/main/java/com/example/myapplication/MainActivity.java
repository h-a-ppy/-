package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.adapter.DishAdapter;
import com.example.myapplication.model.Dish;
import com.example.myapplication.util.CartManager;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DishAdapter adapter;
    private TextView tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        tvTotal = findViewById(R.id.tv_total);
        Button btnCheckout = findViewById(R.id.btn_checkout);

        // 1. 模拟准备菜品数据 (使用网络图片)
        List<Dish> dishList = new ArrayList<>();
        dishList.add(new Dish("宫保鸡丁", "口味：微辣、酸甜。经典川菜，鸡肉鲜嫩，花生香脆。", 28.0, "https://images.unsplash.com/photo-1564834724105-918b73d1b9e0?q=80&w=400"));
        dishList.add(new Dish("麻婆豆腐", "口味：麻辣。豆腐滑嫩，麻辣鲜香，非常下饭。", 18.0, "https://images.unsplash.com/photo-1626200419199-391ae4be7a41?q=80&w=400"));
        dishList.add(new Dish("红烧肉", "口味：咸甜。肥而不腻，入口即化，浓油赤酱。", 38.0, "https://images.unsplash.com/photo-1596797038530-2c107229654b?q=80&w=400"));
        dishList.add(new Dish("水煮牛肉", "口味：麻辣鲜香。牛肉滑嫩，汤底红亮，重口味首选。", 48.0, "https://images.unsplash.com/photo-1598514982205-f36b96d1e8d4?q=80&w=400"));
        dishList.add(new Dish("干煸四季豆", "口味：咸香微辣。四季豆干香脆嫩，肉末增香。", 22.0, "https://images.unsplash.com/photo-1627042633145-b780d842ba45?q=80&w=400"));
        dishList.add(new Dish("糖醋里脊", "口味：酸甜可口。外酥里嫩，酱汁浓郁，老少皆宜。", 32.0, "https://images.unsplash.com/photo-1541614101331-1a5a3a194e92?q=80&w=400"));
        dishList.add(new Dish("清炒时蔬", "口味：清淡爽口。当季新鲜蔬菜，少油健康。", 16.0, "https://images.unsplash.com/photo-1512621776951-a57141f2eefd?q=80&w=400"));

        // 2. 设置 RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DishAdapter(this, dishList, new DishAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Dish dish) {
                // 点击菜品，跳转详情页
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("dish_data", dish);
                startActivity(intent);
            }

            @Override
            public void onAddToCart(Dish dish) {
                // 点击加入购物车
                CartManager.getInstance().addDish(dish);
                updateTotalPrice();
                Toast.makeText(MainActivity.this, "已加入购物车", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);

        // 3. 去结算按钮逻辑
        btnCheckout.setOnClickListener(v -> {
            if (CartManager.getInstance().getCartList().isEmpty()) {
                Toast.makeText(this, "购物车为空", Toast.LENGTH_SHORT).show();
            } else {
                startActivity(new Intent(MainActivity.this, CartActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateTotalPrice(); // 页面重新可见时更新金额
    }

    private void updateTotalPrice() {
        double total = CartManager.getInstance().getTotalPrice();
        tvTotal.setText("总计：¥" + total);
    }
}