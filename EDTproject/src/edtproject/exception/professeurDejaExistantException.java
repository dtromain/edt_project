package edtproject.exception;

/**
 * La classe professeurDejaExistantException permet de gérer les exceptions 
 * lors de la tentative de création d'un professeur déja existant.
 */
public class professeurDejaExistantException extends Exception {
    
    /**
     * Constructeur par défaut de la classe professeurDejaExistantException.
     */
    public professeurDejaExistantException()
    {
        super("Le professeur existe déja !");
    }
    
    /**
     * Constructeur de la classe professeurDejaExistantException.
     * 
     * @see java.lang.String
     * 
     * @param msg Message à afficher
     */
    public professeurDejaExistantException(String msg)
    {
        super(msg);
    }
    
}
