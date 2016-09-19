/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registrameanimal;

import controladores.ControladorPersona;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author emi
 */
public class Factory {
    
    private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("RegistrameAnimalPU");
    
    public IControladorPersona getIControladorPersona(){
        return new ControladorPersona(EMF);
    }
}
