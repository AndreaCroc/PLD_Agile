package modele;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/*
 * TourneeTest
 *
 * Version 1
 *
 * 
 * Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU,
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
public class TourneeTest {
    
    private Tournee tournee;
    
    public TourneeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        tournee = new Tournee();
    }
    
    @After
    public void tearDown() {
        tournee = null;
    }

    /**
     * Test du constructeur de la classe Tournee.
     */
    @Test
    public void testTournee(){
        assertNotNull("L'instance n'est pas créée", tournee);
    }
    
    /**
     * Test de la methode getDuree de la classe Tournee.
     */
    @Test
    public void testGetDuree() {
        System.out.println("getDuree");
        
        //Test 1 : La duree est nulle
        String expResult = null;
        String result = tournee.getDuree();
        assertEquals("La durée est incorrecte",expResult, result);
        
        //Test 2 : La duree est non nulle
        expResult = "10";
        tournee.setDuree("10");
        assertEquals("La durée est incorrecte",expResult, tournee.getDuree());
        
        //Test 3 : La duree est la chaine vide
        expResult = "";
        tournee.setDuree("");
        assertEquals("La durée est incorrecte",expResult, tournee.getDuree());
    }

    /**
     * Test de la methode setDuree de la classe Tournee.
     */
    @Test
    public void testSetDuree() {
        System.out.println("setDuree");
        
        //Test 1 : La duree est nulle
        String duree = null;
        tournee.setDuree(duree);
        assertEquals("La durée est incorrecte",duree, tournee.getDuree());
        
        //Test 2 : La duree est non nulle
        duree = "15";
        tournee.setDuree(duree);
        assertEquals("La durée est incorrecte","15", tournee.getDuree());
        
        //Test 3 : La duree est la chaine vide
        duree = "";
        tournee.setDuree(duree);
        assertEquals("La durée est incorrecte","", tournee.getDuree());
    }

    /**
     * Test de la methode getSuccessionPointsInteret de la classe Tournee.
     */
    @Test
    public void testGetSuccessionPointsInteret() {
        System.out.println("getSuccessionPointsInteret");
        
        //Test 1 : La liste des points d interets est vide
        ArrayList<PointInteret> expResult = new ArrayList();
        ArrayList<PointInteret> result = tournee.getSuccessionPointsInteret();
        assertEquals("La liste de succession de points d'intérêts est incorrecte",expResult, result);
        
        //Test 2 : La liste des points d interets possède un point d interet
        PointInteret pointinteret = new PointInteret();
        expResult.add(pointinteret);
        tournee.ajouterPointInteret(pointinteret);
        assertEquals("La liste de succession de points d'intérêts est incorrecte",expResult, tournee.getSuccessionPointsInteret());
    }

    /**
     * Test de la methode ajouterPointInteret de la classe Tournee.
     */
    @Test
    public void testAjouterPointInteret() {
        System.out.println("ajouterPointInteret");
        
        //Test 1 : Ajouter un point d interet null
        PointInteret pointInteret1 = null;
        tournee.ajouterPointInteret(pointInteret1);
        int expResult = 1;
        int result = tournee.getSuccessionPointsInteret().size();
        assertEquals("Le point d'intérêt n'a pas été ajouté à la tournee",expResult, result);
        
        //Test 2 : Ajouter un point d interet vide
        PointInteret pointInteret2 = new PointInteret();
        tournee.ajouterPointInteret(pointInteret2);
        expResult = 2;
        result = tournee.getSuccessionPointsInteret().size();
        assertEquals("Le point d'intérêt n'a pas été ajouté à la tournee",expResult, result);
        
        //Test 3 : Ajouter un point d interet avec attributs initialises
        Intersection intersection = new Intersection();
        int duree = 10;
        PointInteret pointInteret3 = new PointInteret(intersection, duree);
        tournee.ajouterPointInteret(pointInteret3);
        expResult = 3;
        result = tournee.getSuccessionPointsInteret().size();
        assertEquals("Le point d'intérêt n'a pas été ajouté à la tournee",expResult, result);
        
        
    }

    /**
     * Test de la methode setSuccessionPointsInteret de la classe Tournee.
     */
    @Test
    public void testSetSuccessionPointsInteret() {
        System.out.println("setSuccessionPointsInteret");
        
        //Test 1 : La liste de points d interets est nulle
        ArrayList<PointInteret> successionPointsInteret = null;
        tournee.setSuccessionPointsInteret(successionPointsInteret);
        ArrayList<PointInteret> expResult = successionPointsInteret;
        ArrayList<PointInteret> result = tournee.getSuccessionPointsInteret();
        assertEquals("La liste de la tournée n'a pas été modifiée",expResult, result);
        
        //Test 2 : La liste des points d interets est vide
        successionPointsInteret = new ArrayList();
        tournee.setSuccessionPointsInteret(successionPointsInteret);
        expResult = successionPointsInteret;
        result = tournee.getSuccessionPointsInteret();
        assertEquals("La liste de la tournée n'a pas été modifiée",expResult, result);
        
        //Test 3 : La liste des points d interets possede un point d interet
        PointInteret ptI = new PointInteret();
        successionPointsInteret.add(ptI);
        tournee.setSuccessionPointsInteret(successionPointsInteret);
        expResult = successionPointsInteret;
        result = tournee.getSuccessionPointsInteret();
        assertEquals("La liste de la tournée n'a pas été modifiée",expResult, result);
        
        
    }

    /**
     * Test de la methode toString de la classe Tournee.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        
        //Test 1 : Si la tournee n a pas de point d interet 
        String expResult = "Tournée : \n";
        String result = tournee.toString();
        assertEquals("La tournée n'est pas bien affichée", expResult, result);
        
        //Test 2 : Si la tournee a un point d interet a une intersection et un chemin
        String id1= "1";
        Double latitude1 = 2.1;
        Double longitude1 = 4.7;
        Intersection intersection = new Intersection(id1,latitude1,longitude1);
        int duree = 5;
        PointInteret ptI = new PointInteret(intersection, duree);
        
        String id2= "2";
        Double latitude2 = 45.1;
        Double longitude2 = 4.6;
        Intersection depart = new Intersection(id2, latitude2, longitude2);
        
        String id3= "3";
        Double latitude3 = 42.8;
        Double longitude3 = 5.6;
        Intersection arrivee = new Intersection(id3, latitude3, longitude3);
        
        ArrayList<Troncon> successionTroncons = new ArrayList();
        Chemin chemin = new Chemin(depart, arrivee,successionTroncons);
        ptI.setCheminDepart(chemin);
        tournee.ajouterPointInteret(ptI);
        
        expResult = "Tournée : \nPoint 1"+"\n "+"Chemin de 2 à 3"+"\n";
        result = tournee.toString();
        assertEquals("La tournée n'est pas bien affichée", expResult, result);
    }
    
}
