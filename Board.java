//Rahul Kolla - CS 2336.002
//Problem and Design Analysis: This class is objective is to control the board logic within our class - the ultimate class has a more extensive description of this
import java.util.ArrayList;
//Our board class which constructs the boardMaster or our 3x3 boards
public class Board {
    //This is an individual entity board
    private ArrayList<Character> board;
    //Our board constructor which allows us to initalize our boards
    public Board() {
        //Which becomes a board with a capacity of 9 or 3x3 space
        /*
            It would be mapped as such:
                                           0 1 2
            Board: 0 1 2 3 4 5 6 7 8  ->   3 4 5
                                           6 7 8
         */
        this.board = new ArrayList<>(9);
        //Initalizing our boards with the numbers from 0 to 8
        for(int i = 0; i < 9; i++)
            board.add((char) (i + '0'));
    }
    //Game uses this to modify a specific index of our board with a player character
    public void boardSet(int index, char player) {
        board.set(index, player);
    }
    //Game uses this to return a specific character at an index of a specific board
    public char getChar(int index) {
        return board.get(index);
    }
    //We use this method to check if the board is full
    public boolean isFull() {
        //We check if the board is filled with 'X' and 'O'
        boolean check = true;
        for(int i = 0; i < 9; i++) {
            if(board.get(i) != 'X' && board.get(i) != 'O')
                check = false;
        }
        return check;
    }
    //Checks a winner within a specific board
    //At a specific board, for a specific player
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
    public boolean checkWinner(char player) {
        if (board.get(0) == player && board.get(1) == player && board.get(2) == player)
            return true;
        if (board.get(3) == player && board.get(4) == player && board.get(5) == player)
            return true;
        if (board.get(6) == player && board.get(7) == player && board.get(8) == player)
            return true;
        if (board.get(0) == player && board.get(3) == player && board.get(6) == player)
            return true;
        if (board.get(1) == player && board.get(4) == player && board.get(7) == player)
            return true;
        if (board.get(2) == player && board.get(5) == player && board.get(8) == player)
            return true;
        if (board.get(0) == player && board.get(4) == player && board.get(8) == player)
            return true;
        if (board.get(2) == player && board.get(4) == player && board.get(6) == player)
            return true;

        return false;
    }
    //Displays the list of possible legal moves for either user or the computer
    //Accomplishes this by checking if a specific square is occupied by either player
    //If it is not occupied, we output it as a legal possible move
    public void movesAvailable()
    {
        System.out.println("List of possible legal moves: ");
        //For loop to output possible moves by checking whether it is equal to either player
        for(int i = 0; i < 9; i++) {
            if(board.get(i) != 'X' && board.get(i) != 'O')
               System.out.print(i + " ");
        }
        //To clean up output to ensure it is not on the same line
        System.out.println("");
    }
}
