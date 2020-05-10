/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.kemiantestaaja.domain;

import fi.kemiantestaaja.dao.DatabaseDAO;
import java.util.ArrayList;

/**
 *
 * @author Juuri
 */
public class Database {

    DatabaseDAO databaseDAO;

    /**
     * Sisältää metodeja, joilla käsitellään kyseisen kurssin/tietokannan
     * sisältöä.
     *
     * @param course kurssin/tietokannan nimi
     */
    public Database(String course) {
        String check = course + ".db";
        this.databaseDAO = new DatabaseDAO(check);
    }

    /**
     * Metodi lisää termi/selitys parin kurssin tietokantaan
     *
     * @param term lisättävä termi
     * @param explanation selitys termille
     * @return true: termin lisäys onnistui false: termin lisäys epäonnistui
     */
    public boolean addTerm(String term, String explanation) {
        boolean addedTerm = databaseDAO.addTerm(term, explanation);
        return addedTerm;
    }

    /**
     * Metodi poistaa termi/selitys parin kurssin tietokannasta
     *
     * @param term poistettava termi
     * @return true: termin poisto onnistui false: termin poisto epäonnistui
     */
    public boolean removeTerm(String term) {
        boolean removed = databaseDAO.removeTerm(term);
        return removed;
    }

    /**
     * Metodi palauttaa kurssin tietokannssa olevien termien määrän
     *
     * @return tietokannassa olevien termien lukumäärä
     */
    public int numberOfTerms() {
        int amount = databaseDAO.numberOfTerms();
        return amount;
    }

    /**
     * Metodi palauttaa kaikki tietokannassa olevat termit
     *
     * @return palauttaa ArrayList:an kaikista termeistä
     */
    public ArrayList<String> getTerms() {
        ArrayList<String> listOfTerms = databaseDAO.listTerms();
        return listOfTerms;
    }

    /**
     * Metodi palauttaa kaikki termit ja selitykset tietokannasta
     *
     * @return palauttaa ArrayList:an kaikista termeistä ja selityksistä (termi,
     * selitys, tyhjä rivi)
     */
    public ArrayList<String> getTermsAndExplanations() {
        ArrayList<String> toReturn = databaseDAO.listTermsAndExplanations();
        return toReturn;
    }

    /**
     * Metodi palauttaa halutut termit tai selitykset tietokannasta
     *
     * @param termNumber lista haluttujen tekijöiden numeroista tietokannassa
     * @param name - määritellään halutaanko termit vai selitykset (name=termit,
     * explanation=selitykset)
     * @return lista halutuista termeistä/selityksistä
     */
    ArrayList<String> getTermsOrExplanations(ArrayList<Integer> termNumbers, String name) {
        ArrayList<String> toReturn = databaseDAO.listTermsOrExplanations(termNumbers, name);
        return toReturn;
    }

}
