package cs.ubru.ubcandlefestival;

public class Image {
    private String templeName;
    private String description;
    private String imgName;

    public Image() {
    }

    public Image(String templeName, String description, String imgName) {
        this.templeName = templeName;
        this.description = description;
        this.imgName = imgName;
    }

    public String getTempleName() {
        return templeName;
    }

    public void setTempleName(String templeName) {
        this.templeName = templeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }
}
