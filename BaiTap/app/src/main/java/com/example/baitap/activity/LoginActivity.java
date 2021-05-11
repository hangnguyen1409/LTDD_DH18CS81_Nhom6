package com.example.baitap.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.baitap.R;

public class LoginActivity extends AppCompatActivity {

    EditText edtUser, edtPassword;
    Button btnLogin;
    TextView tvSignup;
    String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUser = findViewById(R.id.edittextuser);
        edtPassword = findViewById(R.id.edittextpassword);
        btnLogin = findViewById(R.id.butndangnhap);
        tvSignup = findViewById(R.id.textViewSignUp);

        ControlButton();
        TextView btn = findViewById(R.id.textViewSignUp);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });
    }


    private void ControlButton() {
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                username = edtUser.getText().toString().trim();
                password = edtPassword.getText().toString().trim();

                edtUser.setText(username);
                edtPassword.setText(password);
                edtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

                if (edtUser.getText().length() != 0 && edtPassword.getText().length() != 0)
                    if (edtUser.getText().toString().equals(username) && edtPassword.getText().toString().equals(password)) {
                        Toast.makeText(LoginActivity.this, "Successful Login", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);

                   /* } else if (edtUser.getText().toString().equals("Ngan") && edtPassword.getText().toString().equals("123")) {
                        Toast.makeText(LoginActivity.this, "Successful Login", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);*/
                    } else {
                        Toast.makeText(LoginActivity.this, "You have failed login", Toast.LENGTH_SHORT).show();
                    }
                else {
                    Toast.makeText(LoginActivity.this, "Please enter enough information ", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}