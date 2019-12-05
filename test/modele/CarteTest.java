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
    
    private static String cheminAcces = "test/fichiersXML/";
    /**
     * Test of construireCarteAPartirDeDOMXML method, of class Carte.
     */
//    @Test
//    public void testConstruireCarteAPartirDeDOMXML() throws Exception{
//        System.out.println("construireCarteAPartirDeDOMXML");
//        
//        File xml = new File(cheminAcces+"miniPlanTest.xml");
//        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
//        Document document = docBuilder.parse(xml);
//        Element racine = document.getDocumentElement();
//        
//        Carte carte = new Carte();
//        
//        carte.construireCarteAPartirDeDOMXML(racine);
//        
//        //Assert
//        List<Intersection> n = carte.getListeIntersections();
//        
//        assertEquals("25175791", n.get(0).getId());
//        assertEquals(45.75406, n.get(0).getLatitude(), 0.00000);
//        assertEquals(4.857418, n.get(0).getLongitude(), 0.000000);
//        
//        assertEquals(n.get(1).getId() , "2129259178");
//        assertEquals(45.750404, n.get(1).getLatitude(), 0.000000);
//        assertEquals(4.8744674, n.get(1).getLongitude(), 0.0000000);
//        
//        assertEquals("2129259178", n.get(0).getTronconsDepart().get(0).getDestination().getId());
//        assertEquals(69.979805, n.get(0).getTronconsDepart().get(0).getLongueur(), 0.000000);
//        assertEquals("Rue Danton", n.get(0).getTronconsDepart().get(0).getNomRue());        
//        assertEquals("25175791", n.get(0).getTronconsDepart().get(0).getOrigine().getId());
//
//        assertEquals("26086130", n.get(1).getTronconsDepart().get(0).getDestination().getId());
//        assertEquals(136.00636, n.get(1).getTronconsDepart().get(0).getLongueur(), 0.000000);
//        assertEquals("Rue de l'Abondance", n.get(1).getTronconsDepart().get(0).getNomRue());        
//        assertEquals("2129259178", n.get(1).getTronconsDepart().get(0).getOrigine().getId());
//        
//        //Test fichier sans noeuds
//        xml = new File(cheminAcces+"planTestSansNoeuds.xml");
//        docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
//        document = docBuilder.parse(xml);
//        racine = document.getDocumentElement();
//        
//        carte = new Carte();
//        
//        assertEquals(false, carte.construireCarteAPartirDeDOMXML(racine));
//        assertEquals(true, carte.getListeIntersections().isEmpty());
//        
//        //Test fichier sans troncons
//        xml = new File(cheminAcces+"planTestSansTroncons.xml");
//        docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
//        document = docBuilder.parse(xml);
//        racine = document.getDocumentElement();
//        
//        carte = new Carte();
//        
//        assertEquals(false, carte.construireCarteAPartirDeDOMXML(racine));
//        assertEquals(false, carte.getListeIntersections().isEmpty());
//        for(Intersection i : carte.getListeIntersections())
//        {
//            assertEquals(true, i.getTronconsDepart().isEmpty());
//        }
//        
//        //Test intersection non-trouvée
//        xml = new File(cheminAcces+"planPointNonTrouve.xml");
//        docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//        document = docBuilder.parse(xml);
//        racine = document.getDocumentElement();
//
//        carte = new Carte();
//
//        assertEquals(false, carte.construireCarteAPartirDeDOMXML(racine));
//        assertEquals(3, carte.getListeIntersections().size());
//
//    }
//
//    /**
//     * Test of construireLivraisonAPartirDeDOMXML method, of class Carte.
//     */
//    @Test
//    public void testConstruireLivraisonAPartirDeDOMXML() throws Exception{
//        System.out.println("construireLivraisonAPartirDeDOMXML");
//        
//        File xml = new File(cheminAcces+"demandePetit1.xml");
//        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
//        Document document = docBuilder.parse(xml);
//        Element racine = document.getDocumentElement();
//        System.out.println(racine.toString());
//        
//        File xml2 = new File(cheminAcces+"miniPlanTest.xml");
//        DocumentBuilder docBuilder2 = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
//        Document document2 = docBuilder2.parse(xml2);
//        Element racine2 = document2.getDocumentElement();
//        
//        Carte carte = new Carte();
//        carte.construireCarteAPartirDeDOMXML(racine2);
////        System.out.println(carte.getListeIntersections());
//        carte.construireLivraisonAPartirDeDOMXML(racine);
//        
//        //Assertions
//        DemandesLivraisons dl = carte.getDemandesLivraisons();
//        List<PointInteret> pi = dl.getListePointsInteret();
//        
//        //recuperation de l'entrepot (attributs adresseDepart et heureDep
//        assertEquals("2129259178", dl.getAdresseDepart().getIntersection().getId());
//        assertEquals("8:0:0", dl.getHeureDepart());
//        assertEquals("2129259178", pi.get(0).getIntersection().getId());
//        
//        //demandes
//        assertEquals("25175791", pi.get(1).getIntersection().getId());
//        assertEquals(180, (int)pi.get(1).getDuree());
//        assertEquals(true, pi.get(1).isEnlevement());
//        assertEquals("26086130", pi.get(1).getPointDependance().getIntersection().getId());
//        assertEquals(1, (int)pi.get(1).getNumeroDemande());
//        
//        assertEquals("26086130", pi.get(2).getIntersection().getId());
//        assertEquals(240, (int)pi.get(2).getDuree());
//        assertEquals(false, pi.get(2).isEnlevement());
//        assertEquals("25175791", pi.get(2).getPointDependance().getIntersection().getId());
//        assertEquals(1, (int)pi.get(2).getNumeroDemande());
//
//        
//        //Cas d'un fichier sans entrepot
//        File xml3 = new File(cheminAcces+"demandeTestSansEntrepot.xml");
//        DocumentBuilder docBuilder3 = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
//        Document document3 = docBuilder3.parse(xml3);
//        Element racine3 = document3.getDocumentElement();
//        //System.out.println(racine.toString());
//        Carte carte3 = new Carte();
//        
//        carte3.construireCarteAPartirDeDOMXML(racine2);
////        System.out.println(carte3.getListeIntersections());
//        
//        assertEquals(false, carte3.construireLivraisonAPartirDeDOMXML(racine3));
//        assertEquals(null, carte3.getDemandesLivraisons());
//        
//        //Fichier sans demandes
//        xml3 = new File(cheminAcces+"demandeTestSansDemandeLivr.xml");
//        docBuilder3 = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
//        document3 = docBuilder3.parse(xml3);
//        racine3 = document3.getDocumentElement();
//        //System.out.println(racine.toString());
//        carte3 = new Carte();
//        
//        carte3.construireCarteAPartirDeDOMXML(racine2);
////        System.out.println(carte3.getListeIntersections());
//        
//        assertEquals(false, carte3.construireLivraisonAPartirDeDOMXML(racine3));
//        assertEquals(1, carte3.getDemandesLivraisons().getListePointsInteret().size());
//
//        //Fichier sans demandes
//        xml3 = new File(cheminAcces+"demandeTestPointNonTrouve.xml");
//        docBuilder3 = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//        document3 = docBuilder3.parse(xml3);
//        racine3 = document3.getDocumentElement();
//        carte3 = new Carte();
//
//        carte3.construireCarteAPartirDeDOMXML(racine2);
////        System.out.println(carte3.getListeIntersections());
//
//        assertEquals(false, carte3.construireLivraisonAPartirDeDOMXML(racine3));
//        assertEquals(1, carte3.getDemandesLivraisons().getListePointsInteret().size());
//    }
//    
    @Test
    public void testSupprimerPointInteret() throws Exception {
        System.out.println("supprimerPointInteret");
        
        //Cas d'une suppression en milieu de tournée
        File xml = new File(cheminAcces+"demandeMoyen3.xml");
        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
        Document document = docBuilder.parse(xml);
        Element racine = document.getDocumentElement();
        System.out.println(racine.toString());
        
        File xml2 = new File(cheminAcces+"moyenPlan.xml");
        DocumentBuilder docBuilder2 = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
        Document document2 = docBuilder2.parse(xml2);
        Element racine2 = document2.getDocumentElement();
        
        Carte carte = new Carte();
        carte.construireCarteAPartirDeDOMXML(racine2);
        carte.construireLivraisonAPartirDeDOMXML(racine);
        carte.calculerTournee();
        
        DemandesLivraisons dl = carte.getDemandesLivraisons();
        Tournee tournee = carte.getTournee();
        ArrayList<PointInteret> listePointsInteret = dl.getListePointsInteret();
        ArrayList<PointInteret> successionPointsInteretInitiale = tournee.getSuccessionPointsInteret();
        int tailleInitiale = successionPointsInteretInitiale.size();
        PointInteret pointASupprimer = successionPointsInteretInitiale.get(2);
        PointInteret pointDependance = pointASupprimer.getPointDependance();
        
        
        //Suppression 
        carte.supprimerPointInteret(pointASupprimer);
        Tournee nouvTournee = carte.getTournee();
        ArrayList<PointInteret> nouvSuccessionPointsInteret = nouvTournee.getSuccessionPointsInteret();
        successionPointsInteretInitiale.remove(pointASupprimer);
        successionPointsInteretInitiale.remove(pointASupprimer);
        int nouvTaille = nouvSuccessionPointsInteret.size();
        
        //Assertions
        assertEquals(successionPointsInteretInitiale, nouvSuccessionPointsInteret);
        assertEquals(nouvTaille, tailleInitiale -2);
        
        //Vérifications des chemins
        for (int i=0; i < nouvTaille-1;i++) {
            assertEquals(nouvSuccessionPointsInteret.get(i).getCheminDepart().getArrivee(),
                    nouvSuccessionPointsInteret.get(i+1).getIntersection());
        }
        assertEquals(nouvSuccessionPointsInteret.get(nouvTaille - 1).getCheminDepart().getArrivee(),
                nouvSuccessionPointsInteret.get(0).getIntersection());

        //Cas d'une suppression en fin de tournée
        carte = new Carte();
        carte.construireCarteAPartirDeDOMXML(racine2);
        carte.construireLivraisonAPartirDeDOMXML(racine);
        carte.calculerTournee();
        
        dl = carte.getDemandesLivraisons();
        tournee = carte.getTournee();
        listePointsInteret = dl.getListePointsInteret();
        successionPointsInteretInitiale = tournee.getSuccessionPointsInteret();
        tailleInitiale = successionPointsInteretInitiale.size();
        pointASupprimer = successionPointsInteretInitiale.get(tailleInitiale-1);
        pointDependance = pointASupprimer.getPointDependance();
        
        //Suppression 
        carte.supprimerPointInteret(pointASupprimer);
        nouvTournee = carte.getTournee();
        nouvSuccessionPointsInteret = nouvTournee.getSuccessionPointsInteret();
        successionPointsInteretInitiale.remove(pointASupprimer);
        successionPointsInteretInitiale.remove(pointASupprimer);
        nouvTaille = nouvSuccessionPointsInteret.size();
        
        //Assertions
        assertEquals(successionPointsInteretInitiale, nouvSuccessionPointsInteret);
        assertEquals(nouvTaille, tailleInitiale -2);
        
        //Vérifications des chemins
        for (int i=0; i < nouvTaille-1;i++) {
            assertEquals(nouvSuccessionPointsInteret.get(i).getCheminDepart().getArrivee(),
                    nouvSuccessionPointsInteret.get(i+1).getIntersection());
        }
        assertEquals(nouvSuccessionPointsInteret.get(nouvTaille - 1).getCheminDepart().getArrivee(),
                nouvSuccessionPointsInteret.get(0).getIntersection());
        
        
        //Cas où il n'y a qu'une seule livraison et qu'on la supprime
        xml = new File(cheminAcces+"demandePetit1.xml");
        docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
        document = docBuilder.parse(xml);
        racine = document.getDocumentElement();
        System.out.println(racine.toString());
        
        xml2 = new File(cheminAcces+"petitPlan.xml");
        docBuilder2 = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
        document2 = docBuilder2.parse(xml2);
        racine2 = document2.getDocumentElement();
        
        carte = new Carte();
        carte.construireCarteAPartirDeDOMXML(racine2);
        carte.construireLivraisonAPartirDeDOMXML(racine);
        carte.calculerTournee();
        
        dl = carte.getDemandesLivraisons();
        tournee = carte.getTournee();
        listePointsInteret = dl.getListePointsInteret();
        successionPointsInteretInitiale = tournee.getSuccessionPointsInteret();
        tailleInitiale = successionPointsInteretInitiale.size();
        pointASupprimer = successionPointsInteretInitiale.get(1);
        pointDependance = pointASupprimer.getPointDependance();
        
        //Suppression 
        carte.supprimerPointInteret(pointASupprimer);
        nouvTournee = carte.getTournee();
        nouvSuccessionPointsInteret = nouvTournee.getSuccessionPointsInteret();
        successionPointsInteretInitiale.remove(pointASupprimer);
        successionPointsInteretInitiale.remove(pointASupprimer);
        nouvTaille = nouvSuccessionPointsInteret.size();
        
         //Assertions
        assertEquals(successionPointsInteretInitiale, nouvSuccessionPointsInteret);
        assertEquals(nouvTaille, tailleInitiale -2);
              
    }
    
    
    @Test
    public void testAjouterLivraison() throws Exception {
        System.out.println("ajouterLivraison");
        
        //Cas d'un ajout simple en fin de tournée
        File xml = new File(cheminAcces+"demandePetit1.xml");
        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
        Document document = docBuilder.parse(xml);
        Element racine = document.getDocumentElement();
        System.out.println(racine.toString());
        
        File xml2 = new File(cheminAcces+"moyenPlan.xml");
        DocumentBuilder docBuilder2 = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
        Document document2 = docBuilder2.parse(xml2);
        Element racine2 = document2.getDocumentElement();
        
        Carte carte = new Carte();
        carte.construireCarteAPartirDeDOMXML(racine2);
        carte.construireLivraisonAPartirDeDOMXML(racine);
        carte.calculerTournee();
        
        DemandesLivraisons dl = carte.getDemandesLivraisons();
        Tournee tournee = carte.getTournee();
        ArrayList<PointInteret> listePointsInteretInitiale = dl.getListePointsInteret();
        ArrayList<PointInteret> successionPointsInteretInitiale = tournee.getSuccessionPointsInteret();
        ArrayList<Intersection> listeIntersections = carte.getListeIntersections();
        int tailleInitiale = successionPointsInteretInitiale.size();
        
        //Initialisation des paramétres
        Double latitudeEnlvt = 45.748684;
        Double longitudeEnlvt = 4.902288;
        Double latitudeLivr = 45.748405;
        Double longitudeLivr = 4.875445;
        int dureeEnlevement = 60;
        int dureeLivraison = 240;
        
        Intersection intersectionEnlvt = null;
        Intersection intersectionLivr = null;
        //Recherche des interesections correspondants aux coordonnées 
        for (Intersection i : listeIntersections) {
            if (i.getLatitude() == latitudeEnlvt && i.getLongitude() == longitudeEnlvt) {
                intersectionEnlvt = i;
            } else if (i.getLatitude() == latitudeLivr && i.getLongitude() == longitudeLivr) {
                intersectionLivr = i;
            }
        }
        int numeroDemande = (listePointsInteretInitiale.size()-1)/2 +1;
        System.out.println("numero demande : "+numeroDemande);
        //Création des points d'intérêt
        PointInteret pointEnlevement = new PointInteret(intersectionEnlvt, dureeEnlevement);
        pointEnlevement.setEnlevement(true);
        pointEnlevement.setEntrepot(false);
        PointInteret pointLivraison = new PointInteret(intersectionLivr, dureeLivraison);
        pointLivraison.setEnlevement(false);
        pointLivraison.setEntrepot(false);
        pointEnlevement.setPointDependance(pointLivraison);
        pointLivraison.setPointDependance(pointEnlevement);
        pointEnlevement.setNumeroDemande(numeroDemande);
        pointLivraison.setNumeroDemande(numeroDemande);
        
        PointInteret pointAvantEnlevement = successionPointsInteretInitiale.get(successionPointsInteretInitiale.size()-1);
        PointInteret pointAvantLivraison = pointAvantEnlevement;
        
        
        
        //Ajout des points
        carte.ajouterLivraison(latitudeEnlvt, longitudeEnlvt, 
                latitudeLivr, longitudeLivr, pointAvantEnlevement, pointAvantLivraison, 
                dureeEnlevement, dureeLivraison);
        Tournee nouvTournee = carte.getTournee();
        ArrayList<PointInteret> nouvListePointsInteret = carte.getDemandesLivraisons().getListePointsInteret();
        ArrayList<PointInteret> nouvSuccessionPointsInteret = nouvTournee.getSuccessionPointsInteret();
        listePointsInteretInitiale.add(pointEnlevement);
        listePointsInteretInitiale.add(pointLivraison);
        int nouvTaille = nouvSuccessionPointsInteret.size();
        
        
        
        //Assertions
        assertEquals(listePointsInteretInitiale, nouvListePointsInteret);
        assertEquals(nouvTaille, tailleInitiale+2);
        
        //Vérifications des chemins
        for (int i=0; i < nouvTaille-1;i++) {
            assertEquals(nouvSuccessionPointsInteret.get(i).getCheminDepart().getArrivee(),
                    nouvSuccessionPointsInteret.get(i+1).getIntersection());
        }
        assertEquals(nouvSuccessionPointsInteret.get(nouvTaille - 1).getCheminDepart().getArrivee(),
                nouvSuccessionPointsInteret.get(0).getIntersection());

        
        //Cas d'un ajout en milieu de tournée
        Tournee tourneeInitiale = nouvTournee;
        listePointsInteretInitiale=nouvListePointsInteret;
        tailleInitiale = nouvTaille;
        //Initialisation des paramétres
        latitudeEnlvt = 45.750404;
        longitudeEnlvt = 4.8744674;
        latitudeLivr = 45.73208;
        longitudeLivr = 4.902046;
        dureeEnlevement = 120;
        dureeLivraison = 600;
        
        intersectionEnlvt = null;
        intersectionLivr = null;
        //Recherche des interesections correspondants aux coordonnées 
        for (Intersection i : listeIntersections) {
            if (i.getLatitude() == latitudeEnlvt && i.getLongitude() == longitudeEnlvt) {
                intersectionEnlvt = i;
            } else if (i.getLatitude() == latitudeLivr && i.getLongitude() == longitudeLivr) {
                intersectionLivr = i;
            }
        }
        numeroDemande = (listePointsInteretInitiale.size()-1)/2 +1;
        //Création des points d'intérêt
        pointEnlevement = new PointInteret(intersectionEnlvt, dureeEnlevement);
        pointEnlevement.setEnlevement(true);
        pointEnlevement.setEntrepot(false);
        pointLivraison = new PointInteret(intersectionLivr, dureeLivraison);
        pointLivraison.setEnlevement(false);
        pointLivraison.setEntrepot(false);
        pointEnlevement.setPointDependance(pointLivraison);
        pointLivraison.setPointDependance(pointEnlevement);
        pointEnlevement.setNumeroDemande(numeroDemande);
        pointLivraison.setNumeroDemande(numeroDemande);
        
        pointAvantEnlevement = nouvSuccessionPointsInteret.get(1);
        pointAvantLivraison = nouvSuccessionPointsInteret.get(3);
        
        
        System.out.println("first tournee "+ tourneeInitiale);
        //Ajout des points
        carte.ajouterLivraison(latitudeEnlvt, longitudeEnlvt, 
                latitudeLivr, longitudeLivr, pointAvantEnlevement, pointAvantLivraison, 
                dureeEnlevement, dureeLivraison);
        nouvTournee = carte.getTournee();
        nouvListePointsInteret = carte.getDemandesLivraisons().getListePointsInteret();
        nouvSuccessionPointsInteret = nouvTournee.getSuccessionPointsInteret();
        listePointsInteretInitiale.add(pointEnlevement);
        listePointsInteretInitiale.add(pointLivraison);
        nouvTaille = nouvSuccessionPointsInteret.size();
        
        System.out.println("nouv tournee "+ nouvTournee);
        
        //Assertions
        assertEquals(listePointsInteretInitiale, nouvListePointsInteret);
        assertEquals(nouvTaille, tailleInitiale+2);
        
        //Vérifications des chemins
        for (int i=0; i < nouvTaille-1;i++) {
            assertEquals(nouvSuccessionPointsInteret.get(i).getCheminDepart().getArrivee(),
                    nouvSuccessionPointsInteret.get(i+1).getIntersection());
        }
        assertEquals(nouvSuccessionPointsInteret.get(nouvTaille - 1).getCheminDepart().getArrivee(),
                nouvSuccessionPointsInteret.get(0).getIntersection());
        
        
//        //Cas où il faut agrandir les matrices de chemins et de couts
//        tourneeInitiale = nouvTournee;
//        listePointsInteretInitiale=nouvListePointsInteret;
//        tailleInitiale = nouvTaille;
//        //Initialisation des paramétres
//        latitudeEnlvt = 45.750404;
//        longitudeEnlvt = 4.8744674;
//        latitudeLivr = 45.73208;
//        longitudeLivr = 4.902046;
//        dureeEnlevement = 120;
//        dureeLivraison = 600;
//        
//        intersectionEnlvt = null;
//        intersectionLivr = null;
//        //Recherche des interesections correspondants aux coordonnées 
//        for (Intersection i : listeIntersections) {
//            if (i.getLatitude() == latitudeEnlvt && i.getLongitude() == longitudeEnlvt) {
//                intersectionEnlvt = i;
//            } else if (i.getLatitude() == latitudeLivr && i.getLongitude() == longitudeLivr) {
//                intersectionLivr = i;
//            }
//        }
//        numeroDemande = (listePointsInteretInitiale.size()-1)/2 +1;
//        //Création des points d'intérêt
//        pointEnlevement = new PointInteret(intersectionEnlvt, dureeEnlevement);
//        pointEnlevement.setEnlevement(true);
//        pointEnlevement.setEntrepot(false);
//        pointLivraison = new PointInteret(intersectionLivr, dureeLivraison);
//        pointLivraison.setEnlevement(false);
//        pointLivraison.setEntrepot(false);
//        pointEnlevement.setPointDependance(pointLivraison);
//        pointLivraison.setPointDependance(pointEnlevement);
//        pointEnlevement.setNumeroDemande(numeroDemande);
//        pointLivraison.setNumeroDemande(numeroDemande);
//        
//        pointAvantEnlevement = nouvSuccessionPointsInteret.get(1);
//        pointAvantLivraison = nouvSuccessionPointsInteret.get(3);
//        
//        
//        System.out.println("first tournee "+ tourneeInitiale);
//        //Ajout des points
//        carte.ajouterLivraison(latitudeEnlvt, longitudeEnlvt, 
//                latitudeLivr, longitudeLivr, pointAvantEnlevement, pointAvantLivraison, 
//                dureeEnlevement, dureeLivraison);
//        nouvTournee = carte.getTournee();
//        nouvListePointsInteret = carte.getDemandesLivraisons().getListePointsInteret();
//        nouvSuccessionPointsInteret = nouvTournee.getSuccessionPointsInteret();
//        listePointsInteretInitiale.add(pointEnlevement);
//        listePointsInteretInitiale.add(pointLivraison);
//        nouvTaille = nouvSuccessionPointsInteret.size();
//        
//        System.out.println("nouv tournee "+ nouvTournee);
//        
//        //Assertions
//        assertEquals(listePointsInteretInitiale, nouvListePointsInteret);
//        assertEquals(nouvTaille, tailleInitiale+2);
//        
//        //Vérifications des chemins
//        for (int i=0; i < nouvTaille-1;i++) {
//            assertEquals(nouvSuccessionPointsInteret.get(i).getCheminDepart().getArrivee(),
//                    nouvSuccessionPointsInteret.get(i+1).getIntersection());
//        }
//        assertEquals(nouvSuccessionPointsInteret.get(nouvTaille - 1).getCheminDepart().getArrivee(),
//                nouvSuccessionPointsInteret.get(0).getIntersection());
    }
}
    
