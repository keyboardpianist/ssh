package entity;

import com.alibaba.fastjson.annotation.JSONField;

public class GroupMember {
    private Integer valid;
    private  String id;// 复合的id

    @JSONField(serialize = false)
    private  Group group; //参考groupid
    private Student student;//参考学生id

    public Group getGroup() {
        return group;
    }
    public void setGroup(Group group) {
        this.group = group;
    }

    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }

    public Integer getValid() {
        return valid;
    }
    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "GroupMember{" +
                ", group=" + group.getGr_id() +
                ", student=" + student.getName() +
                '}';
    }
}
