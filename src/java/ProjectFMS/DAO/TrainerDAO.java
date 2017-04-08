/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectFMS.DAO;

import ProjectFMS.Bean.LeaveBean;
import ProjectFMS.Bean.TaskBean;
import ProjectFMS.Bean.TrainerBean;
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

    public String updateTask(String task, String trainerId) {
        Session session = Util.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(TaskBean.class);
        criteria.add(Restrictions.eq("trainerId", trainerId));
        criteria.add(Restrictions.eq("task", task));
        if (criteria.list().isEmpty()) {
            TaskBean taskBean = (TaskBean) criteria.list().get(0);
            taskBean.setTaskStatus("yes");
            return "success";
        }
        return "fail";
    }

    public String updateLeave(String trainerId, Date leaveDate) {

        LeaveBean leaveBean = new LeaveBean();
        leaveBean.setLeaveDate(leaveDate);
        leaveBean.setTrainerId(trainerId);
        new CommonDAO().addOrUpdateDetails(leaveBean);
        return "success";
    }

     public TrainerBean viewTrainerDetails(String userId) {
        Session session = Util.getSessionFactory().openSession();
        TrainerBean trainerBean = (TrainerBean) session.get(TrainerBean.class, userId);
        session.close();
        return trainerBean;
    }
     public String getTrainerId(String trainerName,String institution)
     {
          Session session = Util.getSessionFactory().openSession();
         Criteria criteria=session.createCriteria(TrainerBean.class);
         criteria.add(Restrictions.eq("trainerName", trainerName));
         criteria.add(Restrictions.eq("institution",institution));
         if(!criteria.list().isEmpty())
         {
             TrainerBean trainerBean=(TrainerBean)criteria.list().get(0);
             session.close();
             return trainerBean.getTrainerId();
         }
          session.close();
         return null;
     }
     public  List<TrainerBean> viewAllTrainer()
     {
         Session session=Util.getSessionFactory().openSession();
         Query query=session.createQuery("From TrainerBean");
         List<TrainerBean> trainerBeans=query.list();
         session.close();
         return trainerBeans;
     }
}
