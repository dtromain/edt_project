package edtproject.controller;

import edtproject.CodePrincipal.Etudiant;
import edtproject.CodePrincipal.Groupe;
import edtproject.CodePrincipal.Professeur;
import edtproject.CodePrincipal.Session;
import edtproject.CodePrincipal.TypeUtilisateur;
import edtproject.EDTproject;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 */
public class CreationSessionController implements Initializable {

    @FXML
    private ComboBox<String> comboBTypeSession;
    @FXML
    private Label lblGroupe;
    @FXML
    private ComboBox<Groupe> comboBGroupe;
    @FXML
    private TextField tfNom, tfPrenom, tfID;
    @FXML 
    private Button btnAdd;
    
    private MenuPrincipalController mainController;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        ObservableList<String> liste = FXCollections.observableArrayList("Professeur", "Elève");
        comboBTypeSession.setItems(liste);
        comboBTypeSession.setValue(liste.get(0));
        lblGroupe.setVisible(false); lblGroupe.setDisable(true); comboBGroupe.setVisible(false); comboBGroupe.setDisable(true);
        
        StringConverter<Groupe> affichageGroupe =  new StringConverter<Groupe>() 
        {
            public String toString(Groupe g) {
                return g.getNom();
            }

            public Groupe fromString(String nomGroupe) 
            {
                Groupe g = new Groupe(nomGroupe, 1);
                return g;
            }
        };;
        comboBGroupe.setConverter(affichageGroupe);
        
        comboBTypeSession.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(comboBTypeSession.getValue().equals("Elève"))
                {
                    lblGroupe.setVisible(true); lblGroupe.setDisable(false);
                    comboBGroupe.setVisible(true); comboBGroupe.setDisable(false);
                }
                else
                {
                    lblGroupe.setVisible(false); lblGroupe.setDisable(true);
                    comboBGroupe.setVisible(false); comboBGroupe.setDisable(true);
                }
                
                ObservableList<Groupe> listeGroupe = FXCollections.observableArrayList();
                listeGroupe.addAll(EDTproject.getGroupesTP());
                comboBGroupe.setItems(listeGroupe);
            }
        });
        
        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) 
            {
                String nom = null;
                String prenom = null;
                String identifiant = null;
                
                try
                {
                    nom = tfNom.getText();
                    prenom = tfPrenom.getText();
                    identifiant = tfID.getText();
                }
                catch(NullPointerException e)
                {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("Erreur");
                    a.setHeaderText("Impossible d'ajouter la session.");
                    a.setContentText("Vous devez renseigner les éléments manquants.");
                    a.showAndWait();
                }
                
                if(!nom.isEmpty() && !prenom.isEmpty() && !identifiant.isEmpty())
                {
                    if(comboBTypeSession.getValue().equals("Elève"))
                    {
                        EDTproject.getSessions().add(new Session(identifiant, TypeUtilisateur.ELEVE));
                        EDTproject.getEtudiants().add(new Etudiant(comboBGroupe.getValue(),
                                                                   prenom,
                                                                   nom,
                                                                   identifiant));
                    }
                    else
                    {
                        EDTproject.getSessions().add(new Session(identifiant, TypeUtilisateur.PROFESSEUR));
                        EDTproject.getProfesseurs().add(new Professeur(prenom,
                                                                       nom,
                                                                       identifiant));
                    }
                }
                else
                {
                    Alert a     = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("Erreur");
                    a.setHeaderText("Impossible d'ajouter la session.");
                    a.setContentText("Vous devez renseigner les éléments manquants.");
                    a.showAndWait();
                }
            }
        });
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
        ObservableList<Groupe> listeGroupe = FXCollections.observableArrayList();
        listeGroupe.addAll(EDTproject.getGroupesTP());
        comboBGroupe.setItems(listeGroupe);

    }
}
