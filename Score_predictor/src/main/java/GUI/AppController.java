package GUI;

import DataProcessing.DataProcessor;
import DataProcessing.DataRetriever;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class AppController {

    @FXML
    private Label scoreLabel;
    @FXML
    private Label teamsLabel;
    @FXML
    private Label versionLabel;
    @FXML
    private BorderPane mainPane;


    private void setScoreLabel() {
        teamsLabel.setFont(new Font(48));
        teamsLabel.setText("Witaj!");
        scoreLabel.setFont(new Font(24));
        scoreLabel.setText("Wybierz plik źródłowy,\n a następnie wypełnij konfigurację!");
        versionLabel.setText("@Copyright Łukasz Wach");
    }

    @FXML
    public void initialize() {
        setScoreLabel();
    }

    @FXML
    void onPredictionButtonAction(ActionEvent event) {
        if (DataRetriever.scoresList != null) {
            if (ConfigWindowController.getAwayTeam() != null && ConfigWindowController.getHomeTeam() != null) {
                String[] results = DataProcessor.predictScore(DataRetriever.scoresList, ConfigWindowController.getHomeTeam(), ConfigWindowController.getAwayTeam()).split(",");
                teamsLabel.setFont(new Font(24));
                teamsLabel.setText(results[0] + " vs. " + results[1]);
                scoreLabel.setFont(new Font(96));
                scoreLabel.setText(results[2] + " : " + results[3]);
            } else {
                teamsLabel.setFont(new Font(48));
                teamsLabel.setText("");
                scoreLabel.setFont(new Font(24));
                scoreLabel.setText("Uzupełnij konfigurację!");
            }
        }

    }

    @FXML
    void onConfigButtonAction(ActionEvent event) {
        try {
            Stage configWindowStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/config_window_setting.fxml"));
            configWindowStage.setTitle("Konfiguracja");
            configWindowStage.setScene(new Scene(root));
            configWindowStage.setResizable(false);
            configWindowStage.initModality(Modality.WINDOW_MODAL);
            configWindowStage.initOwner(mainPane.getScene().getWindow());
            configWindowStage.show();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @FXML
    void onSourceButtonAction(ActionEvent event) {
        try {
            Stage sourceWindowStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/source_window_setting.fxml"));
            sourceWindowStage.setTitle("Źródło");
            sourceWindowStage.setScene(new Scene(root));
            sourceWindowStage.setResizable(false);
            sourceWindowStage.initModality(Modality.WINDOW_MODAL);
            sourceWindowStage.initOwner(mainPane.getScene().getWindow());
            sourceWindowStage.show();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

}
