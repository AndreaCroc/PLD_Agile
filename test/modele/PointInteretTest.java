package modele;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/*
 * PointInteretTest
 *
 * Version 1
 *
 * 
 * Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU,
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
public class PointInteretTest {
    
    private PointInteret pointInteret;
    
    public PointInteretTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        pointInteret = new PointInteret();
    }
    
    @After
    public void tearDown() {
        pointInteret = null;
    }
    
    /**
     * Test du constructeur sans parametre de la classe PointInteret.
     */
    @Test
    public void testPointInteret1(){
        assertNotNull("L'instance n'est pas créée", pointInteret);
    }
    
    /**
     * Test du constructeur avec parametres de la classe PointInteret.
     */
    @Test
    public void testPointInteret2(){
        Intersection intersection = new Intersection();
        int duree = 0;
        pointInteret = new PointInteret(intersection, duree);
        assertNotNull("L'instance n'est pas créée", pointInteret);
    }

    /**
     * Test de la methode getIntersection de la classs PointInteret.
     */
    @Test
    public void testGetIntersection() {
        System.out.println("getIntersection");
        
        //Test 1 : l intersection est nulle
        Intersection expResult = null;
        Intersection result = pointInteret.getIntersection();
        assertEquals("L'intersection est incorrecte",expResult, result);
        
        //Test 2 : l intersection est non nulle
        Intersection intersection = new Intersection();
        pointInteret.setIntersection(intersection);
        expResult = intersection;
        result = pointInteret.getIntersection();
        assertEquals("L'intersection est incorrecte",expResult, result);
        
        //Test 3 : l intersection possede attribut avec valeurs
        String id = "1";
        Double latitude = 11.6;
        Double longitude = 44.9;
        Intersection intersection2 = new Intersection(id,latitude,longitude);
        pointInteret.setIntersection(intersection2);
        expResult = intersection2;
        result = pointInteret.getIntersection();
        assertEquals("L'intersection est incorrecte",expResult, result);
    }

    /**
     * Test de la methode setIntersection de la classe PointInteret.
     */
    @Test
    public void testSetIntersection() {
        System.out.println("setIntersection");
        
        //Test 1 : l intersection est nulle
        Intersection intersection = null;
        pointInteret.setIntersection(intersection);
        Intersection expResult = null;
        Intersection result = pointInteret.getIntersection();
        assertEquals("L'intersection est incorrecte",expResult, result);
        
        //Test 2 : l intersection est non nulle
        intersection = new Intersection();
        pointInteret.setIntersection(intersection);
        expResult = intersection;
        result = pointInteret.getIntersection();
        assertEquals("L'intersection est incorrecte",expResult, result);
        
        //Test 3 : l intersection possede attribut avec valeurs
        String id = "1";
        Double latitude = 11.6;
        Double longitude = 44.9;
        Intersection intersection2 = new Intersection(id,latitude,longitude);
        pointInteret.setIntersection(intersection2);
        expResult = intersection2;
        result = pointInteret.getIntersection();
        assertEquals("L'intersection est incorrecte",expResult, result);
        
    }

    /**
     * Test de la methode getDuree de la classe PointInteret.
     */
    @Test
    public void testGetDuree() {
        System.out.println("getDuree");
        
        //Test 1 : la duree est nulle
        Integer expResult = null;
        Integer result = pointInteret.getDuree();
        assertEquals("La durée est incorrecte",expResult, result);
        
        //Test 2 : la duree est non nulle
        Integer duree = 5;
        pointInteret.setDuree(duree);
        expResult = duree;
        result = pointInteret.getDuree();
        assertEquals("La durée est incorrecte",expResult, result);
    }

    /**
     * Test de la methode setDuree de la classe PointInteret.
     */
    @Test
    public void testSetDuree() {
        System.out.println("setDuree");
        
        //Test 1 : la duree est nulle
        Integer duree = null;
        pointInteret.setDuree(duree);
        Integer result = pointInteret.getDuree();
        assertEquals("La durée est incorrecte",duree, result);
        
        //Test 2 : la duree est non nulle
        duree = 5;
        pointInteret.setDuree(duree);
        result = pointInteret.getDuree();
        assertEquals("La durée est incorrecte",duree, result);
        
    }

    /**
     * Test de la methode isEnlevement de la classe PointInteret.
     */
    @Test
    public void testIsEnlevement() {
        System.out.println("isEnlevement");
        
        //Test 1 : l enlevement est faux
        boolean expResult = false;
        boolean result = pointInteret.isEnlevement();
        assertEquals("L'enlèvement est incorrect",expResult, result);
        
        //Test 2 : l enlevement est vrai
        expResult = true;
        pointInteret.setEnlevement(true);
        result = pointInteret.isEnlevement();
        assertEquals("L'enlèvement est incorrect",expResult, result);
    }

    /**
     * Test de la methode setEnlevement de la classe PointInteret.
     */
    @Test
    public void testSetEnlevement() {
        System.out.println("setEnlevement");
        
        //Test 1 : l enlevement est faux
        boolean expResult = false;
        pointInteret.setEnlevement(false);
        boolean result = pointInteret.isEnlevement();
        assertEquals("L'enlèvement est incorrect",expResult, result);
        
        //Test 2 : l enlevement est vrai
        expResult = true;
        pointInteret.setEnlevement(true);
        result = pointInteret.isEnlevement();
        assertEquals("L'enlèvement est incorrect",expResult, result);
    }

    /**
     * Test de la methode getHeureDepart de la classe PointInteret.
     */
    @Test
    public void testGetHeureDepart() {
        System.out.println("getHeureDepart");
        
        //Test 1 : l heure de depart est nulle
        String expResult = null;
        String result = pointInteret.getHeureDepart();
        assertEquals("L'heure de départ est incorrecte",expResult, result);
        
        //Test 2 : l heure de depart est non nulle
        expResult = "";
        pointInteret.setHeureDepart("");
        result = pointInteret.getHeureDepart();
        assertEquals("L'heure de départ est incorrecte",expResult, result);
    }

    /**
     * Test de la methode isEntrepot de la classe PointInteret.
     */
    @Test
    public void testIsEntrepot() {
        System.out.println("isEntrepot");
        
        //Test 1 : l entrepot est faux
        boolean expResult = false;
        boolean result = pointInteret.isEntrepot();
        assertEquals("L'entrepôt est incorrect",expResult, result);
        
        //Test 2 : l entrepot est vrai
        expResult = true;
        pointInteret.setEntrepot(true);
        result = pointInteret.isEntrepot();
        assertEquals("L'entrepôt est incorrect",expResult, result);
        
    }

    /**
     * Test de la methode de setEntrepot de la classe PointInteret.
     */
    @Test
    public void testSetEntrepot() {
        System.out.println("setEntrepot");
        
        //Test 1 : l entrepot est faux
        boolean expResult = false;
        pointInteret.setEntrepot(false);
        boolean result = pointInteret.isEntrepot();
        assertEquals("L'entrepôt est incorrect",expResult, result);
        
        //Test 2 : l entrepot est vrai
        expResult = true;
        pointInteret.setEntrepot(true);
        result = pointInteret.isEntrepot();
        assertEquals("L'entrepôt est incorrect",expResult, result);
    }

    /**
     * Test de la methode setHeureDepart de la classe PointInteret.
     */
    @Test
    public void testSetHeureDepart() {
        System.out.println("setHeureDepart");
        
        //Test 1 : l heure de depart est nulle
        String heureDepart = null;
        pointInteret.setHeureDepart(heureDepart);
        String result = pointInteret.getHeureDepart();
        assertEquals("L'heure de départ est incorrecte",heureDepart, result);
        
        //Test 2 : l heure de depart est non nulle
        heureDepart = "";
        pointInteret.setHeureDepart("");
        result = pointInteret.getHeureDepart();
        assertEquals("L'heure de départ est incorrecte",heureDepart, result);
                
    }

    /**
     * Test of getHeureArrivee method, of class PointInteret.
     */
    @Test
    public void testGetHeureArrivee() {
        System.out.println("getHeureArrivee");
        
        //Test 1 : l heure d arrivee est nulle
        String expResult = null;
        String result = pointInteret.getHeureArrivee();
        assertEquals("L'heure d'arrivée est incorrecte",expResult, result);
        
        //Test 2 : l heure d arrivee est nulle
        expResult = "";
        pointInteret.setHeureArrivee("");
        result = pointInteret.getHeureArrivee();
        assertEquals("L'heure d'arrivée est incorrecte",expResult, result);
    }

    /**
     * Test de la methode setHeureArrivee de la classe PointInteret.
     */
    @Test
    public void testSetHeureArrivee() {
        System.out.println("setHeureArrivee");
        
        //Test 1 : l heure d arrivee est nulle
        String expResult = null;
        pointInteret.setHeureArrivee(null);
        String result = pointInteret.getHeureArrivee();
        assertEquals("L'heure d'arrivée est incorrecte",expResult, result);
        
        //Test 2 : l heure d arrivee est non nulle
        expResult = "";
        pointInteret.setHeureArrivee("");
        result = pointInteret.getHeureArrivee();
        assertEquals("L'heure d'arrivée est incorrecte",expResult, result);
    }

    /**
     * Test de la methode getPointDependance de la classe PointInteret.
     */
    @Test
    public void testGetPointDependance() {
        System.out.println("getPointDependance");
        
        //Test 1 : le point de dependance est null
        PointInteret expResult = null;
        PointInteret result = pointInteret.getPointDependance();
        assertEquals("Le point de dependance est incorrect",expResult, result);
        
        //Test 2 : le point de dependance est non null
        expResult = new PointInteret();
        pointInteret.setPointDependance(expResult);
        result = pointInteret.getPointDependance();
        assertEquals("Le point de dependance est incorrect",expResult, result);
    }

    /**
     * Test de la methode setPointDependance de la classe PointInteret.
     */
    @Test
    public void testSetPointDependance() {
        System.out.println("setPointDependance");
        
        //Test 1 : le point de dependance est null
        PointInteret pointDependance = null;
        pointInteret.setPointDependance(pointDependance);
        PointInteret result = pointInteret.getPointDependance();
        assertEquals("Le point de dependance est incorrect",pointDependance, result);
        
        //Test 2 : le point de dependance
        pointDependance = new PointInteret();
        pointInteret.setPointDependance(pointDependance);
        result = pointInteret.getPointDependance();
        assertEquals("Le point de dependance est incorrect",pointDependance, result);
    }

    /**
     * Test de la methode getCheminDepart de la classe PointInteret.
     */
    @Test
    public void testGetCheminDepart() {
        System.out.println("getCheminDepart");
        
        //Test 1 : le chemin de depart est null
        Chemin expResult = null;
        Chemin result = pointInteret.getCheminDepart();
        assertEquals("Le chemin de départ est incorrect",expResult, result);
        
        //Test 2 : le chemin de depart est non null
        expResult = new Chemin();
        pointInteret.setCheminDepart(expResult);
        result = pointInteret.getCheminDepart();
        assertEquals("Le chemin de départ est incorrect",expResult, result);
    }

    /**
     * Test de la methode setCheminDepart method de la classe PointInteret.
     */
    @Test
    public void testSetCheminDepart() {
        System.out.println("setCheminDepart");
        
        //Test 1 : le chemin de depart est null
        Chemin expResult = null;
        pointInteret.setCheminDepart(expResult);
        Chemin result = pointInteret.getCheminDepart();
        assertEquals("Le chemin de départ est incorrect",expResult, result);
        
        //Test 2 : le chemin de depart estnon  null
        expResult = new Chemin();
        pointInteret.setCheminDepart(expResult);
        result = pointInteret.getCheminDepart();
        assertEquals("Le chemin de départ est incorrect",expResult, result);
    }

    /**
     * Test de la methode getNumeroDemande de la classe PointInteret.
     */
    @Test
    public void testGetNumeroDemande() {
        System.out.println("getNumeroDemande");
        
        //Test 1 : le numero de demande est null
        Integer expResult = null;
        Integer result = pointInteret.getNumeroDemande();
        assertEquals("Le numéro de demande est incorrect",expResult, result);
        
        //Test 2 : le numero de demande est non null
        expResult = 0;
        pointInteret.setNumeroDemande(0);
        result = pointInteret.getNumeroDemande();
        assertEquals("Le numéro de demande est incorrect",expResult, result);
    }

    /**
     * Test de la methode setNumeroDemande de la classe PointInteret.
     */
    @Test
    public void testSetNumeroDemande() {
        System.out.println("setNumeroDemande");
        
        //Test 1 : le numero de demande est null
        Integer num = null;
        pointInteret.setNumeroDemande(num);
        Integer result = pointInteret.getNumeroDemande();
        assertEquals("Le numéro de demande est incorrect",num, result);
        
        //Test 2 : le numero de demande est non null
        num = 1;
        pointInteret.setNumeroDemande(1);
        result = pointInteret.getNumeroDemande();
        assertEquals("Le numéro de demande est incorrect",num, result);
    }
    
}
