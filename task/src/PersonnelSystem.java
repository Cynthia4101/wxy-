import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class PersonnelSystem {
    private ArrayList<Admin> admins = new ArrayList<>();
    private ArrayList<Employee> employees = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private Admin currentAdmin; // 用于记录当前登录的管理员
    private int loginFailCount = 0; // 记录登录失败次数的计数器

    // 生成指定长度的验证码函数
    public static String generateVerificationCode(int length) {
        StringBuilder verificationCode = new StringBuilder();
        String allChars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        int allCharsLength = allChars.length();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(allCharsLength);
            verificationCode.append(allChars.charAt(index));
        }
        return verificationCode.toString();
    }

    // 管理员注册功能
    public void registerAdmin() {
        System.out.println("请输入管理员账号：");
        String account = scanner.nextLine();
        System.out.println("请输入管理员密码：");
        String password = scanner.nextLine();
        int id = admins.size() + 1;
        Admin admin = new Admin(account, password, id);
        admins.add(admin);
        System.out.println("管理员注册成功！");
    }

    // 管理员登录功能
    public boolean loginAdmin() {
        if (loginFailCount >= 3) {
            System.out.println("您已连续3次登录失败，程序将退出，请重新启动程序后再尝试登录！");
            System.exit(0);
        }
        System.out.println("请输入管理员账号：");
        String account = scanner.nextLine();
        System.out.println("请输入管理员密码：");
        String password = scanner.nextLine();
        // foreach循环 来遍历admins集合
        for (Admin admin : admins) {
            // 检查当前遍历到的管理员对象的账号和输入的账号是否相等，并且密码也相等
            if (admin.getAccount().equals(account) && admin.getPassword().equals(password)) {
                currentAdmin = admin;
                return true;
            }
        }
        loginFailCount++;
        System.out.println("账号或密码错误，请重新登录！您还有 " + (3 - loginFailCount) + " 次尝试机会。");
        return false;
    }

    // 管理员密码修改功能
    public void changeAdminPassword() {
        if (currentAdmin == null) {
            System.out.println("请先登录管理员账号！");
            return;
        }
        // 生成验证码，这里假设验证码长度为6位，你可按需调整
        String verificationCode = generateVerificationCode(6);
        // 在控制台显示验证码,实际操作可以为发送验证码
        System.out.println("本次生成的验证码为: " + verificationCode);
        System.out.println("请输入验证码：");
        String inputCode = scanner.nextLine();
        if (inputCode.equals(verificationCode)) {
            System.out.println("请输入新密码：");
            String newPassword = scanner.nextLine();
            currentAdmin.setPassword(newPassword);
            System.out.println("密码修改成功！");
        } else {
            System.out.println("验证码错误，密码修改失败！");
        }
        currentAdmin = null;//
    }

    // 员工入职功能（增加员工信息）
    public void addEmployee() {
        String empId;

        // 循环直到输入一个有效的工号
        while (true) {
            System.out.println("请输入员工工号：");
            empId = scanner.nextLine();

            // 检查工号是否为空
            if (empId.trim().isEmpty()) {
                System.out.println("工号不能为空，请重新输入！");
                continue; // 返回循环开头，继续要求输入
            }

            // 检查工号是否唯一
            boolean exists = false;
            for (Employee employee : employees) {
                if (employee.getEmpId().equals(empId)) {
                    exists = true;
                    break;
                }
            }

            if (exists) {
                System.out.println("该工号已存在，请重新输入唯一的工号！");
            } else {
                break; // 工号有效且唯一，退出循环
            }
        }

        String name;

        // 循环直到输入一有效的姓名
        while (true) {
            System.out.println("请输入员工姓名：");
            name = scanner.nextLine();

            // 检查姓名是否为空
            if (name.trim().isEmpty()) {
                System.out.println("姓名不能为空，请重新输入！");
            } else {
                break; // 姓名有效，退出循环
            }
        }

        // 输入员工性别
        System.out.println("请输入员工性别（1. 男性;2. 女性）：");
        int genderChoice = scanner.nextInt();
        String gender;
        switch (genderChoice) {
            case 1:
                gender = "男性";
                break;
            case 2:
                gender = "女性";
                break;
            default:
                System.out.println("输入无效，默认为男性");
                gender = "男性";
        }
        scanner.nextLine(); // 处理多余的换行符

        // 输入出生日期
        System.out.println("请输入员工出生日期（格式：yyyy-MM-dd）：");
        String birthdayStr = scanner.nextLine();
        Date birthday;
        try {
            birthday = new SimpleDateFormat("yyyy-MM-dd").parse(birthdayStr);
        } catch (ParseException e) {
            System.out.println("日期格式输入错误，请重新输入！");
            return;
        }

        // 输入入职日期
        System.out.println("请输入员工入职日期（格式：yyyy-MM-dd）：");
        String empdateStr = scanner.nextLine();
        Date empdate;
        try {
            empdate = new SimpleDateFormat("yyyy-MM-dd").parse(empdateStr);
        } catch (ParseException e) {
            System.out.println("日期格式输入错误，请重新输入！");
            return;
        }

        // 输入员工岗位性质
        System.out.println("请输入新的岗位性质：1. 前端，2. 后端，3. 产品经理，4. 人事，5. 前台");
        int jobNatureChoice = scanner.nextInt();
        String jobNature;
        switch (jobNatureChoice) {
            case 1:
                jobNature = "前端";
                break;
            case 2:
                jobNature = "后端";
                break;
            case 3:
                jobNature = "产品经理";
                break;
            case 4:
                jobNature = "人事";
                break;
            case 5:
                jobNature = "前台";
                break;
            default:
                System.out.println("输入无效，默认为前端");
                jobNature = "前端";
        }
        scanner.nextLine(); // 处理多余的换行符

        // 输入基本工资
        System.out.println("请输入员工基本工资：");
        double baseSalary = scanner.nextDouble();
        scanner.nextLine(); // 处理多余的换行符

        // 输入HR ID
        System.out.println("请输入添加该员工的HR的ID：");
        String hrId = scanner.nextLine();

        // 初始化员工对象
        Employee employee = new Employee(empId, birthday, name, empdate, jobNature, baseSalary, gender, hrId);
        employees.add(employee);
        System.out.println("新员工：" + name + " 添加成功！");
    }

    public void deleteEmployee() {
        System.out.println("请输入要离职的员工工号：");
        String empId = scanner.nextLine();
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getEmpId().equals(empId)) {
                employees.remove(i);
                System.out.println("员工已离职，信息删除成功！");
                return;
            }
        }
        System.out.println("未找到该员工信息！");
    }

    // 员工调岗功能
    public void changeEmployeeJob() {
        System.out.println("请输入要调岗的员工姓名：");
        String empName = scanner.nextLine();

        // 存储匹配的员工
        ArrayList<Employee> foundEmployees = new ArrayList<>();

        // 查找符合姓名的员工
        for (Employee employee : employees) {
            if (employee.getName().equals(empName)) {
                foundEmployees.add(employee);
            }
        }

        // 检查是否找到对应姓名的员工
        if (foundEmployees.isEmpty()) {
            System.out.println("未找到该员工信息！");
            return;
        }

        // 显示所有符合条件的员工信息
        System.out.println("找到以下员工的信息：");
        for (int i = 0; i < foundEmployees.size(); i++) {
            Employee employee = foundEmployees.get(i);
            System.out.println((i + 1) + ". 工号: " + employee.getEmpId() + ", 姓名: " + employee.getName() + ", 岗位性质: " + employee.getJobNature());
        }

        // 选择要调岗的员工
        System.out.println("请输入要调岗的员工序号：");
        int choice = scanner.nextInt();
        scanner.nextLine(); // 处理多余的换行符

        // 确保选择的序号有效
        if (choice < 1 || choice > foundEmployees.size()) {
            System.out.println("输入无效，操作取消。");
            return;
        }

        Employee selectedEmployee = foundEmployees.get(choice - 1);

        // 输入新的岗位性质
        System.out.println("请输入新的岗位性质：1. 前端，2. 后端，3. 产品经理，4. 人事，5. 前台");
        int jobNatureChoice = scanner.nextInt();
        String newJobNature;

        switch (jobNatureChoice) {
            case 1:
                newJobNature = "前端";
                break;
            case 2:
                newJobNature = "后端";
                break;
            case 3:
                newJobNature = "产品经理";
                break;
            case 4:
                newJobNature = "人事";
                break;
            case 5:
                newJobNature = "前台";
                break;
            default:
                System.out.println("输入无效，默认为前端");
                newJobNature = "前端";
        }

        // 更新员工的岗位性质
        selectedEmployee.setJobNature(newJobNature);
        System.out.println("员工调岗成功！新岗位性质为：" + newJobNature);
    }

    // 员工调薪功能
    public void changeEmployeeSalary() {
        System.out.println("请输入要调薪的员工工号：");
        String empId = scanner.nextLine();
        for (Employee employee : employees) {
            if (employee.getEmpId().equals(empId)) {
                System.out.println("请输入新的基本工资：");
                double newBaseSalary = scanner.nextDouble();
                employee.setBaseSalary(newBaseSalary);
                System.out.println("员工调薪成功！");
                return;
            }
        }
        System.out.println("未找到该员工信息！");
    }

    // 修改员工信息功能
    public void modifyEmployeeInfo() {
        System.out.println("请输入要修改信息的员工工号：");
        String empId = scanner.nextLine();
        for (Employee employee : employees) {
            if (employee.getEmpId().equals(empId)) {
                System.out.println("请选择要修改的信息：");
                System.out.println("1. 出生日期");
                System.out.println("2. 姓名");
                System.out.println("3. 入职日期");
                System.out.println("4. 岗位性质");
                System.out.println("5. 基本工资");
                System.out.println("6. 性别");
                System.out.println("7. 添加的HR的ID");
                int choice = scanner.nextInt();
                scanner.nextLine(); // 处理多余的换行符
                switch (choice) {
                    case 1:
                        System.out.println("请输入新的出生日期（格式：yyyy-MM-dd）：");
                        String birthdayStr = scanner.nextLine();
                        Date birthday;
                        try {
                            birthday = new SimpleDateFormat("yyyy-MM-dd").parse(birthdayStr);
                        } catch (ParseException e) {
                            System.out.println("日期格式输入错误，请重新输入！");
                            return;
                        }
                        employee.setBirthday(birthday);
                        break;
                    case 2:
                        System.out.println("请输入新的姓名：");
                        String newName = scanner.nextLine();
                        employee.setName(newName);
                        break;
                    case 3:
                        System.out.println("请输入新的入职日期（格式：yyyy-MM-dd）：");
                        String empdateStr = scanner.nextLine();
                        Date empdate;
                        try {
                            empdate = new SimpleDateFormat("yyyy-MM-dd").parse(empdateStr);
                        } catch (ParseException e) {
                            System.out.println("日期格式输入错误，请重新输入！");
                            return;
                        }
                        employee.setempdate(empdate);
                        break;
                    case 4:
                        System.out.println("请输入新的岗位性质（前端、后端、产品经理、人事、前台）：");
                        String newJobNature = scanner.nextLine();
                        employee.setJobNature(newJobNature);
                        break;
                    case 5:
                        System.out.println("请输入新的基本工资：");
                        double newBaseSalary = scanner.nextDouble();
                        employee.setBaseSalary(newBaseSalary);
                        break;
                    case 6:
                        System.out.println("请输入新的性别：");
                        String newGender = scanner.nextLine();
                        employee.setGender(newGender);
                        break;
                    case 7:
                        System.out.println("请输入新的添加的HR的ID：");
                        String newHrId = scanner.nextLine();
                        employee.setHrId(newHrId);
                        break;
                    default:
                        System.out.println("无效的选择，请重新输入！");
                        return;
                }
                System.out.println("员工信息修改成功！");
                return;
            }
        }
        System.out.println("未找到该员工信息！");
    }

    // 查看全部员工信息功能
    public void viewAllEmployees() {
        if (employees.isEmpty()) {
            System.out.println("暂无员工信息！");
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (Employee employee : employees) {
            System.out.println("工号：" + employee.getEmpId() +
                    "，出生日期：" + sdf.format(employee.getBirthday()) +
                    "，姓名：" + employee.getName() +
                    "，入职日期：" + sdf.format(employee.getempdate()) +
                    "，岗位性质：" + employee.getJobNature() +
                    "，基本工资：" + employee.getBaseSalary() +
                    "，性别：" + employee.getGender() +
                    "，添加的HR的ID：" + employee.getHrId());
        }
    }

    public static void main(String[] args) {
        PersonnelSystem system = new PersonnelSystem();
        while (true) {
            System.out.println("----------------------------");
            if (system.currentAdmin == null) {
                System.out.println("1. 管理员注册");
                System.out.println("2. 管理员登录");
                System.out.println("3. 管理员密码修改");
            } else {
                System.out.println("1. 添加新员工");
                System.out.println("2. 删除员工");
                System.out.println("3. 员工调岗");
                System.out.println("4. 员工调薪");
                System.out.println("5. 员工信息修改");
                System.out.println("6. 查看所有员工信息");
            }
            System.out.println("0. 退出系统");
            int choice = system.scanner.nextInt();
            system.scanner.nextLine(); // 处理多余的换行符
            switch (choice) {
                case 1:
                    if (system.currentAdmin == null) {
                        system.registerAdmin();
                    } else {
                        system.addEmployee();
                    }
                    break;
                case 2:
                    if (system.currentAdmin == null) {
                        system.loginAdmin();
                    } else {
                        system.deleteEmployee();
                    }
                    break;
                case 3:
                    if (system.currentAdmin == null) {
                        system.loginAdmin();
                        system.changeAdminPassword();
                    } else {
                        system.changeEmployeeJob();
                    }
                    break;
                case 4:
                    if (system.currentAdmin != null) {
                        system.changeEmployeeSalary();
                    } else {
                        System.out.println("无效的选择，请重新输入！");
                    }
                    break;
                case 5:
                    if (system.currentAdmin != null) {
                        system.modifyEmployeeInfo();
                    } else {
                        System.out.println("无效的选择，请重新输入！");
                    }
                    break;
                case 6:
                    if (system.currentAdmin != null) {
                        system.viewAllEmployees();
                    } else {
                        System.out.println("无效的选择，请重新输入！");
                    }
                    break;
                case 0:
                    System.out.println("系统已退出！bye");
                    System.exit(0);
                default:
                    System.out.println("无效的选择，请重新输入！");
            }
        }
    }
}