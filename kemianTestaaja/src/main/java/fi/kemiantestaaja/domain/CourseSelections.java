/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.kemiantestaaja.domain;

import fi.kemiantestaaja.dao.FileDAO;
import java.util.List;

/**
 *
 * @author Juuri
 */
public class CourseSelections {

    private FileDAO fileDAO;

    /**
     * Sisältää metodeja, joilla käsitellään yleisellä tasolla ohjelman
     * juurikansiossa olevia kursseja/tietokantoja.
     */
    public CourseSelections() {
        this.fileDAO = new FileDAO();
    }

    /**
     * Metodi lisää uuden tietokanta tiedoston
     *
     * @param course Tehtävän tiedoston nimi
     * @return 1: tiedosto jo olemassa 2: tiedosto luotu 3: tiedoston luomisessa
     * tapahtunnut odottamaton virhe false: tietokannan luonti epäonnistui
     */
    public Integer addCourse(String course) {
        int courseCreated = fileDAO.createFile(course);

        return courseCreated;
    }

    /**
     * Metodi palauttaa listan kaikista projektin pääkansiossa olevista
     * "kursseista", eli luoduista tietokannoista.
     *
     * @return lista kursseista.
     */
    public List<String> listCourses() {
        List<String> list = fileDAO.listDBFiles();
        return list;
    }

    /**
     * Metodi poistaa tiedoston/kurssin.
     *
     * @param course poistettavan tiedoston nimi
     * @return boolean true: tiedosto poistettu false: tiedostoa ei poistettu
     * (tarkoituksena se, ettei kyseistä tiedostoa ollutkaan olemassa)
     */
    public Boolean deleteCourse(String course) {
        boolean fileDeleted = fileDAO.deleteFile(course);

        return fileDeleted;
    }

    /**
     * Metodi tarkistaa onko kyseinen kurssi/tiedosto olemassa
     *
     * @param course tarkistettavan tiedoston nimi
     * @return boolean true: tiedosto on olemassa false: tiedostoa ei ole
     * olemassa
     */
    public boolean doesCourseExist(String course) {
        return fileDAO.fileExists(course);
    }
}
