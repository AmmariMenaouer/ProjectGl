package engine.map;

import java.util.Objects;

/**
 * La classe Coordonnee représente une paire de coordonnées (ligne, colonne) dans la grille.
 */
public class Coordonnee {
    private int ligne;
    private int colonne;
    
    /**
     * Constructeur de la classe Coordonnee.
     *
     * @param ligne la ligne de la coordonnée
     * @param colonne la colonne de la coordonnée
     */
    public Coordonnee(int ligne, int colonne) {
        this.ligne = ligne;
        this.colonne = colonne;
    }
    
    /**
     * Retourne la ligne de la coordonnée.
     *
     * @return la ligne de la coordonnée
     */
    public int getLigne() {
        return ligne;
    }
    
    /**
     * Retourne la colonne de la coordonnée.
     *
     * @return la colonne de la coordonnée
     */
    public int getColonne() {
        return colonne;
    }
    
    /**
     * Définit la ligne de la coordonnée.
     *
     * @param ligne la nouvelle ligne de la coordonnée
     */
    public void setLigne(int ligne) {
        this.ligne = ligne;    
    }
    
    /**
     * Définit la colonne de la coordonnée.
     *
     * @param colonne la nouvelle colonne de la coordonnée
     */
    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

    @Override
    public int hashCode() {
        return Objects.hash(colonne, ligne);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Coordonnee other = (Coordonnee) obj;
        return colonne == other.colonne && ligne == other.ligne;
    }
}