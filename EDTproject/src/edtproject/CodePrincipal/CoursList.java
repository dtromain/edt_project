package edtproject.CodePrincipal;

import edtproject.EDTproject;
import edtproject.exception.groupeNonDisponibleException;
import edtproject.exception.heureScolaireNonRespecteesException;
import edtproject.exception.professeurNonDisponibleException;
import edtproject.exception.salleNonDisponibleException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * La classe CoursList contient la liste des cours affectés et celle des cours 
 * non affectés.
 * Cette classe permet d'enregistrer et de manipuler les cours affectés et non 
 * affectés.
 */
public class CoursList {
    
    private List<Cours> coursAffectes, coursNonAffectes;

    /**
     * Constructeur de la classe CoursList.
     * 
     * @see java.util.List
     */
    public CoursList() {
        this.coursAffectes      = new ArrayList<Cours>();
        this.coursNonAffectes   = new ArrayList<Cours>();
    }
    
    /**
     * La méthode ajouterCoursNonAffecte permet d'ajouter un cours à la liste 
     * des cours non affectés en créant un nouvel objet de type cours.
     * 
     * @see Cours
     * 
     * @param duree La durée du cours
     * @param type Le type du cours
     * @param module Le module enseigné par le cours
     * @param semaine Le numéro de la semaine de l'année civile durant laquelle se déroule le cours.
     * 
     * @return Cours
     */
    public Cours ajouterCoursNonAffecte(int duree, TypeCours type, Module module, int semaine) 
    {
        Cours coursNonAffecte = new Cours(duree, type, module, null, null, null, null, semaine);
        coursNonAffectes.add(coursNonAffecte);
        return coursNonAffecte;
    }
    
    /**
     * La méthode getCoursNonAffectes renvoie la liste des cours non affectés.
     * 
     * @see Cours
     * @see java.util.List
     * 
     * @return List
     */
    public List<Cours> getCoursNonAffectes()
    {
        return coursNonAffectes;
    }
    
    /**
     * La méthode getCoursAffectes renvoie la liste des cours affectés.
     * 
     * @see Cours
     * @see java.util.List
     * 
     * @return List
     */
    public List<Cours> getCoursAffectes()
    {
        return coursAffectes;
    }
    
    /**
     * La méthode rechercherCoursAffectes renvoie la liste des cours affectés 
     * pour un module spécifique et appartenant à un semestre précis.
     * 
     * @see Cours
     * @see Module
     * @see java.util.List
     * 
     * @param module Module des cours recherchés
     * @param semestre correspond au semestre dont on veux récupérer les cours
     * 
     * @return List
     */
    public List<Cours> rechercherCoursAffectes(Module module, Semestre semestre)
    {
        List<Cours> coursARetourner = new ArrayList<>();
        for(Cours cours : coursAffectes)
        {
            if(module.equals(cours.getModule()) && semestre.getModules().contains(cours.getModule()))
            {
                coursARetourner.add(cours);
            }
        }
        return coursARetourner;
    }
    
    /**
     * La méthode rechercherCoursAffectes renvoie la liste des cours affectés 
     * pour un groupe spécifique et appartenant à un semestre précis.
     * 
     * @see Cours
     * @see Groupe
     * @see java.util.List
     * 
     * @param groupe Groupe assistant aux cours recherchés
     * @param semestre correspond au semestre dont on veux récupérer les cours
     * 
     * @return List
     */
    public List<Cours> rechercherCoursAffectes(Groupe groupe, Semestre semestre)
    {
        List<Cours> coursARetourner = new ArrayList<>();
        for(Cours cours : coursAffectes)
        {
            if(groupe.equals(cours.getGroupe()) && semestre.getModules().contains(cours.getModule()))
            {
                coursARetourner.add(cours);
            }
        }
        return coursARetourner;
    }
    
    /**
     * La méthode rechercherCoursAffectes renvoie la liste des cours affectés 
     * enseignés par un professeur spécifique et appartenant à un semestre précis.
     * 
     * @see Cours
     * @see Professeur
     * @see java.util.List
     * 
     * @param professeur Professeur enseignant les cours recherchés
     * @param semestre correspond au semestre dont on veux récupérer les cours
     * 
     * @return List
     */
    public List<Cours> rechercherCoursAffectes(Professeur professeur, Semestre semestre)
    {
        List<Cours> coursARetourner = new ArrayList<>();
        for(Cours cours : coursAffectes)
        {
            if(professeur.equals(cours.getProfesseur()) && semestre.getModules().contains(cours.getModule()))
            {
                coursARetourner.add(cours);
            }
        }
        return coursARetourner;
    }

