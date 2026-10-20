public class TableOfContents implements Element {

    private String attributes;

    public TableOfContents() {
        this.attributes = "";
    }

    public TableOfContents(String attributes) {
        this.attributes = attributes;
    }

    public String getAttributes() {
        return attributes;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
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