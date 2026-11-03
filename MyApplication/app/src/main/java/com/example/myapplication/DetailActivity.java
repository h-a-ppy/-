package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.myapplication.model.Dish;
import com.example.myapplication.util.CartManager;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // 接收传过来的菜品数据
        Dish dish = (Dish) getIntent().getSerializableExtra("dish_data");

        if (dish != null) {
            TextView tvName = findViewById(R.id.tv_detail_name);
            TextView tvPrice = findViewById(R.id.tv_detail_price);
            TextView tvDesc = findViewById(R.id.tv_detail_desc);
            ImageView ivImage = findViewById(R.id.iv_detail_image);
            Button btnAdd = findViewById(R.id.btn_detail_add);

            tvName.setText(dish.getName());
            tvPrice.setText("¥" + dish.getPrice());
            tvDesc.setText(dish.getDescription());
            Glide.with(this).load(dish.getImageUrl()).into(ivImage);

            btnAdd.setOnClickListener(v -> {
                CartManager.getInstance().addDish(dish);
                Toast.makeText(this, "已加入购物车", Toast.LENGTH_SHORT).show();
                finish(); // 加入后关闭详情页返回主页
            });
        }
    }
}