package phone_book.exceptions;

public class IncorrectPhoneNumberFormat extends IncorrectInputDataException {
    private String message;
    public IncorrectPhoneNumberFormat() {
        super();
        this.message = "НЕВЕРНЫЙ ФОРМАТ НОМЕРА ТЕЛЕФОНА. ПОПРОБУЙТЕ ЕЩЕ РАЗ!!!";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
