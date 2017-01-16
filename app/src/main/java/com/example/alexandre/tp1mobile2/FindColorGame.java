package com.example.alexandre.tp1mobile2;

/**
 * Created by Alexandre on 2017-01-16.
 */

public class FindColorGame {

    private FindColorController findColorController;

    private int validAnswer;

    public FindColorGame(FindColorController findColorController){
        this.findColorController = findColorController;
        newGame();
    }

    public void getInputFromView(int colorId){
        playSound(validAnswer == colorId);
        newGame();
    }

    private void playSound(boolean goodAnswer){
        if(goodAnswer){
            //good_sound
        }
        else{
            //wrong_sound
        }
    }

    private void newGame(){

        String[] texts = new String[4];
        String[] colors = new String[5];

        //choisir couleurs

        findColorController.setNewGame(texts, colors);
    }

}
