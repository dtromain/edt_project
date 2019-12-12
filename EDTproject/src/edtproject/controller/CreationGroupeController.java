package edtproject.controller;

import edtproject.CodePrincipal.Groupe;
import edtproject.EDTproject;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

/**
 *
 * @author romain
 */
public class CreationGroupeController implements Initializable {

    @FXML   private TextField textFieldSaisieGroupe;
    
    @FXML   private RadioButton radioBAnnee1, radioBAnnee2;
    
    @FXML   private Spinner<Integer> spinNbTP;
    
    @FXML   private Button buttonAjouterGroupe;
    
    private MenuPrincipalController mainController;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
        System.out.println("Lancement de l'interface Creation Groupe...");
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 9, 2);
        
        spinNbTP.setValueFactory(valueFactory);
    }
    
    /**
     *
     */
    @FXML
    public void ajouterGroupe()
    {
        String leNomGroupe = null;
        
        try
        {
            leNomGroupe = textFieldSaisieGroupe.getText();
        }
        
        catch(NullPointerException e)
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Erreur");
            a.setHeaderText("Impossible d'ajouter le groupe.");
            a.setContentText("Vous devez renseigner les éléments manquants.");
            a.showAndWait();
        }
        
        if(!leNomGroupe.isEmpty() && (radioBAnnee1.isSelected() || radioBAnnee2.isSelected()))
        {
            if(radioBAnnee1.isSelected())
            {
                for(int i = 1; i <= spinNbTP.getValue(); i++)
                {
                    boolean test = true;
                    for(Groupe g : EDTproject.getGroupes(1))
                    {
                        if(g.getNom().equals(leNomGroupe + i))
                        {
                            test = false;
                        }
                    }
                    if(test == true)
                    {
                        new Groupe(leNomGroupe + i, 1);
                    }
                }
            }
            else
            {
                for(int i = 1; i <= spinNbTP.getValue(); i++)
                {
                    boolean test = true;
                    for(Groupe g : EDTproject.getGroupes(2))
                    {
                        if(g.getNom().equals(leNomGroupe + i))
                        {
                            test = false;
                        }
                    }
                    if(test == true)
                    {
                        new Groupe(leNomGroupe + i, 2);
                    }
                }
            }
            
            mainController.actualiserFenetres();
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Groupe ajouté");
            a.setHeaderText("Le groupe a bien été ajouté.");
            a.setContentText("Groupe " + leNomGroupe);
            a.showAndWait();
        }
        else
        {
            Alert a     = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Erreur");
            a.setHeaderText("Impossible d'ajouter le groupe.");
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
