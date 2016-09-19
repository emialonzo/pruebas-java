/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author emi
 */
public class ControladorAbstracto {
    
    protected  EntityManagerFactory EMF;

    public ControladorAbstracto(EntityManagerFactory EMF) {
        this.EMF = EMF;
    }
    
    
}
