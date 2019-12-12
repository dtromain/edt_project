package edtproject.CodePrincipal;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * La classe Cours sers à lier les classes TypeCours, Module, Salle, 
 * Professeur, CreneauHoraire et Groupe, ce qui permet de creer un cours.
 * Un cours, possède donc une durée, un type, un module, une salle, 
 * un professeur, un créneau horaire, un groupe et une semaine.
 */
public class Cours {
    
    private int duree; 
    private TypeCours type;
    private Module module;
    private Salle salle;
    private Professeur professeur;
    private CreneauHoraire creneau;
    private Groupe groupe;
    private int semaine;
    
    /**
     * Constructeur de la classe Cours.
     * 
     * @param duree La durée du cours (durée en minutes)
     * @param type Le type du cours (COURS, TD, TP)
     * @param module Le module enseigné lors du cours
     * @param salle La salle ou se déroule le cours
     * @param professeur Le professeur enseignant le cours
     * @param debutCours
     * @param groupe Le groupe assistant au cours
     * @param semaine Le numéro de la semaine de l'année civile durant laquelle se déroule le cours
     * 
     * @see TypeCours
     * @see Module
     * @see Salle
     * @see Professeur
     * @see Groupe 
     */
    public Cours(int duree, TypeCours type, Module module, Salle salle, 
            Professeur professeur, Calendar debutCours , Groupe groupe, 
            int semaine)
    {
        if(debutCours!=null)
        {
            Calendar finCours = (Calendar) debutCours.clone();
            finCours.add(Calendar.HOUR_OF_DAY, duree/60);
            finCours.add(Calendar.MINUTE, duree%60);
            this.creneau    = new CreneauHoraire(debutCours, finCours);
        }
        else
        {
            this.creneau    = null;
        }
        
        this.duree          = duree;
        this.type           = type;
        this.module         = module;
        this.professeur     = professeur;
        this.salle          = salle;
        this.groupe         = groupe;
        this.semaine        = semaine;
    }
    
    /**
     * Retourne l'attribut de type Module correspondant au module enseigné 
     * lors du cours.
     * 
     * @see Module
     * 
     * @return Module
     */
    public Module getModule() 
    {
        return module;
    }
    
    /**
     * Retourne l'attribut de type int correspondant à la durée du cours.
     * 
     * @return int
     */
    public int getDuree() 
    {
        return duree;
    }

    /**
     * Retourne l'attribut de type TypeCours correspondant au type du cours.
     * 
     * @see TypeCours
     * 
     * @return TypeCours
     */
    public TypeCours getType() 
    {
        return type;
    }
    
    /**
     * Retourne l'attribut de type Salle correspondant à la salle ou se 
     * déroule le cours.
     * 
     * @see Salle
     * 
     * @return Salle
     */
    public Salle getSalle()
    {
        return salle;
    }

    /**
     * Retourne l'attribut de type Professeur correspondant au professeur 
     * enseignant le cours.
     * 
     * @see Professeur
     * 
     * @return Professeur
     */
    public Professeur getProfesseur() 
    {
        return professeur;
    }

    /**
     * Retourne l'attribut de type CreneauHoraire correspondant au créneau 
     * horaire de la journée durant lequel se déroule le cours.
     * 
     * @see CreneauHoraire
     * 
     * @return CreneauHoraire
     */
    public CreneauHoraire getCreneau()
    {
        return creneau;
    }

    /**
     * Retourne l'attribut de type Groupe correspondant au groupe assistant 
     * au cours.
     * 
     * @see Groupe
     * 
     * @return Groupe
     */
    public Groupe getGroupe() 
    {
        return groupe;
    }    
    
    /**
     * Retourne l'attribut de type int correspondant au noméro de la semaine 
     * de l'année civile durant laquelle se déroule le cours.
     * 
     * @return int
     */
    public int getSemaine() 
    {
        return semaine;
    } 
    
    /**
     * Retourne une chaine de caractères de type String contenant la valeur 
     * de tout les attributs du cours.
     * 
     * @see String
     * @see java.text.SimpleDateFormat
     * 
     * @return String
     */
    @Override
    public String toString()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        
        return( module.getNom() + ", "  +
                Integer.toString(duree) + ": " +
                type + " en "  +
                salle.getNom() + " avec " +
                professeur.getInitiales()+ " pour " +
                groupe.getNom() + ", horaires: "     +
                dateFormat.format(creneau.getCalendarDebut().getTime()) + " - " +
                dateFormat.format(creneau.getCalendarFin().getTime()));
    }
    
    /**
     * Retourne une chaine de caractères de type String contenant la valeur 
     * des attributs module, type, salle, professeur et groupe pour afficher 
     * le détail du cours dans l'emploi du temps.
     * 
     * @see String
     * 
     * @return String
     */
    public String afficherCoursAffecte()
    {
        return( module.getNom() + ", " +
                type + ", " +
                salle.getNom()+", " +
                professeur.getInitiales()+ ", " +
                groupe.getNom());
    }
    
    /**
     * Retourne une chaine de caractères de type String contenant la valeur 
     * des attributs module, type et duree pour afficher 
     * le détail du cours non affecte dans la reserve de l'emploi du temps.
     * 
     * @see String
     * 
     * @return String
     */
    public String afficherCoursNonAffecte()
    {
        return( module.getNom() + ", " +
                type + ", " +
                Integer.toString(duree / 60) +"h"+ Integer.toString(duree % 60));
    }
}
