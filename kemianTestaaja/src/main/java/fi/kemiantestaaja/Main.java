/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.kemiantestaaja;

import fi.kemiantestaaja.ui.UserInterface;
import java.sql.SQLException;

/**
 *
 * @author Juuri
 */
public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserInterface k = new UserInterface();
        k.start();
    }
}

