package com.ipi.filmquiz;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.ipi.filmquiz.pojos.Question;

public class CheatActivity extends AppCompatActivity {

    private TextView tvReponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        // récupère les éléments
        tvReponse = findViewById(R.id.tvReponse);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);

        // récupère l'intent qui a appelé cette activity
        Intent intent = getIntent();

        // récupère la question qui est dans l'intent
        Question question = (Question)intent.getSerializableExtra(MainActivity.KEY_QUESTION);

        tvReponse.setText(String.format("%s : %s", question.getText(), question.isAnswer()));

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}