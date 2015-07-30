package com.example.sufiy_000.pol.classes;

/**
 * Created by sufiy_000 on 7/28/2015.
 */
import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{

    private String m_androidId;
    private Date m_signUpTime;
    private boolean m_admin;
    private String m_constituency;

    public User(String m_androidId, Date m_signupTime, boolean m_admin, String m_constituency){
        this.m_constituency = m_constituency;
        this.m_androidId = m_androidId;
        this.m_signUpTime = m_signupTime;
        this.m_admin = m_admin;
    }

    public String getConstituency() {
        return m_constituency;
    }

    public void setConstituency(String constituency) {
        m_constituency = constituency;
    }

    public String getAndroidId() {
        return m_androidId;
    }

    public void setAndroidId(String m_androidId) {
        this.m_androidId = m_androidId;
    }

    public Date getSignUpTime() {
        return m_signUpTime;
    }

    public void setSignUpTime(Date m_signUpTime) {
        this.m_signUpTime = m_signUpTime;
    }

    public boolean getAdmin() {
        return m_admin;
    }

    public void setAdmin(boolean m_admin) {
        this.m_admin = m_admin;
    }
}
