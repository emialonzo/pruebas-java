/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.Mascota;
import entidades.Persona;
import java.util.Collection;
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
public class InterfazNuevaTest {
    
    public InterfazNuevaTest() {
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
     * Test of AgregarPersona method, of class InterfazNueva.
     */
    @Test
    public void testAgregarPersona() {
        System.out.println("AgregarPersona");
        String nombre = "";
        InterfazNueva instance = new InterfazNuevaImpl();
        instance.AgregarPersona(nombre);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GetMascotasSeguidas method, of class InterfazNueva.
     */
    @Test
    public void testGetMascotasSeguidas() {
        System.out.println("GetMascotasSeguidas");
        int id = 0;
        InterfazNueva instance = new InterfazNuevaImpl();
        Collection<Mascota> expResult = null;
        Collection<Mascota> result = instance.GetMascotasSeguidas(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GetPersonas method, of class InterfazNueva.
     */
    @Test
    public void testGetPersonas() {
        System.out.println("GetPersonas");
        InterfazNueva instance = new InterfazNuevaImpl();
        Collection<Persona> expResult = null;
        Collection<Persona> result = instance.GetPersonas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class InterfazNuevaImpl implements InterfazNueva {

        public void AgregarPersona(String nombre) {
        }

        public Collection<Mascota> GetMascotasSeguidas(int id) {
            return null;
        }

        public Collection<Persona> GetPersonas() {
            return null;
        }
    }
    
}
