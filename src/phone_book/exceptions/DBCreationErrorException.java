package phone_book.exceptions;

import java.io.IOException;

public class DBCreationErrorException extends IOException {
    private String message;
    public DBCreationErrorException() {
        super();
        this.message = "ОШИБКА ПРИ ПОПЫТКЕ СОЗДАТЬ ПАПКУ ДЛЯ БАЗЫ ДАННЫХ";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
