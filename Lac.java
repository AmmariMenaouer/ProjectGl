package engine.map.obstacle;

/**
 * Lac est une classe représentant un obstacle de type lac dans le jeu.
 * Elle hérite de la classe Obstacle et initialise les attributs spécifiques
 * pour un lac.
 */
public class Lac extends Obstacle {
    
    /**
     * Constructeur de la classe Lac.
     * Initialise un obstacle de type lac avec un nom, une traversabilité et une visibilité spécifiques.
     */
    public Lac() {
        super("Lac", false, true);
    }
}