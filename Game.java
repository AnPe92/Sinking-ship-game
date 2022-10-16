import java.util.*;

public class Game {

    //Things to do, break out input validator to its on method.

    //Running the game loop.
    Scanner scanner = new Scanner(System.in);

    void game(boolean running) {
        //Game menu
        while (running) {
            System.out.println("Welcome to Anton's sinking ship game! \n       Make a choice \n[1] Play a new game vs ai \n[2] Play a game vs a friend (local coop) \n[3] Scorboard \n[4] Exit program");
            int userInput = scanner.nextInt();
            switch (userInput) {
                case 1:
                    System.out.println("Not yet implemented");
                    break;
                case 2:
                    //System.out.println("Lets play local coop!");
                    localCoop();
                    break;
                case 3:
                    running = false;
                    break;
            }
        }
    }

    //Start up loocalcoop game session
    private void localCoop() {

        //Initialize player maps and "drawing" them with W
        char[][] player1Map = new char[10][10];
        char[][] player2Map = new char[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                player1Map[i][j] = 'W';
                player2Map[i][j] = 'W';
            }
        }

        //Getting user1 to setup his gameboard.
        System.out.println("Player one enter your name: ");
        scanner.nextLine();
        String playerName = scanner.nextLine().trim();
        Player player1 = new Player(playerName);
        placeShipsLocalCoop(player1, player1Map);

        //setting up user2
        System.out.println("Player two enter your name: ");
        playerName = scanner.nextLine().trim();
        Player player2 = new Player(playerName);
        placeShipsLocalCoop(player2, player2Map);

