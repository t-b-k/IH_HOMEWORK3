package phone_book.exceptions;

import java.io.IOException;

public class CouldNotOpenFileException extends DataBaseOperationException {
    private String message;
    public CouldNotOpenFileException() {
        super();
        this.message = super.getMessage().concat("НЕВОЗМОЖНО ОТКРЫТЬ ФАЙЛ НА ДОЗАПИСЬ.");
    }

    @Override
    public String getMessage() {
        return message;
    }
}
