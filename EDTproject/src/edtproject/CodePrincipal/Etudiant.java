package edtproject.CodePrincipal;

import edtproject.EDTproject;

/**
 * La classe Etudiant permet de gérer les étudiants dans le gestionnaire d'
 * emploi du temps, elle attribue à un étudiant un identifiant en plus de son 
 * nom et prenom.
 */
public class Etudiant 
{
    private Groupe groupeTP;
    private String prenom;
    private String nom;
    private String identifiant;

    /**
     * Constructeur de la classe Etudiant.
     * 
     * @param groupeTP
     * @param prenom
     * @param nom
     * @param identifiant
     * 
     * @see Groupe
     * @see java.lang.String
     */
    public Etudiant(Groupe groupeTP, String prenom, String nom, String identifiant) 
    {
        this.groupeTP = groupeTP;
        this.prenom = prenom;
        this.nom = nom;
        this.identifiant = identifiant;
        EDTproject.ajouterEtudiant(this);
    }

    /**
     * Cette méthode modifie le groupe de l'étudiant, supprime l'étudiant de 
     * la liste des étudiants de son ancien groupe, et l'ajoute à la liste 
     * des étudiants de son nouveau groupe.
     * 
     * @see Groupe
     * 
     * @param groupeTP
     */
    public void setGroupeTP(Groupe g) 
    {
        this.groupeTP.supprimerEtudiant(this);
        this.groupeTP = g;
        g.ajouterEtudiant(this);
    }

    /**
     * Cette méthode retourne le Groupe de l'étudiant.
     * 
     * @see Groupe
     * 
     * @return Groupe
     */
    public Groupe getGroupeTP() 
    {
        return groupeTP;
    }

    /**
     * Cette méthode retourne une chaine de caractères de type String 
     * correspondant au prenom de l'étudiant.
     * 
     * @see java.lang.String
     * 
     * @return String
     */
    public String getPrenom() 
    {
        return prenom;
    }

    /**
     * Cette méthode retourne une chaine de caractères de type String 
     * correspondant au nom de l'étudiant.
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
     * Cette méthode retourne une chaine de caractères de type String 
     * correspondant à l'identifiant de l'étudiant.
     * 
     * @return int
     */
    public String getIdentifiant() 
    {
        return identifiant;
    }
    
    /**
     * Cette méthode compare l'étudiant actuel avec un autre étudiant passé en 
     * paramètre et retourne true si ces deux étudiants sont identiques, 
     * la méthode retourne false le cas échéant.
     * 
     * @see java.lang.String
     * @see Groupe
     * 
     * @param e Etudiant à comparer
     * 
     * @return boolean
     */
    public boolean equals(Etudiant e)
    {
        if(this.prenom.equals(e.getPrenom()) && this.nom.equals(e.getNom()) &&
          this.identifiant.equals(e.getIdentifiant()))
        {
            return true;
        }
        return false;
    }
    
    /**
     * Cette méthode retourne true si l'étudiant est affecté à un groupe, 
     * la méthode retourne false le cas échéant.
     * 
     * @see Groupe
     * 
     * @return boolean
     */
    public boolean estAffecteAUnGroupe()
    {
        if(this.groupeTP != null)
        {
            return true;
        }
        return false;
    }
}
