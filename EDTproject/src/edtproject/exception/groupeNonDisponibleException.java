package edtproject.exception;

/**
 * La classe groupeNonDisponibleException permet de gérer les exceptions 
 * lorsqu'un groupe n'est pas disponible (lorsqu'il as déja un cours affecté au 
 * créneau actuel).
 */
public class groupeNonDisponibleException extends Exception {
    
    /**
     * Constructeur par défaut de la classe groupeNonDisponibleException.
     */
    public groupeNonDisponibleException()
    {
        super("Le groupe n'est pas disponible pour ce créneau !");
    }
    
    /**
     * Constructeur de la classe groupeNonDisponibleException.
     * 
     * @see java.lang.String
     * 
     * @param msg Message à afficher
     */
    public groupeNonDisponibleException(String msg)
    {
        super(msg);
    }
    
}
