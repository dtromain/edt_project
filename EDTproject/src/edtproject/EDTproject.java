package edtproject;

import edtproject.CodePrincipal.Cours;
import edtproject.CodePrincipal.CoursList;
import edtproject.CodePrincipal.Etudiant;
import edtproject.CodePrincipal.Groupe;
import edtproject.CodePrincipal.Module;
import edtproject.CodePrincipal.Professeur;
import edtproject.CodePrincipal.Salle;
import edtproject.CodePrincipal.Semestre;
import edtproject.CodePrincipal.Session;
import edtproject.CodePrincipal.TypeCours;
import edtproject.CodePrincipal.TypeSalle;
import edtproject.CodePrincipal.TypeUtilisateur;


import edtproject.controller.AffichageEDTController;
import edtproject.controller.MenuPrincipalController;
import edtproject.exception.groupeNonDisponibleException;
import edtproject.exception.heureScolaireNonRespecteesException;
import edtproject.exception.professeurNonDisponibleException;
import edtproject.exception.salleNonDisponibleException;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.prefs.Preferences;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * La classe EDTProject est la classe principale du gestionnaire d'emploi 
 * du temps.
 * Cette classe contient les listes permettant d'instancier tous les objets
 * nécessaires au fonctionnement du gestionnaire d'emploi du temps.
 * Cette classe permet d'enregistrer et de manipuler les objets enregistrés.
 */
public class EDTproject extends Application
{
    private CoursList cours;
    private Stage primaryStage;
    
    private static List<Module> modules = new ArrayList<>();
    private static List<Salle> salles = new ArrayList<>();
    private static List<Groupe> groupes = new ArrayList<>();
    private static List<Etudiant> etudiants = new ArrayList<>();
    private static List<Professeur> professeurs = new ArrayList<>();
    private static List<Session> sessions = new ArrayList<>();
    private static Session userConnecte;
    
    private static CoursList coursList = new CoursList();
    private static Semestre semestre1;
    private static Semestre semestre2;
    private static Semestre semestre3;
    private static Semestre semestre4;
    
    /**
     * La méthode getSemestre renvoie le semestre correspondant au nombre passé 
     * en paramètre et affiche une alerte si celui-ci n'existe pas.
     * 
     * @see Semestre
     * @see Alert
     * 
     * @param numero Numéro du semestre
     * 
     * @return Semestre
     */
    public static Semestre getSemestre(int numero)
    {
        Semestre valRetour=null;
        switch(numero)
        {
            case 1:     valRetour = semestre1;
                        break;
                        
            case 2:     valRetour = semestre2;
                        break;
                        
            case 3:     valRetour = semestre3;
                        break;
                        
            case 4:     valRetour = semestre4;
                        break;
                        
            default:    Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Semestre non existant");
        }
        
        return valRetour;
    }
    
    /**
     * Cette methode permet de recuperer le semestre en fonction de la date
     * 
     * @param date de type LocalDate, contient la date du semestre que l'on veut recuperer
     * @return Le semestre associe
     */
    public static int getSemestre(LocalDate date)
    {
        int valRetour;
        Calendar calDate = Calendar.getInstance();
        calDate.set(Calendar.DAY_OF_YEAR, date.getDayOfYear());
        if(calDate.after(semestre1.getDateDebut()) && calDate.before(semestre1.getDateFin()))
        {
            valRetour = 1;
        }
        else if(calDate.after(semestre2.getDateDebut()) && calDate.before(semestre2.getDateFin()))
        {
            valRetour = 2;
        }
        else
        {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setCalendar(calDate);

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Impossible d'accéder au semestre correspondant a la date du " + sdf.format(calDate.getTime()));
            alert.show();
            valRetour=0;
        }
        return valRetour;
    }
    
    /**
     * La méthode ajouterModule permet d'ajouter un module à la liste des 
     * modules du gestionnaire d'emploi du temps.
     * 
     * @see Module
     * @see java.util.ArrayList
     * 
     * @param m Le module à ajouter dans la liste modules
     */
    public static void ajouterModule(Module m)
    {
        modules.add(m);
    }

    /**
     * La méthode ajouterSalle permet d'ajouter une salle à la liste des 
     * salles du gestionnaire d'emploi du temps.
     * 
     * @see Salle
     * @see java.util.ArrayList
     * 
     * @param s La salle à ajouter dans la liste salles
     */
    public static void ajouterSalle(Salle s)
    {
        salles.add(s);
    }

