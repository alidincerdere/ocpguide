package com.ocppreperation.ocpguide.Service;

import com.ocppreperation.ocpguide.jpa.Chapter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adere on 28.01.2019.
 */
@Component
public class FileProcessor {

    public static String INDEX_CSV_FILENAME = "context/index.csv";

    public static String[] headers = {"Chapter", "Name"};


    public List<Chapter> readChaptersFromCSV() {

        List<Chapter> chapters = new ArrayList<>();


        try {
            Reader in = new FileReader(INDEX_CSV_FILENAME);

            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withDelimiter(';')
                    .withHeader(headers)
                    .withFirstRecordAsHeader()
                    .parse(in);

            for(CSVRecord record : records) {
               chapters.add( new Chapter(record.get(0), record.get(1)));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return chapters;
    }
}
