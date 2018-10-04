import java.util.Arrays;
import java.util.Scanner;
public class NesterovJava3 {
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws java.io.IOException {
        int choice;
        do {
            System.out.println("Выберите игру: 1. Угадайте число. 2. Угадайте слово. 0. Выход.");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    questNumber();
                    break;
                case 2:
                    questWord();
                    break;
            }
        } while (choice != 0);
    }
    private static void questNumber() {
        int ans1 = 0;
        do {
            boolean victory = false;
            System.out.println(" Угадайте число от 0 до 9. У Вас 3 попытки.");
            int quest = (int) (Math.random() * 10);
            for (int i = 0; i < 3; i++) {
                int answer = sc.nextInt();
                if (answer == quest) {
                    System.out.println(" Вы выиграли!");
                    victory = true;
                    break;
                } else if (answer < quest && answer >= 0) {
                    System.out.println(" Загаданное число больше.");
                } else if (answer > quest) {
                    System.out.println(" Загаданное число меньше.");
                } else {
                    System.out.println(" Введите число от 0 до 9.");
                }
            }
            if (!victory) {
                System.out.println("Вы проиграли!");
            }
            System.out.println("Хотите сыграть еще раз? 1 - да, 0 - нет.");
            ans1 = sc.nextInt();
        } while (ans1 != 0 && ans1 == 1);
    }
    private static void questWord() {
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot",
                "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea",
                "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
        int a = (int) (Math.random() * words.length);
        String strQuest = words[a];
        // System.out.println(strQuest); //                                             ТЕСТ
        char[] quest = new char[strQuest.length()];
        for (int i = 0; i < strQuest.length(); i++) {
            quest[i] = strQuest.charAt(i);
        }
        boolean victory = false;
        do {
            String strAnswer = sc.next();
            char[] answer = new char[strAnswer.length()];
            for (int i = 0; i < strAnswer.length(); i++) {
                answer[i] = strAnswer.charAt(i);
            }
            if (Arrays.equals(answer, quest)) {
                victory = true;
                System.out.println("Вы отгадали!");;
            }
            for (int i = 0; i < 15 && !victory ; i++) {
                if ((i < strAnswer.length()) && ( i < strQuest.length()) && (quest[i] == answer[i]) && (!Arrays.equals(answer, quest))) {
                    System.out.print(quest[i]);
                } else {
                    System.out.print("#");
                }
            }
            System.out.println();
        } while (!victory);
    }
}