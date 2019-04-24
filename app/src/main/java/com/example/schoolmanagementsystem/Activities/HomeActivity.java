package com.example.schoolmanagementsystem.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.schoolmanagementsystem.DbClasses.DBManager;
import com.example.schoolmanagementsystem.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnaddnewschool, btnschoollist, btndownloadschools, btnaddvisit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    private void initViews() {
        btnaddnewschool = findViewById(R.id.btnaddnewschool);
        btnschoollist = findViewById(R.id.btnschoollist);
        btndownloadschools = findViewById(R.id.btndownloadschools);
        btnaddvisit = findViewById(R.id.btnaddvisit);

        //clickListenera
        btnaddnewschool.setOnClickListener(this);
        btndownloadschools.setOnClickListener(this);
        btnaddvisit.setOnClickListener(this);
        btnschoollist.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnaddnewschool:
                startActivity(new Intent(HomeActivity.this, HomeDetailActivity.class));
                break;
            case R.id.btnschoollist:
                startActivity(new Intent(HomeActivity.this, HomeDetailActivity.class));
                break;
            case R.id.btndownloadschools:
                startActivity(new Intent(HomeActivity.this, HomeDetailActivity.class));
                break;
            case R.id.btnaddvisit:
                startActivity(new Intent(HomeActivity.this, HomeDetailActivity.class));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        IsFinish();
    }

    public void IsFinish() {

        AlertDialog.Builder alertdialog = new AlertDialog.Builder(this);
        alertdialog.setMessage("Are you sure you Want to exit?");
        alertdialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                HomeActivity.this.finish();
            }
        });

        alertdialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alert = alertdialog.create();
        alert.show();
    }


    public void btnLogout(View view) {
        Toast.makeText(HomeActivity.this, "You Have Successfully Logedout", Toast.LENGTH_SHORT).show();
        DBManager.removeAllPreferencesData(getApplicationContext());
        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
    }
}
