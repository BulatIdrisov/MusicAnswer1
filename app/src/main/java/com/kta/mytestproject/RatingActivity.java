package com.kta.mytestproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.util.List;

public class RatingActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rating_activity);


        Button btnClearDatabase = findViewById(R.id.btnClearDatabase);
        btnClearDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearDatabase();
                setContentView(R.layout.rating_activity);
            }
        });


        DataManager dataManager = new DataManager(this);
        List<Player> playerList = dataManager.fetchData();

        if (!playerList.isEmpty()) {
            for (Player player : playerList) {
                String playerName = player.getPlayerName();
                int playerScore = player.getPlayerScore();

                // Use the playerName and playerScore as needed
                TextView textView = findViewById(R.id.textView);
                textView.append("Имя: " + playerName + ", Очки: " + playerScore + "\n");
            }
        } else {
            // Handle case when no player data is available
            TextView textView = findViewById(R.id.textView);
            textView.setText("");
        }
    }


    @Override
    public void onBackPressed(){
        try {
            Intent intent = new Intent(RatingActivity.this, MainActivity.class);
            startActivity(intent);finish();
        }
        catch (Exception e){
        }
    }

    private void clearDatabase() {
        DataManager dataManager = new DataManager(this);
        dataManager.clearDatabase();
        // Optionally, update UI or show a message indicating that the database has been cleared
    }
}
