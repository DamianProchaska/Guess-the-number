package com.example.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    private TextView tvScores;
    private ImageView ivScores;

    private boolean[] blocked = {false, true, true, true};
    private int maxLevel;

    private final String LEVEL = "levelKey";
    private final String MAX_LEVEL = "maxLevel";
    private final String SHARED_PREFS_1 = "sharedPreferences1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        onLoad();

        tvScores = findViewById(R.id.tvScores);
        ivScores = findViewById(R.id.ivScores);

        tvScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, ScoresActivity.class);
                MenuActivity.this.startActivity(intent);
            }
        });

        ivScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, ScoresActivity.class);
                MenuActivity.this.startActivity(intent);
            }
        });

        ArrayList<ImageView> lvl = new ArrayList<ImageView>();
        lvl.add(findViewById(R.id.lvl1));
        lvl.add(findViewById(R.id.lvl2));
        lvl.add(findViewById(R.id.lvl3));
        lvl.add(findViewById(R.id.lvl4));

        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrix);

        Glide
                .with(MenuActivity.this)
                .load(R.drawable.first)
                .circleCrop()
                .placeholder(R.drawable.first)
                .into(lvl.get(0));

        Glide
                .with(MenuActivity.this)
                .load(R.drawable.second)
                .circleCrop()
                .placeholder(R.drawable.second)
                .into(lvl.get(1));

        Glide
                .with(MenuActivity.this)
                .load(R.drawable.third)
                .circleCrop()
                .placeholder(R.drawable.third)
                .into(lvl.get(2));

        Glide
                .with(MenuActivity.this)
                .load(R.drawable.fourth)
                .circleCrop()
                .placeholder(R.drawable.fourth)
                .into(lvl.get(3));

        for (int i = 0; i < 4; i++) {
            if (maxLevel >= i) {
                blocked[i] = false;
            }
        }

        for (int i = 0; i < 4; i++) {
            if (blocked[i]) {
                ImageView level = lvl.get(i);
                level.setColorFilter(filter);
            }
        }

        lvl.get(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, GameActivity.class);
                intent.putExtra(LEVEL, 0);
                MenuActivity.this.startActivity(intent);
                MenuActivity.this.finish();
            }
        });
        lvl.get(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, GameActivity.class);
                intent.putExtra(LEVEL, 1);
                MenuActivity.this.startActivity(intent);
                MenuActivity.this.finish();
            }
        });
        lvl.get(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, GameActivity.class);
                intent.putExtra(LEVEL, 2);
                MenuActivity.this.startActivity(intent);
                MenuActivity.this.finish();
            }
        });
        lvl.get(3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, GameActivity.class);
                intent.putExtra(LEVEL, 3);
                MenuActivity.this.startActivity(intent);
                MenuActivity.this.finish();
            }
        });
    }

    private void onLoad() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_1, MODE_PRIVATE);
        maxLevel = sharedPreferences.getInt(MAX_LEVEL, maxLevel);
    }
}