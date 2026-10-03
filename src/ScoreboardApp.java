package src;

import java.util.Scanner;

public class ScoreboardApp {

    private static Scanner scanner = new Scanner(System.in);

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    private static int readIntInRange(String prompt, int min, int max) {
        while (true) {
            int value = readInt(prompt);
            if (value >= min && value <= max) {
                return value;
            }
            System.out.println("Please enter a number between " + min + " and " + max + ".");
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number (use a dot, e.g. 7.5).");
            }
        }
    }

    private static String readText(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static boolean readYesNo(String prompt) {
        while (true) {
            System.out.print(prompt + " (y/n): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y") || input.equals("yes")) {
                return true;
            }
            if (input.equals("n") || input.equals("no")) {
                return false;
            }
            System.out.println("Please enter 'y' or 'n'.");
        }
    }

    private static Team selectTeam(Game game) {
        System.out.println("1 = " + game.getHome().getName() + ", 2 = " + game.getAway().getName());
        int choice = readIntInRange("Select team: ", 1, 2);
        if (choice == 1) {
            return game.getHome();
        }
        return game.getAway();
    }

    private static Player selectPlayer(Team team) {
        int jerseyNumber = readInt("Jersey number: ");
        Player player = team.findPlayer(jerseyNumber);
        if (player == null) {
            System.out.println("Player not found.");
        }
        return player;
    }

    private static Team createTeam(String label) {
        Team team;
        while (true) {
            try {
                team = new Team(readText(label + " team name: "));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        int count = readIntInRange("Number of players (1-" + Team.MAX_PLAYERS + "): ",
                1, Team.MAX_PLAYERS);

        for (int i = 1; i <= count; i++) {
            System.out.println("Player " + i + ":");
            while (true) {
                try {
                    String name = readText("  Name: ");
                    int jerseyNumber = readInt("  Jersey number: ");
                    team.addPlayer(new Player(name, jerseyNumber));
                    break;
                } catch (IllegalArgumentException | IllegalStateException e) {
                    System.out.println("Error: " + e.getMessage() + " Try again.");
                }
            }
        }
        return team;
    }

    private static void handleAddShot(Game game) {
        Team team = selectTeam(game);
        Player player = selectPlayer(team);
        if (player == null) {
            return;
        }
        if (player.isFouledOut()) {
            System.out.println(player.getName() + " has fouled out and cannot shoot.");
            return;
        }

        int type = readIntInRange("Shot type (1 = FT, 2 = 2PT, 3 = 3PT): ", 1, 3);
        int quarter = readIntInRange("Quarter (1-4): ", 1, 4);
        boolean made = readYesNo("Made?");

        Shot shot;
        if (type == 1) {
            shot = new FreeThrow(made, quarter);
        } else if (type == 2) {
            shot = new TwoPointShot(made, quarter);
        } else {
            double distance = readDouble("Distance (m): ");
            shot = new ThreePointShot(made, quarter, distance);
        }

        player.addShot(shot);
        System.out.println("Shot added: " + shot);
    }

    private static void handleAddFoul(Game game) {
        Team team = selectTeam(game);
        Player player = selectPlayer(team);
        if (player == null) {
            return;
        }

        player.addFoul();
        if (player.isFouledOut()) {
            System.out.println(player.getName() + " has fouled out!");
        } else {
            System.out.println("Foul added (" + player.getFouls() + "/" + Player.MAX_FOULS + ").");
        }
    }

    private static void handleStats(Game game) {
        Team team = selectTeam(game);
        Player player = selectPlayer(team);
        if (player == null) {
            return;
        }

        System.out.println(player);
        System.out.println("Made 3PT: " + player.countMadeThrees());
        System.out.print("Fouls: " + player.getFouls() + "/" + Player.MAX_FOULS);
        if (player.isFouledOut()) {
            System.out.print(" (fouled out)");
        }
        System.out.println();
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("===== SCOREBOARD =====");
        System.out.println("1. Add shot");
        System.out.println("2. Add foul");
        System.out.println("3. Player stats");
        System.out.println("4. Show scoreboard");
        System.out.println("0. Finish game");
    }

    public static void main(String[] args) {
        System.out.println("=== Basketball Scoreboard ===");

        Team home = createTeam("Home");
        Team away = createTeam("Away");
        Game game = new Game(home, away);

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Choice: ");

            try {
                switch (choice) {
                    case 1:
                        handleAddShot(game);
                        break;
                    case 2:
                        handleAddFoul(game);
                        break;
                    case 3:
                        handleStats(game);
                        break;
                    case 4:
                        System.out.println(game.getScoreboard());
                        break;
                    case 0:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println();
        System.out.println("Final:");
        System.out.println(game.getScoreboard());
    }
}