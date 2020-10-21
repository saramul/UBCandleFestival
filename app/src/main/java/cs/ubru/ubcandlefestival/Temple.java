package cs.ubru.ubcandlefestival;

public class Temple {
    private int id;
    private String templeName;
    private String city;

    public Temple() {
    }

    public Temple(String templeName, String city) {
        this.templeName = templeName;
        this.city = city;
    }

    public Temple(int id, String templeName, String city) {
        this.id = id;
        this.templeName = templeName;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTempleName() {
        return templeName;
    }

    public void setTempleName(String templeName) {
        this.templeName = templeName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
