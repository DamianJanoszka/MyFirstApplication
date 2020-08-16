package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Roll your random number");

        Button rollButton = (Button) findViewById(R.id.rollButton);
        Button gameButton = (Button) findViewById(R.id.game_button);
        final TextView resultsTextView = (TextView) findViewById(R.id.resultTextView);
        final SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        resultsTextView.setText("");
        rollButton.setOnClickListener
               (new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             if(seekBar.getProgress()==0){
                                                 resultsTextView.setText(">0");
                                             }
                                             else {
                                                 int rand = new Random().nextInt(seekBar.getProgress()) + 1;
                                                 // rand.nextInt(seekBar.getProgress());
                                                 resultsTextView.setText(String.valueOf(rand));
                                             }
                                         }
                                     }
        );
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