package modele;

public class Nim extends Partie {

    private int[] lesTas;

    public Nim() {
        super();
        lesTas = new int[3];
        initialiser();
    }

    public void initialiser() {
        for (int i = 0; i < lesTas.length; i++) {
            lesTas[i] = 2 * i + 1;
        }
    }

    public boolean partieTerminee() {
        return nbAllumette() == 0;
    }

    public int nbAllumettes(int numeroTas) {
        return lesTas[numeroTas - 1];
    }

    public int nbAllumette() {
        int total = 0;
        for (int nb : lesTas) {
            total += nb;
        }
        return total;
    }

    public int getNbTas() {
        return lesTas.length;
    }

    public void gererCoup(int numeroTas, int nbAllumettes) throws CoupInvalideException {
        if (numeroTas >= 1 && numeroTas <= lesTas.length && nbAllumettes >= 1 && nbAllumettes <= nbAllumettes(numeroTas)) {
            lesTas[numeroTas - 1] -= nbAllumettes;
        } else {
            throw new CoupInvalideException("Le coup est invalide, rejouez");
        }
    }

    @Override
    public boolean jouerCoup(String saisie, char symbole) {
        String[] morceaux = saisie.trim().split("\\s+");

        if (morceaux.length != 2) {
            return false;
        }

        int numeroTas;
        int nbRetirees;

        try {
            numeroTas = Integer.parseInt(morceaux[0]);
            nbRetirees = Integer.parseInt(morceaux[1]);
        } catch (NumberFormatException e) {
            return false;
        }

        try {
            gererCoup(numeroTas, nbRetirees);
        } catch (CoupInvalideException e) {
            return false;
        }

        if (partieTerminee()) {
            definirVictoire();
        } else {
            changerJoueur();
        }

        return true;
    }

    @Override
    public String toString() {
        String s="";
        for (int nbAllumettes : lesTas) {
            for (int i = 1; i <= nbAllumettes; i++) {
                s+="| ";
            }
            s+="\n";
        }
        return s;
    }

}
