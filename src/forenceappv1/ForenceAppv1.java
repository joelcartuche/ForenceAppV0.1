/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forenceappv1;

import jpaController.PersonaJpaController;
import modelos.Persona;

/**
 *
 * @author joelc
 */
public class ForenceAppv1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Persona per = new Persona("T","T","T","T","T","T");
        PersonaJpaController  jpa = new PersonaJpaController();
        jpa.create(per);
    }
}
