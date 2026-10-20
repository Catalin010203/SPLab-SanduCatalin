public class BookStatistics implements Visitor {

    private int images;
    private int tables;
    private int paragraphs;

    @Override
    public void visit(Book book) {
        for (Element element : book.getChildren()) {
            element.accept(this);
        }
    }

    @Override
    public void visit(Section section) {
        for (Element element : section.getChildren()) {
            element.accept(this);
        }
    }

    @Override
    public void visit(TableOfContents tableOfContents) {
    }

    @Override
    public void visit(Paragraph paragraph) {
        paragraphs++;
    }

    @Override
    public void visit(ImageProxy imageProxy) {
        images++;
    }

    @Override
    public void visit(Table table) {
        tables++;
    }

    public void printStatistics() {
        System.out.println("Book Statistics:");
        System.out.println("*** Number of images: " + images);
        System.out.println("*** Number of tables: " + tables);
        System.out.println("*** Number of paragraphs: " + paragraphs);
    }
}