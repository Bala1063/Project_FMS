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
public class TrainerBean {
    private String trainerId;
    private String trainerName;
    private String areaOfSpecialization;
    private String institution;
    private String selfSkilling;
    private String qualifications;
    private String mailId;
    private int phoneNo;
    private Date dateOfBirth;
    private MinimumWorkingPeriodBean minimumWorkingPeriodBean;
    private List<LeaveBean> leaveBeans;
    private List<TrainingScheduleBean> trainingScheduleBeans;
    private List<TaskBean> taskBeans;

    public MinimumWorkingPeriodBean getMinimumWorkingPeriodBean() {
        return minimumWorkingPeriodBean;
    }

    public void setMinimumWorkingPeriodBean(MinimumWorkingPeriodBean minimumWorkingPeriodBean) {
        this.minimumWorkingPeriodBean = minimumWorkingPeriodBean;
    }

    public List<LeaveBean> getLeaveBeans() {
        return leaveBeans;
    }

    public void setLeaveBeans(List<LeaveBean> leaveBeans) {
        this.leaveBeans = leaveBeans;
    }

    
    public List<TrainingScheduleBean> getTrainingScheduleBeans() {
        return trainingScheduleBeans;
    }

    public void setTrainingScheduleBeans(List<TrainingScheduleBean> trainingScheduleBeans) {
        this.trainingScheduleBeans = trainingScheduleBeans;
    }

    public List<TaskBean> getTaskBeans() {
        return taskBeans;
    }

    public void setTaskBeans(List<TaskBean> taskBeans) {
        this.taskBeans = taskBeans;
    }

   
       
    

    public String getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(String trainerId) {
        this.trainerId = trainerId;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public String getAreaOfSpecialization() {
        return areaOfSpecialization;
    }

    public void setAreaOfSpecialization(String areaOfSpecialization) {
        this.areaOfSpecialization = areaOfSpecialization;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getSelfSkilling() {
        return selfSkilling;
    }

    public void setSelfSkilling(String selfSkilling) {
        this.selfSkilling = selfSkilling;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    public int getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
}
