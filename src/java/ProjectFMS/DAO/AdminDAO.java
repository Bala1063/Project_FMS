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
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Sushmitha
 */
public class AdminDAO {

    public String removeContentsByContentId(List<String> contentIds) {

        Session session = Util.getSessionFactory().openSession();
        for (int i = 0; i < contentIds.size(); i++) {
            Query query = session.createQuery("delete from ContentBean where contentId=:contentId");
            query.setParameter("contentId", contentIds.get(i));
        }
        session.close();
        return "success";
    }

    public List<ReportBean> viewReportByTrainerId(String trainerId) {
        Session session = Util.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(ReportBean.class);
        criteria.add(Restrictions.eq("trainerId", trainerId));
        List<ReportBean> reportList = criteria.list();
        session.close();
        return reportList;
    }

    public List<ReportBean> viewAllReports() {
        Session session = Util.getSessionFactory().openSession();
        Query query = session.createQuery("From ReportBean");
        List<ReportBean> reportBeans = query.list();
        session.close();
        return reportBeans;

    }

    public String removeTrainingDetails(List<String> trainingIdList) {

        Session session = Util.getSessionFactory().openSession();
        for (int i = 0; i < trainingIdList.size(); i++) {
            Query query = session.createQuery("delete from TrainingBean where trainingId=:trainingId");
            query.setParameter("trainingId", trainingIdList.get(i));
        }
        session.close();
        return "success";
    }

    public String removeTrainerDetails(List<String> trainerIdList) {

        Session session = Util.getSessionFactory().openSession();
        for (int i = 0; i < trainerIdList.size(); i++) {
            Query query = session.createQuery("delete from TrainerBean where trainerId = :trainerId");
            query.setParameter("trainerId", trainerIdList.get(i));
        }
        session.close();
        return "success";
    }

    public TrainingScheduleBean viewTrainingScheduleByTrainerId(String trainerId) {
        Session session = Util.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(TrainingScheduleBean.class);
        criteria.add(Restrictions.eq("trainerId", trainerId));
        if (!criteria.list().isEmpty()) {
            List<TrainingScheduleBean> trainingScheduleList = criteria.list();
            Date date = new Date();
            for (TrainingScheduleBean trainingScheduleBean : trainingScheduleList) {
                if ((trainingScheduleBean.getFromDate().getMonth() >= date.getMonth()) && (trainingScheduleBean.getToDate().getMonth() <= date.getMonth())) {
                    return trainingScheduleBean;
                }
            }
            session.close();
        }
        return null;

    }

    public List<TrainingScheduleBean> viewTrainingSchedule() {
        Session session = Util.getSessionFactory().openSession();
        Query query = session.createQuery("From TrainingScheduleBean");
        if (!query.list().isEmpty()) {
            List<TrainingScheduleBean> trainingScheduleList = query.list();
            Date date = new Date();
            for (TrainingScheduleBean trainingScheduleBean : trainingScheduleList) {
                if (!(trainingScheduleBean.getFromDate().getMonth() >= date.getMonth()) && (trainingScheduleBean.getToDate().getMonth() <= date.getMonth())) {
                    trainingScheduleList.remove(trainingScheduleBean);
                }
            }
            session.close();
            return trainingScheduleList;

        }
        return null;

    }

    public List<TrainingScheduleBean> viewAllScheduleTraining() {
        Session session = Util.getSessionFactory().openSession();
        Query query = session.createSQLQuery("From TrainingScheduleBean");
        List<TrainingScheduleBean> allTrainingScheduleList = query.list();
        session.close();
        return allTrainingScheduleList;
    }

    public List<TrainingScheduleBean> notifications() {
        Session session = Util.getSessionFactory().openSession();
        Query query = session.createQuery("From TrainingScheduleBean");
        if (!query.list().isEmpty()) {
            List<TrainingScheduleBean> trainingScheduleList = query.list();
            Date date = new Date();
            for (TrainingScheduleBean trainingScheduleBean : trainingScheduleList) {
                long days = trainingScheduleBean.getToDate().getTime() - trainingScheduleBean.getFromDate().getTime();
                long minimumWorkingPeroid = new CommonDAO().getMinimumWorkingPeroid(trainingScheduleBean.getTrainerId());
                if (!(trainingScheduleBean.getFromDate().getMonth() >= date.getMonth()) && (trainingScheduleBean.getToDate().getMonth() <= date.getMonth()) && days > minimumWorkingPeroid) {
                    trainingScheduleList.remove(trainingScheduleBean);
                }
            }
            session.close();
            return trainingScheduleList;

        }
        return null;
    }
}
