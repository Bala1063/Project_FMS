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
 * PRP_FMS:
 * @author Aruna M,Aswini A,Balaji S K,Sushmitha S.
 */
public class TrainingScheduleBean {

    private String trainingId;
    private String trainerId;
    private Date fromDate;
    private Date toDate;
    private String trainingStream;
    private List<ContentBean> contentBeans;
    private List<QuestionBean> questionBeans;
    private List<TaskBean> taskBeans;

    public List<TaskBean> getTaskBeans() {
        return taskBeans;
    }

    @Override
    public String toString() {
        return trainingId + "," + trainerId + "," + fromDate + "," + toDate + "," + trainingStream;
    }

    public void setTaskBeans(List<TaskBean> taskBeans) {
        this.taskBeans = taskBeans;
    }

    public List<ContentBean> getContentBeans() {
        return contentBeans;
    }

    public void setContentBeans(List<ContentBean> contentBeans) {
        this.contentBeans = contentBeans;
    }

    public String getTrainingStream() {
        return trainingStream;
    }

    public void setTrainingStream(String trainingStream) {
        this.trainingStream = trainingStream;
    }

    public List<QuestionBean> getQuestionBeans() {
        return questionBeans;
    }

    public void setQuestionBeans(List<QuestionBean> questionBeans) {
        this.questionBeans = questionBeans;
    }

    public String getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(String trainerId) {
        this.trainerId = trainerId;
    }

    public String getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(String trainingId) {
        this.trainingId = trainingId;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

}
