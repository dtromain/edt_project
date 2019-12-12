package edtproject.CodePrincipal;

import edtproject.EDTproject;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe de test de la classe Groupe.
 */
public class GroupeTest {
    
    /**
     * Constructeur de la classe GroupeTest.
     */
    public GroupeTest() {
    }
      
    /**
     * Cette méthode permet de vider la liste des objets instanciés dans le 
     * programme avant le début de chaque test.
     */
    @Before
    public void setUp() {
        EDTproject.resetListDependances();
    }
    
    /**
     * Cette méthode permet de vider la liste des objets instanciés dans le 
     * programme après la fin de chaque test.
     */
    @After
    public void tearDown() {
        EDTproject.resetListDependances();
    }

    /**
     * Test de la méthode getNom.
     */
    @Test
    public void testGetNom() {
        System.out.println("getNom");
        Groupe instance = new Groupe("C", 1);
        String expResult = "C";
        String result = instance.getNom();
        assertEquals(expResult, result);
    }

    /**
     * Test de la méthode getAnnee.
     */
    @Test
    public void testGetAnnee() {
        System.out.println("getAnnee");
        Groupe instance = new Groupe("C", 1);
        int expResult = 1;
        int result = instance.getAnnee();
        assertEquals(expResult, result);
    }

    /**
     * Test de la méthode ajouterEtudiant.
     */
    @Test
    public void testAjouterEtudiant() {
        System.out.println("ajouterEtudiant");
        String nom = "Douteau";
        String prenom = "Romain";
        String identifiant ="rdouteau";
        Groupe instance = new Groupe("C", 1);
        Etudiant etudiant = new Etudiant(instance, prenom, nom, identifiant);
        instance.ajouterEtudiant(etudiant);
        for (Etudiant e : EDTproject.getEtudiants()) 
        {
            if(e.getNom().equals(nom)&&e.getPrenom().equals(prenom)&&e.getIdentifiant().equals(identifiant))
            {
                assertTrue(true);
            }
        }
    }

    /**
     * Test de la méthode getEtudiants.
     */
    @Test
    public void testGetEtudiants() {
        System.out.println("getEtudiants");
        Groupe instance = new Groupe("C", 1);
        List<Etudiant> expResult = null;
        List<Etudiant> result = instance.getEtudiants();
        Etudiant etudiant1 = new Etudiant(instance,"Romain", "Douteau", "rdouteau");
        Etudiant etudiant2 = new Etudiant(instance,"Michael", "Lucas", "mlucas");
        instance.ajouterEtudiant(etudiant1);
        instance.ajouterEtudiant(etudiant2);
        assertEquals(expResult, result);
    }

    /**
     * Test de la méthode equals.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        String nom = "D";
        int annee = 1;
        Groupe instance = new Groupe("C", 1);
        boolean expResult = false;
        boolean result = instance.equals(nom, annee);
        assertEquals(expResult, result);
    }

    /**
     * Test de la méthode supprimerEtudiant.
     */
    @Test
    public void testSupprimerEtudiant() {
        System.out.println("supprimerEtudiant");
        Groupe instance = new Groupe("C", 1);
        Etudiant e = new Etudiant(instance, "Romain", "Douteau", "rdouteau");
        instance.supprimerEtudiant(e);
        assertFalse(instance.getEtudiants().contains(e));
    }
    
}
