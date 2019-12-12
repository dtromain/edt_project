package edtproject.controller;
import edtproject.CodePrincipal.Cours;
import edtproject.CodePrincipal.CoursList;
import edtproject.CodePrincipal.Groupe;
import edtproject.CodePrincipal.Module;
import edtproject.CodePrincipal.Professeur;
import edtproject.CodePrincipal.Salle;
import edtproject.CodePrincipal.Semestre;
import edtproject.CodePrincipal.TypeUtilisateur;
import edtproject.EDTproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.TextAlignment;
import javafx.util.StringConverter;

/**
 *
 * @author romain
 */
public class AffichageEDTController{
    
    @FXML
    private ToggleButton toggleBtnAff;
    @FXML
    private Button btnEDTPersonnel;
    
    @FXML
    private Label lblAffSemaine, lblAffSemestre, lblAffichageType;  
    
    @FXML private ComboBox              comboBFiltreElement;
    @FXML private ComboBox<Semestre>    comboBSemestre;
    @FXML private ComboBox<String>      comboBFiltreMode;

    @FXML
    private AnchorPane apMain;
    @FXML 
    private Canvas canvasEDT;
    @FXML
    private DatePicker datePick;
    
    private boolean filtrePersonnel;
    private CoursList cours;
    private LocalDate date;
    private GraphicsContext gc;
    private MenuPrincipalController mainController;
    private ObservableList<String> listeModes;
    private Semestre semestre;
    private int annee;
    
    /**
     *
     */
    public AffichageEDTController() {
    }
    
