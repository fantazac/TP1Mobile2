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

    //BEN_CORRECTION : Tu m'a habitué à mieux....Pourquoi c'est pas private ? C'est pas un DTO que tu a là, mais une classe de la couche Modèle avec
    //                 de la logique applicative dedans...
    Random random;
    public int[] textIdsChosen;

    public FindColorGame(){
        random = new Random();
    }

    public int getValidAnswerForGame(){
        return random.nextInt(4);
    }

    //BEN_CORRECTION : Cette classe est conçue d'une drôle de façon. En fait, elle fourni des données aléatoires, mais ne conserve rien (sauf textIdsChosen, mais
    //                 je vais y venir plus tard). Ton "MainActivity" se rammasse donc avec des reponsabilités qui, normalement, devrait appartenir au moddèle.
    //                 Par exemple, elle doit se souvenir de l'index de la bonne réponse.
    //
    //                 Autre problème : il est possible de créer un bogue facilement en inversant l'ordre d'appel à tes méthodes "getTextIDsForGame" et "getColorIDsForGame".
    //                 Si tu appelle "getColorIDsForGame" avant "getTextIDsForGame", tu va avoir un "NullPointerException". Vérifie, tu verra.

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
