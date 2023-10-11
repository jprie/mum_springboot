package org.example;

public class Main {
    public static void main(String[] args) {

        Notifier myNotifier = new FacebookNotifier(new SmsNotifier(null));

        myNotifier.send();
    }
}

abstract class Notifier {
    abstract public void send();
}

class NotifierDecorator extends Notifier {
    private final Notifier wrappee;

    public NotifierDecorator(Notifier wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public void send() {
        if (wrappee != null) {
            wrappee.send();
        }
    }
}

class SmsNotifier extends NotifierDecorator {

    public SmsNotifier(Notifier wrappee) {
        super(wrappee);
    }

    @Override
    public void send() {
        super.send();
        System.out.println("Send Sms");
    }
}

class FacebookNotifier extends NotifierDecorator {

    public FacebookNotifier(Notifier wrappee) {
        super(wrappee);
    }

    @Override
    public void send() {
        super.send();
        System.out.println("Send Facebook msg");
    }
}