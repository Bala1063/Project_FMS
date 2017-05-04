/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectFMS.SessionListener;

import ProjectFMS.DAO.LoginDAO;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * PRP_FMS:
 *
 * @author Aruna M,Aswini A,Balaji S K,Sushmitha S.
 */
public class ProjectFMSSessionListener implements HttpSessionListener {

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        String userId = (String) se.getSession().getAttribute("userId");
        if (userId != null) {
            new LoginDAO().logout(userId);
            se.getSession().invalidate();
        }
    }
}
