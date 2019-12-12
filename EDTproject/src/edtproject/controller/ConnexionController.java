package edtproject.controller;

import edtproject.CodePrincipal.Groupe;
import edtproject.EDTproject;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 */
public class ConnexionController implements Initializable {

    @FXML
    private Button btnSeConnecter;
    @FXML
    private TextField txtFieldID;
    @FXML
    private PasswordField passwordFieldMDP;
    @FXML
    private Text textErreur;
    
    private MenuPrincipalController controller;
    private String identifiant, motDePasse;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
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
        
        
        System.out.println("Connexion...");
        
        // cacher le text d'erreur
        textErreur.setVisible(false);
        
        passwordFieldMDP.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER))
                {
                    seConnecter();
                }
            }
        });
    }
    
    /**
     *
     */
    @FXML
    public void seConnecter()
    {
        System.out.println("Connexion en cours...");
          
        // récupérer les valeurs
        identifiant = txtFieldID.textProperty().getValue();
        motDePasse = passwordFieldMDP.textProperty().getValue();
        
        if(identifiant.length() == 0)
        {
            System.out.println("Identifiant est vide");
            identifiant = null;
        }
        
        if(motDePasse.length() == 0)
        {
            System.out.println("Mot de passe est vide");
            motDePasse = null;
        }
        
        System.out.println(identifiant);
        System.out.println(motDePasse);
        
        // test si les champs sont remplis
        if(identifiant != null && motDePasse != null)
        {
            // connexion en base... mais où est la base ?!
            System.out.println("Je suis connecté.");
            
            // recherche dans la classe session s'il existe un utilisateur avec
            // cet identifiant et mot de passe
            if(EDTproject.setUserConnecte(identifiant, motDePasse))
            {
                Stage stage = (Stage) btnSeConnecter.getScene().getWindow();
                stage.close();
            }
            else
            {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Erreur de connexion");
                a.setHeaderText("Erreur");
                a.setContentText("Vous devez renseigner un identifiant et un mot de passe valide.");
                a.showAndWait();
            }
        }
        
        else
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Erreur de connexion");
            a.setHeaderText("Erreur");
            a.setContentText("Vous devez renseigner tous les champs nécessaires pour vous connecter.");
            a.showAndWait();
            
            textErreur.setVisible(true);
        }
    }
}

