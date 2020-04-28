/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.kemiantestaaja.ui;

import fi.kemiantestaaja.logics.Database;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Juuri
 */
public class UserInterfaceGraphic extends Application {

    public void start(Stage window, String course) throws Exception {
        BorderPane layout = new BorderPane();
        Database databaseCourse = new Database(course);
        Label welcomeToCourse = new Label("Tervetuloa " + course + ":in pariin");
        Button returnToOptions = new Button("Palaa valikkoon");
        layout.setBottom(returnToOptions);
        layout.setTop(welcomeToCourse);
        layout.setPadding(new Insets(20, 20, 20, 50));
        layout.setAlignment(welcomeToCourse, Pos.CENTER);
        layout.setAlignment(returnToOptions, Pos.CENTER);

        VBox options = new VBox();
        Button oAddTerm = new Button("Lisää termi/selitys");
        oAddTerm.setPrefSize(400, 10);
        Button oRemoveTerm = new Button("Poista termi/selitys");
        oRemoveTerm.setPrefSize(400, 10);
        Button oReadThroughTerms = new Button("Tarkastele termejä");
        oReadThroughTerms.setPrefSize(400, 10);
        Button oCreateExam = new Button("Tee testi");
        oCreateExam.setPrefSize(400, 10);
        options.getChildren().add(oAddTerm);
        options.getChildren().add(oRemoveTerm);
        options.getChildren().add(oReadThroughTerms);
        options.getChildren().add(oCreateExam);
        layout.setCenter(options);
        options.setSpacing(10);

        VBox addTerm = new VBox();
        Label atTermToAdd = new Label("Termi:");
        TextField atTerm = new TextField();
        Label atExplanationToAdd = new Label("Selitys:");
        TextField atExplanation = new TextField();
        Button atInsertTerm = new Button("Lisää termi");
        Label atMessage = new Label(" ");
        addTerm.getChildren().add(atTermToAdd);
        addTerm.getChildren().add(atTerm);
        addTerm.getChildren().add(atExplanationToAdd);
        addTerm.getChildren().add(atExplanation);
        addTerm.getChildren().add(atInsertTerm);
        addTerm.getChildren().add(atMessage);
        atInsertTerm.setOnAction((event) -> {
            try {
                int added = databaseCourse.addTerm(atTerm.getText(), atExplanation.getText());
                if (added == 1) {
                    atMessage.setText("Termi jo tietokannassa");
                } else if (added == 2) {
                    atMessage.setText("Termi ja selitys lisätty tietokantaan");
                } else {
                    atMessage.setText("Selityksen lisäyksessä tapahtui jotakin omituista");
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserInterfaceGraphic.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        addTerm.setSpacing(10);

        oRemoveTerm.setOnAction((event) -> {
            try {
                VBox removeTerm = new VBox();
                Label rtMessage = new Label(" ");
                removeTerm.getChildren().add(rtMessage);
                layout.setCenter(removeTerm);
                ArrayList<String> listOfTerms = databaseCourse.getTerms();
                listOfTerms.forEach((term) -> {
                    final Button thTerm = new Button(term);
                    removeTerm.getChildren().add(thTerm);
                    thTerm.setOnAction((eventt) -> {
                        try {
                            String termToDelete = thTerm.getText();
                            int wasDeleted = databaseCourse.removeTerm(termToDelete);
                            if (wasDeleted == 2) {
                                rtMessage.setText("Termi poistettu");
                            } else if (wasDeleted == 1) {
                                rtMessage.setText("Termiä ei ole tietokannassa");
                            } else {
                                rtMessage.setText("Termin poisto epäonnistui");
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(UserInterfaceGraphic.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                });
            } catch (SQLException ex) {
                Logger.getLogger(UserInterfaceGraphic.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        oReadThroughTerms.setOnAction((event) -> {
            try {
                VBox readThroughTerms = new VBox();
                readThroughTerms.setSpacing(10);
                ScrollPane scrollPane = new ScrollPane();
                scrollPane.setContent(readThroughTerms);
                layout.setCenter(scrollPane);
                ArrayList<String> list = databaseCourse.printTermsAndExplanations();
                list.forEach((term) -> {
                    final Label thTerm = new Label(term);
                    readThroughTerms.getChildren().add(thTerm);
                });
            } catch (SQLException ex) {
                Logger.getLogger(UserInterfaceGraphic.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        

        oAddTerm.setOnAction((event) -> layout.setCenter(addTerm));
        returnToOptions.setOnAction((event) -> layout.setCenter(options));

        layout.setPrefSize(500, 600);
        Scene primaryScene = new Scene(layout);

        window.setScene(primaryScene);
        window.show();
    }

    @Override
    public void start(Stage arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
