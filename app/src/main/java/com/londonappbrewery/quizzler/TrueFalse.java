package com.londonappbrewery.quizzler;

public class TrueFalse {

    private int mQuestionId;
    private boolean mAnswer;

    public TrueFalse(int mQuestionId, boolean mAnswer) {
        this.mQuestionId = mQuestionId;
        this.mAnswer = mAnswer;
    }

    public int getmQuestionId() {
        return mQuestionId;
    }


    public boolean ismAnswer() {
        return mAnswer;
    }

}
