package edtproject.controller;

import edtproject.CodePrincipal.Salle;
import edtproject.CodePrincipal.TypeSalle;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 *
 * @author romain
 */
public class CreationSalleController implements Initializable
{
    @FXML
    private Button buttonAjouterSalle;
    
    @FXML
    private ComboBox<TypeSalle> comboBTypeSalle;
    
    @FXML
    private TextField textFieldSaisieSalle;
    
    private MenuPrincipalController mainController;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
        System.out.println("Lancement de l'interface Creation Salle...");
        ObservableList<TypeSalle> listeTypeSalle = FXCollections.observableArrayList(TypeSalle.AMPHI, TypeSalle.RESEAU, 
                                                                                     TypeSalle.TD,    TypeSalle.TP);
        comboBTypeSalle.setItems(listeTypeSalle);
    }
    
    /**
     *
     */
    @FXML
    public void ajouterSalle()
    {
        String nomSalle = null;
        
        try
        {
            nomSalle = textFieldSaisieSalle.textProperty().getValue();
        }
        
        catch(NullPointerException e)
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Erreur");
            a.setHeaderText("Impossible d'ajouter la salle.");
            a.setContentText("Vous devez renseigner les éléments manquants.");
            a.showAndWait();  
        }
         
        if(!nomSalle.isEmpty())
        {
            new Salle(nomSalle, comboBTypeSalle.getValue());
            mainController.actualiserFenetres();
            
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Salle ajoutée");
            a.setHeaderText("La salle a bien été ajoutée.");
            a.setContentText("Salle " + nomSalle);
            a.showAndWait();  
        }
        else
        {
            Alert a     = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Erreur");
            a.setHeaderText("Impossible d'ajouter la salle.");
            a.setContentText("Vous devez renseigner les éléments manquants.");
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
}
