/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectFMS.Bean;

import java.util.List;

/**
 *
 * @author Sushmitha
 */
public class TrainingBean {

    private String trainingId;
    private List<ContentBean> contentBean;
    private String trainingName;

    public List<ContentBean> getContentBean() {
        return contentBean;
    }

    public void setContentBean(List<ContentBean> contentBean) {
        this.contentBean = contentBean;
    }

    public String getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(String trainingId) {
        this.trainingId = trainingId;
    }

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

}
