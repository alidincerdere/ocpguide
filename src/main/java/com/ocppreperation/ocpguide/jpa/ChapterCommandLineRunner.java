package com.ocppreperation.ocpguide.jpa;

import com.ocppreperation.ocpguide.Service.ChapterBinder;
import com.ocppreperation.ocpguide.Service.FileProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by adere on 18.01.2019.
 */
@Component
public class ChapterCommandLineRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ChapterCommandLineRunner.class);

    @Autowired
    ChapterRepository repository;

    @Autowired
    FileProcessor fileProcessor;

    @Autowired
    ChapterBinder chapterBinder;

    @Override
    public void run(String... strings) throws Exception {

        log.info("setting up DB...");


        List<Chapter> chapters = fileProcessor.readChaptersFromCSV();

        //bind next and previous pointers
        chapterBinder.bind(chapters);

        //save chapters
        repository.saveAll(chapters);


        log.info("DB Setup complete");


    }
}
