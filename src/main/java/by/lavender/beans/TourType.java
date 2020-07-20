package by.lavender.beans;

public class TourType extends Entity {
    private long typeId;
    private String typeName;

    public TourType() {
    }

    public TourType(long typeId) {
        this.typeId = typeId;
    }

    public TourType(long typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
