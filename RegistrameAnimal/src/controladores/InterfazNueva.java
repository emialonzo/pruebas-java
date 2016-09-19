/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.Mascota;
import entidades.Persona;
import java.util.Collection;

/**
 *
 * @author emi
 */
public interface InterfazNueva {

    void AgregarPersona(String nombre);

    Collection<Mascota> GetMascotasSeguidas(int id);

    Collection<Persona> GetPersonas();
    
}
