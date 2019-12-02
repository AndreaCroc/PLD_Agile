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
    private TSP2 unTSP;
    private Tournee uneTournee;
    public static final Double INFINI = 1000000.0; //Valeur max 
    public static final Double NON_DEFINI = -1000.0;

    public Carte() {
        this.listeIntersections = new ArrayList<Intersection>();
        this.unTSP = new TSP2();
        this.uneTournee = new Tournee();
    }

    public ArrayList<Intersection> getListeIntersections() {
        return listeIntersections;
    }

    public void ajouterIntersection(Intersection i) {
        this.listeIntersections.add(i);
    }

    public void setListeIntersections(ArrayList<Intersection> listeIntersections) {
        this.listeIntersections = listeIntersections;
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

    /**
     * Relâchement d'un arc (troncon) reliant deux intersections Utilisée pour
     * le calcul des plus courts chemins (Dijkstra)
     *
     * @param depart origine du troncon
     * @param arrivee destination du troncon
     */
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
                coutArc = (arc.getLongueur() * 15) / 3.6;
            }
        }
        if (arrivee.getDistance() > depart.getDistance() + coutArc) {
            //Relachement de l'arc
            arrivee.setDistance(depart.getDistance() + coutArc);
            arrivee.setPredecesseur(depart);
        }
    }

    /**
     * Implémentation de l'algorithme de Dijkstra permettant de déterminer les
     * plus courts chemins à partir d'une source dans la carte donnée
     *
     * @param depart intersection de départ pour laquelle nous souhaitons
     * trouver les plus courts chemins vers les autres intersections
     */
    public void dijkstra(Intersection depart) {
        Double dMin;
        Intersection sMin; //sommet de la liste de sommets gris ayant la distance minimale
        ArrayList<Intersection> sommetsBlancs = new ArrayList<Intersection>();
        ArrayList<Intersection> sommetsGris = new ArrayList<Intersection>();
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

    /**
     * Méthode permettant de trouver le plus court chemin partant d'une
     * intersection et arrivant à une autre. Elle est appelée après une
     * exécution de Dijkstra pour trouver le succession des points reliants ces
     * deux intersections.
     *
     * @param depart intersection de départ
     * @param arrivee intersection d'arrivée
     */
    public Chemin plusCourtChemin(Intersection depart, Intersection arrivee) {
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

    /**
     * Méthode permettant de créer le graphe des plus courts chemins à partir
     * des points d'intérêt de la carte (donc des demandes de livraisons
     * actuellement chargées)
     *
     * @return la paire cout, chemin où cout[i][j] et la durée du trajet entre
     * le point d'intérêt i et le point d'intérêt j; et chemins[i][j] et la
     * liste ordonnées de tronçons représentant le plus court chemin de i à j.
     */
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
        //plus courts chemins de l'entrepot vers tous les autres points d'intéràªt
        ArrayList<PointInteret> listePointsInteret = this.demandesLivraisons.getListePointsInteret();
        Intersection intersectionCourante;
        Intersection intersectionArrivee;
        Chemin plusCourtChemin = new Chemin();

        //plus court chemin de chaque point d'intéràªt vers tous les autres 
        //(y compris l'entrepot)
        for (int i = 0; i < nbSommets; i++) {
            intersectionCourante = listePointsInteret.get(i).getIntersection();
            dijkstra(intersectionCourante);

            //plus courts chemins vers les autres points d'intéràªt
            for (int j = 0; j < nbSommets; j++) {
                if (i != j) {
                    intersectionArrivee = listePointsInteret.get(j).getIntersection();
                    plusCourtChemin = plusCourtChemin(intersectionCourante, intersectionArrivee);
                    if (plusCourtChemin == null) {
                        cout[i][j] = INFINI;
                        chemins[i][j] = null;
                    } else {
                        //Recuperation du cout en secondes
                        cout[i][j] = plusCourtChemin.getLongueur();
                        chemins[i][j] = plusCourtChemin;

                    }
                } else if (i == j) {

                    if (!listePointsInteret.get(i).isEnlevement() && i != 0) {
                        double numPredecesseurs = 0.0;
                        String idJ = listePointsInteret.get(i).getPointDependance().getIntersection().getId();
                        for (int k = 0; k < nbSommets; k++) {
                            String IdK = listePointsInteret.get(k).getIntersection().getId();
                            if (idJ == IdK) {
                                numPredecesseurs = (double) k;
                            }
                        }

                        cout[i][j] = numPredecesseurs;
                    } else {
                        cout[i][j] = 0.0;
                    }
                    chemins[i][j] = null;
                }

            }
        }
        Pair coutEtChemins = new Pair<>(cout, chemins);
        return coutEtChemins;

    }

    /**
     * Méthode permettant de calculer une tournée pour répondre aux demandes de
     * livraison
     *
     * @return l'objet tournée créée
     */
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
        //Ajout du dernier point d'intérêt qui retourne vers l'entrepôt
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

        //Calcul de l'heure d'arrivee à  l'entrepôt en fin de tournée
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

    /**
     * Méthode permettant de convertir une heure donnée (en string hh:mm:ss) en
     * int (nombre de secondes) Utilisée pour les calculs des heures de départ
     * et d'arrivée lors du calcul de la tournée
     *
     * @param heureStr l'heure en string
     *
     * @return l'heure en int
     */
    public Integer heureToInt(String heureStr) {
        Integer heureInt;
        String[] elements = heureStr.split(":");
        int nbHeure = Integer.parseInt(elements[0]);
        int nbMinutes = Integer.parseInt(elements[1]);
        int nbSecondes = Integer.parseInt(elements[2]);
        heureInt = nbHeure * 3600 + nbMinutes * 60 + nbSecondes;
        return heureInt;
    }

    /* Méthode permettant de convertir une heure donnée (en nombre de secondes,
     * donc int) en string (hh:mm:ss)
     * Utilisée pour les calculs des heures de départ et d'arrivée lors du calcul
     * de la tournée/**
     *
     * @param heureStr l'heure en int
     *
     * @return l'heure en string
     */
    public String intToHeure(Integer heureInt) {
        String heureStr;
        int nbHeures = heureInt / 3600;
        int nbMinutes = (heureInt - (nbHeures * 3600)) / 60;
        int nbSecondes = heureInt - (nbHeures * 3600) - (nbMinutes * 60);
        if (nbHeures >= 24) {
            nbHeures = nbHeures - 24;
        }
        String nbH = Integer.toString(nbHeures);
        if (nbHeures < 10) {
            nbH = "0" + nbH;
        }

        String nbM = Integer.toString(nbMinutes);
        if (nbMinutes < 10) {
            nbM = "0" + nbM;
        }
        String nbS = Integer.toString(nbSecondes);
        if (nbSecondes < 10) {
            nbS = "0" + nbS;
        }
        heureStr = nbH + ":" + nbM + ":" + nbS;
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
     * Chargement des donnà©es de l'element racine d'un document xml contenant
     * le plan de la ville Complete l'attribut listeIntersections et leurs
     * troncons avec les informations lues depuis le document
     *
     * @param noeudDOMRacine Noeud racine du fichier xml a lire
     * @return true si la lecture s'est correctement passee false s'il manque un
     * element dans le fichier xml
     * @throws NumberFormatException
     */
    public boolean construireCarteAPartirDeDOMXML(Element noeudDOMRacine) throws NumberFormatException {
        this.setListeIntersections(new ArrayList<Intersection>());
        boolean ok = true;

        //Lecture des intersections
        NodeList listeNoeuds = noeudDOMRacine.getElementsByTagName("noeud");

        //Si aucune intersection n'a pu etre lu, la methode retourne false
        if (listeNoeuds.getLength() > 0) {
            //Ajout des intersections dans la liste des intersections
            for (int i = 0; i < listeNoeuds.getLength(); i++) {
                String id = listeNoeuds.item(i).getAttributes().item(0).getNodeValue();
                String latitude = listeNoeuds.item(i).getAttributes().item(1).getNodeValue();
                String longitude = listeNoeuds.item(i).getAttributes().item(2).getNodeValue();
                this.ajouterIntersection(new Intersection(id, Double.parseDouble(latitude), Double.parseDouble(longitude)));
            }

            //Lecture des troncons
            NodeList listeTroncons = noeudDOMRacine.getElementsByTagName("troncon");

            //Si aucun troncon n'a pu etre lu, la methode retourne false
            if (listeTroncons.getLength() > 0) {
                // Ajout des troncons : ajout des troncons de depart correspondants a chaque intersection 
                // selon les origines et destinations lues
                for (int i = 0; i < listeTroncons.getLength(); i++) {
                    String destination = listeTroncons.item(i).getAttributes().item(0).getNodeValue();
                    String longueur = listeTroncons.item(i).getAttributes().item(1).getNodeValue();
                    String nomRue = listeTroncons.item(i).getAttributes().item(2).getNodeValue();
                    String origine = listeTroncons.item(i).getAttributes().item(3).getNodeValue();

//                    for (Intersection interDep : listeIntersections) {
//                        if (interDep.getId().equals(origine)) {
//                            for (Intersection interArr : listeIntersections) {
//                                if (interArr.getId().equals(destination)) {
//                                    //System.out.println("Ajout d'un troncon " + nomRue + longueur + interDep +interArr);
//                                    interDep.ajouterTronconDepart(new Troncon(nomRue, Double.parseDouble(longueur), interDep, interArr));
//                                }
//                            }
//                        }
//                    }
                    Intersection interDep = null;
                    Intersection interArr = null;
                    for (Intersection inter : listeIntersections) {
                        if (inter.getId().equals(origine)) {
                            interDep = inter;
                        }
                        if (inter.getId().equals(destination)) {
                            interArr = inter;
                        }
                    }
                    if (interDep != null && interArr != null) {
                        interDep.ajouterTronconDepart(new Troncon(nomRue, Double.parseDouble(longueur), interDep, interArr));
                    } else {
                        ok = false; // s'il y a un point non-trouvà© dans la liste
                    }
                }
            } else {
                ok = false;
            }
        } else {
            ok = false;
        }

        return ok;
    }

    /**
     * Chargement des données de l'element racine d'un document xml contenant
     * les demandes de livraisons Complete l'attribut demandesLivraisons et la
     * liste des points d'interet qui la composent
     *
     * @param noeudDOMRacine Noeud racine du fichier xml a lire
     *
     * @return true si la lecture s'est correctement passee false s'il manque un
     * element dans le fichier xml
     *
     * @throws NumberFormatException
     * @throws Exception
     */
    public boolean construireLivraisonAPartirDeDOMXML(Element noeudDOMRacine) throws NumberFormatException, Exception {
        this.setDemandesLivraisons(null);
        boolean ok = true;

        if (listeIntersections.isEmpty()) {
            ok = false;
            throw new Exception("Infos cartes non chargées");
        }
        NodeList entrepot = noeudDOMRacine.getElementsByTagName("entrepot");

        if (entrepot.getLength() > 0) {
            String adresse = entrepot.item(0).getAttributes().item(0).getNodeValue();
            String heureDepart = entrepot.item(0).getAttributes().item(1).getNodeValue();
            PointInteret pI = null;

            for (Intersection i : listeIntersections) {
                if (i.getId().equals(adresse)) {
                    pI = new PointInteret(i, 0);
                    this.demandesLivraisons = new DemandesLivraisons(pI);
                }
            }
            if (pI == null) {
                ok = false;     // si on toruve pas d'intersection correspondant dans la liste
            }
            this.demandesLivraisons.setHeureDepart(heureDepart);
            this.demandesLivraisons.ajouterPointInteret(pI);
            pI.setEntrepot(true);

            NodeList listeLivraisons = noeudDOMRacine.getElementsByTagName("livraison");
            if (listeLivraisons.getLength() > 0) {
                for (int i = 0; i < listeLivraisons.getLength(); i++) {

                    String adresseEnlevement = listeLivraisons.item(i).getAttributes().item(0).getNodeValue();
                    String adresseLivraison = listeLivraisons.item(i).getAttributes().item(1).getNodeValue();
                    String dureeEnlevement = listeLivraisons.item(i).getAttributes().item(2).getNodeValue();
                    String dureeLivraison = listeLivraisons.item(i).getAttributes().item(3).getNodeValue();

                    PointInteret pe = null;
                    PointInteret pl = null;
                    for (Intersection j : listeIntersections) {
                        if ((j.getId().equals(adresseEnlevement))) {
                            pe = new PointInteret(j, Integer.parseInt(dureeEnlevement));
                            pe.setEstEnlevement(true);
                        }
                        if ((j.getId().equals(adresseLivraison))) {
                            pl = new PointInteret(j, Integer.parseInt(dureeLivraison));
                            pl.setEstEnlevement(false);
                        }
                    }
                    if (pe != null && pl != null) {
                        pe.setPointDependance(pl);
                        pl.setPointDependance(pe);

                        pe.setNumeroDemande(i + 1);
                        pl.setNumeroDemande(i + 1);

                        this.demandesLivraisons.ajouterPointInteret(pe);
                        this.demandesLivraisons.ajouterPointInteret(pl);
                    } else {
                        ok = false; // s'il y a un point non-trouvé dans la liste
                    }

                }
            } else {
                ok = false;
            }

        } else {
            ok = false;
        }

        return ok;
    }

    // lancer l'ouvreur de fichier et choisir la bonne methode pour charger les donnees
    public boolean chargerCarte(boolean estUnChangement) throws Exception, ParserConfigurationException, SAXException, IOException {
        boolean result = false;
        File xml = choisirFichierXML(true);
        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = docBuilder.parse(xml);
        Element racine = document.getDocumentElement();

        if (racine.getNodeName().equals("reseau")) {
            if (estUnChangement) {
                this.listeIntersections.clear();
            }
            if (construireCarteAPartirDeDOMXML(racine)) {
                result = true;
            }
            //System.out.println(this.getListeIntersections().toString());
        }
        if (estUnChangement) {
            this.demandesLivraisons.supprimerLivraison();
        }
        return result;
    }

    public boolean chargerLivraison() throws Exception, ParserConfigurationException, SAXException, IOException {
        boolean result = false;
        File xml = choisirFichierXML(true);
        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = docBuilder.parse(xml);
        Element racine = document.getDocumentElement();

        if (racine.getNodeName().equals("demandeDeLivraisons")) {
            if (construireLivraisonAPartirDeDOMXML(racine)) {
                result = true;
            }
        }
        return result;
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
        System.out.println("adresseDepart:" + dl.getAdresseDepart() + " heureDepart:" + dl.getHeureDepart());
        for (PointInteret pI : dl.getListePointsInteret()) {
            System.out.println("adresse:" + pI.getIntersection().getId() + " duree:" + pI.getDuree() + ((pI.isEnlevement()) ? " estEnlevement" : " estLivraison"));
        }
    }

}
