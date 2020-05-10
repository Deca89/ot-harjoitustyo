/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.kemiantestaaja.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Juuri
 */
public class Exam {

    private int questionAmount;
    private Database database;
    private ArrayList<Integer> termNumbers;
    private ArrayList<Integer> answerList;

    public Exam(int questionAmount, Database database) {
        this.questionAmount = questionAmount;
        this.database = database;
        this.termNumbers = new ArrayList<>();
        this.answerList = new ArrayList<>();

    }

    /**
     * määrittelee käytettyjen termien ID:t tietokannasta
     */
    public void setTermNumbers() {
        ArrayList<Integer> terms = new ArrayList<>();

        for (int i = 1; i <= database.numberOfTerms(); i++) {
            terms.add(i);
        }

        Collections.shuffle(terms);
        List<Integer> termss = terms.subList(0, questionAmount);
        this.termNumbers.addAll(termss);
    }

    /**
     * Palauttaa testissä käytetyt termit
     *
     * @return käytetyt termit ArrayList:ana
     */
    public ArrayList<String> getTerms() {
        ArrayList<String> terms = database.getTermsOrExplanations(this.termNumbers, "name");
        return terms;
    }

    /**
     * Palauttaa testissä käytetyt selitykset
     *
     * @return käytetyt selitykse ArrayList:ana
     */
    public ArrayList<String> getExplanations() {
        answerList.addAll(termNumbers);
        Collections.shuffle(termNumbers);
        ArrayList<String> explanations = database.getTermsOrExplanations(this.termNumbers, "explanation");
        return explanations;
    }

    /**
     * Muuntaa answerList:an listaksi oikeista testin vastauksista
     */
    public void getCorrectAnswers() {
        ArrayList<Integer> correctAnswers = new ArrayList<>();
        for (int i = 0; i < termNumbers.size(); i++) {
            correctAnswers.add(answerList.indexOf(termNumbers.get(i)) + 1);
        }
        answerList.clear();
        answerList.addAll(correctAnswers);
    }

    /**
     * Tarkistaa testin tulokset
     *
     * @param givenAnswers annetut vastaukset
     * @return oikeiden vastausten määrä
     */
    public Integer checkExam(ArrayList<Integer> givenAnswers) {
        getCorrectAnswers();
        int correct = 0;
        for (int i = 0; i < givenAnswers.size(); i++) {
            if (givenAnswers.get(i) == answerList.get(i)) {
                correct++;
            }
        }
        return correct;
    }

}
