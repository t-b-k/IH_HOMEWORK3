package phone_book.model;

import phone_book.exceptions.IncorrectInputDataException;

public class Person {
    private String lastName;
    private String name;
    private String secondName;

    private String dateOfBirth;
    private String phoneNumber;
    private String gender;

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public Person(String lastName, String name, String secondName, String dateOfBirth, String phoneNumber, String gender) {
        this.lastName = lastName;
        this.name = name;
        this.secondName = secondName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }
//    public Person (String[] fields) throws RuntimeException {
//        if (isNameOrLastName(fields[0])) this.lastName = fields[0];
//        else throw new IncorrectInputDataException("ИСКЛЮЧЕНИЕ: НЕВЕРНЫЙ ФОРМАТ ФАМИЛИИ (Фамилия может состоять " +
//                "только из букв и тире (если фамилия двойная или тройная)).");
//        if (isNameOrLastName(fields[1])) this.name = fields[1];
//        else throw new IncorrectInputDataException("ИСКЛЮЧЕНИЕ: НЕВЕРНЫЙ ФОРМАТ ИМЕНИ (Имя может состоять " +
//                "только из букв и тире (если имя двойное или множественное).");
//        if (isSecondName(fields[2])) this.secondName = fields[2];
//        else throw new IncorrectInputDataException("ИСКЛЮЧЕНИЕ: НЕВЕРНЫЙ ФОРМАТ ОТЧЕСТВА (Отчество может состоять" +
//                "только из букв).");
//        if (isDate(fields[3])) this.dateOfBirth = fields[3];
//        else throw new IncorrectInputDataException("ИСКЛЮЧЕНИЕ: НЕВЕРНЫЙ ФОРМАТ ДАТЫ РОЖДЕНИЯ (Дата должна иметь" +
//                "формат DD.MM.YYYY или DD/MM/YYYY или DD-MM-YYYY, день, месяц и год должны иметь реальные значения.");
//        if (isPhoneNumber(fields[4]))  this.phoneNumber = fields[4];
//        else throw new IncorrectInputDataException("ИСКЛЮЧЕНИЕ: НЕВЕРНЫЙ ФОРМАТ НОМЕРА ТЕЛЕФОНА (Телефон должен "+
//                "состоять из 10-ти цифр.");
//        if (isGender(fields[5])) this.gender = fields[5];
//        else throw new IncorrectInputDataException("ИСКЛЮЧЕНИЕ: НЕВЕРНЫЙ ФОРМАТ ПОЛА (Пол обозначается одним "+
//                "из символов: м/М/ж/Ж/m/M/f/F).");
//        }
    public String toDBLine() {
        return String.format("<%s><%s><%s><%s><%s><%s>\n", lastName, name, secondName, dateOfBirth, phoneNumber, gender);
    }
    @Override
    public String toString() {
        return String.format("%s %s %s, рожд. %s, тел: %s, пол: %s\n", lastName, name, secondName, dateOfBirth, phoneNumber, gender);
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
    private boolean isYear (String word) {
        return word.matches("^[0-9]*$");
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
        return word.length() == 10 && word.matches("^[0-9/]*$");
    }
}
