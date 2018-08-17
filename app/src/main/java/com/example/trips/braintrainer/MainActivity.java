package com.example.trips.braintrainer;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locans;
    int score = 0;
    TextView result;
    TextView points;
    TextView sum;
    TextView timer;
    int noques = 0;
    Button playAgain;
    RelativeLayout main;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = (Button)findViewById(R.id.button);
        main = (RelativeLayout)findViewById(R.id.main);

        sum = (TextView)findViewById(R.id.textView3);
        timer = (TextView)findViewById(R.id.textView);

        button0 = (Button)findViewById(R.id.option1);
        button1 = (Button)findViewById(R.id.option2);
        button2 = (Button)findViewById(R.id.option3);
        button3 = (Button)findViewById(R.id.option4);

        playAgain = (Button)findViewById(R.id.button2);

        result = (TextView)findViewById(R.id.result);

        points = (TextView)findViewById(R.id.textView2);

    }

    public void go(View view) {
        startButton.setVisibility(View.INVISIBLE);
        main.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.button2));
    }

    @SuppressLint("SetTextI18n")
    public void generateQuestion(){
        Random rand = new Random();
        int a= rand.nextInt(21);
        int b= rand.nextInt(21);
        sum.setText(Integer.toString(a)+"+"+Integer.toString(b));

        locans = rand.nextInt(4);
        answers.clear();
        int incans;
        for(int i=0;i<4;i++){
            if (i==locans){
                answers.add(a+b);
            }else{
                incans = rand.nextInt(41);
                while (incans == a+b){
                    incans = rand.nextInt(41);
                }
                answers.add(incans);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    @SuppressLint("SetTextI18n")
    public void chooseAnswer(View view) {
        if(view.getTag().toString().equals(Integer.toString(locans))){
            score++;
            result.setText("Correct!");
        }else{
            result.setText("Wrong!");
        }
        noques++;
        points.setText(Integer.toString(score)+"/"+Integer.toString(noques));
        generateQuestion();
    }

    @SuppressLint("SetTextI18n")
    public void playAgain(View view) {
        score = 0;
        noques = 0;
        timer.setText("30 sec");
        points.setText("0/0");
        result.setText("");
        playAgain.setVisibility(View.INVISIBLE);
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);

        generateQuestion();
        new CountDownTimer(30100,1000){

            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(Integer.toString((int) millisUntilFinished/1000)+" sec");
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {
                playAgain.setVisibility(View.VISIBLE);
                timer.setText("0 sec");
                result.setText("Your Score: "+Integer.toString(score)+"/"+Integer.toString(noques));
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
            }
        }.start();
    }
}
