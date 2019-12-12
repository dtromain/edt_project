package edtproject.CodePrincipal;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * La classe CreneauHoraire permet de définir un créneau horaire entre une 
 * heure de début et une heure de fin.
 */
public class CreneauHoraire {
    
    private Calendar heureDebut;
    private Calendar heureFin;

    /**
     * Constructeur de la classe CreneauHoraire.
     * 
     * @see java.util.Calendar
     * 
     * @param heureDebut Début du créneau horaire
     * @param heureFin Fin du créneau horaire
     */
    public CreneauHoraire(Calendar heureDebut, Calendar heureFin) 
    {
        this.heureDebut = heureDebut;
        this.heureFin   = heureFin;
    }
    
    /**
     * La méthode getHeureDebut retourne l'heure de début du créneau horaire
     * en minute.
     * 
     * @see java.util.Calendar
     * 
     * @return int
     */
    public int getHeureDebut() {
        int valRetour=heureDebut.get(Calendar.HOUR_OF_DAY)*60+heureDebut.get(Calendar.MINUTE);
        return valRetour;
    }

    /**
     * La méthode getHeureFin retourne l'heure de fin du créneau horaire
     * en minute.
     * 
     * @see java.util.Calendar
     * 
     * @return int
     */
    public int getHeureFin() {
        int valRetour=heureFin.get(Calendar.HOUR_OF_DAY)*60+heureFin.get(Calendar.MINUTE);
        return valRetour;
    }
    
    /**
     * La méthode getCalendarDebut retourne un Calendar de l'heure de début du 
     * créneau horaire.
     * 
     * @see java.util.Calendar
     * 
     * @return Calendar
     */
    public Calendar getCalendarDebut() {
        return heureDebut;
    }

    /**
     * La méthode getCalendarFin retourne un Calendar de l'heure de fin du 
     * créneau horaire.
     * 
     * @see java.util.Calendar
     * 
     * @return Calendar
     */
    public Calendar getCalendarFin() {
        return heureFin;
    }

    /**
     * La méthode getNomJour retourne un NomJour correspondant au nom du jour 
     * de la semaine dans lequel se trouve le créneau horaire.
     * 
     * @see java.util.Calendar
     * @see NomJour
     * 
     * @return NomJour
     */
    public NomJour getNomJour()
    {
        NomJour valRetour = null;
        switch(heureDebut.get(Calendar.DAY_OF_WEEK))
        {
            case Calendar.MONDAY:
                valRetour=NomJour.LUNDI;
                break;
            case Calendar.TUESDAY:
                valRetour=NomJour.MARDI;
                break;
            case Calendar.WEDNESDAY:
                valRetour=NomJour.MERCREDI;
                break;
            case Calendar.THURSDAY:
                valRetour=NomJour.JEUDI;
                break;
            case Calendar.FRIDAY:
                valRetour=NomJour.VENDREDI;
                break;
            default:
                System.out.println("Erreur: jour incorrect");     
        }
        return valRetour;
    }
    
    /**
     * La méthode getJour retourne le numéro du jour de l'année civile dans 
     * lequel se trouve le créneau horaire.
     * 
     * @see java.util.Calendar
     * 
     * @return int
     */
    public int getJour()
    {
        int valRetour=heureDebut.get(Calendar.DAY_OF_YEAR);
        return valRetour;
    }
    
    /**
     * La méthode getNomMois retourne une chaine de caractères correspondant 
     * au mois dans lequel se trouve le créneau horaire.
     * 
     * @see java.util.Calendar
     * @see java.lang.String
     * 
     * @return String
     */
    public String getNomMois()
    {
        String valRetour = null;
        switch(heureDebut.get(Calendar.MONTH))
        {
            case Calendar.JANUARY:
                valRetour="Janvier";
                break;
            case Calendar.FEBRUARY:
                valRetour="Février";
                break;
            case Calendar.MARCH:
                valRetour="Mars";
                break;
            case Calendar.APRIL:
                valRetour="Avril";
                break;
            case Calendar.MAY:
                valRetour="Mai";
                break;
            case Calendar.JUNE:
                valRetour="Juin";
                break;
            case Calendar.JULY:
                valRetour="Juillet";
                break;
            case Calendar.AUGUST:
                valRetour="Août";
                break;
            case Calendar.SEPTEMBER:
                valRetour="Septembre";
                break;
            case Calendar.OCTOBER:
                valRetour="Octobre";
                break;
            case Calendar.NOVEMBER:
                valRetour="Novembre";
                break;
            case Calendar.DECEMBER:
                valRetour="Décembre";
                break;
                
            default:
                System.out.println("Erreur: mois incorrect");     
        }
        return valRetour;
    }
    
    /**
     * La méthode getSemaine retourne le numéro de la semaine de l'année civile 
     * dans laquelle se trouve le créneau horaire.
     * 
     * @see java.util.Calendar
     * 
     * @return int
     */
    public int getSemaine()
    {
        int valRetour=heureDebut.get(Calendar.WEEK_OF_YEAR);
        return valRetour;
    }
    
    /**
     * La méthode toString une chaine de caractères de type String contenant 
     * la valeur du début et de la fin du créneau horaire sous la forme suivante
     * yyyy-MM-dd-HH-mm avec :
     * 
     * yyyy : Numéro de l'année
     * MM : Numéro du mois
     * dd : Numéro du jour
     * HH : Heures
     * mm : Minutes
     * 
     * @see String
     * @see java.text.SimpleDateFormat
     * 
     * @return String
     */
    @Override
    public String toString()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
        return dateFormat.format(heureDebut.getTime())+';'+dateFormat.format(heureDebut.getTime());
    }
    
    /**
     * Retourne une chaine de caractères de type String correspondant à l'heure 
     * du Calendar passée en paramètre sous la forme HHh00 avec :
     * 
     * HH : Heures
     * 
     * @see java.util.String
     * 
     * @param heure Calendar à convertir en String 
     * 
     * @return String
     */
    public String afficher(Calendar heure)
    {
        String valRetour=heure.get(Calendar.HOUR_OF_DAY)+"h";
        if(heure.get(Calendar.MINUTE)!=0)
        {
            valRetour+=heure.get(Calendar.MINUTE);
        }
        else
        {
            valRetour+="00";
        }
        return valRetour;
    }
}
