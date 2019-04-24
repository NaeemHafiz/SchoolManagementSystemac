package com.example.schoolmanagementsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.schoolmanagementsystem.Tags.USER_NAME;

public class LoginActivity extends AppCompatActivity {
    private EditText uname, upassword;
    ProgressDialog progressdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        uname = findViewById(R.id.uname);
        upassword = findViewById(R.id.upassword);
        progressdialog = new ProgressDialog(getApplicationContext());
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("AFAQ-MDB");
        }
    }

    public void buttonLogin(View view) {
        if (isValid()) {
//            showDialogue();
            String Name = uname.getText().toString();
            String Password = upassword.getText().toString();
            if (Name.equalsIgnoreCase("naeem") && Password.equalsIgnoreCase("123")) {
                hideDialogue();
                DBManager.setStringPrefs(getApplicationContext(), USER_NAME, Name);
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                LoginActivity.this.finish();
            } else {
                Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean isValid() {
        String Name = uname.getText().toString();
        String Password = upassword.getText().toString();
        if (Name.isEmpty()) {
            uname.setError("Please Enter Name");
            uname.requestFocus();
            return false;
        }
        if (Password.isEmpty()) {
            upassword.setError("Please Enter Password");
            upassword.requestFocus();
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
    }
}
