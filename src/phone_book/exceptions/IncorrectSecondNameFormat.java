package phone_book.exceptions;

public class IncorrectSecondNameFormat extends IncorrectInputDataException {
    private String message;
    public IncorrectSecondNameFormat() {
        super();
        this.message = "НЕВЕРНЫЙ ФОРМАТ ОТЧЕСТВА. ПОПРОБУЙТЕ ЕЩЕ РАЗ!!!";
    }
    @Override
    public String getMessage() {
        return message;
    }
}
