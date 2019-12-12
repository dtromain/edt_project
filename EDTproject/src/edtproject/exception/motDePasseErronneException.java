package edtproject.exception;

/**
 * La classe motDePasseErronneException permet de gérer les exceptions 
 * lorsqu'un utilisateur entre un mot de passe erroné.
 */
public class motDePasseErronneException extends Exception {
    
    /**
     * Constructeur par défaut de la classe motDePasseErronneException.
     */
    public motDePasseErronneException()
    {
        super("Le mot de passe est erronné !");
    }
    
    /**
     * Constructeur de la classe motDePasseErronneException.
     * 
     * @see java.lang.String
     * 
     * @param msg Message à afficher
     */
    public motDePasseErronneException(String msg)
    {
        super(msg);
    }
    
}
