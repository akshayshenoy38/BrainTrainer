package com.example.akshay.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    Button btnStart;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationodCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;
    TextView tvResult;
    TextView tvPoints;
    TextView tvSumText;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    TextView tvTimer;
    Button btnPlayAgain;
    RelativeLayout rlGame;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button) findViewById(R.id.btnStart);
        tvSumText = (TextView) findViewById(R.id.tvSumText);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        tvResult = (TextView) findViewById(R.id.tvResult);
        tvPoints = (TextView) findViewById(R.id.tvPoints);
        tvTimer = (TextView) findViewById(R.id.tvTimer);
        btnPlayAgain = (Button) findViewById(R.id.btnPlayAgain);
        rlGame = (RelativeLayout) findViewById(R.id.rlGame);






    }

    public void start(View view) {
        btnStart.setVisibility(View.INVISIBLE);
        rlGame.setVisibility(RelativeLayout.VISIBLE);
        playAgain(findViewById(R.id.btnPlayAgain));
    }

    public void chooseAnswer(View view) {
        if (view.getTag().toString().equals(Integer.toString(locationodCorrectAnswer))){
            score++;
            tvResult.setText("Correct!");
        } else {
            tvResult.setText("Wrong");
        }
        numberOfQuestions++;
        tvPoints.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        generateQuestion();


    }

    public void generateQuestion() {
        Random random = new Random();

        int a = random.nextInt(21);
        int b = random.nextInt(21);

        tvSumText.setText(Integer.toString(a)+"+"+Integer.toString(b));
        locationodCorrectAnswer = random.nextInt(4);
        answers.clear();
        //now for wrong answer
        int incorrectAnswer;
        for (int i = 0;i<4;i++){
            if (i == locationodCorrectAnswer){
                answers.add(a + b);
            } else {
                incorrectAnswer = random.nextInt(41);
                while (incorrectAnswer == a+b){
                    incorrectAnswer = random.nextInt();
                }
                answers.add(incorrectAnswer);
            }
        }
        button1.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));

    }

    public void playAgain(final View view) {
        score=0;
        numberOfQuestions=0;
        tvTimer.setText("30s");
        tvPoints.setText("0/0");
        tvResult.setText("");
        generateQuestion();

        new CountDownTimer(30100,1000){
            @Override
            public void onTick(long l) {
                tvTimer.setText(String.valueOf(l/1000)+"s");
            }

            @Override
            public void onFinish() {
                btnPlayAgain.setVisibility(view.INVISIBLE);
                tvTimer.setText("0s");
                tvResult.setText("Your Score:"+Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
            }
        }.start();
    }
}
