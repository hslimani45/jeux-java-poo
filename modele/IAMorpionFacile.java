package modele;

import java.util.ArrayList;
import java.util.Random;

public class IAMorpionFacile extends IA {

    private Random random;

    public IAMorpionFacile() {
        random = new Random();
    }

    @Override
    public String choisirCoup(Partie partie, char symboleIA, char symboleAdverse) {
        Morpion morpion = (Morpion) partie;
        ArrayList<String> coups = new ArrayList<String>();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (morpion.caseValide(i, j)) {
                    coups.add((i + 1) + " " + (j + 1));
                }
            }
        }

        int choix = random.nextInt(coups.size());
        return coups.get(choix);
    }
}
