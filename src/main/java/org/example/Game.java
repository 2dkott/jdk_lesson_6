package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Game {

    private int roundsNumber;
    Random randomGen = new Random();
    int[] doors = {0,0,0};

    Map<Integer, Boolean> results = new HashMap<>();
    public Game(int roundsNumber) {
        this.roundsNumber = roundsNumber;
    }

    public void play() {
        int switchWins = 0;
        int stayWins = 0;

        for(int plays = 0;plays < roundsNumber;plays++ ){

            System.out.printf("%n<<<Game %s>>>%n", plays+1);

            int carPosition = randomGen.nextInt(3);
            int montyGoatPosition = randomGen.nextInt(3);
            int playerChoicePosition = randomGen.nextInt(3);

            while(montyGoatPosition==carPosition || montyGoatPosition==playerChoicePosition) {
                montyGoatPosition = randomGen.nextInt(3);
            }

            System.out.printf("Car Position %s; Goat Positon %s, Player first choice %s %n", carPosition, montyGoatPosition, playerChoicePosition);

            boolean changeChoice = randomGen.nextBoolean();
            System.out.println("Game started");
            if (changeChoice) {

                for(int secondChoice = 0; secondChoice < 3; secondChoice++) {
                    if (secondChoice!=playerChoicePosition && secondChoice!=montyGoatPosition) {
                        playerChoicePosition=secondChoice;
                        System.out.printf("Player decided to change his choice to %s%n", playerChoicePosition);
                        break;
                    }
                }
            }

            if (playerChoicePosition==carPosition) {
                results.put(plays, true);
                System.out.println("Player wins");
            }
            else {
                results.put(plays, false);
                System.out.println("Player fails");
            }
        }
    }

    public void calResults() {
        int winCount = (int)results.entrySet().stream().filter(Map.Entry::getValue).count();
        System.out.printf("Games %s. Wins %s, Success %s", roundsNumber, winCount, winCount*100/roundsNumber);
    }
}
