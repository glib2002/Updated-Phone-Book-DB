/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.work;

import java.io.IOException;

/**
 *
 * @author Глеб
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        ContactWriter c = new ContactWriter();
        c.start();
    }
    
}
