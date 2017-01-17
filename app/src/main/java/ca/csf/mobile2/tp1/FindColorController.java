package ca.csf.mobile2.tp1;

import android.app.Activity;

/**
 * Created by Alexandre on 2017-01-16.
 */

public class FindColorController {

    private MainActivity mainActivity;
    private FindColorGame findColorGame;

    public FindColorController(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        findColorGame = new FindColorGame(this);
    }

    public void getInputFromView(int colorId){
        findColorGame.getInputFromView(colorId);
    }

    public void setNewGame(String[] texts, int[] colors, int validColor){
        mainActivity.setTextsAndColors(texts, colors, validColor);
    }

    public Activity getActivity(){
        return mainActivity;
    }

}
