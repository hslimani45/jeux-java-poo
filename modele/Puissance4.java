package modele;

public class Puissance4 extends Partie {

    private char[][] grille;

    public Puissance4() {
        super();
        grille = new char[6][7];
        initialiserGrille();
    }

    private void initialiserGrille() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                grille[i][j] = ' ';
            }
        }
    }

    @Override
    public boolean jouerCoup(String saisie, char symbole) {
        int colonne;

        try {
            colonne = Integer.parseInt(saisie.trim()) - 1;
        } catch (NumberFormatException e) {
            return false;
        }

        if (colonne < 0 || colonne > 6) {
            return false;
        }

        int ligne = trouverLigneDisponible(colonne);

        if (ligne == -1) {
            return false;
        }

        grille[ligne][colonne] = symbole;

        if (aGagne(ligne, colonne, symbole)) {
            definirVictoire();
        } else if (grillePleine()) {
            definirNul();
        } else {
            changerJoueur();
        }

        return true;
    }

    public int trouverLigneDisponible(int colonne) {
        for (int i = 5; i >= 0; i--) {
            if (grille[i][colonne] == ' ') {
                return i;
            }
        }
        return -1;
    }

    public void poserJeton(int ligne, int colonne, char symbole) {
        grille[ligne][colonne] = symbole;
    }

    public void viderCase(int ligne, int colonne) {
        grille[ligne][colonne] = ' ';
    }

    public boolean grillePleine() {
        for (int j = 0; j < 7; j++) {
            if (grille[0][j] == ' ') {
                return false;
            }
        }
        return true;
    }

    public boolean aGagne(int ligne, int colonne, char symbole) {
        return compterDirection(ligne, colonne, 0, 1, symbole) >= 4
                || compterDirection(ligne, colonne, 1, 0, symbole) >= 4
                || compterDirection(ligne, colonne, 1, 1, symbole) >= 4
                || compterDirection(ligne, colonne, 1, -1, symbole) >= 4;
    }

    private int compterDirection(int ligne, int colonne, int deltaLigne, int deltaColonne, char symbole) {
        int total = 1;
        total += compterCote(ligne, colonne, deltaLigne, deltaColonne, symbole);
        total += compterCote(ligne, colonne, -deltaLigne, -deltaColonne, symbole);
        return total;
    }

    private int compterCote(int ligne, int colonne, int deltaLigne, int deltaColonne, char symbole) {
        int compteur = 0;

        int i = ligne + deltaLigne;
        int j = colonne + deltaColonne;

        while (i >= 0 && i < 6 && j >= 0 && j < 7 && grille[i][j] == symbole) {
            compteur++;
            i += deltaLigne;
            j += deltaColonne;
        }

        return compteur;
    }

    public char[][] getGrille() {
        return grille;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("  1 2 3 4 5 6 7\n");
        for (int i = 0; i < 6; i++) {
            sb.append("|");
            for (int j = 0; j < 7; j++) {
                sb.append(grille[i][j]).append("|");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
