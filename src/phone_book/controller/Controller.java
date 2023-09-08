package phone_book.controller;

import phone_book.exceptions.DataBaseOperationException;
import phone_book.exceptions.IncorrectInputDataException;
import phone_book.model.DataBase;
import phone_book.view.Viewer;

import java.util.Arrays;

public class Controller {
    private final Viewer viewer;
    private final DataBase dataBase;
    public Controller(Viewer viewer, DataBase dataBase) {
        this.viewer = viewer;
        this.dataBase = dataBase;
    }
    public void run() throws DataBaseOperationException {
        String prompt = "Программа добавления данных абонентов в базу... \n" +
                "Данные пользователя должны разделяться пробелом и иметь следующую последовательность и состав: \n" +
                "Фамилия - буквы и (возможно) тире; \nИмя - буквы и (возможно) тире; \n"+
                "Отчество - буквы; Дата_рождения - ДД/ММ/ГГГГ или ДД-ММ-ГГГГ или ДД.ММ.ГГГГ;\n"+
                "Номер_телефона - 7 цифр;\nПол - одна буква (м/ж/f/F, регистр неважен).\n";
        String inputLine = viewer.getUserInput(prompt);
        String[] fields = new String[0];
//        Person person;
        prompt = "Введите данные пользователя или Q (для выхода): \n";
        while (true) {
            if (inputLine.toLowerCase().equals("q")) return;
            fields = inputLine.split(" ");
            System.out.println(Arrays.toString(fields));
            int dataQtyCheck = dataBase.checkFieldsQty(fields);
            if (dataQtyCheck == -1) viewer.showWarningMessage("НЕПОЛНЫЕ ДАННЫЕ!!! \n" +
                    "Данные пользователя должны разделяться пробелом и иметь следующую последовательность и состав: \n" +
                    "Фамилия - буквы и (возможно) тире; \nИмя - буквы и (возможно) тире; \n"+
                    "Отчество - буквы; Дата_рождения - ДД/ММ/ГГГГ или ДД-ММ-ГГГГ или ДД.ММ.ГГГГ;\n"+
                    "Номер_телефона - 7 цифр;\nПол - одна буква (м/ж/f/F, регистр неважен).\n");
            else if (dataQtyCheck == 1) viewer.showWarningMessage("СЛИШКОМ МНОГО ДАННЫХ!!! \n" +
                    "Данные пользователя должны разделяться пробелом и иметь следующую последовательность и состав: \n" +
                    "Фамилия - буквы и (возможно) тире; \nИмя - буквы и (возможно) тире; \n"+
                    "Отчество - буквы; Дата_рождения - ДД/ММ/ГГГГ или ДД-ММ-ГГГГ или ДД.ММ.ГГГГ;\n"+
                    "Номер_телефона - 7 цифр;\nПол - одна буква (м/ж/f/F, регистр неважен).\n");
            else {
                boolean dataFormatChecked = false;
                try {
                    dataFormatChecked = dataBase.isCorrectData(fields);
                } catch (IncorrectInputDataException e) {
                    viewer.showWarningMessage(e.getMessage());
                }
                if (dataFormatChecked) {
                    try {
                        dataBase.addRecord(fields);
                    } catch (DataBaseOperationException e) {
//                        System.out.println(e.getMessage());
                        e.printStackTrace();
                        return;
                    }
                }
            }
            inputLine = viewer.getUserInput(prompt);
        }
    }
}
