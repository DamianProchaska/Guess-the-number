package com.example.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Debug;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private TextView tvResult, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnClear, btn0, btnCheck, textView2;
    private ImageView ivBack;
    private TextView tvBack;
    int i = 0, max, level, maxLevel, bestScore0, bestScore1, bestScore2, bestScore3;

    private final String SHARED_PREFS_1 = "sharedPreferences1";
    private final String MAX_LEVEL = "maxLevel";
    private final String LEVEL = "levelKey";
    private final String BEST_SCORE_0 = "bestScore0", BEST_SCORE_1 = "bestScore1", BEST_SCORE_2 = "bestScore2", BEST_SCORE_3 = "bestScore3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        onLoad();

        Intent intent = getIntent();
        level = intent.getIntExtra("levelKey", 1);


        switch(level){
            case 0:
                max = 10;
                break;
            case 1:
                max = 100;
                break;
            case 2:
                max = 500;
                break;
            case 3:
                max = 1000;
                break;
        }
        Random rand = new Random();
        int random = rand.nextInt(max);

        tvBack = findViewById(R.id.tvBack);
        ivBack = findViewById(R.id.ivBack);
        textView2 = findViewById(R.id.textView2);
        tvResult = findViewById(R.id.tvResult);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnClear = findViewById(R.id.btnClear);
        btn0 = findViewById(R.id.btn0);
        btnCheck = findViewById(R.id.btnCheck);


        String task = getString(R.string.task) + " 1 " + getString(R.string.and) + " " + max;
        textView2.setText(task);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvResult.setBackgroundResource(R.color.button);
                TextView b = (TextView) view;
                tvResult.append(b.getText().toString());
            }
        };

        btn1.setOnClickListener(listener);
        btn2.setOnClickListener(listener);
        btn3.setOnClickListener(listener);
        btn4.setOnClickListener(listener);
        btn5.setOnClickListener(listener);
        btn6.setOnClickListener(listener);
        btn7.setOnClickListener(listener);
        btn8.setOnClickListener(listener);
        btn9.setOnClickListener(listener);
        btn0.setOnClickListener(listener);

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvResult.setText("");
            }
        });

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                i++;
                if(random > Integer.parseInt(tvResult.getText().toString())){
                    tvResult.setBackground(getDrawable(R.drawable.up));
                }
                if(random < Integer.parseInt(tvResult.getText().toString())){
                    tvResult.setBackground(getDrawable(R.drawable.down));
                }
                if(random == Integer.parseInt(tvResult.getText().toString())){
                    if(maxLevel < level + 1) {
                        maxLevel = level + 1;
                    }
                    switch(level){
                        case 0:
                            if (i < bestScore0 || bestScore0 == 0){
                                onSave(i);
                            } else {
                                onSave();
                            }
                            break;
                        case 1:
                            if (i < bestScore1 || bestScore1 == 0){
                                onSave(i);
                            } else {
                                onSave();
                            }
                            break;
                        case 2:
                            if (i < bestScore2 || bestScore2 == 0){
                                onSave(i);
                            } else {
                                onSave();
                            }
                            break;
                        case 3:
                            if (i < bestScore3 || bestScore3 == 0){
                                onSave(i);
                            } else {
                                onSave();
                            }
                            break;
                    }
                MaterialAlertDialogBuilder alert = new MaterialAlertDialogBuilder(GameActivity.this);
                alert.setTitle(getString(R.string.alert) + " " + i + " " + getString(R.string.tries) + "!");
                alert.setPositiveButton(getString(R.string.nextLvl), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(GameActivity.this, GameActivity.class);
                        intent.putExtra(LEVEL, level + 1);
                        GameActivity.this.startActivity(intent);
                        GameActivity.this.finish();
                    }
                });
                    alert.setNegativeButton(getString(R.string.alertMenu), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                            Intent intent = new Intent(GameActivity.this, MenuActivity.class);
                            GameActivity.this.startActivity(intent);
                        }
                    });
                    alert.setCancelable(false);
                alert.show();
                }
                tvResult.setText("");
            }
        });

        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameActivity.this.finish();
                Intent intent = new Intent(GameActivity.this, MenuActivity.class);
                GameActivity.this.startActivity(intent);
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameActivity.this.finish();
                Intent intent = new Intent(GameActivity.this, MenuActivity.class);
                GameActivity.this.startActivity(intent);
            }
        });
    }
    private void onSave(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_1, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(MAX_LEVEL, maxLevel);
        editor.apply();
    }

    private void onSave(int bestScore){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_1, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(MAX_LEVEL, maxLevel);
        switch(level){
            case 0:
                editor.putInt(BEST_SCORE_0, bestScore);
                break;
            case 1:
                editor.putInt(BEST_SCORE_1, bestScore);
                break;
            case 2:
                editor.putInt(BEST_SCORE_2, bestScore);
                break;
            case 3:
                editor.putInt(BEST_SCORE_3, bestScore);
                break;
        }
        editor.apply();
    }

    private void onLoad(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_1, MODE_PRIVATE);
        maxLevel = sharedPreferences.getInt(MAX_LEVEL, maxLevel);
        bestScore0 = sharedPreferences.getInt(BEST_SCORE_0, bestScore0);
        bestScore1 = sharedPreferences.getInt(BEST_SCORE_1, bestScore1);
        bestScore2 = sharedPreferences.getInt(BEST_SCORE_2, bestScore2);
        bestScore3 = sharedPreferences.getInt(BEST_SCORE_3, bestScore3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.language_menu, menu);
        return true;
    }
}