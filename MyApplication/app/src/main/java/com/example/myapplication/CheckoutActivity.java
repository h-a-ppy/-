package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.util.CartManager;

public class CheckoutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        TextView tvTotal = findViewById(R.id.tv_checkout_total);
        Button btnPay = findViewById(R.id.btn_pay);

        // ==== 新增的返回按钮逻辑 ====
        Button btnBack = findViewById(R.id.btn_back_checkout);
        btnBack.setOnClickListener(v -> {
            finish(); // 关闭当前结算页，自动回到上一页（购物车页）
        });
        // ===========================

        // 显示总金额
        double total = CartManager.getInstance().getTotalPrice();
        tvTotal.setText("合计：¥" + total);

        // 支付按钮逻辑
        btnPay.setOnClickListener(v -> {
            Toast.makeText(this, "支付成功！", Toast.LENGTH_LONG).show();
            CartManager.getInstance().clearCart(); // 清空购物车
            finish(); // 关闭结算页，回到主页
        });
    }
}