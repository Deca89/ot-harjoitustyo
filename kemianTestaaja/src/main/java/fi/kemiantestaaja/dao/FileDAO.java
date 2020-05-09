/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.kemiantestaaja.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Juuri
 */
public class FileDAO {

    public Integer createFile(String fileName) {
        boolean fileExistsAlready = fileExists(fileName);

        if (fileExistsAlready) {
            return 1;
        } else {
            try {
                Connection db = DriverManager.getConnection("jdbc:sqlite:" + fileName + ".db");
                db.createStatement().execute("CREATE TABLE IF NOT EXISTS Terms(id INTEGER PRIMARY KEY, name TEXT UNIQUE NOT NULL, explanation TEXT UNIQUE NOT NULL)");
                return 2;
            } catch (SQLException ex) {
                return 3;
            }
        }

    }

    public boolean fileExists(String fileName) {
        try {
            Class.forName("org.sqlite.JDBC");
            String check = fileName + ".db";
            File file = new File(check);
            return file.exists();
        } catch (ClassNotFoundException ex) {
            return false;
        }
    }

    public boolean deleteFile(String fileName) {
        if (fileExists(fileName)) {
            String check = fileName + ".db";
            File file = new File(check);
            file.delete();
            return true;
        } 
        return false;
    }

    public List<String> listDBFiles() {
        try {
            Stream<Path> curDirFiles = Files.walk(Paths.get("."));

            List<String> courses = curDirFiles.map(x -> x.toString())
                    .filter(f -> f.endsWith(".db")).collect(Collectors.toList());

            return courses;
        } catch (IOException ex) {
            return null;
        }
    }

}
