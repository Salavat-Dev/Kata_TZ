package Kata_TZ;

import java.util.Scanner;

public class Kata {
    public static void main(String[] args) {
        System.out.println("Введите два значения в формате \"Hello\" + \"знак:| + | - | * | / |\" + \"World\"");
        split_result.splitMethod();
    }
}
class split_result {
    public static String splitMethod() {
        Scanner scan = new Scanner(System.in);

        String in = scan.nextLine();
        String[] input = new String[3];
        String firstValue = null;
        String s = null;
        String secondValue = null;
        String result = "";
        if (in.contains("-") || in.contains("+") || in.contains("*") || in.contains("/")) { // Определяем делитель строки
            s = in.replaceAll("[^-+/*]", "");
            if (s.length() > 1) {s = s.substring(1);}                //Проверяем что в строчке больше нет знаков [-+/*], если есть берем второй для примера с "Bye-bye!" - "World!"
            switch (s) {                                                       // Делим строку на массив по делителю
                case "-" -> {s = "-";input = in.split(" - ");}
                case "+" -> {s = "+";input = in.split(" \\+ ");}
                case "*" -> {s = "*";input = in.split(" \\* ");}
                case "/" -> {s = "/";input = in.split(" / ");
                }default -> {System.out.println("Некоректный знак");}
            }
            firstValue = input[0].replace("\"", "").trim();
            secondValue = input[1].replace("\"", "").trim();
        }


        try {                                                                  //Проверяем не являеться ли 1 символ числом
            if (Integer.parseInt(input[0]) >= 0) {
                System.out.println("Первое значение не должно быть цифрой, попробуй еще");
                return splitMethod();
            }
        } catch (NumberFormatException e) {
        }
        if (s.equals("+")) {                                                    // обрабатываем + и -
            result = firstValue + secondValue;
            if (result.length()>40) {
                result = result.substring(0, 40) + "...";
            }
            System.out.println("\"" + result + "\"");
        } else if (s.equals("-")) {
            int index = firstValue.indexOf(secondValue);
            if (index == -1) {
                System.out.println(input[0]);
            } else {
                result = firstValue.substring(0, index);
                result += firstValue.substring(index + secondValue.length());
                System.out.println("\"" + result + "\"");
            }
        }
        if (s.equals("*") || s.equals("/")) {                                     // обрабатываем / и *
            int digit = 0;
            try {
                if (input[1].contains("\"")) {                                  //Проверяем что делим/умножаем не на строку
                    System.out.println("Умножать и делить можно только на число, попробуй еще");
                    return splitMethod();
                }
            } catch (NumberFormatException e) {
                System.out.println("Умножать и делить можно только на число, попробуй еще");
                return splitMethod();
            }
            if (s.equals("*")) {
                if (digit <= Integer.parseInt(secondValue)) {
                    digit = Integer.parseInt(secondValue);
                    if (digit > 10 || digit == 0) {
                        System.out.println("Попробуй еще. Диапазон чисел 1-10");
                        return splitMethod();
                    }
                    for (int i = 0; i < digit; i++) {
                        result += firstValue;
                    }
                    if (result.length()>40) {
                        result = result.substring(0, 40) + "...";
                    }
                    System.out.println("\"" + result + "\"");
                }
            }
            if (s.equals("/")) {
                int index = firstValue.length();
                digit = Integer.parseInt(secondValue);
                if (digit > 10 || digit == 0) {
                    System.out.println("Попробуй еще. Диапазон чисел 1-10");
                    return splitMethod();
                }
                index /= digit;
                result += firstValue.substring(0, index);
                System.out.println("\"" + result + "\"");
            }

        }

        return result;
    }
}

