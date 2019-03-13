package com.ocppreperation.ocpguide.Model;

/**
 * Created by adere on 12.03.2019.
 */
public class PageComponent {

    private ComponentType componentType;

    private String content;

    public ComponentType getComponentType() {
        return componentType;
    }

    public void setComponentType(ComponentType componentType) {
        this.componentType = componentType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
