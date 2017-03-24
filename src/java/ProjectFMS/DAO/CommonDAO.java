/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectFMS.DAO;

import ProjectFMS.Bean.MinimumWorkingPeriodBean;
import ProjectFMS.Bean.ReportBean;
import ProjectFMS.Util.Util;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Sushmitha
 */
public class CommonDAO {
      public String addOrUpdateDetails(Object o) {

        Session session = Util.getSessionFactory().openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            session.saveOrUpdate(o);
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
      public int getMinimumWorkingPeroid(String trainerId)
      {
           Session session = Util.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(MinimumWorkingPeriodBean.class);
        criteria.add(Restrictions.eq("trainerId", trainerId));
        if(!criteria.list().isEmpty()){
        MinimumWorkingPeriodBean minimumWorkingPeriodBean = (MinimumWorkingPeriodBean)criteria.list().get(0);
        return minimumWorkingPeriodBean.getMinimumWorkingPeriod();
        }
        session.close();
        return -1;
      }
}
