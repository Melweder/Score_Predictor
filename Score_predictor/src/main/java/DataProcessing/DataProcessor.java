package DataProcessing;

import java.util.ArrayList;
import java.util.Map;
import org.apache.commons.math3.distribution.PoissonDistribution;

public class DataProcessor {
    static public double calculateTeamStrength(ArrayList<Map<String, String>> list, String teamName, String teamType, String goalsType, boolean isRelative){
        int numberOfAllGoals = 0;
        int numberOfTeamGoals = 0;
        int numberOfAllMatches = list.size();
        int numberOfTeamMatches = 0;
        for(Map<String,String> map : list){
            numberOfAllGoals += Integer.parseInt(map.get(goalsType));
            if(map.get(teamType).equals(teamName)){
                numberOfTeamGoals += Integer.parseInt(map.get(goalsType));
                numberOfTeamMatches++;
            }
        }
        if (!isRelative)
            return ((double)numberOfTeamGoals/numberOfTeamMatches)/((double)numberOfAllGoals/numberOfAllMatches);
        else
            return ((double)numberOfTeamGoals/numberOfTeamMatches);
    }
    static public double projectExpectedTeamGoals(ArrayList<Map<String, String>> list, String homeTeam, String awayTeam, boolean homeTeamResult){
        // Home team goals
        if (homeTeamResult)
            return calculateTeamStrength(list, homeTeam, findMatchingToken(TokenArrays.homeTeamTokens), findMatchingToken(TokenArrays.homeGoalsTokens), true) * calculateTeamStrength(list, awayTeam, findMatchingToken(TokenArrays.awayTeamTokens), findMatchingToken(TokenArrays.homeGoalsTokens), false);
        // Away team goals
        else
            return calculateTeamStrength(list, awayTeam, findMatchingToken(TokenArrays.awayTeamTokens), findMatchingToken(TokenArrays.awayGoalsTokens), true) * calculateTeamStrength(list, homeTeam, findMatchingToken(TokenArrays.homeTeamTokens), findMatchingToken(TokenArrays.awayGoalsTokens), false);
    }
    static public String findMatchingToken (String[] tokens){
        for(String header : DataRetriever.scoresList.get(0).keySet()){
            for(String token : tokens){
                if (token.equals(header)) {
                    return token;
                }
            }
        }
        return null;
    }
    static public String predictScore(ArrayList<Map<String,String>> list, String homeTeam, String awayTeam){

        PoissonDistribution distributionForHomeTeam = new PoissonDistribution(projectExpectedTeamGoals(list, homeTeam, awayTeam, true));
        PoissonDistribution distributionForAwayTeam = new PoissonDistribution(projectExpectedTeamGoals(list, homeTeam, awayTeam, false));
        int maxGoalsExpected = 8;
        double highestProbabilityForHomeTeam = 0;
        double highestProbabilityForAwayTeam = 0;
        int homeTeamScore = 0;
        int awayTeamScore = 0;
        for (int k = 0; k <= maxGoalsExpected; k++){
            if(distributionForHomeTeam.probability(k) > highestProbabilityForHomeTeam) {
                highestProbabilityForHomeTeam = distributionForHomeTeam.probability(k);
                homeTeamScore = k;
            }
            if(distributionForAwayTeam.probability(k) > highestProbabilityForAwayTeam){
                highestProbabilityForAwayTeam = distributionForAwayTeam.probability(k);
                awayTeamScore = k;
            }

        }
        return homeTeam + "," + awayTeam + "," + homeTeamScore + "," + awayTeamScore;
    }
}
