package com.example.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ScoresActivity extends AppCompatActivity {

    private TextView tvBack;
    private ImageView ivBack;
    private ListView lvScores;
    private Button resetBtn;

    private ArrayAdapter<String> adapter;
    int bestScore0, bestScore1, bestScore2, bestScore3;

    private final String SHARED_PREFS_1 = "sharedPreferences1";
    private final String BEST_SCORE_0 = "bestScore0", BEST_SCORE_1 = "bestScore1", BEST_SCORE_2 = "bestScore2", BEST_SCORE_3 = "bestScore3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        onLoad();

        tvBack = findViewById(R.id.tvBack);
        ivBack = findViewById(R.id.ivBack);
        lvScores = findViewById(R.id.lvScores);
        resetBtn = findViewById(R.id.resetBtn);

        ArrayList<String> scores = new ArrayList<>();
        if(bestScore0 > 0) {
            scores.add("Level 1 (1-10): " + bestScore0);
        }
        if(bestScore1 > 0) {
            scores.add("Level 2 (1-100): " + bestScore1);
        }
        if(bestScore2 > 0) {
            scores.add("Level 3 (1-500): " + bestScore2);
        }
        if(bestScore3 > 0) {
            scores.add("Level 4 (1-1000): " + bestScore3);
        }


        adapter = new ArrayAdapter<String>(this, R.layout.list_item, scores);
        lvScores.setAdapter(adapter);

        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScoresActivity.this.finish();
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScoresActivity.this.finish();
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_1, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                Toast.makeText(ScoresActivity.this, "Data has been deleted", Toast.LENGTH_SHORT).show();
                ScoresActivity.this.finish();
                Intent intent = new Intent(ScoresActivity.this, MenuActivity.class);
                ScoresActivity.this.startActivity(intent);
            }
        });

    }
    private void onLoad() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_1, MODE_PRIVATE);
        bestScore0 = sharedPreferences.getInt(BEST_SCORE_0, bestScore0);
        bestScore1 = sharedPreferences.getInt(BEST_SCORE_1, bestScore1);
        bestScore2 = sharedPreferences.getInt(BEST_SCORE_2, bestScore2);
        bestScore3 = sharedPreferences.getInt(BEST_SCORE_3, bestScore3);
    }
}