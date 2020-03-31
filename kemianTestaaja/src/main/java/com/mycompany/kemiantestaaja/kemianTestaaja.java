/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.kemiantestaaja;

import java.sql.SQLException;

/**
 *
 * @author Juuri
 */
public class kemianTestaaja {

    public static void main(String[] args) throws SQLException {
        Kayttoliittyma k = new Kayttoliittyma();
        k.kaynnista();
    }
}
