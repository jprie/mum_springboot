package app.graphics;

public class Text implements Printable {

    private String content;
    private boolean bold;
    private boolean italic;

    public Text(String content, boolean bold, boolean italic) {
        this.content = content;
        this.bold = bold;
        this.italic = italic;
    }

    @Override
    public void print() {
        System.out.println("Text: " + content + ", bold=" + bold + ", italic=" + italic);
    }

    @Override
    public void printBeautifully() {
        System.out.println(content.toUpperCase());
    }

    @Override
    public void erase() {
        System.out.println("Erase: Text");
    }
}
