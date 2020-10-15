package com.example.myapplication3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Random;

public class FieldActivity extends AppCompatActivity {
    int highScore_number; 
    int score_number;
    String playerName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field);
        setTitle("Game");

        //Declaration of buttons
        final Button scoreBtn = (Button) findViewById(R.id.score);
        final Button bestBtn = (Button) findViewById(R.id.bestScore);
        final View swipe = (View) findViewById(R.id.swipe_view);
        scoreBtn.setText("0");
        Button resetBtn = (Button) findViewById(R.id.reset);
        Button button00 = (Button) findViewById(R.id.button_00);
        Button button01 = (Button) findViewById(R.id.button_01);
        Button button02 = (Button) findViewById(R.id.button_02);
        Button button03 = (Button) findViewById(R.id.button_03);
        Button button10 = (Button) findViewById(R.id.button_10);
        Button button11 = (Button) findViewById(R.id.button_11);
        Button button12 = (Button) findViewById(R.id.button_12);
        Button button13 = (Button) findViewById(R.id.button_13);
        Button button20 = (Button) findViewById(R.id.button_20);
        Button button21 = (Button) findViewById(R.id.button_21);
        Button button22 = (Button) findViewById(R.id.button_22);
        Button button23 = (Button) findViewById(R.id.button_23);
        Button button30 = (Button) findViewById(R.id.button_30);
        Button button31 = (Button) findViewById(R.id.button_31);
        Button button32 = (Button) findViewById(R.id.button_32);
        Button button33 = (Button) findViewById(R.id.button_33);

        //Saving buttons in a table
        final Button[] btnTab=new Button[16];
        final String[] tempValTab=new String[16];
        btnTab[0]=button00;
        btnTab[1]=button01;
        btnTab[2]=button02;
        btnTab[3]=button03;
        btnTab[4]=button10;
        btnTab[5]=button11;
        btnTab[6]=button12;
        btnTab[7]=button13;
        btnTab[8]=button20;
        btnTab[9]=button21;
        btnTab[10]=button22;
        btnTab[11]=button23;
        btnTab[12]=button30;
        btnTab[13]=button31;
        btnTab[14]=button32;
        btnTab[15]=button33;
        //Rolling a start position of 2 first buttons with the value of 2
        startGame(btnTab,scoreBtn,bestBtn,swipe);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame(btnTab,scoreBtn,bestBtn,swipe);
            }
        });
        View view = (View) findViewById(R.id.swipe_view);
        view.setOnTouchListener(new OnSwipeTouchListener(FieldActivity.this) {
            public void onSwipeTop() {
                tempValueTab(btnTab,tempValTab);
                moveUp(btnTab, scoreBtn);
                checkGameOver(tempValTab,swipe,bestBtn);
                updateGame(btnTab,tempValTab,swipe, bestBtn);
                changeBtnColor(btnTab);
                bestScore(scoreBtn,bestBtn);

            }
            public void onSwipeRight() {
                tempValueTab(btnTab,tempValTab);
                moveRight(btnTab, scoreBtn);
                checkGameOver(tempValTab,swipe,bestBtn);
                updateGame(btnTab,tempValTab, swipe, bestBtn);
                changeBtnColor(btnTab);
                bestScore(scoreBtn,bestBtn);
            }
            public void onSwipeLeft() {
                tempValueTab(btnTab,tempValTab);
                moveLeft(btnTab, scoreBtn);
                checkGameOver(tempValTab,swipe,bestBtn);
                updateGame(btnTab,tempValTab,swipe,bestBtn);
                changeBtnColor(btnTab);
                bestScore(scoreBtn,bestBtn);
            }
            public void onSwipeBottom() {
                tempValueTab(btnTab,tempValTab);
                moveDown(btnTab, scoreBtn);
                checkGameOver(tempValTab,swipe, bestBtn);
                updateGame(btnTab,tempValTab,swipe, bestBtn);
                changeBtnColor(btnTab);
                bestScore(scoreBtn,bestBtn);
            }

        });




    }
