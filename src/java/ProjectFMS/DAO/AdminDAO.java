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
    public String deleteContentsByContentId(List<String> contentIds){
 
        Session session=Util.getSessionFactory().openSession();
        for(int i=0;i<contentIds.size();i++){
            Query query = session.createSQLQuery("delete from Content_tb where Content_id='"+contentIds.get(i)+"'");
        }
        session.close();
        return "success";
    }
    
    public String deleteDailyTaskAllocation
}
