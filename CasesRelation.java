package engine.map;

import java.util.List;

/**
 * La classe CasesRelation représente la relation entre deux cases de la grille.
 * Elle permet de vérifier l'accessibilité entre les deux cases, de récupérer le chemin
 * entre les deux cases, et de calculer la distance entre elles.
 */
public class CasesRelation {
    
    private Case caseDebut;
    private Case caseFin;
    private Grille grille;
    
    /**
     * Constructeur de la classe CasesRelation.
     *
     * @param caseDebut la case de départ
     * @param caseFin la case d'arrivée
     * @param grille la grille sur laquelle les cases sont situées
     */
    public CasesRelation(Case caseDebut, Case caseFin, Grille grille) {
        this.caseDebut = caseDebut;
        this.caseFin = caseFin;
        this.grille = grille;
    }
    
    /**
     * Vérifie si les deux cases sont accessibles l'une de l'autre.
     *
     * @return true si les cases sont accessibles, false sinon
     */
    public boolean isCasesAccessible() {
        // TODO: Implémenter la méthode pour vérifier l'accessibilité entre les deux cases
        return false;
    }
    
    /**
     * Retourne le chemin entre les deux cases.
     *
     * @return la liste des cases constituant le chemin entre les deux cases, ou null si non accessible
     */
    public List<Case> getCheminCases() {
        // TODO: Implémenter la méthode pour récupérer le chemin entre les deux cases
        return null;
    }
    
    /**
     * Calcule et retourne la distance entre les deux cases.
     *
     * @return la distance entre les deux cases
     */
    public int getDistance() {
        // TODO: Implémenter la méthode pour calculer la distance entre les deux cases
        return 0;
    }
}