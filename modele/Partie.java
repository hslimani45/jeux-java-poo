package modele;

public abstract class Partie {

    private static int compteurPartie = 1;

    protected int idPartie;
    protected String etat;
    protected int joueurCourant;
    protected int vainqueur;

    public Partie() {
        this.idPartie = compteurPartie;
        compteurPartie++;
        this.etat = "EN_COURS";
        this.joueurCourant = 1;
        this.vainqueur = 0;
    }

    public int getIdPartie() {
        return idPartie;
    }

    public String getEtat() {
        return etat;
    }

    public int getJoueurCourant() {
        return joueurCourant;
    }

    public int getVainqueur() {
        return vainqueur;
    }

    public boolean estFinie() {
        return !etat.equals("EN_COURS");
    }

    protected void changerJoueur() {
        if (joueurCourant == 1) {
            joueurCourant = 2;
        } else {
            joueurCourant = 1;
        }
    }

    protected void definirVictoire() {
        etat = "GAGNEE";
        vainqueur = joueurCourant;
    }

    protected void definirNul() {
        etat = "NULLE";
        vainqueur = 0;
    }

    public abstract boolean jouerCoup(String saisie, char symbole);

    @Override
    public abstract String toString();
}
