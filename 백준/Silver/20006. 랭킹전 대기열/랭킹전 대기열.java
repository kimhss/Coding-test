import java.util.*;
import java.io.*;

public class Main {

    static class Player {
        int level;
        String name;

        public Player(int level, String name) {
            this.level = level;
            this.name = name;
        }

        public int getLevel() {
            return this.level;
        }

        public String getName() {
            return this.name;
        }
    }

    static class Room {
        int level;
        List<Player> players;

        public Room(int level) {
            this.level = level;
            players = new ArrayList<>();
        }

        public void addPlayer(Player player) {
            players.add(player);
        }
    }

    static int p, m;

    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);

        p = sc.nextInt();
        m = sc.nextInt();

        List<Player> players = new ArrayList<>();
        List<Room> rooms = new ArrayList<>();

        for(int i = 0; i < p; i++) {
            int level = sc.nextInt();
            String name = sc.next();

            Player player = new Player(level, name);

            boolean entered = false;

            for (Room room : rooms) {
                if(room.players.size() < m &&
                        room.level - 10 <= level &&
                        level <= room.level + 10) {
                    room.addPlayer(player);
                    entered = true;
                    break;
                }
            }

            if (!entered) {
                Room newRoom = new Room(level);
                newRoom.addPlayer(player);
                rooms.add(newRoom);
            }
        }

        for(Room room : rooms) {
            if (room.players.size() == m) {
                System.out.println("Started!");
            }
            else
                System.out.println("Waiting!");

            Collections.sort(room.players, (a, b) -> a.name.compareTo(b.name));

            for(Player player : room.players) {
                System.out.println(player.level + " " + player.name);
            }
        }
    }
}