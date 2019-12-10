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
 * @author Alexanne
 */
public class CheminTest {
    Intersection i, i2, i3;
    ArrayList<Troncon> lesTroncons;
    Troncon t1, t2;
    Chemin c;
    
    public CheminTest() {
        i = new Intersection("123", 12.3, 13.4);
        i2 = new Intersection("456", 15.3, 17.9);
        i3 = new Intersection("789", 14.3, 15.7);

        lesTroncons = new ArrayList<>();
        t1 = new Troncon("Rue Danton", 12.3, i, i2);
        t2 = new Troncon("Rue Victor Hugo", 5.6, i2, i3);

        lesTroncons.add(t1);
        lesTroncons.add(t2);
        
        c = new Chemin(i, i3, lesTroncons);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getDepart method, of class Chemin.
     */
    @Test
    public void testGetDepart() {
        System.out.println("getDepart");
        Intersection expected = new Intersection("123", 12.3, 13.4);
        assertEquals(expected, c.getDepart());
    }

    /**
     * Test of setDepart method, of class Chemin.
     */
    @Test
    public void testSetDepart() {
        System.out.println("setDepart");
        Intersection depart = i2;
        Chemin instance = c;
        instance.setDepart(depart);
        assertEquals(i2, c.getDepart());
    }

    /**
     * Test of getArrivee method, of class Chemin.
     */
    @Test
    public void testGetArrivee() {
        System.out.println("getArrivee");
        Intersection expResult = new Intersection("789", 14.3, 15.7);
        Intersection result = c.getArrivee();
        assertEquals("L'arrivée de la tournée n'est pas correcte", expResult, result);
    }

    /**
     * Test of setArrivee method, of class Chemin.
     */
    @Test
    public void testSetArrivee() {
        System.out.println("setArrivee");
        Intersection arrivee = new Intersection("321", 14.5, 15.2);
        Chemin instance = c;
        assertEquals(i3, c.getArrivee());
        instance.setArrivee(arrivee);
        assertEquals("L'arrivée n'a pas été mise a jour correctement", arrivee, c.getArrivee());
    }

    /**
     * Test of getLongueur method, of class Chemin.
     */
    @Test
    public void testGetLongueur() {
        System.out.println("getLongueur");
        Double expResult = 17.9;
        Double result = c.getLongueur();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLongueur method, of class Chemin.
     */
    @Test
    public void testSetLongueur() {
        System.out.println("setLongueur");
        Double longueur = 14.3;
        Chemin instance = c;
        instance.setLongueur(longueur);
        assertEquals((Double)14.3, c.getLongueur());
    }

    /**
     * Test of getDureeTrajet method, of class Chemin.
     */
    @Test
    public void testGetDureeTrajet() {
        System.out.println("getDureeTrajet");
        Integer expResult = 17;
        Integer result = c.getDureeTrajet();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDureeTrajet method, of class Chemin.
     */
    @Test
    public void testSetDureeTrajet() {
        System.out.println("setDureeTrajet");
        Integer dureeTrajet = 4;
        Chemin instance = c;
        instance.setDureeTrajet(dureeTrajet);
        assertEquals((Integer)4, c.getDureeTrajet());
    }

    /**
     * Test of getSuccessionTroncons method, of class Chemin.
     */
    @Test
    public void testGetSuccessionTroncons() {
        System.out.println("getSuccessionTroncons");
        Chemin instance = c;
        ArrayList<Troncon> expResult = new ArrayList<>();
        expResult.add(t1);
        expResult.add(t2);
        ArrayList<Troncon> result = instance.getSuccessionTroncons();
        assertEquals(expResult, result);
    }

    /**
     * Test of ajouterTroncon method, of class Chemin.
     */
    @Test
    public void testAjouterTroncon() {
        System.out.println("ajouterTroncon");
        Troncon troncon = new Troncon("Rue Feyssine", 13.3, i, i2);
        Chemin instance = c;
        instance.ajouterTroncon(troncon);
    }

    /**
     * Test of calculerLongueur method, of class Chemin.
     */
    @Test
    public void testCalculerLongueur() {
        System.out.println("calculerLongueur");
        Chemin instance = c;
        Double expResult = 17.9;
        Double result = instance.calculerLongueur();
        assertEquals(expResult, result);
    }

    /**
     * Test of calculerDureeTrajet method, of class Chemin.
     */
    @Test
    public void testCalculerDureeTrajet() {
        System.out.println("calculerDureeTrajet");
        Chemin instance = c;
        Integer expResult = 17;
        Integer result = instance.calculerDureeTrajet();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Chemin.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Chemin instance = c;
        String expResult = "Chemin de 123 à 789";
        String result = instance.toString();
        assertEquals("Le chemin ne s'affiche pas correctement", expResult, result);
    }
    
}
