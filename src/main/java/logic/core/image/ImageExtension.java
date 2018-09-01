package logic.core.image;

public enum ImageExtension {
    PNG("png"),
    JPG("jpg"),
    BMP("bmp");

    private String value;

    ImageExtension(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
