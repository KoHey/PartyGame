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

public class GamePlayMulti extends Activity{
    Globals globals;
    private ImageView imagePlayer1_1,imagePlayer1_2,imagePlayer1_3;
    private ImageView imagePlayer2_1,imagePlayer2_2,imagePlayer2_3;
    private ImageView iTarget1,iTarget2,iTarget3;
    private TextView tTimerP1, tTimerP2;//タイマー
    private TextView tScoreP1, tScoreP2;
    private int scoreP1 = 0, scoreP2 = 0;
    private int targetFlg1 = 0,targetFlg2 = 0,targetFlg3 = 0;//これの中身が点数
    private MediaPlayer mediaPlayer;

    // インスタンス生成 CountDownTimer(long millisInFuture, long countDownInterval)
    // 3分= 3x60x1000 = 180000 msec
    public CountDown countDown = new CountDown(30000, 1000);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);
        globals = (Globals)this.getApplication();
        /*プレイヤー1の記述*/
        imagePlayer1_1 = (ImageView) findViewById(R.id.imageP11);
        imagePlayer1_1.setImageResource(R.drawable.player1);
        imagePlayer1_2 = (ImageView) findViewById(R.id.imageP12);
        imagePlayer1_2.setImageResource(R.drawable.player1);
        imagePlayer1_3 = (ImageView) findViewById(R.id.imageP13);
        imagePlayer1_3.setImageResource(R.drawable.player1);
        tTimerP1 = (TextView)findViewById(R.id.textTimerP1);
        tTimerP1.setText("00:00");
        tScoreP1 = (TextView)findViewById(R.id.textScoreP1);
        tScoreP1.setText( String.valueOf(scoreP1));

        /*プレイヤー2の記述*/
        imagePlayer2_1 = (ImageView) findViewById(R.id.imageP21);
        imagePlayer2_1.setImageResource(R.drawable.player2);
        imagePlayer2_1.setRotation(180);
        imagePlayer2_2 = (ImageView) findViewById(R.id.imageP22);
        imagePlayer2_2.setImageResource(R.drawable.player2);
        imagePlayer2_2.setRotation(180);
        imagePlayer2_3 = (ImageView) findViewById(R.id.imageP23);
        imagePlayer2_3.setImageResource(R.drawable.player2);
        imagePlayer2_3.setRotation(180);
        tTimerP2 = (TextView)findViewById(R.id.textTimerP2);
        tTimerP2.setText("00:00");
        tTimerP2.setRotation(180);
        tScoreP2 = (TextView)findViewById(R.id.textScoreP2);
        tScoreP2.setRotation(180);
        tScoreP2.setText( String.valueOf(scoreP2));

        /*ターゲットの記述*/
        iTarget1 = (ImageView) findViewById(R.id.imageTarget1);
        iTarget1.setImageResource(R.drawable.human);
        iTarget2 = (ImageView) findViewById(R.id.imageTarget2);
        iTarget2.setImageResource(R.drawable.human);
        iTarget3 = (ImageView) findViewById(R.id.imageTarget3);
        iTarget3.setImageResource(R.drawable.human);



        // 画像にリスナーをセット
        imagePlayer1_1.setOnClickListener(Player1_1ClickListener);
        imagePlayer1_2.setOnClickListener(Player1_2ClickListener);
        imagePlayer1_3.setOnClickListener(Player1_3ClickListener);

        imagePlayer2_1.setOnClickListener(Player2_1ClickListener);
        imagePlayer2_2.setOnClickListener(Player2_2ClickListener);
        imagePlayer2_3.setOnClickListener(Player2_3ClickListener);

        StartDialog();
    }


    //クリックされた時の加算処理
    private void ClickProcess(int playerNum,int num){
        if(playerNum == 1) {
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
        }else if(playerNum == 2){
            if (num == 1) {
                iTarget1.setImageResource(R.drawable.player2tap);
                scoreP2 += targetFlg1;
                targetFlg1 = 20;
            } else if (num == 2) {
                iTarget2.setImageResource(R.drawable.player2tap);
                scoreP2 += targetFlg2;
                targetFlg2 = 20;
            } else if (num == 3) {
                iTarget3.setImageResource(R.drawable.player2tap);
                scoreP2 += targetFlg3;
                targetFlg3 = 20;
            }
            tScoreP2.setText( String.valueOf(scoreP2));
        }
    }
    /*プレイヤー1*/
    View.OnClickListener Player1_1ClickListener = new View.OnClickListener(){
        int playerNum = 1;
        int targetNum = 1;
        @Override
        public void onClick(View v) {
            if(targetFlg1 == 10){
                //P1がクリックした後targeFlgが10になる
                //きつくするならここにscoreをマイナスする記述をする
            }else if(targetFlg1 == 20){
                //P2がクリックした後targetFlgが20になる
                //きつくするならここにscoreをマイナスする記述をする
            }else{
                ClickProcess(playerNum,targetNum);
            }
        }
    };
    View.OnClickListener Player1_2ClickListener = new View.OnClickListener() {
        int playerNum = 1;
        int targetNum = 2;
        @Override
        public void onClick(View v) {
            if(targetFlg2 == 10){
                //P1がクリックした後targeFlgが10になる
                //きつくするならここにscoreをマイナスする記述をする
            }else if(targetFlg2 == 20){
                //P2がクリックした後targetFlgが20になる
                //きつくするならここにscoreをマイナスする記述をする
            }else{
                ClickProcess(playerNum,targetNum);
            }
        }
    };
    View.OnClickListener Player1_3ClickListener = new View.OnClickListener() {
        int playerNum = 1;
        int targetNum = 3;
        @Override
        public void onClick(View v) {
            if(targetFlg3 == 10){
                //P1がクリックした後targeFlgが10になる
                //きつくするならここにscoreをマイナスする記述をする
            }else if(targetFlg3 == 20){
                //P2がクリックした後targetFlgが20になる
                //きつくするならここにscoreをマイナスする記述をする
            }else{
                ClickProcess(playerNum,targetNum);
            }
        }
    };
    /*プレイヤー2*/
    View.OnClickListener Player2_1ClickListener = new View.OnClickListener() {
        int playerNum = 2;
        int targetNum = 1;
        @Override
        public void onClick(View v) {
            if(targetFlg1 == 10){
                //P1がクリックした後targeFlgが10になる
                //きつくするならここにscoreをマイナスする記述をする
            }else if(targetFlg1 == 20){
                //P2がクリックした後targetFlgが20になる
                //きつくするならここにscoreをマイナスする記述をする
            }else{
                ClickProcess(playerNum,targetNum);
            }
        }
    };
    View.OnClickListener Player2_2ClickListener = new View.OnClickListener() {
        int playerNum = 2;
        int targetNum = 2;
        @Override
        public void onClick(View v) {
            if(targetFlg2 == 10){
                //P1がクリックした後targeFlgが10になる
                //きつくするならここにscoreをマイナスする記述をする
            }else if(targetFlg2 == 20){
                //P2がクリックした後targetFlgが20になる
                //きつくするならここにscoreをマイナスする記述をする
            }else{
                ClickProcess(playerNum,targetNum);
            }
        }
    };
    View.OnClickListener Player2_3ClickListener = new View.OnClickListener() {
        int playerNum = 2;
        int targetNum = 3;
        @Override
        public void onClick(View v) {
            if(targetFlg3 == 10){
                //P1がクリックした後targeFlgが10になる
                //きつくするならここにscoreをマイナスする記述をする
            }else if(targetFlg3 == 20){
                //P2がクリックした後targetFlgが20になる
                //きつくするならここにscoreをマイナスする記述をする
            }else{
                ClickProcess(playerNum,targetNum);
            }
        }
    };

    //ゲームをスタートするダイアログを出す
    private void StartDialog(){
        // ポップアップメニュー表示
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(GamePlayMulti.this);
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
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(GamePlayMulti.this);
        alertDialog.setTitle("おわり");
        alertDialog.setMessage("酔いは冷めたか？");
        alertDialog.setPositiveButton("結果を見る", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                globals.iScoreP1 = scoreP1;
                globals.iScoreP2 = scoreP2;
                globals.gScoreP1 = String.valueOf(scoreP1);
                globals.gScoreP2 = String.valueOf(scoreP2);
                Intent intent = new Intent(getApplication(), ResultMulti.class);
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
            // 音楽停止
            audioStop();
            // 完了
            tTimerP1.setText("00:00");
            tTimerP2.setText("00:00");
            GoToResultDialog();
        }
        // インターバルで呼ばれる
        @Override
        public void onTick(long millisUntilFinished) {
            setTarget();
            // 残り時間を分、秒、ミリ秒に分割
            long mm = millisUntilFinished / 1000 / 60;
            long ss = millisUntilFinished / 1000 % 60;
            tTimerP1.setText(String.format("%1$02d:%2$02d", mm, ss));
            tTimerP2.setText(String.format("%1$02d:%2$02d", mm, ss));
        }
    }

    //ターゲット出現の確率をいじるところ
    private void setTarget(){
        //乱数生成
        Random r = new Random();
        int n = r.nextInt(10);
        switch (n){
            case 0:
                targetPtn("NONE");
                break;
            case 1:
                targetPtn("LEFT");
                break;
            case 2:
                targetPtn("CENTER");
                break;
            case 3:
                targetPtn("RIGHT");
                break;
            case 4:
                targetPtn("LEFT_ARROW");
                break;
            case 5:
                targetPtn("RIGHT_ARROW");
                break;
            case 6:
                targetPtn("CENTER_ARROWRIGHT_NORMAL");
                break;
            case 7:
                targetPtn("CENTER_ARROWLEFT_NORMAL");
                break;
            case 8:
                targetPtn("CENTER_ARROWRIGHT_REVERSE");
                break;
            case 9:
                targetPtn("CENTER_ARROWLEFT_REVERSE");
                break;
        }
    }

    //ターゲットのパターン一覧
    private void targetPtn(String ptn){
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


