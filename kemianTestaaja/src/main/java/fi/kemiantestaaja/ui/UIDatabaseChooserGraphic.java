/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.kemiantestaaja.ui;

import fi.kemiantestaaja.domain.CourseSelections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
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
 * UI eri tiedostojen/tietokantojen hallintaa varten
 *
 * @author Juuri
 */
public class UIDatabaseChooserGraphic extends Application {

    CourseSelections courses = new CourseSelections();

    @Override
    public void start(Stage window) {
        BorderPane layout = new BorderPane();
        Label lWelcome = new Label("Tervetuloa harjoitustyön maailmaan");
        Button returnToOptions = new Button("Palaa valikkoon");
        layout.setTop(lWelcome);
        layout.setBottom(returnToOptions);
        layout.setPadding(new Insets(20, 20, 20, 50));
        layout.setAlignment(lWelcome, Pos.CENTER);
        layout.setAlignment(returnToOptions, Pos.CENTER);

        //Options view for program
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
        options.setSpacing(10);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(options);
        layout.setCenter(scrollPane);

        //Creating new courses/files for the program
        VBox createCourse = new VBox();
        oCreateCourse.setOnAction((event) -> scrollPane.setContent(createCourse));
        TextField crCourseName = new TextField();
        Button crInsertCourse = new Button("Luo kurssi");
        Label crMessage = new Label("");
        createCourse.getChildren().add(crCourseName);
        createCourse.getChildren().add(crInsertCourse);
        createCourse.getChildren().add(crMessage);
        crInsertCourse.setOnAction((event) -> {
            if (crCourseName.getText().equals("")) {
                crMessage.setText("Tyhjä nimike ei kelpaa");
            } else {
                int added = courses.addCourse(crCourseName.getText());
                if (added == 2) {
                    crMessage.setText("Kurssi lisätty");
                } else if (added == 1) {
                    crMessage.setText("Kurssi on jo olemassa");
                } else {
                    crMessage.setText("Kurssin lisäyksessä tapahtui odottamaton ongelma, yritä uudestaan");
                }
            }

        });
        createCourse.setSpacing(10);

        //Opening a course/file
        oChooseCourse.setOnAction((event) -> {
            VBox chooseCourse = new VBox();
            scrollPane.setContent(chooseCourse);
            Label chChooseCouse = new Label("Valitse kurssi listalta:");
            chooseCourse.getChildren().add(chChooseCouse);
            List<String> listOfCourses = courses.listCourses();
            listOfCourses.forEach((course) -> {
                final Button chCourse = new Button(course);
                chooseCourse.getChildren().add(chCourse);
                chCourse.setOnAction((eventt) -> {
                    String courseToStartRaw = chCourse.getText();
                    String courseToStart = courseToStartRaw.substring(2, courseToStartRaw.length() - 3);
                    UserInterfaceGraphic userIG = new UserInterfaceGraphic();
                    Stage newStage = new Stage();
                    try {
                        userIG.start(newStage, courseToStart);
                    } catch (Exception ex) {
                        Logger.getLogger(UIDatabaseChooserGraphic.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            });
            chooseCourse.setSpacing(10);
        });

        //Removing course/file from program(/computer..)
        oRemoveCourse.setOnAction((event) -> {
            VBox deleteCourse = new VBox();
            scrollPane.setContent(deleteCourse);
            Label dcChooseCouse = new Label("Valitse poistettava kurssi listalta:");
            deleteCourse.getChildren().add(dcChooseCouse);
            Label dcMessage = new Label("");
            deleteCourse.getChildren().add(dcMessage);
            List<String> listOfCourses = courses.listCourses();
            listOfCourses.forEach((course) -> {
                final Button thCourse = new Button(course);
                deleteCourse.getChildren().add(thCourse);
                thCourse.setOnAction((eventt) -> {
                    String courseToDeleteRaw = thCourse.getText();
                    String courseToDelete = courseToDeleteRaw.substring(2, courseToDeleteRaw.length() - 3);
                    boolean wasDeleted = courses.deleteCourse(courseToDelete);
                    if (wasDeleted) {
                        dcMessage.setText("Kurssi poistettu");
                        oRemoveCourse.fire();
                    } else {
                        dcMessage.setText("Kurssia ei ole olemassa");
                    }
                });
            });
            deleteCourse.setSpacing(10);
        });

        returnToOptions.setOnAction((event) -> scrollPane.setContent(options));

        layout.setPrefSize(500, 600);
        Scene primaryScene = new Scene(layout);

        window.setScene(primaryScene);
        window.show();

    }

    public static void main(String[] args) {
        launch(UIDatabaseChooserGraphic.class);
    }

}
