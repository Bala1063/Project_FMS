/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectFMS.IDgenerator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

/**
 *
 * @author PROJECT FMS
 */
public class ContentIDgenerator implements IdentifierGenerator {
      @Override
    public Serializable generate(SessionImplementor si, Object o) throws HibernateException {

        ResultSet resultSet = null;
        Statement statement = null;

        try {
            Connection con = si.connection();
            statement = con.createStatement();
            String prefix = "CON_";
            int x = 0;
            try {

                resultSet = statement.executeQuery("Select Content_Seq.nextval from dual");
            } catch (Exception e) {
                statement.execute("create sequence Content_Seq start with 1 increment by 1 nocycle nocache");
                resultSet = statement.executeQuery("Select Content_Seq.nextval from dual");
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
