package org.example;

public class Main {
    public static void main(String[] args) {

        // Packe SmsDecorator in FacebookDecorator
        Notifier myNotifier = new FacebookDecorator(new SmsDecorator(new SlackDecorator()));

        myNotifier.send();
    }
}

abstract class Notifier {
    abstract public void send();
}

class BaseDecorator extends Notifier {
    private Notifier wrappee;

    public BaseDecorator() {}

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

class SmsDecorator extends BaseDecorator {

    public SmsDecorator () {}
    public SmsDecorator(Notifier wrappee) {
        super(wrappee);
    }

    @Override
    public void send() {
        super.send();
        System.out.println("Send Sms");
    }
}

class FacebookDecorator extends BaseDecorator {

    public FacebookDecorator() {}
    public FacebookDecorator(Notifier wrappee) {
        super(wrappee);
    }

    @Override
    public void send() {
        super.send();
        System.out.println("Send Facebook msg");
    }
}

class SlackDecorator extends BaseDecorator {

    public SlackDecorator() {}
    public SlackDecorator(Notifier wrappee) {
        super(wrappee);
    }

    @Override
    public void send() {
        super.send();
        System.out.println("Send Slack message");
    }
}