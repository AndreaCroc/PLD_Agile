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
    private Tournee uneTournee;
    public static final Double INFINI = 1000000.0; //Valeur max 
    public static final Double NON_DEFINI = -1000.0;

    public Carte() {
        this.listeIntersections = new ArrayList<Intersection>();
        this.unTSP = new TSP1();
        this.uneTournee = new Tournee();
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

    public Tournee getTournee() {
        return uneTournee;
    }

    public void setUneTournee(Tournee uneTournee) {
        this.uneTournee = uneTournee;
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
                //Recuperation du cout de l'arc (en secondes)
                coutArc = (arc.getLongueur()*15)/3.6;
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
    //méthode permettant de créer le graphe des plus courts chemins
    public Pair creerGraphePCC() {
        //recuperation du nombre de sommets (en tenant compte de l'entrepot)
        int nbSommets = this.demandesLivraisons.getListePointsInteret().size();
        //Initialisation de la matrice des couts
        Double[][] cout = new Double[nbSommets][nbSommets];
        for (int i = 0; i < nbSommets; i++) {
            cout[i] = new Double[nbSommets];
        }
        //Initialisation de la matrice des plus courts chemins
        Chemin[][] chemins = new Chemin[nbSommets][nbSommets];
        for (int i = 0; i < nbSommets; i++) {
            chemins[i] = new Chemin[nbSommets];
        }
        //Remplissage de la matrice
        //plus courts chemins de l'entrepot vers tous les autres points d'intérÃªt
        ArrayList<PointInteret> listePointsInteret = this.demandesLivraisons.getListePointsInteret();
        Intersection intersectionCourante;
        Intersection intersectionArrivee;
        Chemin plusCourtChemin = new Chemin();

        //plus court chemin de chaque point d'intérÃªt vers tous les autres 
        //(y compris l'entrepot)
        for (int i = 0; i < nbSommets; i++) {
            intersectionCourante = listePointsInteret.get(i).getIntersection();
            dijkstra(intersectionCourante);

            //plus courts chemins vers les autres points d'intérÃªt
            for (int j = 0; j < nbSommets; j++) {
                if (i != j) {
                    intersectionArrivee = listePointsInteret.get(j).getIntersection();
                    plusCourtChemin = plusCourtChemin(intersectionCourante, intersectionArrivee);
                    if (plusCourtChemin == null) {
                        cout[i][j] = INFINI;
                        chemins[i][j] = null;
                    } else {
                        //Recuperation du cout en secondes
                        cout[i][j] = (plusCourtChemin.getLongueur() * 15) / 3.6;
                        chemins[i][j] = plusCourtChemin;
                    }
                } else if (i == j) {
                    cout[i][j] = 0.0;
                    chemins[i][j] = null;
                }

            }
        }
        Pair coutEtChemins = new Pair<>(cout, chemins);
        return coutEtChemins;

    }

    public Tournee calculerTournee() {

        Pair coutEtChemin = creerGraphePCC();
        Double[][] cout = (Double[][]) coutEtChemin.getKey();
        Chemin[][] chemins = (Chemin[][]) coutEtChemin.getValue();

        ArrayList<PointInteret> listePointsInteret = demandesLivraisons.getListePointsInteret();
        int nbSommets = listePointsInteret.size();

        //Initialisation des durees
        Integer[] duree = new Integer[nbSommets];
        for (int i = 0; i < nbSommets; i++) {
            duree[i] = listePointsInteret.get(i).getDuree();
        }
        //Execution du TSP
        unTSP.chercheSolution(1000000, nbSommets, cout, duree);

        Integer indPointPrec = unTSP.getMeilleureSolution(0);

        //Creation de la tournée
        Tournee tournee = new Tournee();
        Integer indPointCourant = 0;
        PointInteret pointCourant = new PointInteret();

        //Recupération de l'heure de départ de l'entrepot
        Integer heureDepartPrec = heureToInt(demandesLivraisons.getHeureDepart());

        Integer heureArriveeCour;
        Integer heureDepartCour;

        int dureeTrajet;
        for (int i = 1; i < nbSommets; i++) {
            indPointCourant = unTSP.getMeilleureSolution(i);
            Chemin chemin = chemins[indPointPrec][indPointCourant];
            pointCourant = listePointsInteret.get(indPointPrec);
            pointCourant.setCheminDepart(chemin);
            if (pointCourant.isEntrepot()) {
                pointCourant.setHeureDepart(intToHeure(heureDepartPrec));
            }
            if (!pointCourant.isEntrepot()) {
                //Mise a jour de l'heure d'arrivee
                dureeTrajet = cout[indPointPrec][indPointCourant].intValue();
                heureArriveeCour = heureDepartPrec + dureeTrajet;
                pointCourant.setHeureArrivee(intToHeure(heureArriveeCour));

                //Mise a jour de l'heure de depart
                heureDepartCour = heureArriveeCour + pointCourant.getDuree();
                pointCourant.setHeureDepart(intToHeure(heureDepartCour));

                heureDepartPrec = heureDepartCour;
            }

            //Ajout a la tournee
            tournee.ajouterPointInteret(pointCourant);
            indPointPrec = indPointCourant;

        }
        //Ajout du dernier point d'intérÃªt qui retourne vers l'entrepÃ´t
        Chemin chemin = chemins[indPointCourant][0];
        pointCourant = listePointsInteret.get(indPointCourant);
        pointCourant.setCheminDepart(chemin);

        //Mise a jour de l'heure d'arrivee
        dureeTrajet = cout[indPointPrec][indPointCourant].intValue();
        heureArriveeCour = heureDepartPrec + dureeTrajet;
        pointCourant.setHeureArrivee(intToHeure(heureArriveeCour));

        //Mise a jour de l'heure de depart
        heureDepartCour = heureArriveeCour + pointCourant.getDuree();
        pointCourant.setHeureDepart(intToHeure(heureDepartCour));
        heureDepartPrec = heureArriveeCour;
        //Ajout a la tournee
        tournee.ajouterPointInteret(pointCourant);

        //Calcul de l'heure d'arrivee Ã  l'entrepÃ´t en fin de tournée
        heureArriveeCour = heureDepartPrec + cout[indPointCourant][0].intValue();
        tournee.getSuccessionPointsInteret().get(0).setHeureArrivee(intToHeure(heureArriveeCour));

        //Calcul de la durée de la tournée
        Integer heureDep = heureToInt(tournee.getSuccessionPointsInteret().get(0).getHeureDepart());
        Integer heureArr = heureToInt(tournee.getSuccessionPointsInteret().get(0).getHeureArrivee());
        Integer dureeTournee = heureArr - heureDep;

        tournee.setDuree(intToHeure(dureeTournee));
        
        this.setUneTournee(tournee);
        return tournee;

    }

    
    public Integer heureToInt(String heureStr) {
        Integer heureInt;
        String[] elements = heureStr.split(":");
        int nbHeure = Integer.parseInt(elements[0]);
        int nbMinutes = Integer.parseInt(elements[1]);
        int nbSecondes = Integer.parseInt(elements[2]);
        heureInt = nbHeure*3600 + nbMinutes*60 + nbSecondes;
        return heureInt;
    }
    
    public String intToHeure (Integer heureInt) {
        String heureStr;
        int nbHeures = heureInt/3600;
        int nbMinutes = (heureInt-(nbHeures*3600))/60;
        int nbSecondes = heureInt-(nbHeures*3600)-(nbMinutes*60);
        String nbH = Integer.toString(nbHeures);
        String nbM = Integer.toString(nbMinutes); 
        String nbS = Integer.toString(nbSecondes);
        heureStr=nbH+":"+nbM+":"+nbS;
        return heureStr;
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

    /**
     * Chargement des données de l'element racine d'un document xml contenant le plan de la ville
     * Complete l'attribut listeIntersections et leurs troncons avec les informations lues depuis le document
     * @param noeudDOMRacine Noeud racine du fichier xml a lire
     * @return true si la lecture s'est correctement passee
     * false s'il manque un element dans le fichier xml
     * @throws NumberFormatException 
     */

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

    

    /**
     * Chargement des donnÃ©es de l'element racine d'un document xml contenant les demandes de livraisons
     * Complete l'attribut demandesLivraisons et la liste des points d'interet qui la composent
     * @param noeudDOMRacine Noeud racine du fichier xml a lire
     * @return true si la lecture s'est correctement passee
     * false s'il manque un element dans le fichier xml
     * @throws NumberFormatException
     * @throws Exception 
     */
    public boolean construireLivraisonAPartirDeDOMXML(Element noeudDOMRacine) throws NumberFormatException,Exception {
        this.setDemandesLivraisons(null);
        boolean ok = true;
        
        if(listeIntersections.isEmpty()){
            ok = false;
            throw new Exception("Infos cartes non chargÃ©es");
        }
        NodeList entrepot = noeudDOMRacine.getElementsByTagName("entrepot");
        
        if(entrepot.getLength()>0)
        {
            String adresse = entrepot.item(0).getAttributes().item(0).getNodeValue();
            String heureDepart = entrepot.item(0).getAttributes().item(1).getNodeValue();
            PointInteret pI = null;
            
            for (Intersection i : listeIntersections) {
                if (i.getId().equals(adresse)) { 
                    pI = new PointInteret(i,0);
                    this.demandesLivraisons = new DemandesLivraisons(pI);
                }
            }
            if(pI == null){
                ok = false;     // si on toruve pas d'intersection correspondant dans la liste
            }
            this.demandesLivraisons.setHeureDepart(heureDepart);
            this.demandesLivraisons.ajouterPointInteret(pI);
            
            NodeList listeLivraisons = noeudDOMRacine.getElementsByTagName("livraison");
            if(listeLivraisons.getLength()>0)
            {
                for (int i = 0; i < listeLivraisons.getLength(); i++) {

                    String adresseEnlevement = listeLivraisons.item(i).getAttributes().item(0).getNodeValue();
                    String adresseLivraison = listeLivraisons.item(i).getAttributes().item(1).getNodeValue();
                    String dureeEnlevement = listeLivraisons.item(i).getAttributes().item(2).getNodeValue();
                    String dureeLivraison = listeLivraisons.item(i).getAttributes().item(3).getNodeValue();
                    
                    PointInteret pe = null;
                    PointInteret pl = null;
                    for(Intersection j : listeIntersections){
                         if ((j.getId().equals(adresseEnlevement))){
                            pe = new PointInteret(j, Integer.parseInt(dureeEnlevement));
                            pe.setEstEnlevement(true);
                         }
                         if ((j.getId().equals(adresseLivraison))){
                            pl = new PointInteret(j, Integer.parseInt(dureeLivraison));
                            pl.setEstEnlevement(false);
                         }
                    }
                    if(pe!=null&&pl!=null){
                        pe.setPointDependance(pl);
                        pl.setPointDependance(pe);

                        this.demandesLivraisons.ajouterPointInteret(pe);
                        this.demandesLivraisons.ajouterPointInteret(pl);
                    }else{
                        ok = false; // s'il y a un point non-trouvÃ© dans la liste
                    }
                    
                }
            } else {
                ok = false;
            }
           
        } else{
            ok = false;
        }
        
        
        
        return ok;
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
            System.out.println("adresse:"+pI.getIntersection().getId()+" duree:"+pI.getDuree()+((pI.isEnlevement())?" estEnlevement":" estLivraison"));
        }
    }

}
