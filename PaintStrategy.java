package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import config.GameConfiguration;
import engine.map.Case;
import engine.map.Grille;
import engine.map.obstacle.Arbre;
import engine.map.obstacle.Lac;
import engine.map.obstacle.Obstacle;
import engine.map.obstacle.Roche;
import engine.personnage.Gardien;
import engine.personnage.Intrus;

/**
 * PaintStrategy est une classe qui définit la stratégie de peinture pour les
 * différents éléments du jeu.
 * Elle gère le dessin de la grille, des gardiens et des intrus.
 */
public class PaintStrategy {
    private BufferedImage guardianImage; // Image du gardien
    private BufferedImage intrusImage;   // Image de l'intrus

    /**
     * Construit une PaintStrategy et charge les images des personnages.
     */
    public PaintStrategy() {
        // Charger les images des personnages
        try {
            guardianImage = ImageIO.read(getClass().getResource("/assets/images/gardien.png"));
            intrusImage = ImageIO.read(getClass().getResource("/assets/images/voleur.jpeg"));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erreur lors du chargement des images des personnages.");
        }
    }

    /**
     * Dessine la grille en utilisant le contexte graphique fourni.
     *
     * @param grille    la grille à dessiner
     * @param graphics  le contexte graphique utilisé pour dessiner
     */
    public void paint(Grille grille, Graphics graphics) {
        int blocksize = GameConfiguration.BLOCK_SIZE;
        Case[][] cases = grille.getGrille();
        int nbLigne = grille.getNbLigne();
        int nbColonne = grille.getNbColonne();

        for (int line = 0; line < nbLigne; line++) {
            for (int col = 0; col < nbColonne; col++) {
                Case cell = cases[line][col];
                Obstacle obstacle = cell.getObstacle();

                if (obstacle instanceof Arbre) {
                    graphics.setColor(new Color(43, 139, 27));
                } else if (obstacle instanceof Lac) {
                    graphics.setColor(Color.blue);
                } else if (obstacle instanceof Roche) {
                    graphics.setColor(Color.gray);
                } else {
                    graphics.setColor(Color.yellow);
                }

                graphics.fillRect(col * blocksize, line * blocksize, blocksize, blocksize);
            }
        }
    }

    /**
     * Dessine le gardien en utilisant le contexte graphique fourni.
     *
     * @param gardien   le gardien à dessiner
     * @param graphics  le contexte graphique utilisé pour dessiner
     */
    public void paint(Gardien gardien, Graphics graphics) {
        int blocksize = GameConfiguration.BLOCK_SIZE;
        int x = gardien.getCoordonnee().getLigne() * blocksize;
        int y = gardien.getCoordonnee().getColonne() * blocksize;

        // Dessiner l'image du gardien au lieu d'un rectangle
        if (guardianImage != null) {
            graphics.drawImage(guardianImage, y, x, blocksize, blocksize, null);
        } else {
            // Fallback : dessiner un rectangle rose si l'image n'est pas chargée
            graphics.setColor(Color.pink);
            graphics.fillRect(y, x, blocksize, blocksize);
        }
    }

    /**
     * Dessine l'intrus en utilisant le contexte graphique fourni.
     *
     * @param intrus    l'intrus à dessiner
     * @param graphics  le contexte graphique utilisé pour dessiner
     */
    public void paint(Intrus intrus, Graphics graphics) {
        int blocksize = GameConfiguration.BLOCK_SIZE;
        int x = intrus.getCoordonnee().getLigne() * blocksize;
        int y = intrus.getCoordonnee().getColonne() * blocksize;

        // Dessiner l'image de l'intrus au lieu d'un rectangle
        if (intrusImage != null) {
            graphics.drawImage(intrusImage, y, x, blocksize, blocksize, null);
        } else {
            // Fallback : dessiner un rectangle rouge si l'image n'est pas chargée
            graphics.setColor(Color.red);
            graphics.fillRect(y, x, blocksize, blocksize);
        }
    }
}