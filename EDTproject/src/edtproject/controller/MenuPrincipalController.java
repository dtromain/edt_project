package edtproject.controller;

import edtproject.CodePrincipal.Cours;
import edtproject.CodePrincipal.Etudiant;
import edtproject.CodePrincipal.Professeur;
import edtproject.CodePrincipal.Session;
import edtproject.CodePrincipal.TypeCours;
import edtproject.EDTproject;
import edtproject.model.BoutonReserve;
import edtproject.model.GestionFichier;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 */
public class MenuPrincipalController{

    @FXML   private AnchorPane apMain, apAffEDT, apModifEDT, apCreationSession, apCreationModule, apCreationGroupe, apCreationSalle;
    @FXML   private HBox hBoxConnecte;
    @FXML   private FlowPane flowpReserve;
    @FXML   private ScrollPane scrollpReserve;
    @FXML   private TabPane tpMain;
    @FXML   private Tab tabEDT, tabCreationCours, tabCreationSession, tabCreationModule, tabCreationGroupe, tabCreationSalle;

    @FXML 
    private Button btnConnexion, btnDeconnexion;
    
    @FXML   
    private Label lblNomSession;
    
    @FXML   
    private ImageView imgParametres, imgProfil;
    
    @FXML
    private MenuItem miSave, miLoad;
    
    @FXML   private CreationSessionController CreationSessionController; 
    @FXML   private CreationCoursController CreationCoursController; 
    @FXML   private CreationGroupeController CreationGroupeController;
    @FXML   private CreationSalleController CreationSalleController;
    @FXML   private CreationModuleController CreationModuleController;
    @FXML   private AffecterCoursController     AffecterCoursController;
    
    @FXML
    private AffichageEDTController AffichageEDTController;
    
    private EDTproject mainClass;
    
    /**
     *
     */
    @FXML
    public void initialize() 
    {
        btnConnexion.setVisible(true);          btnConnexion.setDisable(false);
        hBoxConnecte.setVisible(false);         hBoxConnecte.setDisable(true);
        
        tpMain.setDisable(true);                tpMain.setVisible(false);
        tabEDT.setDisable(true);
        apAffEDT.setVisible(false);          apAffEDT.setDisable(true);
        
        afficherFenetreAdmin(false);
        AffichageEDTController.setMainController(this);
        CreationSessionController.setMainController(this);
        CreationCoursController.setMainController(this);
        CreationGroupeController.setMainController(this);
        CreationSalleController.setMainController(this);
        CreationModuleController.setMainController(this);
       
    }
        
    /**
     *
     * @throws IOException
     */
    public void sauvegarderCours() throws IOException
    {
        FileChooser fc = new FileChooser();
        List<String> extensions = new ArrayList<>();
        extensions.add("*.txt");
        
        fc.setTitle("Enregistrer les cours");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichier de sauvegarde", extensions));
        File fichier = fc.showSaveDialog(apMain.getScene().getWindow());
        if(fichier != null)
        {
            GestionFichier gf = new GestionFichier();
            gf.sauvegarderCours(fichier);
        }
    }
    
    /**
     *
     * @throws FileNotFoundException
     */
    public void sauvegarderDependances() throws FileNotFoundException, IOException
    {
        FileChooser fc = new FileChooser();
        List<String> extensions = new ArrayList<>();
        extensions.add("*.txt");
        
        fc.setTitle("Enregistrer les dépendances");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichier de sauvegarde", extensions));
        File fichier = fc.showSaveDialog(apMain.getScene().getWindow());
        if(fichier != null)
        {
            GestionFichier gf = new GestionFichier();
            gf.sauvegarderDependances(fichier);
        }
    }
    
    /**
     *
     * @throws ParseException
     * @throws FileNotFoundException
     */
    
    public void chargerCours() throws ParseException, FileNotFoundException
    {
        FileChooser fc = new FileChooser();
        List<String> extensions = new ArrayList<String>();
        extensions.add("*.txt");
        fc.setTitle("Charger les cours");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichier de sauvegarde", extensions));
        
        File fichier = fc.showOpenDialog(apMain.getScene().getWindow());
        if(fichier != null)
        {
            GestionFichier gf = new GestionFichier();
            gf.chargerCours(fichier);
        }
    }
    
    /**
     *
     * @throws FileNotFoundException
     */
    public void chargerDependances() throws FileNotFoundException
    {
        FileChooser fc = new FileChooser();
        List<String> extensions = new ArrayList<String>();
        extensions.add("*.txt");
        fc.setTitle("Charger les dépendances");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichier de sauvegarde", extensions));
        
        File fichier = fc.showOpenDialog(apMain.getScene().getWindow());
        if(fichier != null)
        {
            GestionFichier gf = new GestionFichier();
            gf.chargerDependances(fichier);
        }
    }
    
