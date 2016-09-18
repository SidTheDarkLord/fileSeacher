package task3.filesSearcher.handlers;


import java.util.Scanner;

public class ExtentionSearcher extends Searcher {

    private String fileExt;
    private String ext;
    private String id = "extention";

    public ExtentionSearcher(String identifier) {
        super(identifier);
        super.id = id;
        matching();
    }

    @Override
    void checkFile() {
        fileExt = getFullFileName().substring(getFullFileName().lastIndexOf('.'), getFullFileName().length());
        if(fileExt.equals(ext)) {
            System.out.println(getFilePath());
        }
    }

    @Override
    void printMessage() {
        System.out.println("Введите расширение файла (начиная с точки)");
        Scanner in = new Scanner(System.in);
        ext = in.nextLine();
    }
}
