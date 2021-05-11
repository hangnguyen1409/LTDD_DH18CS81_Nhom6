package com.example.baitap.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.baitap.R;
import com.example.baitap.api.ApiInterface;
import com.example.baitap.api.RetrofitClient;
import com.example.baitap.model.Mess;
import com.example.baitap.model.SignupResponse;





import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
    EditText edtUser, edtPassword, edtEmail, edtName;
    Button btnSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        edtUser = findViewById(R.id.edittextuser);
        edtEmail = findViewById(R.id.edittextemail);
        edtPassword = findViewById(R.id.edittextpassword);
        edtName = findViewById(R.id.edittextname);

        btnSignUp = findViewById(R.id.butndangki);



        btnSignUp.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                switch (view.getId()) {
                    case R.id.butndangki:
                        if(check(view)){
                            if(signupUser())
                                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                        }

                        break;
                    default:
                        break;
                }
            }


            private Boolean signupUser() {

                String username = edtUser.getText().toString();
                String password = edtPassword.getText().toString();
                String name = edtName.getText().toString();
                String email = edtEmail.getText().toString();
                SignupResponse signupResponse = new SignupResponse(username, password,name, email);

                ApiInterface apiInterface;
                apiInterface = RetrofitClient.getRetrofitClient().create(ApiInterface.class);
                Call<Mess> call = apiInterface.signupUser(signupResponse);

                call.enqueue(new Callback<Mess>() {
                    @Override
                    public void onResponse(Call<Mess> call, Response<Mess> response) {
                        if(response.isSuccessful()) {
                            System.out.println("success!");
                            Toast.makeText(getBaseContext(),response.body().getMess(),Toast.LENGTH_SHORT).show();
                        }else {
                            int statusCode  = response.code();
                            Toast.makeText(getApplicationContext(),response.message(),Toast.LENGTH_SHORT).show();
                            System.out.println("fail");
                        }

                    }

                    @Override
                    public void onFailure(Call<Mess> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Oh no, Have an error!!! Please try again",Toast.LENGTH_SHORT).show();
                    }
                });


                return call.isExecuted();


            }
            private Boolean validateName() {
                String val = edtName.getText().toString();

                if (val.isEmpty()) {
                    edtName.setError("Field cannot be empty");
                    return false;
                }
                else {
                    edtName.setError(null);
                    return true;
                }
            }
            private boolean validateUsername() {
                String val = edtUser.getText().toString();
                String noWhiteSpace = "\\A\\w{4,20}\\z";

                if (val.isEmpty()) {
                    edtUser.setError("Field cannot be empty");
                    return false;
                } else if (val.length() >= 15) {
                    edtUser.setError("Username too long");
                    return false;
                } else if (!val.matches(noWhiteSpace)) {
                    edtUser.setError("White Spaces are not allowed");
                    return false;
                } else {
                    edtUser.setError(null);
                    return true;
                }
            }
            private Boolean validateEmail() {
                String val = edtEmail.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (val.isEmpty()) {
                    edtEmail.setError("Field cannot be empty");
                    return false;
                } else if (!val.matches(emailPattern)) {
                    edtEmail.setError("Invalid email address");
                    return false;
                } else {
                    edtEmail.setError(null);
                    return true;
                }
            }
            private Boolean validatePassword() {
                String val = edtPassword.getText().toString();
                String passwordVal = "^" +
                        "(?=.*[a-zA-Z])" +      //any letter
                        "(?=.*[@#$%^&+=])" +    //at least 1 special character
                        "(?=\\S+$)" +           //no white spaces
                        ".{4,}" +               //at least 4 characters
                        "$";

                if (val.isEmpty()) {
                    edtPassword.setError("Field cannot be empty");
                    return false;
                } else if (!val.matches(passwordVal)) {
                    edtPassword.setError("Password is too weak");
                    return false;
                } else {
                    edtPassword.setError(null);
                    return true;
                }
            }
            public boolean check(View view) {
                return (!validateName() &!validatePassword() & !validateEmail() & !validateUsername());
            }


        });


    }




}
