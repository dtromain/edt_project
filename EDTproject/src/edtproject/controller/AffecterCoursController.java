/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edtproject.controller;

import edtproject.CodePrincipal.Cours;
import edtproject.CodePrincipal.Groupe;
import edtproject.CodePrincipal.Module;
import edtproject.CodePrincipal.NomJour;
import edtproject.CodePrincipal.Professeur;
import edtproject.CodePrincipal.Salle;
import edtproject.CodePrincipal.TypeCours;
import edtproject.EDTproject;
import edtproject.exception.groupeNonDisponibleException;
import edtproject.exception.heureScolaireNonRespecteesException;
import edtproject.exception.professeurNonDisponibleException;
import edtproject.exception.salleNonDisponibleException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 */
public class AffecterCoursController implements Initializable {

    @FXML   private Spinner<Integer>        spinHeure, spinMinute, spinHeureDebut, spinMinuteDebut;
    @FXML   private ComboBox                comboBTypeCours, comboBSemaine;
    
    @FXML   private ComboBox<Module>        comboBModule;
    @FXML   private ComboBox<Salle>         comboBSalle;
    @FXML   private ComboBox<Professeur>    comboBProf;
    @FXML   private ComboBox<Groupe>        comboBGroupe;
    
    @FXML   private ComboBox<String>        comboBJour;
    @FXML   private Button              btnValider, btnAnnuler;
    
