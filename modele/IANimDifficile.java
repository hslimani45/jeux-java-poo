package modele;

import java.util.ArrayList;
import java.util.Random;

public class IANimDifficile extends IA {

    private Random random;

    public IANimDifficile() {
        random = new Random();
    }

    @Override
    public String choisirCoup(Partie partie, char symboleIA, char symboleAdverse) {
        Nim nim = (Nim) partie;
        int xor = 0;

        for (int tas = 1; tas <= nim.getNbTas(); tas++) {
            xor = xor ^ nim.nbAllumettes(tas);
        }

        if (xor == 0) {
            ArrayList<String> coups = new ArrayList<String>();

            for (int tas = 1; tas <= nim.getNbTas(); tas++) {
                int nb = nim.nbAllumettes(tas);
                for (int retire = 1; retire <= nb; retire++) {
                    coups.add(tas + " " + retire);
                }
            }

            return coups.get(random.nextInt(coups.size()));
        }

        for (int tas = 1; tas <= nim.getNbTas(); tas++) {
            int nbActuel = nim.nbAllumettes(tas);
            int nbApres = nbActuel ^ xor;

            if (nbApres < nbActuel) {
                return tas + " " + (nbActuel - nbApres);
            }
        }

        return "1 1";
    }
}