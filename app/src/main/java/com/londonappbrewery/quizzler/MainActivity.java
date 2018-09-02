package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    // TODO: Declare member variables here:
    Button mTrueButton;
    Button mFalseButton;
    TextView mTextViewQuestions;
    TextView mTextViewScoreBar;
    ProgressBar mProgressBarr;
    int mIndex;
    int mScore;


    // TODO: Uncomment to create question bank
    private TrueFalse[] mQuestionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, false),
            new TrueFalse(R.string.question_3, false),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, true),
            new TrueFalse(R.string.question_7, false),
            new TrueFalse(R.string.question_8, true),
            new TrueFalse(R.string.question_9, false),
            new TrueFalse(R.string.question_10, false),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, true),
            new TrueFalse(R.string.question_13, true)
    };

    // TODO: Declare constants here
    final int PROGRESS_BAR_INCREMENT = (int) Math.ceil(100 / mQuestionBank.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTrueButton = findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_button);
        mTextViewQuestions = findViewById(R.id.question_text_view);
        mTextViewScoreBar = findViewById(R.id.score);
        mProgressBarr = findViewById(R.id.progress_bar);

        if (savedInstanceState != null) {
            mScore = savedInstanceState.getInt("ScoreKey");
            mIndex = savedInstanceState.getInt("IndexKey");
            mTextViewScoreBar.setText("Score " + mScore + "/" +mQuestionBank.length);
        } else {
            mScore = 0;
            mIndex = 0;
        }

        mTextViewQuestions.setText(mQuestionBank[mIndex].getmQuestionId());

        View.OnClickListener myListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.true_button:
                        checkAnswer(true);
                        updateQuestion();
                        break;

                    case R.id.false_button:
                        checkAnswer(false);
                        updateQuestion();
                        break;
                }
            }
        };

        mTrueButton.setOnClickListener(myListener);
        mFalseButton.setOnClickListener(myListener);

    }

    private void updateQuestion() {
        mIndex = (mIndex + 1) % mQuestionBank.length;

        if(mIndex == 0) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("KONIEC");
            alert.setCancelable(false);
            alert.setMessage("Zdobyłeś " + mScore + " punktów!");
            alert.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alert.show();
        }

        mTextViewQuestions.setText(mQuestionBank[mIndex].getmQuestionId());
        mProgressBarr.incrementProgressBy(PROGRESS_BAR_INCREMENT);
        mTextViewScoreBar.setText("Score " + mScore + "/" +mQuestionBank.length);
    }

    private void checkAnswer(boolean userSelection){

        boolean correctAnswer = mQuestionBank[mIndex].ismAnswer();
        
        if(userSelection == correctAnswer) {
            Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
            mScore++;
        } else {
            Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("ScoreKey", mScore);
        outState.putInt("IndexKey", mIndex);

    }
}
