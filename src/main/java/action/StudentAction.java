package action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import entity.Course;
import entity.Student;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import service.StudentService;
import utils.FastJsonUtil;
import utils.LogUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName StudentAction
 * @Description TODO
 * Author sf
 * Date 18-11-26 下午11:09
 * @Version 1.0
 **/
public class StudentAction extends ActionSupport implements ModelDriven<Student> {
    private static Logger logger = LogUtils.getLogger();

    private Student student = new Student();
    private StudentService studentService;

    @Override
    public Student getModel() {
        return student;
    }

    public String getAllCourse(){
        Map<String, Object> session = ActionContext.getContext().getSession();
        Student student = (Student) session.get("user");

        logger.debug(student.getId());

        ValueStack valueStack = ActionContext.getContext().getValueStack();
        List<Course> courseList = studentService.findCourseList(student.getId());
        valueStack.set("courseList", courseList);
        return "course";
    }

    public  String courselist(){
        Map<String, Object> session = ActionContext.getContext().getSession();
        Student student = (Student) session.get("user");

        logger.debug(student.getId());

        //ValueStack valueStack = ActionContext.getContext().getValueStack();
        //List<Course> courseList = studentService.findCourseList(student.getId());
        //valueStack.set("courseList", courseList);
        JSONArray jsonArray = new JSONArray();
        jsonArray=studentService.findCourse(student.getId());
        logger.debug((jsonArray.toString()));

        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/javascript;charset=UTF-8");
        try {
                response.getWriter().print(jsonArray);
            } catch (IOException e) {
                e.printStackTrace();
            }

        return NONE;

    }

    public String searchmember(){
        HttpServletRequest  request = ServletActionContext.getRequest();
        String getvalue=request.getParameter("name");
        logger.debug(getvalue);

        List<Student> getstu=studentService.searchforstudent(getvalue);
        String str = JSON.toJSONString(getstu);
       // System.out.println(getstu);
        HttpServletResponse response=ServletActionContext.getResponse();

        if(getstu.isEmpty()){

            try {
                response.setContentType("text/javascript;charset=UTF-8");
                response.getWriter().print("null");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
                FastJsonUtil.writeJson(response,str);
        }



        return NONE;
    }
    public String getallclassmate(){
        return NONE;
    }

    public StudentService getStudentService() {
        return studentService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }
}
