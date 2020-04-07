/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.kemiantestaaja.ui;

import fi.kemiantestaaja.logics.Database;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Juuri
 */
public class UserInterface {
    private Database database;
    
    public UserInterface() throws SQLException, ClassNotFoundException {
        this.database = new Database();
    }
    
    public void start() throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Tervetuloa harjoitustyön maailmaan\n");
        printOptions();
        
        while (true) {

        
        System.out.println("Valitse toiminto (1-4, valikko, lopeta)");
        String input = scanner.nextLine();
        
        if (input.equals("1")) {
            this.database.addDatabase();
        } else if (input.equals("2") && this.database.doesDatabaseExist()) {
            System.out.print("Termi: ");
            String term = scanner.nextLine();
            System.out.print("Selitys: ");
            String explanation = scanner.nextLine();
            this.database.addTerm(term, explanation);
        } else if (input.equals("3") && this.database.doesDatabaseExist()) {
            System.out.print("Termi: ");
            String term = scanner.nextLine();
            this.database.removeTerm(term);
        } else if (input.equals("4") && this.database.doesDatabaseExist()) {
            this.database.printTermsAndExplanations();
        }else if (input.equals("valikko")) {
            printOptions();
        } else if (input.equals("lopeta")) {
            return;
        }
        
        }
    }
    
    public void printOptions() {
        System.out.println("Valikko:");
        System.out.println("1: Luo tietokanta");
        System.out.println("2: Lisää termi/selitys tietokantaan.");
        System.out.println("3: Poista termi/selitys tietokannasta");
        System.out.println("4: Tarkastele termejä");
        System.out.println("valikko: Tulosta valikko\n");
    }
    
    
}

