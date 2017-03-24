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
 * @author Sushmitha
 */
public class AdminDAO {
    public String deleteTrainingDetails(List<String> trainingIdList){ 
        
        Session session=Util.getSessionFactory().openSession();
        for(int i=0;i<trainingIdList.size();i++){
            Query query=session.createQuery("delete from Training_tb where Training_id = '"+trainingIdList.get(i)+"'");
        }
        return "success";
    }
    
    
}