        //Starting the game session
        startGame(player1, player2, player1Map, player2Map);
    }

    private void startGame(Player player1, Player player2, char[][] player1Map, char[][] player2Map) {
        boolean playing = true;
        int playerOneScore = 0;
        int playerTwoScore = 0;


        while (playing) {
            int y = 0;
            int x = 0;

            boolean playerOneTurn = true;
            boolean playerTwoTurn = true;
            boolean doneY = false;
            boolean doneX = false;

            if (playerOneScore == 2) {
                System.out.println("Game over, Winner is " + player1.getName());
                playing = false;
                break;
            } else if (playerTwoScore == 2) {
                System.out.println("Game over, Winner is " + player2.getName());
                playing = false;
                break;
            }


            while (playerOneTurn) {
                y = 0;
                x = 0;
                printMap(player2Map, true);
                System.out.println(player1.getName() + " THIS IS P1  's move, please enter Y coordinate: ");
                while (!doneY) {
                    String input = scanner.nextLine();
                    try {
                        y = Integer.parseInt(input);
                        try {
                            if (y >= 0 && y <= 9) {
                                doneY = true;
                            } else {
                                System.out.println("The number needs to be between 0 - 9!");
                            }
                        } catch (Exception ignored) {
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a number!");
                    }
                }

                System.out.println("Enter X coordinate: ");
                while (!doneX) {
                    String input = scanner.nextLine();
                    try {
                        x = Integer.parseInt(input);
                        try {
                            if (x >= 0 && x <= 9) {
                                doneX = true;
                            } else {
                                System.out.println("The number needs to be between 0 - 9!");
                            }
                        } catch (Exception ignored) {
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a number!");
                    }
                }

                if (player2Map[y][x] == 'S') {
                    System.out.println("==== HIT ====");
                    System.out.println("_-_-_-_-_-_-_-_-_-_");
                    playerOneScore++;
                    player2Map[y][x] = 'X';
                    playerOneTurn = false;
                } else if (player2Map[y][x] == 'X' || player2Map[y][x] == 'O') {
                    System.out.println("you have already bombed this area please choose another spot.");
                    System.out.println("_-_-_-_-_-_-_-_-_-_");
                    doneY = false;
                    doneX = false;
                } else {
                    player2Map[y][x] = 'O';
                    System.out.println("MISS");
                    System.out.println("_-_-_-_-_-_-_-_-_-_");
                    playerOneTurn = false;
                }
            }

            while (playerTwoTurn) {
                doneY = false;
                doneX = false;
                y = 0;
                x = 0;
                System.out.println(player2.getName() + "'s move");
                printMap(player1Map, true);
                System.out.println(" please enter Y coordinate: ");

                while (!doneY) {
                    String input = scanner.nextLine();
                    try {
                        y = Integer.parseInt(input);
                        try {
                            if (y >= 0 && y <= 9) {
                                doneY = true;
                            } else {
                                System.out.println("The number needs to be between 0 - 9!");
                            }
                        } catch (Exception ignored) {
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a number!");
                    }
                }

                System.out.println("Enter X coordinate: ");

                while (!doneX) {
                    String input = scanner.nextLine();
                    try {
                        x = Integer.parseInt(input);
                        try {
                            if (x >= 0 && x <= 9) {
                                doneX = true;
                            } else {
                                System.out.println("The number needs to be between 0 - 9!");
                            }
                        } catch (Exception ignored) {
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a number!");
                    }
                }

                if (player1Map[y][x] == 'S') {
                    System.out.println("HIT");
                    System.out.println("_-_-_-_-_-_-_-_-_-_");
                    playerTwoScore++;
                    player1Map[y][x] = 'X';
                    playerTwoTurn = false;
                } else if (player1Map[y][x] == 'X' || player1Map[y][x] == 'O') {
                    System.out.println("you have already bombed this area please choose another spot.");
                    System.out.println("_-_-_-_-_-_-_-_-_-_");
                    doneY = false;
                    doneX = false;
                } else {
                    player1Map[y][x] = 'O';
                    System.out.println("MISS");
                    System.out.println("_-_-_-_-_-_-_-_-_-_");
                    playerTwoTurn = false;
                }
            }
        }
    }

    //Drawing a map for users, can see ships when placing your ships, cant see them when bombing opponents map.
    private void printMap(char[][] map, boolean playing) {

        StringBuilder mapBuilder = new StringBuilder("  0 1 2 3 4 5 6 7 8 9\n");
        if (playing) {
            for (int i = 0; i < map.length; i++) {
                mapBuilder.append(i);
                for (int j = 0; j < map.length; j++) {
                    if (map[i][j] == 'S') {
                        mapBuilder.append(" ").append("W");
                    } else {
                        mapBuilder.append(" ").append(map[i][j]);
                    }
                    if (j == 9) {
                        mapBuilder.append("\n");
                    }
                }
            }
        } else {
            for (int i = 0; i < map.length; i++) {
                mapBuilder.append(i);
                for (int j = 0; j < map.length; j++) {
                    mapBuilder.append(" ").append(map[i][j]);
                    if (j == 9) {
                        mapBuilder.append("\n");
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
        boolean redo = true;
        //,3,3,4,5
        int[] boats = {2};

        while (redo) {
            System.out.println(player.getName() + " Start placing your fleet!");
            for (int i = 0; i < boats.length; i++) {
                int boatLength = boats[i];


                System.out.println("The ship to place is " + boats[i] + " spaces long \n First enter the y position for your ship: ");
                boolean doneY = false;
                boolean doneX = false;

                while (!doneY) {
                    String input = scanner.nextLine();
                    try {
                        y = Integer.parseInt(input);
                        try {
                            if (y >= 0 && y <= 9) {
                                doneY = true;
                            } else {
                                System.out.println("The number needs to be between 0 - 9!");
                            }
                        } catch (Exception ignored) {
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a number!");
                    }
                }

                System.out.println("Now enter the x position for your ship: ");
                while (!doneX) {
                    String input = scanner.nextLine();
                    try {
                        x = Integer.parseInt(input);
                        try {
                            if (x >= 0 && x <= 9) {
                                doneX = true;
                            } else {
                                System.out.println("The number needs to be between 0 - 9!");
                            }
                        } catch (Exception ignored) {
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a number!");
                    }
                }
                possbile = false;
                while (!possbile) {
                    System.out.println("Now enter in witch direction you want to place your ship from your start position, by typing \"up\", \"right\", \"down\", \"left\" or \"redo\" to choose new coordinates: ");
                    String direction = scanner.nextLine();

                    int[] yx = {y, x};
                    //temp namn
                    if(direction.trim().equalsIgnoreCase("redo")){
                        redo = true;
                        possbile = true;
                    }else {
                        redo = false;
                        possbile = BoatPlacementPossible(map, yx, direction, boatLength);
                    }

                }
            }
        }
    }



        public boolean BoatPlacementPossible(char[][] map, int[] yx, String direction, int boatLength) {

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


