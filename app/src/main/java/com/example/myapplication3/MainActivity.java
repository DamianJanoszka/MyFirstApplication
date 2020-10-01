package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.*;

public class MainActivity extends AppCompatActivity {
    int highScore_number;
    String playerName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Play 2048");

        Button gameButton = (Button) findViewById(R.id.game_button);
        TextView result2 = findViewById(R.id.resultTextView2);
        SharedPreferences preferences = getSharedPreferences("PREFS",0);
        highScore_number = preferences.getInt("BestScore",0);
        playerName = preferences.getString("PlayerName","");
        result2.setText(playerName+"  "+String.valueOf(highScore_number));

        gameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGame();
            }
        });

    }
    public void openGame() {
        Intent intent = new Intent(this, FieldActivity.class);
        startActivity(intent);
    }
}