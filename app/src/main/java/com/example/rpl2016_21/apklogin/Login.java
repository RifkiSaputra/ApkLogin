package com.example.rpl2016_21.apklogin;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    public EditText editemail;
    public EditText editpass;
    public Button login;
    public TextView textViewRegis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editemail = findViewById(R.id.edUser);
        editpass = findViewById(R.id.edPass);
        login = findViewById(R.id.btnLogin);
        textViewRegis = findViewById(R.id.TvRegis);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editemail.getText().toString().isEmpty()) {
                    Toast.makeText(Login.this, "Email tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else if (editpass.getText().toString().isEmpty()) {
                    Toast.makeText(Login.this, "Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else {
                    AndroidNetworking.post("http://192.168.43.238/login/koneksi.php")
                            .addBodyParameter("username", editemail.getText().toString())
                            .addBodyParameter("password", editpass.getText().toString())
                            .setTag("test")
                            .setPriority(Priority.MEDIUM)
                            .build()
                            .getAsJSONObject(new JSONObjectRequestListener() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        JSONObject hasil = response.getJSONObject("hasil");
                                        if (hasil.getBoolean("sukses")) {
                                            Intent intent = new Intent(Login.this, MainMenu.class);
                                            startActivity(intent);
                                            Toast.makeText(Login.this, "sukses", Toast.LENGTH_SHORT).show();
                                            finish();
                                        } else {
                                            Toast.makeText(Login.this, "username or password false", Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        Toast.makeText(Login.this, "gagal", Toast.LENGTH_SHORT).show();

                                    }
                                }

                                @Override
                                public void onError(ANError anError) {
                                    Log.e("", "onError: " + anError.getErrorBody());
                                    Toast.makeText(Login.this, "error" + anError.getErrorDetail(), Toast.LENGTH_SHORT).show();

                                }
                            });
                }
            }
        });
        textViewRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
