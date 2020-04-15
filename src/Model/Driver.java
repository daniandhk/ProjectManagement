/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Controller_Login;

/**
 *
 * @author dania
 */
public class Driver {
    public static void main(String[] args) {
        Application app = new Application();
        new Controller_Login(app);
    }
}
