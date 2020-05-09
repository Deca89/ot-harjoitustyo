/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.kemiantestaaja.ui;

import fi.kemiantestaaja.domain.Database;
import fi.kemiantestaaja.domain.Exam;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Juuri
 */
public class UITakeExam extends Application {

    public void start(Stage window, Database databaseCourse) throws Exception {
        BorderPane layout = new BorderPane();
        Label upperText = new Label("Tentti");
        Button returnToBeginning = new Button("Palaa tentin alustukseen");
        layout.setBottom(returnToBeginning);
        layout.setTop(upperText);
        layout.setPadding(new Insets(20, 20, 20, 50));
        layout.setAlignment(upperText, Pos.CENTER);
        layout.setAlignment(returnToBeginning, Pos.CENTER);
        ScrollPane scrollpane = new ScrollPane();
        layout.setCenter(scrollpane);

        VBox examFirstScene = new VBox();
        examFirstScene.setSpacing(10);
        HBox amountOfPics = new HBox();
        HBox amountOfTerms = new HBox();
        Button pEcreateExam = new Button("Luo testi");
        Label aopPics = new Label("Kuvien määrä: ");
        TextField aopPicsAmount = new TextField();
        Label aopMaxPics = new Label("max X");
        amountOfPics.getChildren().addAll(aopPics, aopPicsAmount, aopMaxPics);
        Label aopTerms = new Label("Termien määrä: ");
        TextField aopTermsAmount = new TextField();
        Label aopMaxTerms = new Label("max " + databaseCourse.numberOfTerms());
        amountOfTerms.getChildren().addAll(aopTerms, aopTermsAmount, aopMaxTerms);
        Label aopMessage = new Label("");
        examFirstScene.getChildren().addAll(amountOfPics, amountOfTerms, pEcreateExam, aopMessage);
        scrollpane.setContent(examFirstScene);

        pEcreateExam.setOnAction((event) -> {
            VBox takeExam = new VBox();
            int CEamountOfTerms = Integer.valueOf(aopTermsAmount.getText());
            if (CEamountOfTerms > databaseCourse.numberOfTerms() | CEamountOfTerms < 1) {
                aopMessage.setText("Valitse luku 1 ja max lukumäärän väliltä");
            } else {
                scrollpane.setContent(takeExam);
                Exam exam = new Exam(CEamountOfTerms, databaseCourse);
                exam.setTermNumbers();
                ArrayList<String> examTerms = exam.getTerms();
                ArrayList<String> examExplanations = exam.getExplanations();
                
                for (int i = 0; i < CEamountOfTerms; i++) {
                    final HBox box = new HBox();
                    final Label label = new Label((i+1) + ". " + examTerms.get(i) + "   ");
                    final TextField textField = new TextField();
                    final Label label1 = new Label(examExplanations.get(i));
                    box.getChildren().addAll(label, textField, label1);
                    takeExam.getChildren().add(box);
                }
            }

            takeExam.setSpacing(10);
        });

        
        layout.setPrefSize(700, 900);
        Scene primaryScene = new Scene(layout);

        window.setScene(primaryScene);
        window.show();
    }

    @Override
    public void start(Stage arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
