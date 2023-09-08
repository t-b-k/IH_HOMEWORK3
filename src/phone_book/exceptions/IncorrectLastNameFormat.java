package phone_book.exceptions;

public class IncorrectLastNameFormat extends IncorrectInputDataException {
    private String message;
    public IncorrectLastNameFormat() {
        super();
        this.message = "НЕВЕРНЫЙ ФОРМАТ ФАМИЛИИ. ПОПРОБУЙТЕ ЕЩЕ РАЗ!!!";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
