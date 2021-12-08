//Rahul Kolla - CS 2336.002
//Problem and Design Analysis: This class is objective is to control the game logic within our class - the ultimate class has a more extensive description of this
import java.util.*;
//Our game class which handles all of our game logic and actually executes the game
public class Game {
    //Controls our board of 9 Boards
    private ArrayList<Board> boardMaster;
    //Controls the winners of each board
    private ArrayList<Character> boardWinners;
    //This is the board selected by either human or computer player
    private int boardSelected;
    //This is the square selected by either human or computer player
    private int squareSelected;
    //Our player character which is controlled by the player object
    private char playerChar;
    //This is the input validation condition
    private boolean validInput;
    //This is our game continue condition
    private boolean gameContinue;
    //Our player object to access the player methods
    private Player player;
    //Our integer that the user inputted which was passed into the constructor for game
    private int selection;
    //Our game constructor with the passed parameter of the selected game
    public Game(int selection) {
        //Initalization of our private variables
        player = new Player();
        boardMaster = new ArrayList<>(9);
        boardWinners = new ArrayList<>(9);
        playerChar = player.getPlayer();
        validInput = true;
        gameContinue = true;
        this.selection = selection;
        //Initalization of our master board with 9 board objects
        for(int i = 0; i < 9; i++) {
            Board temp = new Board();
            boardMaster.add(temp);
        }
        //Initalization of our board winners with empty spaces
        for(int i = 0; i < 9; i++)
            boardWinners.add(' ');
        //Calling our play game method so it executes the selected game
        playGame(selection);
    }
    //Play game
    private void playGame(int selection) {
        System.out.println("\n===== WELCOME TO THE ULTIMATE TIC-TAC-TOE GAME!! =====");
        //We print our initial array
        printArray();
        //This is for the first pass of our game when the player is selected
        //If AI vs AI, we need to output computer specific output
        if(selection == 1) {
            boardSelected = player.computerFirstPassBoard();
            System.out.println("Please select a valid square on the selected board: ");
            squareSelected = player.computerPassSquare();
            System.out.println("Selected Square : " + squareSelected);
        }
        //If it is Player vs AI or Player vs Player, we need player specific output first
        if(selection == 2 || selection == 3) {
            boardSelected = player.humanFirstPassBoard();
            squareSelected = player.humanPassSquare();
        }
        //Setting the specific board and square equal to 'X' which symbolizes our player 1 and then printing out our master board
        boardMaster.get(boardSelected).boardSet(squareSelected, playerChar);
        printArray();
        //We then change our player symbolizing the next players turn
        playerChar = player.changePlayer();
        //We print out the new player turn, set our board equal to the square, and display the selected board
        System.out.println("\nCurrent Player is : " + playerChar);
        boardSelected = squareSelected;
        System.out.println("Selected Board : " + boardSelected);
        //This is the second pass of our game which we also determine the flow of the game
        //E.g if it is AI vs AI or Player vs AI, every other pass will be an AI playing
        do {
            //We first request for a valid square and present the available moves to the AI before reading in its choice
            if(selection == 1 || selection == 2) {
                System.out.println("Please select a valid square on the selected board: ");
                boardMaster.get(boardSelected).movesAvailable();
                squareSelected = player.computerPassSquare();
                System.out.println("Selected Square : " + squareSelected);
            }
            //If it is a human, we present the moves available and read in the human's choice
            if(selection == 3) {
                boardMaster.get(boardSelected).movesAvailable();
                squareSelected = player.humanPassSquare();
                if(boardMaster.get(boardSelected).getChar(squareSelected) == 'X' || boardMaster.get(boardSelected).getChar(squareSelected) == 'O')
                    System.out.println("Please Try Again! Pick a valid square.\n");
            }
            //Continue to do this until valid input is selected
        }while(boardMaster.get(boardSelected).getChar(squareSelected) == 'X' || boardMaster.get(boardSelected).getChar(squareSelected) == 'O');
        //Then we set the board accordingly and print the array
        boardMaster.get(boardSelected).boardSet(squareSelected, playerChar);
        printArray();
        //It returns to our player 'X'
        playerChar = player.changePlayer();
        //This is the main game logic
        do {
            //If it is AI vs AI, we print out computer output
            if(selection == 1)
                computerOutput();
            //Otherwise we print out human output
            if(selection == 2 || selection == 3)
                humanOutput();
            //If any of our exit game conditions are true, we exit the game and as a failsafe set the while loop to false
            if(exitGame()) {
                gameContinue = false;
                break;
            }
            //If our board is full and no winner has been selected, we exit the program and declare it as a tie
            if(boardIsFull(boardMaster)) {
                gameContinue = false;
                break;
            }
            //Changing our player to  'O'
            playerChar = player.changePlayer();
            //If it a AI vs AI or Player vs AI, we print out computer output
            if(selection == 1 || selection == 2)
                computerOutput();
            //Otherwise we print out player output
            if(selection == 3)
                humanOutput();
            //If any of our exit game conditions are true we exit the program, and set our failsafe to false
            if(exitGame()) {
                gameContinue = false;
                break;
            }
            //If it is a tie, we exit the program and declare it as a tie
            if(boardIsFull(boardMaster)) {
                gameContinue = false;
                break;
            }
            //Changing our program back to 'X'
            playerChar = player.changePlayer();
        //While game continue is false
        }while(gameContinue);
        //If our program determines a gameWinner, we output game winner with the player who won
        if(gameWinner(playerChar, boardWinners))
            System.out.println("\ngame winner is : " + playerChar);
        //Otherwise, we output a tie
        if((boardIsFull(boardMaster) && !gameWinner(playerChar, boardWinners)) || (isFullWinners(boardWinners) && !gameWinner(playerChar, boardWinners)))
            System.out.println("\ngame winner is : T");
    }
    //Our print array function which prints our master board
    private void printArray() {
        //Prints the board in intervals of three printing it out row by row
        for(int i = 0; i < 9; i+=3) {
            System.out.print("	BOARD#"+ i + " | " + boardMaster.get(i).getChar(0)  + " | " +  boardMaster.get(i).getChar(1)  + " | " +  boardMaster.get(i).getChar(2)  + " |	");
            System.out.print("BOARD#" + (i+1) + " | " +  boardMaster.get(i+1).getChar(0) + " | " +  boardMaster.get(i+1).getChar(1) + " | " +  boardMaster.get(i+1).getChar(2) + " |	");
            System.out.println("BOARD#" + (i+2) + " | " +  boardMaster.get(i+2).getChar(0) + " | " + boardMaster.get(i+2).getChar(1) + " | " + boardMaster.get(i+2).getChar(2) + " |	");

            System.out.print("	BOARD#" + i + " | " +  boardMaster.get(i).getChar(3)  + " | " +  boardMaster.get(i).getChar(4)  + " | " +  boardMaster.get(i).getChar(5)  + " |	");
            System.out.print("BOARD#" + (i+1) + " | " +  boardMaster.get(i+1).getChar(3) + " | " +  boardMaster.get(i+1).getChar(4) + " | " +  boardMaster.get(i+1).getChar(5) + " |	");
            System.out.println("BOARD#" + (i+2) + " | " + boardMaster.get(i+2).getChar(3) + " | " + boardMaster.get(i+2).getChar(4) + " | " + boardMaster.get(i+2).getChar(5) + " |	 ");

            System.out.print("	BOARD#" + i + " | " +  boardMaster.get(i).getChar(6)  + " | " +  boardMaster.get(i).getChar(7)  + " | " +  boardMaster.get(i).getChar(8)  + " |	");
            System.out.print("BOARD#" + (i+1) + " | " +  boardMaster.get(i+1).getChar(6) + " | " +  boardMaster.get(i+1).getChar(7) + " | " +  boardMaster.get(i+1).getChar(8) + " |	");
            System.out.println("BOARD#" + (i+2) + " | " + boardMaster.get(i+2).getChar(6) + " | " + boardMaster.get(i+2).getChar(7) + " | " + boardMaster.get(i+2).getChar(8) + " |	 ");
        }
        //We print out the boardwinners as well here, making sure it is in order
        for(int i = 0; i < 9; i++) {
            if(boardWinners.get(i) != ' ')
                System.out.println("The Board#" + i + " winner is " + boardWinners.get(i));
        }
    }
    //Our game winner method to check the board winners list
    //This mimics our board winner class but is a step above because its keeping track of the won boards
    //Its logic mimics it:
    /*
        If the zeroth, first, and second index of the board is equal to the player, then that board is secured by that player (First Row)
        If the third, fourth, and fifth index of the board is equal to the player, then that board is secured by that player (Second Row)
        If the sixth, seventh, and eighth index of the board is equal to the player, then that board is secured by that player (Third Row)

        If the zeroth, first, and second index of the board is equal to the player, then that board is secured by that player (First Column)
        If the third, fourth, and fifth index of the board is equal to the player, then that board is secured by that player (Second Column)
        If the sixth, seventh, and eighth index of the board is equal to the player, then that board is secured by that player (Third Column)

        If the zeroth, fourth, and eighth index of the board is equal to the player, then that board is secured by that player (First Diagonal)
        If the second, fourth, and eigth index of the board is equal to the player, then that board is secured by that player (Second Diagonal)
     */
    private boolean gameWinner(char player, ArrayList<Character> boardWinners) {
        if(boardWinners.get(0) == player && boardWinners.get(1) == player && boardWinners.get(2) == player)
            return true;
        if(boardWinners.get(3) == player && boardWinners.get(4) == player && boardWinners.get(5) == player)
            return true;
        if(boardWinners.get(6) == player && boardWinners.get(7) == player && boardWinners.get(8) == player)
            return true;
        if(boardWinners.get(0) == player && boardWinners.get(3) == player && boardWinners.get(6) == player)
            return true;
        if(boardWinners.get(1) == player && boardWinners.get(4) == player && boardWinners.get(7) == player)
            return true;
        if(boardWinners.get(2) == player && boardWinners.get(5) == player && boardWinners.get(8) == player)
            return true;
        if(boardWinners.get(0) == player && boardWinners.get(4) == player && boardWinners.get(8) == player)
            return true;
        if(boardWinners.get(2) == player && boardWinners.get(4) == player && boardWinners.get(6) == player)
            return true;
        return false;
    }
    //Our exit game functions
    private boolean exitGame() {
        //if it is a game winner, it exits the program
        if(gameWinner(playerChar, boardWinners))
            return true;
        //if our board is full, it exits the program for further evaluation
        if(isFullWinners(boardWinners))
            return true;
        //Otherwise it is false
        else
            return false;
    }
    //Controls the human output
    private void humanOutput() {
        //outputs the current player
        do {
            System.out.println("\nCurrent Player is : " + playerChar);
            //checks for valid input, otherwise need to retry the loop
            if(validInput)
                boardSelected = squareSelected;
            //If our board is full, output that the board is full and allows our user to pick a new board
            if(boardMaster.get(boardSelected).isFull()) {
                //Program continues doing this until a valid board is selected
                do {
                    do {
                        System.out.println("Board is Full!");
                        System.out.print("Please select a valid board: ");
                        //Calls our human input function to gain user input and checks for it being in between the interval of 0-8
                        boardSelected = player.humanInput();
                        if(boardSelected > 8 || boardSelected < 0)
                            System.out.println("Please select a valid board (0-8)");
                    }while(boardSelected < 0 || boardSelected > 8);
                }while(boardMaster.get(boardSelected).isFull());
            }
            //After user select a valid board, we output it
            System.out.println("Selected Board : " + boardSelected);
            //Display the possible moves the user can then take on said board and request for the user to select a square
            boardMaster.get(boardSelected).movesAvailable();
            squareSelected = player.humanPassSquare();
            //Now if it is a invalid square, the program prints an error message and set valid input to false, requiring the user to try again
            if(boardMaster.get(boardSelected).getChar(squareSelected) == 'X' || boardMaster.get(boardSelected).getChar(squareSelected) == 'O') {
                System.out.println("Please try again!");
                validInput = false;
            }
            //Otherwise the program sets the board accordingly and change valid input to true allowing the program to continue
            else {
                boardMaster.get(boardSelected).boardSet(squareSelected, playerChar);
                validInput = true;
            }
            //Now the program calls upon the board methods to check if the board is indeed a winner
            if(boardMaster.get(boardSelected).checkWinner(playerChar))
                //If the board has already been secured then, print the array
                if(boardWinners.get(boardSelected) != 'X' && boardWinners.get(boardSelected) != 'O')
                    //Otherwise, the program loads the winner into board winners and sets the remaining squares to astericks
                    boardWinner(boardMaster, boardSelected, playerChar, boardWinners);
            printArray();
        }while(!validInput);
    }
    //controls the computer output
    private void computerOutput() {
        //Output the player character and sets the board to square
        System.out.println("\nCurrent Player is : " + playerChar);
        boardSelected = squareSelected;
        //Now if the board is full, we first check if the board is full
        if(boardMaster.get(boardSelected).isFull()) {
            do {
                if(isFullWinners(boardWinners))
                    break;
                //otherwise we notify the AI that the board is full and regenerate a board number, and continue to do so until a valid board
                System.out.println("Board is Full!");
                boardSelected = (int)(Math.random() * 9);
            }while(boardMaster.get(boardSelected).isFull());
        }
        //Printing out the selected board and the selection of a valid square
        System.out.println("Selected Board : " + boardSelected);
        System.out.println("Please select a valid square on the selected board: ");
        //Print out valid modes to the AI
        boardMaster.get(boardSelected).movesAvailable();
        //Continue to read input until the program receives a valid square
        do {
            squareSelected = player.computerPassSquare();
        }while(boardMaster.get(boardSelected).getChar(squareSelected) == 'X' || boardMaster.get(boardSelected).getChar(squareSelected) == 'O');
        //print out the computer selected square and sets the board accordingly
        System.out.println("Selected Square : " + squareSelected);
        boardMaster.get(boardSelected).boardSet(squareSelected, playerChar);
        //If the AI wins, the program checks if the board has won, otherwise the program sets board winners accordingly and then prints array
        if(boardMaster.get(boardSelected).checkWinner(playerChar))
            if(boardWinners.get(boardSelected) != 'X' && boardWinners.get(boardSelected) != 'O')
                boardWinner(boardMaster, boardSelected, playerChar, boardWinners);
        printArray();
    }
    //if our program is a board winner, which the program accomplishs with the check winner method, the program sets all the remaining square to astericks
    private void boardWinner(ArrayList<Board> boardMaster, int boardSelected, char player, ArrayList<Character> boardWinners) {
        for(int i = 0; i < 9; i++) {
            if(boardMaster.get(boardSelected).getChar(i) != 'X' && boardMaster.get(boardSelected).getChar(i) != 'O') {
                boardMaster.get(boardSelected).boardSet(i, '*');
            }
        }
        //then we set the specific board in board winners as the player character that has won it
        boardWinners.set(boardSelected, player);
    }
    //this checks if our board winners list is filled - if it is, the program returns true
    //Used to check for ties because if the board winners list is full then the program needs to return a tie or a winner
    private boolean isFullWinners(ArrayList<Character> boardWinners) {
        boolean check = true;
        for(int i = 0; i < 9; i++) {
            if(boardWinners.get(i) != 'X' && boardWinners.get(i) != 'O')
                check = false;
        }
        return check;
    }
    //Last condition for checking if there is a tie - if the board is full then the game board is totally full and the program needs to output a tie or a winner
    private boolean boardIsFull(ArrayList<Board> boardMaster) {
        int counter = 0;
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
                //the program counts the number of x's and o's and if it equals 81 then the entire board is full
                if(boardMaster.get(i).getChar(j) == 'X' || boardMaster.get(i).getChar(j) == 'O')
                    counter++;

        if(counter == 81)
            return true;
        else
            return false;
    }
}
