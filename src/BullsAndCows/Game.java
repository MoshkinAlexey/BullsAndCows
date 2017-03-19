import java.util.*;

/**
 * Процесс игры
 */

class Game {
    static boolean beginGame() {
        int difficult;
        System.out.println("Выберите сложность:");
        System.out.println("0 - очень лёгкая [0..9; 4 символа]");
        System.out.println("1 - лёгкая [0..9; 6 символов]");
        System.out.println("2 - средняя [0..9, A..Z; 4 символа]");
        System.out.println("3 - сложная [0..9, A..Z; 6 символов]");
        System.out.println("4 - очень сложная [0..9, A..Z, a..z; 4 символов]");
        System.out.println("5 - невероятно сложная [0..9, A..Z, a..z; 6 символов]");

        Scanner scanner = new Scanner(System.in);
        do {
            difficult = scanner.nextInt();
            if (difficult < 0 || difficult > 5) {
                System.out.println("Неправильный выбор, попробуйте снова:");
            }
        } while (difficult < 0 || difficult > 5);

        final Code rightCode = Code.generateCode(difficult);
        Code checkingCode;

        do {
            System.out.println("Введите код (\"-\" для выхода):");
            String inputString = scanner.next();
            if (inputString.equals("-")) {
                scanner.close();
                return true;
            }
            checkingCode = new Code(inputString);
            if (checkingCode.equals(rightCode)) {
                System.out.println("Поздравляю! Вы угадали код!");
                System.out.println("Начать заново? (\"+\" - да, \"-\" - нет)");
                String choose = scanner.next();
                scanner.close();
                return !choose.equals("+");
            } else {
                System.out.println(checkingCode.compare(rightCode));
                System.out.print("Попробуйте ещё раз. ");
            }
        } while (!checkingCode.equals(rightCode));
        scanner.close();
        return true;
    }
}
