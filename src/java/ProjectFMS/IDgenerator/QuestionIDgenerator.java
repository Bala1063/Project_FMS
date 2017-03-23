/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectFMS.IDgenerator;

import ProjectFMS.Bean.ContentBean;
import ProjectFMS.Util.DBUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Aruna
 */
public class QuestionIDgenerator {
    
     public String generateOrderId(ContentBean contentBean) {
        ResultSet resultSet = null;
        Statement statement = null;

        try {
            Connection con = DBUtil.getDBConnection();
            statement = con.createStatement();
            String mid=contentBean.getContentId();
            String prefix = "QU_"+""+mid;
            int x = 0;
            try {

                resultSet = statement.executeQuery("Select Question_Seq.nexttval from dual");
            } catch (Exception e) {
                statement.execute("create sequence Question_Seq start with 1 increment by 1 nocycles nocache");
                resultSet = statement.executeQuery("Select Question_Seq.nexttval from dual");
            }
            if (resultSet.next()) {
                x = (Integer) resultSet.getInt(1);
                String suffix = String.format("%03d", x);
                return prefix.concat(suffix);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
