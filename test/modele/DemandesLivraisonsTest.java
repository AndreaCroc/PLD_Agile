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
     * Test of getHeureDepart method, of class DemandesLivraisons.
     */
    @Test
    public void testGetHeureDepart() {
        System.out.println("getHeureDepart");
        DemandesLivraisons instance = null;
        String expResult = "";
        String result = instance.getHeureDepart();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setHeureDepart method, of class DemandesLivraisons.
     */
    @Test
    public void testSetHeureDepart() {
        System.out.println("setHeureDepart");
        String heureDepart = "";
        DemandesLivraisons instance = null;
        instance.setHeureDepart(heureDepart);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAdresseDepart method, of class DemandesLivraisons.
     */
    @Test
    public void testGetAdresseDepart() {
        System.out.println("getAdresseDepart");
        DemandesLivraisons instance = null;
        PointInteret expResult = null;
        PointInteret result = instance.getAdresseDepart();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAdresseDepart method, of class DemandesLivraisons.
     */
    @Test
    public void testSetAdresseDepart() {
        System.out.println("setAdresseDepart");
        PointInteret aD = null;
        DemandesLivraisons instance = null;
        instance.setAdresseDepart(aD);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getListePointsInteret method, of class DemandesLivraisons.
     */
    @Test
    public void testGetListePointsInteret() {
        System.out.println("getListePointsInteret");
        DemandesLivraisons instance = null;
        ArrayList<PointInteret> expResult = null;
        ArrayList<PointInteret> result = instance.getListePointsInteret();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ajouterPointInteret method, of class DemandesLivraisons.
     */
    @Test
    public void testAjouterPointInteret() {
        System.out.println("ajouterPointInteret");
        PointInteret pI = null;
        DemandesLivraisons instance = null;
        instance.ajouterPointInteret(pI);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of supprimerLivraison method, of class DemandesLivraisons.
     */
    @Test
    public void testSupprimerLivraison() {
        System.out.println("supprimerLivraison");
        DemandesLivraisons instance = null;
        instance.supprimerLivraison();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPis method, of class DemandesLivraisons.
     */
    @Test
    public void testGetPis() {
        System.out.println("getPis");
        DemandesLivraisons instance = null;
        ArrayList<PointInteret> expResult = null;
        ArrayList<PointInteret> result = instance.getPis();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
