import java.util.Scanner;

public class Utilities {

    private static long start, memoryStart;

    public static void start() {
        start = System.nanoTime();
        memoryStart = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    public static void end() {
        long end = System.nanoTime();
        double time = ((double) end - start) / 1000000000.0;

        long memoryEnd = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        double memory = (memoryEnd - memoryStart) / (1024.0 * 1024.0);
        System.out.println("\nRun time: " + round(time, 3) + "s");
        System.out.println("Total memory used: " + round(memory, 3) + " MB");
    }

    public static double round(double number, long places) {
        if (places == 0)
            return Math.round(number);

        places = (long) Math.pow(10, places);
        return Math.round(number * (double) places) / (double) places;
    }

    public static boolean isValidInt(String integer) {
        if (integer == null)
            return false;

        int length = integer.length();
        int i = 0;

        if (length == 0)
            return false;

        if (integer.charAt(i) == '-') {
            if (length == 1)
                return false;
            i++;
        }

        char[] array = integer.toCharArray();

        for (; i < length; i++) {
            if (array[i] < '0' || array[i] > '9')
                return false;
        }

        return true;
    }

    public static int getInteger(Scanner scanner, String prompt, String error) {
        System.out.print(prompt + " ");
        String integer = scanner.nextLine();

        while (!isValidInt(integer)) {
            System.out.print("\n" + error + " ");
            integer = scanner.nextLine();
        }

        return Integer.parseInt(integer);
    }

    public static boolean isPositiveInt(String integer) {
        if (integer == null) {
            return false;
        }

        if (integer.length() == 0) {
            return false;
        }

        if (integer.charAt(0) == '-') {
            return false;
        }

        for (char c : integer.toCharArray()) {
            if (c < '0' || c > '9') {
                return false;
            }
        }

        return true;
    }

    public static int getPositiveInteger(Scanner scanner, String prompt, String error) {
        System.out.print(prompt + " ");
        String integer = scanner.nextLine();

        while (!isPositiveInt(integer)) {
            System.out.print("\n" + error + " ");
            integer = scanner.nextLine();
        }

        return Integer.parseInt(integer);
    }

    public static boolean isNegativeInt(String integer) {
        if (integer == null) {
            return false;
        }

        int length = integer.length();
        int i = 0;

        if (length == 0) {
            return false;
        }

        if (integer.charAt(i) != '-') {
            return false;
        } else if (integer.charAt(i) == '-') {
            if (length == 1) {
                return false;
            }
            i++;
        }

        char[] array = integer.toCharArray();

        for (; i < length; i++) {
            if (array[i] < '0' || array[i] > '9') {
                return false;
            }
        }

        return true;
    }

    public static int getNegativeInteger(Scanner scanner, String prompt, String error) {
        System.out.print(prompt + " ");
        String integer = scanner.nextLine();

        while (!isNegativeInt(integer)) {
            System.out.print("\n" + error + " ");
            integer = scanner.nextLine();
        }

        return Integer.parseInt(integer);
    }

    public static boolean isValidDouble(String decimal) {
        if (decimal == null)
            return false;

        int length = decimal.length();
        int i = 0;

        if (length == 0)
            return false;

        if (decimal.charAt(i) == '-') {
            if (length == 1)
                return false;
            i++;
        }

        char[] array = decimal.toCharArray();
        int points = 0;

        for ( ; i < length; i++) {
            if (array[i] == '.') {
                points ++;
                if (points > 1) {
                    return false;
                }
            } else if (array[i] < '0' || array[i] > '9') {
                return false;
            }
        }

        return true;
    }

    public static double getDouble(Scanner scanner, String prompt, String error) {
        System.out.print(prompt + " ");
        String decimal = scanner.nextLine();

        while (!isValidDouble(decimal)) {
            System.out.print("\n" + error + " ");
            decimal = scanner.nextLine();
        }

        return Double.parseDouble(decimal);
    }

    public static void enterToContinue(Scanner scanner) {
        System.out.print("Enter to continue . . . ");
        scanner.nextLine();
    }
}