package by.lavender.beans;

public class Resort extends Entity {
    private long resortId;
    private String resortName;
    private Country country;

    public Resort() {
    }

    public Resort(long resortId) {
        this.resortId = resortId;
    }

    public Resort(long resortId, String resortName, Country country) {
        this.resortId = resortId;
        this.resortName = resortName;
        this.country = country;
    }

    public long getResortId() {
        return resortId;
    }

    public void setResortId(long resortId) {
        this.resortId = resortId;
    }

    public String getResortName() {
        return resortName;
    }

    public void setResortName(String resortName) {
        this.resortName = resortName;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
