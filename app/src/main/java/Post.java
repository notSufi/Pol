import java.util.Date;

/**
 * Created by Thomas on 27/07/2015.
 */
public class Post {
    public int m_parentId;
    public String m_posterId;
    public Date m_timestamp;
    public int m_up_votes;
    public int m_flagged_amount;

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
}
