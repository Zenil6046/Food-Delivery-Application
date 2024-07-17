package com.example.fooddel2;



import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import androidx.appcompat.app.AlertDialog;
        import androidx.appcompat.app.AppCompatActivity;

        import android.annotation.SuppressLint;
        import android.app.Dialog;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.os.Handler;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

public class LoginPage extends AppCompatActivity {
    Button login,dialog_btn;
    EditText name,dialog_edt;
    TextView sign,forgot;
    EditText pass;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        login = findViewById(R.id.login);
        sign = findViewById(R.id.sign);
        pass = findViewById(R.id.password);
        forgot = findViewById(R.id.forgot);
        name = findViewById(R.id.name);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iHome;
                DbHelper dbHelper = new DbHelper(LoginPage.this);
                //dbHelper.addData("100","ansh","56");
                String conf_pass = dbHelper.fetch(name.getText().toString());


                if (pass.getText().toString().equals(conf_pass)) {
                    SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putBoolean("Flag", true);

                    editor.apply();
                    iHome = new Intent(LoginPage.this, Home.class);
                    String contact = dbHelper.fetch(name.getText().toString());
//                    iHome.putExtra("username", (CharSequence) name);
//                    iHome.putExtra("contact",contact);
                    startActivity(iHome);
                } else {
                    Toast.makeText(LoginPage.this, "Invalid Password Or UserName", Toast.LENGTH_SHORT).show();
                }
            }
        });

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPage.this,Sign.class);
                startActivity(intent);

            }
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(LoginPage.this);
                dialog.setContentView(R.layout.dialog_forget_password);


                dialog_edt = dialog.findViewById(R.id.dialog_edt);
                dialog_btn = dialog.findViewById(R.id.btn_ok);
                dialog_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String contact = dialog_edt.getText().toString();

                        DbHelper dbHelper = new DbHelper(getApplicationContext());

                        String P = dbHelper.fetchForgot(contact);
                        if(P != null){
                            Toast.makeText(LoginPage.this, "Your Password is:- "+P, Toast.LENGTH_LONG).show();
                            dialog.dismiss();

                        }
                        else{
                            Toast.makeText(LoginPage.this, "Invalid Uname", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }
                    }
                });

                dialog.show();
            }
        });


    }
}