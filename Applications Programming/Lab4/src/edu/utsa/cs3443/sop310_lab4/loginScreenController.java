package edu.utsa.cs3443.sop310_lab4;

import edu.utsa.cs3443.sop310_lab4.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Controller class for the login screen of the Aid Ship Management Application.
 * <p>
 * This class handles user authentication by validating credentials entered
 * in the login form. If the credentials are valid, the user is transitioned
 * to the main dashboard. If not, an error message is displayed.
 *
 * <p>Features implemented:</p>
 * <ul>
 *     <li>Credential validation against a CSV-based user database</li>
 *     <li>Transition to the main dashboard upon successful login</li>
 *     <li>Error handling and user feedback for invalid or incomplete input</li>
 * </ul>
 *
 * @author Fabian Figueroa
 * @version 1.0
 */
public class loginScreenController {

    /** Label used to display login error messages. Hidden by default. */
    @FXML
    private Label invalidLogin;

    /** Button used to trigger the login process. */
    @FXML
    private Button loginButton;

    /** ImageView displaying the application logo. */
    @FXML
    private ImageView logoImage;

    /** Password field for entering the user's password. */
    @FXML
    private PasswordField passwordTextField;

    /** Text field for entering the user's username. */
    @FXML
    private TextField usernameTextField;

    /**
     * Handles the login verification process.
     * <p>
     * Validates that both username and password fields are filled.
     * If valid, attempts to authenticate the user using the {@link User} model.
     * On success, loads the main dashboard and passes the authenticated user.
     * On failure, displays an appropriate error message.
     *
     * @param event The action event triggered by clicking the Login button.
     */
    @FXML
    void onCheckLogin(ActionEvent event) {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        // always hide the label at the start of login attempt
        invalidLogin.setVisible(false);

        if (username.isEmpty() || password.isEmpty()) {
            invalidLogin.setText("Please enter both username and password.");
            invalidLogin.setVisible(true);
            return;
        }

        User user = User.authenticate(username, password);
        if (user == null) {
            invalidLogin.setText("Invalid credentials");
            invalidLogin.setVisible(true);
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/utsa/cs3443/sop310_lab4/layouts/main-screen.fxml"));
            Scene scene = new Scene(loader.load());
            MainScreenController controller = loader.getController();
            controller.setLoggedInUser(user);
            controller.initDashboard();

            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            invalidLogin.setText("Error loading dashboard.");
            invalidLogin.setVisible(true);
            e.printStackTrace();
        }
    }
}