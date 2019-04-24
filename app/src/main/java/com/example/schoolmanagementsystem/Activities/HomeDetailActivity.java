package com.example.schoolmanagementsystem.Activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.schoolmanagementsystem.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class HomeDetailActivity extends AppCompatActivity implements OnMapReadyCallback {
    private EditText schoolname, contact_person, person_mobile_number, person_landlinenumber, person_email;
    private Spinner spinner;
    private TextView txthousevalue, showdialog, txtstreetvalue, txtmohallahvalue, txtnearstvalue, txttownvalue, txttehsilvalue, txtdistrictvalue;
    private View addressview, click;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_detail);
        initViews();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Add School");
        }

    }

    private void showCuctomDialog() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(HomeDetailActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.custom_dialog, null);
        final EditText editTexthouse, editTextstree, editTextmohallah, editTextnearstlandmark, editTexttown, editTexttehsil, editTextdistrict;
        final TextView textViewcancel, textViewsave;
        editTexthouse = mView.findViewById(R.id.house1);
        editTextstree = mView.findViewById(R.id.street1);
        editTextmohallah = mView.findViewById(R.id.mohallah1);
        editTextnearstlandmark = mView.findViewById(R.id.landmark1);
        editTexttown = mView.findViewById(R.id.town1);
        editTexttehsil = mView.findViewById(R.id.tehsil1);
        editTextdistrict = mView.findViewById(R.id.district1);
        textViewcancel = mView.findViewById(R.id.cancel);
        textViewsave = mView.findViewById(R.id.save);
        alert.setCancelable(false);
        alert.setView(mView);
        final AlertDialog alertDialog = alert.create();
        alertDialog.show();
        textViewcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addressview.setVisibility(View.GONE);
                showdialog.setVisibility(View.VISIBLE);
                alertDialog.dismiss();
            }
        });
        textViewsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String house = editTexthouse.getText().toString();
                String street = editTextstree.getText().toString();
                String mohallah = editTextmohallah.getText().toString();
                String town = editTexttown.getText().toString();
                String landmark = editTextnearstlandmark.getText().toString();
                String tehsil = editTexttehsil.getText().toString();
                String district = editTextdistrict.getText().toString();
                txthousevalue.setText(house);
                txtdistrictvalue.setText(district);
                txtmohallahvalue.setText(mohallah);
                txtnearstvalue.setText(landmark);
                txtstreetvalue.setText(street);
                txttehsilvalue.setText(tehsil);
                txttownvalue.setText(town);
                alertDialog.dismiss();

            }
        });
    }

    private void initViews() {
        click = findViewById(R.id.click);
        addressview = findViewById(R.id.addressview);
        showdialog = findViewById(R.id.showdialog);
        txthousevalue = findViewById(R.id.txthousevalue);
        txtdistrictvalue = findViewById(R.id.txtdistrictvalue);
        txtmohallahvalue = findViewById(R.id.txtmohallahvalue);
        txttownvalue = findViewById(R.id.txttownvalue);
        txtstreetvalue = findViewById(R.id.txtstreetvalue);
        txtnearstvalue = findViewById(R.id.txtnearstvalue);
        txttehsilvalue = findViewById(R.id.txttehsilvalue);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCuctomDialog();
                addressview.setVisibility(View.VISIBLE);
                showdialog.setVisibility(View.GONE);
            }
        });
        schoolname = findViewById(R.id.schoolname);
        contact_person = findViewById(R.id.contact_person);
        person_mobile_number = findViewById(R.id.person_mobile_number);
        person_landlinenumber = findViewById(R.id.person_landlinenumber);
//        textView = findViewById(R.id.save);
        spinner = findViewById(R.id.spinner);
        //Set Data in RecyclerView
        List<String> categories = new ArrayList<>();
        categories.add(0, "Select Membership");
        categories.add("Member");
        categories.add("Non Member");
        categories.add("Full Member");
        ArrayAdapter<String> datadapter;
        datadapter = new ArrayAdapter<>(HomeDetailActivity.this, android.R.layout.simple_spinner_item, categories);
        //Dropdown layout style
        datadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(datadapter);

        person_email = findViewById(R.id.person_email);
        if (schoolname.requestFocus()) {
            schoolname.setError("Enter School Name");
        }
        contact_person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contact_person.setError("Enter Contact Person");
            }
        });
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.googlemap);
        mapFragment.getMapAsync(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnhome:
                startActivity(new Intent(HomeDetailActivity.this, HomeActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Pakistan and move the camera
        LatLng Pakistan = new LatLng(31, 71);
        mMap.addMarker(new MarkerOptions().position(Pakistan).title("Marker in Pakistan"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Pakistan));
    }
}
