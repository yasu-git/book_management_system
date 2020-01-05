package models.validators;

import java.util.ArrayList;
import java.util.List;

public class IsbnValidator {
    public static List<String> validate(String isbn) {
        List<String> errors = new ArrayList<String>();

        String number_error = _validateNumber(isbn);

        if(!number_error.equals("")){
            errors.add(number_error);
        }

        String null_error = _validateNull(isbn);

        if(!null_error.equals("")){
            errors.add(null_error);
        }

        String figureLength_error = _validateFigureLength(isbn);

        if(!figureLength_error.equals("")){
            errors.add(figureLength_error);
        }

        return errors;
    }

    private static String _validateNumber(String isbn){
        try {
            Long.parseLong(isbn);
            } catch (NumberFormatException e) {
            return "数字を入力してください";
        }

        return "";
    }

    private static String _validateNull(String isbn){
        if(isbn == null || isbn.equals("")) {
            return "ISBNコードを入力してください。";
            }

        return "";
    }

    private static String _validateFigureLength(String isbn){
        if(isbn.length() == 13 || isbn.length() == 10) {
            return "ISBN13かISBN10で入力してください";
            }

        return "";
    }
}
