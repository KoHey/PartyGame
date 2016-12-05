package com.example.kenkou.partygame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Description extends Activity {
    private ImageView iDesc;
    private int descPage = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        iDesc = (ImageView) findViewById(R.id.imageDesc);
        iDesc.setImageResource(R.drawable.desc1);

        findViewById(R.id.buttonLeft).setOnClickListener(LeftClickListener);
        findViewById(R.id.buttonCenter).setOnClickListener(CenterClickListener);
        findViewById(R.id.buttonRight).setOnClickListener(RightClickListener);

        Desc();
    }
    View.OnClickListener LeftClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            descPage--;
            Desc();
        }
    };
    View.OnClickListener CenterClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Intent intent = new Intent(getApplication(), TopActivity.class);
            startActivity(intent);
        }
    };
    View.OnClickListener RightClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            descPage++;
            Desc();
        }
    };
    private void Desc(){
        switch (descPage){
            case 1:
                iDesc.setImageResource(R.drawable.desc1);
                findViewById(R.id.buttonLeft).setVisibility(View.INVISIBLE);
                break;
            case 2:
                iDesc.setImageResource(R.drawable.desc2);
                findViewById(R.id.buttonLeft).setVisibility(View.VISIBLE);
                break;
            case 3:
                iDesc.setImageResource(R.drawable.desc3);
                break;
            case 4:
                iDesc.setImageResource(R.drawable.desc4);
                break;
            case 5:
                iDesc.setImageResource(R.drawable.desc5);
                findViewById(R.id.buttonRight).setVisibility(View.VISIBLE);
                break;
            case 6:
                iDesc.setImageResource(R.drawable.desc6);
                findViewById(R.id.buttonRight).setVisibility(View.INVISIBLE);
                break;
        }
    }
}
