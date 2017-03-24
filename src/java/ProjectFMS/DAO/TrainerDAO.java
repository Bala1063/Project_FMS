/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectFMS.DAO;

import ProjectFMS.Bean.TaskBean;
import ProjectFMS.Util.Util;
import org.hibernate.Criteria;
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
   
   

}
