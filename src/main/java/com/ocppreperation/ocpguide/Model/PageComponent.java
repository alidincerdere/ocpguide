package com.ocppreperation.ocpguide.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adere on 12.03.2019.
 */
public class PageComponent {

    private ComponentType componentType;

    private List<String> content;

    public ComponentType getComponentType() {
        return componentType;
    }

    public void setComponentType(ComponentType componentType) {
        this.componentType = componentType;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }

    public PageComponent() {

        content = new ArrayList<>();
    }
}
