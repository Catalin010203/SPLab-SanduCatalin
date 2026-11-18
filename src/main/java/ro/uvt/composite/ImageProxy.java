package ro.uvt.composite;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

@Entity
public class ImageProxy extends BaseElement implements Element, Picture {

    private String url;

    @Embedded
    private Dimension dimension;

    @Transient
    private Image realImage;

    public ImageProxy() {
    }

    public ImageProxy(String url) {
        this.url = url;
        this.dimension = new Dimension(1920, 1080);
    }

    private Image loadImage() {
        if (realImage == null) {
            realImage = new Image(url);
        }

        return realImage;
    }

    public Image getRealImage() {
        return loadImage();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
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
        return loadImage().content();
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