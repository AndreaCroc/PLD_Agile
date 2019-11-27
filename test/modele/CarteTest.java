package modele;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/*
 * CarteTest
 *
 * Version 1
 *
 * 
 * Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU,
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
public class CarteTest {
    
    public CarteTest() {
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
     * Test of construireCarteAPartirDeDOMXML method, of class Carte.
     */
    @Test
    public void testConstruireCarteAPartirDeDOMXML() throws Exception{
        System.out.println("construireCarteAPartirDeDOMXML");
        
        File xml = new File("test/modele/miniPlanTest.xml");
        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
        Document document = docBuilder.parse(xml);
        Element racine = document.getDocumentElement();
        
        Carte carte = new Carte();
        
        carte.construireCarteAPartirDeDOMXML(racine);
        
        //Assert
        List<Intersection> n = carte.getListeIntersections();
        
        assertEquals("25175791", n.get(0).getId());
        assertEquals(45.75406, n.get(0).getLatitude(), 0.00000);
        assertEquals(4.857418, n.get(0).getLongitude(), 0.000000);
        
        assertEquals(n.get(1).getId() , "2129259178");
        assertEquals(45.750404, n.get(1).getLatitude(), 0.000000);
        assertEquals(4.8744674, n.get(1).getLongitude(), 0.0000000);
        
        assertEquals("2129259178", n.get(0).getTronconsDepart().get(0).getDestination().getId());
        assertEquals(69.979805, n.get(0).getTronconsDepart().get(0).getLongueur(), 0.000000);
        assertEquals("Rue Danton", n.get(0).getTronconsDepart().get(0).getNomRue());        
        assertEquals("25175791", n.get(0).getTronconsDepart().get(0).getOrigine().getId());

        assertEquals("26086130", n.get(1).getTronconsDepart().get(0).getDestination().getId());
        assertEquals(136.00636, n.get(1).getTronconsDepart().get(0).getLongueur(), 0.000000);
        assertEquals("Rue de l'Abondance", n.get(1).getTronconsDepart().get(0).getNomRue());        
        assertEquals("2129259178", n.get(1).getTronconsDepart().get(0).getOrigine().getId());
        
        //Test fichier sans noeuds
        xml = new File("test/modele/planTestSansNoeuds.xml");
        docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
        document = docBuilder.parse(xml);
        racine = document.getDocumentElement();
        
        carte = new Carte();
        
        assertEquals(false, carte.construireCarteAPartirDeDOMXML(racine));
        assertEquals(true, carte.getListeIntersections().isEmpty());
        
        //Test fichier sans troncons
        xml = new File("test/modele/planTestSansTroncons.xml");
        docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
        document = docBuilder.parse(xml);
        racine = document.getDocumentElement();
        
        carte = new Carte();
        
        assertEquals(false, carte.construireCarteAPartirDeDOMXML(racine));
        assertEquals(false, carte.getListeIntersections().isEmpty());
        for(Intersection i : carte.getListeIntersections())
        {
            assertEquals(true, i.getTronconsDepart().isEmpty());
        }
        
        //Test intersection non-trouvée
        xml = new File("test/modele/planPointNonTrouve.xml");
        docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        document = docBuilder.parse(xml);
        racine = document.getDocumentElement();

        carte = new Carte();

        assertEquals(false, carte.construireCarteAPartirDeDOMXML(racine));
        assertEquals(3, carte.getListeIntersections().size());

    }

    /**
     * Test of construireLivraisonAPartirDeDOMXML method, of class Carte.
     */
    @Test
    public void testConstruireLivraisonAPartirDeDOMXML() throws Exception{
        System.out.println("construireLivraisonAPartirDeDOMXML");
        
        File xml = new File("test\\modele\\demandePetit1.xml");
        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
        Document document = docBuilder.parse(xml);
        Element racine = document.getDocumentElement();
        System.out.println(racine.toString());
        
        File xml2 = new File("test\\modele\\miniPlanTest.xml");
        DocumentBuilder docBuilder2 = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
        Document document2 = docBuilder2.parse(xml2);
        Element racine2 = document2.getDocumentElement();
        
        Carte carte = new Carte();
        carte.construireCarteAPartirDeDOMXML(racine2);
        System.out.println(carte.getListeIntersections());
        carte.construireLivraisonAPartirDeDOMXML(racine);
        
        //Assertions
        DemandesLivraisons dl = carte.getDemandesLivraisons();
        List<PointInteret> pi = dl.getListePointsInteret();
        
        //recuperation de l'entrepot (attributs adresseDepart et heureDep
        assertEquals("2129259178", dl.getAdresseDepart().getIntersection().getId());
        assertEquals("8:0:0", dl.getHeureDepart());
        assertEquals("2129259178", pi.get(0).getIntersection().getId());
        
        //demandes
        assertEquals("25175791", pi.get(1).getIntersection().getId());
        assertEquals(180, (int)pi.get(1).getDuree());
        assertEquals(true, pi.get(1).isEnlevement());
        assertEquals("26086130", pi.get(1).getPointDependance().getIntersection().getId());
        assertEquals(1, (int)pi.get(1).getNumeroDemande());
        
        assertEquals("26086130", pi.get(2).getIntersection().getId());
        assertEquals(240, (int)pi.get(2).getDuree());
        assertEquals(false, pi.get(2).isEnlevement());
        assertEquals("25175791", pi.get(2).getPointDependance().getIntersection().getId());
        assertEquals(1, (int)pi.get(2).getNumeroDemande());

        
        //Cas d'un fichier sans entrepot
        File xml3 = new File("test\\modele\\demandeTestSansEntrepot.xml");
        DocumentBuilder docBuilder3 = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
        Document document3 = docBuilder3.parse(xml3);
        Element racine3 = document3.getDocumentElement();
        //System.out.println(racine.toString());
        Carte carte3 = new Carte();
        
        carte3.construireCarteAPartirDeDOMXML(racine2);
        System.out.println(carte3.getListeIntersections());
        
        assertEquals(false, carte3.construireLivraisonAPartirDeDOMXML(racine3));
        assertEquals(null, carte3.getDemandesLivraisons());
        
        //Fichier sans demandes
        xml3 = new File("test\\modele\\demandeTestSansDemandeLivr.xml");
        docBuilder3 = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
        document3 = docBuilder3.parse(xml3);
        racine3 = document3.getDocumentElement();
        //System.out.println(racine.toString());
        carte3 = new Carte();
        
        carte3.construireCarteAPartirDeDOMXML(racine2);
        System.out.println(carte3.getListeIntersections());
        
        assertEquals(false, carte3.construireLivraisonAPartirDeDOMXML(racine3));
        assertEquals(1, carte3.getDemandesLivraisons().getListePointsInteret().size());

        //Fichier sans demandes
        xml3 = new File("test\\modele\\demandeTestPointNonTrouve.xml");
        docBuilder3 = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        document3 = docBuilder3.parse(xml3);
        racine3 = document3.getDocumentElement();
        carte3 = new Carte();

        carte3.construireCarteAPartirDeDOMXML(racine2);
        System.out.println(carte3.getListeIntersections());

        assertEquals(false, carte3.construireLivraisonAPartirDeDOMXML(racine3));
        assertEquals(1, carte3.getDemandesLivraisons().getListePointsInteret().size());
    }
    
}