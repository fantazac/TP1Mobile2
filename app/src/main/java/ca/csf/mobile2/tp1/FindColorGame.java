package ca.csf.mobile2.tp1;

import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v4.content.ContextCompat;

import com.example.alexandre.tp1.R;

import java.util.Random;

/**
 * Created by Alexandre on 2017-01-16.
 */

public class FindColorGame {

    Random random;
    public int[] textIdsChosen;

    public FindColorGame(){
        random = new Random();
    }

    public int getValidAnswerForGame(){
        return random.nextInt(4);
    }

    public int[] getTextIDsForGame(int[] textIDs){
        int[] textIDsToSend = new int[4];
        boolean[] takenTexts = new boolean[7];

        textIdsChosen = new int[4];
        int selectedNumber;
        boolean goToNextInt;
        for (int i = 0; i < textIDsToSend.length; i++) {
            goToNextInt = false;
            while (!goToNextInt) {
                selectedNumber = random.nextInt(textIDs.length);
                if (!takenTexts[selectedNumber]) {
                    takenTexts[selectedNumber] = true;
                    goToNextInt = true;
                    textIDsToSend[i] = textIDs[selectedNumber];
                    textIdsChosen[i] = selectedNumber;
                }
            }
        }
        return textIDsToSend;
    }

    public int[] getColorIDsForGame(int[] colorIDs){
        int[] colorsToSend = new int[4];
        boolean[] takenColors = new boolean[7];

        int selectedNumber;
        boolean goToNextInt;
        for (int j = 0; j < colorsToSend.length; j++) {
            goToNextInt = false;
            while (!goToNextInt) {
                goToNextInt = false;
                selectedNumber = random.nextInt(colorIDs.length);
                if (!takenColors[selectedNumber] && textIdsChosen[j] != selectedNumber) {
                    takenColors[selectedNumber] = true;
                    goToNextInt = true;
                    colorsToSend[j] = colorIDs[selectedNumber];
                }
            }
        }
        return colorsToSend;
    }

    public int getCircleColorForGame(int[] currentTextIDs, int[] allTextIDs, int[] allColorIDs, int validAnswer){
        for(int t = 0; t < allTextIDs.length; t++){
            if(allTextIDs[t] == currentTextIDs[validAnswer]){
                return allColorIDs[t];
            }
        }
        return 0;
    }
}
