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
    public static String CONTENT_TEMPLATE_FILE_NAME = "context/templateContentFile.txt";
    public static String CONTENT_DIR = "context/";

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

    public void createContentFileIfNotExist(String filename) {
        try {

            //Path currentRelativePath = Paths.get("");
            //String workingDir = currentRelativePath.toAbsolutePath().toString();

            //System.out.println("Working Directory is:" + workingDir);

            InputStream inStream = null;
            OutputStream outStream = null;

            File file = new File(CONTENT_DIR + filename);

            if (file.createNewFile()){

                File templateFile =new File(CONTENT_TEMPLATE_FILE_NAME);

                inStream = new FileInputStream(templateFile);
                outStream = new FileOutputStream(file);

                byte[] buffer = new byte[1024];

                int length;
                //copy the file content in bytes
                while ((length = inStream.read(buffer)) > 0){

                    outStream.write(buffer, 0, length);

                }

                inStream.close();
                outStream.close();

                System.out.println("File is copied successful!");

                System.out.println("File is created!");
            }else{
                System.out.println("File already exists.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
