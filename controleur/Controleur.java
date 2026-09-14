package controleur;

import modele.IA;
import modele.IAMorpion;
import modele.IAMorpionFacile;
import modele.IANimDifficile;
import modele.IANimFacile;
import modele.IAPuissance;
import modele.IAPuissanceDifficile;
import modele.Joueur;
import modele.Morpion;
import modele.Nim;
import modele.Partie;
import modele.Puissance4;
import vue.Ihm;

public class Controleur {

    private Ihm ihm;
    private Joueur joueur1;
    private Joueur joueur2;
    private boolean contreIA;
    private char symboleJoueur1;
    private char symboleJoueur2;
    private IA ia;

    public Controleur(Ihm ihm) {
        this.ihm = ihm;
    }

    public void lancerJeu() {
        saisirJoueurs();
        int choixJeu = ihm.demanderChoixJeu();
        definirSymboles(choixJeu);

        boolean rejouer = true;

        while (rejouer) {
            Partie partie = creerPartie(choixJeu);
            definirIA(choixJeu);
            jouerPartie(partie, choixJeu);

            Joueur vainqueur = getJoueur(partie.getVainqueur());
            if (vainqueur != null) {
                vainqueur.ajouterVictoire();
            }

            ihm.afficherFinPartie(vainqueur);
            rejouer = ihm.demanderRejouer();
        }

        ihm.afficherBilan(joueur1, joueur2);
    }

    private void saisirJoueurs() {
        String nomJoueur1 = ihm.demanderNomJoueur(1);
        joueur1 = new Joueur(nomJoueur1, 1);

        contreIA = ihm.demanderModeJeu();
        if (contreIA) {
            joueur2 = new Joueur("IA", 2);
        } else {
            String nomJoueur2 = ihm.demanderNomJoueur(2);
            joueur2 = new Joueur(nomJoueur2, 2);
        }
    }

    private void definirSymboles(int choixJeu) {
        if (choixJeu == 1) {
            symboleJoueur1 = 'X';
            symboleJoueur2 = 'O';
        } else if (choixJeu == 2) {
            symboleJoueur1 = 'R';
            symboleJoueur2 = 'J';
}
    }

    private Partie creerPartie(int choixJeu) {
        if (choixJeu == 1) {
            return new Morpion();
        }
        if (choixJeu == 2) {
            return new Puissance4();
        }
        return new Nim();
    }

    private void definirIA(int choixJeu) {
        if (!contreIA) {
            ia = null;
            return;
        }

        int niveau = ihm.demanderNiveauIA();

        if (choixJeu == 1) {
            if (niveau == 1) {
                ia = new IAMorpionFacile();
            } else {
                ia = new IAMorpion();
            }
        } else if (choixJeu == 2) {
            if (niveau == 1) {
                ia = new IAPuissance();
            } else {
                ia = new IAPuissanceDifficile();
            }
        } else {
            if (niveau == 1) {
                ia = new IANimFacile();
            } else {
                ia = new IANimDifficile();
            }
        }
    }

    private void jouerPartie(Partie partie, int choixJeu) {
        while (!partie.estFinie()) {
            ihm.afficherPlateau(partie.toString());

            int idJoueurCourant = partie.getJoueurCourant();
            Joueur joueurCourant = getJoueur(idJoueurCourant);
            char symboleCourant = getSymbole(idJoueurCourant);
            String saisie;

            if (contreIA && idJoueurCourant == 2) {
                saisie = ia.choisirCoup(partie, symboleJoueur2, symboleJoueur1);
                ihm.afficherMessage("IA joue : " + saisie);
            } else if (choixJeu == 1) {
                saisie = ihm.demanderCoupMorpion(joueurCourant.getNom());
            } else if (choixJeu == 2) {
                saisie = ihm.demanderCoupPuissance4(joueurCourant.getNom());
            } else {
                saisie = ihm.demanderCoupNim(joueurCourant.getNom());
            }

            boolean coupValide = partie.jouerCoup(saisie, symboleCourant);

            if (!coupValide) {
                ihm.afficherMessage("Coup invalide. Veuillez recommencer.");
            }
        }

        ihm.afficherPlateau(partie.toString());
    }

    private Joueur getJoueur(int idJoueur) {
        if (idJoueur == 1) {
            return joueur1;
        }
        if (idJoueur == 2) {
            return joueur2;
        }
        return null;
    }

    private char getSymbole(int idJoueur) {
        if (idJoueur == 1) {
            return symboleJoueur1;
        }
        return symboleJoueur2;
    }
}
