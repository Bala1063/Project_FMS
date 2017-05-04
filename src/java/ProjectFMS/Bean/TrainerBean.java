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
public class TrainerBean {

    private String trainerId;
    private String trainerName;
    private String areaOfSpecialization;
    private String institution;
    private String qualifications;
    private String mailId;
    private long phoneNo;
    private Date dateOfBirth;
    private MinimumWorkingPeriodBean minimumWorkingPeriodBean;
    private List<LeaveBean> leaveBeans;
    private List<TrainingScheduleBean> trainingScheduleBeans;

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

    @Override
    public String toString() {
        return trainerId + "," + trainerName + "," + areaOfSpecialization + "," + institution + "," + qualifications + "," + mailId + "," + phoneNo + "," + dateOfBirth + "," + getMinimumWorkingPeriodBean().getMinimumWorkingPeriod();
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

    public long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(long phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

}
