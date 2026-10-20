public class Main {

    public static void main(String[] args) {

        Book myBook = new Book("Playboy");

        Author author = new Author("Radu", "Pavel Gheorghe");
        myBook.addAuthor(author);

        Section section1 = new Section("Front Cover");
        section1.add(new Paragraph("Introduction"));
        section1.add(new ImageProxy("Pamela Anderson"));

        Section section2 = new Section("Summer Girls");
        section2.add(new Paragraph("Chapter introduction"));
        section2.add(new ImageProxy("Kim Kardashian"));
        section2.add(new Table("Statistics table"));

        myBook.addContent(section1);
        myBook.addContent(section2);

        myBook.accept(new RenderContentVisitor());

        System.out.println();

        BookStatistics statistics = new BookStatistics();
        myBook.accept(statistics);
        statistics.printStatistics();

        System.out.println();

        GenerateToC generateToC = new GenerateToC();
        myBook.accept(generateToC);

        System.out.println("Table of Contents:");
        System.out.println(generateToC.getToC().getAttributes());
    }
}