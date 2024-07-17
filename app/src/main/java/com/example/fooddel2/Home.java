package com.example.fooddel2;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


import com.google.android.material.navigation.NavigationView;

public class Home extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerlayout;
    NavigationView naview;
    TextView user,contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        naview = findViewById(R.id.navigationview);
        toolbar = findViewById(R.id.toolbar);
        drawerlayout = findViewById(R.id.drawerlayout);
        user = findViewById(R.layout.header_layout.id.user);
        contact = findViewById(R.id.contact);

        setSupportActionBar(toolbar);

        user.setText("Zenil");
        contact.setText("8154877466");

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                drawerlayout,toolbar,R.string.opendrawer,R.string.closedrawer);

        drawerlayout.addDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();

    }
}