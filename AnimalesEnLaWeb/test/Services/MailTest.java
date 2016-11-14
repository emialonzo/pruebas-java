/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

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
public class MailTest {

    public MailTest() {
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
     * Test of enviarMail method, of class Mail.
     */
    @Test
    public void testEnviarMail() {
        System.out.println("enviarMail");
        try {
            Mail instance = new Mail();
            instance.enviarMail();
        } catch (Exception e) {
            // TODO review the generated test code and remove the default call to fail.
        fail("Error al enviar mail");
        }

        
    }

}
