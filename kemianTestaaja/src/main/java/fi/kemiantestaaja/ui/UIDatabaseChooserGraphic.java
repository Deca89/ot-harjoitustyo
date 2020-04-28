/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.kemiantestaaja.ui;

import fi.kemiantestaaja.logics.CourseSelections;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Juuri
 */
public class UIDatabaseChooserGraphic extends Application {
  CourseSelections courses = new CourseSelections();  

    @Override
    public void start(Stage window) throws IOException {
        BorderPane layout = new BorderPane();
        Label lWelcome = new Label("Tervetuloa harjoitustyön maailmaan");
        Button returnToOptions = new Button("Palaa valikkoon");
        layout.setTop(lWelcome);
        layout.setBottom(returnToOptions);
        List<String> listOfCourses = courses.listCourses();
        layout.setPadding(new Insets(20, 20, 20, 50));
        layout.setAlignment(lWelcome, Pos.CENTER);
        layout.setAlignment(returnToOptions, Pos.CENTER);
        
        VBox options = new VBox();
        Button oCreateCourse = new Button("Luo kurssi");
        oCreateCourse.setPrefSize(400, 10);
        Button oChooseCourse = new Button("Valitse olemassa oleva kurssi");
        oChooseCourse.setPrefSize(400, 10);
        Button oRemoveCourse = new Button("Poista kurssi");
        oRemoveCourse.setPrefSize(400, 10);
        options.getChildren().add(oCreateCourse);
        options.getChildren().add(oChooseCourse);
        options.getChildren().add(oRemoveCourse);
        layout.setCenter(options);
        options.setSpacing(10);
        
        VBox optionDone = new VBox();
        Label odMessage = new Label("jotain");
        Button odReturnToOptions = new Button("Palaa alkuun");
        optionDone.getChildren().add(odMessage);
        optionDone.getChildren().add(odReturnToOptions);
        odReturnToOptions.setOnAction((event) -> layout.setCenter(options));
        optionDone.setSpacing(10);
        
        VBox createCourse = new VBox();
        TextField crCourseName = new TextField();
        Button crInsertCourse = new Button("Luo kurssi");
        createCourse.getChildren().add(crCourseName);
        createCourse.getChildren().add(crInsertCourse);
        crInsertCourse.setOnAction((event) -> {
            try {
                Boolean added = courses.addCourse(crCourseName.getText());
                if (added) {
                    odMessage.setText("Kurssi lisätty");
                    layout.setCenter(optionDone);
                } else {
                    odMessage.setText("Kurssi on jo olemassa");
                    layout.setCenter(optionDone);
                }
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(UIDatabaseChooserGraphic.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        createCourse.setSpacing(10);
        
        VBox chooseCourse = new VBox();
        Label chChooseCouse = new Label("Valitse kurssi listalta:");
        chooseCourse.getChildren().add(chChooseCouse);
        listOfCourses.forEach((course) -> {
            final Button chCourse = new Button(course);
            chooseCourse.getChildren().add(chCourse);        
            chCourse.setOnAction((event) -> {
                String courseToStartRaw = chCourse.getText();
                String courseToStart = courseToStartRaw.substring(2, courseToStartRaw.length()-3);
                try {
                    boolean courseExists = courses.doesCourseExist(courseToStart);
                    if (courseExists) {
                    UserInterfaceGraphic UIG = new UserInterfaceGraphic();
                    Stage newStage = new Stage();
                    UIG.start(newStage, courseToStart);
                } else {
                    odMessage.setText("Kurssia ei ole olemassa");
                    layout.setCenter(optionDone);
                }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(UIDatabaseChooserGraphic.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(UIDatabaseChooserGraphic.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
      });
        chooseCourse.setSpacing(10);
        
        VBox deleteCourse = new VBox();
        Label dcChooseCouse = new Label("Valitse poistettava kurssi listalta:");
        deleteCourse.getChildren().add(dcChooseCouse);
        listOfCourses.forEach((course) -> {
            final Button thCourse = new Button(course);
            deleteCourse.getChildren().add(thCourse);        
            thCourse.setOnAction((event) -> {
                String courseToDeleteRaw = thCourse.getText();
                String courseToDelete = courseToDeleteRaw.substring(2, courseToDeleteRaw.length()-3);
                try {
                    boolean wasDeleted = courses.deleteCourse(courseToDelete);
                    if (wasDeleted) {
                    odMessage.setText("Kurssi poistettu");
                    layout.setCenter(optionDone);
                } else {
                    odMessage.setText("Kurssia ei ole olemassa");
                    layout.setCenter(optionDone);
                }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(UIDatabaseChooserGraphic.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
      });
        deleteCourse.setSpacing(10);
        
        oCreateCourse.setOnAction((event) -> layout.setCenter(createCourse));
        oChooseCourse.setOnAction((event) -> layout.setCenter(chooseCourse));
        oRemoveCourse.setOnAction((event) -> layout.setCenter(deleteCourse));
        returnToOptions.setOnAction((event) -> layout.setCenter(options));
        
        
        layout.setPrefSize(500, 600);
        Scene primaryScene = new Scene(layout);
        
        window.setScene(primaryScene);
        window.show();
        
    }
    
        public static void main(String[] args) {
        launch(UIDatabaseChooserGraphic.class);
    }

    
    
    
}
