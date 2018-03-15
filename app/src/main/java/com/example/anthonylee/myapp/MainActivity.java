package com.example.anthonylee.myapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView hours;
    private int time;
    private String petType ="dog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar();
        Button startTimerButton = (Button)findViewById(R.id.changeAnimalButton);
        changeAnimalIcon(startTimerButton);


    }

    public void seekBar(){
        SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar);
        hours = (TextView)findViewById(R.id.textViewTime);
        seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        int progress = seekBar.getProgress();
                        hours.setText(getHours(progress));
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        time = getMs(seekBar.getProgress());
                    }
                    public String getHours(int progress){
                        if (progress<10){
                            return "00:05";
                        } else if (progress<20){
                            return "00:20";
                        } else if (progress<30){
                            return "00:30";
                        } else if (progress<40){
                            return "00:40";
                        } else if (progress<50){
                            return "00:50";
                        } else if (progress<60){
                            return "01:00";
                        } else if (progress<70){
                            return "01:20";
                        } else if (progress<80){
                            return "01:40";
                        } else if (progress<90){
                            return "02:00";
                        } else {
                            return "03:00";
                        }
                    }
                    public int getMs(int progress){
                        if (progress<10){
                            return 5000;
                        } else if (progress<20){
                            return 1200000;
                        } else if (progress<30){
                            return 1800000;
                        } else if (progress<40){
                            return 2400000;
                        } else if (progress<50){
                            return 3000000;
                        } else if (progress<60){
                            return 3600000;
                        } else if (progress<70){
                            return 4800000;
                        } else if (progress<80){
                            return 6000000;
                        } else if (progress<90){
                            return 7200000;
                        } else {
                            return 10800000;
                        }
                    }
                }
        );
    }

    public void changeAnimal(View view){
        Intent i = new Intent(this, ChangeAnimal.class);
        startActivity(i);
    }

    public void changeAnimalIcon(Button button){
        Bundle data = getIntent().getExtras();
        if (data == null){
            button.setBackgroundResource(R.drawable.dog);
            return;
        }
        String animal = data.getString("petType");
        petType = animal;
        switch(animal){
            case "dog": button.setBackgroundResource(R.drawable.dog);
                        break;
            case "cat": button.setBackgroundResource(R.drawable.cat);
                        break;
            case "rabbit": button.setBackgroundResource(R.drawable.rabbit);
                        break;
            case "bird": button.setBackgroundResource(R.drawable.bird);
                        break;
            case "fish": button.setBackgroundResource(R.drawable.fish);
                break;
            case "horse": button.setBackgroundResource(R.drawable.horse);
                break;
            case "snake": button.setBackgroundResource(R.drawable.snake);
                break;
            case "turtle": button.setBackgroundResource(R.drawable.turtle);
                break;
            case "monkey": button.setBackgroundResource(R.drawable.monkey);
                break;
            default : button.setBackgroundResource(R.drawable.dog);
                      break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void startTimer(View view){
        if(time == 0){
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
            //alertDialog.setTitle("");
            alertDialog.setTitle("Please select a time");


            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            alertDialog.show();
        } else {

            Intent toTimerActivity = new Intent(this, TimerStartedActivity.class);
            int startTimeToSec = time / 1000;

            toTimerActivity.putExtra("totalTimeInSeconds", startTimeToSec);
            toTimerActivity.putExtra("petType", petType);
            startActivity(toTimerActivity);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
            new AlertDialog.Builder(this)
                    .setTitle("Really Exit?")
                    .setMessage("Are you sure you want to exit?")
                    .setNegativeButton(android.R.string.no, null)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            System.exit(0);
                        }
                    }).create().show();

    }
}
