package com.example.anthonylee.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

public class ChangeAnimal extends AppCompatActivity {

    private Intent toMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_animal);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.6),(int)(height*0.4));

    }

    public void changeToDog(View view){
        toMain = new Intent(this, MainActivity.class);
        toMain.putExtra("petType", "dog");
        startActivity(toMain);
    }

    public void changeToCat(View view){
        toMain = new Intent(this, MainActivity.class);
        toMain.putExtra("petType", "cat");
        startActivity(toMain);
    }

    public void changeToRabbit(View view){
        toMain = new Intent(this, MainActivity.class);
        toMain.putExtra("petType", "rabbit");
        startActivity(toMain);
    }

    public void changeToBird(View view){
        toMain = new Intent(this, MainActivity.class);
        toMain.putExtra("petType", "bird");
        startActivity(toMain);
    }

    public void changeToTurtle(View view){
        toMain = new Intent(this, MainActivity.class);
        toMain.putExtra("petType", "turtle");
        startActivity(toMain);
    }

    public void changeToFish(View view){
        toMain = new Intent(this, MainActivity.class);
        toMain.putExtra("petType", "fish");
        startActivity(toMain);
    }

    public void changeToSnake(View view){
        toMain = new Intent(this, MainActivity.class);
        toMain.putExtra("petType", "snake");
        startActivity(toMain);
    }

    public void changeToHorse(View view){
        toMain = new Intent(this, MainActivity.class);
        toMain.putExtra("petType", "horse");
        startActivity(toMain);
    }

    public void changeToMonkey(View view){
        toMain = new Intent(this, MainActivity.class);
        toMain.putExtra("petType", "monkey");
        startActivity(toMain);
    }
}
