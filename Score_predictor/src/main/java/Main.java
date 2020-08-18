import DataProcessing.DataRetriever;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Map;

public class Main extends Application {

    public static void main(String[] args) {
        /*DataProcessing.DataRetriever dataRetriever = new DataProcessing.DataRetriever();
        ArrayList<Map<String,String>> list = dataRetriever.getMatchStatistics("https://www.football-data.co.uk/mmz4281/1920/E0.csv");
        DataProcessing.DataProcessor dataProcessor = new DataProcessing.DataProcessor();
        System.out.println(dataProcessor.predictScore(list,"Man United", "Liverpool"));*/
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/app_setting.fxml"));
        stage.setTitle("Score Predictor");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }
}
