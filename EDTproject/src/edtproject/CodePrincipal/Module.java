package edtproject.CodePrincipal;

import javafx.scene.paint.Color;
import edtproject.EDTproject;

/**
 * La classe Module permet de gérer les modules correspondants aux matières 
 * scolaires dans l'emploi du temps. Un module possède un nom puis une couleur 
 * pour l'affichage de celui-ci dans l'interface graphique.
 */
public class Module {
    private String nom;
    private Color couleur;
    //private int heureCours;
    //private int heureTD;
    //private int heureTP;
    

    /**
     * Constructeur de la classe Module.
     * 
     * @see javafx.scene.paint.Color
     * 
     * @param nom Couleur du module
     * @param couleur Couleur du module
     */
    public Module(String nom, Color couleur) {
        this.nom = nom;
        this.couleur = couleur;
        EDTproject.ajouterModule(this);
    }

    /**
     * Cette méthode retourne une chaine de caractères de type String 
     * correspondant au nom du module.
     * 
     * @see java.lang.String
     * 
     * @return String
     */
    public String getNom() {
        return nom;
    }
    
    /**
     * Cette méthode retourne une couleur de type Color correspondant à 
     * la couleur de l'affichage du module dans l'interface graphique.
     * 
     * @see javafx.scene.paint.Color
     * 
     * @return Color
     */
    public Color getCouleur()
    {
        return couleur;
    }
    
    /**
     * Cette méthode permet de modifier la couleur de l'affichage du module.
     * 
     * @see javafx.scene.paint.Color
     * 
     * @param couleur Couleur de l'affichage du module
     */
    public void setCouleur(Color couleur)
    {
        this.couleur=couleur;
    }
    
    /**
     * Cette méthode compare le module actuel avec un autre module dont le nom 
     * est passé en paramètres et retourne true si ceux-ci sont identiques, la 
     * méthode retourne false le cas échéant.
     * 
     * @param nom Nom à comparer
     * 
     * @return boolean
     */
    public boolean equals(String nom)
    {
        if(this.nom.equals(nom))
        {
            return true;
        }
        return false;
    }
}
