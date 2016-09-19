/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorCentral;

import entidades.Persona;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import registrameanimal.Factory;

/**
 *
 * @author emi
 */
public class ControladorPersona {
    
    public Collection<Persona> GetPersonas(){
        Factory f =  new Factory();
        return f.getIControladorPersona().GetPersonas();
    }
    
    public Collection<String> GetStrPersonas(){
        Factory f =  new Factory();
        List<String> l = new ArrayList<>();
        for (Persona GetPersona : f.getIControladorPersona().GetPersonas()) {
            l.add(GetPersona.toString());
        }
        return l;
    }
    
}
