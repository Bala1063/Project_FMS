/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectFMS.DAO;


import ProjectFMS.Util.Util;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;


/**
 *
 * @author bala
 */
public class AdminDAO {
      public String deleteTrainerDetails(List<String> trainerIDList) {
        Session session = Util.getSessionFactory().openSession();
        for(int i=0;i<trainerIDList.size();i++){
             Query query = session.createSQLQuery("delete from Trainer_tb where Trainer_id = '"+trainerIDList.get(i)+"'");
        }
       return "success";
    }
}
