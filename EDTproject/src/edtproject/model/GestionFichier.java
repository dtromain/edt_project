package edtproject.model;

import edtproject.CodePrincipal.Cours;
import edtproject.CodePrincipal.CreneauHoraire;
import edtproject.CodePrincipal.Etudiant;
import edtproject.CodePrincipal.Groupe;
import edtproject.CodePrincipal.Module;
import edtproject.CodePrincipal.Professeur;
import edtproject.CodePrincipal.Salle;
import edtproject.CodePrincipal.Session;
import edtproject.CodePrincipal.TypeCours;
import edtproject.CodePrincipal.TypeSalle;
import edtproject.CodePrincipal.TypeUtilisateur;
import edtproject.EDTproject;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;

/**
 * La classe GestionFichier permet de sauvegarder et d'enregistrer les données 
 * nécessaires au fonctionnement du gestionnaire d'emploi du temps dans un 
 * fichier.
 */
public class GestionFichier 
{
    /**
     * Cette méthode permet de sauvegarder les cours affectés et non affectés 
     * dans le fichier passé en paramètre.
     * 
     * @see java.io.FileNotFoundException
     * @see java.io.FileWriter
     * @see java.util.logging.Level
     * @see java.util.logging.Logger
     * @see java.io.IOException
     * @see java.io.File
     * @see Cours
     * 
     * 
     * @param fichier Fichier dans lequel enregistrer les cours
     * 
     * @throws FileNotFoundException
     */
    public void sauvegarderCours(File fichier) throws IOException
    {
        try(FileWriter fw = new FileWriter(fichier))
        {
            for(Cours c : EDTproject.getCoursList().getCoursNonAffectes())
            {
                fw.write("coursNonAffecte;"
                        +c.getDuree()+';'
                        +c.getType().toString()+';'
                        +c.getModule().getNom()+';'+c.getSemaine()+'\n');
            }
            for(Cours c : EDTproject.getCoursList().getCoursAffectes())
            {
                fw.write("coursAffecte;"
                            +c.getDuree()+';'
                            +c.getType().toString()+';'
                            +c.getModule().getNom()+';'
                            +c.getSalle().getNom()+';'
                            +c.getProfesseur().getInitiales()+';'
                            +c.getCreneau().toString()+';'
                            +c.getGroupe().getNom()+'\n');
            }
            fw.close();
        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(GestionFichier.class.getName()).log(
                    Level.SEVERE, "Impossibilité de sauvegarder", ex);
        }
    }
    
    /**
     * Cette méthode permet de sauvegarder les dépendances dans le fichier 
     * passé en paramètre.
     * 
     * @see java.io.FileNotFoundException
     * @see java.io.FileWriter
     * @see java.util.logging.Level
     * @see java.util.logging.Logger
     * @see java.io.IOException
     * @see java.io.File
     * @see Salle
     * @see Module
     * @see Groupe
     * @see Professeur
     * @see Etudiant
     * @see Session
     * 
     * @param fichier
     * @throws FileNotFoundException
     */
    public void sauvegarderDependances(File fichier) throws IOException
    {
        try(FileWriter fw = new FileWriter(fichier))
        {
            //Enregistrement des salles
            for(Salle s : EDTproject.getSalles())
            {
                fw.write("salle;"
                        +s.getNom()
                        +'\n');
            }
            
            //Enregistrement des modules
            for(Module m : EDTproject.getModules())
            {
                fw.write("module;"
                        +m.getNom()+';'
                        +m.getCouleur().toString()
                        +'\n');
            }
            
            //Enregistrement des groupes
            for(Groupe g : EDTproject.getGroupes())
            {
                fw.write("groupe;"
                        +g.getNom()
                        +'\n');
            }
            
            //Enregistrement des professeurs
            for(Professeur p : EDTproject.getProfesseurs())
            {
                fw.write("professeur;"
                        +p.getPrenom()+';'
                        +p.getNom()+';'
                        +p.getIdentifiant()
                        +'\n');
            }
            
            //Enregistrement des étudiants
            for(Etudiant e : EDTproject.getEtudiants())
            {
                fw.write("etudiant;"
                        +e.getGroupeTP()+';'
                        +e.getPrenom()+';'
                        +e.getNom()+';'
                        +e.getIdentifiant()
                        +'\n');
            }
            
            for(Session s : EDTproject.getSessions())
            {
                fw.write("session;"
                        +s.getIdentifiant()+';'
                        +s.getMotDePasse()+';'
                        +s.getTypeUser().toString()
                        +'\n');
            }
            
            fw.close();     //Fermeture du fichier
        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(GestionFichier.class.getName()).log(
                    Level.SEVERE, "Impossibilité de sauvegarder", ex);
        }
    }
    
