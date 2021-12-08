//Rahul Kolla
//CS 2336: Professor Borazjany
//Section Number: 002
//Program Analysis:
/*
    The objective of the program is to design an ultimate Tic-Tac-Toe game that contains three
    modes: an AI vs AI, Player vs AI, and Player vs Player. This builds off of previous iterations
    where there was a singular board with a singular objective of obtaining a Tic-Tac-Toe. With ultimate
    Tic-Tac-Toe, we create 9 such boards and store them within one grand board, and try to achieve a tic-tac-toe
    on this larger board. This is to say a player must achieve three board victories in the traditional
    tic-tac-toe ways. After achieving three smaller victories, if it satisifes the conditions for tic-tac-toe,
    then we can declare that player as the winner of tic-tac-toe. Now this would require the usage of a storage
    structure that would hold 9 such boards as well as conditional logic to determine victory and valid input
    by any of the game players.
 */
//Program Design:
/*
    The program utilizes three main classes which is necessary to construct a Ultimate Tic Tac Toe game:
        - The Program need players in order to play the game and hence the player class. Contained within the player class:
            - The program has a player variable which we monitor and keep track of
            - The program manages the inputs from both human and computer players which we feed back into our game class
            - The program has the scanner object which we use to read in input from the human players
            - The player class then uses the math.random method to generate the integer range for the computer players
            - The class contains the first iterations of output for the human and computer objects
        - The Program needs to have a board for the players can play on
            - The program creates an ArrayList of characters to store the preliminary places of the game board
            - The program has methods to check on the state of the board which also allow us to control output when the game tries to access board logic
                - The program has an isfull method to check if a board is filled with player pieces to prevent invalid input
                - The program has a checkWinner method to check if the board contains a winner for the player that places a piece
                - The program has a boardSet method which allows the player to modify the index of the board they would like to place a piece
                - The program has a getChar method to return the character at the desired index of the player which is used ot mediate output
                - The program has a movesAvailable method to return potential moves for either the AI or Player
        - The program has a board and players, now they just need a game to play
            - The program has a player character to display whose turn it is
            - The program creates an ArrayList of boards which functions as our 3x3 or 9 boards to construct the ultimate tic tac toe board
            - The program has a game continue boolean conditional to check if the game should be continued to play
            - The program has a board selected and a square selected variable to control output and to check whether it is a valid square to play
            - The game is passed a selection of user input to determine which game should be played
            - The game has a list of boardWinners to determine the victor of the game using standard tic tac toe logic
            - THe game has a player object to access the player conditional and control the preliminary logic
            - The program also has methods to control the state of the game
                - The program contains a call to start the game which is called within the constructor
                - The program contains standard outputs for human players and computer players
                - The program contains conditional logic to determine the victor of the game
                    - It checks whether there is a board winner, and if there is we merely set all the remaining characters into astericks and move the object into boardwinners
                    - It checks for ties with the is full winners method to check if the board winners list is full
                    - It checks for game winners by checking is the master board is full
                - It contains a master print method which allows us to output the master board
                - It contains an exit game method to control when the program should exit itself

 */
//Program Testing:
/*
    The program was first developed as an intial Player vs AI game because the outputs for both could be utilized to develop the other game modes.
    The way to test the AI method was rigorous - it involved testing the number generator by ensuring that it was outputing integers within the range,
    as well as ensuring that it followed the program logic. I first tested that it was able to change the board, and once it was able to do that
    it was merely a matter of ensuring that it followed the conditional logic. The hardest bug to fix for AI was if the board was completely filled, as in
    all 81 spaces were filled with player pieces, that it was to exit the program. The program resolved that with the boardIsFull method.
    The player class was a bit easier to test. It required countless runs to check if it was reading input correctly and actually modifying the board
    as intended. Once it did that, it was a matter of implementing conditional logic to prevent input invalidation and ensure that if the player won a board
    it would be stored in their name. Then once this was completed, it was a matter of duplicating the AI output twice for the
    AI vs AI and the player to result in the Player vs Player.
 */
public class Ultimate {
    public static void main(String[] args) {
        //Variable for user to select which game they would like to play
        int selection;
        //Calling the player object because we determine who plays with this selection
        Player player = new Player();
        //Do while loop to ensure valid input is selected
        do {
            //1 for the Selection of AI vs AI
            System.out.println("1. AI vs AI ");
            //2 for the Selection of Player vs AI
            System.out.println("2. Player vs AI");
            //3 for the Selection of Player vs Player
            System.out.println("3. Player vs Player ");
            //Reads in user input
            System.out.print("Please select a game to play: ");
            selection = player.humanInput();
            //If it is not, we ask the user to try again
            if(selection > 3 || selection < 1)
                System.out.println("Please select a valid game (1-3)");
        }while(selection < 1 || selection > 3);
        //We load in that game with the selection as our input for the game
        Game game = new Game(selection);
    }
}
