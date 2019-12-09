package Vue;

import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import modele.Chemin;
import modele.PointInteret;
import modele.Tournee;
import modele.Troncon;

/**
 * AffichageEtapes permet d afficher les details des etapes d une tournee
 * dans un tableau
 *
 * @version Version 1
 *
 * @author Lucie BOVO, Andrea CROC, Sophie LABOUCHEIX, Taoyang LIU,
 * Alexanne MAGNIEN, Grazia RIBBENI, Fatoumata WADE
 *
 */
public class AffichageEtapes extends AbstractTableModel {

    private final ArrayList<LigneEtapes> steps; //Lisste des etapes de la tournee
    private final String header[]; //En tete du tableau
    private int lignePISelect; //numero de la ligne selectionnee
    private int lignePIDepSelect; //numero de la ligne du point dependant
    private FormatCellRenderer formatcell; //style a appliquer au tableau
    private Fenetre fenetre; //fenetre dans laquelle se trouve le tableau
    private Tournee tournee; //la tournee realisee

    /**
     * Constructeur de la classe AffichageEtapes
     * @param format style a apliquer au cellule du tableau
     * @param fenetre la fenetre dans lequel est contenu le tableau
     * @param tournee la tournee faite
     */
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

    /**
     * Recuperer la ligne du tableau selectionnee
     * @return ligne selectionnee
     */
    public int getLignePISelect() {
        return lignePISelect;
    }

    /**
     * Modifier la ligne du tableau selectionnee
     * @param lignePISelect nouvelle ligne selectionnee
     */
    public void setLignePISelect(int lignePISelect) {
        this.lignePISelect = lignePISelect;
    }

    /**
     * Recuperer la ligne du point dependant du point selectionne
     * @return ligne du point dependant
     */
    public int getLignePIDepSelect() {
        return lignePIDepSelect;
    }

    /**
     * Modifier la ligne du point dependant du point selectionne
     * @param lignePIDepSelect nouveau numero de ligne du point dependant
     */
    public void setLignePIDepSelect(int lignePIDepSelect) {
        this.lignePIDepSelect = lignePIDepSelect;
    }

    /**
     * Modifier la tournee
     * @param tournee nouvelle tournee
     */
    public void setTournee(Tournee tournee) {
        this.tournee = tournee;
    }

    /**
     * Recuperer le style a appliquer au tableau
     * @return style
     */
    public FormatCellRenderer getFormatcell() {
        return formatcell;
    }

    /**
     * Modifier le format a appliquer au tableau
     * @param formatcell nouveau format
     */
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
            //Liste des points d interets de la tournee
            ArrayList<PointInteret> successionPointsInteret = tournee.getSuccessionPointsInteret();
            String nomRue = ""; //Nom de la rue ou se trouve un point d interet
            String heureArrivee = ""; //Heure d arrivee au point d interet
            String heureDepart = ""; //Heure de depart du point d interet
            String heureDeb = ""; //Heure de debut de la tournee
            String heureFin = ""; //Heure de fin de la tournee
            String type = ""; //Type du point d interet
            String nomRueEntrepot = ""; //Nom de la rue de l entrepot
            int duree = 0; //Duree d une etape
            String dureeMin = "";//Duree d une etape
            int ordre = 0; //Ordre des etapes
            int numDemande = 0; //Numero de demande de la livraison

            //S assurer que la liste contient des points d'interet
            if (successionPointsInteret != null && successionPointsInteret.size() > 1) {
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
                        //Afficher les etapes dans la fenetre
                        fenetre.setPanneauEtapes(ordre, numDemande, type, nomRue, heureDepart, heureArrivee, dureeMin);
                    }
                }
                //Afficher le retour a l'entrepot
                fenetre.setPanneauEtapesEntrepot(ordre + 1, 0, nomRueEntrepot, heureFin);

            } else {
                fenetre.cacherPanneauEtapesEtTour();
            }

        } else {
            fenetre.cacherPanneauEtapesEtTour();
        }

    }

}
