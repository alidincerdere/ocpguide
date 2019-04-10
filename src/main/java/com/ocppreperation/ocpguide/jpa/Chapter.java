package com.ocppreperation.ocpguide.jpa;

import com.ocppreperation.ocpguide.Model.PageType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
import java.util.UUID;

/**
 * Created by adere on 18.01.2019.
 */
@Entity
public class Chapter {

    @Id
    private UUID id;

    private int level;

    private String name;

    private String url;

    private String chapterNum;

    private String fileName;

    private UUID nextChapter;

    private UUID previousChapter;

    private UUID parentChapter;

    private PageType pageType;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getChapterNum() {
        return chapterNum;
    }

    public void setChapterNum(String chapterNum) {
        this.chapterNum = chapterNum;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public UUID getNextChapter() {
        return nextChapter;
    }

    public void setNextChapter(UUID nextChapter) {
        this.nextChapter = nextChapter;
    }

    public UUID getPreviousChapter() {
        return previousChapter;
    }

    public void setPreviousChapter(UUID previousChapter) {
        this.previousChapter = previousChapter;
    }

    public UUID getParentChapter() {
        return parentChapter;
    }

    public void setParentChapter(UUID parentChapter) {
        this.parentChapter = parentChapter;
    }

    public UUID getId() {
        return id;
    }

    public Chapter(int level, String name, String url, String chapterNum, String fileName, PageType pageType) {
        this.id = UUID.randomUUID();
        this.level = level;
        this.name = name;
        this.url = url;
        this.chapterNum = chapterNum;
        this.fileName = fileName;
        this.pageType = pageType;
    }

    public Chapter (int level, String name, String chapterNum, PageType pageType) {
        this(level, name, name.trim(), chapterNum, chapterNum.replace('.','_') + "_" +  name.trim().replace(' ','_') + ".xml", pageType);
    }

    public Chapter (String chapterNum, String name, PageType pageType) {

        this(chapterNum.split("\\.").length, name, chapterNum, pageType);
    }

    public Chapter() {
    }

    public PageType getPageType() {
        return pageType;
    }

    public void setPageType(PageType pageType) {
        this.pageType = pageType;
    }
}
