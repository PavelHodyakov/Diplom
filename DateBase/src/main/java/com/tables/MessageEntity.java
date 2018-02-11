package com.tables;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "message", schema = "public", catalog = "Messenger")
public class MessageEntity {
    private long messageId;
    private String content;
    private Date date;
    private Boolean delivConfRequest;
    private Boolean confReading;
    private long senderSystemId;

    public MessageEntity(String content, Date date, Boolean delivConfRequest, Boolean confReading, long senderSystemId) {
        this.content = content;
        this.date = date;
        this.delivConfRequest = delivConfRequest;
        this.confReading = confReading;
        this.senderSystemId = senderSystemId;
    }

    public MessageEntity() {
    }

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_message_sequence")
    @Column(name = "message_id")
    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "deliv_conf_request")
    public Boolean getDelivConfRequest() {
        return delivConfRequest;
    }

    public void setDelivConfRequest(Boolean delivConfRequest) {
        this.delivConfRequest = delivConfRequest;
    }

    @Basic
    @Column(name = "conf_reading")
    public Boolean getConfReading() {
        return confReading;
    }

    public void setConfReading(Boolean confReading) {
        this.confReading = confReading;
    }

    @Basic
    @Column(name = "sender_system_id")
    public long getSenderSystemId() {
        return senderSystemId;
    }

    public void setSenderSystemId(long senderSystemId) {
        this.senderSystemId = senderSystemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageEntity that = (MessageEntity) o;

        if (messageId != that.messageId) return false;
        if (senderSystemId != that.senderSystemId) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (delivConfRequest != null ? !delivConfRequest.equals(that.delivConfRequest) : that.delivConfRequest != null)
            return false;
        if (confReading != null ? !confReading.equals(that.confReading) : that.confReading != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (messageId ^ (messageId >>> 32));
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (delivConfRequest != null ? delivConfRequest.hashCode() : 0);
        result = 31 * result + (confReading != null ? confReading.hashCode() : 0);
        result = 31 * result + (int) (senderSystemId ^ (senderSystemId >>> 32));
        return result;
    }
}
