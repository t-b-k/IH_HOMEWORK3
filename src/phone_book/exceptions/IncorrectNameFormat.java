package phone_book.exceptions;

public class IncorrectNameFormat extends IncorrectInputDataException{
    private String message;
    public IncorrectNameFormat() {
        super();
        this.message = "НЕВЕРНЫЙ ФОРМАТ ИМЕНИ. ПОПРОБУЙТЕ ЕЩЕ РАЗ!!!";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
