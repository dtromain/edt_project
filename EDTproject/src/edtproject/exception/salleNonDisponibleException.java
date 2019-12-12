package edtproject.exception;

/**
 * La classe salleNonDisponibleException permet de gérer les exceptions 
 * lorsqu'une salle n'est pas disponible (lorsqu'il as déja un cours 
 * affecté au créneau actuel).
 */
public class salleNonDisponibleException extends Exception {
    
    /**
     * Constructeur par défaut de la classe salleNonDisponibleException.
     */
    public salleNonDisponibleException()
    {
        super("La salle n'est pas disponible pour ce créneau !");
    }
    
    /**
     * Constructeur de la classe salleNonDisponibleException.
     * 
     * @see java.lang.String
     * 
     * @param msg Message à afficher
     */
    public salleNonDisponibleException(String msg)
    {
        super(msg);
    }
}
