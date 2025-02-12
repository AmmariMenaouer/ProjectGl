package gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import engine.map.Grille;
import engine.personnage.Gardien;
import engine.personnage.Intrus;

/**
 * GameDisplay est un JPanel personnalisé qui gère l'affichage des éléments du jeu.
 * Il utilise une liste d'objets Drawable pour gérer et dessiner divers composants de jeu
 * tels que la grille, les gardiens et les intrus.
 */
public class GameDisplay extends JPanel {
    private List<Drawable> drawables = new ArrayList<>();

    /**
     * Construit un GameDisplay avec la grille, le gardien et la stratégie de peinture fournis.
     * Il adapte la grille, le gardien et les intrus à l'interface Drawable à l'aide d'adaptateurs.
     *
     * @param grille        la grille du jeu à dessiner
     * @param gardien       le personnage gardien à dessiner
     * @param intrus        les intrus à dessiner
     * @param paintStrategy la stratégie utilisée pour peindre les composants
     */
    public GameDisplay(Grille grille, Gardien gardien, List<Intrus> intrus, PaintStrategy paintStrategy) {
        // Adapter la grille, le gardien et les intrus à l'interface Drawable
        drawables.add(new GrilleAdapter(grille, paintStrategy));
        drawables.add(new GardienAdapter(gardien, paintStrategy));
        for (Intrus intru : intrus) {
            drawables.add(new IntrusAdapter(intru, paintStrategy));
        }
    }

    /**
     * Remplace la méthode paintComponent pour dessiner tous les éléments adaptés.
     *
     * @param g le contexte graphique utilisé pour dessiner
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dessiner tous les éléments adaptés
        for (Drawable drawable : drawables) {
            drawable.draw(g);
        }
    }
}