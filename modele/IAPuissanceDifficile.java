package modele;

import java.util.ArrayList;
import java.util.Random;

public class IAPuissanceDifficile extends IA {

    private Random random;

    public IAPuissanceDifficile() {
        random = new Random();
    }

    @Override
    public String choisirCoup(Partie partie, char symboleIA, char symboleAdverse) {
        Puissance4 jeu = (Puissance4) partie;
        ArrayList<Integer> coups = new ArrayList<Integer>();
        int meilleur = -1;

        for (int colonne = 0; colonne < 7; colonne++) {
            int ligne = jeu.trouverLigneDisponible(colonne);

            if (ligne == -1) {
                continue;
            }

            int score = scoreCoup(jeu, ligne, colonne, symboleIA, symboleAdverse);

            if (score > meilleur) {
                meilleur = score;
                coups.clear();
                coups.add(colonne + 1);
            } else if (score == meilleur) {
                coups.add(colonne + 1);
            }
        }

        int choix = random.nextInt(coups.size());
        return String.valueOf(coups.get(choix));
    }

    private int scoreCoup(Puissance4 jeu, int ligne, int colonne, char moi, char autre) {
        int score = 0;

        if (coupGagnant(jeu, colonne, autre)) {
            score += 900;
        }

        jeu.poserJeton(ligne, colonne, moi);

        if (jeu.aGagne(ligne, colonne, moi)) {
            score += 1000;
        }

        score += 100 * scoreTrois(jeu, ligne, colonne, moi);
        score += 10 * scoreDeux(jeu, ligne, colonne, moi);
        score += bonusColonne(colonne);

        jeu.viderCase(ligne, colonne);
        return score;
    }

    private boolean coupGagnant(Puissance4 jeu, int colonne, char symbole) {
        int ligne = jeu.trouverLigneDisponible(colonne);

        if (ligne == -1) {
            return false;
        }

        jeu.poserJeton(ligne, colonne, symbole);
        boolean gagne = jeu.aGagne(ligne, colonne, symbole);
        jeu.viderCase(ligne, colonne);

        return gagne;
    }

    private int scoreTrois(Puissance4 jeu, int ligne, int colonne, char symbole) {
        int score = 0;
        score += ouvertures(jeu, ligne, colonne, 0, 1, symbole);
        score += ouvertures(jeu, ligne, colonne, 1, 0, symbole);
        score += ouvertures(jeu, ligne, colonne, 1, 1, symbole);
        score += ouvertures(jeu, ligne, colonne, 1, -1, symbole);
        return score;
    }

    private int ouvertures(Puissance4 jeu, int ligne, int colonne, int dl, int dc, char symbole) {
        char[][] grille = jeu.getGrille();

        int avant = compte(grille, ligne, colonne, -dl, -dc, symbole);
        int apres = compte(grille, ligne, colonne, dl, dc, symbole);

        int total = 1 + avant + apres;

        if (total != 3) {
            return 0;
        }

        int nb = 0;

        int l1 = ligne - (avant + 1) * dl;
        int c1 = colonne - (avant + 1) * dc;
        if (dans(l1, c1) && grille[l1][c1] == ' ') {
            nb++;
        }

        int l2 = ligne + (apres + 1) * dl;
        int c2 = colonne + (apres + 1) * dc;
        if (dans(l2, c2) && grille[l2][c2] == ' ') {
            nb++;
        }

        return nb;
    }

    private int scoreDeux(Puissance4 jeu, int ligne, int colonne, char symbole) {
        int score = 0;
        score += groupesDeux(jeu, ligne, colonne, 0, 1, symbole);
        score += groupesDeux(jeu, ligne, colonne, 1, 0, symbole);
        score += groupesDeux(jeu, ligne, colonne, 1, 1, symbole);
        score += groupesDeux(jeu, ligne, colonne, 1, -1, symbole);
        return score;
    }

    private int groupesDeux(Puissance4 jeu, int ligne, int colonne, int dl, int dc, char symbole) {
        char[][] grille = jeu.getGrille();
        int total = 0;

        for (int debut = -3; debut <= 0; debut++) {
            int nbSymbole = 0;
            int nbVide = 0;
            boolean contientCoup = false;
            boolean ok = true;

            for (int i = 0; i < 4; i++) {
                int l = ligne + (debut + i) * dl;
                int c = colonne + (debut + i) * dc;

                if (!dans(l, c)) {
                    ok = false;
                    break;
                }

                if (l == ligne && c == colonne) {
                    contientCoup = true;
                }

                if (grille[l][c] == symbole) {
                    nbSymbole++;
                } else if (grille[l][c] == ' ') {
                    nbVide++;
                }
            }

            if (ok && contientCoup && nbSymbole == 2 && nbVide == 2) {
                total++;
            }
        }

        return total;
    }

    private int compte(char[][] grille, int ligne, int colonne, int dl, int dc, char symbole) {
        int nb = 0;
        int l = ligne + dl;
        int c = colonne + dc;

        while (dans(l, c) && grille[l][c] == symbole) {
            nb++;
            l += dl;
            c += dc;
        }

        return nb;
    }

    private int bonusColonne(int colonne) {
        if (colonne == 3) {
            return 5;
        }
        if (colonne == 2 || colonne == 4) {
            return 3;
        }
        if (colonne == 1 || colonne == 5) {
            return 2;
        }
        return 1;
    }

    private boolean dans(int ligne, int colonne) {
        return ligne >= 0 && ligne < 6 && colonne >= 0 && colonne < 7;
    }
}
