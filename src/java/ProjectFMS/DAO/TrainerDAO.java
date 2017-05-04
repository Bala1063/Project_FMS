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
 * PRP_FMS:
 *
 * @author Sushmitha S.
 */
public class TrainerDAO {
//Retrieves Trainer Detail using User Id.
    public TrainerBean viewTrainerDetails(String userId) {
        Session session = Util.getSessionFactory().openSession();
        TrainerBean trainerBean = (TrainerBean) session.get(TrainerBean.class, userId);
        session.close();
        return trainerBean;
    }
//Retrieves Trainer Id using trainer name and institution.
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
//Retrieves list of Trainer Details.
    public List<TrainerBean> viewAllTrainer() {
        Session session = Util.getSessionFactory().openSession();
        Query query = session.createQuery("From TrainerBean");
        List<TrainerBean> trainerBeans = query.list();
        session.close();
        return trainerBeans;
    }
//Retrieves all Trainer details by institution.
    public List<TrainerBean> viewAllTrainerByInstitution(String institution) {
        Session session = Util.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(TrainerBean.class);
        criteria.add(Restrictions.eq("institution", institution));
        List<TrainerBean> trainerBeans = criteria.list();
        session.close();
        return trainerBeans;

    }
// Returns Training Id using Trainer Id.
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
//Retrieves Training Schedule by training Id.
    public TrainingScheduleBean getTrainingSchedule(String TrainingId) {
        Session session = Util.getSessionFactory().openSession();
        TrainingScheduleBean trainingScheduleBean = (TrainingScheduleBean) session.get(TrainingScheduleBean.class, TrainingId);
        session.close();
        return trainingScheduleBean;
    }
//Check Whether the Task is alloted or not.
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
//Check whether the Content is Added or not.
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
//Returns the count of the content Added by Trainer Id using Training Id and date.
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
//Returns the count of the question Added by Trainer Id using Training Id and date.
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
//Return Task Status using TrainingId and Date.
    public String checkTaskStatus(String trainingId, Date date) {

        Session session = Util.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(TaskBean.class);
        criteria.add(Restrictions.eq("trainingId", trainingId));
        String status = "";
        if (!criteria.list().isEmpty()) {
            List<TaskBean> taskBeans = criteria.list();
            for (TaskBean taskBean : taskBeans) {

                if (taskBean.getTaskDate().getDate() == date.getDate() && taskBean.getTaskDate().getMonth() == date.getMonth() && taskBean.getTaskDate().getYear() == date.getYear()) {
                    status = taskBean.getTaskStatus();
                }
            }
        }
        return status;
    }
//Validate the mail id whether it is given during registration or not.
    public String checkMail(String mailid) {
        Session session = Util.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(TrainerBean.class);
        criteria.add(Restrictions.eq("mailId", mailid));
        if (!criteria.list().isEmpty()) {
            session.close();
            return "true";
        }
        session.close();
        return "false";
    }
//Returns Trainer Id using Mail Id 
    public String getTrainerIdByMailID(String mailid) {
        Session session = Util.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(TrainerBean.class);
        criteria.add(Restrictions.eq("mailId", mailid));
        if (!criteria.list().isEmpty()) {
            TrainerBean trainerBean = (TrainerBean) criteria.list().get(0);
            session.close();
            return trainerBean.getTrainerId();
        }
        session.close();
        return null;
    }
}
