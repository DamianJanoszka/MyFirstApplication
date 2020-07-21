package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MarginLayoutParamsCompat;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.lang.reflect.Field;
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
                move_up(btnTab);
                updateGame(btnTab);
                change_btn_color(btnTab);
            }
            public void onSwipeRight() {
                move_right(btnTab);
                updateGame(btnTab);
                change_btn_color(btnTab);
            }
            public void onSwipeLeft() {
                move_left(btnTab);
                updateGame(btnTab);
                change_btn_color(btnTab);
            }
            public void onSwipeBottom() {
                move_down(btnTab);
                updateGame(btnTab);
                change_btn_color(btnTab);
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
        sum_numbers_right(btn);
    }
    public void move_left(Button[] btn){
        for(int i=3;i>=0;i--) {
            move_line_left(btn,1,i);
            move_line_left(btn,2,i);
            move_line_left(btn,3,i);
            move_line_left(btn,4,i);
        }
        sum_numbers_left(btn);
    }
    public void move_up(Button[] btn){
        for (int i = 0; i < 4; i++) {
            move_line_up(btn,1,i);
            move_line_up(btn,2,i);
            move_line_up(btn,3,i);
            move_line_up(btn,4,i);
        }
        sum_numbers_up(btn);
    }
    public void move_down(Button[] btn) {
        for (int i = 3; i >=0 ; i--) {
            move_line_down(btn,1,i);
            move_line_down(btn,2,i);
            move_line_down(btn,3,i);
            move_line_down(btn,4,i);
        }
        sum_numbers_down(btn);
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
        change_btn_color(btn);
    }
    public void updateGame(Button[] btn){
        boolean updatedField=true;
        int  inf_loop_protection=0;
        int rand;
        while(updatedField){
            rand = new Random().nextInt(16);
            inf_loop_protection++;
            if(btn[rand].getText().toString()==""){
                btn[rand].setText("2");
                updatedField=false;
            }
            else if(inf_loop_protection>50000){
                Toast.makeText(FieldActivity.this, "Game over", Toast.LENGTH_SHORT).show();
                break;
            }
            }
    }
    public void resetGame(Button[] btn){
        for(int i=0;i<16;i++){
            btn[i].setText("");
        }
    }
    public void move_line_left(Button[] btn,int line, int i){
        String tmp;
        if (btn[(line*4-1)-i].getText().toString()!=""&&i!=3){
            tmp=btn[(line*4-1)-i].getText().toString();
            if(btn[(line*4-4)].getText().toString()=="") {
                btn[(line*4-1)-i].setText("");
                btn[(line*4-4)].setText(tmp);
            }
            else if(btn[(line*4-3)].getText().toString()==""){
                btn[(line*4-1) - i].setText("");
                btn[(line*4-3)].setText(tmp);
            }
            else if(btn[(line*4-2)].getText().toString()==""&&i!=2){
                btn[(line*4-1) - i].setText("");
                btn[(line*4-2)].setText(tmp);
            }
            else if(i!=2&&i!=1){
                btn[(line*4-1) - i].setText("");
                btn[(line*4-1)].setText(tmp);
            }
        }
    }
    public void move_line_right(Button[] btn,int line, int i){
        String tmp;
        if (btn[(line*4-1)-i].getText().toString()!=""&&i!=0){
            tmp=btn[(line*4-1)-i].getText().toString();
            if(btn[(line*4-1)].getText().toString()=="") {
                btn[(line*4-1)-i].setText("");
                btn[(line*4-1)].setText(tmp);
            }
            else if(btn[(line*4-2)].getText().toString()==""){
               btn[(line*4-1) - i].setText("");
                btn[(line*4-2)].setText(tmp);
            }
            else if(btn[(line*4-3)].getText().toString()==""&&i!=1){
                btn[(line*4-1) - i].setText("");
                btn[(line*4-3)].setText(tmp);
            }
            else if(i!=1&&i!=2){
                btn[(line*4-1) - i].setText("");
                btn[(line*4-4)].setText(tmp);
            }
        }
    }
    public void move_line_up(Button[] btn, int column, int i){
        String tmp;
        int col=column-1;
        if (btn[i*4+col].getText().toString()!=""&&i!=0){
            tmp=btn[i*4+col].getText().toString();
            if(btn[0+col].getText().toString()=="") {
                btn[i*4+col].setText("");
                btn[col].setText(tmp);
            }
            else if(btn[4+col].getText().toString()==""){
                btn[i*4+col].setText("");
                btn[col+4].setText(tmp);
            }
            else if(btn[8+col].getText().toString()==""&i!=1){
                btn[i*4+col].setText("");
                btn[col+8].setText(tmp);
            }
            else if (i!=1&&i!=2){
                btn[i*4+col].setText("");
                btn[col+12].setText(tmp);
            }
        }
    }
    public void move_line_down(Button[] btn, int column, int i){
        String tmp;
        int col=column-1;
        if (btn[i*4+col].getText().toString()!=""&&i!=3) {
            tmp = btn[i * 4 + col].getText().toString();
            if(btn[12+col].getText().toString()=="") {
                btn[i*4+col].setText("");
                btn[col+12].setText(tmp);
            }
            else if(btn[8+col].getText().toString()==""){
                btn[i*4+col].setText("");
                btn[col+8].setText(tmp);
            }
            else if(btn[4+col].getText().toString()==""&&i!=2){
                btn[i*4+col].setText("");
                btn[col+4].setText(tmp);
            }
            else if (i!=2&&i!=1){
                btn[i*4+col].setText("");
                btn[col].setText(tmp);
            }
        }

    }
    public void sum_numbers_right(Button[] btn){
        for(int i=0;i<4;i++) {
            if (btn[3 + i * 4].getText().toString().equals(btn[2 + i * 4].getText().toString()) && btn[3 + i * 4].getText().toString() != "") {
                btn[3 + i * 4].setText(String.valueOf(2 * Integer.parseInt(btn[3 + i * 4].getText().toString())));
                btn[2 + i * 4].setText(btn[1 + i * 4].getText().toString());
                btn[1 + i * 4].setText(btn[0 + i * 4].getText().toString());
                btn[0 + i * 4].setText("");

            } else if (btn[2 + i * 4].getText().toString().equals(btn[1 + i * 4].getText().toString()) && btn[2 + i * 4].getText().toString() != "") {
                btn[2 + i * 4].setText(String.valueOf(2 * Integer.parseInt(btn[2 + i * 4].getText().toString())));
                btn[1 + i * 4].setText(btn[0 + i * 4].getText().toString());
                btn[0 + i * 4].setText("");
            } else if (btn[1 + i * 4].getText().toString().equals(btn[0 + i * 4].getText().toString()) && btn[1 + i * 4].getText().toString() != "") {
                btn[1 + i * 4].setText(String.valueOf(2 * Integer.parseInt(btn[1 + i * 4].getText().toString())));
                btn[0 + i * 4].setText("");
            }
        }
    }
    public void sum_numbers_left(Button[] btn) {
        for (int line = 1; line<=4; line++) {
            if (btn[0 + (line - 1) * 4].getText().toString().equals(btn[1 + (line - 1) * 4].getText().toString()) && btn[0 + (line - 1) * 4].getText().toString() != "") {
                btn[0 + (line - 1) * 4].setText(String.valueOf(2 * Integer.parseInt(btn[0 + (line - 1) * 4].getText().toString())));
                btn[1 + (line - 1) * 4].setText(btn[2 + (line - 1) * 4].getText().toString());
                btn[2 + (line - 1) * 4].setText(btn[3 + (line - 1) * 4].getText().toString());
                btn[3 + (line - 1) * 4].setText("");
            } else if (btn[1 + (line - 1) * 4].getText().toString().equals(btn[2 + (line - 1) * 4].getText().toString()) && btn[1 + (line - 1) * 4].getText().toString() != "") {
                btn[1 + (line - 1) * 4].setText(String.valueOf(2 * Integer.parseInt(btn[1 + (line - 1) * 4].getText().toString())));
                btn[2 + (line - 1) * 4].setText(btn[3 + (line - 1) * 4].getText().toString());
                btn[3 + (line - 1) * 4].setText("");
            } else if (btn[2 + (line - 1) * 4].getText().toString().equals(btn[3 + (line - 1) * 4].getText().toString()) && btn[2 + (line - 1) * 4].getText().toString() != "") {
                btn[2 + (line - 1) * 4].setText(String.valueOf(2 * Integer.parseInt(btn[2 + (line - 1) * 4].getText().toString())));
                btn[3 + (line - 1) * 4].setText("");
            }
        }
    }
    public void sum_numbers_up(Button[] btn){
        for(int i=0;i<4;i++) {
            if (btn[0 + i].getText().toString().equals(btn[4 + i].getText().toString()) && btn[0 + i].getText().toString() != "") {
                btn[0 + i].setText(String.valueOf(2 * Integer.parseInt(btn[0 + i].getText().toString())));
                btn[4 + i].setText(btn[8 + i].getText().toString());
                btn[8 + i].setText(btn[12 + i].getText().toString());
                btn[12 + i].setText("");

            } else if (btn[4 + i].getText().toString().equals(btn[8 + i].getText().toString()) && btn[4 + i].getText().toString() != "") {
                btn[4+ i ].setText(String.valueOf(2 * Integer.parseInt(btn[4 + i].getText().toString())));
                btn[8 + i].setText(btn[12 + i].getText().toString());
                btn[12 + i].setText("");
            } else if (btn[8 + i].getText().toString().equals(btn[12 + i].getText().toString()) && btn[8 + i].getText().toString() != "") {
                btn[8 + i].setText(String.valueOf(2 * Integer.parseInt(btn[8 + i].getText().toString())));
                btn[12 + i].setText("");
            }
        }
    }
    public void sum_numbers_down(Button[] btn){
        for (int i=3;i>=0;i--){
            if (btn[12 + i].getText().toString().equals(btn[8 + i].getText().toString()) && btn[12 + i].getText().toString() != "") {
                btn[12 + i].setText(String.valueOf(2 * Integer.parseInt(btn[12 + i].getText().toString())));
                btn[8 + i].setText(btn[4 + i].getText().toString());
                btn[4 + i].setText(btn[0 + i].getText().toString());
                btn[0 + i].setText("");

            } else if (btn[8 + i].getText().toString().equals(btn[4 + i].getText().toString()) && btn[8 + i].getText().toString() != "") {
                btn[8 + i ].setText(String.valueOf(2 * Integer.parseInt(btn[8 + i].getText().toString())));
                btn[4 + i].setText(btn[0 + i].getText().toString());
                btn[0 + i].setText("");
            } else if (btn[4 + i].getText().toString().equals(btn[0 + i].getText().toString()) && btn[4 + i].getText().toString() != "") {
                btn[4 + i].setText(String.valueOf(2 * Integer.parseInt(btn[4 + i].getText().toString())));
                btn[0 + i].setText("");
            }
        }
    }
    public void change_btn_color(Button[] tab){
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
                    break;
                default:
                   // tab[i].setBackgroundColor(Color.LTGRAY);
                    tab[i].setBackground(new ColorDrawable(Color.LTGRAY));



            }

        }
    }
}