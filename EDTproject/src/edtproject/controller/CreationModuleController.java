package edtproject.controller;

import edtproject.CodePrincipal.Module;
import edtproject.EDTproject;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 *
 * @author romain
 */
public class CreationModuleController implements Initializable {

    @FXML
    private TextField textFieldSaisieModule;
    
    @FXML
    private ColorPicker colorPicker;
    
    @FXML
    private CheckBox checkBSemestre1, checkBSemestre2, checkBSemestre3, checkBSemestre4;
            
    @FXML
    private Button ButtonAjouterModule;
    private MenuPrincipalController mainController;

    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
        System.out.println("Lancement de l'interface création module...");
        
    }
    
    /**
     *
     */
    @FXML
    public void ajouterModule()
    {
        String leNomModule = null;
        Color saCouleur = null;
        
        try
        {
            leNomModule = textFieldSaisieModule.getText();
            saCouleur   = colorPicker.valueProperty().getValue();
        }
        
        catch(NullPointerException e)
        {
            Alert a     = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Erreur");
            a.setHeaderText("Impossible d'ajouter le module.");
            a.setContentText("Vous devez renseigner les éléments manquants.");
            a.showAndWait();  
        }
        
        if(!leNomModule.isEmpty() && saCouleur != null && (checkBSemestre1.isSelected() || checkBSemestre2.isSelected()|| checkBSemestre3.isSelected()|| checkBSemestre4.isSelected()))
        {
            System.out.println("Module ajouté.");
            if(checkBSemestre1.isSelected())
            {
                EDTproject.getSemestre(1).ajouterModule(leNomModule, saCouleur);
            }
            else if(checkBSemestre2.isSelected())
            {
                EDTproject.getSemestre(2).ajouterModule(leNomModule, saCouleur);
            }
            else if(checkBSemestre3.isSelected())
            {
                EDTproject.getSemestre(3).ajouterModule(leNomModule, saCouleur);
            }
            else
            {
                EDTproject.getSemestre(4).ajouterModule(leNomModule, saCouleur);
            }
            mainController.actualiserFenetres();
            
            Alert a     = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Module ajouté");
            a.setHeaderText("Le module a bien été ajouté.");
            a.setContentText("Module " + leNomModule);
            a.showAndWait();  
        }
        else
        {
            Alert a     = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Erreur");
            a.setHeaderText("Impossible d'ajouter le module.");
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
