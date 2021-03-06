package com.example.anthonylee.myapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "Main";
    private TextView hours;
    private int time;
    private String petType = "dog";
    private Button startTimerButton;
    private boolean check = false;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private SwitchCompat mSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        seekBar();
        startTimerButton = (Button)findViewById(R.id.changeAnimalButton);
        changeAnimalIcon(startTimerButton);

        //navbar implementation

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        if(menuItem.isChecked()) {
                            menuItem.setChecked(false);
                        } else if(!menuItem.isChecked()) {
                            menuItem.setChecked(true);
                        }
                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });
        ischecked();

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
            button.setBackgroundResource(R.mipmap.dog);
            return;
        }
        String animal = data.getString("petType");
        petType = animal;
        switch(animal){
            case "dog": button.setBackgroundResource(R.mipmap.dog);
                        break;
            case "cat": button.setBackgroundResource(R.mipmap.cat);
                        break;
            case "rabbit": button.setBackgroundResource(R.mipmap.rabbit);
                        break;
            case "bird": button.setBackgroundResource(R.mipmap.bird);
                        break;
            case "fish": button.setBackgroundResource(R.mipmap.fish);
                        break;
            case "horse": button.setBackgroundResource(R.mipmap.horse);
                        break;
            case "snake": button.setBackgroundResource(R.mipmap.snake);
                        break;
            case "turtle": button.setBackgroundResource(R.mipmap.turtle);
                        break;
            case "monkey": button.setBackgroundResource(R.mipmap.monkey);
                        break;
            default : button.setBackgroundResource(R.mipmap.dog);
                      break;
        }
    }

    public void startTimer(View view){
        if(time == 0){
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
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

            toTimerActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            startActivity(toTimerActivity);
        }
    }

    public void ischecked() {
        Menu menu = navigationView.getMenu();
        MenuItem menuItem = menu.findItem(R.id.vibrate);
        View actionView = MenuItemCompat.getActionView(menuItem);

        mSwitch = (SwitchCompat) actionView.findViewById(R.id.switchAB);
        //sharedpref setup
        SharedPreferences sharepref = getSharedPreferences("settings", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharepref.edit();

        //OnChecklistener
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    //Your code when checked
                    editor.putBoolean("vibration", true);
                    Toast.makeText(getBaseContext(), "Vibration On", Toast.LENGTH_SHORT).show();
                    Log.i("vibrate","on");
                } else {
                    //Your code when unchecked
                    editor.putBoolean("vibration", false);
                    Toast.makeText(getBaseContext(), "Vibration Off", Toast.LENGTH_SHORT).show();
                    Log.i("vibrate","off");
                }
            }
        });

        editor.apply();
    }

    public void openNavDraw(View view){
        drawer.openDrawer(GravityCompat.START);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_camera:
                check = !item.isChecked();
                item.setChecked(check);
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onBackPressed() {
            new AlertDialog.Builder(this)
                    .setTitle("Really Exit?")
                    .setMessage("Are you sure you want to exit?")
                    .setNegativeButton(android.R.string.no, null)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            finish();
                        }
                    }).create().show();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onStart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        startTimerButton = (Button)findViewById(R.id.changeAnimalButton);
        changeAnimalIcon(startTimerButton);
        Log.i(TAG,"onResume");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG,"onSaveInstance");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG,"onRestoreInstance");
    }

}
