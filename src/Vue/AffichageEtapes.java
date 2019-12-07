/*
 * AffichageEtapes
 *
 * Version 1
 * 
 *
 * 
 * Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU,
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
package Vue;

import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import modele.Chemin;
import modele.PointInteret;
import modele.Tournee;
import modele.Troncon;

/**
 *
 * Classe AffichageEtapes permet d afficher les details des etapes d une tournee
 * dans un tableau
 */
public class AffichageEtapes extends AbstractTableModel {

    private final ArrayList<LigneEtapes> steps;
    private final String header[];
    private int lignePISelect;
    private int lignePIDepSelect;
    private FormatCellRenderer formatcell;
    private Fenetre fenetre;
    private Tournee tournee;

    public AffichageEtapes(FormatCellRenderer format, Fenetre fenetre, Tournee tournee) {
        this.header = new String[]{"Ordre", "Demande", "Type", "Rue", "Arrivée prévue", "Départ prévu", "Durée prévue"};
        this.steps = new ArrayList<>();
        this.lignePISelect = -1;
        this.lignePIDepSelect = -1;
        this.formatcell = format;
        this.fenetre = fenetre;
        this.tournee = tournee;
    }

    /**
     * Recuperer le nombre de lignes que contient le tableau
     *
     * @return : le nombre de lignes du tableau
     */
    @Override
    public int getRowCount() {
        return this.steps.size();
    }

    /**
     * Recuperer le nombre de colonnes que contient le tableau
     *
     * @return : le nombre de colonnes du tableau
     */
    @Override
    public int getColumnCount() {
        return this.header.length;
    }

    /**
     * Recuperer la valeur d une cellue du tableau
     *
     * @param rowIndex : numero de la ligne
     * @param columnIndex : numero de la colonne
     * @return : Objet qui est dans la cellule de coordonnees
     * [rowIndex,columnIndex]
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return this.steps.get(rowIndex).getOrdre();
            case 1:
                return this.steps.get(rowIndex).getNumDemande();
            case 2:
                return this.steps.get(rowIndex).getType();
            case 3:
                return this.steps.get(rowIndex).getRue();
            case 4:
                return this.steps.get(rowIndex).getArrivee();
            case 5:
                return this.steps.get(rowIndex).getDepart();
            case 6:
                return this.steps.get(rowIndex).getDuree();

            default:
                return null;
        }
    }

    /**
     * Recuperer le nom de la colonne
     *
     * @param columnIndex : numero de la colonne
     * @return : nom de la colonne
     */
    @Override
    public String getColumnName(int columnIndex) {
        return header[columnIndex];
    }

    public int getLignePISelect() {
        return lignePISelect;
    }

    public void setLignePISelect(int lignePISelect) {
        this.lignePISelect = lignePISelect;
    }

    public int getLignePIDepSelect() {
        return lignePIDepSelect;
    }

    public void setLignePIDepSelect(int lignePIDepSelect) {
        this.lignePIDepSelect = lignePIDepSelect;
    }
    
    

    public void setTournee(Tournee tournee) {
        this.tournee = tournee;
    }

    public FormatCellRenderer getFormatcell() {
        return formatcell;
    }

    public void setFormatcell(FormatCellRenderer formatcell) {
        this.formatcell = formatcell;
    }

    /**
     * Ajouter une ligne au tableau
     *
     * @param step : ligne a ajouter
     */
    public void addStep(LigneEtapes step) {
        this.steps.add(step);

        //Prevenir le tableau qu une ligne a ete ajoutee a la liste
        this.fireTableRowsInserted(this.steps.size() - 1, this.steps.size() - 1);
    }

    /**
     * Supprimer une ligne du tableau
     *
     * @param rowIndex : numero de la ligne a supprimer
     */
    public void removeStep(int rowIndex) {
        this.steps.remove(rowIndex);

        //Prevenir le tableau qu une ligne a ete supprimee de la liste
        this.fireTableRowsDeleted(rowIndex, rowIndex);
    }

