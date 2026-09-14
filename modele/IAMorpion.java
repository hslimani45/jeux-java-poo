package modele;

public class IAMorpion extends IA {

    @Override
    public String choisirCoup(Partie partie, char symboleIA, char symboleAdverse) {
        Morpion morpion = (Morpion) partie;

        if (morpion.nbCasesVides() <= 4) {
            return choisirAvecMinMax(morpion, symboleIA, symboleAdverse);
        }

        String coup = coupGagnant(morpion, symboleIA);
        if (coup != null) {
            return coup;
        }

        coup = coupGagnant(morpion, symboleAdverse);
        if (coup != null) {
            return coup;
        }

        if (morpion.caseValide(1, 1)) {
            return "2 2";
        }

        int[][] coins = {{0, 0}, {0, 2}, {2, 0}, {2, 2}};
        for (int[] coin : coins) {
            if (morpion.caseValide(coin[0], coin[1])) {
                return (coin[0] + 1) + " " + (coin[1] + 1);
            }
        }

        int[][] cotes = {{0, 1}, {1, 0}, {1, 2}, {2, 1}};
        for (int[] cote : cotes) {
            if (morpion.caseValide(cote[0], cote[1])) {
                return (cote[0] + 1) + " " + (cote[1] + 1);
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (morpion.caseValide(i, j)) {
                    return (i + 1) + " " + (j + 1);
                }
            }
        }

        return "1 1";
    }

    private String coupGagnant(Morpion morpion, char symbole) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (morpion.caseValide(i, j)) {
                    morpion.poserSymbole(i, j, symbole);
                    boolean gagne = morpion.aGagne(symbole);
                    morpion.viderCase(i, j);
                    if (gagne) {
                        return (i + 1) + " " + (j + 1);
                    }
                }
            }
        }
        return null;
    }

    private String choisirAvecMinMax(Morpion morpion, char symboleIA, char symboleAdverse) {
        int meilleurScore = -2;
        String meilleurCoup = null;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (morpion.caseValide(i, j)) {
                    morpion.poserSymbole(i, j, symboleIA);
                    int score = minmax(morpion, false, symboleIA, symboleAdverse);
                    morpion.viderCase(i, j);

                    if (score > meilleurScore) {
                        meilleurScore = score;
                        meilleurCoup = (i + 1) + " " + (j + 1);
                    }
                }
            }
        }

        return meilleurCoup;
    }

    private int minmax(Morpion morpion, boolean tourIA, char symboleIA, char symboleAdverse) {
        if (morpion.aGagne(symboleIA)) {
            return 1;
        }
        if (morpion.aGagne(symboleAdverse)) {
            return -1;
        }
        if (morpion.grillePleine()) {
            return 0;
        }

        if (tourIA) {
            int meilleur = -2;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (morpion.caseValide(i, j)) {
                        morpion.poserSymbole(i, j, symboleIA);
                        int score = minmax(morpion, false, symboleIA, symboleAdverse);
                        morpion.viderCase(i, j);
                        if (score > meilleur) {
                            meilleur = score;
                        }
                    }
                }
            }
            return meilleur;
        }

        int meilleur = 2;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (morpion.caseValide(i, j)) {
                    morpion.poserSymbole(i, j, symboleAdverse);
                    int score = minmax(morpion, true, symboleIA, symboleAdverse);
                    morpion.viderCase(i, j);
                    if (score < meilleur) {
                        meilleur = score;
                    }
                }
            }
        }
        return meilleur;
    }
}
