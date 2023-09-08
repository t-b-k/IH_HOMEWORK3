package phone_book.view;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Viewer {
    public String getUserInput (String prompt) {
        System.out.print(String.format("%s => ", prompt));
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public void showWarningMessage(String message) {
        System.out.println("\u001B[33m"+message+"\u001B[0m");
    }
    public void showInfoMessage(String message) {

        System.out.println("\u001B[32m"+message+"\u001B[0m");
    }
    public void showErrorMessage (String message) {
        System.out.println("\u001B[31m"+message+"\u001B[0m");
    }
}
