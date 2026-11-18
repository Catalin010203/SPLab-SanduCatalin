package ro.uvt.composite;

import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

@Entity
public class Paragraph extends BaseElement implements Element {

    private String text;

    @Transient
    private AlignStrategy textAlignment;

    public Paragraph() {
    }

    public Paragraph(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setAlignStrategy(AlignStrategy strategy) {
        this.textAlignment = strategy;
    }

    @Transient
    public AlignStrategy getAlignStrategy() {
        return textAlignment;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void add(Element element) {
        throw new UnsupportedOperationException(
                "Paragraph cannot contain elements"
        );
    }

    @Override
    public void remove(Element element) {
        throw new UnsupportedOperationException(
                "Paragraph cannot contain elements"
        );
    }

    @Override
    public Element get(int index) {
        throw new UnsupportedOperationException(
                "Paragraph has no children"
        );
    }
}