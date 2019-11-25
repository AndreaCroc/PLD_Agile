package modele;

import tsp.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.util.Pair;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;

/*
 * Carte
 *
 * Version 1
 *
 * 
 * Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU,
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
public class Carte {

    private ArrayList<Intersection> listeIntersections;
    private DemandesLivraisons demandesLivraisons;
    private TSP1 unTSP;
    public static final Double INFINI = 1000.0; //Valeur max 
    public static final Double NON_DEFINI = -1000.0;

    public Carte() {
        this.listeIntersections = new ArrayList<Intersection>();
        this.unTSP = new TSP1();
    }

    public ArrayList<Intersection> getListeIntersections() {
        return listeIntersections;
    }

    public void ajouterIntersection(Intersection i) {
        this.listeIntersections.add(i);
    }

    public DemandesLivraisons getDemandesLivraisons() {
        return demandesLivraisons;
    }

    public void setDemandesLivraisons(DemandesLivraisons dL) {
        this.demandesLivraisons = dL;
    }

    public void relacherArc(Intersection depart, Intersection arrivee) {
        //System.out.println("Relachement Arc (" + depart.getId() + "," + arrivee.getId() + ")");
        Double coutArc = INFINI;
        ArrayList<Troncon> listeTronconsDepart = depart.getTronconsDepart();
        Troncon arc = new Troncon();
        //Recherche du coût de l'arc
        for (int i = 0; i < listeTronconsDepart.size(); i++) {
            if (listeTronconsDepart.get(i).getDestination() == arrivee) {
                arc = listeTronconsDepart.get(i);
                coutArc = arc.getLongueur();
            }
        }
        if (arrivee.getDistance() > depart.getDistance() + coutArc) {
            //Relachement de l'arc
            arrivee.setDistance(depart.getDistance() + coutArc);
            arrivee.setPredecesseur(depart);
        }
    }

    public void dijkstra(Intersection depart) {
        Double dMin;
        Intersection sMin; //sommet de la liste de sommets gris ayant la distance minimale
        ArrayList<Intersection> sommetsBlancs = new ArrayList<Intersection>();
        ArrayList<Intersection> sommetsGris = new ArrayList<Intersection>();
        ArrayList<PointInteret> pointsInteret = this.demandesLivraisons.getListePointsInteret();
        ArrayList<Intersection> successeursMin; //liste des successeurs du sommet
        //gris ayant la distance minimale

        for (int i = 0; i < this.listeIntersections.size(); i++) {
            this.listeIntersections.get(i).setDistance(INFINI);
            listeIntersections.get(i).setPredecesseur(null);
            sommetsBlancs.add(this.listeIntersections.get(i));
        }
        //Initialisation du sommet de départ
        depart.setDistance(0.0);
        depart.setPredecesseur(null);
        sommetsGris.add(depart);

        while (sommetsGris.size() != 0) {
            //Recherche du sommet Si ayant la distance minimale par rapport au
            //sommet de départ
            //Initialisation de indMin et dMin
            dMin = sommetsGris.get(0).getDistance();
            sMin = sommetsGris.get(0);
            for (int i = 0; i < sommetsGris.size(); i++) {
                if (sommetsGris.get(i).getDistance() < dMin) {
                    dMin = sommetsGris.get(i).getDistance();
                    sMin = sommetsGris.get(i);
                }
            }

            successeursMin = sMin.retrouverSuccesseurs();
            for (int j = 0; j < successeursMin.size(); j++) {
                //Si Sj est gris ou blanc alors
                if (sommetsBlancs.contains(successeursMin.get(j))
                        || sommetsGris.contains(successeursMin.get(j))) {
                    //Relacher l'arc (Si, Sj)
                    relacherArc(sMin, successeursMin.get(j));

                    //Si Sj est blanc, alors
                    if (sommetsBlancs.contains(successeursMin.get(j))) {
                        //Colorier en gris
                        sommetsGris.add(successeursMin.get(j));
                        sommetsBlancs.remove(successeursMin.get(j));

                    }
                }
            }
            sommetsGris.remove(sMin);
        }

    }

    

    public Chemin plusCourtChemin(Intersection depart, Intersection arrivee) {
        /*Méthode permettant de retrouver le chemin allant de l'intersection
        de départ à une intersection d'arrivée passée en paramètre
         */
        Chemin chemin = null;
        Intersection intersectionCourante = arrivee;
        ArrayList<Troncon> cheminInverse = new ArrayList<Troncon>(); //on parcourt
        //le chemin dans le sens inverse en utilisant les prédécesseurs

        while (intersectionCourante.getPredecesseur() != null) {
            //Tant qu'on est pas au sommet de départ 
            Intersection intersectionPrec = intersectionCourante;
            intersectionCourante = intersectionCourante.getPredecesseur();

            ArrayList<Troncon> tronconsDepart = intersectionCourante.getTronconsDepart();

            if (tronconsDepart != null) {
                for (int i = 0; i < tronconsDepart.size(); i++) {
                    if (tronconsDepart.get(i).getDestination() == intersectionPrec) {
                        cheminInverse.add(tronconsDepart.get(i));
                        break;
                    }
                }
            }

            //S'il n'y a pas de chemin, retourner null
            if (cheminInverse.size() == 0) {
                return null;
            }

            //Inversion du chemin inverse pour avoir le chemin à l'endroit
            ArrayList<Troncon> successionTroncons = new ArrayList<Troncon>();
            for (int i = cheminInverse.size() - 1; i > -1; i--) {
                successionTroncons.add(cheminInverse.get(i));
            }
            //Creation du chemin correspondant
            chemin = new Chemin(depart, arrivee, successionTroncons);
        }

        return chemin;
    }
    
    //méthode permettant de créer le graphe des plus courts chemins
    public Pair creerGraphePCC() {
        //recuperation du nombre de sommets (en tenant compte de l'entrepot)
        int nbSommets = this.demandesLivraisons.getListePointsInteret().size();
        //Initialisation de la matrice des couts
        Double[][] cout = new Double[nbSommets][nbSommets]; 
        for (int i=0; i<nbSommets;i++) {
                cout[i] = new Double[nbSommets];
        }
        //Initialisation de la matrice des plus courts chemins
        Chemin[][] chemins = new Chemin[nbSommets][nbSommets];
        for (int i=0; i<nbSommets;i++) {
            chemins[i] = new Chemin[nbSommets];
        }
        //Remplissage de la matrice
        //plus courts chemins de l'entrepot vers tous les autres points d'intérêt
        ArrayList<PointInteret> listePointsInteret= this.demandesLivraisons.getListePointsInteret();
        Intersection intersectionCourante;
        Intersection intersectionArrivee;
        Chemin plusCourtChemin = new Chemin();
        
        //plus court chemin de chaque point d'intérêt vers tous les autres 
        //(y compris l'entrepot)
        
        for (int i=0; i<nbSommets; i++) {
            intersectionCourante = listePointsInteret.get(i).getIntersection();
            dijkstra(intersectionCourante);
            
            //plus courts chemins vers les autres points d'intérêt
            for (int j=0;j<nbSommets;j++) {
                if (i != j) {
                    intersectionArrivee = listePointsInteret.get(j).getIntersection();
                    plusCourtChemin = plusCourtChemin(intersectionCourante,intersectionArrivee);
                     if (plusCourtChemin == null) {
                         cout[i][j] = INFINI;
                         chemins[i][j] = null;
                     } else {
                         cout[i][j] = plusCourtChemin.getLongueur();
                         chemins[i][j] = plusCourtChemin;
            }
                }
                else if (i==j) {
                    cout[i][j]=0.0;
                    chemins[i][j]=null;
                }
                
            }
        }
        Pair coutEtChemins = new Pair<>(cout,chemins);
        return coutEtChemins;
        
    }
    
    public Tournee calculerTournee() {
        
        Pair coutEtChemin = creerGraphePCC();
        Double[][] cout = (Double[][]) coutEtChemin.getKey();
        Chemin[][] chemins = (Chemin[][]) coutEtChemin.getValue();
        
        ArrayList<PointInteret> listePointsInteret = demandesLivraisons.getListePointsInteret();
        int nbSommets = listePointsInteret.size();
        Integer[] duree = new Integer[nbSommets];
        for (int i=0;i<nbSommets;i++) {
            duree[i]=3;
        }
        
        unTSP.chercheSolution(1000000,nbSommets,cout,duree);
        
        
        
        Integer lastPoint = unTSP.getMeilleureSolution(0);
        //Creation de la tournée
        Tournee tournee = new Tournee();
        Integer currentPoint=0;
        PointInteret pointCourant = new PointInteret();
        for (int i = 1; i < nbSommets; i++) {
            currentPoint = unTSP.getMeilleureSolution(i);
            System.out.println("lastpoint "+lastPoint);
            System.out.println("currentpoint "+currentPoint);
            Chemin chemin = chemins[lastPoint][currentPoint];
            pointCourant = listePointsInteret.get(lastPoint);
            pointCourant.setCheminDepart(chemin);
            System.out.println("pt Couran"+pointCourant);
            System.out.println(chemin);
            tournee.ajouterPointInteret(pointCourant);
            lastPoint = currentPoint;
        }
        
        Chemin chemin = chemins[currentPoint][0];
        pointCourant = listePointsInteret.get(lastPoint);
        pointCourant.setCheminDepart(chemin);
        System.out.println("pt Couran" + pointCourant);
        System.out.println(chemin);
        tournee.ajouterPointInteret(pointCourant);
        return tournee;
        
    }

  // Lecture des fichier XML
  // FileFilter pour les fichiers Xml
    FileFilter fileFilter = new FileFilter() {
        @Override
        public boolean accept(File f) {
            if (f == null) {
                return false;
            }
            if (f.isDirectory()) {
                return true;
            }
            String extension = getExtension(f);
            if (extension == null) {
                return false;
            }
            return extension.contentEquals("xml");
        }

        @Override
        public String getDescription() {
            return "Fichier XML";
        }

        private String getExtension(File f) {
            String filename = f.getName();
            int i = filename.lastIndexOf('.');
            if (i > 0 && i < filename.length() - 1) {
                return filename.substring(i + 1).toLowerCase();
            }
            return null;
        }
    };

    // Permettant de selectionner un fichier XML dans une fenetre
    public File choisirFichierXML(boolean lecture) throws Exception {
        int returnVal;
        JFileChooser jFileChooserXML = new JFileChooser();
        jFileChooserXML.setFileFilter(fileFilter);
        jFileChooserXML.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (lecture) {
            returnVal = jFileChooserXML.showOpenDialog(null);
        } else {
            returnVal = jFileChooserXML.showSaveDialog(null);
        }
        if (returnVal != JFileChooser.APPROVE_OPTION) {
            throw new Exception("Probleme a l'ouverture du fichier");
        }
        return new File(jFileChooserXML.getSelectedFile().getAbsolutePath());
    }

    //charger les donnees dans la liste des intersections
    public void construireCarteAPartirDeDOMXML(Element noeudDOMRacine) throws NumberFormatException {
        NodeList listeNoeuds = noeudDOMRacine.getElementsByTagName("noeud");
        for (int i = 0; i < listeNoeuds.getLength(); i++) {
            String id = listeNoeuds.item(i).getAttributes().item(0).getNodeValue();
            String latitude = listeNoeuds.item(i).getAttributes().item(1).getNodeValue();
            String longitude = listeNoeuds.item(i).getAttributes().item(2).getNodeValue();
            this.ajouterIntersection(new Intersection(id, Double.parseDouble(latitude), Double.parseDouble(longitude)));
        }

        NodeList listeTroncons = noeudDOMRacine.getElementsByTagName("troncon");
        for (int i = 0; i < listeTroncons.getLength(); i++) {
            String destination = listeTroncons.item(i).getAttributes().item(0).getNodeValue();
            String longueur = listeTroncons.item(i).getAttributes().item(1).getNodeValue();
            String nomRue = listeTroncons.item(i).getAttributes().item(2).getNodeValue();
            String origine = listeTroncons.item(i).getAttributes().item(3).getNodeValue();

            for (Intersection interDep : listeIntersections) {
                if (interDep.getId().equals(origine)) {
                    for (Intersection interArr : listeIntersections) {
                        if (interArr.getId().equals(destination)) {
                            interDep.ajouterTronconDepart(new Troncon(nomRue, Double.parseDouble(longueur), interDep, interArr));
                        }
                    }
                }
            }
        }
    }

    //charger les donnees dans demandesLivraisons
    public void construireLivraisonAPartirDeDOMXML(Element noeudDOMRacine) throws NumberFormatException,Exception {
        if(listeIntersections.isEmpty()){
            throw new Exception("Infos cartes non chargées");
        }
        NodeList entrepot = noeudDOMRacine.getElementsByTagName("entrepot");
        String adresse = entrepot.item(0).getAttributes().item(0).getNodeValue();
        String heureDepart = entrepot.item(0).getAttributes().item(1).getNodeValue();
        for (Intersection i : listeIntersections) {
            if (i.getId().equals(adresse)) { 
                this.demandesLivraisons = new DemandesLivraisons(new PointInteret(i,0));
            }
        }
        this.demandesLivraisons.setHeureDepart(heureDepart);
        
        NodeList listeLivraisons = noeudDOMRacine.getElementsByTagName("livraison");
        for (int i = 0; i < listeLivraisons.getLength(); i++) {
           
            String adresseEnlevement = listeLivraisons.item(i).getAttributes().item(0).getNodeValue();
            String adresseLivraison = listeLivraisons.item(i).getAttributes().item(1).getNodeValue();
            String dureeEnlevement = listeLivraisons.item(i).getAttributes().item(2).getNodeValue();
            String dureeLivraison = listeLivraisons.item(i).getAttributes().item(3).getNodeValue();
            
            for (Intersection j : listeIntersections) {
                for (Intersection k : listeIntersections) {
                    if ((j.getId().equals(adresseEnlevement)) && (k.getId().equals(adresseLivraison))) {
                        
                        PointInteret pe = new PointInteret(j, Integer.parseInt(dureeEnlevement));
                        pe.setEstEnlevement(true);

                        PointInteret pl = new PointInteret(k, Integer.parseInt(dureeLivraison));
                        pl.setEstEnlevement(false);
                        
                        pe.setPointDependance(pl);
                        pl.setPointDependance(pe);
                        
                        this.demandesLivraisons.ajouterPointInteret(pe);
                        this.demandesLivraisons.ajouterPointInteret(pl);
                        
                    }
                }
            }
        }
    }

    // lancer l'ouvreur de fichier et choisir la bonne methode pour charger les donnees
    public void chargerCarte() throws Exception, ParserConfigurationException, SAXException, IOException {
      
        File xml = choisirFichierXML(true);
        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = docBuilder.parse(xml);
        Element racine = document.getDocumentElement();
        
        if (racine.getNodeName().equals("reseau")) {
            construireCarteAPartirDeDOMXML(racine);
            //System.out.println(this.getListeIntersections().toString());
        }else {
            throw new Exception("Document non conforme");
        }
    }
    
    public void chargerLivraison() throws Exception, ParserConfigurationException, SAXException, IOException {
      
        File xml = choisirFichierXML(true);
        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = docBuilder.parse(xml);
        Element racine = document.getDocumentElement();
        
        if (racine.getNodeName().equals("demandeDeLivraisons")) {
            construireLivraisonAPartirDeDOMXML(racine);
        } else {
            throw new Exception("Document non conforme");
        }
    }

    // Les methodes d'affichage ne servent qu'à vérifier les résultats de la lecture
    public void AfficherIntersections() {
        for (Intersection i : listeIntersections) {
            System.out.println("id:" + i.getId() + " latitude:" + i.getLatitude() + " longitude:" + i.getLongitude());
            System.out.println("listeTronconDepart:" + i.getTronconsDepart());
            System.out.println();
        }
    }

    public void AfficherLivraisons() {
        DemandesLivraisons dl = this.demandesLivraisons;
        System.out.println("adresseDepart:"+dl.getAdresseDepart()+" heureDepart:"+dl.getHeureDepart());
        for(PointInteret pI:dl.getListePointsInteret()){
            System.out.println("adresse:"+pI.getIntersection().getId()+" duree:"+pI.getDuree()+((pI.isEstEnlevement())?" estEnlevement":" estLivraison"));
        }
    }

}
