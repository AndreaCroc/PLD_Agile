package modele;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

    public static final Double INFINI = 1000.0; //Valeur max 
    public static final Double NON_DEFINI = -1000.0;

    public Carte() {
        this.listeIntersections = new ArrayList<Intersection>();
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
        System.out.println("Relachement Arc (" + depart.getId() + "," + arrivee.getId() + ")");
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

    public PointInteret trouverPointInteret(Intersection intersection) {
        /*Méthode permettant de trouver le point d'interet correspondant à 
        l'intersection intersection. Celui-ci est null si intersection n'est pas
        un point d'interet.
         */
        PointInteret pI = null;
        ArrayList<PointInteret> pointsInteret = this.demandesLivraisons.getListePointsInteret();
        for (int i = 0; i < pointsInteret.size(); i++) {
            if (pointsInteret.get(i).getIntersection() == intersection) {
                pI = pointsInteret.get(i);
            }
        }
        return pI;

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
    public File ouvre(boolean lecture) throws Exception {
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
                            //System.out.println("Ajout d'un troncon " + nomRue + longueur + interDep +interArr);
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
    public void charger() throws Exception, ParserConfigurationException, SAXException, IOException {
       
        File xml = ouvre(true);
        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = docBuilder.parse(xml);
        Element racine = document.getDocumentElement();
        
        if (racine.getNodeName().equals("reseau")) { 
            construireCarteAPartirDeDOMXML(racine);
            //System.out.println(this.getListeIntersections().toString());
        } else if (racine.getNodeName().equals("demandeDeLivraisons")) {
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
