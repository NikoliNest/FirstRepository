public class NesterovJava1 {

    public static void main(String args[]) {

       // Создать переменные всех пройденных типов данных, и инициализировать их значения

        byte a = 1;
        short b = 2;
        int c = 3;
        long d = 4L;
        float e = 5.00f;
        double f = 6.00;
        boolean g = true;
        char h = 'H';

        // Проверка работы методов

        //

        helloName("Коля");

        //

        System.out.println(arythm1( 1.05, 2.8,8, -77.88));

        //

        for (int i = 0; i < 3; i++){
            float j = (float)((Math.random() - 0.5) * 50);
            float k = (float)((Math.random() - 0.5) * 50);

            if (sumIsFine(j, k)) {
                System.out.println(" Сумма чисел " + j + " и " + k + " прекрасна! (от 10 до 20) ");
            } else {
                System.out.println(" Сумма чисел " + j + " и " + k + "  плохая! (не от 10 до 20) ");
            }
        }

        //

        for (int i = 0; i < 3; i++ ) {
            isPositive((int) ((Math.random() - 0.5) * 10));
        }

        //

        for (int i = 0; i < 3; i++ ) {
            int k =((int)((Math.random() - 0.5) * 10));
            if (isNegative(k)) {
                System.out.println(k + " Отрицательно.");
            } else {
                System.out.println(k + " Положительно.");
            }

        }

        //

        isVisokos(2018);
        isVisokos(2000);
        isVisokos(1900);

    }

    /*
     Написать метод, вычисляющий выражение a * (b + (c / d)) и
     возвращающий результат,где a, b, c, d – входные параметры этого метода
    */

    private static double arythm1(double a, double b, double c, double d) {
        double e = a * (b + (c / d));
        return e;
    }

    /*
    Написать метод, принимающий на вход два числа,
    и проверяющий что их сумма лежит в пределах от 10 до 20(включительно),
     если да – вернуть true, в противном случае – false;
     */

    private static boolean sumIsFine(double a, double b) {
        boolean isFine;
        if ((a+b) >=10 && (a+b) <=20 ) {
            isFine = true;
        } else isFine = false;
        return isFine;
    }

    /*
    Написать метод, которому в качестве параметра передается целое число,
    метод должен напечатать в консоль положительное ли число передали,
    или отрицательное; Замечание: ноль считаем положительным числом.
     */

    private static void isPositive(int a) {
        if (a >=0) {
            System.out.println("Передано положительное число " + a);
        } else {
            System.out.println("Передано отрицательное число " + a);
        }
    }

    /*
     Написать метод, которому в качестве параметра передается целое число,
      метод должен вернуть true, если число отрицательное;
     */

    private static boolean isNegative(int a) {
        boolean isNeg = false;
        if (a<0) {
            isNeg = true;
            return isNeg;
        } else return isNeg;
    }
    /*
    Написать метод, которому в качестве параметра передается строка,
    обозначающая имя, метод должен вывести в консоль сообщение «Привет, указанное_имя!»;
    */

    private static void helloName(String name) {
        System.out.println("Привет, " + name);
    }

    /*
    Написать метод, который определяет является ли год високосным, и выводит сообщение
    в консоль. Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый
    400-й – високосный.
    */

    private static void isVisokos(int year) {
        if (((year % 4) != 0 )|| ( (year % 100) == 0 && (year % 400) != 0) ) { // Не високосный: остаток от деления на 4 не равен нулю,
            System.out.println("Год " + year + " не високосный");  // ИЛИ остаток от деления на 100 равен нулю, но
        } else {                                                  // остаток от деления на 400 не равен нулю
            System.out.println("Год " + year + " високосный");
        }
    }



}
