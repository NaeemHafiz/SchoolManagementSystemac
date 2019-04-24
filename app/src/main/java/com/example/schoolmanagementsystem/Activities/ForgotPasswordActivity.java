package com.example.schoolmanagementsystem.Activities;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.schoolmanagementsystem.DbClasses.DatabaseHelper;
import com.example.schoolmanagementsystem.R;

public class ForgotPasswordActivity extends AppCompatActivity {
    private EditText editTextEmail;
    Button forgotPassword;
    private DatabaseHelper databaseHelper;
    private View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        initViews();
        initObjects();
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidate())
                    verifyFromSQLite();
            }
        });
    }

    private void initObjects() {
        databaseHelper = new DatabaseHelper(ForgotPasswordActivity.this);
    }

    private void initViews() {
        editTextEmail = findViewById(R.id.edittextemail);
        forgotPassword = findViewById(R.id.forgotPassword);
        v = findViewById(R.id.view);
    }

    private void verifyFromSQLite() {
        String Email = editTextEmail.getText().toString().trim();
        if (databaseHelper.checkUser(Email)) {
            Intent intent = new Intent(ForgotPasswordActivity.this, ConfirmPasswordActivity.class);
            intent.putExtra("EMAIL", Email);
            emptyIputEditText();
            startActivity(intent);
            finish();
        } else {
            Snackbar.make(v, "Wrong Email or Password", Snackbar.LENGTH_LONG).show();
        }
    }

    private boolean isValidate() {
        String email = editTextEmail.getText().toString();
        if (email.isEmpty()) {
            editTextEmail.setError("Please Enter Password");
            editTextEmail.requestFocus();
            return false;
        }
        return true;
    }

    private void emptyIputEditText() {
        editTextEmail.setText("");
    }
}
