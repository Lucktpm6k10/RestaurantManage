package com.example.restaurantmanage.Activity.TaiKhoan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.restaurantmanage.Model.User;
import com.example.restaurantmanage.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ThemTaiKhoanActivity extends AppCompatActivity {

    private Button btnThemTK, btnDatLai;
    private RadioButton rdbAdmin, rdbEmployee;
    private EditText edtTenDN, edtMatKhau;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_tai_khoan);
        initView();
        initListener();
    }

    private void initListener() {
        btnThemTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtTenDN.getText().toString().isEmpty()) {
                    Toast.makeText(ThemTaiKhoanActivity.this, "Không được để trống tài khoản ", Toast.LENGTH_SHORT).show();
                } else if (edtMatKhau.getText().toString().isEmpty()) {
                    Toast.makeText(ThemTaiKhoanActivity.this, "Không được để trống tài khoản ", Toast.LENGTH_SHORT).show();
                } else {
                    showdialog();
                }
            }
    });
        btnDatLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtMatKhau.setText("");
                edtTenDN.setText("");
                rdbAdmin.setChecked(true);
            }
        });
}

    private void showdialog() {
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Thêm tài khoản");
        builder.setMessage("Bạn có muốn thêm tài khoản này không");
        builder.setCancelable(false);
        builder.setPositiveButton("Tiếp tục", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                themTaiKhoan();

            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private void themTaiKhoan() {
        final String ten = edtTenDN.getText().toString();
        String matKhau = edtMatKhau.getText().toString();
        int i = 0;
        if (rdbAdmin.isChecked()) {
            i = 0;
        } else if (rdbEmployee.isChecked()) {
            i = 1;
        }
        final User user = new User(ten, matKhau, i);
        FirebaseDatabase.getInstance().getReference().child("User").child(ten).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Toast.makeText(ThemTaiKhoanActivity.this, "Tên đăng nhập đã tồn tại", Toast.LENGTH_SHORT).show();
                } else if (!dataSnapshot.exists()) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("User");
                    myRef.child(ten).setValue(user);
                    Toast.makeText(ThemTaiKhoanActivity.this, "Thêm tài khoản thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), TaiKhoanActivity.class);
                    startActivity(intent);
                    closeActivity();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void initView() {
        btnThemTK = findViewById(R.id.btn_ThemTK);
        btnDatLai = findViewById(R.id.btn_HuyTK);
        rdbAdmin = findViewById(R.id.rdb_Admin);
        rdbEmployee = findViewById(R.id.rdb_Employee);
        edtTenDN = findViewById(R.id.edt_AddTenDN);
        edtMatKhau = findViewById(R.id.edt_AddMatKhauDN);

    }
    private void closeActivity(){
        this.finish();
    }
}
