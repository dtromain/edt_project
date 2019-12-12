package edtproject.CodePrincipal;

/**
 * L'énumération TypeSalle permet de définir un type de salle différent pour 
 * gérer les contraintes matérielles dans le gestionnaire d'emploi du temps.
 */
public enum TypeSalle {

    /**
     * Salle isolée du réseau de l'université
     */
    RESEAU,

    /**
     * Amphithéatre pour les cours majistraux
     */
    AMPHI,

    /**
     * Salles de cours pour les TD
     */
    TD,

    /**
     * Salles machines pour les TP
     */
    TP;
}
