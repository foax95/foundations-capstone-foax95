package com.kenzie.app;

// import necessary libraries

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import static com.kenzie.app.CustomHttpClient.sendGET;

/* TODO
   Implement a two players mode
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

    public static void main(String[] args){
        //Write main execution code here

        //Declarations of variables to track points and counter for the for loop
        try {
            int points = 0;
            int counter = 0;
            //Declaration of random and scanner
            Random random = new Random();
            Scanner scanner = new Scanner(System.in);
            int randomId = random.nextInt(101);

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
                for (Clue clue : mapper(URL).getClues()) {
                    while (clue.getId() == randomId) {
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
                            randomId--;
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
                        randomId++;

                    }
                    if (counter == 10) {
                        break;
                    }
                }
                //Shows total points and gives a message
                    if (points > 7) {
                    System.out.println("You got a total of: " + points + " points" + "\n" +
                            "Good Job Einstein!");
                    } else {
                    System.out.println("You got a total of: " + points + " points" + "\n" +
                            "Better luck next time :("+ "\n");
                }
                //Ask a user to try again and finish or repeats the program
                    System.out.println(" Do you wish to try again?" + "\n" +
                        "Press Y to continue." + "\n" +
                        "Press Any key to end program." + "\n"
                        );
                    eventLoopBreak = scanner.nextLine();

                    if(!eventLoopBreak.equalsIgnoreCase("Y") || eventLoopBreak.isBlank()){
                        break;
                    } else if (eventLoopBreak.equalsIgnoreCase("Y")) {
                    counter = 0;
                    }
                }
            }catch (IOException e){
            e.printStackTrace();
         }
    }

        public static CluesDTO mapper(String URL) throws JsonProcessingException {
            String jsonString = sendGET(URL);
            ObjectMapper om = new ObjectMapper();
            return om.readValue(jsonString, CluesDTO.class);
        }
}


