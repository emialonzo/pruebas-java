/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registrameanimal;

import entidades.Mascota;
import entidades.Persona;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author emi
 */
public interface IControladorPersona {

    void AgregarPersona(String nombre);

    Collection<Mascota> GetMascotasRegistradas(int id);

    Collection<Mascota> GetMascotasSeguidas(int id);

    Persona GetPersonaPorId(int id);

    Collection<Persona> GetPersonas();

    void RegistrarMascota(int idPersona, String nombreMascota, Date fechaNacimiento) throws Exception;

    void SeguirMascota(int idPersona, int idMascota) throws Exception;
    
}
