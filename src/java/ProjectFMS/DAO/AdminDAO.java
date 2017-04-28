/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectFMS.DAO;

import ProjectFMS.Bean.DefaultMWP;
import ProjectFMS.Bean.LeaveBean;
import ProjectFMS.Bean.MinimumWorkingPeriodBean;

import ProjectFMS.Bean.TrainerBean;
import ProjectFMS.Bean.TrainingScheduleBean;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import ProjectFMS.Util.Util;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author PROJECT FMS
 */
public class AdminDAO {

    public String removeTrainingDetails(String trainingId) {
        Session session = Util.getSessionFactory().openSession();
        Query query = session.createQuery("delete from TrainingBean where trainingId = :trainingId");
        query.setParameter("trainingId", trainingId);
        session.close();
        return "success";
    }

    public String removeTrainerDetails(String trainerId) {

        Session session = Util.getSessionFactory().openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            TrainerBean trainerBean = (TrainerBean) session.get(TrainerBean.class, trainerId);
            session.delete(trainerBean);
            t.commit();
        } catch (HibernateException e) {
            if (t != null) {
                t.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return "success";
    }

    public TrainingScheduleBean viewTrainingScheduleByTrainerId(String trainerId) {
        Session session = Util.getSessionFactory().openSession();
        Date date = new Date();
        Criteria criteria = session.createCriteria(TrainingScheduleBean.class);
        criteria.add(Restrictions.ge("toDate", date));
        if (!criteria.list().isEmpty()) {
            System.out.println(criteria.list().size());
            List<TrainingScheduleBean> trainingScheduleBeans = criteria.list();
            session.close();
            for (TrainingScheduleBean trainingScheduleBean : trainingScheduleBeans) {
                if (trainerId.equalsIgnoreCase(trainingScheduleBean.getTrainerId())) {
                    return trainingScheduleBean;
                }
            }
        }
        session.close();
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

    public String allocateMinimumWorkingPeriodList(List<String> trainerIdList, int minimumWorkingPeriod) {

        for (int i = 0; i < trainerIdList.size(); i++) {
            MinimumWorkingPeriodBean minimumWorkingPeriodBean = new MinimumWorkingPeriodBean();
            minimumWorkingPeriodBean.setTrainerId(trainerIdList.get(i));
            minimumWorkingPeriodBean.setMinimumWorkingPeriod(minimumWorkingPeriod);
            new CommonDAO().addOrUpdateDetails(minimumWorkingPeriodBean);
        }
        return "success";
    }

    public DefaultMWP getDefaultMWP() {
        Session session = Util.getSessionFactory().openSession();
        DefaultMWP defaultMWP = (DefaultMWP) session.get(DefaultMWP.class, 1);
        session.close();
        return defaultMWP;
    }

    public List<TrainerBean> getNonScheduleTrainers() {
        Session session = Util.getSessionFactory().openSession();
        Query query = session.createQuery("From TrainerBean");
        if (!query.list().isEmpty()) {
            List<TrainerBean> trainerBeans = query.list();
            session.close();
            Iterator<TrainerBean> i = trainerBeans.iterator();
            while (i.hasNext()) {
                TrainerBean trainerBean = (TrainerBean) i.next();
                if (new TrainerDAO().getTrainingId(trainerBean.getTrainerId()) != null) {
                    i.remove();
                }
            }
            return trainerBeans;
        }
        session.close();
        return null;
    }

    public List<LeaveBean> getLeaveDetails() {
        Session session = Util.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(LeaveBean.class);
        Date date = new Date();
        criteria.add(Restrictions.ge("toDate", date));
        if (!criteria.list().isEmpty()) {
            List<LeaveBean> leaveBeans = criteria.list();
            session.close();

            return leaveBeans;
        }
        return null;
    }

    public List<TrainingScheduleBean> getIncorrectScheduleDetails() {
        Session session = Util.getSessionFactory().openSession();
        Query query = session.createQuery("From TrainerBean");
        if (!query.list().isEmpty()) {
            List<TrainerBean> trainerBeans = query.list();
            List<TrainingScheduleBean> trainingScheduleBeans = new ArrayList<>();
            session.close();
            TrainerDAO trainerDAO = new TrainerDAO();
            for (TrainerBean trainerBean : trainerBeans) {
                String trainingId = trainerDAO.getTrainingId(trainerBean.getTrainerId());
                if (trainingId != null) {
                    TrainingScheduleBean trainingScheduleBean = trainerDAO.getTrainingSchedule(trainingId);
                    if (trainingScheduleBean != null) {
                        long x = (trainingScheduleBean.getToDate().getTime() - trainingScheduleBean.getFromDate().getTime()) / (1000 * 60 * 60 * 24);
                        if (x < trainerBean.getMinimumWorkingPeriodBean().getMinimumWorkingPeriod()) {
                            trainingScheduleBeans.add(trainingScheduleBean);
                        }
                    }
                }
            }
            return trainingScheduleBeans;
        }
        session.close();
        return null;
    }
}
