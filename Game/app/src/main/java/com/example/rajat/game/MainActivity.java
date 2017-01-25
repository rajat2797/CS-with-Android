package com.example.rajat.game;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView your_score, computer_score;
    int y_score=0, c_score=0;
    float x_pos, y_pos;
    ImageView dice;
    boolean turn, start=true;
    Button roll, hold, reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        your_score = (TextView) findViewById(R.id.your_score);
        computer_score = (TextView) findViewById(R.id.computer_score);
        dice = (ImageView) findViewById(R.id.dice);
        roll = (Button) findViewById(R.id.roll);
        reset = (Button) findViewById(R.id.reset);
        hold = (Button) findViewById(R.id.hold);

//        x_pos = dice.getX();
//        Log.e("x_pos=",""+x_pos);

        hold.setEnabled(false);
        reset.setEnabled(false);

        turn = true;
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

    }

    public void animate() {

        for(int i=0;i<5;i++) {

            dice.animate()
                    .setInterpolator(new AccelerateDecelerateInterpolator())
                    .translationX(x_pos+20)
                    .setDuration(1000);

            dice.animate()
                    .setInterpolator(new AccelerateDecelerateInterpolator())
                    .translationX(x_pos-40)
                    .setDuration(1000);

            dice.animate()
                    .setInterpolator(new AccelerateDecelerateInterpolator())
                    .translationX(x_pos+20)
                    .setDuration(1000);
            Log.e("2 : x=",""+dice.getX());
        }

    }

    public void roll(View view) {

        if(start) {
            hold.setEnabled(true);
            reset.setEnabled(true);
//            x_pos = dice.getX();
            start = false;
        }
        int x = ((int) (Math.random()*(6))+1);
        Log.d("x=", ""+x);
        switch(x) {

            case 1 :    dice.setImageResource(R.drawable.dice1);
                        if(turn) {
                            y_score = 0;
                            your_score.setText("0");
                            turn = false;

                        }
                        break;

            case 2 :    dice.setImageResource(R.drawable.dice2);
                        break;

            case 3 :    dice.setImageResource(R.drawable.dice3);
                        break;

            case 4 :    dice.setImageResource(R.drawable.dice4);
                        break;

            case 5 :    dice.setImageResource(R.drawable.dice5);
                        break;

            case 6 :    dice.setImageResource(R.drawable.dice6);
                        break;
        }
        animate();
        if(x!=1 && turn) {
            y_score += x;
            your_score.setText(""+y_score);

            if(y_score >= 100) {
                Toast.makeText(MainActivity.this, "You Win", Toast.LENGTH_SHORT).show();
                reset(reset);
                return;
            }

            turn = false;
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    roll(roll);
                }
            }, 3000);

        }
        else if(!turn) {
            c_score += x;
            computer_score.setText(""+c_score);

            if(c_score >= 100) {
                Toast.makeText(MainActivity.this, "Computer Won", Toast.LENGTH_SHORT).show();
                reset(reset);
            }

            turn = true;
        }

    }

    public void reset(View view) {

        y_score=0;
        c_score=0;
        your_score.setText("0");
        computer_score.setText("0");
        dice.setImageResource(R.drawable.dice1);
        dice.setX(x_pos-dice.getMaxWidth()/2);
        turn = true;
        hold.setEnabled(false);
        reset.setEnabled(false);

    }

    public void hold(View view) {

    }


}
