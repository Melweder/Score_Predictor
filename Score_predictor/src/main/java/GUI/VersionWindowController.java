package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class VersionWindowController {
    @FXML
    private Label versionLabel;

    @FXML
    public void initialize() {
        versionLabel.setText("Score Predictor v1.04\n@Copyright Łukasz Wach");
    }
    @FXML
    void onOKButtonAction(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
}