    private MenuPrincipalController mainController;
    private Cours coursEnAffectation;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        SpinnerValueFactory<Integer> valueFactoryHeure =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 4, 2);
 
        spinHeure.setValueFactory(valueFactoryHeure);
        spinHeure.getEditor().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) 
            {
                int nbSaisi = Integer.valueOf(spinHeure.getEditor().getText());
                if(nbSaisi>4)
                {
                    valueFactoryHeure.setValue(4);
                }
                else if(nbSaisi<0)
                {
                    valueFactoryHeure.setValue(0);
                }
                else
                {
                    valueFactoryHeure.setValue(nbSaisi);
                }
            }
        });
        SpinnerValueFactory<Integer> valueFactoryMinute =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0);
        
        spinMinute.setValueFactory(valueFactoryMinute);
        spinMinute.getEditor().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) 
            {
                int nbSaisi = Integer.valueOf(spinMinute.getEditor().getText());
                if(nbSaisi>59)
                {
                    valueFactoryMinute.setValue(59);
                }
                else if(nbSaisi<0)
                {
                    valueFactoryMinute.setValue(0);
                }
                else
                {
                    valueFactoryMinute.setValue(nbSaisi);
                }
            }
        });
        
        SpinnerValueFactory<Integer> valueFactoryHeureDebut =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 4, 2);
 
        spinHeureDebut.setValueFactory(valueFactoryHeureDebut);
        spinHeureDebut.getEditor().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) 
            {
                int nbSaisi = Integer.valueOf(spinHeureDebut.getEditor().getText());
                if(nbSaisi>4)
                {
                    valueFactoryHeureDebut.setValue(4);
                }
                else if(nbSaisi<0)
                {
                    valueFactoryHeureDebut.setValue(0);
                }
                else
                {
                    valueFactoryHeureDebut.setValue(nbSaisi);
                }
            }
        });
        SpinnerValueFactory<Integer> valueFactoryMinuteDebut =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0);
        
        spinMinuteDebut.setValueFactory(valueFactoryMinuteDebut);
        spinMinuteDebut.getEditor().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) 
            {
                int nbSaisi = Integer.valueOf(spinMinuteDebut.getEditor().getText());
                if(nbSaisi>59)
                {
                    valueFactoryMinuteDebut.setValue(59);
                }
                else if(nbSaisi<0)
                {
                    valueFactoryMinuteDebut.setValue(0);
                }
                else
                {
                    valueFactoryMinuteDebut.setValue(nbSaisi);
                }
            }
        });
        
        
        // configuration des ComboBox avec ajout des Items
        ObservableList<TypeCours> listeTypeCours = FXCollections.observableArrayList(TypeCours.COURS, TypeCours.TD, TypeCours.TP);
        ObservableList<Module> listeModules = FXCollections.observableArrayList(EDTproject.getModules());
        ObservableList<Salle> listeSalles = FXCollections.observableArrayList(EDTproject.getSalles());
        ObservableList<Professeur> listeProfesseurs = FXCollections.observableArrayList(EDTproject.getProfesseurs());
        ObservableList<Groupe> listeGroupes = FXCollections.observableArrayList(EDTproject.getGroupes());
        ObservableList<Integer> listeSemaines = FXCollections.observableArrayList();
        
        ObservableList<String> listeJours = 
        FXCollections.observableArrayList("LUNDI", "MARDI", "MERCREDI", "JEUDI", "VENDREDI"); 
        
        // définition des semaines
        int semaine = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
        for(int i = semaine; i < semaine+6; i++)
        {
            listeSemaines.add(i);
        }
        
        
        //Création des StringConverter pour afficher du texte a partir d'un Object
        StringConverter<Professeur> affichageProfesseur =  new StringConverter<Professeur>() 
        {
            public String toString(Professeur prof) {
                return prof.getInitiales();
            }

             @Override
             public Professeur fromString(String string) {
                 throw new UnsupportedOperationException("Not supported yet.");
             }
             
        };
        comboBProf.setConverter(affichageProfesseur);
        
        //
        StringConverter<Module> affichageModule =  new StringConverter<Module>() 
        {
            public String toString(Module m) {
                return m.getNom();
            }

            public Module fromString(String nomModule) {
                int nbHeures = 0;
                Color c = Color.ALICEBLUE;
                
                Module m = new Module(nomModule, c);
                return m;
            }
        };
        comboBModule.setConverter(affichageModule);
        
        //
        StringConverter<Salle> affichageSalle =  new StringConverter<Salle>() 
        {
            public String toString(Salle s) {
                return s.getNom();
            }

            public Salle fromString(String nomSalle) {
                
                Salle s = new Salle(nomSalle, null);
                return s;
            }
        };
        comboBSalle.setConverter(affichageSalle);
        
        //
        StringConverter<Groupe> affichageGroupe =  new StringConverter<Groupe>() 
        {
            public String toString(Groupe g) {
                return g.getNom();
            }

            public Groupe fromString(String nomGroupe) {
      
                Groupe g = new Groupe(nomGroupe, 1);
                return g;
            }
        };
        comboBGroupe.setConverter(affichageGroupe);
        
        comboBModule.setItems(listeModules);
        comboBTypeCours.setItems(listeTypeCours);
        comboBSalle.setItems(listeSalles);
        comboBProf.setItems(listeProfesseurs);
        comboBGroupe.setItems(listeGroupes);
        comboBSemaine.setItems(listeSemaines);
        comboBJour.setItems(listeJours);
    }    
    
    /**
     *
     * @param module
     * @param type
     * @param duree
     * @param semaine
     */
    public void setCoursNonAffecte(Cours coursNonAffecte)
    {
        comboBModule.setValue(coursNonAffecte.getModule());
        comboBTypeCours.setValue(coursNonAffecte.getType());
        comboBSemaine.setValue(coursNonAffecte.getSemaine());
        
        // configuration des spinners
        SpinnerValueFactory<Integer> valueFactory =
        new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 4, coursNonAffecte.getDuree() / 60);
        
        spinHeure.setValueFactory(valueFactory);
        
        
        SpinnerValueFactory<Integer> valueFactory2 =
        new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, coursNonAffecte.getDuree() % 60);
        
        spinMinute.setValueFactory(valueFactory2);
        
        coursEnAffectation = coursNonAffecte;
    }
    
    /**
     *
     * @return
     */
    public Cours getCours()
    {
        return coursEnAffectation;
    }
    
    /**
     *
     */
    public void affecterCours() throws salleNonDisponibleException, professeurNonDisponibleException, groupeNonDisponibleException, heureScolaireNonRespecteesException
    {
        Calendar debut = Calendar.getInstance();
        debut.set(Calendar.WEEK_OF_YEAR, (Integer)comboBSemaine.getValue());
        switch(comboBJour.getValue())
        {
            case "LUNDI":       debut.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                                break;
            case "MARDI":       debut.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
                                break;
            case "MERCREDI":    debut.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
                                break;
            case "JEUDI":       debut.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
                                break;
            case "VENDREDI":    debut.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
                                break;
        }
        
        debut.set(Calendar.HOUR_OF_DAY, spinHeureDebut.getValue());
        debut.set(Calendar.MINUTE, spinMinuteDebut.getValue());
        
        EDTproject.getCoursList().affecterCours(coursEnAffectation, comboBSalle.getValue(),
                                                comboBProf.getValue(), debut, comboBGroupe.getValue());
        /*try {
            
        } catch (salleNonDisponibleException s, professeurNonDisponibleException p, groupDisponibleException g) 
        {
            Alert
        }*/
    }
    
    /**
     *
     */
    public void close()
    {
        close();
    }
    
    public void setMainController(MenuPrincipalController mainController)
    {
        this.mainController = mainController;
    }
}
