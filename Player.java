//Rahul Kolla - CS 2336.002
//Problem and Design Analysis: This class is objective is to control the player logic within our class - the ultimate class has a more extensive description of this

import java.util.*;
//our player class which controls the player and the inital outputs
public class Player {
    //intialization of the player character
    private char player;
    //board selected for the AI or the player
    private int boardSelected;
    //square selected for the AI or the player
    private int squareSelected;
    //scanner object to read in the inputs
    private Scanner input;
    //constructor that initalizes the player and the scanner object
    public Player() {
        player = 'X';
        input = new Scanner(System.in);
    }
    //returns the player character
    public char getPlayer(){
        return player;
    }
    //changes the player character from the 'X' to 'O' or vice versa
    public char changePlayer() {
        if(player == 'X')
            player = 'O';
        else
            player = 'X';
    //returns the player
        return player;
    }
    //for our first human pass we try to get the board and make sure it is within the bounds of 0 to 8
    public int humanFirstPassBoard() {
        System.out.println("\nCurrent Player is : " + player);
        do
        {
            System.out.print("Please select a valid board: ");
            boardSelected = humanInput();
            if(boardSelected > 8 || boardSelected < 0)
                System.out.println("Please select a valid board (0-8)");
        }while(boardSelected < 0 || boardSelected > 8);
        System.out.println("Selected Board : " + boardSelected);
        return boardSelected;
    }
    //for the square we make sure its valid input before outputing it as the square selected
    public int humanPassSquare() {
        do {
            System.out.print("Please select a valid square on the selected board: ");
            squareSelected = humanInput();
            if(squareSelected > 8 || squareSelected < 0)
                System.out.println("Please select a valid square (0-8)");
        }while(squareSelected < 0 || squareSelected > 8);
        System.out.println("Selected Square : " + squareSelected);

        return squareSelected;
    }
    //generic human input which we return (stored as square selected)
    public int humanInput() {
        squareSelected = input.nextInt();
        return squareSelected;
    }
    //this is for our computer first past board (for AI vs AI)
    public int computerFirstPassBoard() {
        System.out.println("\nCurrent Player is : " + player);
        System.out.println("Please select a valid board: ");
        boardSelected = computerPassSquare();
        System.out.println("Selected Board : " + boardSelected);

        return boardSelected;
    }
    //this is for most of the AI input which is just a random generator
    public int computerPassSquare() {
        squareSelected = (int)(Math.random() * 9);
        return squareSelected;
    }
}
