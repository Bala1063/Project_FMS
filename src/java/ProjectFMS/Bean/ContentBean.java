/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectFMS.Bean;

import java.util.Date;



/**
 *
 * PRP_FMS:
 *  @author Aruna M.
 */
public class ContentBean {

   
    private String contentId;
    private String contentName;
    private String trainingId;
    private Date contentDate;
    private String contentDescription;

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public String getContentDescription() {
        return contentDescription;
    }

    public void setContentDescription(String contentDescription) {
        this.contentDescription = contentDescription;
    }

    public String getTrainingId() {
        return trainingId;
    }

    public Date getContentDate() {
        return contentDate;
    }

    public void setContentDate(Date contentDate) {
        this.contentDate = contentDate;
    }

    public void setTrainingId(String trainingId) {
        this.trainingId = trainingId;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

}