    /**
     *
     * @throws IOException
     */
    public void connexion() throws IOException
    {
        FXMLLoader leLoader = new FXMLLoader(getClass().getResource("/edtproject/view/Connexion.fxml"));
        AnchorPane apLaPage = (AnchorPane)leLoader.load();
        
        Stage sConnect = new Stage();
        sConnect.setTitle("Connexion");
        
        Scene laScene = new Scene(apLaPage);
        sConnect.setScene(laScene);
        sConnect.initOwner(mainClass.getPrimaryStage());
        sConnect.initModality(Modality.WINDOW_MODAL);
        
        sConnect.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) 
            {
                Session user = EDTproject.getUserConnecte();
                if(user!=null)
                {
                    apAffEDT.setVisible(true);          apAffEDT.setDisable(false);
                    hBoxConnecte.setVisible(true);      hBoxConnecte.setDisable(false);
                    btnConnexion.setVisible(false);     btnConnexion.setDisable(true);
                    
                    tpMain.setDisable(false);   tpMain.setVisible(true);
                    tabEDT.setDisable(false);
                    switch (user.getTypeUser()) {
                        case DIRECTEUR:
                            {
                                Professeur p = EDTproject.getProfesseur(user.getIdentifiant());
                                lblNomSession.setText(p.getPrenom()+' '+p.getNom());
                                afficherFenetreAdmin(true);
                                actualiserFenetres();
                                AffichageEDTController.affSemestre();
                                break;
                            }
                        case PROFESSEUR:
                            {
                                Professeur p = EDTproject.getProfesseur(user.getIdentifiant());
                                lblNomSession.setText(p.getPrenom()+' '+p.getNom());
                                actualiserFenetres();
                                AffichageEDTController.affSemestre();
                                break;
                            }
                        default:
                            Etudiant e = EDTproject.getEtudiant(user.getIdentifiant());
                            lblNomSession.setText(e.getPrenom()+' '+e.getNom());
                            actualiserFenetres();
                            AffichageEDTController.affSemestre();
                            break;
                    }
                }
                else
                {
                    sConnect.close();
                }
                
            }
            
        });
        sConnect.showAndWait();
    }
    
    /**
     *
     */
    public void deconnexion()
    {
        EDTproject.deconnexion();
        btnConnexion.setVisible(true);          btnConnexion.setDisable(false);
        hBoxConnecte.setVisible(false);         hBoxConnecte.setDisable(true); 

        tpMain.setDisable(true);                tpMain.setVisible(false);
        afficherFenetreAdmin(false);
        apAffEDT.setVisible(false);          apAffEDT.setDisable(true);
    }
    
    /**
     *
     * @param bool
     */
    public void afficherFenetreAdmin(boolean bool)
    {
        tabCreationCours.setDisable(!bool);
        tabCreationSession.setDisable(!bool);
        tabCreationModule.setDisable(!bool);
        tabCreationGroupe.setDisable(!bool);
        tabCreationSalle.setDisable(!bool);
        
        apCreationSession.setVisible(bool);     apCreationSession.setDisable(!bool);
        apCreationSalle.setVisible(bool);       apCreationSalle.setDisable(!bool);
        apCreationModule.setVisible(bool);      apCreationModule.setDisable(!bool);
        apCreationGroupe.setVisible(bool);      apCreationGroupe.setDisable(!bool);
        apModifEDT.setVisible(bool);            apModifEDT.setDisable(!bool);
    }
    
    /**
     * Cette methode permet d'actualiser les differentes fenetres du projet, elle est utilisee
     * principalement lors d'un ajout d'une donnee dans la base de donnee
     */
    public void actualiserReserve()
    {
        flowpReserve.getChildren().clear();
        for(Cours c : EDTproject.getCoursList().getCoursNonAffectes())
        {
            VBox vbCours = new VBox(1);
            Rectangle bouton = new Rectangle(60, 30, c.getModule().getCouleur().brighter());
            Tooltip tooltip = new Tooltip("Cliquez pour modifier");
            Label lblDescription = new Label(c.afficherCoursNonAffecte());
            
            bouton.setOpacity(0.5);
            bouton.setStroke(c.getModule().getCouleur());       bouton.setStrokeWidth(0.5);
            
            tooltip.install(bouton, tooltip);
            BoutonReserve boutonReserve = new BoutonReserve(bouton, c);
            
            boutonReserve.getBouton().setOnMouseClicked(new EventHandler<MouseEvent>() 
            {
                @Override
                public void handle(MouseEvent event) 
                {
                    try {
                        System.out.println("Click!!");
                        
                        FXMLLoader leLoader = new FXMLLoader();
                        leLoader.setLocation(getClass().getResource("/edtproject/view/affecterCours.fxml"));
                        AnchorPane apLaPage = (AnchorPane)leLoader.load();
                        
                        Stage sAffecterCours = new Stage();
                        sAffecterCours.setTitle("Affecter le cours");
                        
                        Scene laScene = new Scene(apLaPage);
                        sAffecterCours.setScene(laScene);
                        sAffecterCours.initOwner(mainClass.getPrimaryStage());
                        sAffecterCours.initModality(Modality.WINDOW_MODAL);
                        
                        
                        AffecterCoursController controller = leLoader.getController(); 
                        controller.setMainController(MenuPrincipalController.this);
                        sAffecterCours.setOnHiding(new EventHandler<WindowEvent>() 
                        {
                            @Override
                            public void handle(WindowEvent event)
                            {
                                Cours cours = controller.getCours();
                                if(cours != null)
                                {
                                    if(!cours.equals(boutonReserve.getCours()))
                                    {
                                        actualiserReserve();
                                    }
                                }
                            }
                        });
                        sAffecterCours.showAndWait();
                    } catch (IOException ex) 
                    {
                        Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            
            
            vbCours.getChildren().add(boutonReserve.getBouton());
            vbCours.getChildren().add(lblDescription);
            flowpReserve.getChildren().add(vbCours);
        }
        scrollpReserve.setContent(flowpReserve);
        scrollpReserve.setPannable(true);
        
    }
    
    /**
     *
     */
    public void actualiserFenetres()
    {
        AffichageEDTController.actualiser();
        CreationCoursController.actualiser();
        CreationSessionController.actualiser();
        actualiserReserve();
    }
    
    /**
     *
     * @param mainClass
     */
    public void setEDTProject(EDTproject mainClass)
    {
        this.mainClass=mainClass;
    }
}
