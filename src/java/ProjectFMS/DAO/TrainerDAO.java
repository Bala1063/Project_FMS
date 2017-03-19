/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectFMS.DAO;

import ProjectFMS.Bean.DailyTaskUpdationBean;
import ProjectFMS.Bean.LeaveBean;
import ProjectFMS.Bean.ReportBean;
import ProjectFMS.Bean.TrainerBean;
import ProjectFMS.Util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author AswiniAnjappan
 */
public class TrainerDAO {
     public String addOrUpdateLeaveDetails(LeaveBean leaveBean) {
        
        Session session = Util.getSessionFactory().openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
           session.saveOrUpdate(leaveBean);
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
    public String addOrUpdateTrainerDetails(TrainerBean trainerBean) {
        
        Session session = Util.getSessionFactory().openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
           session.saveOrUpdate(trainerBean);
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
    public String addOrUpdateReport(ReportBean reportBean) {
        Session session = Util.getSessionFactory().openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            session.saveOrUpdate(reportBean);
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
      public String addOrUpdateTask(DailyTaskUpdationBean dailyTaskUpdationBean) {
        Session session = Util.getSessionFactory().openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            session.saveOrUpdate(dailyTaskUpdationBean);
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
