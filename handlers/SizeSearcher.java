package task3.filesSearcher.handlers;


import java.util.Scanner;

public class SizeSearcher extends Searcher {

    private int minSize;
    private int maxSize;
    private String id = "size";

    public SizeSearcher(String identifier) {
        super(identifier);
        super.id = id;
        matching();
    }

    @Override
    void checkFile() {
        if(getFileSize() < maxSize && getFileSize() > minSize) {
            System.out.println(getFilePath());
        }
    }

    @Override
    void printMessage() {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите минимальный размер файла (в мегабайтах)");
        minSize = in.nextInt()*1048576;
        in.nextLine();
        System.out.println("Введите максимальный размер файла (в мегабайтах)");
        maxSize = in.nextInt()*1048576;
    }
}
