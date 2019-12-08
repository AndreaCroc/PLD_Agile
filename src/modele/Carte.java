package modele;

import tsp.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
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

    //Pour le graphe de plus courts chemins
    Double[][] cout;
    Chemin[][] chemins;
    TreeMap<Integer, Integer> mapPredecesseur;

    //Pour l'affichage des points d'intérêt à tout moment sur la carte
    ArrayList<PointInteret> listePointsInteretActuelle;

    public Carte() {
        this.listeIntersections = new ArrayList<Intersection>();
        this.listePointsInteretActuelle = new ArrayList<PointInteret>();
        this.unTSP = new TSP2();
        this.uneTournee = new Tournee();
    }

    public ArrayList<Intersection> getListeIntersections() {
        return listeIntersections;
    }

    public void ajouterIntersection(Intersection i) {
        this.listeIntersections.add(i);
    }
    
    public void ajouterIntersection2(PointInteret i) {
        this.listePointsInteretActuelle.add(i);
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

    public void setCout(Double[][] cout) {
        this.cout = cout;
    }

    public void setChemins(Chemin[][] chemins) {
        this.chemins = chemins;
    }

    public ArrayList<PointInteret> getListePointsInteretActuelle() {
        return listePointsInteretActuelle;
    }

    public void setListePointsInteretActuelle(ArrayList<PointInteret> listePointsInteretActuelle) {
        this.listePointsInteretActuelle = listePointsInteretActuelle;
    }

    /**
     * Relâchement d'un arc (troncon) reliant deux intersections Utilisée pour
     * le calcul des plus courts chemins (Dijkstra)
     *
     * @param depart origine du troncon
     * @param arrivee destination du troncon
     */
    public void relacherArc(Intersection depart, Intersection arrivee) {
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
 
        if (depart == arrivee) {
            chemin =  new Chemin(depart, arrivee, cheminInverse);
        }
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
                System.out.println("if chiant");
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
    public void creerGraphePCC() {
        //recuperation du nombre de sommets (en tenant compte de l'entrepot)
        int nbSommets = this.demandesLivraisons.getListePointsInteret().size();
        //Initialisation de la matrice des couts
        cout = new Double[nbSommets + 5][nbSommets + 5];
        for (int i = 0; i < nbSommets + 5; i++) {
            cout[i] = new Double[nbSommets + 5];
        }
        //Initialisation de la matrice des plus courts chemins (avec des colonnes
        //et lignes supplémentaires)
        chemins = new Chemin[nbSommets + 5][nbSommets + 5];
        for (int i = 0; i < nbSommets + 5; i++) {
            chemins[i] = new Chemin[nbSommets + 5];
        }
        //Remplissage de la matrice
        //plus courts chemins de l'entrepot vers tous les autres points d'intéràªt
        ArrayList<PointInteret> listePointsInteret = this.demandesLivraisons.getListePointsInteret();
        Intersection intersectionCourante;
        Intersection intersectionArrivee;
        Chemin plusCourtChemin = new Chemin();

        //plus court chemin de chaque point d'intéràªt vers tous les autres 
        //(y compris l'entrepot)
        long debut = System.currentTimeMillis();
        System.out.println("DEBUT DIKSTRA : ");
        for (int i = 0; i < nbSommets; i++) {
            intersectionCourante = listePointsInteret.get(i).getIntersection();
            dijkstra(intersectionCourante);

            //plus courts chemins vers les autres points d'intérêt
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

    }

    /**
     * Méthode permettant de calculer une tournée pour répondre aux demandes de
     * livraison
     *
     * @return l'objet tournée créée
     */
    public Tournee calculerTournee() {
        ArrayList<PointInteret> listePointsInteret = demandesLivraisons.getListePointsInteret();
        int nbSommets = listePointsInteret.size();
        
        
        //Creation de la tournée
        Tournee tournee = new Tournee();
        Integer indPointCourant = 0;
        Integer indPointPrec;
        Chemin chemin;
        unTSP = new TSP2();
        
        creerGraphePCC();
        
        //Cas où il n'y a aucune demande de livraisons
        if (nbSommets == 1) {
            tournee.ajouterPointInteret(listePointsInteret.get(0));
            this.setUneTournee(tournee);
        } else {

            //Initialisation des durees
            Integer[] duree = new Integer[nbSommets];
            for (int i = 0; i < nbSommets; i++) {
                duree[i] = listePointsInteret.get(i).getDuree();
            }

            //Execution du TSP
            unTSP.chercheSolution(1000000, nbSommets, cout, duree);

            indPointPrec = unTSP.getMeilleureSolution(0);

            PointInteret pointCourant = new PointInteret();
            for (int i = 1; i < nbSommets; i++) {
                indPointCourant = unTSP.getMeilleureSolution(i);
                chemin = chemins[indPointPrec][indPointCourant];
                pointCourant = listePointsInteret.get(indPointPrec);
                pointCourant.setCheminDepart(chemin);

                //Ajout a la tournee
                tournee.ajouterPointInteret(pointCourant);
                indPointPrec = indPointCourant;
                
            }
            //Ajout du dernier point d'intérêt qui retourne vers l'entrepôt
            chemin = chemins[indPointCourant][0];
            pointCourant = listePointsInteret.get(indPointCourant);
            pointCourant.setCheminDepart(chemin);

            //Ajout a la tournee
            tournee.ajouterPointInteret(pointCourant);

            this.setUneTournee(tournee);

            //Calcul des heures
            this.calculerHeuresTournee();
        }
        return tournee;
    }

    /**
     * Méthode permettant de supprimer un point d'intérêt d'une tournée
     *
     * @param pointInteret le point d'intérêt à supprimer
     * @return vrai si la suppression a été effectuée, faux sinon
     */
    public boolean supprimerPointInteret(PointInteret pointInteret) {
        ArrayList<PointInteret> successionPointsInteret = uneTournee.getSuccessionPointsInteret();
        ArrayList<PointInteret> listePointsInteret = demandesLivraisons.getListePointsInteret();
        PointInteret pointPrec; //Point précédent le point dans la tournée
        PointInteret pointSuivant; //Point suivant le point dans la tournée
        int indPrecListeP; //Indice du point precedent dans la liste des points d'intérêts
        int indSuivListeP; //Indice du point suivant dans la liste des points d'intérêts
        int indPointTournee; //Indice du point d'intérêt dans la tournée
        PointInteret pointDependance = pointInteret.getPointDependance(); //point
        //d'enlévement ou de livraison associé
        Chemin cheminPointPrec; //Chemin allant du point précédent au point suivant

        if (!successionPointsInteret.contains(pointInteret)) {
            return false;
        }

        //Récupération de la position du points dans la tournee
        indPointTournee = successionPointsInteret.indexOf(pointInteret);

        //Recuperation du point d'intérêt précédent 
        pointPrec = successionPointsInteret.get(indPointTournee - 1);
        //Recuperation de l'indice du point prec dans la liste des points d'intérêts
        indPrecListeP = listePointsInteret.indexOf(pointPrec);
        //Si le point d'intérêt est le dernier de la tournee (avant le retour à 
        //l'entrepot
        if (indPointTournee == successionPointsInteret.size() - 1) {
            //Recuperation du point d'intérêt suivant (l'entrepôt)
            pointSuivant = successionPointsInteret.get(0);
            //Recuperation de l'indice du point dans la liste des points d'intérêts
            indSuivListeP = 0;
        } else {
            //Recuperation du point d'intérêt suivant 
            pointSuivant = successionPointsInteret.get(indPointTournee + 1);
            //Recuperation de l'indice du point dans la liste des points d'intérêts
            indSuivListeP = listePointsInteret.indexOf(pointSuivant);
        }

        //Prise en compte du cas où il ne reste que l'entrepôt
        if (!(pointPrec == pointSuivant)) {
            cheminPointPrec = chemins[indPrecListeP][indSuivListeP];
            pointPrec.setCheminDepart(cheminPointPrec);
        }

        //Suppression du point de la tournee et de la liste des points d'intérêts
        successionPointsInteret.remove(pointInteret);
        listePointsInteretActuelle.remove(pointInteret);

        //Suppression du point de dépendance correspondant (si nécessaire)
        if (successionPointsInteret.contains(pointDependance)) {
            supprimerPointInteret(pointDependance);
        }

        //S'il reste encore des éléments dans la tournée     
        if (successionPointsInteret.size() > 1) {
            //Mise à jour des heures de départ et d'arrivée
            calculerHeuresTournee();
        }

        return true;
    }
    
    /**
     * Méthode permettant de déplacer un point d'intérêt dans la tournée à une 
     * nouvelle position
     * @param pointADeplacer
     * @param decalage
     * @return vrai si la contrainte de précédence est encore respectée, faux sinon
     */
    public boolean deplacerPointInteret(PointInteret pointADeplacer, int decalage) {
        boolean contraintePrec = true;
        ArrayList<PointInteret> successionPointsInteret = uneTournee.getSuccessionPointsInteret();
        ArrayList<PointInteret> listePointsInteret = demandesLivraisons.getListePointsInteret();
        int positionInitiale = successionPointsInteret.indexOf(pointADeplacer);
        int nouvPosition = positionInitiale+decalage;
        PointInteret pointDependance = pointADeplacer.getPointDependance(); //Point d'enlévement ou de livraison associé
        PointInteret ancienPointSuivant; //Point suivant à l'ancienne position
        PointInteret ancienPointPrecedent; //Point précédent à l'ancienne position
        PointInteret nouvPointSuivant; //Point suivant à la nouvelle position
        PointInteret nouvPointPrecedent; //Point précédent à la nouvelle position
        int indAncienPointSuiv; //Indice de l'ancien point suivant dans la liste des points interet
        int indAncienPointPrec; //Indice de l'ancien point précédent dans la liste des points interet
        int indPointADeplacer = listePointsInteret.indexOf(pointADeplacer);
        int indNouvPointSuiv; //Indice du nouveau point suivant dans la liste des points interet
        int indNouvPointPrec; //Indice du nouveau point précédent dans la liste des points interet
        int positionPointDep = successionPointsInteret.indexOf(pointDependance);
        
        //Vérification de la contrainte de dépendance
        if (pointADeplacer.isEnlevement()) {
            if (positionPointDep < nouvPosition) {
                contraintePrec = false;
            }
        } else {
            if (positionPointDep > nouvPosition) {
                contraintePrec = false;
            }
        }
        //Récupération des points d'intérêt à modifier
        ancienPointPrecedent = successionPointsInteret.get(positionInitiale - 1);
        indAncienPointPrec = listePointsInteret.indexOf(ancienPointPrecedent);
        System.out.println("ancienPointPrec : "+ ancienPointPrecedent.getIntersection().getId());
        if (positionInitiale == successionPointsInteret.size()-1) {
            //Le point suivant est l'entrepôt
            ancienPointSuivant = successionPointsInteret.get(0);
        } else {
            ancienPointSuivant = successionPointsInteret.get(positionInitiale+1);
        }
        System.out.println("ancienPointSuivant : "+ ancienPointSuivant.getIntersection().getId());
        indAncienPointSuiv = listePointsInteret.indexOf(ancienPointSuivant);
        
        
        
        

        if (nouvPosition == successionPointsInteret.size()-1) {
            nouvPointPrecedent = successionPointsInteret.get(nouvPosition);
            //Le point suivant est l'entrepôt
            nouvPointSuivant = successionPointsInteret.get(0);
        } else {
            nouvPointPrecedent = successionPointsInteret.get(nouvPosition-1);
            nouvPointSuivant = successionPointsInteret.get(nouvPosition);
            
        }
        indNouvPointPrec = listePointsInteret.indexOf(nouvPointPrecedent);
        indNouvPointSuiv = listePointsInteret.indexOf(nouvPointSuivant);
        System.out.println("nouvPointPrec : "+ nouvPointPrecedent.getIntersection().getId());
        System.out.println("nouvPointSuivant : "+ nouvPointSuivant.getIntersection().getId());
        
        //Deplacement du point dans la tournee
        successionPointsInteret.remove(pointADeplacer);
        successionPointsInteret.add(nouvPosition, pointADeplacer);
        
        //Mise à jour des chemins
        nouvPointPrecedent.setCheminDepart(chemins[indNouvPointPrec][indPointADeplacer]);
        pointADeplacer.setCheminDepart(chemins[indPointADeplacer][indNouvPointSuiv]);
        ancienPointPrecedent.setCheminDepart(chemins[indAncienPointPrec][indAncienPointSuiv]);
        
        //Mise à jour des heures
        calculerHeuresTournee();
        
        return contraintePrec;
    }

    /**
     * Méthode permettant d'ajouter une nouvelle livraison (point enlevement +
     * point de livraison) à une tournee
     *
     * @param latitudeEnlvt latitude du point d'enlèvement
     * @param longitudeEnlvt longitude du point d'enlèvement
     * @param latitudeLivr latitude du point de livraison
     * @param longitudeLivr longitude du point de livraison
     * @param pointAvantEnlevement point d'intérêt après lequel on souhaite
     * placer le point d'enlèvement
     * @param pointAvantLivraison point d'intérêt après lequel on souhaite
     * placer le point de livraison
     * @param dureeEnlevement durée d'enlèvement
     * @param dureeLivraison durée de livraison
     * @return vrai si l'ajout a été effectué, faux sinon
     */
    public boolean ajouterLivraison(Double latitudeEnlvt, Double longitudeEnlvt,
            Double latitudeLivr, Double longitudeLivr, PointInteret pointAvantEnlevement,
            PointInteret pointAvantLivraison, int dureeEnlevement, int dureeLivraison) {

        ArrayList<PointInteret> successionPointsInteret = uneTournee.getSuccessionPointsInteret();
        ArrayList<PointInteret> listePointsInteret = demandesLivraisons.getListePointsInteret();
        Intersection intersectionEnlvt = null; //Intersection correspondant au premier point (enlévement)
        Intersection intersectionLivr = null; ////Intersection correspondant au second point (livraison)
        PointInteret pointEnlevement; //Le point d'enlévement à ajouter
        PointInteret pointLivraison; //Le point de livraison à ajouter
        //Indice du point précédant l'enlevement dans la tournee
        int indPointAvantEnlvt = successionPointsInteret.indexOf(pointAvantEnlevement);
        //Indice du point précédant la livraison dans la tournee
        int indPointAvantLivr = successionPointsInteret.indexOf(pointAvantLivraison);
        //Numéro de la demande de livraison
        int numeroDemande = (listePointsInteret.size() - 1) / 2 + 1;

        //Recherche des intersections correspondants aux coordonnées 
        for (Intersection i : listeIntersections) {
            if ((i.getLatitude()).toString().equals(latitudeEnlvt.toString())
                    && (i.getLongitude()).toString().equals(longitudeEnlvt.toString())) {
                intersectionEnlvt = i;
            }
            if ((i.getLatitude()).toString().equals(latitudeLivr.toString())
                    && (i.getLongitude()).toString().equals(longitudeLivr.toString())) {
                intersectionLivr = i;
            }
        }
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

        //Ajout aux listes de points d'intérêt
        listePointsInteret.add(pointEnlevement);
        listePointsInteret.add(pointLivraison);
        listePointsInteretActuelle.add(pointEnlevement);
        listePointsInteretActuelle.add(pointLivraison);

        //Vérification de la contrainte de précédence
        if (indPointAvantLivr < indPointAvantEnlvt) {
            return false;
        } //Cas où l'enlèvement doit être placé juste avant la livraison
        else if (indPointAvantLivr == indPointAvantEnlvt) {
            this.ajouterPointInteret(pointEnlevement, pointAvantEnlevement);
            this.ajouterPointInteret(pointLivraison, pointEnlevement);
        } else {
            ajouterPointInteret(pointEnlevement, pointAvantEnlevement);
            ajouterPointInteret(pointLivraison, pointAvantLivraison);
        }

        //Mise à jour des heures de départ et d'arrivée
        this.calculerHeuresTournee();

        return true;
    }

    /**
     * Méthode permettant d'ajouter un point d'intérêt à une tournée après un
     * point d'intérêt donné de la tournée
     *
     * @param pointInteret le point d'intérêt à ajouter
     * @param pointPrecedent le point d'intérêt après lequel il faut ajouter le
     * nouveau point
     * @return vrai si l'ajout a été effectué, faux sinon
     */
    public boolean ajouterPointInteret(PointInteret pointInteret, PointInteret pointPrecedent) {
        ArrayList<PointInteret> successionPointsInteret = uneTournee.getSuccessionPointsInteret();
        ArrayList<PointInteret> listePointsInteret = demandesLivraisons.getListePointsInteret();
        PointInteret pointSuivant; //Point suivant le point dans la tournée
        int indPrecListeP; //Indice du point precedent dans la liste des points d'intérêts
        int indSuivListeP; //Indice du point suivant dans la liste des points d'intérêts
        int indPointListeP; //Indice du point d'intérêt dans la liste des points d'intérêts
        int indPointPrecT; //Indice du point précédent dans la tournée
        Chemin cheminPointPrec; //Chemin allant du point précédent au point d'intérêt
        Chemin cheminPointCourant; //Chemin allant du point d'intérêt ajouté au point d'intérêt suivant

        //Récupération de l'indice du point précédent dans la tournée
        indPointPrecT = successionPointsInteret.indexOf(pointPrecedent);

        //Récupération du point suivant dans la tournée
        //Si l'ajout se fait en fin de tournee
        if (indPointPrecT == successionPointsInteret.size() - 1) {
            //Recuperation du point d'intérêt suivant (l'entrepôt)
            pointSuivant = successionPointsInteret.get(0);
            //Recuperation de l'indice du point dans la liste des points d'intérêts
            indSuivListeP = 0;
        } else {
            //Recuperation du point d'intérêt suivant 
            pointSuivant = successionPointsInteret.get(indPointPrecT + 1);
            //Recuperation de l'indice du point dans la liste des points d'intérêts
            indSuivListeP = listePointsInteret.indexOf(pointSuivant);
        }

        //Récupération des indices des autres points dans la liste des points d'intérêt
        indPrecListeP = listePointsInteret.indexOf(pointPrecedent);
        indPointListeP = listePointsInteret.indexOf(pointInteret);

        //Ajout du point d'intérêt après le point précédent
        successionPointsInteret.add(indPointPrecT + 1, pointInteret);
        
        //Mise à jour des chemins
        //Calcul du chemin allant du point précédent au point ajouté
        dijkstra(pointPrecedent.getIntersection());
        cheminPointPrec = plusCourtChemin(pointPrecedent.getIntersection(), pointInteret.getIntersection());
        System.out.println("chemin point prec " + plusCourtChemin(pointPrecedent.getIntersection(), pointInteret.getIntersection()));

        //Ajout à la matrice des chemins et celle des couts
        ajouterCoutEtChemin(cheminPointPrec, indPrecListeP, indPointListeP);

        //Calcul du chemin allant du point ajouté au point suivant
        dijkstra(pointInteret.getIntersection());
        cheminPointCourant = plusCourtChemin(pointInteret.getIntersection(), pointSuivant.getIntersection());
        System.out.println("chemin point courant " + cheminPointCourant);
        //Ajout à la matrice des chemins et celle des couts
        ajouterCoutEtChemin(cheminPointCourant, indPointListeP, indSuivListeP);

        pointPrecedent.setCheminDepart(cheminPointPrec);
        pointInteret.setCheminDepart(cheminPointCourant);

        return true;
    }
    
    /**
     * Méthode permettant d'agrandir la matrice des chemins et celle des couts
     * dans le cas de l'ajout de points d'intérêt
     * 
     * @param chemin
     * @param indOrigine
     * @param indDestination
     * @return 
     */
    private boolean ajouterCoutEtChemin(Chemin chemin, int indOrigine, int indDestination) {
        Chemin[][] nouvChemins;
        Double[][] nouvCouts;
        int nbSommets = demandesLivraisons.getListePointsInteret().size();
        //Si la matrice des chemins n'est pas encore pleine
        System.out.println("length chemins : "+chemins[0].length);
        if (chemins[0].length >= nbSommets) {
            System.out.println("indOrigine : "+ indOrigine);
            System.out.println("indDestination : "+ indDestination);
            chemins[indOrigine][indDestination] = chemin;
        } else {
            //On crée une nouvelle matrice
            //Initialisation de la matrice des plus courts chemins (avec des colonnes
            //et lignes supplémentaires)
            nouvChemins = new Chemin[nbSommets + 5][nbSommets + 5];
            for (int i = 0; i < nbSommets + 5; i++) {
                nouvChemins[i] = new Chemin[nbSommets + 5];
            }

            //Remplissage de la matrice 
            for (int i = 0; i < nbSommets - 1; i++) {
                for (int j = 0; j < nbSommets - 1; j++) {
                    nouvChemins[i][j] = chemins[i][j];
                }
            }
            nouvChemins[indOrigine][indDestination] = chemin;
            this.setChemins(nouvChemins);
        }
        //Même chose pour la matrice des couts
        System.out.println("length chemins : "+chemins[0].length);
        if (cout[0].length >= nbSommets) {
            System.out.println("indOrigine : "+ indOrigine);
            System.out.println("indDestination : "+ indDestination);
            cout[indOrigine][indDestination] = chemin.getLongueur();
        } else {
            //On crée une nouvelle matrice
            //Initialisation de la matrice des plus courts chemins (avec des colonnes
            //et lignes supplémentaires)
            nouvCouts = new Double[nbSommets + 5][nbSommets + 5];
            for (int i = 0; i < nbSommets + 5; i++) {
                nouvCouts[i] = new Double[nbSommets + 5];
            }

            //Remplissage de la matrice 
            for (int i = 0; i < nbSommets - 1; i++) {
                for (int j = 0; j < nbSommets - 1; j++) {
                    nouvCouts[i][j] = cout[i][j];
                }
            }
            nouvCouts[indOrigine][indDestination] = chemin.getLongueur();
            this.setCout(nouvCouts);
        }
        return true;
    }

    /**
     * Méthode permettant de calculer les heures d'arrivées et de départ des
     * points d'intérêt d'une tournée
     *
     * @param tournee
     * @return la tournée mise à jour
     */
    public boolean calculerHeuresTournee() {
        //Recuperation de l'entrepot
        PointInteret pointCourant = uneTournee.getSuccessionPointsInteret().get(0);
        PointInteret pointPrec = pointCourant;

        //Recuperation de l'heure de départ de l'entrepôt
        Integer heureDepartPrec = heureToInt(demandesLivraisons.getHeureDepart());
        pointCourant.setHeureDepart(intToHeure(heureDepartPrec));

        Integer heureArriveeCour;
        Integer heureDepartCour;

        int dureeTrajet;
        int nbSommets = uneTournee.getSuccessionPointsInteret().size();
        for (int i = 1; i < nbSommets; i++) {
            pointCourant = uneTournee.getSuccessionPointsInteret().get(i);
            if (!pointCourant.isEntrepot()) {
                //Mise a jour de l'heure d'arrivee
                dureeTrajet = pointPrec.getCheminDepart().getDureeTrajet();
                heureArriveeCour = heureDepartPrec + dureeTrajet;
                pointCourant.setHeureArrivee(intToHeure(heureArriveeCour));

                //Mise a jour de l'heure de depart
                heureDepartCour = heureArriveeCour + pointCourant.getDuree();
                pointCourant.setHeureDepart(intToHeure(heureDepartCour));

                heureDepartPrec = heureDepartCour;
            }

            //Ajout a la tournee
            pointPrec = pointCourant;

        }

        //Calcul de l'heure d'arrivee à  l'entrepôt en fin de tournée
        dureeTrajet = pointPrec.getCheminDepart().getDureeTrajet();
        heureArriveeCour = heureDepartPrec + dureeTrajet;
        uneTournee.getSuccessionPointsInteret().get(0).setHeureArrivee(intToHeure(heureArriveeCour));

        //Calcul de la durée de la tournée
        Integer heureDep = heureToInt(uneTournee.getSuccessionPointsInteret().get(0).getHeureDepart());
        Integer heureArr = heureToInt(uneTournee.getSuccessionPointsInteret().get(0).getHeureArrivee());
        Integer dureeTournee = heureArr - heureDep;

        uneTournee.setDuree(intToHeure(dureeTournee));
        return true;

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
        DemandesLivraisons sauvegardeDL = this.demandesLivraisons;
        this.setDemandesLivraisons(null);
        this.listePointsInteretActuelle.clear();
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
            this.listePointsInteretActuelle.add(pI);
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
                            pe.setEnlevement(true);
                        }
                        if ((j.getId().equals(adresseLivraison))) {
                            pl = new PointInteret(j, Integer.parseInt(dureeLivraison));
                            pl.setEnlevement(false);
                        }
                    }
                    if (pe != null && pl != null) {
                        pe.setPointDependance(pl);
                        pl.setPointDependance(pe);

                        pe.setNumeroDemande(i + 1);
                        pl.setNumeroDemande(i + 1);

                        this.demandesLivraisons.ajouterPointInteret(pe);
                        this.listePointsInteretActuelle.add(pe);

                        this.demandesLivraisons.ajouterPointInteret(pl);
                        this.listePointsInteretActuelle.add(pl);

                    } else {
                        ok = false; // s'il y a un point non-trouvé dans la liste
                    }

                }
                
            } else {
                ok = false;
                this.setDemandesLivraisons(sauvegardeDL);
                this.listePointsInteretActuelle = sauvegardeDL.getPis();
            }

        } else {
            ok = false;
            this.setDemandesLivraisons(sauvegardeDL);
            this.listePointsInteretActuelle = sauvegardeDL.getPis();
        }

        return ok;
    }

    // lancer l'ouvreur de fichier et choisir la bonne methode pour charger les donnees
    public boolean chargerCarte(boolean estUnChangement, String fichier) throws Exception, ParserConfigurationException, SAXException, IOException {
        boolean result = false;
        //File xml = choisirFichierXML(true);;
        File xml = null;
        if ("".equals(fichier)) {
            xml = choisirFichierXML(true);
        } else if (fichier.contains(".xml")) {
            xml = new File(fichier).getAbsoluteFile();
            if (!xml.exists()) {
                return false;
            }
        } else {
            return false;
        }
        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = docBuilder.parse(xml);
        Element racine = document.getDocumentElement();

        if (racine.getNodeName().equals("reseau")) {
            if (estUnChangement) {
                this.listeIntersections.clear();
                if (this.demandesLivraisons != null && !this.demandesLivraisons.getListePointsInteret().isEmpty()) {
                    //this.demandesLivraisons.supprimerLivraison();
                    this.demandesLivraisons = null;
                }
            }
            if (construireCarteAPartirDeDOMXML(racine)) {
                result = true;
            }
        }

        return result;
    }

    public boolean chargerLivraison(String fichier) throws Exception, ParserConfigurationException, SAXException, IOException {
        boolean result = false;
        //File xml = choisirFichierXML(true);
        File xml = null;
        if ("".equals(fichier)) {
            xml = choisirFichierXML(true);
        } else if (fichier.contains(".xml")) {
            xml = new File(fichier).getAbsoluteFile();;
            if (!xml.exists()) {
                return false;
            }
        } else {
            return false;
        }

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

//    // Les methodes d'affichage ne servent qu'à vérifier les résultats de la lecture
//    public void AfficherIntersections() {
//        for (Intersection i : listeIntersections) {
//            System.out.println("id:" + i.getId() + " latitude:" + i.getLatitude() + " longitude:" + i.getLongitude());
//            System.out.println("listeTronconDepart:" + i.getTronconsDepart());
//            System.out.println();
//        }
//    }
//
//    public void AfficherLivraisons() {
//        DemandesLivraisons dl = this.demandesLivraisons;
//        System.out.println("adresseDepart:" + dl.getAdresseDepart() + " heureDepart:" + dl.getHeureDepart());
//        for (PointInteret pI : dl.getListePointsInteret()) {
//            System.out.println("adresse:" + pI.getIntersection().getId() + " duree:" + pI.getDuree() + ((pI.isEnlevement()) ? " estEnlevement" : " estLivraison"));
//        }
//    }
}
