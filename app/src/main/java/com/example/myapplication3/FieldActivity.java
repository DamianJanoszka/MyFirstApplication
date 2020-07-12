package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class FieldActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field);
        setTitle("Game");
        //Declaration of buttons
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
        startGame(btnTab);

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame(btnTab);
            }
        });
        View view = (View) findViewById(R.id.swipe_view);
        view.setOnTouchListener(new OnSwipeTouchListener(FieldActivity.this) {
            public void onSwipeTop() {
                //Toast.makeText(FieldActivity.this, "top", Toast.LENGTH_SHORT).show();
                move_up(btnTab);
            }
            public void onSwipeRight() {
               // Toast.makeText(FieldActivity.this, "right", Toast.LENGTH_SHORT).show();
                move_right(btnTab);
            }
            public void onSwipeLeft() {
                //Toast.makeText(FieldActivity.this, "left", Toast.LENGTH_SHORT).show();
                move_left(btnTab);
            }
            public void onSwipeBottom() {
                //Toast.makeText(FieldActivity.this, "bottom", Toast.LENGTH_SHORT).show();
                move_down(btnTab);
            }

        });
    }
    public void move_right(Button[] btn){
        for(int i=0;i<4;i++) {
            move_line_right(btn,1,i);
            move_line_right(btn,2,i);
            move_line_right(btn,3,i);
            move_line_right(btn,4,i);
        }
    }
    public void move_left(Button[] btn){
        for(int i=3;i>=0;i--) {
            move_line_left(btn,1,i);
            move_line_left(btn,2,i);
            move_line_left(btn,3,i);
            move_line_left(btn,4,i);
        }
    }
    public void move_up(Button[] btn){
        for (int i = 0; i < 4; i++) {
            move_line_up(btn,1,i);
            move_line_up(btn,2,i);
            move_line_up(btn,3,i);
            move_line_up(btn,4,i);
        }
    }
    public void move_down(Button[] btn) {
        for (int i = 3; i >=0 ; i--) {
            move_line_down(btn,1,i);
            move_line_down(btn,2,i);
            move_line_down(btn,3,i);
            move_line_down(btn,4,i);

        }
    }
    public void startGame(Button[] btn){
        resetGame(btn);
        int rand_first_number = new Random().nextInt(16);
        int rand_second_number = new Random().nextInt(16);
        if(rand_first_number!=rand_second_number){
        btn[rand_first_number].setText("2");
        btn[rand_second_number].setText("2");
        }
        else{
            rand_first_number=rand_first_number%2+1;
            btn[rand_first_number].setText("2");
            btn[rand_second_number].setText("2");
        }
    }
    public void resetGame(Button[] btn){
        for(int i=0;i<16;i++){
            btn[i].setText("");
        }
    }
    public void move_line_left(Button[] btn,int line, int i){
        String tmp;
        if (btn[(line*4-1)-i].getText().toString()!=""){
            tmp=btn[(line*4-1)-i].getText().toString();
            if(btn[(line*4-4)].getText().toString()=="") {
                btn[(line*4-1)-i].setText("");
                btn[(line*4-4)].setText(tmp);
            }
            else if(btn[(line*4-3)].getText().toString()==""){
                btn[(line*4-1) - i].setText("");
                btn[(line*4-3)].setText(tmp);
            }
            else if(btn[(line*4-2)].getText().toString()==""){
                btn[(line*4-1) - i].setText("");
                btn[(line*4-2)].setText(tmp);
            }
            else{
                btn[(line*4-1) - i].setText("");
                btn[(line*4-1)].setText(tmp);
            }
        }
    }
    public void move_line_right(Button[] btn,int line, int i){
        String tmp;
        if (btn[(line*4-1)-i].getText().toString()!=""){
            tmp=btn[(line*4-1)-i].getText().toString();
            if(btn[(line*4-1)].getText().toString()=="") {
                btn[(line*4-1)-i].setText("");
                btn[(line*4-1)].setText(tmp);
            }
            else if(btn[(line*4-2)].getText().toString()==""){
                btn[(line*4-1) - i].setText("");
                btn[(line*4-2)].setText(tmp);
            }
            else if(btn[(line*4-3)].getText().toString()==""){
                btn[(line*4-1) - i].setText("");
                btn[(line*4-3)].setText(tmp);
            }
            else{
                btn[(line*4-1) - i].setText("");
                btn[(line*4-4)].setText(tmp);
            }
        }
    }
    public void move_line_up(Button[] btn, int column, int i){
        String tmp;
        int col=column-1;
        if (btn[i*4+col].getText().toString()!=""){
            tmp=btn[i*4+col].getText().toString();
            if(btn[0+col].getText().toString()=="") {
                btn[i*4+col].setText("");
                btn[col].setText(tmp);
            }
            else if(btn[4+col].getText().toString()==""){
                btn[i*4+col].setText("");
                btn[col+4].setText(tmp);
            }
            else if(btn[8+col].getText().toString()==""){
                btn[i*4+col].setText("");
                btn[col+8].setText(tmp);
            }
            else{
                btn[i*4+col].setText("");
                btn[col+12].setText(tmp);
            }
        }
    }
    public void move_line_down(Button[] btn, int column, int i){
        String tmp;
        int col=column-1;
        if (btn[i*4+col].getText().toString()!="") {
            tmp = btn[i * 4 + col].getText().toString();
            if(btn[12+col].getText().toString()=="") {
                btn[i*4+col].setText("");
                btn[col+12].setText(tmp);
            }
            else if(btn[8+col].getText().toString()==""){
                btn[i*4+col].setText("");
                btn[col+8].setText(tmp);
            }
            else if(btn[4+col].getText().toString()==""){
                btn[i*4+col].setText("");
                btn[col+4].setText(tmp);
            }
            else{
                btn[i*4+col].setText("");
                btn[col].setText(tmp);
            }
        }

    }
}