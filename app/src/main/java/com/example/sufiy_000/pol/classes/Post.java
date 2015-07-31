package com.example.sufiy_000.pol.classes;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Thomas on 27/07/2015.
 */
public class Post implements Serializable{

    final public int THREAD_PARENT_INDEX = 0;

    private String m_title;

    private String m_content;
    private int m_id;
    private int m_parentId;
    private String m_posterId;
    //private Date m_timestamp;
    private String m_up_votes;
    //private int m_flaggedAmount;

    public Post(int m_parentId, String m_posterId/*, Date m_timestamp*/,
                String m_up_votes, /*int m_flagged_amount,*/ String m_title, String m_content) {
        this.m_title = m_title;
        this.m_content = m_content;
        this.m_parentId = m_parentId;
        this.m_posterId = m_posterId;
        //this.m_timestamp = m_timestamp;
        this.m_up_votes = m_up_votes;
        //this.m_flaggedAmount = m_flagged_amount;
    }

    public String getContent() {
        return m_content;
    }

    public void setContent(String content) {
        m_content = content;
    }

    public String getTitle() {
        return m_title;
    }

    public void setTitle(String title) {
        m_title = title;
    }

    public int getId() {
        return m_id;
    }

    public void setId(int id) {
        m_id = id;
    }

    public String getUp_votes() {
        return m_up_votes;
    }

    public void setUp_votes(String up_votes) {
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
/*
    public Date getTimestamp() {
        return m_timestamp;
    }

    public void setTimestamp(Date m_timestamp) {
        this.m_timestamp = m_timestamp;
    }*/

    public String getUpVotes() {
        return m_up_votes;
    }

    public void setUpVotes(String m_up_votes) {
        this.m_up_votes = m_up_votes;
    }
/*
    public int getFlaggedAmount() {
        return m_flaggedAmount;
    }

    public void setFlaggedAmount(int m_flaggedAmount) {
        this.m_flaggedAmount = m_flaggedAmount;
    }*/
}

