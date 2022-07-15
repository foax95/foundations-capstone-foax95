package com.kenzie.app;

// import necessary libraries

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.*;

import static com.kenzie.app.CustomHttpClient.sendGET;

/* TODO
   Create a time limit to answer questions
   */
public class Main {
    /* Java Fundamentals Capstone project:
       - Define as many variables, properties, and methods as you decide are necessary to
       solve the program requirements.
       - You are not limited to only the class files included here
       - You must write the HTTP GET call inside the CustomHttpClient.sendGET(String URL) method
         definition provided
       - Your program execution must run from the main() method in Main.java
       - The rest is up to you. Good luck and happy coding!

     */
    public static final String URL = "https://jservice.kenzie.academy/api/clues";

    public static void main(String[] args) {
        //Write main execution code here
        Scanner menu = new Scanner(System.in);
        System.out.println(getRandomNumber());
        System.out.println("Choose game mode"+ "\n"+
                "Single player / Multiplayer");
        String menuText = menu.nextLine();

        if(menuText.equalsIgnoreCase("single player")|| menuText.equalsIgnoreCase("single")){
            singlePlayer();
        } else if (menuText.equalsIgnoreCase("multiplayer")|| menuText.equalsIgnoreCase("multi")) {
            System.out.println("Enter your name player 1");
            String playerOne = menu.nextLine();
            Player player1 = new Player(playerOne,0);
            System.out.println("Enter your name player 2");
            String playerTwo = menu.nextLine();
            Player player2 = new Player(playerTwo,0);

            multiPlayer(player1);
            multiPlayer(player2);
            if(player1.getPoints() > player2.getPoints()){
                System.out.println(player1.getName() + " Wins!!!");
            }else if(player2.getPoints() > player1.getPoints()) {
                System.out.println(player2.getName() + " Wins!!!");
            } else if (player1.getPoints() == player2.getPoints()) {
                System.out.println("Is a Tie!!!");
            }
        }

    }

    public static CluesDTO mapper(String URL) throws JsonProcessingException {
        String jsonString = sendGET(URL);
        ObjectMapper om = new ObjectMapper();
        return om.readValue(jsonString, CluesDTO.class);
    }

