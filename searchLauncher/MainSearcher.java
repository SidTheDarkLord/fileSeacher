package task3.filesSearcher.searchLauncher;


import task3.filesSearcher.handlers.*;

import java.util.Scanner;

public class MainSearcher {
    
    public static void main(String[] args)  {

        String identifier = null;
        String [] messages = new String[4];
        messages[0] = "Искать по имени файла? 1/0";
        messages[1] = "Искать по расширению файла? 1/0";
        messages[2] = "Искать по размеру файла? 1/0";
        messages[3] = "Искать по дате изменения файла? 1/0";

        int i;
        for(i = 0; i < messages.length; i++) {
            System.out.println(messages[i]);
            Scanner in = new Scanner(System.in);
            if(in.nextInt() == 1) {
                break;
            }
        }
        switch (i) {
            case 0: identifier = "name"; break;
            case 1: identifier = "extention"; break;
            case 2: identifier = "size"; break;
            case 3: identifier = "date"; break;
        }

        Searcher search1 = new NameSearcher(identifier);
        Searcher search2 = new ExtentionSearcher(identifier);
        Searcher search3 = new SizeSearcher(identifier);
        Searcher search4 = new DateSearcher(identifier);

        //создаются ссылки на следующий экземпляр обработчика
        //экземпляры сами решают, могут ли обработать данные
        search1.setNextSearcher(search2);
        search2.setNextSearcher(search3);
        search3.setNextSearcher(search4);

    }

}
