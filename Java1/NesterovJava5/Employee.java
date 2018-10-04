package NesterovJava5;

public class Employee {
    private String name;
    private String position;
    private String email;
    private String phone;
    private int salary;
    private int age;

    // Конструкторы с перегрузкой
    Employee() {
        this.name = "Имя";
        this.position = "Должность";
        this.email = "Электронная почта";
        this.phone = "Телефон";
        this.salary = 0;
        this.age = 0;
    }
    Employee(String name, String position, String email, String phone, int salary, int age){ // Конструктор
        this.name = name;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }
     Employee(String name, int age, String position) {
        this.name = name;
        this.age = age;
        this.position = position;
        this.email = "Электронная почта";
        this.phone = "Телефон";
        this.salary = 0;
    }

    // Метод вывода в консоль
    void output() {
        System.out.printf("ФИО: %s \n", this.name);
        System.out.printf("Должность: %s \n", this.position);
        System.out.printf("Email: %s \n" , this.email);
        System.out.printf("Телефон: %s \n",  this.phone);
        System.out.printf("Зарплата: %d \n", this.salary);
        System.out.printf("Возраст: %d \n", this.age);
        System.out.println();
    }

    // Сеттеры и геттеры
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    public String getPosition() {
        return position;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPhone() {
        return phone;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
    public int getSalary() {
        return salary;
    }

    public void setAge(int age) {
        this.age = age;
    }
    int getAge() {
        return age;
    }

}