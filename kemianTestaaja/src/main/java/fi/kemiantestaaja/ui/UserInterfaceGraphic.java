/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.kemiantestaaja.ui;

import fi.kemiantestaaja.domain.Database;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Juuri
 */
public class UserInterfaceGraphic extends Application {

    public void start(Stage window, String course) throws Exception {
        //Basic setup
        BorderPane layout = new BorderPane();
        Database databaseCourse = new Database(course);
        Label welcomeToCourse = new Label("Tervetuloa " + course + ":in pariin");
        Button returnToOptions = new Button("Palaa valikkoon");
        layout.setBottom(returnToOptions);
        layout.setTop(welcomeToCourse);
        layout.setPadding(new Insets(20, 20, 20, 50));
        layout.setAlignment(welcomeToCourse, Pos.CENTER);
        layout.setAlignment(returnToOptions, Pos.CENTER);

        //Creating options to choose from
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
        returnToOptions.setOnAction((event) -> layout.setCenter(options));

        //Adding terms into the database
        VBox addTerm = new VBox();
        oAddTerm.setOnAction((event) -> layout.setCenter(addTerm));
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
            boolean added = databaseCourse.addTerm(atTerm.getText(), atExplanation.getText());
            if (!added) {
                atMessage.setText("Termi jo tietokannassa");
            } else {
                atMessage.setText("Termi lisätty tietokantaan");
            }
        });
        addTerm.setSpacing(10);

        //Removing terms from the database
        oRemoveTerm.setOnAction((event) -> {
            VBox removeTerm = new VBox();
            Label rtMessage = new Label(" ");
            removeTerm.getChildren().add(rtMessage);
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(removeTerm);
            layout.setCenter(scrollPane);
            ArrayList<String> listOfTerms = databaseCourse.getTerms();
            listOfTerms.forEach((term) -> {
                final Button thTerm = new Button(term);
                removeTerm.getChildren().add(thTerm);
                thTerm.setOnAction((eventt) -> {
                    String termToDelete = thTerm.getText();
                    boolean wasDeleted = databaseCourse.removeTerm(termToDelete);
                    if (wasDeleted) {
                        rtMessage.setText("Termi poistettu");
                        oRemoveTerm.fire();
                    } else {
                        rtMessage.setText("Termiä ei ole tietokannassa");
                    }
                });
            });
            removeTerm.setSpacing(10);
        });

        //Listing the terms/explanations in database
        oReadThroughTerms.setOnAction((event) -> {
            VBox readThroughTerms = new VBox();
            readThroughTerms.setSpacing(10);
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(readThroughTerms);
            layout.setCenter(scrollPane);
            ArrayList<String> list = databaseCourse.getTermsAndExplanations();
            list.forEach((term) -> {
                final Label thTerm = new Label(term);
                readThroughTerms.getChildren().add(thTerm);
            });
        });

        oCreateExam.setOnAction((event) -> {
            UITakeExam UIExam = new UITakeExam();
                    Stage newStage = new Stage();
                    try {
                        UIExam.start(newStage, databaseCourse);
                    } catch (Exception ex) {
                        Logger.getLogger(UIDatabaseChooserGraphic.class.getName()).log(Level.SEVERE, null, ex);
                    }
        });
        
        

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
