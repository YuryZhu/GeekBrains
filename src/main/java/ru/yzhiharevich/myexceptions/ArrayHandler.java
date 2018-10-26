package ru.yzhiharevich.myexceptions;

public class ArrayHandler {

    public static void handlerOfArray(String[][] arrayStr) throws MySizeArrayException {
        if (arrayStr.length != 4) throw new MySizeArrayException("Массив должен быть 4 на 4");
        else {
            int sum = 0;
            int j;
            int valueOfIndex = 0;
            for (int i = 0; i < arrayStr.length; i++) {
                for (j = 0; j < arrayStr[i].length; j++) {
                    try {
                        boolean canProceed = checkArray(arrayStr[i][j]);
                        if (canProceed) {
                            valueOfIndex = Integer.parseInt(arrayStr[i][j]);
                            sum += valueOfIndex;
                        } else {
                            throw new MyArrayDataException("Не корректные данные в столбце: " + (i + 1) + ", поле в строке: " + (j + 1) + ", информация в поле:" + arrayStr[i][j]);
                        }
                    } catch (MyArrayDataException ex) {
                        ex.printStackTrace();
                    }
                }
                if (j != 4) throw new MySizeArrayException("Массив должен быть 4 на 4");
            }
            System.out.println(sum);
        }
    }

    public static boolean checkArray(String arrayIndex) {
        String str = arrayIndex;
        for (char c : str.toCharArray())
            if (c < '0' || c > '9') return false;
        return true;
    }
}
