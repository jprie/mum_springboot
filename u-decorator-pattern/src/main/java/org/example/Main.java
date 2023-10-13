package org.example;

public class Main {
    public static void main(String[] args) {

        // Packe SmsDecorator in FacebookDecorator
        Notifier myNotifier = new FacebookBase(new SmsBase(new SlackNotifierDecorator(null)));

        myNotifier.send();
    }
}

abstract class Notifier {
    abstract public void send();
}

class BaseDecorator extends Notifier {
    private final Notifier wrappee;

    public BaseDecorator(Notifier wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public void send() {
        if (wrappee != null) {
            wrappee.send();
        }
    }
}

class SmsBase extends BaseDecorator {

    public SmsBase(Notifier wrappee) {
        super(wrappee);
    }

    @Override
    public void send() {
        super.send();
        System.out.println("Send Sms");
    }
}

class FacebookBase extends BaseDecorator {

    public FacebookBase(Notifier wrappee) {
        super(wrappee);
    }

    @Override
    public void send() {
        super.send();
        System.out.println("Send Facebook msg");
    }
}

class SlackNotifierDecorator extends BaseDecorator {

    public SlackNotifierDecorator(Notifier wrappee) {
        super(wrappee);
    }

    @Override
    public void send() {
        super.send();
        System.out.println("Send Slack message");
    }
}