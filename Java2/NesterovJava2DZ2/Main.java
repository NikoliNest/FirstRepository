public class Main {
    final static int A = 4;             // Здесь можно изменить размер подаваемого массива

    public static void main(String[] args) {

        try {
            String[][] myArray = new String[A][A]; // Задаётся массив

            myArray[0][0] = "12";
            myArray[0][1] = "10";
            myArray[0][2] = "Уфф";      // Тест
            myArray[0][3] = "3332";

            myArray[1][0] = "17";
            myArray[1][1] = "20";
            myArray[1][2] = "-888";
            myArray[1][3] = "22 Эта строка не преобразуется в целое число"; // Тест

            myArray[2][0] = "126";
            myArray[2][1] = "103";
            myArray[2][2] = "Ыфф";      // Тест
            myArray[2][3] = "73332.5";  // Тест

            myArray[3][0] = "179";
            myArray[3][1] = "250";
            myArray[3][2] = "6888";
            myArray[3][3] = "467720";

            if (myArray.length != 4) {
                throw new MyArraySizeException("Не соответствует размер массива");
            }

            int[][] myIntArray;                                   // Получение целочисленного массива
            myIntArray = myArrayRebuild(myArray);

            System.out.println("\n Вывод результата счета \n ");   // Вывод результата счета
            for (int i = 0; i < A; i++) {
                for (int j = 0; j < A; j++) {
                    System.out.printf("Ячейка %d, %d: %d. \n", i, j, myIntArray[i][j]);
                }
            }

        } catch (MyArraySizeException e) {   // Обработка неправильной длины массива
            e.printStackTrace();
        }
    }

    private static int[][] myArrayRebuild(String[][] myArray) {     //Метод преобразования строкового массива в целочисленный
        int[][] myIntArray = new int[A][A];
        for (int i = 0; i < A; i++) {
            for (int j = 0; j < A; j++) {
                try {
                    myIntArray[i][j] = Integer.parseInt(myArray[i][j]);
                } catch (Exception e) {
                    try {
                        throw new MyArrayDataException("Невозможно преобразовать элемент массива:");
                    } catch (MyArrayDataException e1) {
                        System.out.printf("Ячейка %d, %d. Ошибка обработана \n \n", i, j);
                    }
                }
            }
        }
        return myIntArray;
    }

    private static class MyArraySizeException extends Exception {
        String msg;
        MyArraySizeException(String msg) {
            super(msg);
            System.out.println(msg);
        }
    }

    private static class MyArrayDataException extends Exception {
        String msg;
        MyArrayDataException(String msg) {
            super(msg);
            System.out.println(msg);
        }
    }
}