    @FXML
    private void initialize() 
    {
        StringConverter<Semestre> affichage =  new StringConverter<Semestre>() 
        {
            @Override
            public String toString(Semestre s) 
            {
                return "Semestre " + s.getNumero();
            }

            @Override
            public Semestre fromString(String string) {
                throw new UnsupportedOperationException("Non supporté");
            }

        };
        
        comboBSemestre.setConverter(affichage);
        
        datePick.setValue(LocalDate.now());
        date=datePick.getValue();
        gc = canvasEDT.getGraphicsContext2D();
        
        
        listeModes = FXCollections.observableArrayList("Salles","Modules","Groupes");
        comboBFiltreMode.setItems(listeModes);
        comboBFiltreMode.setValue("Salles");
        affSalle();
        
        
        filtrePersonnel=true;
        TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        int semaine=date.get(woy);
        lblAffSemaine.setText("Semaine "+semaine);
           
        datePick.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event) 
            {
                date=datePick.getValue();
                clearCanvas();
                affSemestre();
                
                if(toggleBtnAff.isSelected())
                {
                    afficherJour(EDTproject.getCoursList());
                }
                else
                {
                    afficherSemaine(EDTproject.getCoursList());
                }
                
                TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
                int semaine=date.get(woy);

                lblAffSemaine.setText("Semaine "+semaine);
            }
        });
       
        toggleBtnAff.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
                if(toggleBtnAff.isSelected())
                {
                    afficherSemaine(EDTproject.getCoursList());
                    lblAffichageType.setText("Affichage par semaine");
                }
                else
                {
                    afficherJour(EDTproject.getCoursList());
                    lblAffichageType.setText("Affichage par jour");
                }
            }
        });
        
        btnEDTPersonnel.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event) 
            {
                filtrePersonnel = true;
                toggleBtnAff.setSelected(false);
                lblAffichageType.setText("Affichage par jour");
                afficherJour(EDTproject.getCoursList());
            }
        });
       
       comboBFiltreMode.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) 
            {
                switch(comboBFiltreMode.getValue())
                {
                    case "Salles"       :   affSalle();
                                            break;
                    case "Modules"      :   affModule();
                                            break;
                    case "Professeurs"  :   affProfesseur();
                                            break;
                    case "Groupes"      :   affGroupe();
                                            break;
                    default : System.out.println("AffichageEDTController/initialize(): Switch non respecte");
                }
            }
        });
       
        comboBFiltreElement.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event) 
            {
                filtrePersonnel=false;
                if(comboBFiltreElement.getValue() != null)
                {
                    if(toggleBtnAff.isSelected())
                    {
                        afficherSemaine(EDTproject.getCoursList());
                    }
                    else
                    {
                        afficherJour(EDTproject.getCoursList());
                    }
                }
            }
            
        });
    }

    /**
     *
     * @param cours
     */
    public void afficherJour(CoursList cours)
    {
        clearCanvas();
        this.cours = cours;
        List<Cours> listeCours = filtrerCours();
        if(!listeCours.isEmpty())
        {
            for(Cours c : listeCours)
            {
                if(c.getCreneau().getJour()==date.getDayOfYear())
                {
                    double posY=20+(c.getCreneau().getHeureDebut()-8*60)/1.5;
                    String jour=c.getCreneau().getNomJour().name()+' '+c.getCreneau().getCalendarDebut().get(Calendar.DAY_OF_MONTH)+' '+c.getCreneau().getNomMois();

                    gc.setFill(c.getModule().getCouleur());
                    gc.fillRect(35,posY , canvasEDT.getWidth()-35, c.getDuree()/1.5);

                    gc.setFill(Color.BLACK);
                    gc.setTextAlign(TextAlignment.CENTER);
                    gc.fillText(jour, canvasEDT.getWidth()/2-15, 15);

                    gc.fillText(c.afficherCoursAffecte(), canvasEDT.getWidth()/2-15, posY+c.getDuree()/3);

                    gc.setTextAlign(TextAlignment.LEFT);
                    gc.fillText(c.getCreneau().afficher(c.getCreneau().getCalendarDebut()), 0, posY+5);
                    gc.fillText(c.getCreneau().afficher(c.getCreneau().getCalendarFin()), 0, posY+c.getDuree()/1.5-5);
                }
            }
        }
        else
        {
            gc.setFill(Color.BLACK);
            gc.setTextAlign(TextAlignment.CENTER);
            gc.fillText("Aucun cours pour le "+date.format(DateTimeFormatter.ofPattern("EEEE dd MMM")), canvasEDT.getWidth()/2, canvasEDT.getHeight()/2);
        }
    }
    
    /**
     *
     * @param cours
     */
    public void afficherSemaine(CoursList cours)
    {
        clearCanvas();
        this.cours=cours;
 
        gc.setTextAlign(TextAlignment.CENTER);
        
        List<Cours> listeCours = filtrerCours();
        if(!listeCours.isEmpty())
        {
            for(Cours c : listeCours)
            {
                double posX=35 +c.getCreneau().getNomJour().ordinal() *130;
                double posY=20 +(c.getCreneau().getHeureDebut()-8*60) /2;
                String jour=c.getCreneau().getNomJour().name() +' '+ c.getCreneau().getCalendarDebut().get(Calendar.DAY_OF_MONTH)+' '+c.getCreneau().getNomMois();

                gc.setFill(c.getModule().getCouleur());
                gc.fillRect(posX, posY, 120, c.getDuree()/2);

                gc.setFill(Color.BLACK);
                gc.setTextAlign(TextAlignment.CENTER);
                gc.fillText(jour, posX +120/2, 15);


                gc.fillText(c.afficherCoursAffecte(), posX +120 /2, posY +c.getDuree() /4);

                gc.setTextAlign(TextAlignment.LEFT);
                gc.fillText(c.getCreneau().afficher(c.getCreneau().getCalendarDebut()), 0, posY +5);
                gc.fillText(c.getCreneau().afficher(c.getCreneau().getCalendarFin()), 0, posY + c.getDuree() /2-5);
            }
        }
        else
        {
            gc.setFill(Color.BLACK);
            gc.setTextAlign(TextAlignment.CENTER);
            gc.fillText("Aucun cours pour la semaine " + date.format(DateTimeFormatter.ofPattern("ww")), canvasEDT.getWidth() / 2, canvasEDT.getHeight() / 2);
        }
    }
    
    /**
     *
     * @return
     */
    public List<Cours> filtrerCours()
    {
        List<Cours> listeRetour = new ArrayList<>();
        TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        int semaine = date.get(woy);
        
        if(filtrePersonnel)
        {
             if(!cours.getCoursUtilisateur(semestre).isEmpty())
             {
                 for(Cours c : cours.getCoursUtilisateur(semestre))
                 {
                     if(c.getSemaine() == semaine)
                     {
                         listeRetour.add(c);
                     }
                 }
             }
        }
        else
        {
            if(!cours.getCoursAffectes().isEmpty())
            {
                switch(comboBFiltreMode.getValue())
                {
                    case "Salles"       :   for(Cours c : cours.rechercherCoursAffectes((Salle)comboBFiltreElement.getValue(), semestre))
                                            {
                                                if(c.getSemaine() == semaine)
                                                {
                                                    listeRetour.add(c);
                                                }
                                            }
                                            break;

                    case "Modules"      :   for(Cours c : cours.rechercherCoursAffectes((Module)comboBFiltreElement.getValue(), semestre))
                                            {
                                                if(c.getSemaine() == semaine)
                                                {
                                                    listeRetour.add(c);
                                                }
                                            }
                                            break;

                    case "Professeurs"  :   for(Cours c : cours.rechercherCoursAffectes((Professeur)comboBFiltreElement.getValue(), semestre))
                                            {
                                                if(c.getSemaine() == semaine)
                                                {
                                                    listeRetour.add(c);
                                                }
                                            }
                                            break;

                    case "Groupes"      :   Groupe groupe = (Groupe)comboBFiltreElement.getValue();
                                            if(groupe.getNom().length() > 2)
                                            {
                                                for(Cours c : cours.rechercherCoursAffectes(groupe.getAnnee()))
                                                {
                                                    if(c.getSemaine() == semaine)
                                                    {
                                                        listeRetour.add(c);
                                                    }
                                                }
                                            }
                                            else
                                            {
                                                for(Cours c : cours.rechercherCoursAffectes(groupe, semestre))
                                                {
                                                    if(c.getSemaine() == semaine)
                                                    {
                                                        listeRetour.add(c);
                                                    }
                                                }
                                            }
                                            break;
                                            

                    default : System.out.println("AffichageEDTController/filtrerCours(): Switch non respecte");
                }
            }
        }
        return listeRetour;
    }
    
    /**
     *
     */
    public void clearCanvas()
    {
        gc.clearRect(0, 0, canvasEDT.getWidth(), canvasEDT.getHeight());
    }
    
    /**
     *
     */
    public void affProfesseur()
    {
        ObservableList<Professeur> liste = FXCollections.observableArrayList(EDTproject.getProfesseurs());
        StringConverter<Professeur> affichage =  new StringConverter<Professeur>() 
        {
            public String toString(Professeur prof) {
                return prof.getInitiales();
            }

            @Override
            public Professeur fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

        };;
        
        comboBFiltreElement.setConverter(affichage);
        comboBFiltreElement.setItems(liste);
    }
    
    /**
     *
     */
    public void affModule()
    {
        ObservableList<Module> liste = FXCollections.observableArrayList(EDTproject.getModules(semestre));
        StringConverter<Module> affichage =  new StringConverter<Module>() 
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
        };;
        
        comboBFiltreElement.setConverter(affichage);
        comboBFiltreElement.setItems(liste);
    }
    
    /**
     *
     */
    public void affSalle()
    {
        ObservableList<Salle> liste = FXCollections.observableArrayList(EDTproject.getSalles());
        StringConverter<Salle> affichage =  new StringConverter<Salle>() 
        {
            public String toString(Salle s) {
                return s.getNom();
            }

            public Salle fromString(String nomSalle) {
                
                Salle s = new Salle(nomSalle, null);
                return s;
            }
        };;
        
        comboBFiltreElement.setConverter(affichage);
        comboBFiltreElement.setItems(liste);

    }
    
    /**
     *
     */
    public void affGroupe()
    {
        ObservableList<Groupe> liste = FXCollections.observableArrayList(EDTproject.getGroupes(annee));
        StringConverter<Groupe> affichage =  new StringConverter<Groupe>() 
        {
            public String toString(Groupe g) {
                return g.getNom();
            }

            public Groupe fromString(String nomGroupe) {
      
                Groupe g = new Groupe(nomGroupe, 1);
                return g;
            }
        };;
        
        comboBFiltreElement.setConverter(affichage);
        comboBFiltreElement.setItems(liste);
    }
    
    /**
     *
     */
    public void affSemestre()
    {
        if(EDTproject.getUserConnecte().getTypeUser().equals(TypeUtilisateur.ELEVE))
        {
            if(EDTproject.getEtudiant(EDTproject.getUserConnecte().getIdentifiant()).getGroupeTP().getAnnee() == 1)
            {
                semestre = EDTproject.getSemestre(EDTproject.getSemestre(date));
            }
            else
            {
                semestre =  EDTproject.getSemestre(EDTproject.getSemestre(date) + 2);
            }
            lblAffSemestre.setText("Semestre " + semestre);
            lblAffSemestre.setVisible(true);    lblAffSemestre.setDisable(false);
            comboBSemestre.setVisible(false);   comboBSemestre.setDisable(true);
        }
        else
        {
            ObservableList<Semestre> liste = FXCollections.observableArrayList(EDTproject.getSemestre(EDTproject.getSemestre(date)), 
                                                                               EDTproject.getSemestre(EDTproject.getSemestre(date) + 2));
            comboBSemestre.setItems(liste);
            
            lblAffSemestre.setVisible(false);    lblAffSemestre.setDisable(true);
            comboBSemestre.setVisible(true);   comboBSemestre.setDisable(false);
                        
            comboBSemestre.setValue(EDTproject.getSemestre(EDTproject.getSemestre(date)));
            semestre = comboBSemestre.getValue();
            
            
            comboBSemestre.setOnAction(new EventHandler<ActionEvent>() 
            {
                @Override
                public void handle(ActionEvent event) 
                {
                    semestre = comboBSemestre.getValue();
                    semestreToAnnee();
                    clearCanvas();
                    actualiser();
                }
            });
            
        }
        semestreToAnnee();
    }
    
    /**
     *
     * @param mainController
     */
    public void setMainController(MenuPrincipalController mainController)
    {
        this.mainController = mainController;
    }
    
    /**
     *
     */
    public void actualiser()
    {
        if(!EDTproject.getUserConnecte().getTypeUser().equals(TypeUtilisateur.ELEVE))
        {
            if(!listeModes.contains("Professeurs"))
            {
                listeModes.add("Professeurs");
            }
        }
        else
        {
            if(listeModes.contains("Professeurs"))
            {
                listeModes.remove("Professeurs");
            }
        }
        switch(comboBFiltreMode.getValue())
        {
            case "Salles"       :   affSalle();
                                    break;
            case "Modules"      :   affModule();
                                    break;
            case "Professeurs"  :   affProfesseur();
                                    break;
            case "Groupes"      :   affGroupe();
                                    break;
            default : System.out.println("Switch non respecte");
        }
    }
    
    /**
     *
     */
    public void semestreToAnnee()
    {
        if(semestre.getNumero() <= 2)
        {
            annee = 1;
        }
        else
        {
            annee = 2;
        }
    }
}
