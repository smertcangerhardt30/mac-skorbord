package src;

public class Main {
    public static void main(String[] args) {

        // ===== TAKIMLAR VE OYUNCULAR =====
        Team eagles = new Team("Ankara Eagles");
        Team seagulls = new Team("Izmir Seagulls");

        Player ali = new Player("Ali Kaya", 5);
        Player can = new Player("Can Ozturk", 7);
        Player deniz = new Player("Deniz Arslan", 23);
        Player emre = new Player("Emre Sahin", 10);
        Player furkan = new Player("Furkan Celik", 14);

        eagles.addPlayer(ali);
        eagles.addPlayer(can);
        eagles.addPlayer(deniz);
        seagulls.addPlayer(emre);
        seagulls.addPlayer(furkan);

        // ===== ŞUTLAR =====
        ali.addShot(new ThreePointShot(true, 1, 7.5));
        ali.addShot(new TwoPointShot(true, 1));
        ali.addShot(new TwoPointShot(false, 2));
        ali.addShot(new FreeThrow(true, 3));
        ali.addShot(new FreeThrow(true, 3));

        can.addShot(new ThreePointShot(true, 2, 8.0));
        can.addShot(new ThreePointShot(false, 4, 7.0));
        can.addShot(new TwoPointShot(true, 4));

        deniz.addShot(new TwoPointShot(true, 1));
        deniz.addShot(new TwoPointShot(true, 3));
        deniz.addShot(new TwoPointShot(true, 4));

        emre.addShot(new ThreePointShot(true, 1, 7.0));
        emre.addShot(new ThreePointShot(true, 2, 7.2));
        emre.addShot(new TwoPointShot(false, 4));

        furkan.addShot(new TwoPointShot(true, 2));
        furkan.addShot(new TwoPointShot(true, 3));
        furkan.addShot(new FreeThrow(false, 3));
        furkan.addShot(new ThreePointShot(true, 4, 6.9));

        Game game = new Game(eagles, seagulls);

        // ===== 1. OYUNCU İSTATİSTİKLERİ =====
        System.out.println("===== PLAYERS =====");
        Player[] allPlayers = { ali, can, deniz, emre, furkan };
        for (Player p : allPlayers) {
            System.out.println(p + " | Made 3PT: " + p.countMadeThrees());
        }

        // ===== 2. SAYI LİDERLERİ =====
        System.out.println();
        System.out.println("===== TOP SCORERS =====");
        System.out.println(eagles.getName() + ": " + eagles.getTopScorer().getName());
        System.out.println(seagulls.getName() + ": " + seagulls.getTopScorer().getName());

        // ===== 3. OYUNCU ARAMA =====
        System.out.println();
        System.out.println("===== FIND PLAYER =====");
        System.out.println("findPlayer(7): " + eagles.findPlayer(7).getName());
        System.out.println("findPlayer(99): " + eagles.findPlayer(99));

        // ===== 4. SKORBORD =====
        System.out.println();
        System.out.println("===== SCOREBOARD =====");
        System.out.println(game.getScoreboard());

        // ===== 5. POLİMORFİZM =====
        System.out.println();
        System.out.println("===== POLYMORPHISM =====");
        Scoreable[] items = { ali, furkan, eagles, seagulls };
        for (Scoreable s : items) {
            System.out.println(s.getTotalPoints());
        }

        // ===== 6. HATA TESTLERİ =====
        System.out.println();
        System.out.println("===== ERROR TESTS =====");

        try {
            eagles.addPlayer(new Player("Hakan Ak", 5));
        } catch (IllegalArgumentException e) {
            System.out.println("1. " + e.getMessage());
        }

        try {
            new ThreePointShot(true, 1, 5.0);
        } catch (IllegalArgumentException e) {
            System.out.println("2. " + e.getMessage());
        }

        try {
            new TwoPointShot(true, 5);
        } catch (IllegalArgumentException e) {
            System.out.println("3. " + e.getMessage());
        }

        for (int i = 0; i < 5; i++) {
            deniz.addFoul();
        }
        System.out.println("4. Deniz fouled out: " + deniz.isFouledOut());

        try {
            deniz.addFoul();
        } catch (IllegalStateException e) {
            System.out.println("5. " + e.getMessage());
        }

        try {
            deniz.addShot(new TwoPointShot(true, 4));
        } catch (IllegalStateException e) {
            System.out.println("6. " + e.getMessage());
        }

        // ===== 7. TOPLAM ŞUT =====
        System.out.println();
        System.out.println("===== TOTAL SHOTS =====");
        System.out.println(Shot.getTotalShots());
    }
}
