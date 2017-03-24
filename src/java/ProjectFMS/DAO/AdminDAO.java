/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectFMS.DAO;

import ProjectFMS.Util.Util;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import ProjectFMS.Bean.ReportBean;
import ProjectFMS.Bean.TrainerBean;
import ProjectFMS.Bean.TrainingBean;

import ProjectFMS.Bean.ReportBean;
import ProjectFMS.Bean.TrainingScheduleBean;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import ProjectFMS.Util.Util;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Sushmitha
 */
public class AdminDAO {
    public String removeContentsByContentId(List<String> contentIds){
 
        Session session=Util.getSessionFactory().openSession();
        for(int i=0;i<contentIds.size();i++){
            Query query = session.createQuery("delete from ContentBean where contentId=:contentId");
            query.setParameter("contentId", contentIds.get(i));
        }
        session.close();
        return "success";
    }
    

    public String allocateTrainingList(List<String> trainerIdList,String trainingId,Date fromDate,Date toDate){
        CommonDAO commonDAO=new CommonDAO();
        for(int i=0;i<trainerIdList.size();i++){
           TrainingScheduleBean trainingScheduleBean=new TrainingScheduleBean();
           trainingScheduleBean.setTrainerId(trainerIdList.get(i));
           trainingScheduleBean.setTrainingId(trainingId);
           trainingScheduleBean.setFromDate(fromDate);
           trainingScheduleBean.setToDate(toDate);
           commonDAO.addOrUpdateDetails(trainingScheduleBean);
        }
        return "success";
    }
    public String allocateTraining(String trainerId,String trainingId,Date fromDate,Date toDate){
        CommonDAO commonDAO=new CommonDAO();
        TrainingScheduleBean trainingScheduleBean=new TrainingScheduleBean();
        trainingScheduleBean.setTrainerId(trainerId);
        trainingScheduleBean.setTrainingId(trainingId);
        trainingScheduleBean.setFromDate(fromDate);
        trainingScheduleBean.setToDate(toDate);
        commonDAO.addOrUpdateDetails(trainingScheduleBean);
        return "success";
    }
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
        session.close();
        return reportBeans;

    }
    public String removeTrainingDetails(List<String> trainingIdList){ 
        
        Session session=Util.getSessionFactory().openSession();
        for(int i=0;i<trainingIdList.size();i++){
            Query query=session.createQuery("delete from TrainingBean where trainingId=:trainingId");
            query.setParameter("trainingId", trainingIdList.get(i));
        }
        session.close();
        return "success";
    }
    
     public String removeTrainerDetails(List<String> trainerIdList){ 
        
        Session session=Util.getSessionFactory().openSession();
        for(int i=0;i<trainerIdList.size();i++){
            Query query=session.createQuery("delete from TrainerBean where trainerId = :trainerId");
            query.setParameter("trainerId", trainerIdList.get(i));
        }
        session.close();
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

    
    public String viewTrainerByTrainerId(String trainerId){
        Session session = Util.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(TrainerBean.class);
        criteria.add(Restrictions.eq("Trainer_Id", trainerId));
        List<TrainingBean> trainingBeanList=criteria.list();
        session.close();
        return trainingBeanList.get(0).getTrainingName();
    }
    
   

}
