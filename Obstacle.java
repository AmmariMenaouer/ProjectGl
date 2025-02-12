package engine.map.obstacle;

import java.util.Objects;

/**
 * Obstacle est une classe abstraite représentant un obstacle dans le jeu.
 * Chaque obstacle a un type, et peut bloquer la vision et/ou le déplacement.
 */
public abstract class Obstacle {
    private String type;
    private boolean bloqueVision;
    private boolean bloqueDeplacement;
    
    /**
     * Constructeur de la classe Obstacle.
     *
     * @param type le type de l'obstacle
     * @param isBloqueVision indique si l'obstacle bloque la vision
     * @param isBloqueDeplacement indique si l'obstacle bloque le déplacement
     */
    public Obstacle(String type, boolean isBloqueVision, boolean isBloqueDeplacement) {
        this.type = type;
        this.bloqueVision = isBloqueVision;
        this.bloqueDeplacement = isBloqueDeplacement;
    }

    /**
     * Retourne le type de l'obstacle.
     *
     * @return le type de l'obstacle
     */
    public String getType() {
        return type;
    }

    /**
     * Indique si l'obstacle bloque la vision.
     *
     * @return true si l'obstacle bloque la vision, false sinon
     */
    public boolean isBloqueVision() {
        return bloqueVision;
    }

    /**
     * Indique si l'obstacle bloque le déplacement.
     *
     * @return true si l'obstacle bloque le déplacement, false sinon
     */
    public boolean isBloqueDeplacement() {
        return bloqueDeplacement;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Obstacle other = (Obstacle) obj;
        return Objects.equals(type, other.type);
    }
}