public class Admin {
    private String account;
    private String password;
    private int id;

    public Admin(String account, String password, int id) {
        this.account = account;
        this.password = password;
        this.id = id;
    }

    public Admin() {
    }

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