// TEST
    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        SharedPreferences.Editor editor = preferences.edit();
        if(score_number>=highScore_number){
        editor.putInt("BestScore", score_number);
        editor.putString("PlayerName", playerName);
        editor.apply();
        }
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void bestScore(Button score, Button bestBtn){
            SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            score_number=Integer.parseInt(score.getText().toString());
        if(Integer.parseInt(score.getText().toString())>=Integer.parseInt(bestBtn.getText().toString())){
            bestBtn.setText(score.getText().toString());
           highScore_number=Integer.parseInt(bestBtn.getText().toString());
            editor.putInt("HighScore_4", highScore_number);
            editor.commit();
        }
    }
    public void moveRight(Button[] btn, Button  score_btn){
        for(int i=0;i<4;i++) {
            moveLineRight(btn,1,i);
            moveLineRight(btn,2,i);
            moveLineRight(btn,3,i);
            moveLineRight(btn,4,i);
        }
        sumNumbersRight(btn, score_btn);
    }
    public void moveLeft(Button[] btn, Button score_btn){
        for(int i=3;i>=0;i--) {
            moveLineLeft(btn,1,i);
            moveLineLeft(btn,2,i);
            moveLineLeft(btn,3,i);
            moveLineLeft(btn,4,i);
        }
        sumNumbersLeft(btn, score_btn);
    }
    public void moveUp(Button[] btn, Button score_btn){

        for (int i = 0; i < 4; i++) {
            moveLineUp(btn,1,i);
            moveLineUp(btn,2,i);
            moveLineUp(btn,3,i);
            moveLineUp(btn,4,i);
        }
        sumNumbersUp(btn,score_btn);
    }
    public void moveDown(Button[] btn, Button score_btn) {
        for (int i = 3; i >=0 ; i--) {
            moveLineDown(btn,1,i);
            moveLineDown(btn,2,i);
            moveLineDown(btn,3,i);
            moveLineDown(btn,4,i);
        }
        sumNumbersDown(btn, score_btn);
    }
    public void startGame(Button[] btn, Button score_btn, Button bestBtn,View swipe_view){
        swipe_view.setVisibility(View.VISIBLE);
        resetGame(btn, score_btn);
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        highScore_number = sharedPref.getInt("HighScore_4", 0);
        bestBtn.setText(String.valueOf(highScore_number));
        int randFirstNumber = new Random().nextInt(16);
        int randSecondNumber = new Random().nextInt(16);
        if(randFirstNumber!=randSecondNumber){
        btn[randFirstNumber].setText("2");
        btn[randSecondNumber].setText("2");
        }
        else{
            randFirstNumber=randFirstNumber%2+1;
            btn[randFirstNumber].setText("2");
            btn[randSecondNumber].setText("2");
        }
        changeBtnColor(btn);
    }

    public void updateGame(Button[] btn,String[] temp_btn, View swipe, Button bestBtn){
        boolean updatedField=true;
        int rand;
        if(!sameTabs(btn, temp_btn)){
        while(updatedField){
            rand = new Random().nextInt(16);
            if(btn[rand].getText().toString().equals("")){
                btn[rand].setText("2");
                updatedField=false;
            }
            else if(checkGameOver(temp_btn, swipe, bestBtn)){
                Toast.makeText(FieldActivity.this, "error", Toast.LENGTH_SHORT).show();
                updatedField=false;
            }
            }
        }
    }
    public void resetGame(Button[] btn, Button score_btn){
        score_btn.setText("0");
        for(int i=0;i<16;i++){
            btn[i].setText("");
        }
    }
    public void moveLineLeft(Button[] btn,int line, int i){
        String tmp;
        if (btn[(line*4-1)-i].getText().toString()!=""&&i!=3){
            tmp=btn[(line*4-1)-i].getText().toString();
            if(btn[(line*4-4)].getText().toString().equals("")) {
                btn[(line*4-1)-i].setText("");
                btn[(line*4-4)].setText(tmp);
            }
            else if(btn[(line*4-3)].getText().toString().equals("")){
                btn[(line*4-1) - i].setText("");
                btn[(line*4-3)].setText(tmp);
            }
            else if(btn[(line*4-2)].getText().toString().equals("")&&i!=2){
                btn[(line*4-1) - i].setText("");
                btn[(line*4-2)].setText(tmp);
            }
            else if(i!=2&&i!=1){
                btn[(line*4-1) - i].setText("");
                btn[(line*4-1)].setText(tmp);
            }
        }
    }
    public void moveLineRight(Button[] btn,int line, int i){
        String tmp;
        if (btn[(line*4-1)-i].getText().toString()!=""&&i!=0){
            tmp=btn[(line*4-1)-i].getText().toString();
            if(btn[(line*4-1)].getText().toString().equals("")) {
                btn[(line*4-1)-i].setText("");
                btn[(line*4-1)].setText(tmp);
            }
            else if(btn[(line*4-2)].getText().toString().equals("")){
               btn[(line*4-1) - i].setText("");
                btn[(line*4-2)].setText(tmp);
            }
            else if(btn[(line*4-3)].getText().toString().equals("")&&i!=1){
                btn[(line*4-1) - i].setText("");
                btn[(line*4-3)].setText(tmp);
            }
            else if(i!=1&&i!=2){
                btn[(line*4-1) - i].setText("");
                btn[(line*4-4)].setText(tmp);
            }
        }
    }
    public void moveLineUp(Button[] btn, int column, int i){
        String tmp;
        int col=column-1;
        if (btn[i*4+col].getText().toString()!=""&&i!=0){
            tmp=btn[i*4+col].getText().toString();
            if(btn[0+col].getText().toString().equals("")) {
                btn[i*4+col].setText("");
                btn[col].setText(tmp);
            }
            else if(btn[4+col].getText().toString().equals("")){
                btn[i*4+col].setText("");
                btn[col+4].setText(tmp);
            }
            else if(btn[8+col].getText().toString().equals("")&i!=1){
                btn[i*4+col].setText("");
                btn[col+8].setText(tmp);
            }
            else if (i!=1&&i!=2){
                btn[i*4+col].setText("");
                btn[col+12].setText(tmp);
            }
        }
    }
    public void moveLineDown(Button[] btn, int column, int i){
        String tmp;
        int col=column-1;
        if (btn[i*4+col].getText().toString()!=""&&i!=3) {
            tmp = btn[i * 4 + col].getText().toString();
            if(btn[12+col].getText().toString().equals("")) {
                btn[i*4+col].setText("");
                btn[col+12].setText(tmp);
            }
            else if(btn[8+col].getText().toString().equals("")){
                btn[i*4+col].setText("");
                btn[col+8].setText(tmp);
            }
            else if(btn[4+col].getText().toString().equals("")&&i!=2){
                btn[i*4+col].setText("");
                btn[col+4].setText(tmp);
            }
            else if (i!=2&&i!=1){
                btn[i*4+col].setText("");
                btn[col].setText(tmp);
            }
        }

    }
    public void sumNumbersRight(Button[] btn, Button score_btn){
        int sum;
        for(int i=0;i<4;i++) {
            if (btn[3 + i * 4].getText().toString().equals(btn[2 + i * 4].getText().toString()) && btn[3 + i * 4].getText().toString() != "") {
                btn[3 + i * 4].setText(String.valueOf(2 * Integer.parseInt(btn[3 + i * 4].getText().toString())));
                btn[2 + i * 4].setText(btn[1 + i * 4].getText().toString());
                btn[1 + i * 4].setText(btn[0 + i * 4].getText().toString());
                btn[0 + i * 4].setText("");
                sum=Integer.parseInt(score_btn.getText().toString())+Integer.parseInt(btn[3 + i * 4].getText().toString());
                score_btn.setText(String.valueOf(sum));
            } else if (btn[2 + i * 4].getText().toString().equals(btn[1 + i * 4].getText().toString()) && btn[2 + i * 4].getText().toString() != "") {
                btn[2 + i * 4].setText(String.valueOf(2 * Integer.parseInt(btn[2 + i * 4].getText().toString())));
                btn[1 + i * 4].setText(btn[0 + i * 4].getText().toString());
                btn[0 + i * 4].setText("");
                sum=Integer.parseInt(score_btn.getText().toString())+Integer.parseInt(btn[2 + i * 4].getText().toString());
                score_btn.setText(String.valueOf(sum));
            } else if (btn[1 + i * 4].getText().toString().equals(btn[0 + i * 4].getText().toString()) && btn[1 + i * 4].getText().toString() != "") {
                btn[1 + i * 4].setText(String.valueOf(2 * Integer.parseInt(btn[1 + i * 4].getText().toString())));
                btn[0 + i * 4].setText("");
                sum=Integer.parseInt(score_btn.getText().toString())+Integer.parseInt(btn[1 + i * 4].getText().toString());
                score_btn.setText(String.valueOf(sum));
            }
        }
    }
    public void sumNumbersLeft(Button[] btn, Button score_btn) {
        int sum;
        for (int line = 1; line<=4; line++) {
            if (btn[0 + (line - 1) * 4].getText().toString().equals(btn[1 + (line - 1) * 4].getText().toString()) && btn[0 + (line - 1) * 4].getText().toString() != "") {
                btn[0 + (line - 1) * 4].setText(String.valueOf(2 * Integer.parseInt(btn[0 + (line - 1) * 4].getText().toString())));
                btn[1 + (line - 1) * 4].setText(btn[2 + (line - 1) * 4].getText().toString());
                btn[2 + (line - 1) * 4].setText(btn[3 + (line - 1) * 4].getText().toString());
                btn[3 + (line - 1) * 4].setText("");
                sum=Integer.parseInt(score_btn.getText().toString())+Integer.parseInt(btn[0 + (line - 1) * 4].getText().toString());
                score_btn.setText(String.valueOf(sum));
            } else if (btn[1 + (line - 1) * 4].getText().toString().equals(btn[2 + (line - 1) * 4].getText().toString()) && btn[1 + (line - 1) * 4].getText().toString() != "") {
                btn[1 + (line - 1) * 4].setText(String.valueOf(2 * Integer.parseInt(btn[1 + (line - 1) * 4].getText().toString())));
                btn[2 + (line - 1) * 4].setText(btn[3 + (line - 1) * 4].getText().toString());
                btn[3 + (line - 1) * 4].setText("");
                sum=Integer.parseInt(score_btn.getText().toString())+Integer.parseInt(btn[1 + (line - 1) * 4].getText().toString());
                score_btn.setText(String.valueOf(sum));
            } else if (btn[2 + (line - 1) * 4].getText().toString().equals(btn[3 + (line - 1) * 4].getText().toString()) && btn[2 + (line - 1) * 4].getText().toString() != "") {
                btn[2 + (line - 1) * 4].setText(String.valueOf(2 * Integer.parseInt(btn[2 + (line - 1) * 4].getText().toString())));
                btn[3 + (line - 1) * 4].setText("");
                sum=Integer.parseInt(score_btn.getText().toString())+Integer.parseInt(btn[2 + (line - 1) * 4].getText().toString());
                score_btn.setText(String.valueOf(sum));
            }
        }
    }
    public void sumNumbersUp(Button[] btn, Button score_btn){
        int sum;
        for(int i=0;i<4;i++) {
            if (btn[0 + i].getText().toString().equals(btn[4 + i].getText().toString()) && btn[0 + i].getText().toString() != "") {
                btn[0 + i].setText(String.valueOf(2 * Integer.parseInt(btn[0 + i].getText().toString())));
                btn[4 + i].setText(btn[8 + i].getText().toString());
                btn[8 + i].setText(btn[12 + i].getText().toString());
                btn[12 + i].setText("");
                sum=Integer.parseInt(score_btn.getText().toString())+Integer.parseInt(btn[0 + i].getText().toString());
               score_btn.setText(String.valueOf(sum));

            } else if (btn[4 + i].getText().toString().equals(btn[8 + i].getText().toString()) && btn[4 + i].getText().toString() != "") {
                btn[4+ i ].setText(String.valueOf(2 * Integer.parseInt(btn[4 + i].getText().toString())));
                btn[8 + i].setText(btn[12 + i].getText().toString());
                btn[12 + i].setText("");
                sum=Integer.parseInt(score_btn.getText().toString())+Integer.parseInt(btn[4 + i].getText().toString());
                score_btn.setText(String.valueOf(sum));
            } else if (btn[8 + i].getText().toString().equals(btn[12 + i].getText().toString()) && btn[8 + i].getText().toString() != "") {
                btn[8 + i].setText(String.valueOf(2 * Integer.parseInt(btn[8 + i].getText().toString())));
                btn[12 + i].setText("");
                sum=Integer.parseInt(score_btn.getText().toString())+Integer.parseInt(btn[8 + i].getText().toString());
                score_btn.setText(String.valueOf(sum));
            }
        }
    }
    public void sumNumbersDown(Button[] btn,  Button score_btn){
        int sum;
        for (int i=3;i>=0;i--){
            if (btn[12 + i].getText().toString().equals(btn[8 + i].getText().toString()) && btn[12 + i].getText().toString() != "") {
                btn[12 + i].setText(String.valueOf(2 * Integer.parseInt(btn[12 + i].getText().toString())));
                btn[8 + i].setText(btn[4 + i].getText().toString());
                btn[4 + i].setText(btn[0 + i].getText().toString());
                btn[0 + i].setText("");
                sum=Integer.parseInt(score_btn.getText().toString())+Integer.parseInt(btn[12 + i].getText().toString());
                score_btn.setText(String.valueOf(sum));

            } else if (btn[8 + i].getText().toString().equals(btn[4 + i].getText().toString()) && btn[8 + i].getText().toString() != "") {
                btn[8 + i ].setText(String.valueOf(2 * Integer.parseInt(btn[8 + i].getText().toString())));
                btn[4 + i].setText(btn[0 + i].getText().toString());
                btn[0 + i].setText("");
                sum=Integer.parseInt(score_btn.getText().toString())+Integer.parseInt(btn[8 + i].getText().toString());
                score_btn.setText(String.valueOf(sum));
            } else if (btn[4 + i].getText().toString().equals(btn[0 + i].getText().toString()) && btn[4 + i].getText().toString() != "") {
                btn[4 + i].setText(String.valueOf(2 * Integer.parseInt(btn[4 + i].getText().toString())));
                btn[0 + i].setText("");
                sum=Integer.parseInt(score_btn.getText().toString())+Integer.parseInt(btn[4 + i].getText().toString());
                score_btn.setText(String.valueOf(sum));
            }
        }
    }
    public void changeBtnColor(Button[] tab){
        for (int i = 0; i < tab.length; i++) {
            switch (tab[i].getText().toString()){
                case "2":
                    tab[i].setBackgroundColor(Color.parseColor("#c1efdd"));
                    break;
                case "4":
                    tab[i].setBackgroundColor(Color.parseColor("#ade9d1"));
                    break;
                case"8":
                    tab[i].setBackgroundColor(Color.parseColor("#84dfbb"));
                    break;
                case "16":
                    tab[i].setBackgroundColor(Color.parseColor("#6fd9af"));
                    break;
                case "32":
                    tab[i].setBackgroundColor(Color.parseColor("#5ad4a4"));
                    break;
                case "64":
                    tab[i].setBackgroundColor(Color.parseColor("#46cf99"));
                    break;
                case "128":
                    tab[i].setBackgroundColor(Color.parseColor("#32ca8e"));
                    break;
                case "256":
                    tab[i].setBackgroundColor(Color.parseColor("#2db57f"));
                    break;
                case "512":
                    tab[i].setBackgroundColor(Color.parseColor("#28a171"));
                    break;
                case "1024":
                    tab[i].setBackgroundColor(Color.parseColor("#238d63"));
                    break;
                case "2048":
                    tab[i].setBackgroundColor(Color.parseColor("#1e7955"));
                    do{
                    Toast.makeText(FieldActivity.this, "Congratulation, you achieved 2048", Toast.LENGTH_SHORT).show();
                    } while(highScore_number==-1);
                    break;
                default:
                    tab[i].setBackground(new ColorDrawable(Color.LTGRAY));



            }

        }
    }
    public void tempValueTab(Button[] btn, String[] tempValTab){
        for (int i = 0; i < btn.length; i++) {
            tempValTab[i]=btn[i].getText().toString();
        }
    }
    public boolean sameTabs(Button[] btn, String[] tempValTab){
        boolean same_tab=false;
        for (int i = 0; i < btn.length; i++) {
            if(btn[i].getText().toString().equals(tempValTab[i])){
                same_tab=true;
            }
            else
            {
                same_tab=false;
                break;
            }
        }
        return same_tab;
    }
    public boolean checkGameOver(String[] tempValTab, View swipe, Button bestBtn){
        boolean game_over=false;
        int control_number=0;
        for (int i = 0; i < 3; i++) {
            if(tempValTab[i].equals("")||tempValTab[i].equals(tempValTab[i+1])){
                control_number=0;
                break;}
            else if(tempValTab[i+4].equals("")||tempValTab[i+4].equals(tempValTab[i+5])){
                control_number=0;
                break;}
            else if(tempValTab[i+8].equals("")||tempValTab[i+8].equals(tempValTab[i+9])){
                control_number=0;
                break;}
            else if(tempValTab[i+12].equals("")||tempValTab[i+12].equals(tempValTab[i+13])){
                control_number=0;
                break;}
            else if(tempValTab[3+4*i].equals("")||tempValTab[3+4*(i+1)].equals("")){
                control_number=0;
                break;
            }
            else if(tempValTab[i].equals(tempValTab[i+4])){
                control_number=0;
                break;}
            else if(tempValTab[i+4].equals(tempValTab[i+8])){
                control_number=0;
                break;}
            else if(tempValTab[i+8].equals(tempValTab[i+12])){
                control_number=0;
                break;}
            else if(tempValTab[3+4*i].equals(tempValTab[3+(4*(i+1))])){
                control_number=0;
                break;}
            else{
                control_number++;

            }
        }
        if(control_number==3){
            swipe.setVisibility(View.INVISIBLE);
            Toast.makeText(FieldActivity.this, "Game over", Toast.LENGTH_SHORT).show();
            game_over=true;
            if(score_number>=highScore_number)
                getPlayerName();
        }

        return game_over;
}
    public void getPlayerName(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("You beat the highscore, write your name");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setCancelable(false);

        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                playerName = input.getText().toString();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
}
}