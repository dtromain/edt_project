package edtproject.CodePrincipal;

import edtproject.EDTproject;

/**
 * La classe Professeur permet de gérer les professeurs dans le gestionnaire d'
 * emploi du temps, elle attribue à un professeur un identifiant en plus de son 
 * nom et prenom. La classe attribue également un libellé correspondant aux 
 * initiales des professeurs.
 */
public class Professeur {
    private String prenom;
    private String nom;
    private String identifiant;
    private String initiales;

    /**
     * Constructeur de la classe Professeur
     * 
     * @see java.lang.String
     * 
     * @param prenom Prenom du professeur
     * @param nom Nom du professeur
     * @param identifiant Identifiant du professeur
     */
    public Professeur(String prenom, String nom, String identifiant) 
    {
        this.prenom  = prenom;
        this.nom     = nom;
        this.identifiant = identifiant;
        this.initiales = prenom.charAt(0)+nom.substring(0, 2);             //Trois caractères correspondants aux initiales des professeurs.
        EDTproject.ajouterProfesseur(this);
        EDTproject.ajouterSession(new Session(identifiant, TypeUtilisateur.PROFESSEUR));
    }

    /**
     * Cette méthode retourne une chaine de caractères de type String 
     * correspondant au prenom du professeur.
     * 
     * @see java.lang.String
     * 
     * @return String
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Cette méthode retourne une chaine de caractères de type String 
     * correspondant au nom du professeur.
     * 
     * @see java.lang.String
     * 
     * @return String
     */
    public String getNom() {
        return nom;
    }
    
    /**
     * Cette méthode modifie les initiales du professeur par une chaine de 
     * caractères passée en paramètre.
     * 
     * @see java.lang.String
     * 
     * @param initiales Nouvelles initiales du professeur
     */
    public void setInitiales(String initiales) 
    {
        this.initiales = initiales;
    }
    
    /**
     * Cette méthode retourne une chaine de caractères de type String 
     * correspondant aux initiales du professeur.
     * 
     * @see java.lang.String
     * 
     * @return String
     */
    public String getInitiales() 
    {
        return initiales;
    }

    /**
     * Cette méthode retourne une chaine de caractères de type String 
     * correspondant à l'identifiant du professeur.
     * 
     * @see java.lang.String
     * 
     * @return String
     */
    public String getIdentifiant() {
        return identifiant;
    }
    
}
