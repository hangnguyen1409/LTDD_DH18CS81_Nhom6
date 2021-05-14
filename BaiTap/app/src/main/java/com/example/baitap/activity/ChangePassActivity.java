package com.example.baitap.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.baitap.R;
import com.example.baitap.SessionManagement;
import com.example.baitap.api.ApiInterface;
import com.example.baitap.api.RetrofitClient;
import com.example.baitap.model.Mess;
import com.example.baitap.model.ModelChangePass;
import com.example.baitap.model.ModelUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassActivity extends AppCompatActivity {
    Button butnchangepass,butnback;
    EditText edittextnewpassword,edittextconfirmpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepass);
        butnchangepass = findViewById(R.id.butnchangepass);
        butnback = findViewById(R.id.butnback);
        edittextnewpassword = findViewById(R.id.edittextnewpassword);
        edittextconfirmpassword = findViewById(R.id.edittextconfirmpassword);

    }
    @Override
    protected void onStart() {
        super.onStart();
        butnchangepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkIsEmpty(edittextnewpassword)||checkIsEmpty(edittextconfirmpassword)){
                    Toast.makeText(getApplicationContext(),"Please enter enough information",Toast.LENGTH_SHORT).show();
                }
                else if(CheckCorrectPass(edittextconfirmpassword)==false){
                    Toast.makeText(getApplicationContext(),"Please enter match password",Toast.LENGTH_SHORT).show();
                    edittextconfirmpassword.setError("password does not match");
                }
                else {
                    SessionManagement sessionManagement = new SessionManagement(ChangePassActivity.this);
                    ApiInterface apiInterface;
                    apiInterface = RetrofitClient.getRetrofitClient().create(ApiInterface.class);
                   String pass = edittextconfirmpassword.getText().toString();
                    ModelChangePass modelChangePass = new ModelChangePass();
                    modelChangePass.setPassword(pass);
                    Call<Mess> call = apiInterface.change_pass(sessionManagement.getSession(),modelChangePass);
                    call.enqueue(new Callback<Mess>() {
                        @Override
                        public void onResponse(Call<Mess> call, Response<Mess> response) {
                            if(response.isSuccessful()){
                                sessionManagement.removeSession();
                                if(sessionManagement.getSession()==-1) {
                                    startActivity(new Intent(ChangePassActivity.this, LoginActivity.class));
                                    Toast.makeText(getApplicationContext(), response.body().getMess(), Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                Toast.makeText(getApplicationContext(),response.body().getMess(),Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Mess> call, Throwable t) {

                        }
                    });
                }

            }
        });
        butnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChangePassActivity.this, UserProfile.class));
            }
        });

    }
    public Boolean checkIsEmpty(EditText editText){
        if(editText.getText().toString().isEmpty()){
            editText.setError("Field cannot be empty");
        }
        return editText.getText().toString().isEmpty();

    }
    public Boolean CheckCorrectPass(EditText editText){
        return editText.getText().toString().equals(edittextnewpassword.getText().toString());
    }
}
