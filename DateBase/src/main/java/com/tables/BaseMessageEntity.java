package com.tables;

import javax.persistence.*;

@Entity
@Table(name = "base_message", schema = "public", catalog = "Messenger")
public class BaseMessageEntity {
    private int idBaseMessage;
    private long messagesIdMessage;
    private String recipient;
    private Boolean delivered;
    private Boolean read;

    public BaseMessageEntity(long messagesIdMessage, String recipient, Boolean delivered, Boolean read) {
        this.messagesIdMessage = messagesIdMessage;
        this.recipient = recipient;
        this.delivered = delivered;
        this.read = read;
    }

    @Id
    @SequenceGenerator(name="pk_sequence",sequenceName="id_base_message_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence")
    @Column(name = "id_base_message")
    public int getIdBaseMessage() {
        return idBaseMessage;
    }

    public void setIdBaseMessage(int idBaseMessage) {
        this.idBaseMessage = idBaseMessage;
    }

    @Basic
    @Column(name = "messages_id_message")
    public long getMessagesIdMessage() {
        return messagesIdMessage;
    }

    public void setMessagesIdMessage(long messagesIdMessage) {
        this.messagesIdMessage = messagesIdMessage;
    }

    @Basic
    @Column(name = "recipient")
    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    @Basic
    @Column(name = "delivered")
    public Boolean getDelivered() {
        return delivered;
    }

    public void setDelivered(Boolean delivered) {
        this.delivered = delivered;
    }

    @Basic
    @Column(name = "read")
    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseMessageEntity that = (BaseMessageEntity) o;

        if (idBaseMessage != that.idBaseMessage) return false;
        if (messagesIdMessage != that.messagesIdMessage) return false;
        if (recipient != null ? !recipient.equals(that.recipient) : that.recipient != null) return false;
        if (delivered != null ? !delivered.equals(that.delivered) : that.delivered != null) return false;
        if (read != null ? !read.equals(that.read) : that.read != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idBaseMessage;
        result = 31 * result + (int) (messagesIdMessage ^ (messagesIdMessage >>> 32));
        result = 31 * result + (recipient != null ? recipient.hashCode() : 0);
        result = 31 * result + (delivered != null ? delivered.hashCode() : 0);
        result = 31 * result + (read != null ? read.hashCode() : 0);
        return result;
    }
}
