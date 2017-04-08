/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectFMS.Servlet;

import ProjectFMS.Bean.DefaultMWP;
import ProjectFMS.Bean.MinimumWorkingPeriodBean;
import ProjectFMS.Bean.TrainerBean;
import ProjectFMS.DAO.AdminDAO;
import ProjectFMS.DAO.CommonDAO;
import ProjectFMS.DAO.TrainerDAO;
import ProjectFMS.IDgenerator.TrainerIDgenerator;
import ProjectFMS.Util.Util;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author bala
 */
public class AdminTrainerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");

        if (operation.equalsIgnoreCase("add") || operation.equalsIgnoreCase("update")) {
            TrainerBean trainerBean = null;
            if (operation.equalsIgnoreCase("add")) {
                trainerBean = new TrainerBean();
            } else {
                trainerBean = new TrainerDAO().viewTrainerDetails(request.getParameter("userid"));
            }
            trainerBean.setAreaOfSpecialization(request.getParameter("areaofspecialization"));
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println(request.getParameter("dateofbirth"));

            try {
                trainerBean.setDateOfBirth(dateFormat.parse(request.getParameter("dateofbirth")));
            } catch (ParseException ex) {
                Logger.getLogger(AdminTrainerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            trainerBean.setInstitution(request.getParameter("institution"));
            trainerBean.setMailId(request.getParameter("mailid"));
            trainerBean.setPhoneNo(Long.parseLong(request.getParameter("phoneno")));
            trainerBean.setQualifications(request.getParameter("qualifications"));
            trainerBean.setSelfSkilling(request.getParameter("selfskilling"));
            trainerBean.setTrainerName(request.getParameter("trainername"));
       
            if (operation.equalsIgnoreCase("add")) {
                trainerBean.setTrainerId(new TrainerIDgenerator().generateTrainerId(trainerBean));
             MinimumWorkingPeriodBean   minimumWorkingPeriodBean=new MinimumWorkingPeriodBean();
                minimumWorkingPeriodBean.setTrainerBean(trainerBean);
                minimumWorkingPeriodBean.setMinimumWorkingPeriod(new AdminDAO().getDefaultMWP().getDefaultMWP());
                trainerBean.setMinimumWorkingPeriodBean(minimumWorkingPeriodBean);
            }
            if (new CommonDAO().addOrUpdateDetails(trainerBean).equalsIgnoreCase("success")) {
                if (operation.equalsIgnoreCase("add")) {
                    request.setAttribute("status", "Trainer Added");
                    request.getRequestDispatcher("AdminAddTrainer.jsp").forward(request, response);
                } else if (operation.equalsIgnoreCase("update")) {
                    request.setAttribute("status", "Trainer Updated");
                    request.getRequestDispatcher("AdminUpdateTrainer.jsp").forward(request, response);
                }
            }
        } else if (operation.equalsIgnoreCase("institutionlist")) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            String institutionlist = "";
            List<TrainerBean> trainerBeans = new TrainerDAO().viewAllTrainer();
            for (TrainerBean trainerBean : trainerBeans) {
                institutionlist += "<option>" + trainerBean.getInstitution() + "</option>";
            }
            out.println(institutionlist);
        } else if (operation.equalsIgnoreCase("namelist")) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            String namelist = "";
            String institution = request.getParameter("institution");
            List<TrainerBean> trainerBeans = new TrainerDAO().viewAllTrainer();
            for (TrainerBean trainerBean : trainerBeans) {
                if (institution.equalsIgnoreCase(trainerBean.getInstitution())) {
                    namelist += "<option>" + trainerBean.getTrainerName() + "</option>";
                }
            }
            out.println(namelist);
        } else if (operation.equalsIgnoreCase("getDetails")) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            String details = "";
            String institution = request.getParameter("institution");
            String trainerName = request.getParameter("trainername");
            List<TrainerBean> trainerBeans = new TrainerDAO().viewAllTrainer();
            for (TrainerBean trainerBean : trainerBeans) {
                if (institution.equalsIgnoreCase(trainerBean.getInstitution()) && trainerName.equalsIgnoreCase(trainerBean.getTrainerName())) {
                    details = trainerBean.toString();
                }
            }
            out.println(details);

        } else if (operation.equalsIgnoreCase("remove")) {
            String institution = request.getParameter("institution");
            String trainername = request.getParameter("trainername");
            System.out.println(trainername);
            System.out.println(institution);
            System.out.println(new TrainerDAO().getTrainerId(trainername, institution));
            if (new AdminDAO().removeTrainerDetails(new TrainerDAO().getTrainerId(trainername, institution)).equalsIgnoreCase("success")) {
                request.setAttribute("status", "Trainer Removed");
                request.getRequestDispatcher("AdminRemoveTrainer.jsp").forward(request, response);
            }

        } else if (operation.equalsIgnoreCase("getdmwp")) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            String result = new AdminDAO().getDefaultMWP().getDefaultMWP() + "";
            out.println(result);

        }
        else if(operation.equalsIgnoreCase("dmwp"))
        {
            Session session=Util.getSessionFactory().openSession();
            Query query=session.createQuery("From DefaultMWP");
            int dwmp=Integer.parseInt(request.getParameter("minimumworkingperiod"));
            if(!query.list().isEmpty())
            {
                DefaultMWP defaultMWP=new AdminDAO().getDefaultMWP();
                defaultMWP.setDefaultMWP(dwmp);
                if(new CommonDAO().addOrUpdateDetails(defaultMWP).equalsIgnoreCase("success"))
                {
                    request.setAttribute("status", "Updated");
                    request.getRequestDispatcher("AdminSetDMWP.jsp").forward(request, response);
                }
            }
          
        }
    }
}
