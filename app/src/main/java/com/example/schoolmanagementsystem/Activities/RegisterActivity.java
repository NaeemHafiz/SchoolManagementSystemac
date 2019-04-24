package com.example.schoolmanagementsystem.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.schoolmanagementsystem.DBManager;
import com.example.schoolmanagementsystem.DatabaseHelper;
import com.example.schoolmanagementsystem.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.schoolmanagementsystem.Tags.USER_EMAIL;

public class RegisterActivity extends AppCompatActivity {
    private EditText editTextname, editTextemail, editTextpassword;
    private Button btnregister;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editTextname = findViewById(R.id.editname);
        editTextemail = findViewById(R.id.editemail);
        editTextpassword = findViewById(R.id.editpswrd);
        btnregister = findViewById(R.id.btnregister);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidate()) {
                    setRegister();
                }
            }
        });
    }

    private void setRegister() {
        String username = editTextname.getText().toString();
        String useremail = editTextemail.getText().toString();
        String userpassword = editTextpassword.getText().toString();
        boolean save = databaseHelper.saveDataSqLite(username, useremail, userpassword);
        if (save) {
            Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
            DBManager.setStringPrefs(this, USER_EMAIL, useremail);
            startActivity(intent);
            Toast.makeText(this, "Inserted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Not Inserted", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidate() {
        String studentname = editTextname.getText().toString();
        String studentemail = editTextemail.getText().toString();
        String studentpassword = editTextpassword.getText().toString();
        if (studentname.isEmpty()) {
            editTextname.setError("Please Enter Name");
            editTextname.requestFocus();
            return false;
        }
        if (studentemail.isEmpty()) {
            editTextemail.setError("Please Enter Email");
            editTextemail.requestFocus();
            return false;
        }
        if (studentpassword.isEmpty()) {
            editTextpassword.setError("Please Enter Password");
            editTextpassword.requestFocus();
            return false;
        }
        if (!isEmailValidate(studentemail)) {
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
