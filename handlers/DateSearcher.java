package task3.filesSearcher.handlers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class DateSearcher extends Searcher {

    private String id = "date";
    private String str1;
    private String str2;
    private Date minDate;
    private Date maxDate;

    public DateSearcher(String identifier) {
        super(identifier);
        super.id = id;
        matching();
    }

    @Override
    void checkFile() {
        if(getFileDate().after(minDate) && getFileDate().before(maxDate)) {
            System.out.println(getFilePath());
        }
    }

    @Override
    void printMessage() {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите первую дату в формате: dd-MM-yy hh:mm");
        str1 = in.nextLine();
        System.out.println("Введите вторую дату в формате: dd-MM-yy hh:mm");
        str2 = in.nextLine();

        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yy hh:mm");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yy hh:mm");

        try {
            minDate = sdf1.parse(str1);
            maxDate = sdf2.parse(str2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}