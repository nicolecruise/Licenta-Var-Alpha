/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

/**
 *
 * @author iradoi
 */
public class RowRaport {
    
    private String anRelease;
    private String releaseName;
    private String projectName;
    private String category;
    private String item;
    private String storyPoint;
    private String sprintName;

    public RowRaport() {
    }

    public RowRaport(String anRelease, String releaseName, String projectName, String category, String item, String storyPoint, String idSprint) {
        this.anRelease = anRelease;
        this.releaseName = releaseName;
        this.projectName = projectName;
        this.category = category;
        this.item = item;
        this.storyPoint = storyPoint;
        this.sprintName = idSprint;
    }

    public String getAnRelease() {
        return anRelease;
    }

    public void setAnRelease(String anRelease) {
        this.anRelease = anRelease;
    }

    public String getReleaseName() {
        return releaseName;
    }

    public void setReleaseName(String releaseName) {
        this.releaseName = releaseName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getStoryPoint() {
        return storyPoint;
    }

    public void setStoryPoint(String storyPoint) {
        this.storyPoint = storyPoint;
    }

    public String getSprintName() {
        return sprintName;
    }

    public void setSprintName(String sprintName) {
        this.sprintName = sprintName;
    }

    

    
    
    
    
    
    
}
