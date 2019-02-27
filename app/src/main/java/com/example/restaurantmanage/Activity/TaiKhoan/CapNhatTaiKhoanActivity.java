package com.example.restaurantmanage.Activity.TaiKhoan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restaurantmanage.Activity.More.MainActivity;
import com.example.restaurantmanage.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CapNhatTaiKhoanActivity extends AppCompatActivity {

    private String ten, matKhau;
    private int chucVu;
    private Button btnEdit, btnHuy;
    EditText edtEditMK;
    TextView tvEditTK;
    RadioButton rdbEditAdmin, rdbEditEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap_nhat_tai_khoan);
        getDataFromIntent();
        initView();
        initListener();
    }

    private void initListener() {
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int aChucVu;
                if(rdbEditAdmin.isChecked()==true){
                    aChucVu =0;
                }else {
                    aChucVu=1;
                }
                Log.e("aaa",aChucVu+"/"+chucVu);
                if ((tvEditTK.getText().toString()).equals(MainActivity.tenDNFinal) == true && aChucVu != chucVu) {
                    Toast.makeText(CapNhatTaiKhoanActivity.this, "Không được thay đổi trường chức vụ đối với tài khoản này",
                            Toast.LENGTH_SHORT).show();
                    rdbEditAdmin.setChecked(true);
                } else {
                    showAlertDialog();
                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initView() {
        btnEdit = findViewById(R.id.btn_EditThemTK);
        btnHuy = findViewById(R.id.btn_EditHuyTK);
        tvEditTK = findViewById(R.id.edt_EditTenDN);
        edtEditMK = findViewById(R.id.edt_EditMatKhauDN);
        rdbEditAdmin = findViewById(R.id.rdb_EditAdmin);
        rdbEditEmployee = findViewById(R.id.rdb_EditEmployee);

        //Set view
        tvEditTK.setText(ten);
        edtEditMK.setText(matKhau);
        if (chucVu == 0) {
            rdbEditAdmin.setChecked(true);
        } else if (chucVu == 1) {
            rdbEditEmployee.setChecked(true);
        }
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        ten = intent.getStringExtra("Ten");
        matKhau = intent.getStringExtra("Matkhau");
        chucVu = (int) intent.getIntExtra("Chucvu", 0);
    }

    public void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cập nhật thông tin tài khoản");
        builder.setMessage("Bạn có muốn tiếp tục lưu không?");
        builder.setCancelable(false);
        builder.setPositiveButton("Tiếp tục", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                CapNhatTaiKhoan();
                Toast.makeText(CapNhatTaiKhoanActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), TaiKhoanActivity.class);
                startActivity(intent);
                closeActivity();

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

    private void CapNhatTaiKhoan() {
        String newTen = tvEditTK.getText().toString();
        String newMatKhau = edtEditMK.getText().toString();
        int newChucVu = 0;
        if (rdbEditEmployee.isChecked()) {
            newChucVu = 1;
        } else if (rdbEditAdmin.isChecked()) {
            newChucVu = 0;
        }
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("User");
        myRef.child(newTen).child("chucVu").setValue(newChucVu);
        myRef.child(newTen).child("matKhau").setValue(newMatKhau);
    }

    private void closeActivity() {
        this.finish();
    }
}
