package phone_book.exceptions;

import java.io.IOException;

public class CouldNotCreateFileException extends DataBaseOperationException {
    private String message;
    public CouldNotCreateFileException() {
        super();
        this.message = super.getMessage().concat("НЕВОЗМОЖНО СОЗДАТЬ ФАЙЛ.");
    }

    @Override
    public String getMessage() {
        return message;
    }
}
