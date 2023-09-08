package phone_book.model;

import phone_book.exceptions.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.LinkedList;
import java.util.List;

public class DataBase {
    private final int PERSON_FIELDS_QTY = 6;
    private String dbPath;
    private String dbName;

    public DataBase() {
        this.dbPath = System.getProperty("user.dir");
        this.dbName = "DB";
    }
    public DataBase(String path, String dbName) {
        this.dbPath = path;
        this.dbName = dbName;
    }
    public DataBase(String dbName) {
        this.dbPath = System.getProperty("user.dir");
        this.dbName = dbName;
    }
    public String getDbPath() {
        return dbPath;
    }
    public String getDbName() {
        return dbName;
    }
    public int checkFieldsQty (String[] fields) {
        return ((Integer)fields.length).compareTo(PERSON_FIELDS_QTY);
    }
    public boolean isCorrectData (String[] fields) throws IncorrectInputDataException {
        boolean result = false;
        if (!isNameOrLastName(fields[0])) throw new IncorrectLastNameFormat();
        if (!isNameOrLastName(fields[1])) throw new IncorrectNameFormat();
        if (!isSecondName(fields[2])) throw new IncorrectSecondNameFormat();
        if (!isDate(fields[3])) throw new IncorrectBirthDateFormat();
        if (!isPhoneNumber(fields[4])) throw new IncorrectPhoneNumberFormat();
        if (!isGender(fields[5])) throw new IncorrectGenderFormat();
        result = true;
        return result;
    }
    private boolean isNameOrLastName (String word) {
        return word.matches("^[a-zA-Zа-яА-Я\\-]*$");
    }
    private boolean isSecondName (String word) {
        return word.matches("^[a-zA-Zа-яА-Я]*$");
    }
    private boolean consistsOfDigitsAndPoints (String word) {
        return word.matches("^[0-9.]*$");
    }
    private boolean consistsOfDigitsAndSlashes (String word) {
        return word.matches("^[0-9/]*$");
    }
    private boolean consistsOfDigitsAndDashes (String word) {
        return word.matches("^[0-9\\-]*$");
    }
    private boolean isDate(String word) {
        boolean result = false;
        String substr = word;
        String delimiter;
        if (consistsOfDigitsAndPoints(word)) delimiter = ".";
        else if (consistsOfDigitsAndSlashes(word)) delimiter = "/";
        else if (consistsOfDigitsAndDashes(word)) delimiter = "-";
        else return result;
        int index = substr.indexOf(delimiter);
        if (!((index == 1) || (index == 2))) return result;
        int date = Integer.parseInt(substr.substring(0,index));
        if (date < 1 || date > 31) return result;
        substr = substr.substring(index + 1, substr.length());
        index = substr.indexOf(delimiter);
        if (!((index == 1) || (index == 2))) return result;
        int month = Integer.parseInt(substr.substring(0,index));
        if (month < 1 || month > 12) return result;
        substr = substr.substring(index + 1, substr.length());
        int year = Integer.parseInt(substr);
        if (year < 1900 || year > 2022) return result;
        else result = true;
        return result;
    }
    private boolean isGender (String word) {
        String gender = word.toLowerCase();
        return gender.equals("f") ||
                gender.equals("m") ||
                gender.equals("м") ||
                gender.equals("ж");
    }

    private boolean isPhoneNumber (String word) {
        return word.length() == 7 && word.matches("^[0-9/]*$");
    }
    public boolean addRecord (String[] personData) throws DataBaseOperationException {
        boolean result = false;
        String stringToRecord = "";
        if (personData.length == PERSON_FIELDS_QTY) {
            for (String elem : personData) {
                System.out.println(elem.isEmpty());
                stringToRecord = stringToRecord.concat(String.format("<%s>", elem));
                System.out.println(stringToRecord);
            }
            stringToRecord.concat("\n");
            String txtFileName = personData[0].concat(".txt");
//            ДЛЯ ПРОВЕРКИ НА ОШИБКИ ПРИ РАБОТЕ С ФАЙЛОВОЙ СИСТЕМОЙ
//              String fullFileName = String.format("%ssd;lf\\%s\\%s", dbPath, dbName, txtFileName);
//            *****************************************************
            String fullFileName = String.format("%s\\%s\\%s", dbPath, dbName, txtFileName);
            File file = new File(fullFileName);
            if (!file.exists()) {
                try {
                    if (file.createNewFile()) System.out.println(String.format("Создали новый файл: %s", txtFileName));
                } catch (IOException e) {
                    throw new CouldNotCreateFileException();
                }
            }
            try (FileWriter fw = new FileWriter(file, true);) {
//            ДЛЯ ПРОВЕРКИ НА ОШИБКИ ПРИ РАБОТЕ С ФАЙЛОВОЙ СИСТЕМОЙ
//                System.out.println("Открыли FileWriter");
//                fw.close();
//                System.out.println("Закрыли FileWriter");
//            *****************************************************
                fw.write(stringToRecord);
                fw.write("\n");
            } catch (IOException e) {
                throw new CouldNotOpenFileException();
            }
            result = true;
        }
        return result;
    }
}
