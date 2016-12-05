package com.example.kenkou.partygame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultMulti extends Activity {
    Globals globals;
    private TextView tScoreP1;
    private TextView tScoreP2;
    private TextView tJudgment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        globals = (Globals)this.getApplication();

        tScoreP1 = (TextView)findViewById(R.id.textResultScoreP1);
        tScoreP1.setText(globals.gScoreP1);
        tScoreP2 = (TextView)findViewById(R.id.textResultScoreP2);
        tScoreP2.setText(globals.gScoreP2);
        tJudgment = (TextView)findViewById(R.id.textJudgment);
        if(globals.iScoreP1 > globals.iScoreP2){
            tJudgment.setText("プレイヤー1の勝利");
        }else if(globals.iScoreP1 < globals.iScoreP2){
            tJudgment.setText("プレイヤー2の勝利");
        }else {
            tJudgment.setText("引き分け");
        }
        findViewById(R.id.buttonBack).setOnClickListener(BackClickListener);
    }

    //トップボタンを押したときの処理
    View.OnClickListener BackClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Intent intent = new Intent(getApplication(), TopActivity.class);
            startActivity(intent);
        }
    };
}
