package modele;

public class Joueur {

    private String nom;
    private int idJoueur;
    private int nbPartiesGagnees;

    public Joueur(String nom, int idJoueur) {
        this.nom = nom;
        this.idJoueur = idJoueur;
        this.nbPartiesGagnees = 0;
    }

    public String getNom() {
        return nom;
    }

    public int getIdJoueur() {
        return idJoueur;
    }

    public int getNbPartiesGagnees() {
        return nbPartiesGagnees;
    }

    public void ajouterVictoire() {
        nbPartiesGagnees++;
    }
}