    /**
     * Supprimer tous les elements de la liste associee a un tableau
     */
    public void clearSteps() {
        this.steps.clear();
    }

    /**
     * Afficher les etapes d une tournee
     *
     * @param afficher savoir si il faut afficher le tableau ou le label
     */
    public void afficherEtapes(boolean afficher) {
        if (afficher) {
            System.out.println("afficher : "+afficher);
            System.out.println("if 1");
            ArrayList<PointInteret> successionPointsInteret = tournee.getSuccessionPointsInteret();
            String nomRue = "";
            String heureArrivee = "";
            String heureDepart = "";
            String heureDeb = "";
            String heureFin = "";
            String type = "";
            String nomRueEntrepot = "";
            int duree = 0;
            String dureeMin = "";
            int ordre = 0;
            int numDemande = 0;

            //S assurer que la liste contient des points d'interet
            if (successionPointsInteret != null && successionPointsInteret.size()>1) {
                System.out.println("if 2");
                //fenetre.viderPanneauEtapes();
                if (successionPointsInteret.size() > 1) {
                    for (PointInteret pt : successionPointsInteret) {
                        //Recuperer le numero de l etape
                        ordre = successionPointsInteret.indexOf(pt);
                        Chemin c = pt.getCheminDepart();
                        Troncon t = c.getSuccessionTroncons().get(0);
                        //Recuperer l adresse
                        nomRue = t.getNomRue();

                        //Recuperer le point d'interet correspondant a l'entrepot
                        if (ordre == 0) {
                            nomRueEntrepot = nomRue;
                            numDemande = 0;
                            heureDeb = pt.getHeureDepart();
                            heureDeb = heureDeb.substring(0, heureDeb.lastIndexOf(":"));
                            heureDeb = heureDeb.replace(":", "h");
                            heureFin = pt.getHeureArrivee();
                            heureFin = heureFin.substring(0, heureFin.lastIndexOf(":"));
                            heureFin = heureFin.replace(":", "h");
                            //Afficher le depart de l'entrepot
                            fenetre.setPanneauEtapesEntrepot(ordre, numDemande, nomRueEntrepot, heureDeb);
                        } else {
                            numDemande = pt.getNumeroDemande();
                            if (pt.isEnlevement()) {
                                type = "Enlèvement";
                            } else {
                                type = "Livraison";
                            }
                            //Recuperer la duree de l etape
                            DecimalFormat df = new DecimalFormat("0.00");
                            duree = pt.getDuree();
                            dureeMin = df.format(duree / 60);
                            dureeMin = dureeMin.substring(0, dureeMin.lastIndexOf(","));
                            //Recuperer l heure d arrivee au point d interet
                            heureArrivee = pt.getHeureArrivee();
                            heureArrivee = heureArrivee.substring(0, heureArrivee.lastIndexOf(":"));
                            heureArrivee = heureArrivee.replace(":", "h");
                            //Recuperer l heure de depart du point d interet
                            heureDepart = pt.getHeureDepart();
                            heureDepart = heureDepart.substring(0, heureDepart.lastIndexOf(":"));
                            heureDepart = heureDepart.replace(":", "h");
                            System.out.println("nomRue : " + nomRue + "type : " + type + "heure Arrivee : " + heureArrivee + "duree : " + dureeMin);
                            //Afficher les etapes dans la fenetre
                            fenetre.setPanneauEtapes(ordre, numDemande, type, nomRue, heureDepart, heureArrivee, dureeMin);
                        }
                    }
                    //Afficher le retour a l'entrepot
                    fenetre.setPanneauEtapesEntrepot(ordre + 1, 0, nomRueEntrepot, heureFin);

                }
            } else {
                System.out.println("else1");
                fenetre.cacherPanneauEtapesEtTour();
                fenetre.afficherOuCacherMessageTournee(true);
            }

        } else {
            System.out.println("else2");
            fenetre.cacherPanneauEtapesEtTour();
            fenetre.afficherOuCacherMessageTournee(true);
        }

    }

}
