package engine.map;

import config.GameConfiguration;
import engine.personnage.Gardien;
import engine.personnage.Intrus;
import engine.personnage.Personnage;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe Grille représente la grille du jeu, contenant des cases, des intrus et des gardiens.
 * Elle permet d'initialiser la grille, d'ajouter et de retirer des personnages, et de récupérer des informations sur la grille.
 */
public class Grille {
    
    private Case[][] grille;
    private int nbLigne;
    private int nbColonne;
    
    private List<Intrus> intrus = new ArrayList<>();
    private List<Gardien> gardiens = new ArrayList<>();
    
    /**
     * Constructeur de la classe Grille.
     * Initialise la grille avec le nombre de lignes et de colonnes spécifié.
     *
     * @param nbLigne le nombre de lignes de la grille
     * @param nbColonne le nombre de colonnes de la grille
     */
    public Grille(int nbLigne, int nbColonne) {
        init(nbLigne, nbColonne);
        for (int lineIndex = 0; lineIndex < nbLigne; lineIndex++) {
            for (int columnIndex = 0; columnIndex < nbColonne; columnIndex++) {
                Coordonnee position = new Coordonnee(lineIndex, columnIndex);
                grille[lineIndex][columnIndex] = new Case(position, GameConfiguration.PLAINE);
            }
        }
    }
    
    /**
     * Initialise les attributs de la grille.
     *
     * @param nbLigne le nombre de lignes de la grille
     * @param nbColonne le nombre de colonnes de la grille
     */
    private void init(int nbLigne, int nbColonne) {
        this.nbLigne = nbLigne;
        this.nbColonne = nbColonne;
        this.grille = new Case[nbLigne][nbColonne];
    }
    
    /**
     * Retourne le nombre de lignes de la grille.
     *
     * @return le nombre de lignes de la grille
     */
    public int getNbLigne() {
        return nbLigne;
    }
    
    /**
     * Retourne le nombre de colonnes de la grille.
     *
     * @return le nombre de colonnes de la grille
     */
    public int getNbColonne() {
        return nbColonne;
    }

    /**
     * Ajoute un intrus à la liste des intrus.
     *
     * @param intrus l'intrus à ajouter
     */
    public void ajouterIntrus(Intrus intrus) {
        this.intrus.add(intrus);
    }
    
    /**
     * Retourne la liste des intrus.
     *
     * @return la liste des intrus
     */
    public List<Intrus> getIntrus() {
        return this.intrus;
    }
    
    /**
     * Retire un intrus de la liste des intrus.
     *
     * @param intrus l'intrus à retirer
     */
    public void retirerIntrus(Intrus intrus) {
        this.intrus.remove(intrus);
    }
    
    /**
     * Ajoute un gardien à la liste des gardiens.
     *
     * @param gardien le gardien à ajouter
     */
    public void ajouterGardien(Gardien gardien) {
        this.gardiens.add(gardien);
    }

    /**
     * Retourne la liste des gardiens.
     *
     * @return la liste des gardiens
     */
    public List<Gardien> getGardiens() {
        return this.gardiens;
    }
    
    /**
     * Retourne la case à la position spécifiée.
     *
     * @param position la position de la case
     * @return la case à la position spécifiée, ou null si la position est en dehors des limites
     */
    public Case getCase(Coordonnee position) {
        int ligne = position.getLigne();
        int colonne = position.getColonne();
        if (ligne >= 0 && ligne < nbLigne && colonne >= 0 && colonne < nbColonne) {
            return grille[ligne][colonne];
        }
        return null;
    }
    
    /**
     * Retourne la case aux coordonnées spécifiées.
     *
     * @param ligne la ligne de la case
     * @param colonne la colonne de la case
     * @return la case aux coordonnées spécifiées, ou null si les coordonnées sont en dehors des limites
     */
    public Case getCase(int ligne, int colonne) {
        Coordonnee position = new Coordonnee(ligne, colonne);
        return getCase(position);
    }
    
    /**
     * Retourne la grille.
     *
     * @return la grille
     */
    public Case[][] getGrille() {
        return this.grille;
    }
    
    /**
     * Définit la grille.
     *
     * @param grille la nouvelle grille
     */
    public void setGrille(Case[][] grille) {
        this.grille = grille;
    }
    
    /**
     * Retourne la liste des personnages à la coordonnée spécifiée.
     *
     * @param coordonnee la coordonnée à vérifier
     * @return la liste des personnages à la coordonnée spécifiée, ou null si la coordonnée est null
     */
    public List<Personnage> getPersonnages(Coordonnee coordonnee) {
        if (coordonnee == null) {
            return null;
        }
        List<Personnage> personnages = new ArrayList<>();
        for (Intrus intrus : this.intrus) {
            if (intrus.getCoordonnee().equals(coordonnee)) {
                personnages.add(intrus);
            }
        }
        
        for (Gardien gardien : this.gardiens) {
            if (gardien.getCoordonnee().equals(coordonnee)) {
                personnages.add(gardien);
            }
        }
        
        return personnages;
    }
}