package phone_book;

import phone_book.controller.Controller;
import phone_book.exceptions.DBCreationErrorException;
import phone_book.exceptions.DataBaseOperationException;
import phone_book.model.DataBase;
import phone_book.view.Viewer;

import java.io.File;
import java.io.IOException;

import static phone_book.utils.DBConnector.createDB;

public class Main {
    public static void main(String[] args)  {
        Viewer viewer = new Viewer();
        DataBase dataBase = null;
        try {
            dataBase = createDB(viewer);
        } catch (DBCreationErrorException e) {
            e.printStackTrace();
            return;
        }
        Controller controller = new Controller(viewer, dataBase);
        try {
            controller.run();
        } catch (DataBaseOperationException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }
    }
}