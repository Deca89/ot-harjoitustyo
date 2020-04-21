/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.kemiantestaaja.ui;

import fi.kemiantestaaja.logics.Database;
import fi.kemiantestaaja.logics.Exam;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Juuri
 */
public class UserInterface {
    private Database database;
    private String currentCourse;
    private Scanner scanner;
    
    public UserInterface(String courseName) throws SQLException, ClassNotFoundException {
        this.database = new Database(courseName);
        this.currentCourse = courseName;
        this.scanner = new Scanner(System.in);
    }
    
    public void start() throws SQLException, ClassNotFoundException {
        System.out.println("Tervetuloa " + this.currentCourse + ":n maailmaan\n");
        printOptions();
        
        while (true) {

        
        System.out.println("Valitse toiminto (1-4, valikko, lopeta)");
        String input = scanner.nextLine();
        
        if (input.equals("1")) {
            System.out.print("Termi: ");
            String term = scanner.nextLine();
            System.out.print("Selitys: ");
            String explanation = scanner.nextLine();
            this.database.addTerm(term, explanation);
        } else if (input.equals("2")) {
            System.out.print("Termi: ");
            String term = scanner.nextLine();
            this.database.removeTerm(term);
        } else if (input.equals("3")) {
            this.database.printTermsAndExplanations();
        } else if (input.equals("4")) {
            exam();
        } else if (input.equals("valikko")) {
            printOptions();
        } else if (input.equals("lopeta")) {
            return;
        }
        
        }
    }
    
    public void printOptions() {
        System.out.println("\nValikko:");
        System.out.println("1: Lisää termi/selitys tietokantaan.");
        System.out.println("2: Poista termi/selitys tietokannasta");
        System.out.println("3: Tarkastele termejä");
        System.out.println("4: Luo testi");
        System.out.println("valikko: Tulosta valikko\n");
    }
    
    public void exam() throws SQLException, ClassNotFoundException {
        int questions = this.database.termsInDatabase();
        System.out.print("Kurssi sisältää " + questions + " termiä. Kuinka monta niistä haluat tenttiin? ");
        String amount = scanner.nextLine();
        int amountValue = Integer.valueOf(amount);
        Exam toDo = new Exam(this.currentCourse, amountValue, questions);
        List<Integer> correctAnswers = toDo.createExam();
        System.out.println("\n Syötä termejä vastaavien selitysten numerot yksi kerrallaan.\n");
        ArrayList<Integer> answers = new ArrayList<>();
        for (int i = 0; i < amountValue; i++) {
            String answer = scanner.nextLine();
            answers.add(correctAnswers.get(Integer.valueOf(answer)-1));
        }
        toDo.checkExam(answers);
    }
    
    
}

