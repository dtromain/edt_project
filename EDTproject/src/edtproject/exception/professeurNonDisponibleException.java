package edtproject.exception;

/**
 * La classe professeurNonDisponibleException permet de gérer les exceptions 
 * lorsqu'un professeur n'est pas disponible (lorsqu'il as déja un cours 
 * affecté au créneau actuel).
 */
public class professeurNonDisponibleException extends Exception {

    /**
     * Constructeur par défaut de la classe professeurNonDisponibleException.
     */
    public professeurNonDisponibleException()
    {
        super("Le professeur n'est pas disponible pour ce créneau !");
    }
    
    /**
     * Constructeur de la classe professeurNonDisponibleException.
     * 
     * @see java.lang.String
     * 
     * @param msg Message à afficher
     */
    public professeurNonDisponibleException(String msg)
    {
        super(msg);
    }
}
