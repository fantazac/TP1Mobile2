package com.example.alexandre.tp1mobile2;

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

    public void setNewGame(String[] texts, String[] colors){
        mainActivity.setTextsAndColors(texts, colors);
    }

}
