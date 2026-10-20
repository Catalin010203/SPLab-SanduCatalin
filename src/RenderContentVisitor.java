public class RenderContentVisitor implements Visitor {

    @Override
    public void visit(Book book) {
        System.out.println("Book: " + book.getTitle());

        System.out.println();
        System.out.println("Authors:");

        for (Author author : book.getAuthors()) {
            author.print();
        }

        for (Element element : book.getChildren()) {
            element.accept(this);
        }
    }

    @Override
    public void visit(Section section) {
        System.out.println(section.getTitle());

        for (Element element : section.getChildren()) {
            element.accept(this);
        }
    }

    @Override
    public void visit(TableOfContents tableOfContents) {
        System.out.println("Table of Contents");
    }

    @Override
    public void visit(Paragraph paragraph) {
        AlignStrategy strategy = paragraph.getAlignStrategy();

        if (strategy == null) {
            System.out.println("Paragraph: " + paragraph.getText());
        } else {
            strategy.render(
                    paragraph,
                    new Context(paragraph.getText())
            );
        }
    }

    @Override
    public void visit(ImageProxy imageProxy) {
        System.out.println("Image with name:" + imageProxy.url());
    }

    @Override
    public void visit(Table table) {
        System.out.println("Table: " + table.getAttributes());
    }
}