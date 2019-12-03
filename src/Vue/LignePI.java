/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import javax.swing.JButton;
import javax.swing.JTextArea;

/**
 *
 * @author acer
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
