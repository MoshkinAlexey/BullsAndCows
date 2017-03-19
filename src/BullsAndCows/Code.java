import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Реализация игрового кода:
 * - создание нового кода - Code(строка с кодом)
 * - генерация случайного кода - generateCode(сложность: 0..5)
 * - сравнение с правильным кодом - compare(правильный код)
 * Переопределены hashCode(), toString(), equals().
 */

final class Code {
    private final char[] code;

    Code (String code) {
        this.code = code.toCharArray();
    }

    static Code generateCode(int difficult) {
        final StringBuilder codeString = new StringBuilder();
        final int length;
        final ArrayList<Character> symbolsArray = new ArrayList<Character>(62);

        if (difficult % 2 == 0) {
            length = 4;
        } else {
            length = 6;
        }

        if (difficult / 2 > 1) {
            for (char ch = 'a'; ch < 'z'; ch++) {
                symbolsArray.add(ch);
            }
        }
        if (difficult / 2 > 0) {
            for (char ch = 'A'; ch < 'Z'; ch++) {
                symbolsArray.add(ch);
            }
        }

        for (char ch = '0'; ch < '9'; ch++){
            symbolsArray.add(ch);
        }

        final Random random = new Random(System.currentTimeMillis());

        for (int i = 0; i < length; i++) {
            codeString.append(symbolsArray.remove(random.nextInt(symbolsArray.size())));
        }

        return new Code(codeString.toString());
    }

    String compare(Code rightCode) {
        if (code.length != rightCode.code.length) return "Длины кодов не совпадают";
        int bulls = 0, cows = 0;

        for (int i = 0; i < code.length; i++) {
            for (int j = 0; j < code.length; j++) {
                if (code[i] == rightCode.code[j]) {
                    if (i == j) {
                        bulls++;
                    } else {
                        cows++;
                    }
                }
            }
        }

        String bullsStr = " бык", cowsStr = " корова";
        if (bulls == 0 || bulls > 4) {
            bullsStr = " быков";
        } else if (bulls > 1 && bulls < 5) {
            bullsStr = " быка";
        }
        if (cows == 0 || cows > 4) {
            cowsStr = " коров";
        } else if (cows > 1 && cows < 5) {
            cowsStr = " коровы";
        }

        return cows + cowsStr + ", " + bulls + bullsStr;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (char ch: code) {
            string.append(code);
        }
        return string.toString();
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(code);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (!(obj instanceof Code)) return false;
        Code other = (Code) obj;
        if (code.length != other.code.length) return false;
        for (int i = 0; i < code.length; i++) {
            if (code[i] != other.code[i]) return false;
        }
        return true;
    }
}
