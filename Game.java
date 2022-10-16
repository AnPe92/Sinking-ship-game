import java.util.*;

public class Game {

    List<Ship> ships = List.of(new Ship("Abba", 2, 1), new Ship("Bebba", 3,2 ));
    Scanner scanner = new Scanner(System.in);
    void game(boolean running, GameBoard gameBoard){



        //Game menu
        while(running){
            System.out.println("Welcome to Anton's sinking ship game! \n Make a choice \n [1] play a new game vs ai \n [2] play a game vs a friend (local coop) \n [3] Scorboard \n[4] exit program");
            int userInput = scanner.nextInt();
            switch (userInput){
                case    1:
                    System.out.println("Not yet implemented");
                    break;
                case    2:
                    System.out.println("Lets play local coop!");
                    localCoop();
                    break;
                case    3:
                    running = false;
                    break;
            }
        }
    }
    private void localCoop() {

        //Initialize player maps
        char[][] player1Map = new char[10][10];
        char[][] player2Map = new char[10][10];

        for (int i = 0; i <10 ; i++) {
            for (int j = 0; j <10 ; j++) {
                player1Map[i][j] = 'W';
                player2Map[i][j] = 'W';
            }
        }

        System.out.println("Player one enter your name: ");
        scanner.nextLine();

        String playerName = scanner.nextLine().trim();
        Player player1 = new Player(playerName);
        placeShipsLocalCoop(player1, player1Map);

        System.out.println("Player two enter your name: ");
        playerName = scanner.nextLine().trim();
        Player player2 = new Player(playerName);
        placeShipsLocalCoop(player2, player2Map);

        startGame(player1, player2, player1Map, player2Map);
    }

    private void startGame(Player player1, Player player2, char[][] player1Map, char[][] player2Map) {

        boolean playing = true;
        int playerOneScore =0;
        int playerTwoScore =0;
        int y = 0;
        int x = 0;

        while(playing) {

            boolean playerOneTurn = true;
            boolean playerTwoTurn = true;
            if (playerOneScore == 17) {
                System.out.println("Game over, Winner is " + player1.getName());
                playing = false;
                break;
            } else if (playerTwoScore == 17) {
                System.out.println("Game over, Winner is " + player2.getName());
                playing = false;
                break;
            }

            System.out.println("Time to play!");
            while(playerOneTurn) {
                printMap(player2Map, true);
                System.out.println(player1.getName() + "'s move, please enter Y coordinate: ");
                y = scanner.nextInt();
                System.out.println("Enter X coordinate: ");
                x = scanner.nextInt();
                if (player2Map[y][x] == 'S') {
                    System.out.println("HIT");
                    playerOneScore++;
                    player2Map[y][x] = 'X';
                    playerOneTurn = false;
                } else if (player2Map[y][x] == 'X') {
                    System.out.println("you have already bombed this area please choose another spot.");
                } else {
                    player2Map[y][x] = 'X';
                    System.out.println("MISS");
                    playerOneTurn = false;
                }
            }

            while(playerTwoTurn) {
                System.out.println(player2.getName() + "'s move");
                printMap(player1Map, true);
                System.out.println( " please enter Y coordinate: ");
                try{
                    y = scanner.nextInt();
                } catch (InputMismatchException e){
                    System.err.println("Please use numbers!");
                    scanner.nextLine();
                }

                System.out.println("Enter X coordinate: ");
                x = scanner.nextInt();
                if (player1Map[y][x] == 'S') {
                    playerTwoScore++;
                    player1Map[y][x] = 'X';
                    playerTwoTurn = false;
                } else if (player1Map[y][x] == 'X') {
                    System.out.println("you have already bombed this area please choose another spot.");
                } else {
                    player1Map[y][x] = 'O';
                    playerTwoTurn = false;
                }
            }
        }
    }

