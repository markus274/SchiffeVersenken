import java.util.Scanner;

/**
    Diese Klasse erzeugt ein Spiel Schiffe versenken. Sie generiert 
    zwei Spielfelder, eins zum Verteidigen und eins zum Angreifen. 
    Darueber hinaus koennen die beiden Spielfelder ausgegeben werden.
    Sie stellt die Methoden attack, defend und print bereit.
    @author David Nancekievill Matrikelnummer Gruppe 9c
    @author Markus Berning Matrikelnummer Gruppe 9c
    */
public class SchiffeVersenken {
    
    /**
    Array zum darstellen des spielereigenen Spielfeldes.
    */
    private static char[][] player = {
        {'.', '#', '#', '#', '#', '.', '.', '.', '.', '.'}, //row 0
        {'.', '.', '.', '.', '.', '#', '#', '#', '#', '.'}, //row 1
        {'.', '.', '.', '#', '.', '.', '.', '.', '#', '.'}, //row 2
        {'.', '.', '.', '#', '.', '.', '.', '.', '#', '.'}, //row 3
        {'#', '.', '.', '#', '.', '.', '.', '.', '#', '.'}, //row 4
        {'#', '.', '.', '.', '.', '.', '.', '.', '#', '#'}, //row 5
        {'.', '.', '#', '#', '#', '#', '.', '.', '.', '#'}, //row 6
        {'.', '.', '.', '.', '.', '.', '#', '.', '.', '#'}, //row 7
        {'.', '.', '.', '.', '.', '.', '#', '.', '.', '.'}, //row 8
        {'.', '#', '#', '.', '.', '.', '.', '#', '#', '.'} //row 9
        };
    
