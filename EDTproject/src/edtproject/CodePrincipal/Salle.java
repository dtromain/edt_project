package edtproject.CodePrincipal;

import edtproject.EDTproject;

/**
 * La classe Salle permet de gérer les salles dans le gestionnaire d'emploi du 
 * temps.
 */
public class Salle {
    private String nom;
    private TypeSalle type;
    /**
     * Constructeur de la classe Salle.
     * 
     * @see java.lang.String
     * 
     * @param nom Nom de la salle
     */
    public Salle(String nom, TypeSalle type) 
    {
        this.nom = nom;
        this.type = type;
        EDTproject.ajouterSalle(this);
    }

    /**
     * Cette méthode retourne une chaine de caractères de type String 
     * correspondant au nom de la salle.
     * 
     * @see java.lang.String
     * 
     * @return String
     */
    public String getNom()
    {
        return nom;
    }
    
    /**
     * Cette méthode retourne les caractéristiques de la salle via l'enum TypeSalle.
     * 
     * @see TypeSalle
     * 
     * @return TypeSalle
     */
    public TypeSalle getTypeSalle() 
    {
        return type;
    }
}
