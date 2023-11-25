package quanlythuvien.dto;

public class UserDto {
    private int accountId;
    private String username;
    private String password;
    private String roll;
    private String name;
    private String job;
    private String phone;
    private String email;
    private String other;

    public UserDto() {
    }

    public UserDto(int accountId, String username, String password, String roll, String name, String job, String phone, String email, String other) {
        this.accountId = accountId;
        this.username = username;
        this.password = password;
        this.roll = roll;
        this.name = name;
        this.job = job;
        this.phone = phone;
        this.email = email;
        this.other = other;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