    /**
     *
     * @param fichier
     * @throws ParseException
     * @throws FileNotFoundException
     */
    
    public void chargerCours(File fichier) 
            throws ParseException, FileNotFoundException
    {
        EDTproject.getCoursList().resetListCours();
        try(Scanner sc = new Scanner(fichier))
        {
            while(sc.hasNextLine())
            {
                String[] champ = sc.nextLine().split(";");
                
                if(champ[0].equals("coursAffectes"))
                {
                    Module module = null;
                    Professeur professeur = null;
                    for(Module m: EDTproject.getModules())
                    {
                        if(m.getNom().equals(champ[3]))
                        {
                            module = m;
                        }
                    }
                    for(Professeur p : EDTproject.getProfesseurs())
                    {
                        if(p.getInitiales().equals(champ[4]))
                        {
                            professeur = p;
                        }
                    }
                    
                    Calendar debut = Calendar.getInstance();
                    SimpleDateFormat sdf = 
                            new SimpleDateFormat("yyyy-MM-dd-HH-mm");
                    debut.setTime(sdf.parse(champ[7]));
                    Calendar fin = Calendar.getInstance();
                    fin.setTime(sdf.parse(champ[8]));
                    
                    CreneauHoraire creneau = new CreneauHoraire(debut,fin);
                    
                    Groupe groupe = null;
                    for(Groupe g : EDTproject.getGroupes())
                    {
                        if(g.getNom().equals(champ[6]))
                        {
                            groupe = g;
                        }
                    }
                    Salle salle = null;
                    for(Salle s : EDTproject.getSalles())
                    {
                        if(s.getNom().equals(champ[5]))
                        {
                            salle = s;
                        }
                    }
                    EDTproject.getCoursList().getCoursAffectes().add(
                            new Cours(Integer.valueOf(champ[1]),
                                    TypeCours.valueOf(champ[2]), 
                                    module, salle, professeur, debut, 
                                    groupe, Integer.valueOf(champ[8])));
                }
                else if(champ[0].equals("coursNonAffectes"))
                {
                    Module module = null;
                    for(Module m: EDTproject.getModules())
                    {
                        if(m.getNom().equals(champ[3]))
                        {
                            module = m;
                        }
                    }
               
                    EDTproject.getCoursList().ajouterCoursNonAffecte(
                            Integer.valueOf(champ[1]), 
                                    TypeCours.valueOf(champ[2]), 
                                    module, Integer.valueOf(champ[8]));
                }
            }
        }
        catch(FileNotFoundException ex)
        {
            Logger.getLogger(GestionFichier.class.getName()).log(
                    Level.SEVERE, "Impossibilité de charger le fichier", ex);
        }
        
    }
    /*

    /**
     *
     * @param fichier
     * @throws FileNotFoundException
     */
    
    public void chargerDependances(File fichier) throws FileNotFoundException
    {
        EDTproject.resetListDependances();
        try(Scanner sc = new Scanner(fichier))
        {
            while(sc.hasNextLine())
            {
                String[] champ = sc.nextLine().split(";");
                
                switch(champ[0])
                {
                    case "salle" : new Salle(champ[1],TypeSalle.valueOf(champ[2]));
                                    break;
                    case "groupe" : new Groupe(champ[1], Integer.valueOf(champ[2]));
                                    break;
                    case "professeur" : new Professeur(champ[1], champ[2], champ[3]);
                                    break;
                    case "module" : new Module(champ[1],Color.valueOf(champ[2]));
                                    break;
                    case "etudiant" : Groupe groupe = null;
                        for(Groupe g : EDTproject.getGroupes())
                                        {
                                            if(g.getNom().equals(champ[1]))
                                            {
                                                groupe = g;
                                            }
                                        }
                        new Etudiant(groupe,champ[2],champ[3], champ[4]);
                        break;
                    case "session" : new Session(champ[1],champ[2],
                                    TypeUtilisateur.valueOf(champ[3]));
                }
            }
        }
        catch(FileNotFoundException ex)
        {
            Logger.getLogger(GestionFichier.class.getName()).log(
                    Level.SEVERE, "Impossibilité de charger le fichier", ex);
        }
    }
    
}
