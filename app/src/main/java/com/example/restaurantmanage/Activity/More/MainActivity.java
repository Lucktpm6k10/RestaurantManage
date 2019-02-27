package com.example.restaurantmanage.Activity.More;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.restaurantmanage.Activity.TaiKhoan.TaiKhoanActivity;
import com.example.restaurantmanage.R;

public class MainActivity extends AppCompatActivity {
        CardView cvTaiKhoan,cvDanhMuc,cvThongKe,cvCaiDat;

        public static String tenDNFinal ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initListener() {
        cvTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TaiKhoanActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        cvTaiKhoan = findViewById(R.id.cv_TaiKhoan);
        cvDanhMuc = findViewById(R.id.cv_DanhMuc);
        cvThongKe = findViewById(R.id.cv_ThongKe);
        cvCaiDat = findViewById(R.id.cv_CaiDat);
        Intent intent = getIntent();
        tenDNFinal = intent.getStringExtra("tenTK");
    }
}
