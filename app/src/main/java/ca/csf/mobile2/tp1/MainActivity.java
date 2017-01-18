package ca.csf.mobile2.tp1;

import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alexandre.tp1.R;

public class MainActivity extends AppCompatActivity {

    private TextView[] colorTexts;
    private ImageView circle;

    private MediaPlayer goodSound;
    private MediaPlayer wrongSound;

    private int[] currentTextIDs;
    private int[] currentColorIDs;
    private int currentCircleColor;

    private int validAnswer;

    private int[] textIDs;
    private int[] colorIDs;

    private final String VALID_ANSWER_KEY = "SAVE_INSTANCE_VALID_ANSWER";
    private final String TEXTS_KEY = "SAVE_INSTANCE_TEXTS";
    private final String COLORS_KEY = "SAVE_INSTANCE_COLORS";
    private final String CIRCLE_COLOR_KEY = "SAVE_INSTANCE_CIRCLE_COLOR";

    private FindColorGame findColorGame;

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

        goodSound = MediaPlayer.create(this, R.raw.good_ping);
        wrongSound = MediaPlayer.create(this, R.raw.wrong_ping);

        findColorGame = new FindColorGame();

        if(savedInstanceState == null){
            newGame();
        }
    }

    private void initialiseView(){
        circle = (ImageView) findViewById(R.id.imageView);

        colorTexts = new TextView[4];

        colorTexts[0] = (TextView)findViewById(R.id.textColor1);
        colorTexts[1] = (TextView)findViewById(R.id.textColor2);
        colorTexts[2] = (TextView)findViewById(R.id.textColor3);
        colorTexts[3] = (TextView)findViewById(R.id.textColor4);

        textIDs = new int[] {R.string.redText, R.string.pinkText, R.string.violetText, R.string.blueText, R.string.greenText, R.string.brownText, R.string.orangeText};
        colorIDs = new int[] {R.color.red, R.color.pink, R.color.violet, R.color.blue, R.color.green, R.color.brown, R.color.orange};
    }

    @SuppressWarnings("deprecation")
    private void newGame(){
        validAnswer = findColorGame.getValidAnswerForGame();
        currentTextIDs = findColorGame.getTextIDsForGame(textIDs);
        currentColorIDs = findColorGame.getColorIDsForGame(colorIDs);
        currentCircleColor = findColorGame.getCircleColorForGame(currentTextIDs, textIDs, colorIDs, validAnswer);
        for(int i = 0; i < currentColorIDs.length; i++){
            colorTexts[i].setText(getResources().getText(currentTextIDs[i]));
            colorTexts[i].setTextColor(getResources().getColor(currentColorIDs[i]));
        }
        circle.setColorFilter(getResources().getColor(currentCircleColor));
    }

    @SuppressWarnings("deprecation")
    private void useSameGame(int validOption, int[] texts, int[] colors, int circleColor){
        validAnswer = validOption;
        currentTextIDs = texts;
        currentColorIDs = colors;
        currentCircleColor = circleColor;
        for(int i = 0; i < currentColorIDs.length; i++){
            colorTexts[i].setText(getResources().getText(currentTextIDs[i]));
            colorTexts[i].setTextColor(getResources().getColor(currentColorIDs[i]));
        }
        circle.setColorFilter(getResources().getColor(currentCircleColor));
    }

    private void playSound(boolean goodAnswer) {
        if (goodAnswer) {
            if(goodSound.isPlaying()){
                goodSound.stop();
            }
            goodSound.start();
        } else {
            if(wrongSound.isPlaying()){
                wrongSound.stop();
            }
            wrongSound.start();
        }
    }

    public void onSelectedColor(View view){
        playSound(colorTexts[validAnswer].getId() == view.getId());
        newGame();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(VALID_ANSWER_KEY, validAnswer);
        outState.putIntArray(TEXTS_KEY, currentTextIDs);
        outState.putIntArray(COLORS_KEY, currentColorIDs);
        outState.putInt(CIRCLE_COLOR_KEY, currentCircleColor);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        useSameGame(savedInstanceState.getInt(VALID_ANSWER_KEY),
                savedInstanceState.getIntArray(TEXTS_KEY),
                savedInstanceState.getIntArray(COLORS_KEY),
                savedInstanceState.getInt(CIRCLE_COLOR_KEY));

    }
}
