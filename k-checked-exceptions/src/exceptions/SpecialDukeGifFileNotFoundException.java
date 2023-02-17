package exceptions;

public class SpecialDukeGifFileNotFoundException extends Exception {

    // Eine eigene Exception sollte immer ein Argument message haben

    public SpecialDukeGifFileNotFoundException(String message) {
        super(message);
    }
}
