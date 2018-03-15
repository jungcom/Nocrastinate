package com.example.anthonylee.myapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pl.droidsonroids.gif.GifImageView;

public class TimerStartedActivity extends AppCompatActivity {
    private boolean finished;
    private Bundle timeData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_started);
        startTimer();
        finished = false;
        Button alertButton = (Button)findViewById(R.id.cancel_button);
        final Intent toMain = new Intent(this, MainActivity.class);

        //gif image
        timeData = getIntent().getExtras();
        String petType = timeData.getString("petType");
        toMain.putExtra("petType", petType);
        GifImageView gif = (GifImageView)findViewById(R.id.gifImage);
        switch(petType){
            case "dog": gif.setBackgroundResource(R.drawable.doggif);
                break;
            case "cat": gif.setBackgroundResource(R.drawable.catgif);
                break;
            case "rabbit": gif.setBackgroundResource(R.drawable.rabbitgif);
                break;
            case "bird": gif.setBackgroundResource(R.drawable.birdgif);
                break;
            case "fish": gif.setBackgroundResource(R.drawable.fishgif);
                break;
            case "horse": gif.setBackgroundResource(R.drawable.horsegif);
                break;
            case "snake": gif.setBackgroundResource(R.drawable.snakegif);
                break;
            case "turtle": gif.setBackgroundResource(R.drawable.turtlegif);
                break;
            case "monkey": gif.setBackgroundResource(R.drawable.monkeygif);
                break;
            default :
                break;
        }


        alertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(finished){
                    startActivity(toMain);
                } else {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(TimerStartedActivity.this);
                    alertDialog.setCancelable(true);
                    alertDialog.setTitle("Quit");
                    alertDialog.setMessage("Are you sure you want to kill your pet?");

                    alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });

                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(toMain);
                        }
                    });
                    alertDialog.show();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        final Intent toMain = new Intent(this, MainActivity.class);
        timeData = getIntent().getExtras();
        String petType = timeData.getString("petType");
        toMain.putExtra("petType", petType);
        if(finished){
            startActivity(toMain);
            return;
        }
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(TimerStartedActivity.this);
        alertDialog.setCancelable(true);
        alertDialog.setTitle("Quit");
        alertDialog.setMessage("Are you sure you want to kill your pet?");

        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        alertDialog.setPositiveButton("Kill", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                TimerStartedActivity.this.finish();
            }
        });
        alertDialog.show();
    }


    public void startTimer(){
        timeData = getIntent().getExtras();
        final TextView displayTime = (TextView)findViewById(R.id.countDownTextView);
        int totalTime = timeData.getInt("totalTimeInSeconds");
        final Button cancel = (Button)findViewById(R.id.cancel_button);

        new CountDownTimer(totalTime*1000, 1000) {

            public void onTick(long millisUntilFinished) {
                int timeLeftInSeconds = (int)(millisUntilFinished/1000);
                int hours = timeLeftInSeconds/3600;
                int minutes = (timeLeftInSeconds%3600)/60;
                int seconds = timeLeftInSeconds%60;
                String displayTimeString = String.format("%d:%02d:%02d",hours,minutes,seconds);
                displayTime.setText(displayTimeString);
            }

            public void onFinish() {
                displayTime.setText(R.string.textWhenFinished);
                finished = true;
                cancel.setText(R.string.textafterfinished);
                cancel.setAlpha(1);
            }
        }.start();
    }

}
