package util;

public class NumberHelper {

    public static int getValidateId(String strId) {
        int number = -1;
        try {
            number = Integer.parseInt(strId);
        } catch (NumberFormatException e) {
            e.printStackTrace(System.out);
        }
        return number;
    }
}
