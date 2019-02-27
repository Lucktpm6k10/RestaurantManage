package com.example.restaurantmanage.Activity.TaiKhoan;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.restaurantmanage.Adapter.UserAdapter;
import com.example.restaurantmanage.R;
import com.example.restaurantmanage.Model.User;
import com.example.restaurantmanage.Ulti.ItemClickListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TaiKhoanActivity extends AppCompatActivity {
    RecyclerView userRecyclerView;
    UserAdapter userAdapter;
    ArrayList<User> listUser;
    private ItemClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_khoan);
        initActionbar();
        initView();
        listenerClickItem();
        initRecyclerview();
        initDataFromFB();
    }

    private void listenerClickItem() {
        listener = new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Toast.makeText(TaiKhoanActivity.this, "TRUE", Toast.LENGTH_SHORT).show();
                Log.d("TTTTTTTTTTTTTTTTTTTTTT", "TRUEE");
            }
        };
    }

    //Lấy dữ liệu từ firebase
    private void initDataFromFB() {
        FirebaseDatabase.getInstance().getReference().child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    listUser.clear();
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        listUser.add(new User(data.getValue(User.class).getTenTaiKhoan(), data.getValue(User.class).getMatKhau(), data.getValue(User.class).getChucVu()));
                    }
                    userAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void initView() {
        userRecyclerView = findViewById(R.id.rcv_TaiKhoan);
        listUser = new ArrayList<>();
    }

    //Xử lý menu và action bar
    private void initActionbar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Tài Khoản"); //Thiết lập tiêu đề nếu muốn
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu1:
                Intent intent = new Intent(getApplicationContext(), ThemTaiKhoanActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //Xử lý Recyvlerview
    private void initRecyclerview() {


        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        userRecyclerView.setLayoutManager(layoutManager);
        userAdapter = new UserAdapter(listUser, getApplicationContext());
        userRecyclerView.setAdapter(userAdapter);

    }


}
