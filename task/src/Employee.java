import java.util.Date;

public class Employee {
    private String empId; // 工号
    private Date birthday; // 出生日期
    private String name; // 姓名
    private Date empdate; // 入职日期
    private String jobNature; // 岗位性质
    private double baseSalary; // 基本工资
    private String gender; // 性别
    private String hrId; // 添加的HR的ID

    public Employee(String empId, Date getEmpDate, String name, Date empdate, String jobNature, double baseSalary, String gender, String hrId) {
        this.empId = empId;
        this.birthday = birthday;
        this.name = name;
        this.empdate = empdate;
        this.jobNature = jobNature;
        this.baseSalary = baseSalary;
        this.gender = gender;
        this.hrId = hrId;
    }


    public Employee() {
    }

    /**
     * 获取
     * @return empId
     */
    public String getEmpId() {
        return empId;
    }

    /**
     * 设置
     * @param empId
     */
    public void setEmpId(String empId) {
        this.empId = empId;
    }

    /**
     * 获取
     * @return birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置
     * @param birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return empdate
     */
    public Date getempdate() {
        return empdate;
    }

    /**
     * 设置
     * @param empdate
     */
    public void setempdate(Date empdate) {
        this.empdate = empdate;
    }

    /**
     * 获取
     * @return jobNature
     */
    public String getJobNature() {
        return jobNature;
    }

    /**
     * 设置
     * @param jobNature
     */
    public void setJobNature(String jobNature) {
        this.jobNature = jobNature;
    }

    /**
     * 获取
     * @return baseSalary
     */
    public double getBaseSalary() {
        return baseSalary;
    }

    /**
     * 设置
     * @param baseSalary
     */
    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    /**
     * 获取
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * 设置
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 获取
     * @return hrId
     */
    public String getHrId() {
        return hrId;
    }

    /**
     * 设置
     * @param hrId
     */
    public void setHrId(String hrId) {
        this.hrId = hrId;
    }

    public String toString() {
        return "Employee{empId = " + empId + ", birthday = " + birthday + ", name = " + name + ", empdate = " + empdate + ", jobNature = " + jobNature + ", baseSalary = " + baseSalary + ", gender = " + gender + ", hrId = " + hrId + "}";
    }
}