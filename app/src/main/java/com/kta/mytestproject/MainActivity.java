package com.kta.mytestproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
    private EditText name;
    SharedPreferences sPref; //хранение
    final String SAVED_TEXT = "saved_text";

    private long backPressedTime;
    private Toast backToast; //сообщение


    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        } else{
            backToast = Toast.makeText(getBaseContext(), "Нажмите еще раз для выхода", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Animation anim_button = AnimationUtils.loadAnimation(this,
                R.anim.button_animation);
        name = (EditText) findViewById(R.id.name);
        loadText();

        Button save = (Button) findViewById(R.id.btn_save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(anim_button);
                saveText();
            }
        });

        Button start = (Button) findViewById(R.id.btn_start);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                v.startAnimation(anim_button);

                // Проверка наличия введенного имени
                String playerName = name.getText().toString().trim();
                if (playerName.isEmpty()) {
                    // Поле с именем пусто, выводим сообщение
                    Toast.makeText(MainActivity.this, "Введите ваше имя", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        Intent intent = new Intent(MainActivity.this, lvls.class);
                        startActivity(intent);
                        finish();
                    } catch (Exception e) {
                    }
                }
            }
        });

        Button rating = (Button) findViewById(R.id.btn_start_r);
        rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(anim_button);
                try {
                    Intent intent = new Intent(MainActivity.this, RatingActivity.class);
                    startActivity(intent);finish();
                }
                catch (Exception e){

                }
            }
        });
    }
    void saveText() {

        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(SAVED_TEXT, name.getText().toString());
        ed.apply();

        // Сохранение имени в SharedPreferences для RatingActivity
        SharedPreferences ratingPrefs = getSharedPreferences("RatingPrefs", MODE_PRIVATE);
        SharedPreferences.Editor ratingEditor = ratingPrefs.edit();
        ratingEditor.putString("playerName", name.getText().toString());
        ratingEditor.apply();

    }
    void loadText() {
        sPref = getPreferences(MODE_PRIVATE);
        String savedText = sPref.getString(SAVED_TEXT, "");
        name.setText(savedText);
    }

}
