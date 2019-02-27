package com.example.restaurantmanage.Activity.More;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.restaurantmanage.Model.User;
import com.example.restaurantmanage.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    EditText edtTenDN,edtMatKhauDN;
    Button btnDangNhap;
    TextView tvCheckTenDN,tvCheckMatKhauDN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initListener();
    }

    private void initListener() {
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvCheckMatKhauDN.setVisibility(View.INVISIBLE);
                tvCheckTenDN.setVisibility(View.INVISIBLE);
                if (edtTenDN.getText().toString().length()>0&&edtMatKhauDN.getText().toString().length()>0){
                    FirebaseDatabase.getInstance().getReference().child("User").child(edtTenDN.getText().toString()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()){
                                if (dataSnapshot.getValue(User.class).getMatKhau().equals(edtMatKhauDN.getText().toString())){
                                    if(dataSnapshot.getValue(User.class).getChucVu()==0){
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        intent.putExtra("tenTK",edtTenDN.getText().toString());
                                        startActivity(intent);
                                        closeActivity();
                                    }else if(dataSnapshot.getValue(User.class).getChucVu()==1) {
                                        Intent intent = new Intent(getApplicationContext(), EmployeeMain.class);
                                        startActivity(intent);
                                        closeActivity();
                                    }
                                }else {
                                    tvCheckMatKhauDN.setVisibility(View.VISIBLE);
                                }
                            }else {
                                tvCheckTenDN.setVisibility(View.VISIBLE);
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                }
            }
        });

    }

    private void initView() {
        edtMatKhauDN = findViewById(R.id.edt_MatKhauDN);
        edtTenDN = findViewById(R.id.edt_TenDN);
        btnDangNhap = findViewById(R.id.btn_DangNhap);
        tvCheckTenDN = findViewById(R.id.tv_checkTenDN);
        tvCheckMatKhauDN = findViewById(R.id.tv_checkMatKhauDN);
    }
    private void closeActivity(){
        this.finish();
    }

}
