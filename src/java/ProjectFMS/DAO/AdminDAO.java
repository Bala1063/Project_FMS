/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectFMS.DAO;

import ProjectFMS.Bean.ReportBean;
import ProjectFMS.Bean.TrainingScheduleBean;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import ProjectFMS.Util.Util;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Sushmitha
 */
public class AdminDAO {

    public List<ReportBean> viewReportByTrainerId(String trainerId) {
        Session session = Util.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(ReportBean.class);
        criteria.add(Restrictions.eq("Trainer_Id", trainerId));
        List<ReportBean> reportList = criteria.list();
        session.close();
        return reportList;
    }

    public List<ReportBean> viewAllReports() {
        Session session = Util.getSessionFactory().openSession();
        Query query = session.createSQLQuery("From ReportBean");
        List<ReportBean> reportBeans = query.list();
        return reportBeans;

    }
    public String removeTrainingDetails(List<String> trainingIdList){ 
        
        Session session=Util.getSessionFactory().openSession();
        for(int i=0;i<trainingIdList.size();i++){
            Query query=session.createQuery("delete from TrainingBean where Training_id = '"+trainingIdList.get(i)+"'");
        }
        return "success";
    }
    
     public String deleteTrainerDetails(List<String> trainerIdList){ 
        
        Session session=Util.getSessionFactory().openSession();
        for(int i=0;i<trainerIdList.size();i++){
            Query query=session.createQuery("delete from Trainer_tb where Training_id = '"+trainerIdList.get(i)+"'");
        }
        return "success";
    }
    public List<TrainingScheduleBean> viewTrainingScheduleByTrainerId(String trainerId) {
        Session session = Util.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(TrainingScheduleBean.class);
        criteria.add(Restrictions.eq("Trainer_Id", trainerId));
        List<TrainingScheduleBean> trainingScheduleList = criteria.list();
        session.close();
        return trainingScheduleList;
    }

    public List<TrainingScheduleBean> viewAllScheduleTraining() {
        Session session = Util.getSessionFactory().openSession();
        Query query = session.createSQLQuery("From TrainingScheduleBean");
        List<TrainingScheduleBean> allTrainingScheduleList = query.list();
        session.close();
        return allTrainingScheduleList;
    }

}
