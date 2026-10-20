public class Paragraph implements Element {

    private String text;
    private AlignStrategy textAlignment;

    public Paragraph(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setAlignStrategy(AlignStrategy strategy) {
        this.textAlignment = strategy;
    }

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