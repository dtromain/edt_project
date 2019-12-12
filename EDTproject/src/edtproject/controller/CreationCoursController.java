package edtproject.controller;

import edtproject.EDTproject;
import edtproject.CodePrincipal.Module;
import edtproject.CodePrincipal.TypeCours;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 */
public class CreationCoursController implements Initializable {

    // menu
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu ajouterMenu, modifierMenu, supprimerMenu;
    @FXML
    private MenuItem ajouterCoursInMenu, ajouterProfInMenu, ajouterModuleInMenu;
    
    // rectangle
    @FXML
    private Rectangle rectangle;
    
    // button
    @FXML
    private Button boutonAjouterCoursFinal;
    
    // anchorPane
    @FXML
    private AnchorPane anchorPane;
    
    // text
    @FXML
    private Text textSelectionnerModule, textSelectionnerTypeCours, textSelectionnerSemaine;
    
    // comboBox
    @FXML
    private ComboBox<Module> comboBoxModule;
    @FXML
    private ComboBox<TypeCours> comboBoxTypeCours;
    @FXML
    private ComboBox<Integer> comboBoxSemaine;
    
    // spinner
    @FXML
    private Spinner<Integer> spinnerHeure, spinnerMinute;

    private MenuPrincipalController mainController;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        System.out.println("Chargement de l'interface CreationCours...");
        
        // configuration des spinners
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 4, 2);
 
        spinnerHeure.setValueFactory(valueFactory);
        spinnerHeure.getEditor().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) 
            {
                int nbSaisi = Integer.valueOf(spinnerHeure.getEditor().getText());
                if(nbSaisi>4)
                {
                    valueFactory.setValue(4);
                }
                else if(nbSaisi<0)
                {
                    valueFactory.setValue(0);
                }
                else
                {
                    valueFactory.setValue(nbSaisi);
                }
            }
        });
        SpinnerValueFactory<Integer> valueFactory2 =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0);
        
        spinnerMinute.setValueFactory(valueFactory2);
        spinnerMinute.getEditor().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) 
            {
                int nbSaisi = Integer.valueOf(spinnerMinute.getEditor().getText());
                if(nbSaisi>59)
                {
                    valueFactory2.setValue(59);
                }
                else if(nbSaisi<0)
                {
                    valueFactory2.setValue(0);
                }
                else
                {
                    valueFactory2.setValue(nbSaisi);
                }
            }
        });
        
        // configuration des ComboBox avec ajout des Items
        ObservableList<TypeCours> listeTypeCours = FXCollections.observableArrayList(TypeCours.COURS, TypeCours.TD, TypeCours.TP);
        ObservableList<Module> listeModule = FXCollections.observableArrayList(EDTproject.getModules());
        ObservableList<Integer> listeSemaine = FXCollections.observableArrayList(); 
        
        // définition des semaines
        int semaine = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
        for(int i = semaine; i < semaine+6; i++)
        {
            listeSemaine.add(i);
        }
        
        comboBoxModule.setItems(listeModule);
        comboBoxTypeCours.setItems(listeTypeCours);
        comboBoxSemaine.setItems(listeSemaine);
        
        // bug fix affichage comboBox  
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
        
        comboBoxModule.setConverter(affichageModule);

    }
    
    /**
     *
     */
    public void ajouterCoursFinal()
    {
        System.out.println("Ajouter cours...");
        
        Module leModule = null;
        int laSemaine = 0;
        int laDuree = 0;
        TypeCours leTypeCours = null;
        
        try
        {
            leModule = comboBoxModule.valueProperty().getValue();
            laSemaine = comboBoxSemaine.valueProperty().getValue();
            leTypeCours = comboBoxTypeCours.valueProperty().getValue();
            System.out.println(Integer.toString(spinnerMinute.valueProperty().getValue()));
            System.out.println(Integer.toString(spinnerHeure.valueProperty().getValue()));
            laDuree = (spinnerMinute.valueProperty().getValue()) + ((spinnerHeure.valueProperty().getValue()) * 60);
        }
        
        catch(NullPointerException e)
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Erreur");
            a.setHeaderText("Impossible d'ajouter le cours.");
            a.setContentText("Vous devez renseigner les éléments manquants.");
            a.showAndWait();  
        }
        System.out.println(Integer.toString(spinnerMinute.valueProperty().getValue()));
            System.out.println(Integer.toString(spinnerHeure.valueProperty().getValue()));
        if(leModule != null && laSemaine != 0 && leTypeCours != null && laDuree != 0)
        {
            System.out.println("Le cours a été ajouté");
            
            EDTproject.getCoursList().ajouterCoursNonAffecte(laDuree, leTypeCours, leModule, laSemaine);
            mainController.actualiserFenetres();
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Cours ajouté");
            a.setHeaderText("Le cours a bien été ajouté.");
            a.setContentText(leModule.getNom() + " ("+ leTypeCours.toString() + ") en semaine " + laSemaine);
            a.showAndWait();
        }
    }
    
    /**
     *
     * @param mainController
     */
    public void setMainController(MenuPrincipalController mainController)
    {
        this.mainController=mainController;
    }
    
    /**
     *
     */
    public void actualiser()
    {
        ObservableList<Module> listeModule = FXCollections.observableArrayList(EDTproject.getModules());
        comboBoxModule.setItems(listeModule);
    }
}
