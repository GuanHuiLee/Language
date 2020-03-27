package com.lgh.language;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button btnChinese, btnEnglish;
    private TextView tvLang;
    private SharedPreferences sharedPref;
    private String languageKey = "language";
    private String sharePrefName = "shared_prefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPref = getSharedPreferences(sharePrefName, Context.MODE_PRIVATE);
        String language = getString(languageKey, LanguageUtils.getSystemLanguage());
        LanguageUtils.setLanguage(this, language);
        initView();
    }

    private void initView() {
        btnChinese = findViewById(R.id.btn_chinese);
        btnEnglish = findViewById(R.id.btn_english);

        tvLang = findViewById(R.id.tv_lang);

        btnChinese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putString(languageKey, "ch");
                LanguageUtils.setLanguage(MainActivity.this, "ch");
                LanguageUtils.resetApp(MainActivity.this);
            }
        });

        btnEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putString(languageKey, "en");
                LanguageUtils.setLanguage(MainActivity.this, "en");
                LanguageUtils.resetApp(MainActivity.this);
            }
        });
    }


    private void putString(String key, String value) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    private String getString(String key, String defaultValue) {
        return sharedPref.getString(key, defaultValue);
    }
}
