/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.kemiantestaaja.ui;

import fi.kemiantestaaja.ui.UIDatabaseChooser;
import fi.kemiantestaaja.ui.UserInterface;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author Juuri
 */
public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        UIDatabaseChooser k = new UIDatabaseChooser();
        k.start();

    }
}
