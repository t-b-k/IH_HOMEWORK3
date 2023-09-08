package phone_book.exceptions;

import java.io.IOException;

public class DataBaseOperationException extends IOException {
    private String message;
    public DataBaseOperationException() {
        super();
        this.message = "ОШИБКА БАЗЫ ДАННЫХ\n";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
