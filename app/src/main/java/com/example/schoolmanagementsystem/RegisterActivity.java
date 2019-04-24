package com.example.schoolmanagementsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        btnregister = findViewById(R.id.btnlogin);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValid()) {
                    setRegister();
                }
            }
        });
    }

    private void setRegister() {
        String studentname = editTextname.getText().toString();
        String studentsirnamename = editTextemail.getText().toString();
        String studentmarks = editTextpassword.getText().toString();
        boolean save = databaseHelper.saveDataSqLite(studentname, studentsirnamename, studentmarks);
        if (save)
            Toast.makeText(this, "Inserted", Toast.LENGTH_SHORT).show();
        else Toast.makeText(this, "Not Inserted", Toast.LENGTH_SHORT).show();
    }

    private boolean isValid() {
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
