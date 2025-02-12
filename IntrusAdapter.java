package gui;

import java.awt.Graphics;
import engine.personnage.Intrus;

/**
 * IntrusAdapter est une classe qui adapte un Intrus à l'interface Drawable.
 * Elle permet de dessiner l'Intrus en utilisant une stratégie de peinture fournie.
 */
public class IntrusAdapter implements Drawable {
    private Intrus intrus;
    private PaintStrategy paintStrategy;

    /**
     * Construit un IntrusAdapter avec l'intrus et la stratégie de peinture fournis.
     *
     * @param intrus        l'intrus à adapter
     * @param paintStrategy la stratégie utilisée pour dessiner l'intrus
     */
    public IntrusAdapter(Intrus intrus, PaintStrategy paintStrategy) {
        this.intrus = intrus;
        this.paintStrategy = paintStrategy;
    }

    /**
     * Dessine l'intrus en utilisant la stratégie de peinture.
     *
     * @param g le contexte graphique utilisé pour dessiner
     */
    @Override
    public void draw(Graphics g) {
        paintStrategy.paint(intrus, g);
    }
}