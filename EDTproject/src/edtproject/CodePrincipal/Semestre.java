package edtproject.CodePrincipal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javafx.scene.paint.Color;

/**
 * La classe Semestre permet de gérer les semestres dans le gestionnaire 
 * d'emploi du temps. Un semestre contient un numéro, une liste de modules 
 * enseignés tout au long de celui-ci ainsi qu'une date de début et de fin 
 * afin de savoir quand il commence et quand il termine.
 */
public class Semestre {
    
    private List<Module> modules;
    private Calendar dateDebut;
    private Calendar dateFin;
    private int numeroSemestre;
    
    /**
     * Constructeur de la classe semestre.
     * 
     * @see java.util.ArrayList
     * @see java.lang.String
     * @see java.util.Calendar
     * 
     * @param dateDebut Date de début du semestre
     * @param dateFin Date de finDate de début du semestre du semestre
     * @param numeroSemestre Numero du semestre
     */
    public Semestre(Calendar dateDebut, Calendar dateFin, int numeroSemestre)
    {
        modules             = new ArrayList<>();
        this.dateDebut      = dateDebut;
        this.dateFin        = dateFin;
        this.numeroSemestre = numeroSemestre;
    }

    /**
     * Cette méthode retourne un Calendar correspondant à la date du début du 
     * semestre.
     * 
     * @see java.util.Calendar
     * 
     * @return Calendar
     */
    public Calendar getDateDebut() {
        return dateDebut;
    }

    /**
     * Cette méthode retourne un Calendar correspondant à la date de fin du 
     * semestre.
     * 
     * @see java.util.Calendar
     * 
     * @return Calendar
     */
    public Calendar getDateFin() {
        return dateFin;
    }

    /**
     * Cette méthode retourne un numéro correspondant au numéro du semestre.
     * 
     * @return int
     */
    public int getNumero() {
        return numeroSemestre;
    }
    
    /**
     * Cette méthode permet d'ajouter un module à la liste des modules 
     * du semestre.
     * 
     * @see Module
     * @see java.lang.String
     * @see javafx.scene.paint.Color
     * 
     * @param nom Nom du module
     * @param couleur Couleur d'affichage du module
     */
    public void ajouterModule(String nom, Color couleur)
    {
        if(!modules.equals(nom))
        {
            modules.add(new Module(nom, couleur));
        }
    }
    
    /**
     * Cette méthode permet de récupérer les modules d'un semestre pour les 
     * transférer dans le semestre actuel.
     * 
     * @see Module
     * 
     * @param semestre 
     */
    public void recupererModulesSemestre(Semestre semestre)
    {
        modules = semestre.getModules();
    }
    
    /**
     * Cette méthode renvoie la liste des modules pour le semestre actuel.
     * 
     * @see Module
     * @see java.util.List
     * 
     * @return List
     */
    public List<Module> getModules()
    {
        return modules;
    }
    
    @Override
    public String toString()
    {
        return "Semestre "+numeroSemestre;
    }
}
