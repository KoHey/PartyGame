package com.example.kenkou.partygame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.IOException;
import java.util.Random;

public class GamePlaySingle extends Activity{
    Globals globals;
    private ImageView iTarget1,iTarget2,iTarget3,
                        iTarget4,iTarget5,iTarget6,
                        iTarget7,iTarget8,iTarget9;
    private int targetFlg1 = 0,targetFlg2 = 0,targetFlg3 = 0,
                  targetFlg4 = 0,targetFlg5 = 0,targetFlg6 = 0,
                  targetFlg7 = 0,targetFlg8 = 0,targetFlg9 = 0;//これの中身が点数
    private TextView tTimerP1;//タイマー
    private TextView tScoreP1;
    private int scoreP1 = 0;
    private MediaPlayer mediaPlayer;//bgm用


    // インスタンス生成 CountDownTimer(long millisInFuture, long countDownInterval)
    // 3分= 3x60x1000 = 180000 msec
    public CountDown countDown = new CountDown(30000, 1000);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play_single);
        globals = (Globals)this.getApplication();
        globals.gScoreP1 = "0";
        tTimerP1 = (TextView)findViewById(R.id.textTimerP1);
        tTimerP1.setText("00:00");
        tScoreP1 = (TextView)findViewById(R.id.textScoreP1);
        tScoreP1.setText( String.valueOf(scoreP1));

        /*ターゲットの記述*/
        iTarget1 = (ImageView) findViewById(R.id.imageTarget1);
        iTarget1.setImageResource(R.drawable.human);
        iTarget2 = (ImageView) findViewById(R.id.imageTarget2);
        iTarget2.setImageResource(R.drawable.human);
        iTarget3 = (ImageView) findViewById(R.id.imageTarget3);
        iTarget3.setImageResource(R.drawable.human);
        iTarget4 = (ImageView) findViewById(R.id.imageTarget4);
        iTarget4.setImageResource(R.drawable.human);
        iTarget5 = (ImageView) findViewById(R.id.imageTarget5);
        iTarget5.setImageResource(R.drawable.human);
        iTarget6 = (ImageView) findViewById(R.id.imageTarget6);
        iTarget6.setImageResource(R.drawable.human);
        iTarget7 = (ImageView) findViewById(R.id.imageTarget7);
        iTarget7.setImageResource(R.drawable.human);
        iTarget8 = (ImageView) findViewById(R.id.imageTarget8);
        iTarget8.setImageResource(R.drawable.human);
        iTarget9 = (ImageView) findViewById(R.id.imageTarget9);
        iTarget9.setImageResource(R.drawable.human);

        /*リスナーの処理*/
        iTarget1.setOnClickListener(UpLeftClickListener);
        iTarget2.setOnClickListener(UpCenterClickListener);
        iTarget3.setOnClickListener(UpRightClickListener);
        iTarget4.setOnClickListener(MiddleLeftClickListener);
        iTarget5.setOnClickListener(MiddleCenterClickListener);
        iTarget6.setOnClickListener(MiddleRightClickListener);
        iTarget7.setOnClickListener(UnderLeftClickListener);
        iTarget8.setOnClickListener(UnderCenterClickListener);
        iTarget9.setOnClickListener(UnderRightClickListener);

        StartDialog();
    }



    //どのフラグかチェックする
    private int FlgCheck(int playerNum, int targetNum){
        int fc = 10;
        if(playerNum == 1 && targetNum == 1){
            fc = targetFlg1;
        }else if(playerNum == 1 && targetNum == 2){
            fc = targetFlg2;
        }else if(playerNum == 1 && targetNum == 3){
            fc = targetFlg3;
        }else if(playerNum == 2 && targetNum == 1){
            fc = targetFlg4;
        }else if(playerNum == 2 && targetNum == 2){
            fc = targetFlg5;
        }else if(playerNum == 2 && targetNum == 3){
            fc = targetFlg6;
        }else if(playerNum == 3 && targetNum == 1){
            fc = targetFlg7;
        }else if(playerNum == 3 && targetNum == 2){
            fc = targetFlg8;
        }else if(playerNum == 3 && targetNum == 3){
            fc = targetFlg9;
        }
        return fc;
    }
    //スコア加算の処理
    private void AddScore(int playerNum,int num) {
        if (playerNum == 1) {
            if (num == 1) {
                iTarget1.setImageResource(R.drawable.player1tap);
                scoreP1 += targetFlg1;
                targetFlg1 = 10;
            } else if (num == 2) {
                iTarget2.setImageResource(R.drawable.player1tap);
                scoreP1 += targetFlg2;
                targetFlg2 = 10;
            } else if (num == 3) {
                iTarget3.setImageResource(R.drawable.player1tap);
                scoreP1 += targetFlg3;
                targetFlg3 = 10;
            }
            tScoreP1.setText( String.valueOf(scoreP1));
        }else if (playerNum == 2) {
            if (num == 1) {
                iTarget4.setImageResource(R.drawable.player1tap);
                scoreP1 += targetFlg4;
                targetFlg4 = 10;
            } else if (num == 2) {
                iTarget5.setImageResource(R.drawable.player1tap);
                scoreP1 += targetFlg5;
                targetFlg5 = 10;
            } else if (num == 3) {
                iTarget6.setImageResource(R.drawable.player1tap);
                scoreP1 += targetFlg6;
                targetFlg6 = 10;
            }
            tScoreP1.setText( String.valueOf(scoreP1));
        }else if (playerNum == 3) {
            if (num == 1) {
                iTarget7.setImageResource(R.drawable.player1tap);
                scoreP1 += targetFlg7;
                targetFlg7 = 10;
            } else if (num == 2) {
                iTarget8.setImageResource(R.drawable.player1tap);
                scoreP1 += targetFlg8;
                targetFlg8 = 10;
            } else if (num == 3) {
                iTarget9.setImageResource(R.drawable.player1tap);
                scoreP1 += targetFlg9;
                targetFlg9 = 10;
            }
            tScoreP1.setText( String.valueOf(scoreP1));
        }
    }
    //クリックされた時の処理
    private void ClickProcess(int playerNum,int targetNum,int clFlg){
        clFlg = FlgCheck(playerNum,targetNum);
        if(clFlg == 10){
            //P1がクリックした後targeFlgが10になる
            //きつくするならここにscoreをマイナスする記述をする
        }else{
            AddScore(playerNum,targetNum);
        }
    }

    /*リスナーの処理*/
    View.OnClickListener UpLeftClickListener = new View.OnClickListener(){
        int playerNum = 1;//行
        int targetNum = 1;//列
        int clFlg;
        @Override
        public void onClick(View v) {
            ClickProcess(playerNum,targetNum,clFlg);
        }
    };
    View.OnClickListener UpCenterClickListener = new View.OnClickListener() {
        int playerNum = 1;
        int targetNum = 2;
        int clFlg;
        @Override
        public void onClick(View v) {
            ClickProcess(playerNum,targetNum,clFlg);
        }
    };
    View.OnClickListener UpRightClickListener = new View.OnClickListener() {
        int playerNum = 1;
        int targetNum = 3;
        int clFlg;
        @Override
        public void onClick(View v) {
            ClickProcess(playerNum,targetNum,clFlg);
        }
    };
    View.OnClickListener MiddleLeftClickListener = new View.OnClickListener(){
        int playerNum = 2;//行
        int targetNum = 1;//列
        int clFlg;
        @Override
        public void onClick(View v) {
            ClickProcess(playerNum,targetNum,clFlg);
        }
    };
    View.OnClickListener MiddleCenterClickListener = new View.OnClickListener(){
        int playerNum = 2;//行
        int targetNum = 2;//列
        int clFlg;
        @Override
        public void onClick(View v) {
            ClickProcess(playerNum,targetNum,clFlg);
        }
    };
    View.OnClickListener MiddleRightClickListener = new View.OnClickListener(){
        int playerNum = 2;//行
        int targetNum = 3;//列
        int clFlg;
        @Override
        public void onClick(View v) {
            ClickProcess(playerNum,targetNum,clFlg);
        }
    };
    View.OnClickListener UnderLeftClickListener = new View.OnClickListener(){
        int playerNum = 3;//行
        int targetNum = 1;//列
        int clFlg;
        @Override
        public void onClick(View v) {
            ClickProcess(playerNum,targetNum,clFlg);
        }
    };
    View.OnClickListener UnderCenterClickListener = new View.OnClickListener(){
        int playerNum = 3;//行
        int targetNum = 2;//列
        int clFlg;
        @Override
        public void onClick(View v) {
            ClickProcess(playerNum,targetNum,clFlg);
        }
    };
    View.OnClickListener UnderRightClickListener = new View.OnClickListener(){
        int playerNum = 3;//行
        int targetNum = 3;//列
        int clFlg;
        @Override
        public void onClick(View v) {
            ClickProcess(playerNum,targetNum,clFlg);
        }
    };

    //ゲームをスタートするダイアログを出す
    private void StartDialog(){
        // ポップアップメニュー表示
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(GamePlaySingle.this);
        alertDialog.setTitle("よーい...");
        alertDialog.setMessage("スタートを押すとはじまるで");
        alertDialog.setPositiveButton("スタート", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 音楽再生
                audioPlay();
                countDown.start();//タイマースタート
            }
        });
        AlertDialog myDialog = alertDialog.create();
        myDialog.setCanceledOnTouchOutside(false);//ダイアログ画面外をタッチされても消えないようにする
        myDialog.show();
    }

    //ゲーム終了時にリザルト画面へ行ダイアログを出す
    private void GoToResultDialog(){
        // ポップアップメニュー表示
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(GamePlaySingle.this);
        alertDialog.setTitle("おわり");
        alertDialog.setMessage("酔いは冷めたか？");
        alertDialog.setPositiveButton("結果を見る", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                globals.gScoreP1 = String.valueOf(scoreP1);
                Intent intent = new Intent(getApplication(), ResultSingle.class);
                startActivity(intent);
            }
        });
        AlertDialog myDialog = alertDialog.create();
        myDialog.setCanceledOnTouchOutside(false);//ダイアログ画面外をタッチされても消えないようにする
        myDialog.show();
    }

    //カウントダウンタイマ
    private class CountDown extends CountDownTimer {
        public CountDown(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        //終わった時の
        @Override
        public void onFinish() {
            // 完了
            // 音楽停止
            audioStop();
            tTimerP1.setText("00:00");
            GoToResultDialog();
        }
        // インターバルで呼ばれる
        @Override
        public void onTick(long millisUntilFinished) {
            setTarget1(10);
            setTarget2(10);
            setTarget3(10);
            // 残り時間を分、秒、ミリ秒に分割
            long mm = millisUntilFinished / 1000 / 60;
            long ss = millisUntilFinished / 1000 % 60;
            tTimerP1.setText(String.format("%1$02d:%2$02d", mm, ss));
        }
    }

    //ターゲット出現の確率をいじるところ
    private void setTarget1(int x){
        //乱数生成
        Random r = new Random();
        int n = r.nextInt(x);
        switch (n){
            case 0:
                targetPtn1("NONE");
                break;
            case 1:
                targetPtn1("LEFT");
                break;
            case 2:
                targetPtn1("CENTER");
                break;
            case 3:
                targetPtn1("RIGHT");
                break;
            case 4:
                targetPtn1("LEFT_ARROW");
                break;
            case 5:
                targetPtn1("RIGHT_ARROW");
                break;
            case 6:
                targetPtn1("CENTER_ARROWRIGHT_NORMAL");
                break;
            case 7:
                targetPtn1("CENTER_ARROWLEFT_NORMAL");
                break;
            case 8:
                targetPtn1("CENTER_ARROWRIGHT_REVERSE");
                break;
            case 9:
                targetPtn1("CENTER_ARROWLEFT_REVERSE");
                break;
        }
    }
    //ターゲット出現の確率をいじるところ
    private void setTarget2(int x){
        //乱数生成
        Random r = new Random();
        int n = r.nextInt(x);
        switch (n){
            case 0:
                targetPtn2("NONE");
                break;
            case 1:
                targetPtn2("LEFT");
                break;
            case 2:
                targetPtn2("CENTER");
                break;
            case 3:
                targetPtn2("RIGHT");
                break;
            case 4:
                targetPtn2("LEFT_ARROW");
                break;
            case 5:
                targetPtn2("RIGHT_ARROW");
                break;
            case 6:
                targetPtn2("CENTER_ARROWRIGHT_NORMAL");
                break;
            case 7:
                targetPtn2("CENTER_ARROWLEFT_NORMAL");
                break;
            case 8:
                targetPtn2("CENTER_ARROWRIGHT_REVERSE");
                break;
            case 9:
                targetPtn2("CENTER_ARROWLEFT_REVERSE");
                break;
        }
    }
    //ターゲット出現の確率をいじるところ
    private void setTarget3(int x){
        //乱数生成
        Random r = new Random();
        int n = r.nextInt(x);
        switch (n){
            case 0:
                targetPtn3("NONE");
                break;
            case 1:
                targetPtn3("LEFT");
                break;
            case 2:
                targetPtn3("CENTER");
                break;
            case 3:
                targetPtn3("RIGHT");
                break;
            case 4:
                targetPtn3("LEFT_ARROW");
                break;
            case 5:
                targetPtn3("RIGHT_ARROW");
                break;
            case 6:
                targetPtn3("CENTER_ARROWRIGHT_NORMAL");
                break;
            case 7:
                targetPtn3("CENTER_ARROWLEFT_NORMAL");
                break;
            case 8:
                targetPtn3("CENTER_ARROWRIGHT_REVERSE");
                break;
            case 9:
                targetPtn3("CENTER_ARROWLEFT_REVERSE");
                break;
        }
    }

    //ターゲットのパターン一覧
    private void targetPtn1(String ptn){
        if(ptn == "NONE"){
            /*ターゲットの記述*/
            //×　×　×
            targetFlg1 = 0; targetFlg2 = 0; targetFlg3 = 0;
            iTarget1.setImageResource(R.drawable.human);
            iTarget2.setImageResource(R.drawable.human);
            iTarget3.setImageResource(R.drawable.human);
        }else if(ptn == "LEFT"){
            /*ターゲットの記述*/
            //○　×　×
            targetFlg1 = 1; targetFlg2 = 0; targetFlg3 = 0;
            iTarget1.setImageResource(R.drawable.drunk);
            iTarget2.setImageResource(R.drawable.human);
            iTarget3.setImageResource(R.drawable.human);
        }else if(ptn == "CENTER"){
            /*ターゲットの記述*/
            //×　○　×
            targetFlg1 = 0; targetFlg2 = 1; targetFlg3 = 0;
            iTarget1.setImageResource(R.drawable.human);
            iTarget2.setImageResource(R.drawable.drunk);
            iTarget3.setImageResource(R.drawable.human);
        }else if(ptn == "RIGHT"){
            /*ターゲットの記述*/
            //×　×　○
            targetFlg1 = 0; targetFlg2 = 0; targetFlg3 = 1;
            iTarget1.setImageResource(R.drawable.human);
            iTarget2.setImageResource(R.drawable.human);
            iTarget3.setImageResource(R.drawable.drunk);
        }else if(ptn == "LEFT_ARROW"){
            /*ターゲットの記述*/
            //→　×　×
            targetFlg1 = -1; targetFlg2 = 1; targetFlg3 = 0;
            iTarget1.setImageResource(R.drawable.right);
            iTarget2.setImageResource(R.drawable.human);
            iTarget3.setImageResource(R.drawable.human);
        }else if(ptn == "RIGHT_ARROW"){
            /*ターゲットの記述*/
            //×　×　←
            targetFlg1 = 0; targetFlg2 = 1; targetFlg3 = -1;
            iTarget1.setImageResource(R.drawable.human);
            iTarget2.setImageResource(R.drawable.human);
            iTarget3.setImageResource(R.drawable.left);
        }else if(ptn == "CENTER_ARROWRIGHT_NORMAL"){
            /*ターゲットの記述*/
            //×　→　×
            targetFlg1 = -2; targetFlg2 = -1; targetFlg3 = 2;
            iTarget1.setImageResource(R.drawable.human);
            iTarget2.setImageResource(R.drawable.right);
            iTarget3.setImageResource(R.drawable.human);
        }else if(ptn == "CENTER_ARROWLEFT_NORMAL"){
            /*ターゲットの記述*/
            //×　←　×
            targetFlg1 = 2; targetFlg2 = -1; targetFlg3 = -2;
            iTarget1.setImageResource(R.drawable.human);
            iTarget2.setImageResource(R.drawable.left);
            iTarget3.setImageResource(R.drawable.human);
        }else if(ptn == "CENTER_ARROWRIGHT_REVERSE"){
            /*ターゲットの記述*/
            //×　→|　×
            targetFlg1 = 2; targetFlg2 = 1; targetFlg3 = -2;
            iTarget1.setImageResource(R.drawable.human);
            iTarget2.setImageResource(R.drawable.right_reverse);
            iTarget3.setImageResource(R.drawable.human);
        }else if(ptn == "CENTER_ARROWLEFT_REVERSE"){
            /*ターゲットの記述*/
            //×　|←　×
            targetFlg1 = -2; targetFlg2 = 1; targetFlg3 = 2;
            iTarget1.setImageResource(R.drawable.human);
            iTarget2.setImageResource(R.drawable.left_reverse);
            iTarget3.setImageResource(R.drawable.human);
        }
    }
    //ターゲットのパターン一覧
    private void targetPtn2(String ptn){
        if(ptn == "NONE"){
            /*ターゲットの記述*/
            //×　×　×
            targetFlg4 = 0; targetFlg5 = 0; targetFlg6 = 0;
            iTarget4.setImageResource(R.drawable.human);
            iTarget5.setImageResource(R.drawable.human);
            iTarget6.setImageResource(R.drawable.human);
        }else if(ptn == "LEFT"){
            /*ターゲットの記述*/
            //○　×　×
            targetFlg4 = 1; targetFlg5 = 0; targetFlg6 = 0;
            iTarget4.setImageResource(R.drawable.drunk);
            iTarget5.setImageResource(R.drawable.human);
            iTarget6.setImageResource(R.drawable.human);
        }else if(ptn == "CENTER"){
            /*ターゲットの記述*/
            //×　○　×
            targetFlg4 = 0; targetFlg5 = 1; targetFlg6 = 0;
            iTarget4.setImageResource(R.drawable.human);
            iTarget5.setImageResource(R.drawable.drunk);
            iTarget6.setImageResource(R.drawable.human);
        }else if(ptn == "RIGHT"){
            /*ターゲットの記述*/
            //×　×　○
            targetFlg4 = 0; targetFlg5 = 0; targetFlg6 = 1;
            iTarget4.setImageResource(R.drawable.human);
            iTarget5.setImageResource(R.drawable.human);
            iTarget6.setImageResource(R.drawable.drunk);
        }else if(ptn == "LEFT_ARROW"){
            /*ターゲットの記述*/
            //→　×　×
            targetFlg4 = -1; targetFlg5 = 1; targetFlg6 = 0;
            iTarget4.setImageResource(R.drawable.right);
            iTarget5.setImageResource(R.drawable.human);
            iTarget6.setImageResource(R.drawable.human);
        }else if(ptn == "RIGHT_ARROW"){
            /*ターゲットの記述*/
            //×　×　←
            targetFlg4 = 0; targetFlg5 = 1; targetFlg6 = -1;
            iTarget4.setImageResource(R.drawable.human);
            iTarget5.setImageResource(R.drawable.human);
            iTarget6.setImageResource(R.drawable.left);
        }else if(ptn == "CENTER_ARROWRIGHT_NORMAL"){
            /*ターゲットの記述*/
            //×　→　×
            targetFlg4 = -2; targetFlg5 = -1; targetFlg6 = 2;
            iTarget4.setImageResource(R.drawable.human);
            iTarget5.setImageResource(R.drawable.right);
            iTarget6.setImageResource(R.drawable.human);
        }else if(ptn == "CENTER_ARROWLEFT_NORMAL"){
            /*ターゲットの記述*/
            //×　←　×
            targetFlg4 = 2; targetFlg5 = -1; targetFlg6 = -2;
            iTarget4.setImageResource(R.drawable.human);
            iTarget5.setImageResource(R.drawable.left);
            iTarget6.setImageResource(R.drawable.human);
        }else if(ptn == "CENTER_ARROWRIGHT_REVERSE"){
            /*ターゲットの記述*/
            //×　→|　×
            targetFlg4 = 2; targetFlg5 = 1; targetFlg6 = -2;
            iTarget4.setImageResource(R.drawable.human);
            iTarget5.setImageResource(R.drawable.right_reverse);
            iTarget6.setImageResource(R.drawable.human);
        }else if(ptn == "CENTER_ARROWLEFT_REVERSE"){
            /*ターゲットの記述*/
            //×　|←　×
            targetFlg4 = -2; targetFlg5 = 1; targetFlg6 = 2;
            iTarget4.setImageResource(R.drawable.human);
            iTarget5.setImageResource(R.drawable.left_reverse);
            iTarget6.setImageResource(R.drawable.human);
        }
    }
    //ターゲットのパターン一覧
    private void targetPtn3(String ptn){
        if(ptn == "NONE"){
            /*ターゲットの記述*/
            //×　×　×
            targetFlg7 = 0; targetFlg8 = 0; targetFlg9 = 0;
            iTarget7.setImageResource(R.drawable.human);
            iTarget8.setImageResource(R.drawable.human);
            iTarget9.setImageResource(R.drawable.human);
        }else if(ptn == "LEFT"){
            /*ターゲットの記述*/
            //○　×　×
            targetFlg7 = 1; targetFlg8 = 0; targetFlg9 = 0;
            iTarget7.setImageResource(R.drawable.drunk);
            iTarget8.setImageResource(R.drawable.human);
            iTarget9.setImageResource(R.drawable.human);
        }else if(ptn == "CENTER"){
            /*ターゲットの記述*/
            //×　○　×
            targetFlg7 = 0; targetFlg8 = 1; targetFlg9 = 0;
            iTarget7.setImageResource(R.drawable.human);
            iTarget8.setImageResource(R.drawable.drunk);
            iTarget9.setImageResource(R.drawable.human);
        }else if(ptn == "RIGHT"){
            /*ターゲットの記述*/
            //×　×　○
            targetFlg7 = 0; targetFlg8 = 0; targetFlg9 = 1;
            iTarget7.setImageResource(R.drawable.human);
            iTarget8.setImageResource(R.drawable.human);
            iTarget9.setImageResource(R.drawable.drunk);
        }else if(ptn == "LEFT_ARROW"){
            /*ターゲットの記述*/
            //→　×　×
            targetFlg7 = -1; targetFlg8 = 1; targetFlg9 = 0;
            iTarget7.setImageResource(R.drawable.right);
            iTarget8.setImageResource(R.drawable.human);
            iTarget9.setImageResource(R.drawable.human);
        }else if(ptn == "RIGHT_ARROW"){
            /*ターゲットの記述*/
            //×　×　←
            targetFlg7 = 0; targetFlg8 = 1; targetFlg9 = -1;
            iTarget7.setImageResource(R.drawable.human);
            iTarget8.setImageResource(R.drawable.human);
            iTarget9.setImageResource(R.drawable.left);
        }else if(ptn == "CENTER_ARROWRIGHT_NORMAL"){
            /*ターゲットの記述*/
            //×　→　×
            targetFlg7 = -2; targetFlg8 = -1; targetFlg9 = 2;
            iTarget7.setImageResource(R.drawable.human);
            iTarget8.setImageResource(R.drawable.right);
            iTarget9.setImageResource(R.drawable.human);
        }else if(ptn == "CENTER_ARROWLEFT_NORMAL"){
            /*ターゲットの記述*/
            //×　←　×
            targetFlg7 = 2; targetFlg8 = -1; targetFlg9 = -2;
            iTarget7.setImageResource(R.drawable.human);
            iTarget8.setImageResource(R.drawable.left);
            iTarget9.setImageResource(R.drawable.human);
        }else if(ptn == "CENTER_ARROWRIGHT_REVERSE"){
            /*ターゲットの記述*/
            //×　→|　×
            targetFlg7 = 2; targetFlg8 = 1; targetFlg9 = -2;
            iTarget7.setImageResource(R.drawable.human);
            iTarget8.setImageResource(R.drawable.right_reverse);
            iTarget9.setImageResource(R.drawable.human);
        }else if(ptn == "CENTER_ARROWLEFT_REVERSE"){
            /*ターゲットの記述*/
            //×　|←　×
            targetFlg7 = -2; targetFlg8 = 1; targetFlg9 = 2;
            iTarget7.setImageResource(R.drawable.human);
            iTarget8.setImageResource(R.drawable.left_reverse);
            iTarget9.setImageResource(R.drawable.human);
        }
    }

    //BGM
    private boolean audioSetup(){
        boolean fileCheck = false;
        // インタンスを生成
        mediaPlayer = new MediaPlayer();
        //音楽ファイル名, あるいはパス
        String filePath = "hiyokonokakekko.mp3";

        try {
            // assetsから mp3 ファイルを読み込み
            AssetFileDescriptor afdescripter = getAssets().openFd(filePath);
            // MediaPlayerに読み込んだ音楽ファイルを指定
            mediaPlayer.setDataSource(afdescripter.getFileDescriptor(),
                    afdescripter.getStartOffset(),
                    afdescripter.getLength());
            // 音量調整を端末のボタンに任せる
            setVolumeControlStream(AudioManager.STREAM_MUSIC);
            mediaPlayer.prepare();
            fileCheck = true;
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return fileCheck;
    }
    private void audioPlay() {

        if (mediaPlayer == null) {
            // audio ファイルを読出し
            if (audioSetup()){
                //Toast.makeText(getApplication(), "Rread audio file", Toast.LENGTH_SHORT).show();
            }
            else{
                //Toast.makeText(getApplication(), "Error: read audio file", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        else{
            // 繰り返し再生する場合
            mediaPlayer.stop();
            mediaPlayer.reset();
            // リソースの解放
            mediaPlayer.release();
        }

        // 再生する
        mediaPlayer.start();
        // 終了を検知するリスナー
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                //Log.d("debug","end of audio");
            }
        });
    }
    private void audioStop() {
        // 再生終了
        mediaPlayer.stop();
        // リセット
        mediaPlayer.reset();
        // リソースの解放
        mediaPlayer.release();
        mediaPlayer = null;
    }
}