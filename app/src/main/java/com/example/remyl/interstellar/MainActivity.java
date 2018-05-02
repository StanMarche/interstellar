package com.example.remyl.interstellar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.remyl.interstellar.database.TelechargementDonnees;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TelechargementDonnees telechargementDonnees = new TelechargementDonnees(this, this);
        telechargementDonnees.execute();




    }

    public void pictureClick(View v)
    {
        Intent intent = new Intent(this, ImageActivity.class);
        startActivity(intent);

    }


}
