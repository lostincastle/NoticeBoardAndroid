package com.shishir.onlinenoticeboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.shishir.onlinenoticeboard.StrictMode.StrictModeC;
import com.shishir.onlinenoticeboard.api.BLL;
import com.shishir.onlinenoticeboard.model.UserModel;

public class RegisterActivity extends AppCompatActivity {
    private EditText username, email, password,mobileno;
    private Button Signup, LinkToLogin;
    Context context;
    //String user,pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        context = this;
        //binding
        username = findViewById(R.id.etsusername);
        email = findViewById(R.id.etsemail);
        password = findViewById(R.id.etspassword);
        mobileno = findViewById(R.id.etsmobile);
        Signup = findViewById(R.id.btn_signup);
        LinkToLogin = findViewById(R.id.btnLinkToLoginScreen);

        LinkToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserModel model = new UserModel(
                        username.getText().toString(),
                        email.getText().toString(),
                        password.getText().toString()
                        ,"dummyimage.jpg",
                        mobileno.getText().toString(),
                        "temporary Addreess",
                        "permanenent Addreess");


                        BLL BLL = new BLL();

                if(BLL.RegisterBLL(model)){
                    Toast.makeText(context,"Successfully Register ",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(context,"User Not Registered",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

}
