package by.lavender.beans;

public class Country extends Entity {
    private long countryId;
    private String countryName;

    public Country() {
    }

    public Country(long countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }

    public long getCountryId() {
        return countryId;
    }

    public void setCountryId(long countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
