/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectFMS.Bean;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author AswiniAnjappan
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
    private LeaveBean leaveBean;
    private List<TrainingScheduleBean> trainingScheduleBean;
    private List<DailyTaskUpdationBean> dailyTaskUpdationBean;

    public MinimumWorkingPeriodBean getMinimumWorkingPeriodBean() {
        return minimumWorkingPeriodBean;
    }

    public void setMinimumWorkingPeriodBean(MinimumWorkingPeriodBean minimumWorkingPeriodBean) {
        this.minimumWorkingPeriodBean = minimumWorkingPeriodBean;
    }

    public LeaveBean getLeaveBean() {
        return leaveBean;
    }

    public void setLeaveBean(LeaveBean leaveBean) {
        this.leaveBean = leaveBean;
    }

    public List<TrainingScheduleBean> getTrainingScheduleBean() {
        return trainingScheduleBean;
    }

    public void setTrainingScheduleBean(List<TrainingScheduleBean> trainingScheduleBean) {
        this.trainingScheduleBean = trainingScheduleBean;
    }

   
    public List<DailyTaskUpdationBean> getDailyTaskUpdationBean() {
        return dailyTaskUpdationBean;
    }

    public void setDailyTaskUpdationBean(List<DailyTaskUpdationBean> dailyTaskUpdationBean) {
        this.dailyTaskUpdationBean = dailyTaskUpdationBean;
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
