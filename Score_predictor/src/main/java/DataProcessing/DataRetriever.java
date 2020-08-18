package DataProcessing;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class DataRetriever {

    public static ArrayList<Map<String,String>> scoresList;

    public static String checkFileURL(String file){
        try{
            URL url = new URL(file);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                return null;
            }
            else {
                return "Połączenie z plikiem źródłowym nie zostało nawiązane poprawnie ";
            }
        }
        catch (MalformedURLException malformedURLException){
            return "Niepoprawna ścieżka pliku źródłowego!";
        }
        catch (IOException exception) {
            if (file.contains("http")){
                return "Błąd połączenia ze stroną z plikiem źródłowym";
            }
            else {
                try (InputStream ignored = new URL(file).openStream()){
                    return null;
                }
                catch (Exception e){
                    return "Błąd otworzenia pliku źródłowego";
                }
            }
        }
    }

    public static ArrayList<Map<String,String>> getMatchStatistics(String url){

        try (BufferedReader input = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {

            String[] headers = input.readLine().split(",");
            ArrayList<Map<String, String>> listOfMatchStatistics = new ArrayList<>();
            String nextLine = "";
            while((nextLine = input.readLine()) != null){
                String[] statistics = nextLine.split(",");
                Map<String,String> singleMatchStatistics = new HashMap<>();
                for(int k = 0; k< statistics.length; k++){
                    singleMatchStatistics.put(headers[k],statistics[k]);
                }
                listOfMatchStatistics.add(singleMatchStatistics);
            }
            return listOfMatchStatistics;
        }
        catch (IOException e) {

            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    public static HashSet<String> getTeamsSet(){
        HashSet<String> teamsSet = new HashSet<>();
        if (scoresList == null){
            return teamsSet;
        }
        for(Map<String,String> map : scoresList){
            teamsSet.add(map.get("HomeTeam"));
        }
        return teamsSet;
    }
}
