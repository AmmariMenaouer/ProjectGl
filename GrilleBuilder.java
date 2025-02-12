package engine.map.generation;

import config.GameConfiguration;
import config.ConfigurationMapAleatoire;
import engine.map.Case;
import engine.map.Coordonnee;
import engine.map.Grille;
import engine.map.obstacle.Obstacle;

import java.util.ArrayList;
import java.util.List;

/**
 * GrilleBuilder est une classe qui construit et initialise la grille du jeu avec des obstacles.
 * Elle génère des obstacles aléatoires et les place sur la grille en respectant certaines règles.
 */
public class GrilleBuilder {
    private Grille grille;
    private List<ObstacleBuilder> obstacleBuilders;
    
    /**
     * Constructeur de la classe GrilleBuilder.
     * Initialise la grille et les obstacles.
     */
    public GrilleBuilder() {
        this.grille = new Grille(GameConfiguration.NB_LIGNE, GameConfiguration.NB_COLONNE);
        this.obstacleBuilders = new ArrayList<>();
        initObstacleBuilders();
        genererObstacles();
    }
    
    /**
     * Initialise les constructeurs d'obstacles.
     */
    private void initObstacleBuilders() {
        this.obstacleBuilders = ConfigurationMapAleatoire.genererObstaclesAleatoires();
    }
    
    /**
     * Génère les obstacles sur la grille.
     */
    private void genererObstacles() {
        for (ObstacleBuilder builder : obstacleBuilders) {
            placerObstacles(builder);
        }
        remplissageTrou();
    }
    
    /**
     * Place les obstacles sur la grille en utilisant les coordonnées générées aléatoirement.
     *
     * @param builder le constructeur d'obstacles
     */
    private void placerObstacles(ObstacleBuilder builder) {
        MapProbaCoordonnee mapProbaCoordonnee = builder.getMapProbaCoordonnee();
        List<Coordonnee> coordonnees = getListCoordonneeGrille();
        mapProbaCoordonnee.ajouterProbabilite(100.0 / coordonnees.size(), coordonnees);
        
        int nbObstacle = builder.getNbObstacle();
        Obstacle obstacle = builder.getObstacle();
        int densite = builder.getDensite();
        int nbCaseDensite = builder.getNbCaseDensiteObstacle();
        int obstaclesPlaces = 0;
        
        while (obstaclesPlaces < nbObstacle) {
            // On prend une valeur aléatoire
            Coordonnee coordonneeAleatoire = mapProbaCoordonnee.getCoordonneeAleatoire(mapProbaCoordonnee.getListeAleatoire());
            if (coordonneeAleatoire != null && grille.getCase(coordonneeAleatoire) != null) {
                // On change la case avec le nouvel obstacle et on supprime la coordonnée de la map
                grille.getCase(coordonneeAleatoire).setObstacle(obstacle);
                mapProbaCoordonnee.supprimerCoordonnee(coordonneeAleatoire);
                
                List<Coordonnee> coordonneeAdjacentes = getCoordonneeAdjacentes(coordonneeAleatoire, nbCaseDensite);
                augmenterProbabilite(mapProbaCoordonnee, coordonneeAdjacentes, densite);
                obstaclesPlaces++;
            }
        }
    }
    
    /**
     * Retourne la liste des coordonnées de la grille.
     *
     * @return la liste des coordonnées
     */
    private List<Coordonnee> getListCoordonneeGrille() {
        List<Coordonnee> coordonnees = new ArrayList<>();
        for (int i = 0; i < GameConfiguration.NB_LIGNE; i++) {
            for (int j = 0; j < GameConfiguration.NB_COLONNE; j++) {
                Coordonnee position = new Coordonnee(i, j);
                if (grille.getCase(position).getObstacle().equals(GameConfiguration.PLAINE)) {
                    coordonnees.add(position);
                }
            }
        }
        return coordonnees;
    }
    
    /**
     * Retourne la liste des coordonnées adjacentes à une coordonnée donnée avec une densité spécifiée.
     *
     * @param coordonnee la coordonnée centrale
     * @param nbCaseDensiteObstacle la densité de cases autour de la coordonnée
     * @return la liste des coordonnées adjacentes
     */
    private List<Coordonnee> getCoordonneeAdjacentes(Coordonnee coordonnee, int nbCaseDensiteObstacle) {
        List<Coordonnee> coordonneeAdjacentes = new ArrayList<>();
        int nbCaseDensite = nbCaseDensiteObstacle;

        for (int i = -nbCaseDensite; i <= nbCaseDensite; i++) {
            for (int j = -nbCaseDensite; j <= nbCaseDensite; j++) {
                if (i == 0 && j == 0) { continue; }
                
                Coordonnee coordonneeAdjacente = new Coordonnee(coordonnee.getLigne() + i, coordonnee.getColonne() + j);
                Case caseAdjacente = grille.getCase(coordonneeAdjacente);
                if (caseAdjacente != null && caseAdjacente.getObstacle().equals(GameConfiguration.PLAINE)){
                    coordonneeAdjacentes.add(coordonneeAdjacente);
                }
            }
        }
        return coordonneeAdjacentes;
    }
    
