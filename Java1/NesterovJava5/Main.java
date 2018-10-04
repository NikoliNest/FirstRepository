package NesterovJava5;

public class Main {
    public static void main(String[] args) {
        Employee[] employeeArray = new Employee[5];
        employeeArray[0] = new Employee("Филипп Джей Фрай", "курьер","f_j_fry@yandex.ru", "+7812", 1000, 1030);
        employeeArray[1] = new Employee("Туранга Лила", "пилот","lila2982@mail.ru", "8921", 3000, 33);
        employeeArray[2] = new Employee("Хьюберт Фарнсворт", "профессор","profffesor@rambler.ru", "5252", 3750, 127);
        employeeArray[3] = new Employee("Гермес Конрад ", "бюрократ","comrade-conrad@mail.ru", "8800", 3206, 44);
        employeeArray[4] = new Employee("Джон Зойдберг", "врач","doctorzoidberg@mzrf.ru", "+1312", 2740, 27);

        for (Employee anEmployeeArray : employeeArray) {
            if (anEmployeeArray.getAge() > 40) {
                anEmployeeArray.output();
            }
        }
    }
}
