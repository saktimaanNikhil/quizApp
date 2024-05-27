package com.example.quizsection;

public class Question {
    private String question;
    private int imageResId;
    private String[] options;
    private int correctAnswer;

    public Question(String question, int imageResId, String[] options, int correctAnswer) {
        this.question = question;
        this.imageResId = imageResId;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}
