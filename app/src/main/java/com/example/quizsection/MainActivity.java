package com.example.quizsection;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {
    private CardView cardView;

    private TextView questionCounter;
    private TextView questionText;
    private ImageView questionImage;
    private RadioGroup optionsGroup;
    private TextView correctAnswerText;
    private Button nextButton;

    private int currentQuestionIndex = 0;
    private boolean isAnswered = false;


    private Question[] questions = new Question[]{
            new Question("What is this animal?", R.drawable.cat, new String[]{"Dog", "Cat", "Bird", "Fish"}, 1),
            new Question("What is the capital of France?", R.drawable.paris, new String[]{"Paris", "London", "Berlin", "Rome"}, 0),
            new Question("What is 2 + 2?", R.drawable.four, new String[]{"3", "4", "5", "6"}, 1),
            new Question("What is the color of the sky?", R.drawable.sky, new String[]{"Blue", "Green", "Red", "Yellow"}, 0),
            new Question("Which planet is known as the Red Planet?", R.drawable.mars, new String[]{"Earth", "Mars", "Venus", "Jupiter"}, 1),
            new Question("What is the largest ocean on Earth?", R.drawable.arctic_ocean, new String[]{"Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean"}, 3),
            new Question("Who wrote the play 'Romeo and Juliet'?", R.drawable.william, new String[]{"William Shakespeare", "Charles Dickens", "Mark Twain", "Jane Austen"}, 0),
            new Question("What is the chemical symbol for gold?", R.drawable.gold, new String[]{"Ag", "Au", "Fe", "Pb"}, 1),
            new Question("Which country is known as the Land of the Rising Sun?", R.drawable.sky, new String[]{"China", "Japan", "India", "Thailand"}, 1),
            new Question("Who painted the Mona Lisa?", R.drawable.leonardo, new String[]{"Vincent van Gogh", "Pablo Picasso", "Leonardo da Vinci", "Michelangelo"}, 2),
            new Question("What is the hardest natural substance on Earth?", R.drawable.diamond, new String[]{"Gold", "Iron", "Diamond", "Platinum"}, 2),
            new Question("Which element is necessary for the process of photosynthesis?", R.drawable.google, new String[]{"Hydrogen", "Oxygen", "Carbon Dioxide", "Nitrogen"}, 2),
            new Question("What is the smallest prime number?", R.drawable.two, new String[]{"0", "1", "2", "3"}, 2),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionCounter = findViewById(R.id.questionCounter);
        questionText = findViewById(R.id.questionText);
        correctAnswerText = findViewById(R.id.correctAnswerText);
        questionImage = findViewById(R.id.questionImage);
        optionsGroup = findViewById(R.id.optionsGroup);
        cardView = findViewById(R.id.cardView);
        nextButton = findViewById(R.id.nextButton);


        loadQuestion();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation move = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move);
                cardView.setAnimation(move);

                if (!isAnswered) {
                    showCorrectAnswer();
                } else {
                    currentQuestionIndex++;
                    if (currentQuestionIndex < questions.length) {
                        loadQuestion();
                    } else {
                        // Quiz finished
                        questionText.setText("Quiz Finished!");
                        questionImage.setVisibility(View.GONE);
                        optionsGroup.setVisibility(View.GONE);
                        nextButton.setVisibility(View.GONE);
                        correctAnswerText.setVisibility(View.GONE);
                    }
                    isAnswered = false;
                }
            }
        });
    }

    private void loadQuestion() {
        if (currentQuestionIndex < questions.length) {
            Question currentQuestion = questions[currentQuestionIndex];
            questionCounter.setText("Question " + (currentQuestionIndex + 1) + "/" + questions.length);
            questionText.setText(currentQuestion.getQuestion());
            questionImage.setImageResource(currentQuestion.getImageResId());

            optionsGroup.removeAllViews();
            for (int i = 0; i < currentQuestion.getOptions().length; i++) {
                RadioButton radioButton = new RadioButton(this);
                radioButton.setText(currentQuestion.getOptions()[i]);
                radioButton.setId(i);
                optionsGroup.addView(radioButton);
            }

            correctAnswerText.setVisibility(View.GONE);
        }
    }
    private void showCorrectAnswer() {
        int selectedId = optionsGroup.getCheckedRadioButtonId();
        if (selectedId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedId);
            int correctAnswer = questions[currentQuestionIndex].getCorrectAnswer();
            String correctAnswerTextStr = "Correct Answer: " + questions[currentQuestionIndex].getOptions()[correctAnswer];
            correctAnswerText.setText(correctAnswerTextStr);
            correctAnswerText.setVisibility(View.VISIBLE);

            if (selectedId == correctAnswer) {
                correctAnswerText.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
            } else {
                correctAnswerText.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
            }

            isAnswered = true;
        }
    }
}
