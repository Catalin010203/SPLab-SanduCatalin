public interface Element {

    void add(Element element);
    void remove(Element element);
    void accept(Visitor visitor);
    Element get(int index);


}
