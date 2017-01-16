package com.example.alexandre.tp1mobile2;

import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView questionText;
    private TextView[] colorTexts;
    private TextView color1Text;
    private TextView color2Text;
    private TextView color3Text;
    private TextView color4Text;
    private Drawable circle;

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

        InitialiseView();

        findColorController = new FindColorController(this);
    }

    private void InitialiseView(){
        questionText = (TextView)findViewById(R.id.textViewQuestion);
        color1Text = (TextView)findViewById(R.id.textColor1);
        color2Text = (TextView)findViewById(R.id.textColor2);
        color3Text = (TextView)findViewById(R.id.textColor3);
        color4Text = (TextView)findViewById(R.id.textColor4);
        circle = getDrawable(R.drawable.circle);

        colorTexts = new TextView[4];

        colorTexts[0] = color1Text;
        colorTexts[1] = color2Text;
        colorTexts[2] = color3Text;
        colorTexts[3] = color4Text;
    }

    public void onSelectedColor(View view){
        for(int i = 0; i < colorTexts.length; i++){
            if(view.getId() == colorTexts[i].getId()){
                findColorController.getInputFromView(i);
                break;
            }
        }
    }

    public void setTextsAndColors(String[] texts, String[] colors){
        for(int i = 0; i < texts.length; i++){
            colorTexts[i].setText(texts[i]);
            colorTexts[i].setTextColor(Color.parseColor(colors[i]));
        }
        circle.setColorFilter(Color.parseColor(colors[colors.length-1]), PorterDuff.Mode.SRC_ATOP);
    }
}
