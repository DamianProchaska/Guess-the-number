package com.example.guessthenumber;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView button;
    private Spinner languageSpinner;
    private String selectedLanguage = "english";
    private ArrayAdapter<CharSequence> adapter;
    private final String SHARED_PREFS_1 = "sharedPreferences1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onLoad();

        languageSpinner = findViewById(R.id.languageSpinner);
        button = findViewById(R.id.button);

        adapter = ArrayAdapter.createFromResource(this, R.array.languages, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(adapter);

        if(selectedLanguage.equals("pl")){
            languageSpinner.setSelection(1);
        }else {
            languageSpinner.setSelection(0);
        }

        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                if(text.equals("Polski")){
                    setLocale("pl");
                    onLanguageSave("pl");
                } else if(text.equals("English")){
                    setLocale("english");
                    onLanguageSave("english");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, MenuActivity.class);
                MainActivity.this.startActivity(myIntent);
                MainActivity.this.finish();
            }
        });
    }

    private void onLoad() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_1, MODE_PRIVATE);
        selectedLanguage = sharedPreferences.getString("language", selectedLanguage);
    }

    private void setLocale(String language){
        Resources resources = getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = new Locale(language);
        resources.updateConfiguration(configuration, metrics);
        onConfigurationChanged(configuration);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private void onLanguageSave(String language){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_1, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("language", language);
        editor.apply();
    }
}