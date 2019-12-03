/*
 * LignePIs
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

import javax.swing.JButton;
import javax.swing.JTextArea;

/**
 *
 * Classe LignePI correspond a un eligne du tableauPIs
 */
public class LignePI {
    private int numero;
    private String type;
    private JTextArea rue;
    private JButton boutonModifier;
    private JButton boutonSupp;
    

    public LignePI(int numero, String type, String rue) {
        this.numero = numero;
        this.type = type;
        this.rue = new JTextArea(rue);
        this.rue.setLineWrap(true);
        this.rue.setWrapStyleWord(true);
        this.boutonModifier = new JButton("Modifier");
        this.boutonSupp = new JButton("Supprimer");
    }

    public LignePI() {
    }

    public int getNumero() {
        return numero;
    }

    public String getType() {
        return type;
    }

    public JTextArea getRue() {
        return rue;
    }
    
    public JButton getBoutonModifier() {
        return boutonModifier;
    }

    public JButton getBoutonSupp() {
        return boutonSupp;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRue(JTextArea rue) {
        this.rue = rue;
    }
    
    public void setBoutonModifier(JButton modifier) {
        this.boutonModifier = modifier;
    }

    public void setBoutonSupp(JButton supp) {
        this.boutonSupp = supp;
    }
    
    
}
