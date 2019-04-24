package com.example.schoolmanagementsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    private EditText editTextname, editTextemail, editTextpassword;
    private Button btnregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editTextname = findViewById(R.id.editname);
        editTextemail = findViewById(R.id.editemail);
        editTextpassword = findViewById(R.id.editpswrd);
        btnregister = findViewById(R.id.btnlogin);
    }
}
