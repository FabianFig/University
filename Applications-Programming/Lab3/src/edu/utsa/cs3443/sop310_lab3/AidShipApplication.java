package edu.utsa.cs3443.sop310_lab3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Entry point for the Aid Ship Management System JavaFX application.
 * <p>
 * This class initializes and launches the graphical user interface (GUI)
 * by loading the FXML layout and linking it to the corresponding controller.
 * </p>
 *
 * <p>Responsibilities:</p>
 * <ul>
 *     <li>Load the FXML layout file: {@code main-screen.fxml}</li>
 *     <li>Configure the primary application stage (title, size, and scene)</li>
 *     <li>Display the main interface for user interaction</li>
 * </ul>
 *
 * <p>
 * The layout is defined in:
 * {@code /edu/utsa/cs3443/sop310_lab3/layouts/main-screen.fxml}
 * and is controlled by
 * {@link edu.utsa.cs3443.sop310_lab3.MainScreenController}.
 * </p>
 *
 * @author Fabian Figueroa
 * @version 1.0
 */
public class AidShipApplication extends Application {

    /**
     * Initializes and displays the main JavaFX window.
     * <p>
     * Loads the FXML layout, creates a new {@link Scene}, and sets
     * the stage title, resizability, and visibility.
     * </p>
     *
     * @param stage the primary {@link Stage} provided by the JavaFX runtime
     * @throws Exception if the FXML file cannot be loaded
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/edu/utsa/cs3443/sop310_lab3/layouts/main-screen.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Aid Ship Management System!");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

    /**
     * The main entry point of the program.
     * <p>
     * Launches the JavaFX application by calling {@link #start(Stage)}.
     * </p>
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        launch(args);
    }
}