    /**
    Array zum darstellen, des gegenerischen Spielfeldes.
    */
    private static char[][] opponent = {
        {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'}, //row 0
        {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'}, //row 1
        {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'}, //row 2
        {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'}, //row 3
        {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'}, //row 4
        {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'}, //row 5
        {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'}, //row 6
        {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'}, //row 7
        {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'}, //row 8
        {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'} //row 9
        };
    
    /**
    Integer Variable, die die Treffer der Spielers zaehlt und das 
    Gewinnen des Spieles markiert.
    */
    private static int strikesPlayer = 0;
    
    /**
    Integer Variable, die die Treffer der Gegners zaehlt und das 
    Verlieren des Spieles markiert.
    */
    private static int strikesOpponent = 0;
    
    /**
    Diese Methode fragt den Spieler nach den Koordinaten fuer seinen
    Angriff und traegt ein X für Treffer und O für keinen Treffer in
    das array opponent ein.
    */
    public static void attack() {
        
        Scanner scan = new Scanner(System.in);
        int counter = 0;
        int counter2 = 0;
        int input, row, col;
        
        /* 
        Abfrage der Zeile und Spalte des Ziels, abfangen ungueltiger Eingaben
        (Int von 0-9)
        */
        do {
            System.out.print("\nWohin moechtest du schiessen?\n");
            
            // Zeile
            System.out.print("Zeile: ");
            
            do {
                row = convertChar();
                if (!(row >= 0 && row <= 9)) {
                    System.out.print("Ungueltige Eingabe! \nNochmal: ");
                }
            } while (!(row >= 0 && row <= 9));     
            
            // Spalte
            System.out.print("Spalte: ");
            
            do {
                col = checkInt();
                if (!(row >= 0 && col <= 9)) {
                    System.out.print("Ungueltige Eingabe! \nNochmal: ");
                }
            } while (!(row >= 0 && col <= 9));
            
            /*
            Eingabe durch den Spieler ob der Schuss ein Treffer war, hochzaehlen der
            Variable strikesPlayer, Sieg bei strikesPlayer == 30, auffangen ungueltiger
            Eingaben, Markieren der Treffer und Nieten
            */
            do {
                System.out.println("\nWar der Schuss ein Treffer?\n(1) Ja\n(2) Nein");
                input = checkInt();

                switch (input) {
                    case 1: opponent[row][col] = 'X';
                        counter = 1;
                        counter2 = 0;
                        print(opponent);
                        strikesPlayer += 1;
                        if (strikesPlayer == 30) {
                            System.out.println("Du hast gewonnen!");
                            System.exit(0);
                        }
                        break;
                    case 2: opponent[row][col] = 'O';
                        counter = 0;
                        counter2 = 0;
                        print(opponent);
                        break;
                    default: System.out.println("Keine gueltige Eingabe! Bitte wahle aus (1) oder (2) aus!");
                        counter2 = 1;                        
                        break;
                }
            } while (counter2 == 1);
        } while (counter == 1);
    }
    /**
    Diese Methode fragt den Spieler nach den Koordinaten des gegnerischen
    Angriffs und traegt ein X für Treffer und O für keinen Treffer in
    das array player ein.
    */
    public static void defend() {
        
        int row = 0, col = 0;
        int counter = 0;
        
        /*
        Eingabe des gegnerischen Angriffs als Zeile und Spalte, hochzaehlen
        der Variable strikesOpponent, Niederlage bei strikesOpponent = 30, 
        abfangen ungueltiger Eingaben
        */
        do {
            System.out.print("\nWohin wurde geschossen?");
            
            // Zeile
            System.out.print("Zeile: ");
            
            do {
                row = convertChar();
                if (!(row >= 0 && row <= 9)) {
                    System.out.print("Ungueltige Eingabe! \nNochmal: ");
                }
            } while (!(row >= 0 && row <= 9));
            
            //Spalte
            System.out.print("Spalte: ");
            
            do {
                col = checkInt();
                if (!(row >= 0 && col <= 9)) {
                    System.out.print("Ungueltige Eingabe! \nNochmal: ");
                }
            } while (!(row >= 0 && col <= 9));
            
            /*
            Abfrage ob der Angriff ein Treffer war, markieren der Treffer 
            und Nieten
            */
            if ((player[row][col] == '#') == true) {
                player[row][col] = 'X';
                print(player);
                System.out.println("\nDu wurdest getroffen!");
                counter = 1;
                strikesOpponent += 1;
                
                if (strikesOpponent == 30) {
                    System.out.println("Du hast verloren!");
                    System.exit(0);
                }
            } else {
                player[row][col] = 'O';
                print(player);
                System.out.println("\nDu wurdest nicht getroffen!");
                counter = 0;
            }
        } while (counter == 1);
    }
    
    /**
    Diese Methode gibt das uebergebene char-Array als Spielfeld auf dem
    Bildschirm aus.
    @param grid char-Array player oder opponent
    */
    public static void print(char[][] grid) {
    
        char[] rowAlpha = {'A','B','C','D','E','F','G','H','I','J'};
        
        System.out.println("\n " + "0123456789");
        for (int i = 0; i < grid.length; i++) {
            System.out.print(rowAlpha[i]);
            for (int n = 0; n < grid.length; n++) {
                System.out.print(grid[i][n]);
            }
            System.out.print("\n");
        }
    }
        /**
        Die Methode ueberprueft ob eine Eingabe ein 
        Integer ist und verlangt sonst die Eingabe eines Integers.
        @return Gibt die Eingabe als Integer zurück.
        */
    public static int checkInt() {
    
        Scanner check = new Scanner(System.in);
        int input;
        
        // Ueberprueft ob die Eingabe ein Integer ist            
        while (!check.hasNextInt()) {
            check.next();
            System.out.print("Ungueltige Eingabe! Nochmal: ");
        }
        input = check.nextInt();
        
        //Rueckgabe des Eingabewertes als Integer
        return input;
    }    

    /**
    Die Methode ueberprueft, ob die Einegabe ein char zwischen A und J ist und verlangt
    sonst die Einabe eines chars, der Zwischen A und J liegt. Zudem konvertiert die Methode
    die Buchstaben in Integer in der Form a=0, A=0 -> j=9, J=9.
    @return gibt die char Eingabe als konvertierten Integer zurück.
    */
    public static int convertChar() {

        Scanner scan = new Scanner(System.in);
        String inputAsString;
        char inputAsChar;
        int inputAsInt, convertedInt = 400, counter = 0;

        do {
            inputAsString = scan.next();
            if (inputAsString.length() == 1) {                  //ueberprueft, ob ein einzelnes Zeichen eingegeben wurde
                inputAsChar = inputAsString.charAt(0);          //Konvertierung String->Char
                inputAsInt = (int) inputAsChar;                 //Konvertierung Char->Int

                if (inputAsInt >= 65 && inputAsInt <= 74) {         //Grossbuchstaben A bis J
                    convertedInt = inputAsInt - 65;
                    counter = 1;
                }
                else if (inputAsInt >=97 && inputAsInt <=106) {      //Kleinbuchstaben a bis j
                    convertedInt = inputAsInt -97;
                    counter = 1;
                }
                else {
                    counter = 0;
                    scan.nextLine();
                    System.out.print("Ungueltige Eingabe! Bitte eine Zeile zwischen A und J waehlen. \nZeile: ");
                }
            }
            else {
                counter = 0;
                scan.nextLine();
                System.out.println("Unguelitge Eingabe! Bitte eine Zeile zwischen A und J waehlen. \nZeile: ");
            }
        } while (counter == 0);

        return convertedInt;
    }

    /**
    Gibt zu Beginn des Spieles beide Spielfelder aus ,stellt eine 
    Menuestruktur bereit, die dem Spieler das auswaehlen 
    bestimmter Aktionen ermoeglicht.
    @param args Keine Parameter werden uebergeben.
    */
    public static void main(String[] args) {
    
        Scanner scan = new Scanner(System.in);
        int input;
        int counter = 0;
        
        print(player);
        print(opponent);
        
        while (true) {

            do {
                System.out.print("\nWas moechtest du tun?\n(1) Angreifen\n(2) Verteidigen\n(3) Print\n(4) Exit\n");
                input = checkInt();
                
                switch (input) {
                    case 1: attack();
                        counter = 0;
                        break;
                    case 2: defend();
                        counter = 0;
                        break;
                    case 3: print(player);
                        print(opponent);
                        counter = 0;
                        break;
                    case 4: System.exit(0);
                        break;
                    default: System.out.print("Bitte waehle aus den Optionen (1), (2), (3) oder (4) aus!");
                        counter = 1;
                        break;
                }
            } while (counter == 1);
        }   
    }
}
