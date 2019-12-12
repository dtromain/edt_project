package edtproject.CodePrincipal;

/**
 * L'énumération NomJour permet de définir les noms des différents jours de la 
 * semaine ainsi que l'heure de début et l'heure de fin (en minutes) de la 7
 * journée.
 * Avant l'heure de début et après l'heure de fin de la journée, aucun cours 
 * ne peuvent être affectés.
 */
public enum NomJour {

    /**
     * Lundi
     * 
     * Début : 8h
     * Fin : 18h
     */
    LUNDI(8*60, 18*60+15),

    /**
     * Mardi
     * 
     * Début : 8h
     * Fin : 18h
     */
    MARDI(8*60, 18*60+15),

    /**
     * Mercredi
     * 
     * Début : 8h
     * Fin : 18h
     */
    MERCREDI(8*60, 18*60+15),

    /**
     * Jeudi
     * 
     * Début : 8h
     * Fin : 12h
     */
    JEUDI(8*60, 12*60+15),

    /**
     * Vendredi
     * 
     * Début : 8h
     * Fin : 18h
     */
    VENDREDI(8*60, 18*60+15);
    
    private int heureDebut;
    private int heureFin;
    
    NomJour(int heureDebut, int heureFin)
    {
        this.heureDebut = heureDebut;
        this.heureFin   = heureFin;
    }
    
    /**
     *
     * @return
     */
    public int getHeureDebutEcole()
    {
        return heureDebut;
    }
    
    /**
     *
     * @return
     */
    public int getHeureFinEcole()
    {
        return heureFin;
    }
}
