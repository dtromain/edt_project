package edtproject.CodePrincipal;

import edtproject.EDTproject;
import java.util.List;


/**
 * La classe Groupe permet de gérer les groupes dans le gestionnaire d'
 * emploi du temps, elle attribue à un groupe un nom, le semestre auquel 
 * il se réfère ; un groupe possède également une liste des étudiants 
 * qu'il contient.
 */
public class Groupe {
    
    private String nom;
    private int annee;
    private List<Etudiant> etudiants;

    /**
     * Constructeur de la classe Groupe.
     * 
     * @param nom 
     * @param annee 
     */
    public Groupe(String nom, int annee)
    {
        this.nom = nom;
        this.annee = annee;
        EDTproject.ajouterGroupe(this);
    }
    
    /**
     * Cette méthode retourne une chaine de caractères de type String 
     * correspondant au nom du groupe.
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
     * Cette méthode retourne l'année à laquelle se réfère le groupe
     * 
     * @return int
     */
    public int getAnnee()
    {
        return annee;
    }
    
    /**
     * Cette méthode permet d'ajouter un étudiant à la liste des étudiants 
     * du groupe, la méthode vérifie que l'étudiant n'est pas déja dans la 
     * liste et n'est pas affecté à un autre groupe.
     * 
     * @see Etudiant
     * @see java.util.ArrayList
     * 
     * @param e Etudiant à ajouter au groupe
     */
    public void ajouterEtudiant(Etudiant e)
    {
        this.etudiants.add(e);
    }

    /**
     * Cette méthode retourne la liste des étudiants du groupe.
     * 
     * @see Etudiant
     * @see java.util.List
     * 
     * @return List
     */
    public List<Etudiant> getEtudiants() 
    {
        return etudiants;
    }
    
    
    

    /**
     * Cette méthode compare le groupe actuel avec un autre groupe dont le nom 
     * et le semestre sont passés en paramètres et retourne true si ces deux 
     * groupes sont identiques, la méthode retourne false le cas échéant.
     * 
     * @param nom Nom à comparer
     * @param annee
     * 
     * @return boolean
     */
    public boolean equals(String nom, int annee)
    {
        if (this.nom.equals(nom) && 
            this.annee == annee)
        {
            return true;
        }
        return false;       
    }

    /**
     * Cette méthode modifie le semestre du groupe par un autre semestre passé 
     * en paramètre.
     * 
     * @see Etudiant
     * @see java.util.ArrayList
     * 
     * @param e Etudiant à supprimer du groupe
     */
    void supprimerEtudiant(Etudiant e) 
    {
        this.etudiants.remove(e);
    }
}
