package by.lavender.beans;

public class TourOperator extends Entity {
    private long operatorId;
    private String operatorName;
    private String telNumber;
    private String email;

    public TourOperator() {
    }

    public TourOperator(long operatorId) {
        this.operatorId = operatorId;
    }

    public TourOperator(long operatorId, String operatorName, String telNumber, String email) {
        this.operatorId = operatorId;
        this.operatorName = operatorName;
        this.telNumber = telNumber;
        this.email = email;
    }

    public long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(long operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
