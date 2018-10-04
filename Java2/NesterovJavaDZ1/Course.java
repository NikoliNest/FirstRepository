package NesterovJavaDZ;

public class Course {

    int obstacleNumber;
    Obstacle[] obstacles;

    public Course() {
        this.obstacleNumber = (int) (Math.random() * 10);
        this.obstacles = new Obstacle[obstacleNumber];
        randomObstacles(obstacleNumber);
    }

    public Course( int obstacleNumber){
        this.obstacleNumber = obstacleNumber;
        this.obstacles = new Obstacle[obstacleNumber];
        randomObstacles(obstacleNumber);
    }

    public Course( int obstacleNumber, Obstacle[] obstacles){
        this.obstacleNumber = obstacleNumber;
        this.obstacles = obstacles;
    }

    private void randomObstacles(int obstacleNumber) {  // Создает случайную полосу препятствий
        for (int i = 0; i < (obstacleNumber); i++) {
            int rnd = (int) (Math.random() * 3);
            switch (rnd) {
                case 0: {
                    int h = (int)(Math.random()* 70);
                   obstacles[i] = new Wall(h);
                    System.out.printf("Препятствие %d - стена, %d сантиметров \n" ,(i+1), h);
                    break;
                }
                case 1: {
                    int h = (int)(Math.random()* 10);
                    obstacles[i] = new Water(h);
                    System.out.printf("Препятствие %d - вода, %d м \n" ,(i+1), h);
                    break;
                }
                case 2: {
                    int h = (int)(Math.random()* 100);
                   obstacles[i] = new Cross(h);
                   System.out.printf("Упражнение %d - бег, %d метров \n" ,(i+1), h);
                   break;
                }default:{
                    int h = (int)(Math.random()* 50);
                    obstacles[i] = new Cross(h);
                    System.out.printf("Упражнение %d - бег, %d метров \n" ,(i+1), h);
                    break;
                }
                }
                System.out.println();
        }
    }

    void doItComando (Team team){
            for (int i = 0; i < (obstacleNumber); i++) {
                for (int j = 0; j < (team.Comando.length ); j++) {
                    obstacles[i].doIt(team.Comando[j]);
                }
            }
        }
    }

