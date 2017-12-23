
package uutissivusto.wad.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class PostObject {
    
    @NotEmpty
    @Size(min = 1, max = 100)
    private String headline;
    
    @NotEmpty
    @Size(min = 5, max = 300)
    private String lead;
    
    @NotEmpty
    @Size(min = 10, max = 10000)
    private String text;

    public String getHeadline() {
        return headline;
    }

    public String getLead() {
        return lead;
    }

    public String getText() {
        return text;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public void setLead(String lead) {
        this.lead = lead;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    
}
