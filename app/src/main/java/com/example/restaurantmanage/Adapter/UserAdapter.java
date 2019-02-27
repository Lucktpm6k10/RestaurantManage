package com.example.restaurantmanage.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restaurantmanage.Activity.TaiKhoan.CapNhatTaiKhoanActivity;
import com.example.restaurantmanage.Activity.More.MainActivity;
import com.example.restaurantmanage.Model.User;
import com.example.restaurantmanage.R;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private ArrayList<User> listUser;
    private Context context;
    Dialog dialog;

    public UserAdapter(ArrayList<User> listUser, Context context) {
        this.listUser = listUser;
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_user, viewGroup, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final UserViewHolder userViewHolder, final int i) {

        userViewHolder.tvItemTenTK.setText(listUser.get(i).getTenTaiKhoan());
        userViewHolder.tvItemMatKhauTK.setText(listUser.get(i).getMatKhau());
        if (listUser.get(i).getChucVu() == 1) {
            userViewHolder.tvItemChucVuTK.setText("Nhân Viên");
        } else {
            userViewHolder.tvItemChucVuTK.setText("Quản Lý");
        }
        userViewHolder.btnDeleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String taikhoan = listUser.get(i).getTenTaiKhoan();
                Log.e("aaa",taikhoan +""+ MainActivity.tenDNFinal);
                if (taikhoan.equals(MainActivity.tenDNFinal)==true) {
                    Toast.makeText(context, "Không được xóa tài khoản bạn đang sử dụng", Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(userViewHolder.itemView.getContext());
                    builder.setMessage("Bạn có muốn xóa tài khoản không?");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            FirebaseDatabase.getInstance().getReference().child("User").child(taikhoan).removeValue();
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
            }
        });
        userViewHolder.btnEditItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ten = listUser.get(i).getTenTaiKhoan();
                String matkhau = listUser.get(i).getMatKhau();
                int chucvu = listUser.get(i).getChucVu();
                Intent intent = new Intent(context, CapNhatTaiKhoanActivity.class);
                intent.putExtra("Ten", ten);
                intent.putExtra("Matkhau", matkhau);
                intent.putExtra("Chucvu", chucvu);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }


    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tvItemTenTK, tvItemMatKhauTK, tvItemChucVuTK;
        ImageButton btnDeleteItem, btnEditItem;

        public UserViewHolder(View itemView) {
            super(itemView);
            tvItemTenTK = (TextView) itemView.findViewById(R.id.tv_itemTenTK);
            tvItemMatKhauTK = (TextView) itemView.findViewById(R.id.tv_itemMatKhauTK);
            tvItemChucVuTK = (TextView) itemView.findViewById(R.id.tv_itemChucVuTK);
            btnDeleteItem = itemView.findViewById(R.id.btn_itemDeleteTK);
            btnEditItem = itemView.findViewById(R.id.btn_itemEditTK);

        }

    }
}
