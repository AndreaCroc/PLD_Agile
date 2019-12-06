/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import Vue.Fenetre;
import Vue.JCarte;
import javax.swing.JOptionPane;
import modele.Carte;
import modele.Intersection;
import modele.PointInteret;
import modele.Tournee;

/**
 *
 * @author labou
 */
public class EtatAjouter implements Etat {

    @Override
    public void ajouter(Controleur controleur, Fenetre fenetre, Carte carte, Tournee tournee) {
        Intersection iE = null;
        Integer dureeE = null;
        Double laE = null;
        Double loE = null;

        Intersection iL = null;
        Integer dureeL = null;
        Double laL = null;
        Double loL = null;

        while (true) {
            while (iE == null) {
                try {
                    laE = Double.valueOf(JOptionPane.showInputDialog("Merci de saisir la latitude du point d'enlevement"));
                    loE = Double.valueOf(JOptionPane.showInputDialog("Merci de saisir la longitude du point d'enlevement"));
                    // chercher l'intersection dans la liste
                    for (Intersection i : carte.getListeIntersections()) {
                        if (i.getLatitude().compareTo(laE) == 0 && i.getLongitude().compareTo(loE) == 0) {
                            iE = i;
                            break;
                        }
                    }
                    // si on n'a pas trouve l'intersection
                    if (iE == null) {
                        JOptionPane.showMessageDialog(fenetre, "L'intersection n'existe pas, merci de saisir des coordonnees valides");
                        continue;
                    } else {
                        boolean existeDansListePI = false;
                        for (PointInteret pI : carte.getDemandesLivraisons().getListePointsInteret()) {
                            if (pI.getIntersection().getLatitude().compareTo(laE) == 0 && pI.getIntersection().getLongitude().compareTo(loE) == 0) {
                                JOptionPane.showMessageDialog(fenetre, "Le point d'interet existe deja, merci de saisir d'autres coordonnees");
                                existeDansListePI = true;
                                break;
                            }
                        }
                        if (existeDansListePI) {
                            iE = null;
                            continue;
                        }
                    }

                    dureeE = Integer.parseInt(JOptionPane.showInputDialog("Merci de saisir la duree du point d'enlevement"));

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(fenetre, "Erreur du format, merci de re-saisir les coordonnees");
                    // si on détecte une erreur de format, on passe a la prochaine iteration
                    iE = null;
                    continue;

                }
            }

            while (iL == null) {
                try {
                    laL = Double.parseDouble(JOptionPane.showInputDialog("Merci de saisir la latitude du point de livraison"));
                    loL = Double.parseDouble(JOptionPane.showInputDialog("Merci de saisir la longitude du point de livraison"));

                    if (laL.compareTo(laE) == 0 && loL.compareTo(loE) == 0) {
                        JOptionPane.showMessageDialog(fenetre, "Le point de livraison doit etre different du point d'enlevement, merci de saisir d'autres coordonnees");
                        continue;
                    }

                    for (Intersection i : carte.getListeIntersections()) {
                        if (i.getLatitude().compareTo(laL) == 0 && i.getLongitude().compareTo(loL) == 0) {
                            iL = i;
                            break;
                        }
                    }

                    if (iL == null) {
                        JOptionPane.showMessageDialog(fenetre, "L'intersection n'existe pas, merci de saisir des coordonnees valides");
                        continue;
                    } else {
                        boolean existeDansListePI = false;
                        for (PointInteret pI : carte.getDemandesLivraisons().getListePointsInteret()) {
                            if (pI.getIntersection().getLatitude().compareTo(laL) == 0 && pI.getIntersection().getLongitude().compareTo(loL) == 0) {
                                JOptionPane.showMessageDialog(fenetre, "Le point d'interet existe deja, merci de saisir d'autres coordonnees");
                                existeDansListePI = true;
                                break;
                            }
                        }
                        if (existeDansListePI) {
                            continue;
                        }
                    }

                    dureeL = Integer.parseInt(JOptionPane.showInputDialog("Merci de saisir la duree du point de livraison"));

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(fenetre, "Erreur du format, merci de re-saisir les coordonnees");
                    // si on détecte une erreur de format, on passe a la prochaine itération
                    iE = null;
                    continue;
                }
            }

            // confirmation des informations
            int value = JOptionPane.showConfirmDialog(fenetre, "Merci de confirmer les informations:\nPointEnlevement Latitude: " + laE + " Longitude: " + loE + " duree:" + dureeE
                    + "\nPointLivraison Latitude: " + laL + " Longitude: " + loL + " duree: " + dureeL);
            // si les infos ne sont pas confirmées, on re-saisit les coorsdonnées
            if (value == JOptionPane.NO_OPTION) {
                iE = null;
                iL = null;
                continue;
                // si oui
            } else if (value == JOptionPane.YES_OPTION || value == JOptionPane.CANCEL_OPTION) {
                break;
            }
        }
        if (iE != null || iL != null) {
            PointInteret pE = new PointInteret(iE, dureeE);
            PointInteret pL = new PointInteret(iL, dureeL);
            pE.setEnlevement(true);
            pL.setEnlevement(false);
            pE.setPointDependance(pL);
            pL.setPointDependance(pE);
            carte.getDemandesLivraisons().ajouterPointInteret(pE);
            carte.getDemandesLivraisons().ajouterPointInteret(pL);
            fenetre.repaint();
        }
        tournee = carte.getTournee();
        fenetre.setPanneauCarte(new JCarte(carte, tournee, fenetre,fenetre.getPanneauCarte().getZoom()));
        fenetre.setTournee(tournee);
        controleur.setTournee(tournee);
        fenetre.viderPanneauEtapes();
        fenetre.viderPanneauPIs();
        fenetre.afficherEtapesTour(true);
        fenetre.afficherPanneauPI(true);
        fenetre.afficherBoutonSupprimer();
        fenetre.repaint();
        
        controleur.setEtat(controleur.etatTournee);
        
    }
}
