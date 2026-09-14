package modele;

import java.util.ArrayList;
import java.util.Random;

//Pas demandé mais laissé comme vous nous avez dit en cours (similaire donc à notre classe IAMorpionFacile)

public class IANimFacile extends IA {

    private Random random;

    public IANimFacile() {
        random = new Random();
    }

    @Override
    public String choisirCoup(Partie partie, char symboleIA, char symboleAdverse) {
        Nim nim = (Nim) partie;
        ArrayList<String> coups = new ArrayList<String>();

        for (int tas = 1; tas <= nim.getNbTas(); tas++) {
            int nb = nim.nbAllumettes(tas);
            for (int retire = 1; retire <= nb; retire++) {
                coups.add(tas + " " + retire);
            }
        }

        int choix = random.nextInt(coups.size());
        return coups.get(choix);
    }
}
