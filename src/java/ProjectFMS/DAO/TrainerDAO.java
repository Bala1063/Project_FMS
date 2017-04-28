/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectFMS.DAO;

import ProjectFMS.Bean.ContentBean;
import ProjectFMS.Bean.QuestionBean;
import ProjectFMS.Bean.TaskBean;
import ProjectFMS.Bean.TrainerBean;
import ProjectFMS.Bean.TrainingScheduleBean;
import ProjectFMS.Util.Util;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author bala
 */
public class TrainerDAO {

    public TrainerBean viewTrainerDetails(String userId) {
        Session session = Util.getSessionFactory().openSession();
        TrainerBean trainerBean = (TrainerBean) session.get(TrainerBean.class, userId);
        session.close();
        return trainerBean;
    }

    public String getTrainerId(String trainerName, String institution) {
        Session session = Util.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(TrainerBean.class);
        criteria.add(Restrictions.eq("trainerName", trainerName));
        criteria.add(Restrictions.eq("institution", institution));
        if (!criteria.list().isEmpty()) {
            TrainerBean trainerBean = (TrainerBean) criteria.list().get(0);
            session.close();
            return trainerBean.getTrainerId();
        }
        session.close();
        return null;
    }

    public List<TrainerBean> viewAllTrainer() {
        Session session = Util.getSessionFactory().openSession();
        Query query = session.createQuery("From TrainerBean");
        List<TrainerBean> trainerBeans = query.list();
        session.close();
        return trainerBeans;
    }

    public List<TrainerBean> viewAllTrainerByInstitution(String institution) {
        Session session = Util.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(TrainerBean.class);
        criteria.add(Restrictions.eq("institution", institution));
        List<TrainerBean> trainerBeans = criteria.list();
        session.close();
        return trainerBeans;

    }

    public String getTrainingId(String trainerId) {
        Session session = Util.getSessionFactory().openSession();
        Date date = new Date();
        Criteria criteria = session.createCriteria(TrainingScheduleBean.class);
        criteria.add(Restrictions.ge("toDate", date));
        if (!criteria.list().isEmpty()) {
            System.out.println(criteria.list().size());
            List<TrainingScheduleBean> trainingScheduleBeans = criteria.list();
          
            for (TrainingScheduleBean trainingScheduleBean : trainingScheduleBeans) {
                if (trainerId.equalsIgnoreCase(trainingScheduleBean.getTrainerId())) {
                    return trainingScheduleBean.getTrainingId();
                }
            }
        }
        session.close();
        return null;
    }

    public TrainingScheduleBean getTrainingSchedule(String TrainingId) {
        Session session = Util.getSessionFactory().openSession();
        TrainingScheduleBean trainingScheduleBean = (TrainingScheduleBean) session.get(TrainingScheduleBean.class, TrainingId);
        session.close();
        return trainingScheduleBean;
    }

    public boolean checkTask(String trainingId, Date date) {
        Session session = Util.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(TaskBean.class);
        criteria.add(Restrictions.eq("trainingId", trainingId));
        if (!criteria.list().isEmpty()) {
            List<TaskBean> taskBeans = criteria.list();
            for (TaskBean taskBean : taskBeans) {

                if (taskBean.getTaskDate().getDate() == date.getDate() && taskBean.getTaskDate().getMonth() == date.getMonth() && taskBean.getTaskDate().getYear() == date.getYear()) {
                    return true;
                }
            }
        }
        session.close();
        return false;

    }

    public boolean checkContent(String trainingId, Date date) {
        Session session = Util.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(ContentBean.class);
        criteria.add(Restrictions.eq("trainingId", trainingId));
        if (!criteria.list().isEmpty()) {
            List<ContentBean> contentBeans = criteria.list();
            for (ContentBean contentBean : contentBeans) {

                if (contentBean.getContentDate().getDate() == date.getDate() && contentBean.getContentDate().getMonth() == date.getMonth() && contentBean.getContentDate().getYear() == date.getYear()) {
                    return true;
                }
            }
        }
        session.close();
        return false;

    }

    public int contentCount(String trainingId, Date date) {
        Session session = Util.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(ContentBean.class);
        criteria.add(Restrictions.eq("trainingId", trainingId));
        int count = 0;
        if (!criteria.list().isEmpty()) {
            List<ContentBean> contentBeans = criteria.list();
            for (ContentBean contentBean : contentBeans) {

                if (contentBean.getContentDate().getDate() == date.getDate() && contentBean.getContentDate().getMonth() == date.getMonth() && contentBean.getContentDate().getYear() == date.getYear()) {
                    count++;
                }
            }
        }
        session.close();
        return count;

    }

    public int questionCount(String trainingId, Date date) {
        Session session = Util.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(QuestionBean.class);
        criteria.add(Restrictions.eq("trainingId", trainingId));
        int count = 0;
        if (!criteria.list().isEmpty()) {
            List<QuestionBean> questionBeans = criteria.list();
            for (QuestionBean questionBean : questionBeans) {

                if (questionBean.getQuestionDate().getDate() == date.getDate() && questionBean.getQuestionDate().getMonth() == date.getMonth() && questionBean.getQuestionDate().getYear() == date.getYear()) {
                    count++;
                }
            }
        }
        session.close();
        return count;

    }
    public String checkTaskStatus(String trainingId,Date date)
    {
        
        Session session = Util.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(TaskBean.class);
        criteria.add(Restrictions.eq("trainingId", trainingId));
        String status="";
        if (!criteria.list().isEmpty()) {
            List<TaskBean> taskBeans = criteria.list();
            for (TaskBean taskBean : taskBeans) {

                if (taskBean.getTaskDate().getDate() == date.getDate() && taskBean.getTaskDate().getMonth() == date.getMonth() && taskBean.getTaskDate().getYear() == date.getYear()) {
                  status= taskBean.getTaskStatus();
                }
            }
        }
        return status;
    }
}