    /**
     * La méthode ajouterGroupe permet d'ajouter un groupe à la liste des 
     * groupes du gestionnaire d'emploi du temps.
     * 
     * @see Groupe
     * @see java.util.ArrayList
     * 
     * @param g Le groupe à ajouter dans la liste groupes
     */
    public static void ajouterGroupe(Groupe g)
    {
        groupes.add(g);
    }

    /**
     * La méthode ajouterEtudiant permet d'ajouter un etudiant à la liste des 
     * etudiants du gestionnaire d'emploi du temps.
     * 
     * @see Etudiant
     * @see java.util.ArrayList
     * 
     * @param e L'étudiant à ajouter dans la liste étudiants
     */
    public static void ajouterEtudiant(Etudiant e)
    {
        etudiants.add(e);
    }
    
    /**
     * La méthode ajouterProfesseur permet d'ajouter un professeur à la liste 
     * des professeurs du gestionnaire d'emploi du temps.
     * 
     * @see Professeur
     * @see java.util.ArrayList
     * 
     * @param p Le professeur à ajouter dans la liste professeurs
     */
    public static void ajouterProfesseur(Professeur p)
    {
        professeurs.add(p);
    }
    
    /**
     * La méthode ajouterSession permet d'ajouter une session à la liste 
     * des sessions du gestionnaire d'emploi du temps.
     * 
     * @see Session
     * @see java.util.ArrayList
     * 
     * @param s Lea session à ajouter dans la liste sessions
     */
    public static void ajouterSession(Session s)
    {
        sessions.add(s);
    }
    
    /**
     * La méthode start permet d'initialiser puis de lancer l'interface 
     * graphique (au format FXML) du gestionnaire d'emploi du temps.
     * 
     * @see javafx.fxml.FXMLLoader
     * @see javafx.scene.Scene
     * @see javafx.scene.layout.AnchorPane
     * @see javafx.scene.paint.Color
     * @see javafx.stage.Stage
     * 
     * @param primaryStage Fenêtre principale du programme
     * 
     * @throws IOException 
     */
    @Override
    public void start(Stage primaryStage) throws IOException
    {
        if(false)
        {
            FXMLLoader leLoader = new FXMLLoader(getClass().getResource(
                    "view/AffichageEDT.fxml"));
            AnchorPane root = (AnchorPane)leLoader.load();

            AffichageEDTController leController;
            leController = leLoader.getController();

            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.show();
        }
        else
        {
            FXMLLoader leLoader = new FXMLLoader(getClass().getResource(
                    "view/MenuPrincipal.fxml"));
            AnchorPane root = (AnchorPane)leLoader.load();

            MenuPrincipalController leController;
            leController = leLoader.getController();
            leController.setEDTProject(this);

            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            this.primaryStage=primaryStage;
            primaryStage.show();
        } 
    }
    
