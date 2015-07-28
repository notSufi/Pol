/**
 * Created by sufiy_000 on 7/28/2015.
 */
import java.util.Date;

public class User {

    private String m_androidId;
    private Date m_signUpTime;
    private int m_admin;

    public User(String m_androidId, Date m_signupTime, int m_admin){

        this.m_androidId = m_androidId;
        this.m_signUpTime = m_signupTime;
        this.m_admin = m_admin;

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

    public int getAdmin() {
        return m_admin;
    }

    public void setAdmin(int m_admin) {
        this.m_admin = m_admin;
    }
}
