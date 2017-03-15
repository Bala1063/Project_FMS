/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectFMS.Bean;

import java.util.List;

/**
 *
 * @author bala
 */
public class ContentBean {
    private String trainingId;
    private String contentId;
    private String content;
    private List<QuestionBean> questionBean;

    public List<QuestionBean> getQuestionBean() {
        return questionBean;
    }

    public void setQuestionBean(List<QuestionBean> questionBean) {
        this.questionBean = questionBean;
    }

    public String getTrainingId() {
        return trainingId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
}
