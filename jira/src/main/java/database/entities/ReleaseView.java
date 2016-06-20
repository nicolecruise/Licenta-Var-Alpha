package database.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.inject.Named;

@Named
@SessionScoped
public class ReleaseView implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<SelectItem> releases;    
	    private String selection;
	 
	    @PostConstruct
	    public void init() {
	    	releases = new ArrayList<SelectItem>();
	        SelectItemGroup group1 = new SelectItemGroup("2016-R1");
	        SelectItemGroup group2 = new SelectItemGroup("2016-R2");
	        SelectItemGroup group3 = new SelectItemGroup("2016-R3");
	        SelectItemGroup group4 = new SelectItemGroup("2016-R4");

	        
	        SelectItem option11 = new SelectItem("Sprint 1", "Sprint 1");
	        SelectItem option12 = new SelectItem("Sprint 2", "Sprint 2");
	        SelectItem option13 = new SelectItem("Sprunt 3", "Sprunt 3");
	        SelectItem option14 = new SelectItem("Sprint 4", "Sprint 4");
	        SelectItem option15 = new SelectItem("Sprint 5", "Sprint 5");
	        SelectItem option16 = new SelectItem("Sprint 5 VM", "Sprint 5 VM");
	        
	        group1.setSelectItems(new SelectItem[]{option11, option12, option13, option14,
	        		option15, option16});
   
	        releases.add(group1);
	        releases.add(group2);
	        releases.add(group3);
	        releases.add(group4);
	       
	    }

		public List<SelectItem> getReleases() {
			return releases;
		}

		public String getSelection() {
			return selection;
		}

		public void setSelection(String selection) {
			this.selection = selection;
		}
	     
	    
	}

	
