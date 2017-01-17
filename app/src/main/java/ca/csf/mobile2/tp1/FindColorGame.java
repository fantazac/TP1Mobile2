package ca.csf.mobile2.tp1;

import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Build;

import com.example.alexandre.tp1.R;

import java.util.Random;

/**
 * Created by Alexandre on 2017-01-16.
 */

public class FindColorGame {

    private FindColorController findColorController;

    private int validAnswer;

    private String[] texts;
    private int[] colors;

    private MediaPlayer goodSound;
    private MediaPlayer wrongSound;

    private Resources resources;

    public FindColorGame(FindColorController findColorController) {
        this.findColorController = findColorController;
        resources = findColorController.getActivity().getResources();

        goodSound = MediaPlayer.create(findColorController.getActivity(), R.raw.good_ping);
        wrongSound = MediaPlayer.create(findColorController.getActivity(), R.raw.wrong_ping);

        initializeAvailableTextsAndColors();

        newGame();
    }

    private void initializeAvailableTextsAndColors() {
        texts = new String[7];
        colors = new int[7];

        texts[0] = resources.getString(R.string.redText);
        texts[1] = resources.getString(R.string.pinkText);
        texts[2] = resources.getString(R.string.violetText);
        texts[3] = resources.getString(R.string.blueText);
        texts[4] = resources.getString(R.string.greenText);
        texts[5] = resources.getString(R.string.brownText);
        texts[6] = resources.getString(R.string.orangeText);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            colors[0] = resources.getColor(R.color.red, null);
            colors[1] = resources.getColor(R.color.pink, null);
            colors[2] = resources.getColor(R.color.violet, null);
            colors[3] = resources.getColor(R.color.blue, null);
            colors[4] = resources.getColor(R.color.green, null);
            colors[5] = resources.getColor(R.color.brown, null);
            colors[6] = resources.getColor(R.color.orange, null);
        } else {
            //noinspection deprecation
            colors[0] = resources.getColor(R.color.red);
            //noinspection deprecation
            colors[1] = resources.getColor(R.color.pink);
            //noinspection deprecation
            colors[2] = resources.getColor(R.color.violet);
            //noinspection deprecation
            colors[3] = resources.getColor(R.color.blue);
            //noinspection deprecation
            colors[4] = resources.getColor(R.color.green);
            //noinspection deprecation
            colors[5] = resources.getColor(R.color.brown);
            //noinspection deprecation
            colors[6] = resources.getColor(R.color.orange);
        }

    }

    public void getInputFromView(int colorId) {
        playSound(validAnswer == colorId);
        newGame();
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

    private void newGame() {

        String[] textsToSend = new String[4];
        int[] colorsToSend = new int[textsToSend.length + 1];
        boolean[] takenTexts = new boolean[texts.length];
        boolean[] takenColors = new boolean[colors.length];
        int colorOfCircle = 0;
        int[] textIDs = new int[textsToSend.length];

        for (int b = 0; b < takenTexts.length; b++) {
            takenTexts[b] = false;
        }

        Random random = new Random();
        int selectedNumber;

        validAnswer = random.nextInt(textsToSend.length);

        boolean goToNextInt;
        for (int i = 0; i < textsToSend.length; i++) {
            goToNextInt = false;
            while (!goToNextInt) {
                selectedNumber = random.nextInt(texts.length);
                if (!takenTexts[selectedNumber]) {
                    takenTexts[selectedNumber] = true;
                    goToNextInt = true;
                    textsToSend[i] = texts[selectedNumber];
                    textIDs[i] = selectedNumber;
                }
            }
        }

        for (int j = 0; j < colorsToSend.length - 1; j++) {
            goToNextInt = false;
            while (!goToNextInt) {
                goToNextInt = false;
                selectedNumber = random.nextInt(colors.length);
                if (!takenColors[selectedNumber] && textIDs[j] != selectedNumber) {
                    takenColors[selectedNumber] = true;
                    goToNextInt = true;
                    colorsToSend[j] = colors[selectedNumber];
                }
            }
        }

        for(int t = 0; t < texts.length; t++){
            if(texts[t].equals(textsToSend[validAnswer])){
                colorOfCircle = colors[t];
            }
        }

        findColorController.setNewGame(textsToSend, colorsToSend, colorOfCircle);
    }

}
