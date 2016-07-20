package com.nexient.orgchart.model;

/**
 * Created by mrangel on 7/20/2016.
 */
public class BaseModel {

    private Integer id;

    private boolean isActive;

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
