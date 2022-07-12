package com.kenzie.app;

// import necessary libraries

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import static com.kenzie.app.CustomHttpClient.sendGET;

/* TODO
   Create an event loop so the user can play multiple times
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


            //Pre game info explaining the game system
            System.out.println("Hello! Welcome to the \"Restless Neuron Game!\"" + "\n");
            System.out.println("The game consists in answering as many questions correctly.");
            System.out.println("For every question that is correct you get a point, for a total of 10 questions." + "\n" +
                    "Lets see what you got. Ready?");
            System.out.println("Type Y to continue");
            scanner.nextLine();
            System.out.println("First Question");

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
                        if(answer.isEmpty() || answer.isBlank()){
                            throw new RuntimeException();
                        }
                    }catch (RuntimeException e){
                        counter--;
                        randomId--;
                        System.out.println( "Please enter a valid answer!"+ "\n");
                    }

                   if (answer.equalsIgnoreCase(clue.getAnswer())) {
                       System.out.println("Correct! You get a point!");
                       points++;
                       System.out.println("Points: " + points + "\n");
                   } else if(!answer.equalsIgnoreCase(clue.getAnswer()) && !answer.isEmpty() && !answer.isBlank()){
                        System.out.println("Wrong answer. The correct answer is: " + clue.getAnswer() + "\n");
                        System.out.println("Points: " + points);
                    }
                    randomId++;

                }
                if (counter == 10) {
                    break;
                }
            }
            if (points > 7) {
                System.out.println("You got a total of: " + points + " points");
                System.out.println("Good Job Einstein!");
            } else {
                System.out.println("You got a total of: " + points + " points");
                System.out.println("Better luck next time :(");
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


