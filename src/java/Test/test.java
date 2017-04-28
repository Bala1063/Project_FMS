/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import ProjectFMS.Bean.TrainingScheduleBean;
import ProjectFMS.DAO.AdminDAO;
import ProjectFMS.DAO.LoginDAO;
/**
 *
 * @author bala
 */
public class test {
    public static void main(String[] args) {
        AdminDAO adminDAO=new AdminDAO();
//        List<TrainerBean> trainerBeans=new AdminDAO().getNonScheduleTrainers();
//        System.out.println(trainerBeans);
//        logout Admin        
        new LoginDAO().logout("Admin");
//          TrainingScheduleBean trainingScheduleBean=adminDAO.viewTrainingScheduleByTrainerId("TRN_bal041");
//          System.out.println(trainingScheduleBean.toString());



    }
}
