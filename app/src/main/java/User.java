/**
 * Created by sufiy_000 on 7/28/2015.
 */
import java.util.Date;

public class User {

    private String m_androidId;
    private Date m_signupTime;
    private int m_admin;

    public String getM_androidId() {
        return m_androidId;
    }

    public void setM_androidId(String m_androidId) {
        this.m_androidId = m_androidId;
    }

    public Date getM_signupTime() {
        return m_signupTime;
    }

    public void setM_signupTime(Date m_signupTime) {
        this.m_signupTime = m_signupTime;
    }

    public int getM_admin() {
        return m_admin;
    }

    public void setM_admin(int m_admin) {
        this.m_admin = m_admin;
    }

    public User(String m_androidId, Date m_signupTime, int m_admin){

        this.m_androidId = m_androidId;
        this.m_signupTime = m_signupTime;
        this.m_admin = m_admin;

    }

}
