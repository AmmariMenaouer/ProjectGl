package engine.map.obstacle;

/**
 * Roche est une classe représentant un obstacle de type roche dans le jeu.
 * Elle hérite de la classe Obstacle et initialise les attributs spécifiques
 * pour une roche.
 */
public class Roche extends Obstacle {
    
    /**
     * Constructeur de la classe Roche.
     * Initialise un obstacle de type roche avec un nom, une traversabilité et une visibilité spécifiques.
     */
    public Roche() {
        super("Roche", true, true);
    }
}