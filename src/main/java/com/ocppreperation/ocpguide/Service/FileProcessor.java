package com.ocppreperation.ocpguide.Service;

import com.ocppreperation.ocpguide.Model.*;
import com.ocppreperation.ocpguide.jpa.Chapter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adere on 28.01.2019.
 */
@Component
public class FileProcessor {

    public static String INDEX_CSV_FILENAME = "context/index.csv";
    public static String CONTENT_TEMPLATE_FILE_NAME = "context/templateContentFile.xml";
    public static String EXAM_TEMPLATE_FILE_NAME = "context/templateExamFile.xml";
    public static String CONTENT_DIR = "context/";

    public static String DESCRIPTION_START_TAG = "Description Start:";
    public static String DESCRIPTION_END_TAG = "Description End:";

    public static String CODE_START_TAG = "Code Snipped Start:";
    public static String CODE_END_TAG = "Code Snipped End:";

    public static String QUESTION_XML_TAG = "Question";
    public static String TEXT_XML_TAG = "Text";
    public static String CODE_XML_TAG = "Code";
    public static String OPTIONS_XML_TAG = "Options";
    public static String OPTION_XML_TAG = "Option";
    public static String ANSWER_XML_TAG = "Answer";
    public static String EXPLANATION_XML_TAG = "Explanation";
    public static String CORRECT_ANSWERS_XML_TAG = "CorrectOptions";

    public static String DESCRIPTION_XML_TAG = "Description";

    public static String CODE_SNIPPED_XML_TAG = "CodeSnipped";




    public static String[] headers = {"Chapter", "Name"};

    public List<Question> readQuestionsFromXml(String fileName) {

        List<Question> questions = new ArrayList<>();

        try {
            File fXmlFile = new File(CONTENT_DIR + fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName(QUESTION_XML_TAG);

            for(int i=0; i<nList.getLength(); i++) {

                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    Question question = new Question();

                    question.setText(eElement.getElementsByTagName(TEXT_XML_TAG).item(0).getTextContent());

                    question.setNumber(i+1);

                    question.setCode(eElement.getElementsByTagName(CODE_XML_TAG).item(0).getTextContent());



                    Element optionsMain = (Element) eElement.getElementsByTagName(OPTIONS_XML_TAG).item(0);

                    NodeList options = optionsMain.getElementsByTagName(OPTION_XML_TAG);

                    List<Option> optionList = new ArrayList<>();

                    for(int j=0; j<options.getLength(); j++) {

                        Element optionElement = (Element) options.item(j);

                        Option option = new Option();
                        option.setLetter(OptionLetter.values()[j]);
                        option.setOptionText(optionElement.getTextContent());
                        optionList.add(option);

                    }

                    question.setOptions(optionList);


                    Element answerXml = (Element) eElement.getElementsByTagName(ANSWER_XML_TAG).item(0);

                    Element explanation = (Element) answerXml.getElementsByTagName(EXPLANATION_XML_TAG).item(0);

                    Element correctAnswersXml = (Element) answerXml.getElementsByTagName(CORRECT_ANSWERS_XML_TAG).item(0);


                    Answer answer = new Answer();
                    answer.setExplanation(explanation.getTextContent());

                    question.setSerializedCorrectAnswer(correctAnswersXml.getTextContent());

                    String[] correctOptions = correctAnswersXml.getTextContent().split(",");

                    List<Option> correctAnswers = new ArrayList<>();

                    for (String letter: correctOptions) {
                        Option correctOption = new Option();
                        correctOption.setLetter(OptionLetter.valueOf(letter));
                        correctAnswers.add(correctOption);
                    }

                    answer.setCorrectOptions(correctAnswers);

                    question.setCorrectAnswer(answer);

                    questions.add(question);
                }

            }



        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return questions;
    }


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
                PageType pageType = record.get(2) == null || record.get(2).contentEquals("") ? PageType.LECTURE : PageType.EXAM;
                chapters.add(new Chapter(record.get(0), record.get(1), pageType));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return chapters;
    }

    public List<PageComponent> readContentFromXml(String fileName) {
        List<PageComponent> pageComponentList = new ArrayList<>();


        try {
            File fXmlFile = new File(CONTENT_DIR + fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nodeList = doc.getDocumentElement().getChildNodes();

            for (int i=0; i<nodeList.getLength(); i++) {

                PageComponent pageComponent = new PageComponent();

                Node node = nodeList.item(i);

                if(node.getNodeName().contentEquals(DESCRIPTION_XML_TAG) || node.getNodeName().contentEquals(CODE_SNIPPED_XML_TAG)) {

                    if (node.getNodeName().contentEquals(DESCRIPTION_XML_TAG)) {
                        pageComponent.setComponentType(ComponentType.DESCRIPTION);
                    } else {
                        pageComponent.setComponentType(ComponentType.CODE_SNIPPED);
                    }
                    String[] contentLines = node.getTextContent().split("\\n");
                    List<String> content = new ArrayList<>();
                    for (String contentLine : contentLines) {
                        content.add(contentLine);
                    }

                    pageComponent.setContent(content);
                    pageComponentList.add(pageComponent);
                }
            }


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }


        return pageComponentList;
    }
/*
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
    */
    public void createContentFileIfNotExist(String filename, PageType pageType) {
        try {

            //Path currentRelativePath = Paths.get("");
            //String workingDir = currentRelativePath.toAbsolutePath().toString();

            //System.out.println("Working Directory is:" + workingDir);

            InputStream inStream = null;
            OutputStream outStream = null;

            File file = new File(CONTENT_DIR + filename);

            if (file.createNewFile()){

                File templateFile = null;
                if(pageType == PageType.LECTURE) {
                    templateFile = new File(CONTENT_TEMPLATE_FILE_NAME);
                } else {
                    templateFile = new File(EXAM_TEMPLATE_FILE_NAME);
                }

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