    private void printMap(char [][] map, boolean playing) {

        String mapBuilder = "  0 1 2 3 4 5 6 7 8 9\n";
        if(playing) {

            for (int i = 0; i < map.length; i++) {
                mapBuilder = mapBuilder + i;
                for (int j = 0; j < map.length; j++) {
                    if (map[i][j] == 'S') {
                        mapBuilder = mapBuilder + " " + "W";
                    } else {
                        mapBuilder = mapBuilder + " " + map[i][j];
                    }
                    if (j == 9) {
                        mapBuilder = mapBuilder + "\n";
                    }
                }
            }
        } else {
            for (int i = 0; i < map.length; i++) {
                mapBuilder = mapBuilder + i;
                for (int j = 0; j < map.length; j++) {
                   mapBuilder = mapBuilder + " " + map[i][j];
                    if (j == 9) {
                        mapBuilder = mapBuilder + "\n";
                    }
                }
            }
        }
        System.out.println(mapBuilder);
    }

    public void placeShipsLocalCoop(Player player, char[][] map) {
            //ships Carrier 5, Battleship 4, Destroyer 3, Submarine 3, Patrol boat 2
            int y = 0;
            int x = 0;
            boolean possbile = false;
            //,3,3,4,5
            int[] boats = {2};
            System.out.println(player.getName() + " Start placing your fleet!");
            for (int i = 0; i < boats.length; i++) {
                int boatLength = boats[i];
                possbile = false;

                while(!possbile) {
                    System.out.println("The ship to place is " + boats[i] + " spaces long \n First enter the y position for your ship: ");
                    boolean doneY = false;
                    boolean doneX = false;
                    while(!doneY)
                    {

                        String input = scanner.nextLine();
                        try
                        {
                            y = Integer.parseInt(input);
                            doneY = true;
                        }
                        catch( Exception e )
                        {
                            System.out.println("Please enter a number!");
                        }
                    }
                    //y = scanner.nextInt();
                    while (y > 9 || y < 0) {
                        System.out.println("Invalid input please input your y position again: ");
                        y = scanner.nextInt();
                    }
                    System.out.println("Now enter the x position for your ship: ");
                    while(!doneX)
                    {
                        String input = scanner.nextLine();
                        try
                        {
                            x = Integer.parseInt(input);
                            doneX = true;
                        }
                        catch( Exception e )
                        {
                            System.out.println("Please enter a number!");
                        }

                    }
                    while (x > 9 || x < 0) {
                        System.out.println("Invalid input please input your x position again: ");
                        x = scanner.nextInt();
                    }

                    System.out.println("Now enter in witch direction you want to place your ship from your start position, by typing \"up\", \"right\", \"down\" or \"left\": ");
                    String direction = scanner.nextLine();
                    int[] yx = {y, x};
                    //temp namn
                    possbile = checkIfPossible(map, yx, direction, boatLength);
                }
            }
        }

        public boolean checkIfPossible(char[][] map, int[] yx, String direction, int boatLength) {

            int newY = yx[0];
            int newX = yx[1];
            char mapPosValue = map[newY][newX];
            char w = 'W';

            switch (direction) {
                case "up":
                    System.out.println("up");
                    if(yx[0] - boatLength >= 0) {
                        //place boat
                        for (int i = 0; i < boatLength; i++) {

                            if(mapPosValue != w){
                                return false;
                            }
                            map[newY][newX] = 'S';
                            newY = newY - 1;
                        }
                        printMap(map, false);
                        return true;
                    }
                    else {
                        return false;
                    }

                case "right":
                    System.out.println("right");
                    if(yx[1] + boatLength <= 9) {

                        for (int i = 0; i < boatLength; i++) {

                            if(mapPosValue != w){
                                return false;
                            }
                            map[newY][newX] = 'S';
                            newX = newX + 1;
                        }
                        printMap(map, false);
                        return true;
                    }
                    else {
                        System.out.println("We are in last else state");
                        return false;
                    }

                case "down":
                    System.out.println("down");
                    if(yx[0] + boatLength <= 9) {

                        for (int i = 0; i < boatLength; i++) {

                            if(mapPosValue != w){
                                return false;
                            }
                            map[newY][newX] = 'S';
                            newY = newY + 1;
                        }
                        printMap(map, false);
                        return true;
                    }
                    else {
                        return false;
                    }

                case "left":
                    System.out.println("left");
                    if(yx[1] - boatLength >= 0) {

                        for (int i = 0; i < boatLength; i++) {
                            if(mapPosValue != w){
                                return false;
                            }
                            map[newY][newX] = 'S';
                            newX = newX - 1;
                        }
                        printMap(map, false);
                        return true;
                    }
                    else {

                        return false;
                    }
            }

        return false;
        }



    }


