package gui;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * SpriteLoader est une classe utilitaire pour charger des images (sprites) à partir du système de fichiers.
 */
public class SpriteLoader {
    
    /**
     * Charge une image (sprite) à partir du chemin spécifié.
     *
     * @param path le chemin du fichier image à charger
     * @return l'image chargée sous forme de BufferedImage, ou null si une erreur survient
     */
    public static BufferedImage loadSprite(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}