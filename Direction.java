package engine.map;

/**
 * La classe Direction représente les directions de déplacement dans la grille.
 * Elle fournit des constantes pour les directions courantes et des méthodes pour obtenir les déplacements associés.
 */
public enum Direction {
    NORD(-1, 0),
    SUD(1, 0),
    EST(0, 1),
    OUEST(0, -1);

    private final int deltaLigne;
    private final int deltaColonne;

    /**
     * Constructeur de l'énumération Direction.
     *
     * @param deltaLigne le déplacement en ligne associé à la direction
     * @param deltaColonne le déplacement en colonne associé à la direction
     */
    Direction(int deltaLigne, int deltaColonne) {
        this.deltaLigne = deltaLigne;
        this.deltaColonne = deltaColonne;
    }

    /**
     * Retourne le déplacement en ligne associé à la direction.
     *
     * @return le déplacement en ligne
     */
    public int getDeltaLigne() {
        return deltaLigne;
    }

    /**
     * Retourne le déplacement en colonne associé à la direction.
     *
     * @return le déplacement en colonne
     */
    public int getDeltaColonne() {
        return deltaColonne;
    }

    /**
     * Applique la direction à une coordonnée pour obtenir la nouvelle coordonnée résultante.
     *
     * @param coordonnee la coordonnée de départ
     * @return la nouvelle coordonnée après application de la direction
     */
    public Coordonnee appliquerDirection(Coordonnee coordonnee) {
        int nouvelleLigne = coordonnee.getLigne() + this.deltaLigne;
        int nouvelleColonne = coordonnee.getColonne() + this.deltaColonne;
        return new Coordonnee(nouvelleLigne, nouvelleColonne);
    }
}