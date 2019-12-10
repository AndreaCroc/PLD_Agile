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
public class IntersectionTest {
    
    Intersection intersection;
    
    public IntersectionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        intersection = new Intersection();
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Test of setId method, of class Intersection.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        String id = "2";
        intersection.setId(id);
        assertEquals("2", intersection.getId());
    }

    /**
     * Test of getId method, of class Intersection.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        String id = "2";
        intersection.setId(id);
        String expResult = "2";
        String result = intersection.getId();
        assertEquals(expResult, intersection.getId());
       
    }

    /**
     * Test of setLatitude method, of class Intersection.
     */
    @Test
    public void testSetLatitude() {
        System.out.println("setLatitude");
        Double latitude = 3.14;
        intersection.setLatitude(latitude);
        assertEquals(latitude, intersection.getLatitude());
    }
    
    /**
     * Test of getLatitude method, of class Intersection.
     */
    @Test
    public void testGetLatitude() {
        System.out.println("getLatitude");
        Double latitude = 3.14;
        intersection.setLatitude(latitude);
        Double result = intersection.getLatitude();
        assertEquals(latitude, result);
    }

    /**
     * Test of setLatitude method, of class Intersection.
     */
    @Test
    public void testSetLongitude() {
        System.out.println("setLongitude");
        Double longitude = 3.14;
        intersection.setLongitude(longitude);
        assertEquals(longitude, intersection.getLongitude());
    }
    
    /**
     * Test of getLatitude method, of class Intersection.
     */
    @Test
    public void testGetLongitude() {
        System.out.println("getLongitude");
        Double longitude = 3.14;
        intersection.setLongitude(longitude);
        Double result = intersection.getLongitude();
        assertEquals(longitude, result);
    }
    
     /**
     * Test of setDistance method, of class Intersection.
     */
    @Test
    public void testSetDistance() {
        System.out.println("setLongitude");
        Double distance = 3.18;
        intersection.setDistance(distance);
        assertEquals(distance, intersection.getDistance());
    }
    
    /**
     * Test of getDistance method, of class Intersection.
     */
    @Test
    public void testGetDistance() {
        System.out.println("getDistance");
        Double distance = 3.18;
        intersection.setDistance(distance);
        Double result = intersection.getDistance();
        assertEquals(distance, result);
    }


    /**
     * Test of getTronconsDepart method, of class Intersection.
     */
    @Test
    public void testGetTronconsDepart() {
        System.out.println("getTronconsDepart");
        Troncon t = new Troncon();
        intersection.ajouterTronconDepart(t);
        ArrayList<Troncon> expect =  new ArrayList<Troncon>();
        expect.add(t);
        ArrayList<Troncon> result = intersection.getTronconsDepart();
        assertEquals(expect, result);
       
    }

    /**
     * Test of ajouterTronconDepart method, of class Intersection.
     */
    @Test
    public void testAjouterTronconDepart() {
        System.out.println("ajouterTronconDepart");
        Troncon t = new Troncon();
        intersection.ajouterTronconDepart(t);
        ArrayList<Troncon> expect =  new ArrayList<Troncon>();
        expect.add(t);
        ArrayList<Troncon> result = intersection.getTronconsDepart();
        assertEquals(expect, result);
    }
    
    /**
     * Test of setPredecesseur method, of class Intersection.
     */
    @Test
    public void testSetPredecesseur() {
        System.out.println("setPredecesseur");
        Intersection i = new Intersection();
        intersection.setPredecesseur(i);
        assertEquals(i, intersection.getPredecesseur());
    }

    /**
     * Test of getPredecesseur method, of class Intersection.
     */
    @Test
    public void testGetPredecesseur() {
        System.out.println("getPredecesseur");
        Intersection i = new Intersection();
        intersection.setPredecesseur(i);
        assertEquals(i, intersection.getPredecesseur());
    }

    /**
     * Test of retrouverSuccesseurs method, of class Intersection.
     */
    @Test
    public void testRetrouverSuccesseurs() {
        System.out.println("retrouverSuccesseurs");
        ArrayList<Intersection> listeSuccesseurs = new ArrayList<Intersection>();
        assertEquals(null, intersection.getPredecesseur());
        
    }
    
}