    /**
     * La méthode rechercherCoursAffectes renvoie la liste des cours affectés se 
     * déroulant dans une salle spécifique et appartenant à un semestre précis.
     * 
     * @see Cours
     * @see Salle
     * @see java.util.List
     * 
     * @param salle Salle dans laquelle se déroulent les cours recherchés
     * @param semestre correspond au semestre dont on veux récupérer les cours
     * 
     * @return List
     */
    public List<Cours> rechercherCoursAffectes(Salle salle, Semestre semestre)
    {
        List<Cours> coursARetourner = new ArrayList<>();
        for(Cours cours : coursAffectes)
        {
            if(salle.equals(cours.getSalle()) && semestre.getModules().contains(cours.getModule()))
            {
                coursARetourner.add(cours);
            }
        }
        return coursARetourner;
    }
    
    /**
     * La méthode rechercherCoursAffectes renvoie la liste de l'année/promo en cours
     * 
     * @see Cours
     * @see Salle
     * @see java.util.List
     * 
     * @param annee Représente l'année en cours récupère 1 ou 2
     * 
     * @return List
     */
    public List<Cours> rechercherCoursAffectes(int annee)
    {
        List<Cours> coursARetourner = new ArrayList<>();
        for(Cours cours : coursAffectes)
        {
            if(cours.getGroupe().getAnnee() == annee && !coursARetourner.contains(cours))
            {
                coursARetourner.add(cours);
            }
        }
        return coursARetourner;
    }
    
    /**
     * La méthode getCoursUtilisateur renvoie la liste des cours affectés 
     * spécifiques à l'utilisateur connecté et appartenant à un semestre précis.
     * 
     * @see Cours
     * @see java.util.List
     * 
     * @param semestre correspond au semestre dont on veux récupérer les cours
     * @return List
     */
    public List<Cours> getCoursUtilisateur(Semestre semestre)
    {
        List<Cours> coursARetourner = new ArrayList<>();
        Session utilisateur = EDTproject.getUserConnecte();
        
        Professeur  prof = EDTproject.getProfesseur(utilisateur.getIdentifiant());
        Etudiant    etud = EDTproject.getEtudiant(utilisateur.getIdentifiant());
        
        for(Cours cours : coursAffectes)
        {
            if(prof != null && cours.getProfesseur().equals(prof))
            {
                if(semestre.getModules().contains(cours.getModule()))
                {
                    coursARetourner.add(cours);
                }
            }
            else if(etud != null && cours.getGroupe().getEtudiants().contains(etud))
            {
                if(semestre.getModules().contains(cours.getModule()))
                {
                    coursARetourner.add(cours);
                }
            }
        }
        return coursARetourner;
    }
    
