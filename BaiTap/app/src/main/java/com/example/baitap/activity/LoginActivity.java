package com.example.baitap.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.baitap.R;
import com.example.baitap.SessionManagement;
import com.example.baitap.api.ApiInterface;
import com.example.baitap.api.RetrofitClient;
import com.example.baitap.model.LoginResponse;
import com.example.baitap.model.ModelLogin;
import com.example.baitap.model.ModelUser;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText edtUser, edtPassword;
    Button btnLogin,butnhome;
    //    TextView tvSignup;
    String username, password;
//    private List<ModelUser> mListModelUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUser = findViewById(R.id.edittextuser);
        edtPassword = findViewById(R.id.edittextpassword);
        btnLogin = findViewById(R.id.butndangnhap);
        butnhome = findViewById(R.id.butnhome);
//      tvSignup = findViewById(R.id.textViewSignUp);

        TextView btn = findViewById(R.id.textViewSignUp);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });
        butnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = edtUser.getText().toString().trim();
                password = edtPassword.getText().toString().trim();
//                        edtUser.setText(username);
//                        edtPassword.setText(password);

                ModelLogin login = new ModelLogin(username, password);
                userLogin(login);
            }

            private void userLogin(ModelLogin login) {

                if (username.isEmpty()) {
                    edtUser.setError("Username is required");
                    edtUser.requestFocus();
                    return;
                }

                if (password.isEmpty()) {
                    edtPassword.setError("Password is required");
                    edtPassword.requestFocus();
                    return;
                }

                ApiInterface apiInterface;
                apiInterface = RetrofitClient.getRetrofitClient().create(ApiInterface.class);

                Call<LoginResponse> call = apiInterface.userLogin(login);

                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        LoginResponse loginResponse = response.body();
                        if (response.isSuccessful()) {
                            if (loginResponse.getMess().equals("Login Successful!")) {
                                SessionManagement sessionManagement = new SessionManagement(LoginActivity.this);
                                sessionManagement.saveSession(response.body().user_login);
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            }
                            Toast.makeText(LoginActivity.this, loginResponse.getMess(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, loginResponse.getMess(), Toast.LENGTH_SHORT).show();
                            System.out.println(loginResponse);
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });
    }
    private void checkSession() {
        //check if user is logged in
        //if user is logged in --> move to mainActivity

        SessionManagement sessionManagement = new SessionManagement(this);
        int userID = sessionManagement.getSession();

        if(userID != -1){
            //user id logged in and so move to mainActivity
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
        else{
            //do nothing
        }
    }

}
