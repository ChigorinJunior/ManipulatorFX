package sample;

public class Parameter {
    private String mKey;
    private Double mValue;

    public Parameter(String mKey, Double mValue) {
        this.mKey = mKey;
        this.mValue = mValue;
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        this.mKey = key;
    }

    public Double getValue() {
        return mValue;
    }

    public void setValue(Double value) {
        this.mValue = value;
    }
}
