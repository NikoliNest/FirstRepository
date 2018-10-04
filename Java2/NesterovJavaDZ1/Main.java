package NesterovJavaDZ;

public class Main {

    public static void main(String[] args) {

        Team team1 = new Team(new Dog("Снежок") , new Cat("Дружок"), new Human ("Коля"), new Human("Лёша"));
        Course course1 = new Course(3, new Obstacle[]{new Water(5), new Wall(30), new Cross(500), new Water(4)});
        course1.doItComando(team1);
        team1.everyMemberInfo();
        team1.memberOnDistanceInfo();

        System.out.println();

        Team team2 = new Team();
        Course course2 = new Course();
        course2.doItComando(team2);
        team2.everyMemberInfo();
        team2.memberOnDistanceInfo();

    }
}
