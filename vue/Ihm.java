package vue;

import java.util.Scanner;
import modele.Joueur;

public class Ihm {

    private Scanner scanner;

    public Ihm() {
        scanner = new Scanner(System.in);
    }

    public void afficherMessage(String message) {
        System.out.println(message);
    }

    public String demanderNomJoueur(int numeroJoueur) {
        while (true) {
            System.out.print("Nom du joueur " + numeroJoueur + " : ");
            String nom = scanner.nextLine().trim();

            if (!nom.isEmpty()) {
                return nom;
            }

            System.out.println("Le nom ne doit pas etre vide.");
        }
    }

    public boolean demanderModeJeu() {
        while (true) {
            System.out.print("Jouer a deux joueurs ou contre l'ordinateur ? (2/ia) : ");
            String saisie = scanner.nextLine().trim().toLowerCase();

            if (saisie.equals("2")) {
                return false;
            }

            if (saisie.equals("ia")) {
                return true;
            }

            System.out.println("Choix invalide.");
        }
    }

    public int demanderChoixJeu() {
        while (true) {
            System.out.println("Choisir un jeu :");
            System.out.println("1 - Morpion");
            System.out.println("2 - Puissance 4");
            System.out.println("3 - Nim");
            System.out.print("Votre choix : ");

            String saisie = scanner.nextLine().trim();

            if (saisie.equals("1") || saisie.equals("2") || saisie.equals("3")) {
                return Integer.parseInt(saisie);
            }

            System.out.println("Choix invalide.");
        }
    }

    public int demanderNiveauIA() {
        while (true) {
            System.out.print("Choisir le niveau de l'IA pour cette partie (facile/difficile) : ");
            String saisie = scanner.nextLine().trim().toLowerCase();

            if (saisie.equals("facile")) {
                return 1;
            }

            if (saisie.equals("difficile")) {
                return 2;
            }

            System.out.println("Choix invalide.");
        }
    }

    public void afficherPlateau(String plateau) {
        System.out.println(plateau);
    }

    public String demanderCoupMorpion(String nomJoueur) {
        System.out.print(nomJoueur + " : a vous de jouer (ligne colonne) : ");
        return scanner.nextLine();
    }

    public String demanderCoupPuissance4(String nomJoueur) {
        System.out.print(nomJoueur + " : a vous de jouer (colonne 1 a 7) : ");
        return scanner.nextLine();
    }

    public String demanderCoupNim(String nomJoueur) {
        System.out.print(nomJoueur + " : a vous de jouer un coup sous la forme 'm n' ou m est le tas choisi et n le nombre d'allumettes a retirer : ");
        return scanner.nextLine();
    }

    public boolean demanderRejouer() {
        while (true) {
            System.out.print("Rejouer une partie ? (oui/non) : ");
            String saisie = scanner.nextLine().trim().toLowerCase();

            if (saisie.equals("oui")) {
                return true;
            }

            if (saisie.equals("non")) {
                return false;
            }

            System.out.println("Reponse invalide.");
        }
    }

    public void afficherFinPartie(Joueur vainqueur) {
        if (vainqueur == null) {
            System.out.println("Partie nulle.");
        } else {
            System.out.println("Le vainqueur est : " + vainqueur.getNom());
        }
    }

    public void afficherBilan(Joueur joueur1, Joueur joueur2) {
        System.out.println("Bilan final :");
        System.out.println(joueur1.getNom() + " a gagne " + joueur1.getNbPartiesGagnees() + " partie(s).");
        System.out.println(joueur2.getNom() + " a gagne " + joueur2.getNbPartiesGagnees() + " partie(s).");

        if (joueur1.getNbPartiesGagnees() > joueur2.getNbPartiesGagnees()) {
            System.out.println("Vainqueur final : " + joueur1.getNom());
        } else if (joueur2.getNbPartiesGagnees() > joueur1.getNbPartiesGagnees()) {
            System.out.println("Vainqueur final : " + joueur2.getNom());
        } else {
            System.out.println("Resultat final : ex aequo");
        }
    }
}
