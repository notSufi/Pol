package com.example.sufiy_000.pol.classes;
import java.util.Date;

/**
 * Created by Thomas on 27/07/2015.
 */
public class Post {

    final public int THREAD_PARENT_INDEX = 0;

    private int m_id;
    private int m_parentId;
    private String m_posterId;
    private Date m_timestamp;
    private int m_up_votes;
    private int m_flaggedAmount;

    public Post(int m_parentId, String m_posterId, Date m_timestamp, int m_up_votes, int m_flagged_amount) {
        this.m_parentId = m_parentId;
        this.m_posterId = m_posterId;
        this.m_timestamp = m_timestamp;
        this.m_up_votes = m_up_votes;
        this.m_flaggedAmount = m_flagged_amount;
    }

    public int getId() {
        return m_id;
    }

    public void setId(int id) {
        m_id = id;
    }

    public int getUp_votes() {
        return m_up_votes;
    }

    public void setUp_votes(int up_votes) {
        m_up_votes = up_votes;
    }

    public boolean IsThread() {
        return m_parentId == THREAD_PARENT_INDEX;
    }

    public int getParentId() {
        return m_parentId;
    }

    public void setParentId(int m_parentId) {
        this.m_parentId = m_parentId;
    }

    public String getPosterId() {
        return m_posterId;
    }

    public void setPosterId(String m_posterId) {
        this.m_posterId = m_posterId;
    }

    public Date getTimestamp() {
        return m_timestamp;
    }

    public void setTimestamp(Date m_timestamp) {
        this.m_timestamp = m_timestamp;
    }

    public int getUpVotes() {
        return m_up_votes;
    }

    public void setUpVotes(int m_up_votes) {
        this.m_up_votes = m_up_votes;
    }

    public int getFlaggedAmount() {
        return m_flaggedAmount;
    }

    public void setFlaggedAmount(int m_flaggedAmount) {
        this.m_flaggedAmount = m_flaggedAmount;
    }
}

