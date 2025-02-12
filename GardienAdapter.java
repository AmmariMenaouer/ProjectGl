package gui;

import java.awt.Graphics;
import engine.personnage.Gardien;

/**
 * GardienAdapter est une classe qui adapte un Gardien à l'interface Drawable.
 * Elle permet de dessiner le Gardien en utilisant une stratégie de peinture fournie.
 */
public class GardienAdapter implements Drawable {
    private Gardien gardien;
    private PaintStrategy paintStrategy;

    /**
     * Construit un GardienAdapter avec le gardien et la stratégie de peinture fournis.
     *
     * @param gardien       le gardien à adapter
     * @param paintStrategy la stratégie utilisée pour dessiner le gardien
     */
    public GardienAdapter(Gardien gardien, PaintStrategy paintStrategy) {
        this.gardien = gardien;
        this.paintStrategy = paintStrategy;
    }

    /**
     * Dessine le gardien en utilisant la stratégie de peinture.
     *
     * @param g le contexte graphique utilisé pour dessiner
     */
    @Override
    public void draw(Graphics g) {
        paintStrategy.paint(gardien, g);
    }
}