package engine.map;

import engine.map.obstacle.Obstacle;

import java.util.Objects;

/**
 * Représente une case de la grille.
 */
public class Case {
    private Coordonnee position;
    private Obstacle obstacle;
    
    /**
     * Constructeur de la classe Case.
     *
     * @param position la position de la case
     * @param obstacle l'obstacle présent sur la case
     */
    public Case(Coordonnee position, Obstacle obstacle) {
        this.position = position;
        this.obstacle = obstacle;
    }
    
    /**
     * Retourne la position de la case.
     *
     * @return la position de la case
     */
    public Coordonnee getPosition() {
        return this.position;
    }
    
    /**
     * Définit la position de la case.
     *
     * @param position la nouvelle position de la case
     */
    public void setPosition(Coordonnee position) {
        this.position = position;
    }
    
    /**
     * Retourne l'obstacle présent sur la case.
     *
     * @return l'obstacle présent sur la case
     */
    public Obstacle getObstacle() {
        return this.obstacle;
    }
    
    /**
     * Définit l'obstacle présent sur la case.
     *
     * @param obstacle le nouvel obstacle présent sur la case
     */
    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Case other = (Case) obj;
        return Objects.equals(position, other.position);
    }
}