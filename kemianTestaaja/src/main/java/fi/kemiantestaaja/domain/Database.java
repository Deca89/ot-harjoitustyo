/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.kemiantestaaja.domain;

import fi.kemiantestaaja.dao.DatabaseDAO;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Juuri
 */
public class Database {
    DatabaseDAO DAO;
/**
 * Sisältää metodeja, joilla käsitellään kyseisen kurssin/tietokannan sisältöä.
 * @param course kurssin/tietokannan nimi
 * @throws SQLException
 * @throws ClassNotFoundException 
 */
    public Database(String course) throws SQLException, ClassNotFoundException {
        String check = course + ".db";
        this.DAO = new DatabaseDAO(check);
    }
/**
 * Metodi lisää termi/selitys parin kurssin tietokantaan
 * @param term lisättävä termi
 * @param explanation selitys termille
 * @return 1: termi on jo lisätty tietokantaan 2: termi ja selitys lisättiin tietokantaan 3: selityksen lisäyksessä tapahtui ongelma
 */
    public boolean addTerm(String term, String explanation){
        boolean addedTerm = DAO.addTerm(term, explanation);
        return addedTerm;
    }
/**
 * Metodi poistaa termi/selitys parin kurssin tietokannasta
 * @param term poistettava termi
 * @return 1: termiä ei ole tietokannassa 2: termi ja selitys poistettu 3: ongelma poistossa.
 * @throws SQLException 
 */
    public boolean removeTerm(String term){
        boolean removed = DAO.removeTerm(term);
        return removed;
    }
/**
 * Metodi palauttaa kurssin tietokannssa olevien termien määrän
 * @return tietokannassa olevien termien lukumäärä
 * @throws SQLException 
 */
    public int numberOfTerms(){
        int amount = DAO.numberOfTerms();
        return amount;
    }
    /**
     * Metodi palauttaa kaikki tietokannassa olevat termit
     * @return palauttaa ArrayList:an kaikista termeistä
     * @throws SQLException 
     */
    public ArrayList<String> getTerms() {
        ArrayList<String> listOfTerms = DAO.listTerms();
        return listOfTerms;
    }
/**
 * Metodi palauttaa kaikki termit ja selitykset tietokannasta
 * @return palauttaa ArrayList:an kaikista termeistä ja selityksistä (termi, selitys, tyhjä rivi)
 * @throws SQLException 
 */
    public ArrayList<String> getTermsAndExplanations() {
        ArrayList<String> toReturn = DAO.listTermsAndExplanations();
        return toReturn;
    }

}
