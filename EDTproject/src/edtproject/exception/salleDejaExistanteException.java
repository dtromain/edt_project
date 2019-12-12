package edtproject.exception;

/**
 * La classe salleDejaExistanteException permet de gérer les exceptions 
 * lors de la création d'une salle déja existant.
 */
public class salleDejaExistanteException extends Exception {
    
    /**
     * Constructeur par défaut de la classe salleDejaExistanteException.
     */
    public salleDejaExistanteException()
    {
        super("La salle existé déja !");
    }
    
    /**
     * Constructeur de la classe salleDejaExistanteException.
     * 
     * @see java.lang.String
     * 
     * @param msg Message à afficher
     */
    public salleDejaExistanteException(String msg)
    {
        super(msg);
    }
    
}
