import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String playerX = "X";
        String playerO = "O";
        boolean flag = true;

        String[][] map = new String[3][3];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                map[i][j] = String.valueOf(' ');
            }
        }

        int i = 0;

        while (flag) {

            if(i % 2 == 0) {
                map = makingMove(map, playerX, scanner);
            } else {
                map = makingMove(map, playerO, scanner);
            }

            i += 1;

            flag = checkStateOfGame(map);

        }

    }

    private static String[][] makingMove(String[][] map, String player, Scanner scanner) {

        printMap(map);

        for (;;) {
            System.out.print("Enter the coordinates: ");
            String strLine = scanner.nextLine();
            String strLineSplit[] = strLine.split(" ");
            if (strLineSplit.length < 2) {
                System.out.println("You should enter numbers!");
                continue;
            }
            if (!stringValidation(strLineSplit[0]) || !stringValidation(strLineSplit[1])) {
                System.out.println("You should enter numbers!");
                continue;
            }
            int x = Integer.parseInt(strLineSplit[0]);
            int y = Integer.parseInt(strLineSplit[1]);

            if (x < 1 || x > 3 || y < 1 || y > 3){
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            if (map[x-1][y-1].equals(" ")){
                map[x-1][y-1] = player;
                break;
            } else if (map[x-1][y-1].equals("X") || map[x-1][y-1].equals("O")){
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
        }

        return map;

    }


    private static boolean stringValidation (String str){
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean checkStateOfGame(String[][] map) {
        boolean isTrueX = false;
        boolean isTrueO = false;
        boolean forDraw = false;
        boolean gameNotFinished = false;
        boolean isImpossible = false;
        boolean draw = true;

        for (int i = 0; i < map.length; i++) {
            if((map[i][0].equals("X") && map[i][1].equals("X") && map[i][2].equals("X") || map[map.length-3][map.length-3].equals("X") && map[map.length-2][map.length-2].equals("X") && map[map.length-1][map.length-1].equals("X") ||
                    map[map.length-3][map.length-1].equals("X") && map[map.length-2][map.length-2].equals("X") && map[map.length-1][map.length-3].equals("X") || map[0][i].equals("X") && map[1][i].equals("X") && map[2][i].equals("X")) &&
                    (map[i][0].equals("O") == false && map[i][1].equals("O") == false && map[i][2].equals("O") == false || map[map.length-3][map.length-3].equals("O") == false && map[map.length-2][map.length-2].equals("O") == false && map[map.length-1][map.length-1].equals("O") == false ||
                            map[map.length-3][map.length-1].equals("O") == false && map[map.length-2][map.length-2].equals("O") == false && map[map.length-1][map.length-3].equals("O") == false || map[0][i].equals("O") == false && map[1][i].equals("O") == false && map[2][i].equals("O") == false)){
                isTrueX = true;
            }else if((map[i][0].equals("O") && map[i][1].equals("O") && map[i][2].equals("O") || map[map.length-3][map.length-3].equals("O") && map[map.length-2][map.length-2].equals("O") && map[map.length-1][map.length-1].equals("O") ||
                    map[map.length-3][map.length-1].equals("O") && map[map.length-2][map.length-2].equals("O") && map[map.length-1][map.length-3].equals("O") || map[0][i].equals("O") && map[1][i].equals("O") && map[2][i].equals("O")) &&
                    (map[i][0].equals("X") == false && map[i][1].equals("X") == false && map[i][2].equals("X") == false || map[map.length-3][map.length-3].equals("X") == false && map[map.length-2][map.length-2].equals("X") == false && map[map.length-1][map.length-1].equals("X") == false ||
                            map[map.length-3][map.length-1].equals("X") == false && map[map.length-2][map.length-2].equals("X") == false && map[map.length-1][map.length-3].equals("X") == false || map[0][i].equals("X") == false && map[1][i].equals("X") == false && map[2][i].equals("X") == false)){
                isTrueO = true;
            }
        }

        if(isTrueX) {
            draw = false;
        }
        if(isTrueO){
            draw = false;
        }


        int counterO = 0;
        int counterX = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(map[i][j].equals("O")){
                    counterO++;
                }else if(map[i][j].equals("X")){
                    counterX++;
                }
            }
        }
        if (Math.abs(counterX-counterO) >= 2 || isTrueO && isTrueX){
            isImpossible = true;
        }


        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (draw && map[i][j].equals("X") && map[i][j].equals("_") == false || draw && map[i][j].equals("O") && map[i][j].equals("_") == false) {
                    gameNotFinished = false;
                    forDraw = true;
                }
                if(draw && map[i][j].equals(" ") || draw && map[i][j].equals("_")){
                    gameNotFinished = true;
                    forDraw = false;
                    break;
                }
            }
        }

        boolean flag = true;

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(map[i][j].equals(" ")) {
                    flag = false;
                    break;
                }
            }
        }


        if(flag) {
            if (forDraw){
                System.out.println("Draw");
                return false;
            }
        }
        if (isImpossible){
            System.out.println("Impossible");
            return false;
        }
        if (gameNotFinished){
            return true;
        }
        if (isTrueO){
            printMap(map);
            System.out.println("O wins");
            return false;
        }
        if (isTrueX){
            printMap(map);
            System.out.println("X wins");
            return false;
        }

        return true;
    }

    private static void printMap(String[][] map) {
        System.out.println("---------");
        for (String[] innerArray : map) {
            System.out.print("| ");
            for (String element : innerArray) {
                System.out.print(element + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

}
