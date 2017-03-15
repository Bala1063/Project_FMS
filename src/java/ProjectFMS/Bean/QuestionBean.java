/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectFMS.Bean;

import java.util.List;

/**
 *
 * @author AswiniAnjappan
 */
public class QuestionBean {
    private String trainingId;
    private String contentId;
    private String questionId;
    private String question;
    private List<AnswerBean> answerBean;

    public List<AnswerBean> getAnswerBean() {
        return answerBean;
    }

    public void setAnswerBean(List<AnswerBean> answerBean) {
        this.answerBean = answerBean;
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

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
    
}
