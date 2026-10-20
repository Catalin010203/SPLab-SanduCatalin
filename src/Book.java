import java.util.ArrayList;
import java.util.List;

public class Book extends Section {

    private List<Author> authors = new ArrayList<>();

    public Book(String title) {
        super(title);
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void addContent(Element element) {
        add(element);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}