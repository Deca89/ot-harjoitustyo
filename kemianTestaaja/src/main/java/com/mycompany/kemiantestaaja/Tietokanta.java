/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.kemiantestaaja;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Juuri
 */
public class Tietokanta {

    boolean tietokantaOnOlemassa;
    Connection kasittelija;

    public Tietokanta() throws SQLException {
        File tiedosto = new File("kemianTesteri.db");
        if (tiedosto.exists()) {
            this.tietokantaOnOlemassa = true;
            this.kasittelija = DriverManager.getConnection("jdbc:sqlite:kemianTesteri.db");
        } else {
            this.tietokantaOnOlemassa = false;
            this.kasittelija = null;
        }
    }

    public void luoTietokanta() throws SQLException {
        if (tietokantaOnOlemassa) {
            System.out.println("Tietokanta on jo olemassa\n");
        } else {
            Connection db = DriverManager.getConnection("jdbc:sqlite:kemianTesteri.db");
            this.tietokantaOnOlemassa = true;
            kasittelija = db;

            System.out.println("Tietokanta luotu\n");

            kasittelija.createStatement().execute("CREATE TABLE IF NOT EXISTS Termit(id INTEGER PRIMARY KEY, nimi TEXT UNIQUE NOT NULL)");
            kasittelija.createStatement().execute("CREATE TABLE IF NOT EXISTS Selitykset(Termi_id INTEGER REFERENCES Termit, selitys TEXT UNIQUE NOT NULL)");
        }
    }

    public boolean isTietokantaOnOlemassa() {
        if (!tietokantaOnOlemassa) {
            System.out.println("\nTietokantaa ei ole vielä olemassa. \nYstävällisesti luo ensin tietokanta ja yritä sitten uudestaan.\n");
        }
        return tietokantaOnOlemassa;
    }

    public void lisaaTermi(String termi, String selitys) throws SQLException {
        PreparedStatement p = kasittelija.prepareStatement("INSERT INTO Termit(nimi) VALUES (?)");
        p.setString(1, termi);
        try {
            p.executeUpdate();
            System.out.println(termi + " lisätty termeihin\n");
        } catch (SQLException e) {
            System.out.println("Termi on jo lisätty tietokantaan. Yritä uudelleen.\n");
        }

        PreparedStatement p2 = kasittelija.prepareStatement("SELECT id FROM Termit WHERE nimi=?");
        p2.setString(1, termi);
        ResultSet r = p2.executeQuery();

        PreparedStatement p3 = kasittelija.prepareStatement("INSERT INTO Selitykset(Termi_id, selitys) VALUES (?, ?)");
        p2.setString(2, selitys);
        p2.setInt(1, r.getInt("id"));

        try {
            p2.executeUpdate();
            System.out.println("Selitys lisätty myös.\n");
        } catch (SQLException e) {
            System.out.println("Selityksen lisäyksessä sattui virhe. En tiedä vielä miksi.\n");
        }

    }
}