    /**
     * La méthode main est la méthode principale du gestionnaire d'emploi du 
     * temps.
     * 
     * @param args
     * @throws edtproject.exception.salleNonDisponibleException
     * @throws edtproject.exception.professeurNonDisponibleException
     * @throws edtproject.exception.groupeNonDisponibleException
     * @throws edtproject.exception.heureScolaireNonRespecteesException
     * 
     * @see java.lang.String
     * @see Session
     * @see Professeur
     * @see Salle
     * @see Module
     * @see Groupe
     */
    public static void main(String[] args) throws salleNonDisponibleException, professeurNonDisponibleException, groupeNonDisponibleException, heureScolaireNonRespecteesException
    {
        Calendar dateDebut1 = Calendar.getInstance();
        Calendar dateFin1 = Calendar.getInstance();
        
        dateDebut1.set(Calendar.YEAR,2016); dateDebut1.set(Calendar.MONTH, Calendar.SEPTEMBER); dateDebut1.set(Calendar.DAY_OF_MONTH,1);
        dateFin1.set(Calendar.YEAR,2017); dateFin1.set(Calendar.MONTH, Calendar.JANUARY); dateFin1.set(Calendar.DAY_OF_MONTH,1);
        
        
        semestre1 = new Semestre(dateDebut1, dateFin1, 1);
        semestre3 = new Semestre(dateDebut1, dateFin1, 3);        
        
        Calendar dateDebut2 = Calendar.getInstance();
        Calendar dateFin2 = Calendar.getInstance();
        dateDebut2.set(Calendar.YEAR,2017); dateDebut2.set(Calendar.MONTH, Calendar.JANUARY); dateDebut2.set(Calendar.DAY_OF_MONTH,2);
        dateFin2.set(Calendar.YEAR,2017); dateFin2.set(Calendar.MONTH, Calendar.JUNE); dateFin2.set(Calendar.DAY_OF_MONTH,30);
        
        semestre2 = new Semestre(dateDebut2, dateFin2, 2);
        semestre4 = new Semestre(dateDebut2, dateFin2, 4);
        
        Groupe groupeC = new Groupe("C", 1);
        Groupe groupeD2 = new Groupe("D2", 1);
        Groupe groupeA1 = new Groupe("A1", 1);
        Groupe groupeAnnee1 = new Groupe("Annee1", 1);
        Groupe groupeAnnee2 = new Groupe("Annee2", 2);
        Groupe groupeC1 = new Groupe("C1", 1);
        
        new Session("admin", TypeUtilisateur.DIRECTEUR);
        new Professeur("Regis","Nohe","admin");
        
        new Session("pgo", TypeUtilisateur.PROFESSEUR);
        new Professeur("Petra","Gomez", "pgo");
        
        new Session("rdouteau", TypeUtilisateur.ELEVE);
        new Etudiant(groupeC1, "Romain", "Douteau", "rdouteau");
        
        new Session("bvalogne", TypeUtilisateur.ELEVE);
        new Etudiant(groupeC1, "Benjamin", "Valognes", "bvalogne");
        
        new Session("mlucas", TypeUtilisateur.ELEVE);
        new Etudiant(groupeC1, "Michael", "Lucas", "mlucas");
        
        
        new Salle("D206", TypeSalle.TD);   
        new Salle("C205", TypeSalle.TP);
        new Salle("D301", TypeSalle.TP);
        new Salle("Amphi", TypeSalle.AMPHI);

        semestre2.ajouterModule("AP",Color.ALICEBLUE);
        semestre4.ajouterModule("BD",Color.BISQUE);
        semestre2.ajouterModule("POO",Color.YELLOWGREEN);


        


        new Professeur("Farid","Ammar-Boudjelal", "fab");
        new Professeur("Cyril","Fauche", "cfa");

        
        Calendar debutCours8 = Calendar.getInstance()  ;
        debutCours8.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);  
        debutCours8.set(Calendar.HOUR_OF_DAY, 8);  debutCours8.set(Calendar.MINUTE,0);
        Calendar debutCours10 = Calendar.getInstance() ;
        debutCours10.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);  
        debutCours10.set(Calendar.HOUR_OF_DAY, 10);  debutCours10.set(Calendar.MINUTE,15);
        
        Calendar debutCours14 = Calendar.getInstance() ;
        debutCours14.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);  
        debutCours14.set(Calendar.HOUR_OF_DAY, 14);  debutCours14.set(Calendar.MINUTE,0);
        
        Calendar debutCours16 = Calendar.getInstance() ;
        debutCours16.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);  
        debutCours16.set(Calendar.HOUR_OF_DAY, 16);  debutCours16.set(Calendar.MINUTE,15);
        
        Calendar debutCours = Calendar.getInstance() ;
        debutCours.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);  
        debutCours.set(Calendar.HOUR_OF_DAY, 16);  debutCours.set(Calendar.MINUTE,15);
        

        Cours coursASaisir = coursList.ajouterCoursNonAffecte(120, TypeCours.TD,modules.get(0), debutCours.get(Calendar.WEEK_OF_YEAR));
        coursList.affecterCours(coursASaisir,salles.get(0),professeurs.get(0),debutCours8,groupes.get(0));

        coursASaisir = coursList.ajouterCoursNonAffecte(120, TypeCours.COURS,modules.get(0), debutCours.get(Calendar.WEEK_OF_YEAR));
        coursList.affecterCours(coursASaisir,salles.get(3),professeurs.get(1),debutCours10,groupes.get(3));

        coursASaisir = coursList.ajouterCoursNonAffecte(120, TypeCours.COURS,modules.get(1), debutCours.get(Calendar.WEEK_OF_YEAR));
        coursList.affecterCours(coursASaisir,salles.get(3),professeurs.get(1),debutCours14,groupes.get(4));

        coursASaisir = coursList.ajouterCoursNonAffecte(120, TypeCours.TP,modules.get(0), debutCours.get(Calendar.WEEK_OF_YEAR));
        coursList.affecterCours(coursASaisir,salles.get(1),professeurs.get(2),debutCours16,groupes.get(2));
        
        coursASaisir = coursList.ajouterCoursNonAffecte(120, TypeCours.TD,modules.get(0), debutCours.get(Calendar.WEEK_OF_YEAR));
        coursList.affecterCours(coursASaisir,salles.get(0),professeurs.get(2),debutCours,groupes.get(0));
        
        coursASaisir = coursList.ajouterCoursNonAffecte(120, TypeCours.TD,modules.get(2), debutCours.get(Calendar.WEEK_OF_YEAR));
        coursList.affecterCours(coursASaisir,salles.get(0),professeurs.get(2),debutCours14,groupes.get(5));
        
        launch(args);
    }
    
    /**
     * La méthode getPrimaryStage renvoie la fenêtre principale du gestionnaire 
     * d'emploi du temps.
     * 
     * @see Stage
     * 
     * @return Stage
     */
    public Stage getPrimaryStage()
    {
        return primaryStage;
    }

    /**
     * La méthode getUserConnecte renvoie la session de l'utilisateur 
     * actuellement connecté.
     * 
     * @see Session
     * 
     * @return Session
     */
    public static Session getUserConnecte() {
        return userConnecte;
    }
    
    /**
     * La méthode setUserConnecte permet de changer d'utilisateur connecté en 
     * changeant la valeur de l'attribut session.
     * La méthode vérifie également que l'utilisateur connecté est différent 
     * de l'utilisateur souhaitant se connecter.
     * 
     * @param identifiant
     * @param motDePasse
     * 
     * @see java.lang.String
     * @see Session
     * 
     * @return boolean
     */
    public static boolean setUserConnecte(String identifiant, String motDePasse) 
    {
        for(Session s : sessions)
        {
            if(identifiant.equals(s.getIdentifiant()) && motDePasse.equals(
                    s.getMotDePasse()))
            {
                userConnecte=s;
                return true;
            }
        }
        return false;
    }
    
    /**
     * La méthode deconnexion permet de deconnecté l'utilisateur connecté en 
     * affectant la valeur de l'attribut session à null.
     */
    public static void deconnexion()
    {
        userConnecte=null;
    }
    
    /**
     * La méthode getProfesseur retourne l'objet de type Professeur 
     * (s'il existe) possédant le même identifiant qu'un professeur indexé dans 
     * la liste professeurs.
     * 
     * @see java.lang.String
     * @see java.util.Integer
     * 
     * @param identifiant L'identifiant du professeur
     * 
     * @return Professeur
     */
    public static Professeur getProfesseur(String identifiant)
    {
        Professeur valRetour = null;
        for(Professeur p : professeurs)
        {
            if(p.getIdentifiant().equals(identifiant))
            {
                valRetour = p;
            }
        }
        return valRetour;
    }

    /**
     * La méthode getEtudiant retourne l'objet de type Etudiant 
     * (s'il existe) possédant le même identifiant qu'un étudiant indexé dans 
     * la liste étudiants.
     * 
     * @see java.lang.String
     * @see java.util.Integer
     * 
     * @param identifiant L'identifiant de l'étudiant
     * 
     * @return Etudiant
     */
    public static Etudiant getEtudiant(String identifiant)
    {
        Etudiant valRetour = null;
        for(Etudiant e : etudiants)
        {
            if(e.getIdentifiant().equals(identifiant))
            {
                valRetour = e;
            }
        }
        return valRetour;
    }
    
    /**
     * La méthode getModules renvoie la liste des modules instanciés.
     * 
     * @see Module
     * @see java.util.List
     * 
     * @return List
     */
    public static List<Module> getModules() 
    {
        return modules;
    }    

    /**
     * La méthode getModules renvoie la liste des modules instanciés, associés au semestre saisi.
     * 
     * @param semestre
     * @see Module
     * @see java.util.List
     * 
     * @return List
     */
    public static List<Module> getModules(Semestre semestre) 
    {
        List<Module> listeRetour = new ArrayList<>();
        for(Module m : semestre.getModules())
        {
            listeRetour.add(m);
        }
        return listeRetour;
    }    
    
    /**
     * La méthode getSalles renvoie la liste des salles instanciées.
     * 
     * @see Salle
     * @see java.util.List
     * 
     * @return List
     */
    public static List<Salle> getSalles() 
    {
        return salles;
    }

    

    /**
     * La méthode getEtudiants renvoie la liste des etudiants instanciés.
     * 
     * @see Etudiant
     * @see java.util.List
     * 
     * @return List
     */
    public static List<Etudiant> getEtudiants() 
    {
        return etudiants;
    }
    
    /**
     * La méthode getProfesseurs renvoie la liste des professeur instanciés.
     * 
     * @see Professeur
     * @see java.util.List
     * 
     * @return List
     */
    public static List<Professeur> getProfesseurs() 
    {
        return professeurs;
    }

    /**
     * La méthode getSessions renvoie la liste des sessions instanciés.
     * 
     * @see Session
     * @see java.util.List
     * 
     * @return List
     */
    public static List<Session> getSessions() 
    {
        return sessions;
    }

    /**
     * La méthode getCoursList renvoie un objet de type CoursList contenant 
     * la liste des cours affectés et celle des cours non affectés. 
     * 
     * @see CoursList
     * 
     * @return CoursList
     */
    public static CoursList getCoursList() 
    {
        return coursList;
    }
    
    
    /**
     * La méthode getGroupes renvoie la liste des groupes instanciés.
     * 
     * @see Groupe
     * @see java.util.List
     * 
     * @return List
     */
    public static List<Groupe> getGroupes() 
    {
        return groupes;
    }
    
    /**
     * La méthode getGroupes renvoie la liste des groupes instanciés récupère 
     * tous les groupes associés à une année.
     * 
     * @see Groupe
     * @see java.util.List
     * 
     * @param annee
     * 
     * @return List
     */
    public static List<Groupe> getGroupes(int annee) 
    {
        List<Groupe> listeRetour = new ArrayList<>();
        for(Groupe g : groupes)
        {
            if(g.getAnnee() == annee)
            {
                listeRetour.add(g);
            }
        }
        return listeRetour;
    }
    
    
    /**
     * La méthode getGroupesTD renvoie la liste des groupes de TD instanciés.
     * 
     * @see Groupe
     * @see java.util.List
     * 
     * @return List
     */
    public static List<Groupe> getGroupesTD()
    {
        List<Groupe> listeRetour = new ArrayList<>();
        for(Groupe g : groupes)
        {
            if(g.getNom().length() == 1)
            {
                listeRetour.add(g);
            }
        }
        return listeRetour;
    }
    
    /**
     * La méthode getGroupesTP renvoie la liste des groupes de TP instanciés.
     * 
     * @see Groupe
     * @see java.util.List
     * 
     * @return List
     */
    public static List<Groupe> getGroupesTP()
    {
        List<Groupe> listeRetour = new ArrayList<>();
        for(Groupe g : groupes)
        {
            if(g.getNom().length() == 2)
            {
                listeRetour.add(g);
            }
        }
        return listeRetour;
    }
    
    /**
     * La méthode getGroupesTP renvoie la liste des groupes de TP instanciés appartenant au groupe de TD saisi.
     * 
     * @see Groupe
     * @see java.util.List
     * 
     * @param c  char correspondant au nom du groupe TD
     * 
     * @return List
     */
    public static List<Groupe> getGroupesTP(char c)
    {
        List<Groupe> listeRetour = new ArrayList<>();
        for(Groupe g : groupes)
        {
            if(g.getNom().length() == 2)
            {
                listeRetour.add(g);
            }
        }
        return listeRetour;
    }
    
    
    /**
     * La méthode getFilePath renvoie un fichier selectionné par l'utilisateur.
     * 
     * @see java.lang.String
     * @see java.io.File
     * 
     * @return File
     */
    public File getFilePath()
    {
        Preferences userPref = Preferences.userNodeForPackage(EDTproject.class);
        String filePath = userPref.get("filePath", null);
        if(filePath != null)
        {
            return new File(filePath);
        }
        else
        {
            return null;
        }
    }
    
    /**
     * La méthode setFilePath permet à l'utilisateur de choisir un fichier.
     * 
     * @see java.lang.String
     * @see java.io.File
     * 
     * @param file Fichier définit par l'utilisateur
     */
    public void setFilePath(File file)
    {
        Preferences userPref = Preferences.userNodeForPackage(EDTproject.class);
        if(file != null)
        {
            userPref.put("filePath", file.getPath());
        }
        else
        {
            userPref.remove("filePath");
        }
    }
    
    /**
     * La méthode resetListDependances permet de vider le contenu des listes 
     * contenant les modules, les groupes, les professeurs, les salles et les 
     * sessions.
     * 
     * @see java.util.ArrayList
     */
    public static void resetListDependances()
    {
        modules.clear();
        groupes.clear();
        professeurs.clear();
        salles.clear();
        sessions.clear();
    }
}
