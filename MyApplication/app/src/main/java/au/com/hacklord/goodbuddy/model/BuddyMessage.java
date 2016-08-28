package au.com.hacklord.goodbuddy.model;

import java.util.List;

/**
 * Created by john on 28/08/2016.
 */
public class BuddyMessage {

    public enum BuddyMessageType{
        QUESTION,
        COMMENT
    }

    private String messageId;
    private BuddyMessageType messageType;
    private String referenceMessageId;
    private List<String> questions;
    private String comment;

    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public BuddyMessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(BuddyMessageType messageType) {
        this.messageType = messageType;
    }

    public String getReferenceMessageId() {
        return referenceMessageId;
    }

    public void setReferenceMessageId(String referenceMessageId) {
        this.referenceMessageId = referenceMessageId;
    }
}
