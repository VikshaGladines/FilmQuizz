package com.ipi.filmquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ipi.filmquiz.pojos.Question;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private TextView tvQuestion;
    private TextView tvScore;
    private Button btnTrue;
    private Button btnFalse;
    private List<Question> questions = new ArrayList<>();

    private int indexQuestion = 0;
    private int score = 0;
    private Question question;

    private final String TAG = "QuizActivity";
    public static final String KEY_INDEX = "index";
    public static final String KEY_SCORE = "score";
    public static final String KEY_QUESTION = "question";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ici que l'on code

        Log.d(TAG, "onCreate() called");

        // récupère tous les éléments
        tvQuestion = findViewById(R.id.tvQuestion);
        tvScore = findViewById(R.id.tvScore);
        btnTrue = findViewById(R.id.btnTrue);
        btnFalse = findViewById(R.id.btnFalse);

        // créé les 5 questions
        Question q1 = new Question(getString(R.string.question_ai), true);
        Question q2 = new Question(getString(R.string.question_taxi_driver), true);
        Question q3 = new Question(getString(R.string.question_2001), false);
        Question q4 = new Question(getString(R.string.question_reservoir_dogs), true);
        Question q5 = new Question(getString(R.string.question_citizen_kane), false);

        // ajouter les 5 questions dans une liste
        questions.add(q1);
        questions.add(q2);
        questions.add(q3);
        questions.add(q4);
        questions.add(q5);

        // récupère les données si il y en a dans savedInstanceState
        if (savedInstanceState != null) {
            indexQuestion = savedInstanceState.getInt(KEY_INDEX);
            score = savedInstanceState.getInt(KEY_SCORE);
        }

        // récupère la première question
        question = questions.get(indexQuestion);

        // modifie le text du textview tvQuestion
        tvQuestion.setText(question.getText());

        // modifie le text du textview tvScore
        tvScore.setText(getString(R.string.score) + " : " + score);

        // click sur le bouton true
        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // tester si la réponse à la question est true
                if (question.isAnswer() == true) {
                    // incrémente le score
                    score++;

                    Toast toast = Toast.makeText(getApplicationContext(), "Bonne réponse", Toast.LENGTH_SHORT);
                    toast.show();
                }

                if (indexQuestion < questions.size() - 1) {
                    // incrémente l'index de la question
                    indexQuestion++;
                }
                else {
                    indexQuestion = 0;
                    score = 0;

                    Toast toast = Toast.makeText(getApplicationContext(), "Fin de la partie", Toast.LENGTH_SHORT);
                    toast.show();
                }

                // récupère la question
                question = questions.get(indexQuestion);

                // affiche la question
                tvQuestion.setText(question.getText());

                // affiche le score
                tvScore.setText(getString(R.string.score) + " : " + score);
            }
        });

        // clic sur le bouton false
        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // tester si la réponse à la question est true
                if (question.isAnswer() == false) {
                    // incrémente le score
                    score++;

                    Toast toast = Toast.makeText(getApplicationContext(), "Bonne réponse", Toast.LENGTH_SHORT);
                    toast.show();
                }

                if (indexQuestion < questions.size() - 1) {
                    // incrémente l'index de la question
                    indexQuestion++;
                }
                else {
                    indexQuestion = 0;
                    score = 0;

                    Toast toast = Toast.makeText(getApplicationContext(), "Fin de la partie", Toast.LENGTH_SHORT);
                    toast.show();
                }

                // récupère la question
                question = questions.get(indexQuestion);

                // affiche la question
                tvQuestion.setText(question.getText());

                // affiche le score
                tvScore.setText(getString(R.string.score) + " : " + score);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d(TAG, "onRestart() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "onDestroy() called");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.d(TAG, "onSaveInstanceState() called");

        outState.putInt(KEY_INDEX, indexQuestion);
        outState.putInt(KEY_SCORE, score);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // créé le menu à partir de la ressource
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // effectue une action suivant l'item sélectionné
        // test avec un switch l'id de l'item
        switch (item.getItemId()) {
            case R.id.cheat:
                // créé un intent pour démarrer CheatActivity
                Intent intent = new Intent(getApplicationContext(), CheatActivity.class);

                // ajoute dans le intent l'objet de la question
                intent.putExtra(KEY_QUESTION, question);

                startActivity(intent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}