package config;

import engine.map.obstacle.Arbre;
import engine.map.obstacle.Lac;
import engine.map.obstacle.Plaine;
import engine.map.obstacle.Roche;

/**
 * Configuration du jeu, contenant les paramètres pour la fenêtre, la grille, 
 * les obstacles et les vitesses des personnages.
 */
public class GameConfiguration {
    
    // Dimensions de la fenêtre du jeu
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 800;
    
    // Taille d'un bloc de la grille en pixels
    public static final int BLOCK_SIZE = 25;
    
    // Vitesse du jeu en millisecondes (intervalle de mise à jour)
    public static final int GAME_SPEED = 1000;

    // Vitesses des intrus et des gardiens en unités de déplacement
    public static final int VITESSE_INTRUS = 1000; // Intervalle de déplacement des intrus en millisecondes (augmenté pour réduire la vitesse)
    public static final int VITESSE_GARDIENS = 12;
    
    // Niveau de difficulté du jeu (1 à 3, 3 étant le plus difficile)
    public static final int DIFFICULTE = 3;
    
    // Dimensions de la grille en nombre de lignes et de colonnes
    public static final int NB_LIGNE = 32;
    public static final int NB_COLONNE = 32;
    
    // Paramètres pour les lacs
    public static final int NB_LAC_MIN = 2;
    public static final int NB_LAC_MAX = 3;
    public static final int DENSITE_LAC_MIN = 20000;
    public static final int DENSITE_LAC_MAX = 20000;
    public static final int TOTAL_CASE_LAC_MIN = (NB_LIGNE * NB_COLONNE) / (40 * (4 - DIFFICULTE));
    public static final int TOTAL_CASE_LAC_MAX = (NB_LIGNE * NB_COLONNE) / (10 * (4 - DIFFICULTE));
    public static final int NB_CASE_DENSITE_LAC = 2;
    
    // Paramètres pour les roches
    public static final int NB_ROCHE_MIN = 3;
    public static final int NB_ROCHE_MAX = 6;
    public static final int DENSITE_ROCHE_MIN = 250;
    public static final int DENSITE_ROCHE_MAX = 500;
    public static final int TOTAL_CASE_ROCHE_MIN = (NB_LIGNE * NB_COLONNE) / (80 * (4 - DIFFICULTE));
    public static final int TOTAL_CASE_ROCHE_MAX = (NB_LIGNE * NB_COLONNE) / (25 * (4 - DIFFICULTE));
    public static final int NB_CASE_DENSITE_ROCHE = 3;
    
    // Paramètres pour les arbres
    public static final int DENSITE_ARBRE_MIN = 1;
    public static final int DENSITE_ARBRE_MAX = 1;
    public static final int TOTAL_CASE_ARBRE_MIN = (NB_LIGNE * NB_COLONNE) / (17 * (4 - DIFFICULTE));
    public static final int TOTAL_CASE_ARBRE_MAX = (NB_LIGNE * NB_COLONNE) / (10 * (4 - DIFFICULTE));
    public static final int NB_CASE_DENSITE_ARBRE = 0;

    // Nombre de voleurs (intrus)
    public static final int NB_VOLEURS = 5; // Augmenter le nombre de voleurs
    
    // Instances des différents types d'obstacles
    public static final Plaine PLAINE = new Plaine();
    public static final Arbre ARBRE = new Arbre();
    public static final Roche ROCHE = new Roche();    
    public static final Lac LAC = new Lac();
    
    // Permettre ou non le déplacement diagonal des personnages
    public static final boolean PERMET_DEPLACEMENT_DIAGONNAL = true;
}