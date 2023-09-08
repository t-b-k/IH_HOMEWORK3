package phone_book.exceptions;

public class IncorrectGenderFormat extends IncorrectInputDataException{
    private String message;
    public IncorrectGenderFormat() {
        super();
        this.message = "НЕВЕРНЫЙ ФОРМАТ ПРИЗНАКА ПОЛА. ПОПРОБУЙТЕ ЕЩЕ РАЗ";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
