package com.ocppreperation.ocpguide.Service;

import com.ocppreperation.ocpguide.Model.ComponentType;
import com.ocppreperation.ocpguide.Model.PageComponent;
import com.ocppreperation.ocpguide.jpa.Chapter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adere on 28.01.2019.
 */
@Component
public class FileProcessor {

    public static String INDEX_CSV_FILENAME = "context/index.csv";

    public static String DESCRIPTION_START_TAG = "Description Start:";
    public static String DESCRIPTION_END_TAG = "Description End:";

    public static String CODE_START_TAG = "Code Snipped Start:";
    public static String CODE_END_TAG = "Code Snipped End:";

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

            for (CSVRecord record : records) {
                chapters.add(new Chapter(record.get(0), record.get(1)));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return chapters;
    }

    public List<PageComponent> readContentFromTxt(String fileName) {

        List<PageComponent> pageComponentList = new ArrayList<>();

        BufferedReader br = null;
        FileReader fr = null;

        try {
            fr = new FileReader("context/" + fileName);
            br = new BufferedReader(fr);

            String sCurrentLine;

            System.out.println("reading input file...");

            int state = 0;

            PageComponent pageComponent = new PageComponent();
            while ((sCurrentLine = br.readLine()) != null) {

                switch (state) {
                    case 0: //start reading search for tag


                        if (sCurrentLine.contains(DESCRIPTION_START_TAG)) {
                            state = 1;
                            pageComponent = new PageComponent();
                            pageComponent.setComponentType(ComponentType.DESCRIPTION);
                            continue;
                        }

                        if (sCurrentLine.contains(CODE_START_TAG)) {
                            state = 2;
                            pageComponent = new PageComponent();
                            pageComponent.setComponentType(ComponentType.CODE_SNIPPED);
                            continue;
                        }

                        break;

                    case 1: //description reading started search for description end

                        if (sCurrentLine.contains(DESCRIPTION_END_TAG)) {
                            state = 0;
                            pageComponentList.add(pageComponent);
                            continue;
                        }
                        pageComponent.getContent().add(sCurrentLine);
                        break;

                    case 2: // search for code snipped end

                        if (sCurrentLine.contains(CODE_END_TAG)) {
                            state = 0;
                            pageComponentList.add(pageComponent);
                            continue;
                        }
                        pageComponent.getContent().add(sCurrentLine);

                        break;


                }


            }
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {

            try {

                if (br != null) {
                    br.close();
                }

                if (fr != null) {
                    fr.close();
                }

            } catch (IOException ex) {

                //ex.printStackTrace();
                System.out.println(ex.getMessage());

            }

        }

        return pageComponentList;
    }
}
