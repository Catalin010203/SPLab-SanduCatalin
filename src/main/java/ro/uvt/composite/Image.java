package ro.uvt.composite;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

@Entity
public class Image extends BaseElement implements Element, Picture {

    private String url;

    @Embedded
    private Dimension dimension;

    @Embedded
    private ImageContent imageContent;

    public Image() {
    }

    public Image(String url) {
        this.url = url;
        this.dimension = new Dimension(1920, 1080);
        this.imageContent = new ImageContent("Image content: " + url);
    }

    @Override
    public void accept(Visitor visitor) {
        throw new UnsupportedOperationException(
                "Use ImageProxy when visiting images"
        );
    }

    @Override
    public String url() {
        return url;
    }

    @Override
    public Dimension dim() {
        return dimension;
    }

    @Override
    public ImageContent content() {
        return imageContent;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public void add(Element element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(Element element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Element get(int index) {
        throw new UnsupportedOperationException();
    }
}