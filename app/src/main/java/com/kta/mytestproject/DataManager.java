package com.kta.mytestproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "game_data.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "player_data";
    private static final String COLUMN_NAME = "player_name";
    private static final String COLUMN_SCORE = "player_score";
    private static final String COLUMN_SCORE_2 = "player_score_2";

    public DataManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_SCORE + " INTEGER,"
                + COLUMN_SCORE_2 + " INTEGER);";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertPlayerData(String playerName, int playerScore, boolean isFirstScore) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Check if the player already exists in the database
        Cursor cursor = db.query(TABLE_NAME, null, COLUMN_NAME + "=?",
                new String[]{playerName}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            // Player already exists, update the score
            ContentValues updateValues = new ContentValues();
            updateValues.put(isFirstScore ? COLUMN_SCORE : COLUMN_SCORE_2, playerScore);
            long rowsUpdated = db.update(TABLE_NAME, updateValues,
                    COLUMN_NAME + "=?", new String[]{playerName});
            cursor.close();
            db.close();
            return rowsUpdated;
        } else {
            // Player does not exist, insert a new record
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, playerName);
            values.put(isFirstScore ? COLUMN_SCORE : COLUMN_SCORE_2, playerScore);
            values.put(!isFirstScore ? COLUMN_SCORE : COLUMN_SCORE_2, 0);
            long newRowId = db.insert(TABLE_NAME, null, values);
            if (cursor != null) {
                cursor.close();
            }
            db.close();
            return newRowId;
        }
    }

    public Cursor getPlayerData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"id", COLUMN_NAME, COLUMN_SCORE, COLUMN_SCORE_2};
        return db.query(TABLE_NAME, columns, null, null, null, null, null);
    }

    // Add the fetchData method to retrieve data
    public List<Player> fetchData() {
        List<Player> playerList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Define your column names
        String[] columns = {COLUMN_NAME, COLUMN_SCORE, COLUMN_SCORE_2};

        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Use try-catch to handle potential exceptions
                try {
                    String playerName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
                    int playerScore = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SCORE));
                    int playerScore2 = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SCORE_2));
                    Player player = new Player(playerName, playerScore + playerScore2);
                    playerList.add(player);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } while (cursor.moveToNext());

            cursor.close();
        }

        db.close();
        return playerList;
    }

    public void clearDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }

}