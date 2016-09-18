package task3.filesSearcher.handlers;

import java.io.File;
import java.util.Date;

public abstract class Searcher {

    private String identifier;
    protected String id;
    private String startPath = "E://StartPath"; //начальная папка для поиска
    private String fullFileName;  //  результатом поиска является полный путь файла
    private String filePath;
    private long fileSize;
    private Date fileDate;
    private File topDirectory = new File(startPath);
    private Searcher nextSearcher;  // ссылка на следующий экземпляр обработчика

    //конструктор принимает на фход идентификатор, по которому определяется, может ли экземпляр его обработать
    public Searcher(String identifier) {
        this.identifier = identifier;
    }

    //проверяется, может ли экземпляр обработать даннные или передать следующему
    void matching() {
        if(id.equals(identifier)) {
            printMessage();
            search();
        } else if (nextSearcher != null) {
            nextSearcher.matching();
        }

    }

    //если экземпляр может обработать, запускается поиск
    //метод поиск одинаков для всех наследников, но по разным критериям
    void search() {
        File[]list = topDirectory.listFiles();
        for(File file : list) {
            if(file.isFile()) {
                fullFileName = file.getName();
                filePath = file.getAbsolutePath();
                fileSize = file.length();
                fileDate = new Date(file.lastModified());
                checkFile();
            } else {
                topDirectory = new File(file.getAbsolutePath());
                search();
            }
        }
    }

    //у наследников задается критерий поиска, обязателен для определения
    abstract void printMessage();

    //происходит проверка по критерию
    abstract void checkFile();

    public String getFilePath() {
        return filePath;
    }

    public String getFullFileName() {
        return fullFileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public Date getFileDate() {
        return fileDate;
    }

    public void setNextSearcher(Searcher nextSearcher) {
        this.nextSearcher = nextSearcher;
    }

}
