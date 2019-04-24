package com.example.schoolmanagementsystem.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.schoolmanagementsystem.DbClasses.DBManager;
import com.example.schoolmanagementsystem.DbClasses.DatabaseHelper;
import com.example.schoolmanagementsystem.R;

import static com.example.schoolmanagementsystem.Constants.Tags.USER_EMAIL;

public class LoginActivity extends AppCompatActivity {
    private EditText email, password;
    private ProgressDialog progressdialog;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (getSupportActionBar() != null)
            getSupportActionBar().hide();
        initViews();
        initObjects();

    }

    public void buttonLogin(View view) {
        if (isValidate()) {
//            showDialogue();
            String Email = email.getText().toString();
            String Password = password.getText().toString();
            if (databaseHelper.checkUser(Email, Password)) {
//                hideDialogue();
                DBManager.setStringPrefs(getApplicationContext(), USER_EMAIL, Email);
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                LoginActivity.this.finish();
                Toast.makeText(LoginActivity.this, "You Have Successfully LogedIn", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Password or Email is incorrect", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean isValidate() {
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

    public void forgotPassword(View view) {
        startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
    }

    private void initViews() {
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
    }

    private void initObjects() {
        databaseHelper = new DatabaseHelper(LoginActivity.this);
        progressdialog = new ProgressDialog(getApplicationContext());
    }
}
