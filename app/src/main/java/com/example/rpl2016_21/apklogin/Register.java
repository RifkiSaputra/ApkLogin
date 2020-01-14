package com.example.rpl2016_21.apklogin;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

public class Register extends AppCompatActivity {

    public EditText editusername;
    public EditText editpassword;
    public EditText editconfirmpass;
    public Button btnregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editusername = findViewById(R.id.edtuser);
        editpassword = findViewById(R.id.edtpassword);
        editconfirmpass = findViewById(R.id.edtconfirmpassword);
        btnregister = findViewById(R.id.btnRegis);

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editusername.getText().toString().isEmpty()) {
                    Toast.makeText(Register.this, "Username tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else if (editpassword.getText().toString().isEmpty()) {
                    Toast.makeText(Register.this, "Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else if(!editpassword.getText().toString().equals(editconfirmpass.getText().toString())) {
                    Toast.makeText(Register.this, "Password harus sama", Toast.LENGTH_SHORT).show();
                }else {
                    AndroidNetworking.post("http://192.168.43.238/login/insert.php")
                            .addBodyParameter("username", editusername.getText().toString())
                            .addBodyParameter("password", editpassword.getText().toString())
                            .setTag("test")
                            .setPriority(Priority.MEDIUM)
                            .build()
                            .getAsJSONObject(new JSONObjectRequestListener() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    JSONObject hasil = null;
                                    try {
                                        hasil = response.getJSONObject("hasil");
                                        boolean respon = hasil.getBoolean("respon");
                                        if (respon) {
                                            Toast.makeText(getApplicationContext(), "sukses daftar", Toast.LENGTH_SHORT).show();
                                            finish();
                                        } else {
                                            Toast.makeText(getApplicationContext(), "gagal", Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        boolean respon = hasil.getBoolean("sukses");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onError(ANError error) {
                                    // handle error
                                }
                            });
                }
            }
        });
    }
}
