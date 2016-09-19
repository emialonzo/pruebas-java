/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import registrameanimal.IControladorPersona;
import DAOs.MascotaJpaController;
import DAOs.PersonaJpaController;
import entidades.Mascota;
import entidades.Persona;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author emi
 */
public class ControladorPersona extends ControladorAbstracto implements IControladorPersona, InterfazNueva {

    public ControladorPersona(EntityManagerFactory EMF) {
        super(EMF);
        daoPersona = new PersonaJpaController(EMF);
    }
    private PersonaJpaController daoPersona;

    @Override
    public void AgregarPersona(String nombre) {
        Persona p = new Persona();
        p.setNombre(nombre);
        daoPersona.create(p);
    }

    @Override
    public Collection<Persona> GetPersonas() {
        List<Persona> findPersonaEntities = daoPersona.findPersonaEntities();
        return findPersonaEntities;
    }

    @Override
    public Persona GetPersonaPorId(int id) {
        return daoPersona.findPersona((long) id);
    }

    @Override
    public Collection<Mascota> GetMascotasSeguidas(int id) {
        return daoPersona.findPersona((long) id).getMascotasSeguidas();
    }

    @Override
    public Collection<Mascota> GetMascotasRegistradas(int id) {
        return daoPersona.findPersona((long) id).getMascotasRegistradas();
    }

    @Override
    public void RegistrarMascota(int idPersona, String nombreMascota, Date fechaNacimiento) throws Exception {
        Persona p = daoPersona.findPersona((long) idPersona);
        Mascota m = new Mascota();
        m.setNombre(nombreMascota);
        m.setFechaNacimiento(fechaNacimiento);
        p.getMascotasRegistradas().add(m);
        daoPersona.edit(p);

    }

    @Override
    public void SeguirMascota(int idPersona, int idMascota) throws Exception {
        MascotaJpaController daoMascota = new MascotaJpaController(EMF);
        Mascota m = daoMascota.findMascota((long) idMascota);

        Persona p = daoPersona.findPersona((long) idPersona);
        p.getMascotasSeguidas().add(m);
        daoPersona.edit(p);

    }

}
