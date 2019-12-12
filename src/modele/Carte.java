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
import javax.swing.JOptionPane;

/*
 * Carte
 *
 * Version 1
 * Contient des extraits du code de l application PlaCo
 * 
 * Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU,
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
public class Carte {

    private ArrayList<Intersection> listeIntersections;
    private DemandesLivraisons demandesLivraisons;
    private TSP3 unTSP;
    private Tournee uneTournee;
    public static final Double INFINI = 1000000.0; //Valeur max 
    public static final Double NON_DEFINI = -1000.0;

    //Pour le graphe de plus courts chemins
    Double[][] cout;
    Chemin[][] chemins;
    TreeMap<Integer, Integer> mapPredecesseur;

    //Pour l affichage des points d interet a tout moment sur la carte
    ArrayList<PointInteret> listePointsInteretActuelle;

    public Carte() {
        this.listeIntersections = new ArrayList<Intersection>();
        this.listePointsInteretActuelle = new ArrayList<PointInteret>();
        this.unTSP = new TSP3();
        this.uneTournee = new Tournee();
    }

    public ArrayList<Intersection> getListeIntersections() {
        return listeIntersections;
    }

    /**
     * Methode permettant d ajouter une intersection a la carte
     * @param i l intersection a ajouter
     */
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
     * Relachement d un arc (troncon) reliant deux intersections 
     * Utilisee pour le calcul des plus courts chemins (Dijkstra)
     *
     * @param depart origine du troncon
     * @param arrivee destination du troncon
     */
    public void relacherArc(Intersection depart, Intersection arrivee) {
        Double coutArc = INFINI;
        ArrayList<Troncon> listeTronconsDepart = depart.getTronconsDepart();
        Troncon arc = new Troncon();
        //Recherche du coût de l arc
        for (int i = 0; i < listeTronconsDepart.size(); i++) {
            if (listeTronconsDepart.get(i).getDestination() == arrivee) {
                arc = listeTronconsDepart.get(i);
                //Recuperation du cout de l arc (en secondes)
                coutArc = (arc.getLongueur() * 15) / 3.6;
            }
        }
        if (arrivee.getDistance() > depart.getDistance() + coutArc) {
            //Relachement de l arc
            arrivee.setDistance(depart.getDistance() + coutArc);
            arrivee.setPredecesseur(depart);
        }
    }

    /**
     * Implementation de l algorithme de Dijkstra permettant de determiner les
     * plus courts chemins a partir d une source dans la carte donnee
     *
     * @param depart intersection de depart pour laquelle nous souhaitons
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
        //Initialisation du sommet de depart
        depart.setDistance(0.0);
        depart.setPredecesseur(null);
        sommetsGris.add(depart);

        while (sommetsGris.size() != 0) {
            //Recherche du sommet Si ayant la distance minimale par rapport au
            //sommet de depart
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
                    //Relacher l arc (Si, Sj)
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
     * Methode permettant de trouver le plus court chemin partant d une
     * intersection et arrivant a une autre. Elle est appelee apres une
     * execution de Dijkstra pour trouver la succession des points reliant ces
     * deux intersections.
     *
     * @param depart intersection de depart
     * @param arrivee intersection d arrivee
     * 
     * @return le chemin calclule
     */
    public Chemin plusCourtChemin(Intersection depart, Intersection arrivee) {
        Chemin chemin = null;
        Intersection intersectionCourante = arrivee;
        Intersection intersectionPrec;
        ArrayList<Troncon> cheminInverse = new ArrayList<Troncon>(); //on parcourt
        //le chemin dans le sens inverse en utilisant les predecesseurs

        if (depart == arrivee) {
            chemin = new Chemin(depart, arrivee, cheminInverse);
        }
        while (intersectionCourante.getPredecesseur() != null) {
            //Tant qu on est pas au sommet de depart 
            intersectionPrec = intersectionCourante;
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

            //S il n y a pas de chemin, retourner null
            if (cheminInverse.size() == 0) {
                return null;
            }

            //Inversion du chemin inverse pour avoir le chemin a l endroit
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
     * Methode permettant de creer le graphe des plus courts chemins a partir
     * des points d interet de la carte (donc des demandes de livraisons
     * actuellement chargees)
     */
    public void creerGraphePCC() {
        //recuperation du nombre de sommets (en tenant compte de l entrepot)
        int nbSommets = this.demandesLivraisons.getListePointsInteret().size();
        //Initialisation de la matrice des couts
        cout = new Double[nbSommets + 5][nbSommets + 5];
        for (int i = 0; i < nbSommets + 5; i++) {
            cout[i] = new Double[nbSommets + 5];
        }

        mapPredecesseur = new TreeMap<>();
        //Initialisation de la matrice des plus courts chemins (avec des colonnes
        //et lignes supplementaires)
        chemins = new Chemin[nbSommets + 5][nbSommets + 5];
        for (int i = 0; i < nbSommets + 5; i++) {
            chemins[i] = new Chemin[nbSommets + 5];
        }
        //Remplissage de la matrice
        //plus courts chemins de l entrepot vers tous les autres points d interaªt
        ArrayList<PointInteret> listePointsInteret = this.demandesLivraisons.getListePointsInteret();
        Intersection intersectionCourante;
        Intersection intersectionArrivee;
        Chemin plusCourtChemin = new Chemin();

        //plus court chemin de chaque point d interaªt vers tous les autres 
        //(y compris l entrepot)
        for (int i = 0; i < nbSommets; i++) {
            intersectionCourante = listePointsInteret.get(i).getIntersection();
            dijkstra(intersectionCourante);

            //plus courts chemins vers les autres points d interet
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
                        String idJ = listePointsInteret.get(i).getPointDependance().getIntersection().getId();
                        for (int k = 0; k < nbSommets; k++) {
                            String IdK = listePointsInteret.get(k).getIntersection().getId();
                            if (idJ == IdK) {
                                mapPredecesseur.put(i, k);
                            }
                        }

                        cout[i][j] = 0.0;
                    } else {
                        cout[i][j] = 0.0;
                    }
                    chemins[i][j] = null;
                }

            }
        }

    }

    /**
     * Methode permettant de calculer une tournee pour repondre aux demandes de
     * livraison actuellement chargees
     *
     * @return la tournee creee
     */
    public Tournee calculerTournee() {
        ArrayList<PointInteret> listePointsInteret = demandesLivraisons.getListePointsInteret();
        //ArrayList<PointInteret> listePointsInteret = this.listePointsInteretActuelle;
        int nbSommets = listePointsInteret.size();
        double nbPheromone = 15;

        Double[][] matricePheromone = new Double[nbSommets + 5][nbSommets + 5];
        //Creation de la tournee
        for (int i = 0; i < nbSommets; i++) {
            for (int j = 0; j < nbSommets; j++) {
                matricePheromone[i][j] = nbPheromone;
            }
        }
        Tournee tournee = new Tournee();
        Integer indPointCourant = 0;
        Integer indPointPrec;
        Chemin chemin;
        unTSP = new TSP3();

        creerGraphePCC();

        //Cas ou il n y a aucune demande de livraisons
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
            unTSP.chercheSolution3(1000000, nbSommets, cout, duree, this.mapPredecesseur, matricePheromone);

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
            //Ajout du dernier point d interet qui retourne vers l entrepot
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
     * Methode permettant de supprimer un point d interet d une tournee
     *
     * @param pointInteret le point d interet a supprimer
     * @return vrai si la suppression a ete effectuee, faux sinon
     */
    public boolean supprimerPointInteret(PointInteret pointInteret) {
        ArrayList<PointInteret> successionPointsInteret = uneTournee.getSuccessionPointsInteret();
        ArrayList<PointInteret> listePointsInteret = demandesLivraisons.getListePointsInteret();
        PointInteret pointPrec; //Point precedant le point dans la tournee
        PointInteret pointSuivant; //Point suivant le point dans la tournee
        int indPrecListeP; //Indice du point precedent dans la liste des points d interets
        int indSuivListeP; //Indice du point suivant dans la liste des points d interets
        int indPointTournee; //Indice du point d interet dans la tournee
        PointInteret pointDependance = pointInteret.getPointDependance(); //point
        //d enlevement ou de livraison associe
        Chemin cheminPointPrec; //Chemin allant du point precedent au point suivant

        if (!successionPointsInteret.contains(pointInteret)) {
            return false;
        }

        //Recuperation de la position du point dans la tournee
        indPointTournee = successionPointsInteret.indexOf(pointInteret);
        //Recuperation du point d interet precedent 
        pointPrec = successionPointsInteret.get(indPointTournee - 1);
        //Recuperation de l indice du point prec dans la liste des points d interet
        indPrecListeP = listePointsInteret.indexOf(pointPrec);
        //Si le point d interet est le dernier de la tournee (avant le retour a 
        //l entrepot
        if (indPointTournee == successionPointsInteret.size() - 1) {
            //Recuperation du point d interet suivant (l entrepot)
            pointSuivant = successionPointsInteret.get(0);
            //Recuperation de l indice du point dans la liste des points d interets
            indSuivListeP = 0;
        } else {
            //Recuperation du point d interet suivant 
            pointSuivant = successionPointsInteret.get(indPointTournee + 1);
            //Recuperation de l indice du point dans la liste des points d interets
            indSuivListeP = listePointsInteret.indexOf(pointSuivant);
        }

        //Prise en compte du cas ou il ne reste que l entrep ot
        if (!(pointPrec == pointSuivant)) {
            cheminPointPrec = chemins[indPrecListeP][indSuivListeP];
            pointPrec.setCheminDepart(cheminPointPrec);
        }

        //Suppression du point de la tournee et de la liste des points d interets
        successionPointsInteret.remove(pointInteret);
        listePointsInteretActuelle.remove(pointInteret);

        //Suppression du point de dependance correspondant (si necessaire)
        if (successionPointsInteret.contains(pointDependance)) {
            supprimerPointInteret(pointDependance);
        }

        //S il reste encore des elements dans la tournee     
        if (successionPointsInteret.size() > 1) {
            //Mise a jour des heures de depart et d arrivee
            calculerHeuresTournee();
        }
        return true;
    }

    /**
     * Methode permettant de deplacer un point d interet dans la tournee a une
     * nouvelle position
     *
     * @param pointADeplacer le point a deplacer
     * @param decalage le nombre de pas dont il se decale dans la tournee 
     * (positif si le point avance, negatif sinon)
     * @return vrai si la contrainte de precedence est encore respectee, faux
     * sinon
     */
    public boolean deplacerPointInteret(PointInteret pointADeplacer, int decalage) {
        boolean contraintePrec = true;
        ArrayList<PointInteret> successionPointsInteret = uneTournee.getSuccessionPointsInteret();
        ArrayList<PointInteret> listePointsInteret = demandesLivraisons.getListePointsInteret();
        int positionInitiale = successionPointsInteret.indexOf(pointADeplacer);
        int nouvPosition = positionInitiale + decalage;
        PointInteret pointDependance = pointADeplacer.getPointDependance(); //Point d enlevement ou de livraison associe
        PointInteret ancienPointSuivant; //Point suivant a l ancienne position
        PointInteret ancienPointPrecedent; //Point precedent a l ancienne position
        PointInteret nouvPointSuivant; //Point suivant a la nouvelle position
        PointInteret nouvPointPrecedent; //Point precedent a la nouvelle position
        int indAncienPointSuiv; //Indice de l ancien point suivant dans la liste des points interet
        int indAncienPointPrec; //Indice de l ancien point precedent dans la liste des points interet
        int indPointADeplacer = listePointsInteret.indexOf(pointADeplacer);
        int indNouvPointSuiv; //Indice du nouveau point suivant dans la liste des points interet
        int indNouvPointPrec; //Indice du nouveau point precedent dans la liste des points interet
        //Position du point de dependance du point a deplacer
        int positionPointDep = successionPointsInteret.indexOf(pointDependance); 

        //Cas ou on a pas besoin de deplacer
        if (decalage == 0) {
            return true;
        }
        //Verification de la contrainte de dependance
        if (pointADeplacer.isEnlevement()) {
            if (positionPointDep <= nouvPosition) {
                contraintePrec = false;
                pointADeplacer.setEnlevement(false);
                pointDependance.setEnlevement(true);
            }
        } else {
            if (positionPointDep >= nouvPosition) {
                contraintePrec = false;
                pointADeplacer.setEnlevement(true);
                pointDependance.setEnlevement(false);
            }
        }

        //Recuperation des points d interet a modifier
        ancienPointPrecedent = successionPointsInteret.get(positionInitiale - 1);
        indAncienPointPrec = listePointsInteret.indexOf(ancienPointPrecedent);
        //Cas d un deplacement depuis la fin de la tournee
        if (positionInitiale == successionPointsInteret.size() - 1) {
            //Le point suivant est l entrepot
            ancienPointSuivant = successionPointsInteret.get(0);
        } else {
            ancienPointSuivant = successionPointsInteret.get(positionInitiale + 1);
        }
        indAncienPointSuiv = listePointsInteret.indexOf(ancienPointSuivant);
        
        //Cas d un deplacement en fin de tournee
        if (nouvPosition == successionPointsInteret.size() - 1) {
            nouvPointPrecedent = successionPointsInteret.get(nouvPosition);
            //Le point suivant est l entrepot
            nouvPointSuivant = successionPointsInteret.get(0);
        } else {
            //Si on avance dans la tournee
            if (decalage > 0) {
                nouvPointPrecedent = successionPointsInteret.get(nouvPosition);
                nouvPointSuivant = successionPointsInteret.get(nouvPosition + 1);
            } else { //Si on recule
                nouvPointPrecedent = successionPointsInteret.get(nouvPosition - 1);
                nouvPointSuivant = successionPointsInteret.get(nouvPosition);
            }

        }
        indNouvPointPrec = listePointsInteret.indexOf(nouvPointPrecedent);
        indNouvPointSuiv = listePointsInteret.indexOf(nouvPointSuivant);

        //Deplacement du point dans la tournee
        successionPointsInteret.remove(pointADeplacer);
        successionPointsInteret.add(nouvPosition, pointADeplacer);

        //Mise a jour des chemins
        nouvPointPrecedent.setCheminDepart(chemins[indNouvPointPrec][indPointADeplacer]);
        pointADeplacer.setCheminDepart(chemins[indPointADeplacer][indNouvPointSuiv]);
        ancienPointPrecedent.setCheminDepart(chemins[indAncienPointPrec][indAncienPointSuiv]);

        //Mise a jour des heures
        calculerHeuresTournee();
        return contraintePrec;

    }

    /**
     * Methode permettant d ajouter une nouvelle livraison (point enlevement +
     * point de livraison) a une tournee
     *
     * @param pointEnlevement point d enlevement a ajouter
     * @param pointLivraison point de livraison a ajouter
     * @param pointAvantEnlevement point d interet apres lequel on souhaite
     * placer le point d enlevement
     * @param pointAvantLivraison point d interet apres lequel on souhaite
     * placer le point de livraison
     * @return vrai si l ajout a ete effectue, faux sinon
     */
    public boolean ajouterLivraison(PointInteret pointEnlevement, PointInteret pointLivraison,
            PointInteret pointAvantEnlevement, PointInteret pointAvantLivraison) {

        ArrayList<PointInteret> successionPointsInteret = uneTournee.getSuccessionPointsInteret();
        ArrayList<PointInteret> listePointsInteret = demandesLivraisons.getListePointsInteret();
        //Indice du point precedant l enlevement dans la tournee
        int indPointAvantEnlvt = successionPointsInteret.indexOf(pointAvantEnlevement);
        //Indice du point precedant la livraison dans la tournee
        int indPointAvantLivr = successionPointsInteret.indexOf(pointAvantLivraison);
        

        pointEnlevement.setEnlevement(true);
        pointLivraison.setEnlevement(false);
        pointEnlevement.setPointDependance(pointLivraison);
        pointLivraison.setPointDependance(pointEnlevement);

        //Verification de la contrainte de precedence
        if (indPointAvantLivr < indPointAvantEnlvt) {
            return false;
        } 
        else {
            //Ajout aux listes de points d interet
            listePointsInteret.add(pointEnlevement);
            listePointsInteret.add(pointLivraison);
            listePointsInteretActuelle.add(pointEnlevement);
            listePointsInteretActuelle.add(pointLivraison);
            creerGraphePCC();
            //Cas ou l enlevement doit etre place juste avant la livraison
            if (indPointAvantLivr == indPointAvantEnlvt) {

                this.ajouterPointInteret(pointEnlevement, pointAvantEnlevement);
                this.ajouterPointInteret(pointLivraison, pointEnlevement);

            } else {
                ajouterPointInteret(pointEnlevement, pointAvantEnlevement);
                ajouterPointInteret(pointLivraison, pointAvantLivraison);
            }
        }

        //Mise a jour des heures de depart et d arrivee
        this.calculerHeuresTournee();
        return true;
    }

    /**
     * Methode permettant d ajouter un point d interet a une tournee apres un
     * point d interet donne de la tournee
     *
     * @param pointInteret le point d interet a ajouter
     * @param pointPrecedent le point d interet apres lequel il faut ajouter le
     * nouveau point
     * @return vrai si l ajout a ete effectue, faux sinon
     */
    public boolean ajouterPointInteret(PointInteret pointInteret, PointInteret pointPrecedent) {
        ArrayList<PointInteret> successionPointsInteret = uneTournee.getSuccessionPointsInteret();
        ArrayList<PointInteret> listePointsInteret = demandesLivraisons.getListePointsInteret();
        PointInteret pointSuivant; //Point suivant le point dans la tournee
        int indPrecListeP; //Indice du point precedent dans la liste des points d interets
        int indSuivListeP; //Indice du point suivant dans la liste des points d interets
        int indPointListeP; //Indice du point d interet dans la liste des points d interets
        int indPointPrecT; //Indice du point precedent dans la tournee
        Chemin cheminPointPrec; //Chemin allant du point precedent au point d interet
        Chemin cheminPointCourant; //Chemin allant du point d interet ajoute au point d interet suivant

        //Recuperation de l indice du point precedent dans la tournee
        indPointPrecT = successionPointsInteret.indexOf(pointPrecedent);

        //Recuperation du point suivant dans la tournee
        //Si l ajout se fait en fin de tournee
        if (indPointPrecT == successionPointsInteret.size() - 1) {
            //Recuperation du point d interet suivant (l entrep ot)
            pointSuivant = successionPointsInteret.get(0);
            //Recuperation de l indice du point dans la liste des points d interets
            indSuivListeP = 0;
        } else {
            //Recuperation du point d interet suivant 
            pointSuivant = successionPointsInteret.get(indPointPrecT + 1);
            //Recuperation de l indice du point dans la liste des points d interets
            indSuivListeP = listePointsInteret.indexOf(pointSuivant);
        }

        //Recuperation des indices des autres points dans la liste des points d interet
        indPrecListeP = listePointsInteret.indexOf(pointPrecedent);
        indPointListeP = listePointsInteret.indexOf(pointInteret);

        //Ajout du point d interet apres le point precedent
        successionPointsInteret.add(indPointPrecT + 1, pointInteret);
        cheminPointPrec = chemins[indPrecListeP][indPointListeP];
        cheminPointCourant = chemins[indPointListeP][indSuivListeP];
        pointPrecedent.setCheminDepart(cheminPointPrec);
        pointInteret.setCheminDepart(cheminPointCourant);

        return true;
    }

    
    /**
     * Methode permettant de calculer les heures d arrivee et de depart des
     * points d interet d une tournee
     *
     * @param tournee la tournee pour lesquelles nous souhaitons calculer les heures
     * @return la tournee mise a jour
     */
    public boolean calculerHeuresTournee() {
        int dureeTrajet; //Duree totale de la tournee
        int nbSommets = uneTournee.getSuccessionPointsInteret().size();
        //Recuperation de l entrepot
        PointInteret pointCourant = uneTournee.getSuccessionPointsInteret().get(0);
        PointInteret pointPrec = pointCourant;

        //Recuperation de l heure de depart de l entrepot
        Integer heureDepartPrec = heureToInt(demandesLivraisons.getHeureDepart());
        pointCourant.setHeureDepart(intToHeure(heureDepartPrec));

        Integer heureArriveeCour;
        Integer heureDepartCour;

        for (int i = 1; i < nbSommets; i++) {
            pointCourant = uneTournee.getSuccessionPointsInteret().get(i);
            if (!pointCourant.isEntrepot()) {
                //Mise a jour de l heure d arrivee
                dureeTrajet = pointPrec.getCheminDepart().getDureeTrajet();
                heureArriveeCour = heureDepartPrec + dureeTrajet;
                pointCourant.setHeureArrivee(intToHeure(heureArriveeCour));

                //Mise a jour de l heure de depart
                heureDepartCour = heureArriveeCour + pointCourant.getDuree();
                pointCourant.setHeureDepart(intToHeure(heureDepartCour));
                heureDepartPrec = heureDepartCour;
            }

            //Ajout a la tournee
            pointPrec = pointCourant;

        }

        //Calcul de l heure d arrivee a  l entrep ot en fin de tournee
        dureeTrajet = pointPrec.getCheminDepart().getDureeTrajet();
        heureArriveeCour = heureDepartPrec + dureeTrajet;
        uneTournee.getSuccessionPointsInteret().get(0).setHeureArrivee(intToHeure(heureArriveeCour));

        //Calcul de la duree de la tournee
        Integer heureDep = heureToInt(uneTournee.getSuccessionPointsInteret().get(0).getHeureDepart());
        Integer heureArr = heureToInt(uneTournee.getSuccessionPointsInteret().get(0).getHeureArrivee());
        Integer dureeTournee = heureArr - heureDep;

        uneTournee.setDuree(intToHeure(dureeTournee));
        return true;

    }

    /**
     * Methode permettant de convertir une heure donnee (en string hh:mm:ss) en
     * int (nombre de secondes) 
     * Utilisee pour les calculs des heures de depart et d arrivee 
     * lors du calcul de la tournee
     *
     * @param heureStr l heure en string
     *
     * @return l heure en int
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

    /**
     * Methode permettant de convertir une heure donnee (en nombre de secondes,
     * donc int) en string (hh:mm:ss)
     * Utilisee pour les calculs des heures de depart et d arrivee lors du calcul
     * de la tournee
     *
     * @param heureStr l heure en int
     *
     * @return l heure en string
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
            int i = filename.lastIndexOf( '.' );
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
            throw new Exception("Probleme a l ouverture du fichier");
        }
        return new File(jFileChooserXML.getSelectedFile().getAbsolutePath());
    }

    /**
     * Chargement des donnees de l element racine d un document xml contenant
     * le plan de la ville Complete l attribut listeIntersections et leurs
     * troncons avec les informations lues depuis le document
     *
     * @param noeudDOMRacine Noeud racine du fichier xml a lire
     * @return true si la lecture s est correctement passee false s il manque un
     * element dans le fichier xml
     * @throws NumberFormatException
     */
    public boolean construireCarteAPartirDeDOMXML(Element noeudDOMRacine) throws NumberFormatException {
        this.setListeIntersections(new ArrayList<Intersection>());
        boolean ok = true;

        //Lecture des intersections
        NodeList listeNoeuds = noeudDOMRacine.getElementsByTagName("noeud");

        //Si aucune intersection n a pu etre lue, la methode retourne false
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

            //Si aucun troncon n a pu etre lu, la methode retourne false
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
                        ok = false; // s il y a un point non-trouva© dans la liste
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
     * Chargement des donnees de l element racine d un document xml contenant
     * les demandes de livraisons Complete l attribut demandesLivraisons et la
     * liste des points d interet qui la composent
     *
     * @param noeudDOMRacine Noeud racine du fichier xml a lire
     *
     * @return true si la lecture s est correctement passee false s il manque un
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
            throw new Exception("Infos cartes non chargees");
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
                ok = false;     // si on toruve pas d intersection correspondant dans la liste
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
                        ok = false; // s il y a un point non-trouve dans la liste
                    }

                }

            } else {
                ok = false;
                if (sauvegardeDL != null && demandesLivraisons != null && !listeIntersections.isEmpty()) {
                    this.setDemandesLivraisons(sauvegardeDL);
                    this.listePointsInteretActuelle = sauvegardeDL.getPis();
                }
            }

        } else {
            ok = false;
            if (sauvegardeDL != null && demandesLivraisons != null && !listeIntersections.isEmpty()) {
                this.setDemandesLivraisons(sauvegardeDL);
                this.listePointsInteretActuelle = sauvegardeDL.getPis();
            }
        }

        return ok;
    }

    // lancer l ouvreur de fichier et choisir la bonne methode pour charger les donnees
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

}
