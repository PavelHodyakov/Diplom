package com.tables;

import javax.persistence.*;

@Entity
@Table(name = "message_base", schema = "public", catalog = "Messenger")
public class MessageBaseEntity {
    private long messageBaseId;
    private long recipientSystemId;
    private long messageMessageId;
    private Boolean delivered;
    private Boolean read;

    public MessageBaseEntity(long recipientSystemId, long messageMessageId, Boolean delivered, Boolean read) {
        this.recipientSystemId = recipientSystemId;
        this.messageMessageId = messageMessageId;
        this.delivered = delivered;
        this.read = read;
    }

    public MessageBaseEntity() {
    }

    @Id
    @SequenceGenerator(name="pk_sequence",sequenceName="message_base_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence")
    @Column(name = "message_base_id")
    public long getMessageBaseId() {
        return messageBaseId;
    }

    public void setMessageBaseId(long messageBaseId) {
        this.messageBaseId = messageBaseId;
    }

    @Basic
    @Column(name = "recipient_system_id")
    public long getRecipientSystemId() {
        return recipientSystemId;
    }

    public void setRecipientSystemId(long recipientSystemId) {
        this.recipientSystemId = recipientSystemId;
    }

    @Basic
    @Column(name = "message_message_id")
    public long getMessageMessageId() {
        return messageMessageId;
    }

    public void setMessageMessageId(long messageMessageId) {
        this.messageMessageId = messageMessageId;
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

        MessageBaseEntity that = (MessageBaseEntity) o;

        if (messageBaseId != that.messageBaseId) return false;
        if (recipientSystemId != that.recipientSystemId) return false;
        if (messageMessageId != that.messageMessageId) return false;
        if (delivered != null ? !delivered.equals(that.delivered) : that.delivered != null) return false;
        if (read != null ? !read.equals(that.read) : that.read != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (messageBaseId ^ (messageBaseId >>> 32));
        result = 31 * result + (int) (recipientSystemId ^ (recipientSystemId >>> 32));
        result = 31 * result + (int) (messageMessageId ^ (messageMessageId >>> 32));
        result = 31 * result + (delivered != null ? delivered.hashCode() : 0);
        result = 31 * result + (read != null ? read.hashCode() : 0);
        return result;
    }
}
