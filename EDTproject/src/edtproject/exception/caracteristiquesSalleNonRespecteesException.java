package edtproject.exception;

/**
 * La classe caracteristiquesSalleNonRespecteesException permet de gérer les exceptions 
 * lors de l'affectation des salles aux cours.
 */
public class caracteristiquesSalleNonRespecteesException extends Exception {

    /**
     * Constructeur par défaut de la classe caracteristiquesSalleNonRespecteesException.
     */
    public caracteristiquesSalleNonRespecteesException()
    {
        super("Les caractéristiques de la salle ne sont pas respectées !");
    }
    
    /**
     * Constructeur de la classe caracteristiquesSalleNonRespecteesException.
     * 
     * @see java.lang.String
     * 
     * @param msg Message à afficher
     */
    public caracteristiquesSalleNonRespecteesException(String msg)
    {
        super(msg);
    }
}
