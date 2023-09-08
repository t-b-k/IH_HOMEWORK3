package phone_book.utils;

import phone_book.exceptions.DBCreationErrorException;
import phone_book.model.DataBase;
import phone_book.view.Viewer;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;

public class DBConnector {
    public static final String DB_NAME = "DB";
    public static DataBase createDB(Viewer viewer) throws DBCreationErrorException {
        String prompt = "Введите название каталога, в котором находится (или должна быть создана) база данных." +
                "По умолчанию будет использоваться имя \"DB\"\n";
        String dataBaseName = viewer.getUserInput(prompt);
        if (dataBaseName == "") dataBaseName = DB_NAME;
        prompt = "Введите полный путь к этому каталогу (по умолчанию будет использоваться текущий). \n";
        String path = viewer.getUserInput(prompt);
        if (path == "") {
            try {
                path = new File(".").getCanonicalPath();
                System.out.printf("Текущим является каталог: %s\n", path);
            } catch (IOException e) {
                throw new DBCreationErrorException();
            }
        }
        System.out.println("Path = " + path);
        String dirName = String.format("%s\\%s", path, dataBaseName);
        File dir = new File(dirName);
        if (!dir.exists()) {
            boolean created = dir.mkdir();
            if (created)
                viewer.showInfoMessage(String.format("Создан новый каталог базы данных с именем %s", dataBaseName));
            else throw new DBCreationErrorException();
//                viewer.showErrorMessage(String.format("Невозможно сосздать папку %s в папке %s. ", dirName));
        } else {
            viewer.showInfoMessage(String.format("Каталог с именем %s существует. База данных будет храниться в нем. ", dataBaseName));
        }
        return new DataBase(path, dataBaseName);
    }
}
