package GUI;

import DataProcessing.DataRetriever;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;

public class ConfigWindowController {

    static private String homeTeam;
    static private String awayTeam;
    @FXML private ChoiceBox<String> homeTeamBox;
    @FXML private ChoiceBox<String> awayTeamBox;

    static public String getHomeTeam(){
        return homeTeam;
    }
    static public String getAwayTeam(){
        return awayTeam;
    }

    @FXML
    public void initialize() {
        homeTeamBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(s != null && !awayTeamBox.getItems().contains(s)){
                        awayTeamBox.getItems().add(s);
                }
                awayTeamBox.getItems().remove(t1);
            }
        });
        awayTeamBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(s != null && !homeTeamBox.getItems().contains(s)){
                    homeTeamBox.getItems().add(s);
                }
                homeTeamBox.getItems().remove(t1);
            }
        });
        for (String choice : DataRetriever.getTeamsSet()){
            homeTeamBox.getItems().add(choice);
            awayTeamBox.getItems().add(choice);
        }
    }

    @FXML
    void onApplyButtonAction(ActionEvent event) {
        if (!homeTeamBox.getSelectionModel().isEmpty() && !awayTeamBox.getSelectionModel().isEmpty()){
            homeTeam = homeTeamBox.getValue();
            awayTeam = awayTeamBox.getValue();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
    }

    @FXML
    void onBackButtonAction(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

}
