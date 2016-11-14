/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.Mascota;
import entidades.Persona;
import java.util.Collection;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author emi
 */
public class ControladorPersonaTest {
    
    public ControladorPersonaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of AgregarPersona method, of class ControladorPersona.
     */
    @Test
    public void testAgregarPersona() {
        //obtener personas guardar la cantidad
        //setear valor unico para la persona, elegimos nick
        //obtener usuario por nick, tiene que dar 0, ASSERT persona == null
        //agregar persona con nick elegido
        //obtener todas las personas, ASSERT largo = cantidad +1, 
        //Obtener persona por nick ingresado y hacer un asert ppor cada atributo publico
        System.out.println("AgregarPersona");
        String nombre = "";
        ControladorPersona instance = null;
        instance.AgregarPersona(nombre);
        try {
            instance.AgregarPersona(nombre);
            fail("No se controla nombre unico.");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Ya existia una persona con ese nombre.");
        }
        

//        asser
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GetPersonas method, of class ControladorPersona.
     */
    @Test
    public void testGetPersonas() {
        System.out.println("GetPersonas");
        ControladorPersona instance = null;
        Collection<Persona> expResult = null;
        Collection<Persona> result = instance.GetPersonas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GetPersonaPorId method, of class ControladorPersona.
     */
    @Test
    public void testGetPersonaPorId() {
        System.out.println("GetPersonaPorId");
        int id = 0;
        ControladorPersona instance = null;
        Persona expResult = null;
        Persona result = instance.GetPersonaPorId(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GetMascotasSeguidas method, of class ControladorPersona.
     */
    @Test
    public void testGetMascotasSeguidas() {
        System.out.println("GetMascotasSeguidas");
        int id = 0;
        ControladorPersona instance = null;
        Collection<Mascota> expResult = null;
        Collection<Mascota> result = instance.GetMascotasSeguidas(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GetMascotasRegistradas method, of class ControladorPersona.
     */
    @Test
    public void testGetMascotasRegistradas() {
        System.out.println("GetMascotasRegistradas");
        int id = 0;
        ControladorPersona instance = null;
        Collection<Mascota> expResult = null;
        Collection<Mascota> result = instance.GetMascotasRegistradas(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of RegistrarMascota method, of class ControladorPersona.
     */
    @Test
    public void testRegistrarMascota() throws Exception {
//        System.out.println("RegistrarMascota");
//        int idPersona = 0;
//        String nombreMascota = "";
//        Date fechaNacimiento = null;
//        ControladorPersona instance = null;
//        instance.RegistrarMascota(idPersona, nombreMascota, fechaNacimiento);
        // TODO review the generated test code and remove the default call to fail.
        assertTrue("Test que falla", false);
    }

    /**
     * Test of SeguirMascota method, of class ControladorPersona.
     */
    @Test
    public void testSeguirMascota() throws Exception {
        System.out.println("SeguirMascota");
        int idPersona = 0;
        int idMascota = 0;
        ControladorPersona instance = null;
        instance.AgregarPersona("............");
        instance.SeguirMascota(idPersona, idMascota);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
