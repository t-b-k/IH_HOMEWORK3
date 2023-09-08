package phone_book.exceptions;

public class IncorrectBirthDateFormat extends IncorrectInputDataException {
    private String message;
    public IncorrectBirthDateFormat() {
        super();
        this.message = "НЕВЕРНЫЙ ФОРМАТ ДАТЫ РОЖДЕНИЯ. ПОПРОБУЙТЕ ЕЩЕ РАЗ!!!";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
