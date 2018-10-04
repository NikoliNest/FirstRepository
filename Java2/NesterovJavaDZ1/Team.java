package NesterovJavaDZ;

public class Team {

    static int teamNumber = 0;

    int number = 4;
    Competitor Comando[] = {new Cat("Пушок"), new Dog("Шарик"), new Human("Ваня"), new Human("Петя")}; // Такая форма объявления массива интерфейсных объектов работает 0_о

    //Competitor Comando[] = new Competitor[number];

    Team(){
        teamNumber++;
        System.out.println(teamNumber);
    }

    Team(Competitor a, Competitor b, Competitor c, Competitor d ){
        teamNumber++;
        Comando[0] = a;
        Comando[1] = b;
        Comando[2] = c;
        Comando[3] = d;
        System.out.println("Команда " + teamNumber);
    }

    void memberOnDistanceInfo() {
        System.out.printf("Дошедшие до финиша члены команды %d прошли полосу препятствий следующим образом: \n", teamNumber);
        for (int i = 0; i < number; i++) {
            if (Comando[i].isOnDistance()) {
                Comando[i].info();
            }
        }
    }

    void everyMemberInfo() {
        System.out.printf("Команда %d прошла полосу препятствий следующим образом: \n", teamNumber);
        for (int i = 0; i < number; i++) {
            Comando[i].info();
        }
    }
}
