import java.util.ArrayList;
import java.util.List;

public class GenerateToC implements Visitor {

    private List<String> entries = new ArrayList<>();

    @Override
    public void visit(Book book) {
        entries.clear();

        for (Element element : book.getChildren()) {
            element.accept(this);
        }
    }

    @Override
    public void visit(Section section) {
        entries.add(section.getTitle());

        for (Element element : section.getChildren()) {
            if (element instanceof Section) {
                element.accept(this);
            }
        }
    }

    @Override
    public void visit(TableOfContents tableOfContents) {
    }

    @Override
    public void visit(Paragraph paragraph) {
    }

    @Override
    public void visit(ImageProxy imageProxy) {
    }

    @Override
    public void visit(Table table) {
    }

    public TableOfContents getToC() {
        StringBuilder result = new StringBuilder();

        for (String entry : entries) {
            result.append(entry).append(System.lineSeparator());
        }

        return new TableOfContents(result.toString());
    }
}