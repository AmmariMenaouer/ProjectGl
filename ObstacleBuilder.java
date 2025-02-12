package engine.map.generation;

import engine.map.obstacle.Obstacle;

/**
 * ObstacleBuilder est une classe qui aide à construire des obstacles
 * avec des propriétés spécifiques telles que la densité, le nombre d'obstacles,
 * et la densité de cases autour de l'obstacle. Elle utilise une carte de probabilités
 * pour gérer les positions des obstacles.
 */
public class ObstacleBuilder {
    private Obstacle obstacle;
    private int densite;
    private int nbObstacle;
    private int nbCaseDensiteObstacle;
    private MapProbaCoordonnee mapProbaCoordonnee;
    
    /**
     * Constructeur de la classe ObstacleBuilder.
     *
     * @param obstacle l'obstacle à construire
     * @param densite la densité de l'obstacle
     * @param nbObstacle le nombre d'obstacles à générer
     * @param nbCaseDensiteObstacle le nombre de cases autour de l'obstacle pour la densité
     */
    public ObstacleBuilder(Obstacle obstacle, int densite, int nbObstacle, int nbCaseDensiteObstacle) {
        this.obstacle = obstacle;
        this.densite = densite;
        this.nbObstacle = nbObstacle;
        this.nbCaseDensiteObstacle = nbCaseDensiteObstacle;
        this.mapProbaCoordonnee = new MapProbaCoordonnee();
    }

    /**
     * Retourne la carte de probabilités des coordonnées.
     *
     * @return la carte de probabilités
     */
    public MapProbaCoordonnee getMapProbaCoordonnee() {
        return mapProbaCoordonnee;
    }

    /**
     * Définit l'obstacle.
     *
     * @param obstacle l'obstacle à définir
     */
    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }
    
    /**
     * Retourne l'obstacle.
     *
     * @return l'obstacle
     */
    public Obstacle getObstacle() {
        return obstacle;
    }

    /**
     * Définit la densité de l'obstacle.
     *
     * @param densite la densité à définir
     */
    public void setDensite(int densite) {
        this.densite = densite;
    }
    
    /**
     * Retourne la densité de l'obstacle.
     *
     * @return la densité
     */
    public int getDensite() {
        return densite;
    }

    /**
     * Définit le nombre d'obstacles à générer.
     *
     * @param nbObstacle le nombre d'obstacles à définir
     */
    public void setNbObstacle(int nbObstacle) {
        this.nbObstacle = nbObstacle;
    }
    
    /**
     * Retourne le nombre d'obstacles à générer.
     *
     * @return le nombre d'obstacles
     */
    public int getNbObstacle() {
        return nbObstacle;
    }

    /**
     * Définit le nombre de cases autour de l'obstacle pour la densité.
     *
     * @param nbCaseDensiteObstacle le nombre de cases à définir
     */
    public void setNbCaseDensiteObstacle(int nbCaseDensiteObstacle) {
        this.nbCaseDensiteObstacle = nbCaseDensiteObstacle;
    }
    
    /**
     * Retourne le nombre de cases autour de l'obstacle pour la densité.
     *
     * @return le nombre de cases
     */
    public int getNbCaseDensiteObstacle() {
        return nbCaseDensiteObstacle;
    }
}