package com.tables;

import javax.persistence.*;

@Entity
@Table(name = "system", schema = "public", catalog = "Messenger")
public class SystemEntity {
    private int systemId;
    private String login;
    private String password;
    private String firstName;
    private String secondName;
    private int systemDepartmentId;
    private String addressSystem;
    private String mailAddress;
    private String telegramAddress;

    public SystemEntity(String login, String password, String firstName,
                        String secondName, int systemDepartmentId,
                        String addressSystem, String mailAddress,
                        String telegramAddress) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.systemDepartmentId = systemDepartmentId;
        this.addressSystem = addressSystem;
        this.mailAddress = mailAddress;
        this.telegramAddress = telegramAddress;
    }

    public SystemEntity() {
    }

    @Id
//    @SequenceGenerator(name="pk_sequence",sequenceName="system_id_seq", allocationSize=1)
//    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence")
    @SequenceGenerator(name="pk_sequence",sequenceName="system_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence")
    @Column(name = "system_id")
    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }

    @Basic
    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "second_name")
    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @Basic
    @Column(name = "system_department_id")
    public int getSystemDepartmentId() {
        return systemDepartmentId;
    }

    public void setSystemDepartmentId(int systemDepartmentId) {
        this.systemDepartmentId = systemDepartmentId;
    }

    @Basic
    @Column(name = "address_system")
    public String getAddressSystem() {
        return addressSystem;
    }

    public void setAddressSystem(String addressSystem) {
        this.addressSystem = addressSystem;
    }

    @Basic
    @Column(name = "mail_address")
    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    @Basic
    @Column(name = "telegram_address")
    public String getTelegramAddress() {
        return telegramAddress;
    }

    public void setTelegramAddress(String telegramAddress) {
        this.telegramAddress = telegramAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SystemEntity that = (SystemEntity) o;

        if (systemId != that.systemId) return false;
        if (systemDepartmentId != that.systemDepartmentId) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (secondName != null ? !secondName.equals(that.secondName) : that.secondName != null) return false;
        if (addressSystem != null ? !addressSystem.equals(that.addressSystem) : that.addressSystem != null)
            return false;
        if (mailAddress != null ? !mailAddress.equals(that.mailAddress) : that.mailAddress != null) return false;
        if (telegramAddress != null ? !telegramAddress.equals(that.telegramAddress) : that.telegramAddress != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = systemId;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + systemDepartmentId;
        result = 31 * result + (addressSystem != null ? addressSystem.hashCode() : 0);
        result = 31 * result + (mailAddress != null ? mailAddress.hashCode() : 0);
        result = 31 * result + (telegramAddress != null ? telegramAddress.hashCode() : 0);
        return result;
    }
}
