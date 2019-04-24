package com.example.schoolmanagementsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.schoolmanagementsystem.Tags.USER_EMAIL;

public class LoginActivity extends AppCompatActivity {
    private EditText email, password;
    ProgressDialog progressdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        progressdialog = new ProgressDialog(getApplicationContext());
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("APPLICATION");
        }
    }

    public void buttonLogin(View view) {
        if (isValid()) {
//            showDialogue();
            String Email = email.getText().toString();
            String Password = password.getText().toString();
            if (Email.equalsIgnoreCase("naeem@yahoo.com") && Password.equalsIgnoreCase("123")) {
                hideDialogue();
                DBManager.setStringPrefs(getApplicationContext(), USER_EMAIL, Email);
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                LoginActivity.this.finish();
            } else {
                Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean isValid() {
        String Name = email.getText().toString();
        String Password = password.getText().toString();
        if (Name.isEmpty()) {
            email.setError("Please Enter Name");
            email.requestFocus();
            return false;
        }
        if (Password.isEmpty()) {
            password.setError("Please Enter Password");
            password.requestFocus();
            return false;
        }
        return true;
    }

    private void showDialogue() {
        if (progressdialog != null) {
            progressdialog.setMessage("Please Wait....");
            progressdialog.show();
        }
    }

    private void hideDialogue() {
        if (progressdialog != null)
            progressdialog.dismiss();
    }

    public void btnmovetoregisteractivity(View view) {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        LoginActivity.this.finish();
    }
}
