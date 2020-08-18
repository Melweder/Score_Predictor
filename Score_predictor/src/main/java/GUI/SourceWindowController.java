package GUI;

import DataProcessing.DataRetriever;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;


public class SourceWindowController {

    @FXML private Label messageLabel;
    @FXML private TextField textField;

    @FXML
    public void initialize() {
        textField.setText("https://www.football-data.co.uk/mmz4281/1920/E0.csv");
    }

    @FXML
    void onApplyButtonAction(ActionEvent event) {
        DataRetriever.scoresList = DataRetriever.getMatchStatistics(textField.getText());
        if (DataRetriever.scoresList.isEmpty()){
            messageLabel.setText("Wczytywanie pliku źródłowego nie powiodło się!");
            messageLabel.setTextFill(Color.web("#FF0000"));
        }
        else{
            ((Node)(event.getSource())).getScene().getWindow().hide();

        }
    }

    @FXML
    void onBackButtonAction(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void onCheckButtonAction(ActionEvent event) {
        String message = DataRetriever.checkFileURL(textField.getText());
        if (message == null){
            messageLabel.setText("Ścieżka z plikiem źródłowym jest poprawna!");
            messageLabel.setTextFill(Color.web("#008000"));
        }
        else {
            messageLabel.setText(message);
            messageLabel.setTextFill(Color.web("#FF0000"));
        }
    }
}
