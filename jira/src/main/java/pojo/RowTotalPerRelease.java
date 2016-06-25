/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

/**
 *
 * @author Administrator
 */
public class RowTotalPerRelease {
    
    private Long idProjectRelease;
    
    private String releaseYear;
    private String releaseName;
    private String projectName;
    
    private String totalPerRelease;
    

    public RowTotalPerRelease() {
    }

    public RowTotalPerRelease(Long idProjectRelease, String releaseYear, String releaseName, String projectName, String totalPerRelease) {
        this.idProjectRelease = idProjectRelease;
        this.releaseYear = releaseYear;
        this.releaseName = releaseName;
        this.projectName = projectName;
        this.totalPerRelease = totalPerRelease;
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

    public String getTotalPerRelease() {
        return totalPerRelease;
    }

    public void setTotalPerRelease(String totalPerRelease) {
        this.totalPerRelease = totalPerRelease;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Long getIdProjectRelease() {
        return idProjectRelease;
    }

    public void setIdProjectRelease(Long idProjectRelease) {
        this.idProjectRelease = idProjectRelease;
    }

    
    
    
    
}
