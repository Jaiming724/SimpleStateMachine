import java.util.Scanner;

public class Thermometer {
    private int temperature;
    private Scanner scanner;

    public Thermometer() {
        scanner = new Scanner(System.in);
    }

    public void setTemperature() {
        System.out.print("Set new temperature: ");
        temperature = scanner.nextInt();
    }

    public int getTemperature() {
        return temperature;
    }
}