    public static void singlePlayer() {
        try {
            //Declarations of variables to track points and counter for the for loop
            int points = 0;
            int counter = 0;
            //Declaration of random and scanner
            Scanner scanner = new Scanner(System.in);


            String eventLoopBreak = "Y";
            while (eventLoopBreak.equalsIgnoreCase("Y")) {

                //Pre game info explaining the game system
                System.out.println(
                        "Hello! Welcome to the \"Restless Neuron Game\"" + "\n" +
                                "The game consists in answering as many questions correctly." + "\n" +
                                "For every question that is correct you get a point, for a total of 10 questions." + "\n" +
                                "Lets see what you got. Ready?" + "\n"
                );
                System.out.println("Press Any key to continue");
                scanner.nextLine();
                System.out.println("First Question:" + "\n");

                //Main execution of program using loops and scanners
                while (counter < 10) {
                    int rand = getRandomNumber();
                    for (Clue clue : mapper(URL).getClues()) {
                        if (clue.getId() == rand) {
                            System.out.println(rand);
                            System.out.println(counter);
                            System.out.println("Category: " + clue.getCategory().getTitle() + "\n"
                                    + "Question: " + clue.getQuestion() + "\n");
                            System.out.println("Type your answer");
                            String answer = scanner.nextLine();
                            counter++;
                            //Try/catch to check that string is not empty or white spaces also repeats the same question until a valid answer is entered
                            try {
                                if (answer.isEmpty() || answer.isBlank()) {
                                    throw new RuntimeException();
                                }
                            } catch (RuntimeException e) {
                                counter--;
                                System.out.println("Please enter a valid answer!" + "\n");
                            }

                            if (answer.equalsIgnoreCase(clue.getAnswer())) {
                                System.out.println("Correct! You get a point!");
                                points++;
                                System.out.println("Points: " + points + "\n");
                            } else if (!answer.equalsIgnoreCase(clue.getAnswer()) && !answer.isEmpty() && !answer.isBlank()) {
                                System.out.println("Wrong answer. The correct answer is: " + clue.getAnswer() + "\n");
                                System.out.println("Points: " + points);
                            }

                            if (counter == 10) {
                                break;
                            }
                        }
                    }
                }
                //Shows total points and gives a message
                if (points > 7) {
                    System.out.println("You got a total of: " + points + " points" + "\n" +
                            "Good Job Einstein!");
                } else {
                    System.out.println("You got a total of: " + points + " points" + "\n" +
                            "Better luck next time :(" + "\n");
                }
                //Ask a user to try again and finish or repeats the program
                System.out.println("Do you wish to try again?" + "\n" +
                        "Press Y to continue." + "\n" +
                        "Press Any key to end program." + "\n"
                );
                eventLoopBreak = scanner.nextLine();

                if (!eventLoopBreak.equalsIgnoreCase("Y") || eventLoopBreak.isBlank()) {
                    break;
                } else if (eventLoopBreak.equalsIgnoreCase("Y")) {
                    counter = 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void multiPlayer(Player player) {
        try {
            //Declarations of variables to track points and counter for the for loop
            int points = 0;
            int counter = 0;
            //Declaration of scanner
            Scanner scanner = new Scanner(System.in);


            String eventLoopBreak = "Y";
            while (eventLoopBreak.equalsIgnoreCase("Y")) {

                //Pre game info explaining the game system
                System.out.println(
                        "Hello! " + player.getName() + " Welcome to the \"Restless Neuron Game\"" + "\n" +
                                "The game consists in answering as many questions correctly." + "\n" +
                                "For every question that is correct you get a point, for a total of 10 questions." + "\n" +
                                "Lets see what you got. Ready?" + "\n"
                );
                System.out.println("Press Any key to continue");
                scanner.nextLine();
                System.out.println("First Question:" + "\n");

                //Main execution of program using loops and scanners
                while (counter < 10) {
                    int rand = getRandomNumber();
                    for (Clue clue : mapper(URL).getClues()) {
                        if (clue.getId() == rand) {
                            System.out.println(counter);
                            System.out.println("Category: " + clue.getCategory().getTitle() + "\n"
                                    + "Question: " + clue.getQuestion() + "\n");
                            System.out.println("Type your answer");
                            String answer = scanner.nextLine();
                            counter++;

                            //Try/catch to check that string is not empty or white spaces also repeats the same question until a valid answer is entered
                            try {
                                if (answer.isEmpty() || answer.isBlank()) {
                                    throw new RuntimeException();
                                }
                            } catch (RuntimeException e) {
                                counter--;
                                System.out.println("Please enter a valid answer!" + "\n");
                            }

                            if (answer.equalsIgnoreCase(clue.getAnswer())) {
                                System.out.println("Correct! You get a point!");
                                points++;
                                System.out.println("Points: " + points + "\n");
                            } else if (!answer.equalsIgnoreCase(clue.getAnswer()) && !answer.isEmpty() && !answer.isBlank()) {
                                System.out.println("Wrong answer. The correct answer is: " + clue.getAnswer() + "\n");
                                System.out.println("Points: " + points);
                            }

                            if (counter == 10) {
                                break;
                            }
                        }
                    }
                }
                    player.setPoints(points);
                    //Shows total points and gives a message
                    System.out.println("You got a total of: " + player.getPoints());
                    //Ask a user to try again and finish or repeats the program
                    System.out.println("Press Any key to end program." + "\n");
                    eventLoopBreak = scanner.nextLine();
                    if (!eventLoopBreak.equalsIgnoreCase("y")) {
                        break;
                    }
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static int getRandomNumber(){
        ArrayList<Integer> list = new ArrayList<>();
        int result = 0;
        Random random = new Random();
        int rand = random.nextInt(100);
        for(int i = 1 ; i < 101; i++){
            if(!list.contains(rand)){
                list.add(i);
                Collections.shuffle(list);
            } else{
                Collections.shuffle(list);
            }
            for(int j = 0; j<list.size(); j++){
                Collections.shuffle(list);
                result = list.get(j);
           }
        }
        return result;
    }
}


