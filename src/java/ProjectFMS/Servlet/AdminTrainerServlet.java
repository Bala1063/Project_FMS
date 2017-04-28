/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectFMS.Servlet;

import ProjectFMS.Bean.AnswerBean;
import ProjectFMS.Bean.ContentBean;
import ProjectFMS.Bean.DefaultMWP;
import ProjectFMS.Bean.LoginBean;
import ProjectFMS.Bean.MinimumWorkingPeriodBean;
import ProjectFMS.Bean.QuestionBean;
import ProjectFMS.Bean.TaskBean;
import ProjectFMS.Bean.TrainerBean;
import ProjectFMS.Bean.TrainingScheduleBean;
import ProjectFMS.DAO.AdminDAO;
import ProjectFMS.DAO.CommonDAO;
import ProjectFMS.DAO.SendEmailDAO;
import ProjectFMS.DAO.TrainerDAO;
import ProjectFMS.IDgenerator.TrainerIDgenerator;
import ProjectFMS.IDgenerator.TrainingIDgenerator;
import ProjectFMS.Util.Util;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
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

            try {
                trainerBean.setDateOfBirth(dateFormat.parse(request.getParameter("dateofbirth")));
            } catch (ParseException ex) {
                Logger.getLogger(AdminTrainerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            trainerBean.setInstitution(request.getParameter("institution"));
            trainerBean.setMailId(request.getParameter("mailid"));
            trainerBean.setPhoneNo(Long.parseLong(request.getParameter("phoneno")));
            trainerBean.setQualifications(request.getParameter("qualifications"));
            trainerBean.setTrainerName(request.getParameter("trainername"));

            if (operation.equalsIgnoreCase("add")) {
                trainerBean.setTrainerId(new TrainerIDgenerator().generateTrainerId(trainerBean));
                MinimumWorkingPeriodBean minimumWorkingPeriodBean = new MinimumWorkingPeriodBean();
                minimumWorkingPeriodBean.setTrainerBean(trainerBean);
                minimumWorkingPeriodBean.setMinimumWorkingPeriod(new AdminDAO().getDefaultMWP().getDefaultMWP());
                trainerBean.setMinimumWorkingPeriodBean(minimumWorkingPeriodBean);
            }
            if (new CommonDAO().addOrUpdateDetails(trainerBean).equalsIgnoreCase("success")) {
                if (operation.equalsIgnoreCase("add")) {
                    LoginBean loginBean = new LoginBean();
                    loginBean.setUserId(trainerBean.getTrainerId());
                    loginBean.setPassword(trainerBean.getTrainerId());
                    Random rand = new Random();
                    int n = rand.nextInt(999999 + 1 - 99999) + 99999;
                    String msg="Username:"+" "+trainerBean.getTrainerId()+" "+"Password:"+" "+n;
                    new SendEmailDAO().sendmail(trainerBean.getMailId(), msg);
                    loginBean.setType("T");
                    new CommonDAO().addOrUpdateDetails(loginBean);
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
            out.flush();
            out.close();
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
            out.flush();
            out.close();
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
            out.flush();
            out.close();

        } else if (operation.equalsIgnoreCase("remove")) {
            String institution = request.getParameter("institution");
            String trainername = request.getParameter("trainername");
            if (new AdminDAO().removeTrainerDetails(new TrainerDAO().getTrainerId(trainername, institution)).equalsIgnoreCase("success")) {
                request.setAttribute("status", "Trainer Removed");
                request.getRequestDispatcher("AdminRemoveTrainer.jsp").forward(request, response);
            }

        } else if (operation.equalsIgnoreCase("getdmwp")) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            String result = new AdminDAO().getDefaultMWP().getDefaultMWP() + "";
            out.println(result);
            out.flush();
            out.close();

        } else if (operation.equalsIgnoreCase("dmwp")) {
            Session session = Util.getSessionFactory().openSession();
            Query query = session.createQuery("From DefaultMWP");
            int dwmp = Integer.parseInt(request.getParameter("minimumworkingperiod"));
            if (!query.list().isEmpty()) {
                DefaultMWP defaultMWP = new AdminDAO().getDefaultMWP();

                if (new CommonDAO().addOrUpdateDetails(defaultMWP).equalsIgnoreCase("success")) {
                    request.setAttribute("status", "Updated");
                    request.getRequestDispatcher("AdminSetDMWP.jsp").forward(request, response);
                }
            } else {
                DefaultMWP defaultMWP = new DefaultMWP();
                defaultMWP.setDefaultMWP(dwmp);
                if (new CommonDAO().addOrUpdateDetails(defaultMWP).equalsIgnoreCase("success")) {
                    request.setAttribute("status", "Updated");
                    request.getRequestDispatcher("AdminSetDMWP.jsp").forward(request, response);
                }
            }

        } else if (operation.equalsIgnoreCase("mwp")) {
            TrainerDAO trainerDAO = new TrainerDAO();
            TrainerBean trainerBean = trainerDAO.viewTrainerDetails(trainerDAO.getTrainerId(request.getParameter("trainername"), request.getParameter("institution")));
            trainerBean.getMinimumWorkingPeriodBean().setMinimumWorkingPeriod(Integer.parseInt(request.getParameter("minimumworkingperiod")));
            if (new CommonDAO().addOrUpdateDetails(trainerBean).equalsIgnoreCase("success")) {
                request.setAttribute("status", "success");
                request.getRequestDispatcher("AdminSetMWP.jsp").forward(request, response);
            }
        } else if (operation.equalsIgnoreCase("allocate")) {
            TrainerDAO trainerDAO = new TrainerDAO();
            TrainerBean trainerBean = trainerDAO.viewTrainerDetails(trainerDAO.getTrainerId(request.getParameter("trainername"), request.getParameter("institution")));
            TrainingScheduleBean trainingScheduleBean = new TrainingScheduleBean();
            trainingScheduleBean.setTrainingId(new TrainingIDgenerator().generateTrainingId(trainerBean));
            trainingScheduleBean.setTrainingStream(request.getParameter("trainingstream"));
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                trainingScheduleBean.setFromDate(dateFormat.parse(request.getParameter("commencingdate")));
                trainingScheduleBean.setToDate(dateFormat.parse(request.getParameter("endingdate")));
            } catch (ParseException ex) {
                Logger.getLogger(AdminTrainerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            trainingScheduleBean.setTrainerId(trainerBean.getTrainerId());
            trainerBean.getTrainingScheduleBeans().add(trainingScheduleBean);
            if (new CommonDAO().addOrUpdateDetails(trainerBean).equalsIgnoreCase("success")) {
                request.setAttribute("status", "Training Allotted");
                request.getRequestDispatcher("AdminAllocateTraining.jsp").forward(request, response);
            }
        } else if (operation.equalsIgnoreCase("addtask")) {
            TrainerDAO trainerDAO = new TrainerDAO();
            String trainingId = trainerDAO.getTrainingId(trainerDAO.getTrainerId(request.getParameter("trainername"), request.getParameter("institution")));
            if (trainingId != null) {
                Session session = Util.getSessionFactory().openSession();
                TrainingScheduleBean trainingScheduleBean = (TrainingScheduleBean) session.get(TrainingScheduleBean.class, trainingId);
                session.close();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = dateFormat.parse(request.getParameter("taskdate"));
                } catch (ParseException ex) {
                    Logger.getLogger(AdminTrainerServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(trainerDAO.checkTask(trainingId, date));
                if (!trainerDAO.checkTask(trainingId, date)) {
                    TaskBean taskBean = new TaskBean();
                    taskBean.setTask(request.getParameter("task"));
                    taskBean.setTaskDate(date);
                    taskBean.setTaskStatus("no");
                    taskBean.setTrainingId(trainingId);
                    trainingScheduleBean.getTaskBeans().add(taskBean);
                    if (new CommonDAO().addOrUpdateDetails(trainingScheduleBean).equalsIgnoreCase("success")) {
                        request.setAttribute("status", "Task Added");
                        request.getRequestDispatcher("AdminAddTask.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("status", "Already Added");
                    request.getRequestDispatcher("AdminAddTask.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("status", "There is no Training to Add Task");
                request.getRequestDispatcher("AdminAddTask.jsp").forward(request, response);
            }
        } else if (operation.equalsIgnoreCase("scheduledetails")) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            TrainerDAO trainerDAO = new TrainerDAO();
            String trainingId = trainerDAO.getTrainingId(trainerDAO.getTrainerId(request.getParameter("trainername"), request.getParameter("institution")));
            Session session = Util.getSessionFactory().openSession();
            TrainingScheduleBean trainingScheduleBean = (TrainingScheduleBean) session.get(TrainingScheduleBean.class, trainingId);
            session.close();
            String details = trainingScheduleBean.toString();
            out.println(details);
            out.flush();
            out.close();

        } else if (operation.equalsIgnoreCase("updatetraining")) {
            Session session = Util.getSessionFactory().openSession();
            TrainingScheduleBean trainingScheduleBean = (TrainingScheduleBean) session.get(TrainingScheduleBean.class, request.getParameter("trainingid"));
            session.close();
            trainingScheduleBean.setTrainingStream(request.getParameter("trainingstream"));
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                trainingScheduleBean.setFromDate(dateFormat.parse(request.getParameter("commencingdate")));
                trainingScheduleBean.setToDate(dateFormat.parse(request.getParameter("endingdate")));
            } catch (ParseException ex) {
                Logger.getLogger(AdminTrainerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (new CommonDAO().addOrUpdateDetails(trainingScheduleBean).equalsIgnoreCase("success")) {
                request.setAttribute("status", "Updated");
                request.getRequestDispatcher("AdminUpdateTraining.jsp").forward(request, response);
            }
        } else if (operation.equalsIgnoreCase("trainingDetails")) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            TrainerDAO trainerDAO = new TrainerDAO();
            AdminDAO adminDAO = new AdminDAO();
            String content = "";
            String institution = request.getParameter("institution");
            List<TrainerBean> trainerBeans = null;
            if (institution.equalsIgnoreCase("all")) {
                trainerBeans = trainerDAO.viewAllTrainer();
            } else {
                trainerBeans = trainerDAO.viewAllTrainerByInstitution(institution);
            }
            if (!trainerBeans.isEmpty()) {
                for (TrainerBean trainerBean : trainerBeans) {
                    content += "<tr>";
                    content += "<td scope='row'>" + trainerBean.getTrainerName() + "</td><td>" + trainerBean.getInstitution() + "</td><td>" + trainerBean.getMinimumWorkingPeriodBean().getMinimumWorkingPeriod() + "</td>";
                    TrainingScheduleBean trainingScheduleBean = adminDAO.viewTrainingScheduleByTrainerId(trainerBean.getTrainerId());
                    if (trainingScheduleBean != null) {
                        content += "<td>" + trainingScheduleBean.getTrainingStream() + "</td><td>" + trainingScheduleBean.getFromDate() + "</td><td>" + trainingScheduleBean.getToDate() + "</td>";
                    } else {
                        content += "<td>" + "nil" + "</td><td>" + "nil" + "</td><td>" + "nil" + "</td>";
                    }
                }
                out.println(content);
                out.flush();
                out.close();
            } else {
                content += "<tr><td colspan='6'><div class='col-sm-offset-4'> No RECORDS</div></td></tr>";
                out.println(content);
                out.flush();
                out.close();
            }
        } else if (operation.equalsIgnoreCase("traineractivity")) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            TrainerDAO trainerDAO = new TrainerDAO();
            AdminDAO adminDAO = new AdminDAO();
            String details = "";
            System.out.println(request.getParameter("trainername"));
            System.out.println(request.getParameter("institution"));
            String trainerId = trainerDAO.getTrainerId(request.getParameter("trainername"), request.getParameter("institution"));
            System.out.println(trainerId);
            TrainingScheduleBean trainingScheduleBean = adminDAO.viewTrainingScheduleByTrainerId(trainerId);
            System.out.println(trainingScheduleBean);
            if (trainingScheduleBean != null) {
                details += trainingScheduleBean.getTrainingStream() + "," + trainingScheduleBean.getFromDate() + "," + trainingScheduleBean.getToDate() + "," + (((trainingScheduleBean.getToDate().getTime() - trainingScheduleBean.getFromDate().getTime()) / (1000 * 60 * 60 * 24)) + 1) + ",";
                List<ContentBean> contentBeans = trainingScheduleBean.getContentBeans();
                List<QuestionBean> questionBeans = trainingScheduleBean.getQuestionBeans();
                List<TaskBean> taskBeans = trainingScheduleBean.getTaskBeans();
                if (!taskBeans.isEmpty()) {
                    for (TaskBean taskBean : taskBeans) {
                        if (taskBean.getTaskStatus().equalsIgnoreCase("no")) {
                            details += "<tr class='danger'><td scope='row'>" + taskBean.getTask() + "</td><td>" + taskBean.getTaskDate() + "</td></tr>";
                        } else {
                            details += "<tr class='success'><td scope='row'>" + taskBean.getTask() + "</td><td>" + taskBean.getTaskDate() + "</td></tr>";
                        }
                    }
                    details += ",";
                } else {
                    details += "<tr><td scope='row' colspan='2'><div class='col-sm-offset-5' > No Records</div></td></tr>,";
                }
                int i = 1;
                int count = 0;
                if (!contentBeans.isEmpty()) {

                    for (ContentBean contentBean : contentBeans) {
                        if (trainerDAO.checkTaskStatus(contentBean.getTrainingId(), contentBean.getContentDate()).equalsIgnoreCase("yes")) {
                            count++;
                        }
                    }
                    if (count > 0) {
                        details += "<div class='panel-group'>";
                        for (ContentBean contentBean : contentBeans) {
                            if (trainerDAO.checkTaskStatus(contentBean.getTrainingId(), contentBean.getContentDate()).equalsIgnoreCase("yes")) {
                                details += "<div class='panel panel-default'>" + "<div class='panel-heading'>" + i + "." + contentBean.getContentName() + "</div><div class='panel-body'>" + contentBean.getContentDescription() + "</div></div>";
                                i++;
                            }
                        }
                        i = 1;
                        details += "</div>,";
                    } else {
                        details += "nocontent,";
                    }
                } else {
                    details += "nocontent,";
                }
                count = 0;
                if (!questionBeans.isEmpty()) {
                    for (QuestionBean questionBean : questionBeans) {
                        if (trainerDAO.checkTaskStatus(questionBean.getTrainingId(), questionBean.getQuestionDate()).equalsIgnoreCase("yes")) {
                            count++;
                        }
                    }
                    if (count > 0) {
                        details += "<div class='panel-group'>";
                        for (QuestionBean questionBean : questionBeans) {
                            details += "<div class='panel panel-default'>" + "<div class='panel-heading'>" + i + "." + questionBean.getQuestion() + "</div><div class='panel-body'>Options: ";
                            List<AnswerBean> answerBeans = questionBean.getAnswerBeans();
                            char option = 'a';
                            String answer = "";
                            for (AnswerBean answerBean : answerBeans) {
                                if (answerBean.getAnswerType().equalsIgnoreCase("A")) {
                                    answer = answerBean.getAnswer();
                                }
                            }
                            for (AnswerBean answerBean : answerBeans) {
                                if (answerBean.getAnswerType().equalsIgnoreCase("o")) {
                                    details += option + ")" + answerBean.getAnswer() + " ";
                                    if (answer.equalsIgnoreCase(answerBean.getAnswer())) {
                                        answer = option + ")" + answerBean.getAnswer();
                                    }
                                    option++;
                                }

                            }
                            i++;
                            details += "</div><div class='panel-footer'>" + "Answer: " + answer + "</div></div>,";
                        }
                        i = 1;
                    } else {
                        details += "noquestion";
                    }
                } else {
                    details += "noquestion";

                }
            }
            out.println(details.trim());
            out.flush();
            out.close();
        }

    }
}
