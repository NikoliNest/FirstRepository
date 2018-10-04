import java.util.Arrays;
import java.util.Scanner;
public class NesterovJava4 {
    private static final int SIZE = 10;
    private static char[][] map = new char[SIZE][SIZE];
    private static final char DOT_EMPTY = '\u2219';
    private static final char DOT_X = 'X';
    private static final char DOT_0 = '0';
    private static int lineLength = 4;
    private static int[] lHm = new int[(2 * lineLength)]; // Последние ходы человека
    private static int[] lAIm = new int[(2 * lineLength)];
    private static int xAI;  // Координаты хода АИ. Пришлось сделать их переменными класса :(
    private static int yAI;
    private static int turnNumber = 0; // Номер хода
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        initMap();
        printMap();
        gameLogic();
    }
    private static void gameLogic() {
        while(true){
            if (checkPossibleTurn()){
                System.out.println("Draw");
                break;
            }
            turnHuman();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("Player wins");
                break;
            }
            if (checkPossibleTurn()){
                System.out.println("Draw");
                break;
            }
            turnAi();
            printMap();
            if (checkWin(DOT_0)){
                System.out.println("AI wins");
                break;
            }
            turnNumber++;
            System.out.println("Последние ходы человека:"  + Arrays.toString(lHm));
            System.out.println("Последние ходы АИ:" + Arrays.toString(lAIm));
        }
        System.out.println( "Game over.");
    }
    private static boolean checkPossibleTurn() { // Проверка на наличие свободного места
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }
    private static boolean checkWin(char dot) { // Переделать на любую длину линии
        char[] line = new char[lineLength]; // Создает массив - эталон для сравнения
        for (int a = 0; a < lineLength; a++) {
            line[a] = dot;
        }
        return ((checkHorizontalLine(line)) || checkVerticalLine(line) || checkDiagonLine(line));
    }
    private static boolean checkDiagonLine(char[] line) { // Проверка по диагоналям
        char[] tempLine = new char[lineLength];
        for (int i = 0; i <= (SIZE - lineLength) ; i++) {// Сначала слева сверху вправо вниз
            for (int m = 0; m <=(SIZE - lineLength); m++) {
                for(int k = 0; k < lineLength; k++) {
                    tempLine[k] = map[(i + k)][(m + k)];
                }
                if (Arrays.equals(tempLine,line)) {
                    return true;
                }
            }
        }
        for (int i = (SIZE - 1); i > (SIZE - lineLength + 1)  ; i--) {// Потом наоборот. Не всегда схватывает :( !!!!!!
            for (int m = 0; m <= (SIZE - lineLength); m++) {
                for(int k = 0; k < lineLength; k++) {
                    tempLine[k] = map[(m + k)][(i - k)];
                    // System.out.println((i+k) + "  " + (m + k) + "   " + (Arrays.toString(tempLine))); // ТЕСТ
                }
                if (Arrays.equals(tempLine,line)) {
                    return true;
                }
            }
        }
        return false;
    }
    private static boolean checkVerticalLine(char[] line) {
        char[] tempLine = new char[lineLength]; // Создает временный массив, сравниваемый с эталоном
        for (int i = 0; i < SIZE; i++) { // Номер вертикали
            for (int j = 0; j <=(SIZE - lineLength); j++) {// Номер  позиции в вертикали - начальной позиции временного массива
                for(int k = 0; k < lineLength; k++) {
                    tempLine[k] = map[(j + k)][i];// Заполнение временного массива
                }
                if (Arrays.equals(tempLine,line)) {
                    return true;// Проверка совпадения эталонного и временного массивов
                }
            }
        }
        return false;
    }
    private static boolean checkHorizontalLine(char[] line) { // Проверяет горизонтали
        char[] tempLine = new char[lineLength]; // Создает временный массив, сравниваемый с эталоном
        for (int i = 0; i < SIZE; i++) { // Номер горизонтали
             for (int j = 0; j <=(SIZE - lineLength); j++) {// Номер  позиции в горизонтали - начальной позиции временного массива
                 for(int k = 0; k < lineLength; k++) {
                    tempLine[k] = map[i][(j + k)];// Заполнение временного массива
                }
                if (Arrays.equals(tempLine,line)) {
                        return true;// Проверка совпадения эталонного и временного массивов
                }
            }
        }
        return false;
    }
    private static void turnAi() {//               Переделать так, чобы АИ мешал человеку.

        while (true) {
            int differentX = (Math.abs(lHm[1] - lHm[3]));
            int differentY = (Math.abs(lHm[0] - lHm[2]));
            System.out.println(" Разница x:" + differentX);
            System.out.println(" Разница y:" + differentY);
            if (((differentX == 1) && (differentY == 1))|| ((differentX == 0) && (differentY == 1)) || ((differentX == 1) && (differentY == 0))){ //Человек строит линию
                System.out.println("Человек строит линию");
                blockHumanLine();
                // killHumanLine();
                //randAI();
                break;
            } else if ((differentX >= lineLength) || (differentY) >= lineLength) { // Человек не туда пошёл
           System.out.println("Человек не туда пошёл");
                buildAiLine();
                // killHumanLine();// /
                // randAI();
                break;
            } else if (((differentX == 2) && (differentY) == 2) || // Человек строит ловушку
                      ((differentX == 0) && (differentY == 2) ||
                      ((differentX == 2) && (differentY == 0)))) {
                System.out.println("Человек строит ловушку");
                killHumanLine();
                //randAI();
                break;
            } else {
                randAI(); // Случайный ход
                break;
            }
        }
        pushLast(lAIm, xAI, yAI); // Запоминает ход
    }
    private static void buildAiLine() { // Строить свою собственную линию, не ориентируясь на человека
        randAI(); // Пока заглушка
   }
    private static void randAI() {
        while (true) {
            int x = (int) (Math.random() * SIZE);
            int y = (int) (Math.random() * SIZE);
            System.out.println(y + " " +x);
            if (!isCellInvalid(x, y)) {
                map[y][x] = DOT_0;
                xAI = x;
                yAI = y;
                break;
            }
        }
    }
    private static void blockHumanLine() {
        int x;
        if (lHm[1] < lHm[3]){
            x = (lHm[1] - 1);
        } else if (lHm[1] == lHm[3]) {
            x = lHm[1];
        } else x = lHm[1] + 1;
        int y;
        if (lHm[0] < lHm[2]){
            y = (lHm[0] - 1);
        } else if (lHm[0] == lHm[2]) {
            y = lHm[0];
        }else {
            y = (lHm[0] + 1);
        }
        if (!isCellInvalid(x, y)) {
            map[y][x] = DOT_0;
            xAI = x;
            yAI = y;
        } else randAI();
    }

    private static void killHumanLine() {
        int x;
        int y;
        if (lHm[1] <= lHm[3]) {
            x = (lHm[3] - ((lHm[1] - lHm[3]) / 2));
        } else {
            x = (lHm[3] + ((lHm[1] - lHm[3]) / 2));
        }
        if (lHm[0] <= lHm[2]) {
            y = (lHm[2] - ((lHm[0] - lHm[2]) / 2));
        } else {
            y = (lHm[2] + ((lHm[0] - lHm[2]) / 2));
        }
        if (!isCellInvalid(x, y)) {
            map[y][x] = DOT_0;
            xAI = x;
            yAI = y;
        } else randAI();

    }

    private static void turnHuman() { // Ход человека
        int x;
        int y;
        while(true){
             System.out.println(" Input coordinates (X - horizontal, Y - vertical).");
             x = sc.nextInt() - 1;
             y = sc.nextInt() - 1;
             if (!isCellInvalid(x,y)) break;
         }
        map[y][x] = DOT_X;
        pushLast(lHm, x, y); // Запоминает ход
        }
    private static void pushLast(int[] array, int x, int y) { // Память на последние ходы
         for (int i = (array.length); i >= 4; i-=1) {
            int x1 = array [(i - 3)];
            int y1 = array [(i - 4)];
            array[(i - 1)] = x1;
            array[(i - 2)] = y1;
            }
        array[1] = x;
        array[0] = y;
    }
    private static boolean isCellInvalid(int x,int y) {          // Проверка на возможность хода
            return (x < 0 || y < 0 || x >= SIZE || y >= SIZE) || (map[y][x] != DOT_EMPTY);
    }
    private static void initMap() {  // Формирование игрового поля
        for (int i=0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
                }
            }
        }
    private static void printMap() {  // Печать игрового поля в консоль
        System.out.println();
        for (int i = 0; i <= SIZE; i ++) {
                System.out.printf( " %d ",i);
        }
        System.out.println();
        for (int i = 0; i < SIZE; i ++) {
            System.out.printf(" %d ",i+1);
            for (int j = 0; j < SIZE; j++) {
                System.out.printf(" %c ", map[i][j]);
            }
            System.out.println();
        }
    }
}