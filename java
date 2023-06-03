import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView questionTextView;
    private RadioGroup answerRadioGroup;
    private Button nextButton;
    private Button resetButton;
    private Button exitButton;

    private String[] questions = {
            "Question 1",
            "Question 2",
            "Question 3"
    };

    private String[] answers = {
            "Answer 1",
            "Answer 2",
            "Answer 3"
    };

    private int currentQuestionIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = findViewById(R.id.questionTextView);
        answerRadioGroup = findViewById(R.id.answerRadioGroup);
        nextButton = findViewById(R.id.nextButton);
        resetButton = findViewById(R.id.resetButton);
        exitButton = findViewById(R.id.exitButton);

        displayQuestion();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.length) {
                    displayQuestion();
                } else {
                    Toast.makeText(MainActivity.this, "Quiz completed!", Toast.LENGTH_SHORT).show();
                    nextButton.setEnabled(false);
                    resetButton.setEnabled(false);
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerRadioGroup.clearCheck();
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void displayQuestion() {
        questionTextView.setText(questions[currentQuestionIndex]);
        answerRadioGroup.clearCheck();

        RadioButton option1RadioButton = findViewById(R.id.option1RadioButton);
        option1RadioButton.setText("Option 1");

        RadioButton option2RadioButton = findViewById(R.id.option2RadioButton);
        option2RadioButton.setText("Option 2");

        RadioButton option3RadioButton = findViewById(R.id.option3RadioButton);
        option3RadioButton.setText("Option 3");

        RadioButton option4RadioButton = findViewById(R.id.option4RadioButton);
        option4RadioButton.setText("Option 4");
    }

    private void checkAnswer() {
        int selectedRadioButtonId = answerRadioGroup.getCheckedRadioButtonId();

        if (selectedRadioButtonId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
            String selectedAnswer = selectedRadioButton.getText().toString();

            if (selectedAnswer.equals(answers[currentQuestionIndex])) {
                Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Incorrect!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(MainActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
        }
    }
}
