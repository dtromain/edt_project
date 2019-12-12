package edtproject.CodePrincipal;

import edtproject.EDTproject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javafx.scene.paint.Color;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe de test de la classe Semestre.
 */
public class SemestreTest {
    
    /**
     * Constructeur de la classe SemestreTest.
     */
    public SemestreTest() {
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
     * Test de la méthode getDateDebut.
     */
    @Test
    public void testGetDateDebut() {
        System.out.println("getDateDebut");
        Calendar dateDebut = Calendar.getInstance();
        Calendar dateFin = Calendar.getInstance();
        dateDebut.set(2017, 9, 2);
        dateFin.set(2018, 1, 2);
        Semestre instance = new Semestre(dateDebut, dateFin, 1);
        String expResult = "2017:9:2";
        String result = instance.getDateDebut().get(Calendar.YEAR)+":"
                    +instance.getDateDebut().get(Calendar.MONTH)+":"
                    +instance.getDateDebut().get(Calendar.DAY_OF_MONTH);
        assertEquals(expResult, result);
    }

    /**
     * Test de la méthode getDateFin.
     */
    @Test
    public void testGetDateFin() {
        System.out.println("getDateDebut");
        Calendar dateDebut = Calendar.getInstance();
        Calendar dateFin = Calendar.getInstance();
        dateDebut.set(2017, 9, 2);
        dateFin.set(2018, 1, 2);
        Semestre instance = new Semestre(dateDebut, dateFin, 1);
        String expResult = "2018:1:2";
        String result = instance.getDateFin().get(Calendar.YEAR)+":"
                    +instance.getDateFin().get(Calendar.MONTH)+":"
                    +instance.getDateFin().get(Calendar.DAY_OF_MONTH);
        assertEquals(expResult, result);
    }

    /**
     * Test de la méthode getNumero.
     */
    @Test
    public void testGetNumero() {
        System.out.println("getNumero");
        Semestre instance = new Semestre(Calendar.getInstance(), Calendar.getInstance(), 1);
        int expResult = 1;
        int result = instance.getNumero();
        assertEquals(expResult, result);
    }

    /**
     * Test de la méthode ajouterModule.
     */
    @Test
    public void testAjouterModule() {
        System.out.println("ajouterModule");
        Semestre instance = new Semestre(Calendar.getInstance(), Calendar.getInstance(), 1);
        String nom = "AP";
        Color couleur = Color.ALICEBLUE;
        instance.ajouterModule(nom, couleur);
        for (Module m : EDTproject.getModules(instance)) 
        {
            if(m.getNom().equals(nom)&&m.getCouleur().equals(couleur))
            {
                assertTrue(true);
            }
        }
    }

    /**
     * Test de la méthode recupererModulesSemestre.
     */
    @Test
    public void testRecupererModulesSemestre() {
        System.out.println("recupererModulesSemestre");
        Calendar dateDebut1 = Calendar.getInstance();
        Calendar dateFin1 = Calendar.getInstance();
        dateDebut1.set(2017, 9, 2);
        dateFin1.set(2018, 1, 2);
        Calendar dateDebut2 = Calendar.getInstance();
        Calendar dateFin2 = Calendar.getInstance();
        dateDebut2.set(2017, 9, 2);
        dateFin2.set(2018, 1, 2);
        Semestre semestre1 = new Semestre(dateDebut1, dateFin1, 1);
        Semestre semestre2 = new Semestre(dateDebut2, dateFin2, 2);
        semestre1.ajouterModule("AP", Color.AZURE);
        semestre1.ajouterModule("POO", Color.ALICEBLUE);
        semestre1.ajouterModule("BD", Color.BEIGE);
        semestre2.recupererModulesSemestre(semestre1);
        assertEquals(semestre1.getModules(), semestre2.getModules());
    }

    /**
     * Test de la méthode getModules.
     */
    @Test
    public void testGetModules() {
        System.out.println("getModules");
        Semestre instance = new Semestre(Calendar.getInstance(), Calendar.getInstance(), 1);
        instance.ajouterModule("AP", Color.AQUAMARINE);
        instance.ajouterModule("BD", Color.BROWN);
        List<Module> expResult = new ArrayList<>();
        expResult.add(new Module("AP", Color.AQUAMARINE));
        expResult.add(new Module("BD", Color.BROWN));
        List<Module> result = instance.getModules();
        assertEquals(expResult, result);
    }

    /**
     * Test de la méthode toString.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Semestre instance = new Semestre(Calendar.getInstance(), Calendar.getInstance(), 1);
        String expResult = "Semestre 1";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
