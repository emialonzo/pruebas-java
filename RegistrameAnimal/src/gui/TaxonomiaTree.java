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
public class TaxonomiaTree extends javax.swing.JInternalFrame {

    /**
     * Creates new form TaxonomiaTree
     */
    public TaxonomiaTree() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();

        jScrollPane1.setViewportView(jTree1);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public DefaultMutableTreeNode getTreeModelNode(Taxonomia t){
        return TaxonomiaAdaptadorTree.obtenerTreeNode(t);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables
}
