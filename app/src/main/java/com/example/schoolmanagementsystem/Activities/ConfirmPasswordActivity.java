package com.example.schoolmanagementsystem.Activities;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.schoolmanagementsystem.DatabaseHelper;
import com.example.schoolmanagementsystem.R;

public class ConfirmPasswordActivity extends AppCompatActivity {
    private EditText pswrd, cpswrd;
    Button btnreset;
    private DatabaseHelper databaseHelper;
    String email;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_password);
        if (getIntent().getExtras() != null)
            email = getIntent().getStringExtra("EMAIL");
        databaseHelper = new DatabaseHelper(ConfirmPasswordActivity.this);
        pswrd = findViewById(R.id.pswrd);
        cpswrd = findViewById(R.id.cpswrd);
        btnreset = findViewById(R.id.btnreset);
        view = findViewById(R.id.view);
        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isValidate()) {
                    updatePassword();
                }
            }
        });
    }

    private boolean isValidate() {
        String Password = pswrd.getText().toString();
        String Cpassword = cpswrd.getText().toString();
        if (Password.isEmpty()) {
            pswrd.setError("Please Enter Password");
            pswrd.requestFocus();
            return false;
        }
        if (Cpassword.isEmpty()) {
            cpswrd.setError("Please Enter Confirm Password");
            cpswrd.requestFocus();
            return false;
        }
        if (!Password.contentEquals(Cpassword)) {
            Toast.makeText(ConfirmPasswordActivity.this, "Passwords Doesn't Match", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!databaseHelper.checkUser(email)) {
            Snackbar.make(view, "Email Doesn't exists", Snackbar.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void emptyIputEditText() {
        pswrd.setText("");
    }

    private void updatePassword() {
        String password = pswrd.getText().toString();
        databaseHelper.checkUser(email, password);
        Toast.makeText(ConfirmPasswordActivity.this, "Password reset successfully", Toast.LENGTH_SHORT).show();
        emptyIputEditText();
        startActivity(new Intent(ConfirmPasswordActivity.this, LoginActivity.class));
        finish();
    }
}
