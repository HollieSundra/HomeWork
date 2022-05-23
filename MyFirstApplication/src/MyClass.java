import java.util.Scanner;

public class MyClass {
    public static void main(String [] args) {
        System.out.println("Hello World!!");

        System.out.println("What is your name?");

        Scanner reader = new Scanner(System.in);
        String name = reader.nextLine();
        System.out.println("Hello, " + name + "!");
        reader.close();
    }
}
