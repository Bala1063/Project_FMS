/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectFMS.Servlet;

import ProjectFMS.Bean.AnswerBean;
import ProjectFMS.Bean.ContentBean;
import ProjectFMS.Bean.LeaveBean;
import ProjectFMS.Bean.QuestionBean;
import ProjectFMS.Bean.TaskBean;
import ProjectFMS.Bean.TrainerBean;
import ProjectFMS.Bean.TrainingScheduleBean;
import ProjectFMS.DAO.CommonDAO;
import ProjectFMS.DAO.TrainerDAO;
import ProjectFMS.IDgenerator.QuestionIDgenerator;
import ProjectFMS.Util.Util;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;

/**
 *
 * @author bala
 */
public class TrainerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");
        if (operation.equalsIgnoreCase("leave")) {
            String trainerId = (String) request.getSession(false).getAttribute("userId");
            TrainerBean trainerBean = new TrainerDAO().viewTrainerDetails(trainerId);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fromDate = null;
            Date toDate = null;
            try {
                fromDate = dateFormat.parse(request.getParameter("fromdate"));
                toDate = dateFormat.parse(request.getParameter("todate"));
            } catch (ParseException ex) {
                Logger.getLogger(TrainerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            LeaveBean leaveBean = new LeaveBean();
            leaveBean.setTrainerId(trainerId);
            leaveBean.setFromDate(fromDate);
            leaveBean.setToDate(toDate);
            leaveBean.setPurpose(request.getParameter("purpose"));
            trainerBean.getLeaveBeans().add(leaveBean);
            if (new CommonDAO().addOrUpdateDetails(trainerBean).equalsIgnoreCase("success")) {
                request.setAttribute("status", "Leave Updated");
                request.getRequestDispatcher("TrainerLeave.jsp").forward(request, response);
            }
        } else if (operation.equalsIgnoreCase("content")) {
            TrainerDAO trainerDAO = new TrainerDAO();
            String trainerId = (String) request.getSession(false).getAttribute("userId");
            String trainingId = new TrainerDAO().getTrainingId(trainerId);
            if (trainingId != null) {
                Session session = Util.getSessionFactory().openSession();
                TrainingScheduleBean trainingScheduleBean = (TrainingScheduleBean) session.get(TrainingScheduleBean.class, trainingId);
                session.close();
                Date date = new Date();
                if (trainerDAO.checkTask(trainingId, date)) {
                    ContentBean contentBean = new ContentBean();
                    contentBean.setContentDescription(request.getParameter("contentdescription"));
                    contentBean.setContentDate(date);
                    contentBean.setContentName(request.getParameter("contenttitle"));
                    contentBean.setTrainingId(trainingId);
                    trainingScheduleBean.getContentBeans().add(contentBean);
                    if (new CommonDAO().addOrUpdateDetails(trainingScheduleBean).equalsIgnoreCase("success")) {
                        request.setAttribute("status", "Added Successfully");
                        request.getRequestDispatcher("TrainerCreateContent.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("status", "No Task is Alloted");
                    request.getRequestDispatcher("TrainerCreateContent.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("status", "No Training is Alloted to Add");
                request.getRequestDispatcher("TrainerCreateContent.jsp").forward(request, response);
            }

        } else if (operation.equalsIgnoreCase("question")) {
            TrainerDAO trainerDAO = new TrainerDAO();
            String trainerId = (String) request.getSession(false).getAttribute("userId");
            String trainingId = new TrainerDAO().getTrainingId(trainerId);
            if (trainingId != null) {
                Session session = Util.getSessionFactory().openSession();
                TrainingScheduleBean trainingScheduleBean = (TrainingScheduleBean) session.get(TrainingScheduleBean.class, trainingId);
                session.close();
                Date date = new Date();
                if (trainerDAO.checkTask(trainingId, date)) {
                    QuestionBean questionBean = new QuestionBean();
                    questionBean.setQuestion(request.getParameter("question"));
                    questionBean.setQuestionDate(date);
                    questionBean.setTrainingId(trainingId);
                    questionBean.setQuestionId(new QuestionIDgenerator().generateQuestionId(trainingScheduleBean));
                    List<AnswerBean> answerBeans = new ArrayList<>();
                    AnswerBean answerBean = null;
                    int count = Integer.parseInt(request.getParameter("count"));
                    for (int i = 1; i <= count; i++) {
                        answerBean = new AnswerBean();
                        answerBean.setAnswer(request.getParameter("option" + i));
                        answerBean.setAnswerType("O");
                        answerBean.setQuestionId(questionBean.getQuestionId());
                        answerBeans.add(answerBean);
                    }
                    answerBean = new AnswerBean();
                    answerBean.setAnswer(request.getParameter("answer"));
                    answerBean.setAnswerType("A");
                    answerBean.setQuestionId(questionBean.getQuestionId());
                    answerBeans.add(answerBean);
                    questionBean.setAnswerBeans(answerBeans);
                    trainingScheduleBean.getQuestionBeans().add(questionBean);
                    if (new CommonDAO().addOrUpdateDetails(trainingScheduleBean).equalsIgnoreCase("success")) {
                        request.setAttribute("status", "Added Successfully");
                        request.getRequestDispatcher("TrainerAddQuestion.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("status", "No Task is Alloted");
                    request.getRequestDispatcher("TrainerCreateContent.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("status", "No Training is Alloted to Add");
                request.getRequestDispatcher("TrainerAddQuestion.jsp").forward(request, response);
            }
        } else if (operation.equalsIgnoreCase("updatetask")) {
            TrainerDAO trainerDAO = new TrainerDAO();
            String trainerId = (String) request.getSession(false).getAttribute("userId");
            String trainingId = new TrainerDAO().getTrainingId(trainerId);
            Session session = Util.getSessionFactory().openSession();
            TaskBean taskBean = (TaskBean) session.get(TaskBean.class, request.getParameter("taskid"));
            session.close();
            if (taskBean.getTask().equalsIgnoreCase("contentcreation")) {
                if (trainerDAO.contentCount(trainingId, taskBean.getTaskDate()) > 0) {
                    taskBean.setTaskStatus("yes");
                    if (new CommonDAO().addOrUpdateDetails(taskBean).equalsIgnoreCase("success")) {
                        request.setAttribute("status", "Task Updated");
                        request.getRequestDispatcher("TrainerUpdateTask.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("status", "InComplete Task");
                    request.getRequestDispatcher("TrainerUpdateTask.jsp").forward(request, response);
                }
            } else if (taskBean.getTask().equalsIgnoreCase("questionbankcreation")) {
                if (trainerDAO.questionCount(trainingId, taskBean.getTaskDate()) >= 10) {
                    taskBean.setTaskStatus("yes");
                    if (new CommonDAO().addOrUpdateDetails(taskBean).equalsIgnoreCase("success")) {
                        request.setAttribute("status", "Task Updated");
                        request.getRequestDispatcher("TrainerUpdateTask.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("status", "Add Atleast 10 Questions");
                    request.getRequestDispatcher("TrainerUpdateTask.jsp").forward(request, response);
                }

            }
        } else if (operation.equalsIgnoreCase("getOTP")) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            Random rand = new Random();
            int n = rand.nextInt(999999 + 1 - 99999) + 99999;
            out.println(n + "");
            out.flush();
            out.close();
        }
        else if(operation.equalsIgnoreCase(""))

    }

}
