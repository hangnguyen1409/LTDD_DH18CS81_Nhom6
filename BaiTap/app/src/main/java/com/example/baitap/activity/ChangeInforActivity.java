package com.example.baitap.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.example.baitap.R;

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
                            System.out.println("da vao");
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
