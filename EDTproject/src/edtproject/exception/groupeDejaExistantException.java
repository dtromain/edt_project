package edtproject.exception;

/**
 * La classe groupeDejaExistantException permet de gérer les exceptions 
 * lors de la création d'un groupe déja existant.
 */
public class groupeDejaExistantException extends Exception {
    
    /**
     * Constructeur par défaut de la classe groupeDejaExistantException.
     */
    public groupeDejaExistantException()
    {
        super("Les groupe existe déja !");
    }
    
    /**
     * Constructeur de la classe groupeDejaExistantException.
     * 
     * @see java.lang.String
     * 
     * @param msg Message à afficher
     */
    public groupeDejaExistantException(String msg)
    {
        super(msg);
    }
}
