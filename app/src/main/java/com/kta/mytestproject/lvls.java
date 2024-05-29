package com.kta.mytestproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class lvls extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lvls2);


        Button lvl1 = (Button) findViewById(R.id.lvl1);
        lvl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(lvls.this, Activity2.class);
                    startActivity(intent);finish();
                }
                catch (Exception e){

                }
            }
        });
        Button lvl2 = (Button) findViewById(R.id.lvl2);
        lvl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(lvls.this, MainActivity2.class);
                    startActivity(intent);finish();
                }
                catch (Exception e){

                }
            }
        });




    }
    @Override
    public void onBackPressed(){

        try {
            Intent intent = new Intent(lvls.this, MainActivity.class);
            startActivity(intent);finish();
        }
        catch (Exception e){
        }
    }
}