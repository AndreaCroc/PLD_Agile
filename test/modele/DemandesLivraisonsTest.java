/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author labou
 */
public class DemandesLivraisonsTest {
    
    DemandesLivraisons demandesLivraisons;
    Intersection intersection;
    PointInteret pi;
    
    public DemandesLivraisonsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Intersection intersection = new Intersection();
        pi = new PointInteret(intersection,0);
        this.demandesLivraisons = new DemandesLivraisons(pi);
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Test of constructor
     */
    @Test
    public void testConstructeur() {
        System.out.println("DemandesLivraisons");
        assertNotNull("Constructeur", demandesLivraisons);
    }

     /**
     * Test of setHeureDepart method, of class DemandesLivraisons.
     */
    @Test
    public void testSetHeureDepart() {
        System.out.println("setHeureDepart");
        String heureDepart = "3h15";
        demandesLivraisons.setHeureDepart(heureDepart);
        assertEquals("Résultat : ", "3h15", demandesLivraisons.getHeureDepart());
    }
    
    /**
     * Test of getHeureDepart method, of class DemandesLivraisons.
     */
    @Test
    public void testGetHeureDepart() {
        System.out.println("getHeureDepart");
        String expResult = "3h15";
        demandesLivraisons.setHeureDepart(expResult);
        String result = demandesLivraisons.getHeureDepart();
        assertEquals("Résultat : ", expResult, result);
    }

    /**
     * Test of setAdresseDepart method, of class DemandesLivraisons.
     */
    @Test
    public void testSetAdresseDepart() {
         System.out.println("setAdresseDepart");
        PointInteret pi = demandesLivraisons.getAdresseDepart();
        demandesLivraisons.setAdresseDepart(pi);
        assertEquals("Résultat :",pi, demandesLivraisons.getAdresseDepart());
    }
   
    /**
     * Test of getAdresseDepart method, of class DemandesLivraisons.
     */
    @Test
    public void testGetAdresseDepart() {
        System.out.println("getAdresseDepart");
        PointInteret pi = demandesLivraisons.getAdresseDepart();
        demandesLivraisons.setAdresseDepart(pi);
        assertEquals("Résultat :",pi, demandesLivraisons.getAdresseDepart());
        
    }

    
    /**
     * Test of getListePointsInteret method, of class DemandesLivraisons.
     */
    @Test
    public void testGetListePointsInteret() {
        System.out.println("getListePointsInteret");
        ArrayList<PointInteret> pi = new ArrayList<PointInteret>();
        assertEquals("Résultat ",pi, demandesLivraisons.getListePointsInteret());
    }

    /**
     * Test of ajouterPointInteret method, of class DemandesLivraisons.
     */
    @Test
    public void testAjouterPointInteret() {
        System.out.println("ajouterPointInteret");
        PointInteret pI = new PointInteret();
        demandesLivraisons.ajouterPointInteret(pI);
        ArrayList<PointInteret> listePi = new ArrayList<PointInteret>();
        listePi.add(pI);
        assertEquals("Résultat ",listePi, demandesLivraisons.getListePointsInteret());
    }

    /**
     * Test of supprimerLivraison method, of class DemandesLivraisons.
     */
    @Test
    public void testSupprimerLivraison() {
        System.out.println("supprimerLivraison");
        PointInteret pI = new PointInteret();
        demandesLivraisons.ajouterPointInteret(pI);
        demandesLivraisons.supprimerLivraison();
        ArrayList<PointInteret> listePi = new ArrayList<PointInteret>();
        assertEquals("Résultat ",listePi, demandesLivraisons.getListePointsInteret());
    }

    /**
     * Test of getPis method, of class DemandesLivraisons.
     */
    @Test
    public void testGetPis() {
        System.out.println("getPis");
        PointInteret pi1 = new PointInteret();
        PointInteret pi2 = new PointInteret();
        ArrayList<PointInteret> listePi = new ArrayList<PointInteret>();
        listePi.add(pi1);
        listePi.add(pi2);
        demandesLivraisons.ajouterPointInteret(pi1);
        demandesLivraisons.ajouterPointInteret(pi2);
        assertEquals("Résultat ",listePi, demandesLivraisons.getListePointsInteret());
    }
    
}
