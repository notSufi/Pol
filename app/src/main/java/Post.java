import java.util.Date;

/**
 * Created by Thomas on 27/07/2015.
 */
public class Post {
    private int m_parentId;
    private String m_posterId;
    private Date m_timestamp;
    private int m_up_votes;
    private int m_flagged_amount;

    public Post(int m_parentId, String m_posterId, Date m_timestamp, int m_up_votes, int m_flagged_amount) {
        this.m_parentId = m_parentId;
        this.m_posterId = m_posterId;
        this.m_timestamp = m_timestamp;
        this.m_up_votes = m_up_votes;
        this.m_flagged_amount = m_flagged_amount;
    }

    public boolean IsThread() {
        return m_parentId == 0;
    }

    public int getM_parentId() {
        return m_parentId;
    }

    public void setM_parentId(int m_parentId) {
        this.m_parentId = m_parentId;
    }

    public String getM_posterId() {
        return m_posterId;
    }

    public void setM_posterId(String m_posterId) {
        this.m_posterId = m_posterId;
    }

    public Date getM_timestamp() {
        return m_timestamp;
    }

    public void setM_timestamp(Date m_timestamp) {
        this.m_timestamp = m_timestamp;
    }

    public int getM_up_votes() {
        return m_up_votes;
    }

    public void setM_up_votes(int m_up_votes) {
        this.m_up_votes = m_up_votes;
    }

    public int getM_flagged_amount() {
        return m_flagged_amount;
    }

    public void setM_flagged_amount(int m_flagged_amount) {
        this.m_flagged_amount = m_flagged_amount;
    }
}
