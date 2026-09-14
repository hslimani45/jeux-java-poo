package modele;

public class Morpion extends Partie {

    private char[][] grille;

    public Morpion() {
        super();
        grille = new char[3][3];
        initialiserGrille();
    }

    private void initialiserGrille() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grille[i][j] = ' ';
            }
        }
    }

    @Override
    public boolean jouerCoup(String saisie, char symbole) {
        String[] morceaux = saisie.trim().split("\\s+");

        if (morceaux.length != 2) {
            return false;
        }

        int ligne;
        int colonne;

        try {
            ligne = Integer.parseInt(morceaux[0]) - 1;
            colonne = Integer.parseInt(morceaux[1]) - 1;
        } catch (NumberFormatException e) {
            return false;
        }

        if (!caseValide(ligne, colonne)) {
            return false;
        }

        grille[ligne][colonne] = symbole;

        if (aGagne(symbole)) {
            definirVictoire();
        } else if (grillePleine()) {
            definirNul();
        } else {
            changerJoueur();
        }

        return true;
    }

    public boolean caseValide(int ligne, int colonne) {
        if (ligne < 0 || ligne > 2 || colonne < 0 || colonne > 2) {
            return false;
        }
        return grille[ligne][colonne] == ' ';
    }

    public void poserSymbole(int ligne, int colonne, char symbole) {
        grille[ligne][colonne] = symbole;
    }

    public void viderCase(int ligne, int colonne) {
        grille[ligne][colonne] = ' ';
    }

    public boolean aGagne(char symbole) {
        for (int i = 0; i < 3; i++) {
            if (grille[i][0] == symbole && grille[i][1] == symbole && grille[i][2] == symbole) {
                return true;
            }
        }

        for (int j = 0; j < 3; j++) {
            if (grille[0][j] == symbole && grille[1][j] == symbole && grille[2][j] == symbole) {
                return true;
            }
        }

        if (grille[0][0] == symbole && grille[1][1] == symbole && grille[2][2] == symbole) {
            return true;
        }

        return grille[0][2] == symbole && grille[1][1] == symbole && grille[2][0] == symbole;
    }

    public boolean grillePleine() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grille[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public int nbCasesVides() {
        int nb = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grille[i][j] == ' ') {
                    nb++;
                }
            }
        }
        return nb;
    }

    public char[][] getGrille() {
        return grille;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("  1 2 3\n");
        for (int i = 0; i < 3; i++) {
            sb.append(i + 1).append(" ");
            for (int j = 0; j < 3; j++) {
                sb.append(grille[i][j]);
                if (j < 2) {
                    sb.append("|");
                }
            }
            sb.append("\n");
            if (i < 2) {
                sb.append("  -+-+-\n");
            }
        }

        return sb.toString();
    }
}
