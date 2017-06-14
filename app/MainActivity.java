package com.example.rxbro.test1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rxbro on 6/14/2017.
 */

public class MainActivity extends AppCompatActivity implements UserRecyclerAdapter.ItemClickListener {
    private static final String TAG = MainActivity.class.getSimpleName() + "_TAG";
    private static final String RETROFIT_URL = "https://randomuser.me";
    ArrayList<User> userList;
    RecyclerView recyclerView;
    UserRecyclerAdapter adapter;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            postResult();
        }
    };
    private void postResult() {
        adapter = new UserRecyclerAdapter(userList, this);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.studentRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userList = new ArrayList<>();
        doRetrofitNetworkCall();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter = new UserRecyclerAdapter(userList, this);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
    }
    private void doRetrofitNetworkCall() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RETROFIT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService service = retrofit.create(RetrofitService.class);
        retrofit2.Call<RandomAPI> call = service.getRandomUser();
        call.enqueue(new retrofit2.Callback<RandomAPI>() {
            @Override
            public void onResponse(Call<RandomAPI> call, Response<RandomAPI> response) {
                if (response.isSuccessful()) {
                    Message msg = handler.obtainMessage();
                    Bundle data = new Bundle();
                    String currentName = "";
                    String currentAddress = "";
                    String currentEmail = "";
                    String currentGender = "";
                    String currentPhone = "";
                    String currentCell = "";
                    String currentDateOfBirth = "";
                    String currentNationality = "";
                    String currentRegister = "";
                    String currentImage = "";

                    RandomAPI randomAPI = response.body();
                    for (Result result : randomAPI.getResults()) {
                        currentName = result.getName().getTitle() + " " + result.getName().getFirst() + " " + result.getName().getLast();
                        currentAddress = result.getLocation().getStreet() + " " + result.getLocation().getCity() + ", " + result.getLocation().getState() + " " + result.getLocation().getPostcode();
                        currentEmail = result.getEmail();
                        currentGender = result.getGender();
                        currentPhone = result.getPhone();
                        currentCell = result.getCell();
                        currentDateOfBirth = result.getDob();
                        currentNationality = result.getNat();
                        currentRegister = result.getRegistered();
                        currentImage = result.getPicture().getMedium();
                        User currentUser = new User(currentName, currentAddress, currentEmail, currentGender, currentPhone, currentCell, currentDateOfBirth, currentNationality, currentRegister, currentImage);
                        userList.add(currentUser);
                    }
                    msg.setData(data);
                    handler.sendMessage(msg);
                } else {}
            }

            @Override
            public void onFailure(Call<RandomAPI> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(MainActivity.this, ViewUserActivity.class);
        intent.putExtra("user", userList.get(position));
        startActivity(intent);
    }

}