    /**
     * Retourne la liste des coordonnées adjacentes immédiates à une coordonnée donnée.
     *
     * @param coordonnee la coordonnée centrale
     * @return la liste des coordonnées adjacentes immédiates
     */
    private List<Coordonnee> getCoordonneeAdjacentes(Coordonnee coordonnee) {
        List<Coordonnee> coordonnees = new ArrayList<>();
        List<Coordonnee> directions = List.of(new Coordonnee(0, 1), new Coordonnee(-1, 0), new Coordonnee(0, -1), new Coordonnee(1, 0));
        for (Coordonnee direction : directions) {
            Coordonnee coordonneeAdjacente = new Coordonnee(coordonnee.getLigne() + direction.getLigne(), coordonnee.getColonne() + direction.getColonne());
            Case caseAdjacente = grille.getCase(coordonneeAdjacente);
            if (caseAdjacente != null){
                coordonnees.add(coordonneeAdjacente);
            }
        }
        return coordonnees;
    }

    /**
     * Augmente la probabilité d'occurrence des coordonnées données dans la map des probabilités.
     *
     * @param mapProbaCoordonnee la map des probabilités
     * @param coordonnees la liste des coordonnées
     * @param densite la densité à appliquer
     */
    private void augmenterProbabilite(MapProbaCoordonnee mapProbaCoordonnee, List<Coordonnee> coordonnees, int densite) {
        for (Coordonnee coordonnee : coordonnees) {
            Double probaActuelle = mapProbaCoordonnee.getProbabiliteFromCoordonnee(coordonnee);
            if (probaActuelle != null) {
                double nouvelleProbabilite = (probaActuelle) * (densite / 100.0);
                
                mapProbaCoordonnee.supprimerCoordonnee(coordonnee);
                mapProbaCoordonnee.ajouterProbabilite(nouvelleProbabilite, coordonnee);
            }
        }
    }
    
    /**
     * Remplit les trous dans la grille avec des obstacles appropriés.
     */
    private void remplissageTrou() {
        int nbLigne = getGrille().getNbLigne();
        int nbColonne = getGrille().getNbColonne();
        for (int i = 0; i < nbLigne ;i++) {
            for (int j = 0; j < nbColonne ;j++) {
                Coordonnee coordonnee = new Coordonnee(i, j);
                List<Coordonnee> coordonneesAdjacentes = getCoordonneeAdjacentes(coordonnee);
                Case caseActuelle = grille.getCase(coordonnee);
                if (caseEntoure(coordonneesAdjacentes)) {
                    if (caseEntoureLac(coordonneesAdjacentes)) {
                        caseActuelle.setObstacle(GameConfiguration.LAC);
                    } else {
                        caseActuelle.setObstacle(GameConfiguration.ROCHE);
                    }
                }
            }
        }
    }
    
    /**
     * Vérifie si une case est entourée d'obstacles bloquant le déplacement.
     *
     * @param coordonneesAdjacentes la liste des coordonnées adjacentes
     * @return true si la case est entourée, false sinon
     */
    private boolean caseEntoure(List<Coordonnee> coordonneesAdjacentes){
        for (Coordonnee coordonnee : coordonneesAdjacentes) {
            Case caseAdjacente = grille.getCase(coordonnee);
            if (caseAdjacente != null && !caseAdjacente.getObstacle().isBloqueDeplacement()){
                return false;
            }
        }
        return true;
    }
    
    /**
     * Vérifie si une case est entourée d'au moins deux lacs.
     *
     * @param coordonneesAdjacentes la liste des coordonnées adjacentes
     * @return true si la case est entourée d'au moins deux lacs, false sinon
     */
    private boolean caseEntoureLac(List<Coordonnee> coordonneesAdjacentes){
        int nbLac = 0;
        for (Coordonnee coordonnee : coordonneesAdjacentes) {
            Case caseAdjacente = grille.getCase(coordonnee);
            if (caseAdjacente != null && caseAdjacente.getObstacle().equals(GameConfiguration.LAC)) {
                nbLac += 1;
            }
        }
        return nbLac >= 2;
    }
    
    /**
     * Retourne la grille générée.
     *
     * @return la grille générée
     */
    public Grille getGrille() {
        return this.grille;
    }
}