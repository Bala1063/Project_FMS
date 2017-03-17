/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectFMS.DAO;

import ProjectFMS.Bean.DailyTaskAllocationBean;
import ProjectFMS.Util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Aruna
 */
public class DailyTaskAllocationDAO {
     public String addDailyTask(DailyTaskAllocationBean dailyTaskAllocationBean){
        Session session = Util.getSessionFactory().openSession();
         Transaction t = null;
        try {
            t = session.beginTransaction();
            session.saveOrUpdate(dailyTaskAllocationBean);
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
}
