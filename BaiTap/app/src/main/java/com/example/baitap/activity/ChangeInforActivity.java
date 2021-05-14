package com.example.baitap.activity;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.baitap.model.ModelChangeInfo;
import com.example.baitap.model.ModelUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeInforActivity extends AppCompatActivity {
    Button butnchangeinfor,butnback;
    EditText edittextemail,edittextuser,edittextname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeinfor);
        butnchangeinfor = findViewById(R.id.butnchangeinfor);
        butnback = findViewById(R.id.butnback);
        edittextemail = findViewById(R.id.edittextemail);
        edittextuser = findViewById(R.id.edittextuser);
        edittextname = findViewById(R.id.edittextname);


    }
    @Override
    protected void onStart() {
        super.onStart();
        butnchangeinfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.butnchangeinfor:
                        if(check(view)){
                            ApiInterface apiInterface;
                            apiInterface = RetrofitClient.getRetrofitClient().create(ApiInterface.class);
                            ModelChangeInfo modelChangeInfo = new ModelChangeInfo();
                            modelChangeInfo.setEmail(edittextemail.getText().toString());
                            modelChangeInfo.setName(edittextname.getText().toString());
                            modelChangeInfo.setUsername(edittextuser.getText().toString());
                            SessionManagement sessionManagement = new SessionManagement(ChangeInforActivity.this);
                            Call<Mess> call = apiInterface.change_info(sessionManagement.getSession(),modelChangeInfo);
                            call.enqueue(new Callback<Mess>() {
                                @Override
                                public void onResponse(Call<Mess> call, Response<Mess> response) {
                                    if(response.isSuccessful()){
                                        Toast.makeText(getApplicationContext(),response.body().getMess(),Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(ChangeInforActivity.this, UserProfile.class));
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(),response.body().getMess(),Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Mess> call, Throwable t) {

                                }
                            });
                        }else
                            System.out.println("err");

                        break;
                    default:
                        break;
                }

            }
        });
        butnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChangeInforActivity.this, UserProfile.class));
            }
        });

    }

    private Boolean validateName() {
        String val =edittextname .getText().toString();

        if (val.isEmpty()) {
            edittextname.setError("Field cannot be empty");
            return false;
        }
        else {
            edittextname.setError(null);
            return true;
        }
    }
    private boolean validateUsername() {
        String val = edittextuser.getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (val.isEmpty()) {
            edittextuser.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 15) {
            edittextuser.setError("Username too long");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            edittextuser.setError("White Spaces are not allowed");
            return false;
        } else {
            edittextuser.setError(null);
            return true;
        }
    }
    private Boolean validateEmail() {
        String val = edittextemail.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            edittextemail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            edittextemail.setError("Only using gmail exp @gmail.com");
            return false;
        } else {
            edittextemail.setError(null);
            return true;
        }
    }
    public boolean check(View view) {
        return (validateName() & validateEmail() & validateUsername());
    }
}
