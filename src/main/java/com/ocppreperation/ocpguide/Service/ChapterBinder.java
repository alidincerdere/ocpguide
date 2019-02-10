package com.ocppreperation.ocpguide.Service;

import com.ocppreperation.ocpguide.jpa.Chapter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * Created by adere on 8.02.2019.
 */
@Component
public class ChapterBinder {

    public void bind(List<Chapter> chapterList) {

        setRecursiveParent(chapterList, 0, 0);
    }

    public void setRecursiveParent(List<Chapter> chapterList, int currentIndex, int parentIndex ) {

        Chapter currentChapter = chapterList.get(currentIndex);

        if (currentIndex==0) {

            currentChapter.setPreviousChapter(null);
            currentChapter.setParentChapter(null);
            currentChapter.setNextChapter(chapterList.get(currentIndex+1).getId());
            chapterList.get(currentIndex+1).setPreviousChapter(currentChapter.getId());

            currentIndex ++;
            setRecursiveParent(chapterList, currentIndex, 0);

        } else {

            Chapter parentChapter = chapterList.get(parentIndex);

            if(parentChapter.getLevel() < currentChapter.getLevel()) {

                currentChapter.setParentChapter(parentChapter.getId());


            } else if (parentChapter.getLevel() == currentChapter.getLevel()){

                currentChapter.setParentChapter(parentChapter.getParentChapter());

            } else {

                parentIndex = currentIndex;

            }

            currentIndex++;

            if(currentIndex == chapterList.size()) {
                return;
            }

            Chapter prevChapter = currentChapter;

            currentChapter = chapterList.get(currentIndex);

            prevChapter.setNextChapter(currentChapter.getId());
            currentChapter.setPreviousChapter(prevChapter.getId());

            if (prevChapter.getLevel() < currentChapter.getLevel()) {

                parentIndex = currentIndex -1;

            }

            setRecursiveParent(chapterList, currentIndex, parentIndex);

        }

    }
}
