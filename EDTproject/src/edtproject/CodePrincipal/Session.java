package edtproject.CodePrincipal;

import edtproject.EDTproject;

/**
 * La classe Session permet de gérer les sessions des utilisateurs du système.
 * Un utilisateur possède un identifiant, un mot de passe et un type d'
 * utilisateur définit par l'énnulmération TypeUser.
 */
public class Session 
{
    private String identifiant;
    private String motDePasse;
    private TypeUtilisateur typeUser;

    /**
     * Constructeur de la classe Session.
     * 
     * @param identifiant 
     * @param motDePasse Mot de passe de l'utilisateur
     * @param typeUser Type de l'utilisateur
     */
    public Session(String identifiant, String motDePasse, TypeUtilisateur typeUser) 
    {
        this.identifiant = identifiant;
        this.motDePasse = motDePasse;
        this.typeUser = typeUser;
        EDTproject.ajouterSession(this);
    }
    
    /**
     * Constructeur de la classe Session avec génération du mot de passe "123" 
     * par défaut.
     * 
     * @param identifiant
     * @param typeUser
     */
    public Session(String identifiant, TypeUtilisateur typeUser) 
    {
        this.identifiant = identifiant;
        if(typeUser.equals(TypeUtilisateur.ELEVE))
        {
            this.motDePasse = "123";
        }
        else if(typeUser.equals(TypeUtilisateur.PROFESSEUR))
        {
            this.motDePasse = "123";
        }
        else
        {
            this.motDePasse = "123";
        }
        this.typeUser = typeUser;
        EDTproject.ajouterSession(this);
    }

    /**
     * Cette méthode retourne une chaine de caractères de type String 
     * correspondant à l'identifiant de l'utilisateur.
     * 
     * @see java.lang.String
     * 
     * @return String
     */
    public String getIdentifiant() {
        return identifiant;
    }

    /**
     * Cette méthode retourne une chaine de caractères de type String 
     * correspondant au mot de passe de l'utilisateur.
     * 
     * @see java.lang.String
     * 
     * @return String
     */
    public String getMotDePasse() {
        return motDePasse;
    }

    /**
     * Cette méthode modifie le mot de passe de l'utilisateur et remplace 
     * celui-ci par la chaine de caractères passée en paramètre.
     * 
     * @see Groupe
     * @see java.lang.String
     * 
     * @param motDePasse Nouveau mot de passe de l'utilisateur
     */
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    /**
     * Cette méthode retourne un objet de type TypeUtilisateur correspondant 
     * au type de l'utilisateur actuel.
     * 
     * @see TypeUtilisateur
     * 
     * @return TypeUtilisateur
     */
    public TypeUtilisateur getTypeUser() {
        return typeUser;
    }
    
    
}
