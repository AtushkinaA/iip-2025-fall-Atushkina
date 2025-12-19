import java.util.Scanner;
public class Auth {
    private Scanner sc;
    private boolean isAuth;
    public Auth() {
        sc = new Scanner(System.in);
        isAuth = false;
    }
    public void start() {
        System.out.println("Добро пожаловать в CRM");
        while (!isAuth) {
            System.out.println("1. Войти");
            System.out.println("2. Выйти");
            System.out.println("Выберите действие:");
            String choice = sc.nextLine();
            if (choice.equals("1")) {
                System.out.println("Введите любое имя пользователя:");
                String username = sc.nextLine();
                System.out.println("Введите пароль:");
                String password = sc.nextLine();
                if (!(username.trim().isEmpty()) && !(password.isEmpty())) {
                    isAuth = true;
                    System.out.println("Добро пожаловать " + username);
                } else {
                    System.out.println("Имя пользователя не может быть пустым!");
                }
            } else if (choice.equals("2")) {
                System.exit(0);
            } else {
                System.out.println("Неверный выбор!");
            }
        }
    }
    public boolean isAuth() {
        return isAuth;
    }

}
