package com.example.kenkou.partygame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TopActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);
        findViewById(R.id.buttonSingle).setOnClickListener(SingleClickListener);
        findViewById(R.id.buttonMulti).setOnClickListener(MultiClickListener);
        findViewById(R.id.buttonDescription).setOnClickListener(DescriptionClickListener);

        // フォントを取得
        Typeface tf = Typeface.createFromAsset(getAssets(), "mouhitsu.ttf");
        // ButtonウィジェットIDを取得
        Button bSingle = (Button)findViewById(R.id.buttonSingle);
        Button bMulti = (Button)findViewById(R.id.buttonMulti);
        Button bDesc = (Button)findViewById(R.id.buttonDescription);
        // フォントを設定
        bSingle.setTypeface(tf);
        bMulti.setTypeface(tf);
        bDesc.setTypeface(tf);
        // フォントサイズを20に設定
        bSingle.setTextSize(40.0f);
        bMulti.setTextSize(40.0f);
        bDesc.setTextSize(40.0f);

    }

    //ひとりを押したときの処理
    View.OnClickListener SingleClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Intent intent = new Intent(getApplication(), GamePlaySingle.class);
            startActivity(intent);
        }
    };
    //ふたりを押したときの処理
    View.OnClickListener MultiClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Intent intent = new Intent(getApplication(), GamePlayMulti.class);
            startActivity(intent);
        }
    };
    //せつめいを押したときの処理
    View.OnClickListener DescriptionClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Intent intent = new Intent(getApplication(), Description.class);
            startActivity(intent);
        }
    };
}
