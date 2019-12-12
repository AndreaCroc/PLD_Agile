/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

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
public class TronconTest {
    
    Troncon t, t2;
    Intersection i, i2;
    
    public TronconTest() {
        i = new Intersection("123", 12.3, 13.4);
        i2 = new Intersection("456", 15.3, 17.9);

        t = new Troncon("Rue Danton", 12.3, i, i2);
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
     * Test of constructor
     */
    @Test
    public void testConstructeur() {
        System.out.println("Constructeur Troncon");
        assertNotNull("Constructeur", t);
    }

    /**
     * Test of getNomRue method, of class Troncon.
     */
    @Test
    public void testGetNomRue() {
        System.out.println("getNomRue");
        Troncon instance = t;
        String expResult = "Rue Danton";
        String result = instance.getNomRue();
        assertEquals("Le nom de rue n a pas ete recupere correctement", 
                     expResult, result);
    }

    /**
     * Test of setNomRue method, of class Troncon.
     */
    @Test
    public void testSetNomRue() {
        System.out.println("setNomRue");
        String nomRue = "Rue Lumiere";
        Troncon instance = t;
        instance.setNomRue(nomRue);
        assertEquals("Le nom de rue n a pas ete mis a jour correctement", 
                     nomRue, t.getNomRue());
    }

    /**
     * Test of getLongueur method, of class Troncon.
     */
    @Test
    public void testGetLongueur() {
        System.out.println("getLongueur");
        Troncon instance = t;
        Double expResult = 12.3;
        Double result = instance.getLongueur();
        assertEquals("La longueur n a pas ete recuperee correctement", 
                     expResult, result);
    }

    /**
     * Test of setLongueur method, of class Troncon.
     */
    @Test
    public void testSetLongueur() {
        System.out.println("setLongueur");
        Double longueur = 14.4;
        Troncon instance = t;
        instance.setLongueur(longueur);
        assertEquals("La longueur n'a pas ete mise a jour correctement",
                     (Double) 14.4, t.getLongueur());
    }

    /**
     * Test of getOrigine method, of class Troncon.
     */
    @Test
    public void testGetOrigine() {
        System.out.println("getOrigine");
        Troncon instance = t;
        Intersection expResult = i;
        Intersection result = instance.getOrigine();
        assertEquals("L origine n a pas ete recuperee correctement", expResult, 
                     result);
    }

    /**
     * Test of setOrigine method, of class Troncon.
     */
    @Test
    public void testSetOrigine() {
        System.out.println("setOrigine");
        Intersection origine = i2;
        Troncon instance = t;
        instance.setOrigine(origine);
        assertEquals("L origine n a pas ete mise a jour correctement", i2, 
                     t.getOrigine());
    }

    /**
     * Test of getDestination method, of class Troncon.
     */
    @Test
    public void testGetDestination() {
        System.out.println("getDestination");
        Troncon instance = t;
        Intersection expResult = i2;
        Intersection result = instance.getDestination();
        assertEquals("La destination n a pas ete recuperee correctement", 
                     expResult, result);
    }

    /**
     * Test of setDestination method, of class Troncon.
     */
    @Test
    public void testSetDestination() {
        System.out.println("setDestination");
        Intersection destination = i2;
        Troncon instance = t;
        instance.setDestination(destination);
        assertEquals("La destination n'a pas été mise a jour correctement", i2, 
                     t.getDestination());
    }
    
}
