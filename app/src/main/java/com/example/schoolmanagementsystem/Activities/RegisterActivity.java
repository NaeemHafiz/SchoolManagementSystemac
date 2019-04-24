package com.example.schoolmanagementsystem.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.schoolmanagementsystem.DbClasses.DBManager;
import com.example.schoolmanagementsystem.DbClasses.DatabaseHelper;
import com.example.schoolmanagementsystem.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.schoolmanagementsystem.Constants.Tags.USER_EMAIL;

public class RegisterActivity extends AppCompatActivity {
    private EditText editTextname, editTextemail, editTextpassword, editTextcpassword;
    private Button btnregister;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();
        initObjects();
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidate()) {
                    setRegister();
                }
            }
        });

    }

    private void initObjects() {
        databaseHelper = new DatabaseHelper(RegisterActivity.this);
    }

    private void initViews() {
        if (getSupportActionBar() != null)
            getSupportActionBar().hide();
        editTextname = findViewById(R.id.editname);
        editTextemail = findViewById(R.id.editemail);
        editTextpassword = findViewById(R.id.editpswrd);
        editTextcpassword = findViewById(R.id.editcpswrd);
        btnregister = findViewById(R.id.btnregister);
    }

    private void setRegister() {
        String name = editTextname.getText().toString();
        String email = editTextemail.getText().toString();
        String password = editTextpassword.getText().toString();
        boolean save = databaseHelper.saveDataSqLite(name, email, password);
        if (save) {
            Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
            DBManager.setStringPrefs(this, USER_EMAIL, email);
            startActivity(intent);
            RegisterActivity.this.finish();
            Toast.makeText(RegisterActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean isValidate() {
        String name = editTextname.getText().toString();
        String email = editTextemail.getText().toString();
        String password = editTextpassword.getText().toString();
        String cpassword = editTextcpassword.getText().toString();

        if (!password.contentEquals(cpassword)) {
            Toast.makeText(RegisterActivity.this, "Passwords Doesn't Match", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (cpassword.isEmpty()) {
            editTextcpassword.setError("Please Enter Confirm Password");
            editTextcpassword.requestFocus();
            return false;
        }
        if (!databaseHelper.checkEmail(email)) {
            Toast.makeText(this, "Email Already Exists", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (name.isEmpty()) {
            editTextname.setError("Please Enter Name");
            editTextname.requestFocus();
            return false;
        }
        if (password.length() < 6) {
            editTextpassword.setError("Password cannot be less than 6 characters!");
            editTextpassword.requestFocus();
            return false;
        }
        if (email.isEmpty()) {
            editTextemail.setError("Please Enter Email");
            editTextemail.requestFocus();
            return false;
        }
        if (password.isEmpty()) {
            editTextpassword.setError("Please Enter Password");
            editTextpassword.requestFocus();
            return false;
        }
        if (!isEmailValidate(email)) {
            editTextemail.setError("Please Enter Correct Email");
            editTextemail.requestFocus();
            return false;
        }
        return true;
    }

    public static boolean isEmailValidate(String email) {
        if (email != null) {
            Pattern pattern;
            Matcher matcher;
            final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            pattern = Pattern.compile(EMAIL_PATTERN);
            matcher = pattern.matcher(email);
            return matcher.matches();
        } else {
            return false;
        }
    }
}
