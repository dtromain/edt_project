/*
 * Classe permettant de tester les exceptions et de les lever si besoin
 */
package edtproject.exception;

import edtproject.CodePrincipal.Cours;
import edtproject.CodePrincipal.Groupe;
import edtproject.CodePrincipal.Professeur;
import edtproject.CodePrincipal.Salle;
import edtproject.EDTproject;
import java.util.Calendar;

/**
 *
 * @author Michaël
 */
public class TestException 
{
    public static void testSalleDisponible(Salle salle, Calendar dateDebut, int duree) throws salleNonDisponibleException
    {
        for(Cours c : EDTproject.getCoursList().getCoursAffectes())
        {
            if(c.getSalle().equals(salle))
            {
                Calendar dateFin = (Calendar)dateDebut.clone();
                dateFin.add(Calendar.MINUTE, duree);
                
                if((c.getCreneau().getCalendarDebut().after(dateDebut) && 
                    c.getCreneau().getCalendarDebut().before(dateFin)) ||
                   (c.getCreneau().getCalendarFin().after(dateDebut)  &&
                    c.getCreneau().getCalendarFin().before(dateFin)))
                {
                    throw new salleNonDisponibleException();
                }
            }
        }
    }
    
    public static void testProfesseurDisponible(Professeur professeur, Calendar dateDebut, int duree) throws professeurNonDisponibleException
    {
        for(Cours c : EDTproject.getCoursList().getCoursAffectes())
        {
            if(c.getProfesseur().equals(professeur))
            {
                Calendar dateFin = (Calendar)dateDebut.clone();
                dateFin.add(Calendar.MINUTE, duree);
                
                if((c.getCreneau().getCalendarDebut().after(dateDebut) && 
                    c.getCreneau().getCalendarDebut().before(dateFin)) ||
                   (c.getCreneau().getCalendarFin().after(dateDebut)  &&
                    c.getCreneau().getCalendarFin().before(dateFin)))
                {
                    throw new professeurNonDisponibleException();
                }
            }
        }
    }
    
    public static void testGroupeDisponible(Groupe groupe, Calendar dateDebut, int duree) throws groupeNonDisponibleException
    {
        for(Cours c : EDTproject.getCoursList().getCoursAffectes())
        {
            Calendar dateFin = (Calendar)dateDebut.clone();
            dateFin.add(Calendar.MINUTE, duree);
            if(groupe.getNom().length() > 2)
            {
                if(c.getGroupe().getAnnee() == groupe.getAnnee())
                {
                    if((c.getCreneau().getCalendarDebut().after(dateDebut) && 
                    c.getCreneau().getCalendarDebut().before(dateFin)) ||
                   (c.getCreneau().getCalendarFin().after(dateDebut)  &&
                    c.getCreneau().getCalendarFin().before(dateFin)))
                    {
                        throw new groupeNonDisponibleException();
                    }
                }
            }
            else if(groupe.getNom().length() == 2)
            {
                if(c.getGroupe().getNom().charAt(0) == groupe.getNom().charAt(0))
                {
                    if((c.getCreneau().getCalendarDebut().after(dateDebut) && 
                    c.getCreneau().getCalendarDebut().before(dateFin)) ||
                   (c.getCreneau().getCalendarFin().after(dateDebut)  &&
                    c.getCreneau().getCalendarFin().before(dateFin)))
                    {
                        throw new groupeNonDisponibleException();
                    }
                }
            }
            else
            {
                if(c.getGroupe().getNom().equals(groupe.getNom()))
                {
                    if((c.getCreneau().getCalendarDebut().after(dateDebut) && 
                    c.getCreneau().getCalendarDebut().before(dateFin)) ||
                   (c.getCreneau().getCalendarFin().after(dateDebut)  &&
                    c.getCreneau().getCalendarFin().before(dateFin)))
                    {
                        throw new groupeNonDisponibleException();
                    }
                }
            }
        }
    }
    
    
}