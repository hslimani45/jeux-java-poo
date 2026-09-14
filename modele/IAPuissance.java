package modele;

import java.util.ArrayList;
import java.util.Random;

public class IAPuissance extends IA {

    private Random random;

    public IAPuissance() {
        random = new Random();
    }

    @Override
    public String choisirCoup(Partie partie, char symboleIA, char symboleAdverse) {
        Puissance4 puissance4 = (Puissance4) partie;
        ArrayList<Integer> coupsPossibles = new ArrayList<Integer>();

        for (int colonne = 0; colonne < 7; colonne++) {
            if (puissance4.trouverLigneDisponible(colonne) != -1) {
                coupsPossibles.add(colonne + 1);
            }
        }

        int choix = random.nextInt(coupsPossibles.size());
        return String.valueOf(coupsPossibles.get(choix));
    }
}
