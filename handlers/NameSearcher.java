package task3.filesSearcher.handlers;

import java.util.Scanner;

public class NameSearcher extends Searcher {

    private String fileName;
    private String name;
    private String id = "name";

    public NameSearcher(String identifier) {
        super(identifier);
        super.id = id;
        matching();
    }

    @Override
    void checkFile() {
        fileName = getFullFileName().substring(0, getFullFileName().lastIndexOf('.'));
        if(fileName.equals(name)) {
            System.out.println(getFilePath());
        }
    }

    @Override
    void printMessage() {
        System.out.println("Введите имя файла");
        Scanner in = new Scanner(System.in);
        name = in.nextLine();
    }
}
