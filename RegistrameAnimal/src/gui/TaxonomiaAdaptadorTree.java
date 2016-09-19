/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entidades.Taxonomia;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author emi
 */
public class TaxonomiaAdaptadorTree {
    
    
    static public DefaultMutableTreeNode obtenerTreeNode(Taxonomia t){
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(t);
        for (Taxonomia tHijo : t.getHijos()) {
            node.add(obtenerTreeNode(t));
        }
        return node;
    }
    
}
