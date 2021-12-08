public class Ultimate {
    public static void main(String[] args) {
        
        int selection;
        Player player = new Player();
        do {
            System.out.println("1. AI vs AI ")' 
            System.out.println("2. Player vs AI");   
            System.out.println("3. Player vs Player ");  
            System.out.print("Please select a game to play: ");
            
            selection = player.humanInput();

            if(selection > 3 || selection < 1)
                System.out.println("Please select a valid game (1-3)");
            
        }while(selection < 1 || selection > 3);
        Game game = new Game(selection);
    }
}
