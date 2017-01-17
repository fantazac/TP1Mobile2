package com.example.alexandre.tp1;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView[] colorTexts;
    private ImageView circle;

    private FindColorController findColorController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            setContentView(R.layout.activity_main_landscape);
        }
        else{
            setContentView(R.layout.activity_main);
        }

        initialiseView();

        findColorController = new FindColorController(this);
    }

    private void initialiseView(){
        circle = (ImageView) findViewById(R.id.imageView);

        colorTexts = new TextView[4];

        colorTexts[0] = (TextView)findViewById(R.id.textColor1);
        colorTexts[1] = (TextView)findViewById(R.id.textColor2);
        colorTexts[2] = (TextView)findViewById(R.id.textColor3);
        colorTexts[3] = (TextView)findViewById(R.id.textColor4);
    }

    public void onSelectedColor(View view){
        for(int i = 0; i < colorTexts.length; i++){
            if(view.getId() == colorTexts[i].getId()){
                findColorController.getInputFromView(i);
                break;
            }
        }
    }

    public void setTextsAndColors(String[] texts, int[] colors, int validColor){
        for(int i = 0; i < texts.length; i++){
            colorTexts[i].setText(texts[i]);
            colorTexts[i].setTextColor(colors[i]);
        }
        circle.setColorFilter(validColor);
    }
}
