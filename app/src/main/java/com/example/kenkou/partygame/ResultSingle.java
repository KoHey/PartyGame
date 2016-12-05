package com.example.kenkou.partygame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultSingle extends Activity {
    Globals globals;
    private TextView tScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_single);

        tScore = (TextView)findViewById(R.id.textYourScore);
        tScore.setText(globals.gScoreP1);
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
