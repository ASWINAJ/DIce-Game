package com.example.android.dice_game;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button hold_button;
    private Button reset_button;
    private Button roll_button;
    private int user_total=0;
    private int comp_total=0;
    private int user_hold=0;
    private int comp_hold=0;
    private Random rand = new Random();
    private int randomNum;
    private int turn = 0;
    private ImageView image;
    private TextView user;
    private TextView comp;
    private int i=0;
    private TextView hold_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        roll_button = (Button)findViewById(R.id.roll_button);
        reset_button = (Button)findViewById(R.id.reset_button);
        hold_button = (Button)findViewById(R.id.hold_button);
        image = (ImageView)findViewById(R.id.image);
        user = (TextView)findViewById(R.id.my_score);
        comp = (TextView)findViewById(R.id.computer_score);

        hold_text = (TextView)findViewById(R.id.hold_text);
        Toast.makeText(MainActivity.this,"Play the game by rolling the dias",Toast.LENGTH_SHORT).show();


    }

    public void click(View view){

        if(view.getId() == R.id.roll_button){
            randomNum =1+ rand.nextInt(6);

            //Toast.makeText(MainActivity.this,"The number is " + String.valueOf(randomNum),Toast.LENGTH_SHORT).show();

            if(randomNum==1)
                image.setImageResource(R.drawable.dice1);

            if(randomNum==2)
                image.setImageResource(R.drawable.dice2);
            if(randomNum==3)
                image.setImageResource(R.drawable.dice3);
            if(randomNum==4)
                image.setImageResource(R.drawable.dice4);
            if(randomNum==5)
                image.setImageResource(R.drawable.dice5);
            if(randomNum==6)
                image.setImageResource(R.drawable.dice6);

            user_hold+=randomNum;
            hold_text.setText("current hold up is " + String.valueOf(user_hold));


            if(randomNum==1) {
                Toast.makeText(MainActivity.this,"Sorry for such a loss..",Toast.LENGTH_SHORT).show();
                user_hold = 0;
                hold_text.setText("current hold up is " + String.valueOf(user_hold));
                support();
                roll_button.setClickable(false);
                reset_button.setClickable(false);
                Toast.makeText(MainActivity.this,"Computers turn",Toast.LENGTH_SHORT).show();
            }

        }
        else if(view.getId() == R.id.hold_button) {
            user_total += user_hold;
            user_hold = 0;
            hold_text.setText("current hold up is " + String.valueOf(user_hold));

            user.setText(String.valueOf(user_total));

            if (user_total >= 100) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("User wins");
                builder.setCancelable(true);
                AlertDialog dialog = builder.create();
                dialog.show();

                user_hold=0;
                user_total=0;
                comp_hold=0;
                comp_total=0;

                comp.setText(String.valueOf(0));
                user.setText(String.valueOf(0));
            } else {
                support();
                roll_button.setClickable(false);
                reset_button.setClickable(false);
                Toast.makeText(MainActivity.this,"Computers turn",Toast.LENGTH_SHORT).show();
            }
        }
        else if(view.getId() == R.id.reset_button){
            user_hold=0;
            user_total=0;
            comp_hold=0;
            comp_total=0;

            comp.setText(String.valueOf(0));
            user.setText(String.valueOf(0));
            hold_text.setText(String.valueOf(0));
        }
    }

    public void support(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                randomNum =1+ rand.nextInt(6);

               // Toast.makeText(MainActivity.this,"Computer got " + String.valueOf(randomNum),Toast.LENGTH_SHORT).show();

                if(randomNum==1)
                    image.setImageResource(R.drawable.dice1);

                if(randomNum==2)
                    image.setImageResource(R.drawable.dice2);
                if(randomNum==3)
                    image.setImageResource(R.drawable.dice3);
                if(randomNum==4)
                    image.setImageResource(R.drawable.dice4);
                if(randomNum==5)
                    image.setImageResource(R.drawable.dice5);
                if(randomNum==6)
                    image.setImageResource(R.drawable.dice6);

                if(randomNum!=1)
                comp_hold+=randomNum;

                hold_text.setText("current hold up is " + String.valueOf(comp_hold));


                computer();
            }
        },1500);
    }



    public void computer(){

        if(comp_hold<20 &&randomNum!=1 && comp_hold+comp_total<100)
            support();
        else if(randomNum==1){
            Toast.makeText(MainActivity.this,"Computer may also make mistakes",Toast.LENGTH_SHORT).show();
            comp_hold=0;
            hold_text.setText("current hold up is " + String.valueOf(comp_hold));

        }

            if(comp_hold>=20 || randomNum==1 ||comp_hold+comp_total>=100){
            comp_total += comp_hold;
            comp_hold=0;
                hold_text.setText("current hold up is " + String.valueOf(comp_hold));

                comp.setText(String.valueOf(comp_total));
            if(comp_total>=100) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Computer wins");
                builder.setCancelable(true);
                AlertDialog dialog = builder.create();
                dialog.show();

                user_hold=0;
                user_total=0;
                comp_hold=0;
                comp_total=0;

                comp.setText(String.valueOf(0));
                user.setText(String.valueOf(0));

            }
                roll_button.setClickable(true);
                reset_button.setClickable(true);
                Toast.makeText(MainActivity.this,"Now your chance honey",Toast.LENGTH_SHORT).show();

        }

    }



    }




