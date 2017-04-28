/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectFMS.Bean;

import java.util.Date;
import java.util.List;

/**
 *
 * @author PROJECT FMS
 */
public class QuestionBean {
    
    private String trainingId;
    private String questionId;
    private String question;
    private Date questionDate;
    private List<AnswerBean> answerBeans;

    public List<AnswerBean> getAnswerBeans() {
        return answerBeans;
    }

    public Date getQuestionDate() {
        return questionDate;
    }

    public void setQuestionDate(Date questionDate) {
        this.questionDate = questionDate;
    }

    public void setAnswerBeans(List<AnswerBean> answerBeans) {
        this.answerBeans = answerBeans;
    }

    public String getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(String trainingId) {
        this.trainingId = trainingId;
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