    /**
     * La méthode affecterCours permet d'affecterune valeur aux attributs salle, 
     * professeur, creneau, groupe, semaine afin de passer un cours de la liste 
     * des cours non affectés à la liste des cours affectés.
     * 
     * @throws edtproject.exception.salleNonDisponibleException
     * @throws edtproject.exception.heureScolaireNonRespecteesException
     * @throws edtproject.exception.professeurNonDisponibleException
     * @throws edtproject.exception.groupeNonDisponibleException
     * @see Cours
     * @see Salle
     * @see Cours
     * @see Professeur
     * @see CreneauHoraire
     * @see Groupe
     * 
     * @param cours Le cours à affecter
     * @param salle La salle dans lequel se déroule le cours
     * @param professeur Le professeur enseignant le cours
     * @param debutCours L'heure du début du cours
     * @param groupe Le groupe assistant au cours
     * 
     * @return boolean
     */
    public boolean affecterCours(Cours cours, Salle salle, 
            Professeur professeur, Calendar debutCours, 
            Groupe groupe) throws salleNonDisponibleException, professeurNonDisponibleException, groupeNonDisponibleException, heureScolaireNonRespecteesException
    {
        boolean salleDisponible = true;
 /*       for(Cours c : coursAffectes)
        {
            if(
                c.getSalle().equals(cours.getSalle())&&
                ((cours.getCreneau().getCalendarDebut().before(c.getCreneau().getCalendarDebut()) && cours.getCreneau().getCalendarFin().after(c.getCreneau().getCalendarDebut()))||
                (cours.getCreneau().getCalendarDebut().before(c.getCreneau().getCalendarFin()) && cours.getCreneau().getCalendarFin().after(c.getCreneau().getCalendarFin())))
              )
            {
                salleDisponible=false;
                throw new salleNonDisponibleException("La salle n'est pas disponible.");
            }
        }
       
*/        
       
        boolean caracteristiquesSalleRespectes  = true;
        boolean professeurDisponible            = true;
  /*      for(Cours c : coursAffectes)
        {
            if(
                c.getProfesseur().equals(cours.getProfesseur())&&
                ((cours.getCreneau().getCalendarDebut().before(c.getCreneau().getCalendarDebut()) && cours.getCreneau().getCalendarFin().after(c.getCreneau().getCalendarDebut()))||
                (cours.getCreneau().getCalendarDebut().before(c.getCreneau().getCalendarFin()) && cours.getCreneau().getCalendarFin().after(c.getCreneau().getCalendarFin())))
              )
            {
                professeurDisponible=false;
                throw new professeurNonDisponibleException("Le professeur n'est pas disponible.");
            }
        }
  */
      
        boolean professeurExercantModule = true;
        boolean groupeDisponible         = true;
 /*       for(Cours c : coursAffectes)
        {
            if(
                c.getGroupe().equals(cours.getGroupe())&&
                ((cours.getCreneau().getCalendarDebut().before(c.getCreneau().getCalendarDebut()) && cours.getCreneau().getCalendarFin().after(c.getCreneau().getCalendarDebut()))||
                (cours.getCreneau().getCalendarDebut().before(c.getCreneau().getCalendarFin()) && cours.getCreneau().getCalendarFin().after(c.getCreneau().getCalendarFin())))
              )
            {
                groupeDisponible=false;
                throw new groupeNonDisponibleException("Le groupe n'est pas disponible.");
            }
        }
 */
    
        boolean respectHeureScolaire = true;
  /*      if(creneau.getCalendarDebut().before(creneau.getNomJour().getHeureDebutEcole())||
           creneau.getCalendarFin().after(creneau.getNomJour().getHeureFinEcole())
          )
        {
            respectHeureScolaire = false;
            throw new heureScolaireNonRespecteesException("La salle n'est pas disponible.");
            
        }
 */
       boolean valRetour = false;
        
        if(salleDisponible&&caracteristiquesSalleRespectes&&professeurDisponible&&professeurExercantModule&&groupeDisponible&&respectHeureScolaire)
        {
            coursAffectes.add(new Cours(cours.getDuree(), cours.getType(), cours.getModule(), salle, professeur, debutCours, groupe, cours.getSemaine()));
            coursNonAffectes.remove(cours);
            valRetour=true;
        }
        
        return valRetour;
    }
      
    
    /**
     * La méthode getJour renvoie une liste contenant tous les cours se 
     * déroulant le jour passé en paramètre.
     * 
     * @see Cours 
     * @see java.util.Calendar
     * @see java.util.List
     * @see java.util.ArrayList
     * 
     * @param jour Numero du jour de l'année civile
     * 
     * @return List
     */
    public List<Cours> getJour(int jour)
    {
        
        List<Cours> journee = new ArrayList<>();
        for(Cours cours : coursAffectes)
        {
            if(cours.getCreneau().getCalendarDebut().get(Calendar.DAY_OF_YEAR) == jour)
            {
                journee.add(cours);
                System.out.println(cours.toString());
            }
        }
        return journee;
    }
    
    /**
     * La méthode getSemaine renvoie une liste contenant tous les cours se 
     * déroulant la semaine passé en paramètre.
     * 
     * @see Cours 
     * @see java.util.Calendar
     * @see java.util.List
     * @see java.util.ArrayList
     * 
     * @param semaine Numero de la semaine de l'année civile
     * 
     * @return List
     */
    public List<Cours> getSemaine(int semaine)
    {
        
        List<Cours> journee = new ArrayList<>();
        for(Cours cours : coursAffectes)
        {
            if(cours.getCreneau().getCalendarDebut().get(Calendar.WEEK_OF_YEAR) == semaine)
            {
                journee.add(cours);
                System.out.println(cours.toString());
            }
        }
        return journee;
    }
    
    /**
     * La méthode resetListCours vide le contenu des listes coursAffectes et 
     * coursNonAffectes.
     * 
     * @see java.util.ArrayList
     * 
     * @return boolean
     */
    public boolean resetListCours()
    {
        coursAffectes.clear();
        coursNonAffectes.clear();
        return !(coursAffectes.isEmpty()||coursNonAffectes.isEmpty());
    }
}
