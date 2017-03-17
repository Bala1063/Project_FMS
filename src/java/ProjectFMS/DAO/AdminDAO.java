/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectFMS.DAO;

import ProjectFMS.Bean.ContentBean;
import ProjectFMS.Util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Sushmitha
 */
public class AdminDAO {
     public String addOrUpdateContent(ContentBean contentBean) {
        Session session = Util.getSessionFactory().openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            session.saveOrUpdate(contentBean);
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
       return "success";
    }
    
     public String addOrUpdateQuestion(QuestionBean questionBean){
         
        Session session = Util.getSessionFactory().openSession();
         Transaction t = null;
        try {
            t = session.beginTransaction();
            session.saveOrUpdate(questionBean);
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
     
    
     public String addOrUpdateDailyTaskAllocation(DailyTaskAllocationBean dailyTaskAllocationBean){
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
