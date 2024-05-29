package com.kta.mytestproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {
    private MediaPlayer my_music;
    private MediaPlayer my_music1;
    private MediaPlayer my_music2;
    private Toast trueToast;
    private Toast falseToast;
    private int score = 0;

    private boolean True;

    private static SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        String song1 = "sweden";
        String song2 = "белые розы";
        String song3 = "sweet dreams";



//        // Получаем объект SharedPreferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);


        EditText otvet = (EditText) findViewById(R.id.otvet);
        Button proverka = (Button) findViewById(R.id.btn_proverka);

        EditText otvet2 = (EditText) findViewById(R.id.otvet2);
        Button proverka2 = (Button) findViewById(R.id.btn_proverka2);

        EditText otvet3 = (EditText) findViewById(R.id.otvet3);
        Button proverka3 = (Button) findViewById(R.id.btn_proverka3);

        Button proverkafin = (Button) findViewById(R.id.btn_proverkafin);

        proverka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (otvet.getText().toString().trim().toLowerCase().equals(song1)) {
                    trueToast = Toast.makeText(getBaseContext(), "Всё верно! Молодец!",
                            Toast.LENGTH_SHORT);
                    trueToast.show();
                    increaseScore();
                } else {
                    falseToast = Toast.makeText(getBaseContext(), "Неверно, попробуйте ещё раз.", Toast.LENGTH_SHORT);
                    falseToast.show();
                }
            }
        });

        proverka2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (otvet2.getText().toString().trim().toLowerCase().equals(song2)) {
                    trueToast = Toast.makeText(getBaseContext(), "Всё верно! Умница!",
                            Toast.LENGTH_SHORT);
                    trueToast.show();
                    increaseScore();
                } else {
                    falseToast = Toast.makeText(getBaseContext(), "Неверно, попробуйте ещё раз.", Toast.LENGTH_SHORT);
                    falseToast.show();

                }
            }
        });

        proverka3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (otvet3.getText().toString().trim().toLowerCase().equals(song3)) {
                    trueToast = Toast.makeText(getBaseContext(), "Всё верно! Невероятно!",
                            Toast.LENGTH_SHORT);
                    trueToast.show();
                    increaseScore();
                } else {
                    falseToast = Toast.makeText(getBaseContext(), "Неверно, попробуйте ещё раз.", Toast.LENGTH_SHORT);
                    falseToast.show();
                }
            }
        });

        proverkafin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (otvet3.getText().toString().trim().toLowerCase().equals(song3)&& otvet2.getText().toString().trim().toLowerCase().equals(song2)&& otvet.getText().toString().trim().toLowerCase().equals(song1) ) {
                    trueToast = Toast.makeText(getBaseContext(), "Ты справился! Все ответы верные! ^_^ ",
                            Toast.LENGTH_SHORT);
                    trueToast.show();
                    saveScoreToPreferences();
                } else {
                    falseToast = Toast.makeText(getBaseContext(), "Ответь на всё правильно, и нажми на меня.", Toast.LENGTH_SHORT);
                    falseToast.show();
                    saveScoreToPreferences();
                }
            }
        });

        final Button play = (Button)findViewById(R.id.btn_play);
        final Button stop = (Button)findViewById(R.id.btn_stop);
        final Button play2 = (Button)findViewById(R.id.btn_play2);
        final Button stop2 = (Button)findViewById(R.id.btn_stop2);
        final Button play3 = (Button)findViewById(R.id.btn_play3);
        final Button stop3 = (Button)findViewById(R.id.btn_stop3);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                my_music.start();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (my_music.isPlaying()) {
                    my_music.stop();
                    my_music.release();
                    my_music = MediaPlayer.create(Activity2.this, R.raw.music);
                }
            }
        });

        play2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                my_music1.start();
            }
        });

        stop2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (my_music1.isPlaying()) {
                    my_music1.stop();
                    my_music1.release();
                    my_music1 = MediaPlayer.create(Activity2.this, R.raw.music2);
                }
            }
        });

        play3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                my_music2.start();
            }
        });

        stop3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (my_music2.isPlaying()) {
                    my_music2.stop();
                    my_music2.release();
                    my_music2 = MediaPlayer.create(Activity2.this, R.raw.music3);
                }
            }
        });

        my_music = MediaPlayer.create(this, R.raw.music);
        my_music.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                my_music.start();
            }
        });
        my_music1 = MediaPlayer.create(this, R.raw.music2);
        my_music1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                my_music1.start();
            }
        });
        my_music2 = MediaPlayer.create(this, R.raw.music3);
        my_music2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                my_music2.start();
            }
        });
    }

    private void increaseScore() {
        score=score+10;
    }

    private void saveScoreToPreferences() {
        SharedPreferences ratingPrefs = getSharedPreferences("RatingPrefs", MODE_PRIVATE);

        // Сохранение очков в базе данных SQLite
        DataManager dataManager = new DataManager(this);
        dataManager.insertPlayerData(ratingPrefs.getString("playerName", ""), score, false);
    }



    @Override
    public void onBackPressed(){

        try {
            Intent intent = new Intent(Activity2.this, lvls.class);
            startActivity(intent);finish();
        }
        catch (Exception e){
        }
    }
}
