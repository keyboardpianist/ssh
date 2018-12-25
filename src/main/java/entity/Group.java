package entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.HashSet;
import java.util.Set;

/**
 *@author:LNX
 *@date:2018-12-13
 *@description:
 */
public class Group {

    private  String gr_id;//小组编号
    private  String gr_cheif;//小组leader编号
    private  String gr_email;//小组邮箱
    private  String gr_qq;//小组qq
    private  String gr_phone;//小组联系电话

    private  Integer gr_num;//小组成员数量
    private  Integer valid;
    private  Course course;
    private Set<GroupMember> groupMembers = new HashSet<>();//一个小组对多个小组成员
    private Set<Homework> homeworks=new HashSet<>();//一个小组有多个作业
    @JSONField(serialize=false)
    public Set<Homework> getHomeworks() {
        return homeworks;
    }
    @JSONField(serialize=false)
    public void setHomeworks(Set<Homework> homeworks) {
        this.homeworks = homeworks;
    }
    @JSONField(serialize=false)
    public Set<GroupMember> getGroupMembers() {
        return groupMembers;
    }


    @JSONField(serialize=false)
    public void setGroupMembers(Set<GroupMember> groupMembers) {
        this.groupMembers = groupMembers;
    }



    public String getGr_id() {
        return gr_id;
    }
    @JSONField(serialize=false)
    public void setGr_id(String gr_id) {
        this.gr_id = gr_id;
    }

    public String getGr_cheif() {
        return gr_cheif;
    }
    @JSONField(serialize=false)
    public void setGr_cheif(String gr_cheif) {
        this.gr_cheif = gr_cheif;
    }

    public String getGr_email() {
        return gr_email;
    }
    @JSONField(serialize=false)
    public void setGr_email(String gr_email) {
        this.gr_email = gr_email;
    }

    public String getGr_qq() {
        return gr_qq;
    }
    @JSONField(serialize=false)
    public void setGr_qq(String gr_qq) {
        this.gr_qq = gr_qq;
    }

    public String getGr_phone() {
        return gr_phone;
    }
    @JSONField(serialize=false)
    public void setGr_phone(String gr_phone) {
        this.gr_phone = gr_phone;
    }

    public Integer getGr_num() {
        return gr_num;
    }
    @JSONField(serialize=false)
    public void setGr_num(Integer gr_num) {
        this.gr_num = gr_num;
    }

    public Integer getValid() {
        return valid;
    }
    @JSONField(serialize=false)
    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public Course getCourse() {
        return course;
    }
    @JSONField(serialize=false)
    public void setCourse(Course course) {
        this.course = course;
    }
}
