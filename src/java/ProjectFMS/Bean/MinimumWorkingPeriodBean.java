/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectFMS.Bean;

/**
 *
 * @author bala
 */
public class MinimumWorkingPeriodBean {
    private String trainerId;
    private String minimumWorkingPeriod;
    private TrainerBean trainerBean;

    public TrainerBean getTrainerBean() {
        return trainerBean;
    }

    public void setTrainerBean(TrainerBean trainerBean) {
        this.trainerBean = trainerBean;
    }

    public String getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(String trainerId) {
        this.trainerId = trainerId;
    }

    public String getMinimumWorkingPeriod() {
        return minimumWorkingPeriod;
    }

    public void setMinimumWorkingPeriod(String minimumWorkingPeriod) {
        this.minimumWorkingPeriod = minimumWorkingPeriod;
    }
    
}
