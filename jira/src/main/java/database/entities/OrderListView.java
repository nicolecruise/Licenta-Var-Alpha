package database.entities;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
 
@ManagedBean
public class OrderListView {
     
    @ManagedProperty("#{themeService}")
    //private ThemeService service;
     
    private List<String> cities;
    //private List<Theme> themes;
     
    @PostConstruct
    public void init() {
        //Cities
    	cities = new ArrayList<String>();
    	cities.add("San Francisco");
    	cities.add("London");
    	cities.add("Paris");
    	cities.add("Istanbul");
    	cities.add("Berlin");
    	cities.add("Barcelona");
    	cities.add("Rome");
              
        //Themes
        //themes = service.getThemes().subList(0, 6);    
    }
 
//    public ThemeService getService() {
//        return service;
//    }
 
//    public void setService(ThemeService service) {
//        this.service = service;
//    }
 
 
//    public List<Theme> getThemes() {
//        return themes;
//    }
// 
//    public void setThemes(List<Theme> themes) {
//        this.themes = themes;
    //}   
 
    public void onSelect(SelectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", event.getObject().toString()));
    }
     
    public List<String> getCities() {
		return cities;
	}

	public void setCities(List<String> cities) {
		this.cities = cities;
	}

	public void onUnselect(UnselectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Unselected", event.getObject().toString()));
    }
     
    public void onReorder() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "List Reordered", null));
    } 
}