package edtproject.exception;

/**
 * La classe heureScolaireNonRespecteesException permet de gérer les exceptions 
 * lorsque les horaires ne sont pas respectés à l'affectation des créneaux aux 
 * cours.
 */
public class heureScolaireNonRespecteesException extends Exception {
    
    /**
     * Constructeur par défaut de la classe heureScolaireNonRespecteesException.
     */
    public heureScolaireNonRespecteesException()
    {
        super("Les horaires n'ont pas été respectées !");
    }
    
    /**
     * Constructeur de la classe heureScolaireNonRespecteesException.
     * 
     * @see java.lang.String
     * 
     * @param msg Message à afficher
     */
    public heureScolaireNonRespecteesException(String msg)
    {
        super(msg);
    }
    
}
