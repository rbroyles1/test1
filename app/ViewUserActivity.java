package com.example.rxbro.test1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by rxbro on 6/14/2017.
 */

public class ViewUserActivity extends AppCompatActivity {
    ImageView userImage;
    TextView userData;
    User myUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);
        userImage = (ImageView)findViewById(R.id.imageIV);
        userData = (TextView)findViewById(R.id.userTv);
        myUser = getIntent().getParcelableExtra("user");
        Glide.with(this)
                .load(myUser.getImage())
                .into(userImage);
        userData.setText("Name: " + myUser.getName() + "\nAddress:  " + myUser.getAddress() + "\nEmail:  " + myUser.getEmail() + "\nGender: " + myUser.getGender() + "\nPhone: " + myUser.getPhone() + "\nCell:  " + myUser.getCell() + "\nDate of birth:  " + myUser.getDateOfBirth() + "\nNationality:  " + myUser.getNationality() + "\nRegistered:  " + myUser.getRegistered());
    }
}
