package gui;

import java.awt.Graphics;
import engine.map.Grille;

/**
 * GrilleAdapter est une classe qui adapte une Grille à l'interface Drawable.
 * Elle permet de dessiner la Grille en utilisant une stratégie de peinture fournie.
 */
public class GrilleAdapter implements Drawable {
    private Grille grille;
    private PaintStrategy paintStrategy;

    /**
     * Construit un GrilleAdapter avec la grille et la stratégie de peinture fournies.
     *
     * @param grille        la grille à adapter
     * @param paintStrategy la stratégie utilisée pour dessiner la grille
     */
    public GrilleAdapter(Grille grille, PaintStrategy paintStrategy) {
        this.grille = grille;
        this.paintStrategy = paintStrategy;
    }

    /**
     * Dessine la grille en utilisant la stratégie de peinture.
     *
     * @param g le contexte graphique utilisé pour dessiner
     */
    @Override
    public void draw(Graphics g) {
        paintStrategy.paint(grille, g);
    }
}