package com.company;

public class Main {

    public static void main(String[] args) {

        Message message = new Message();
        (new Thread(new Writer(message))).start();
        (new Thread(new Reader(message))).start();
    }
}

class Message {
    private String message;
    private boolean empty = true;

    public synchronized String read() {
        while(empty) {

        }
        empty = true;
        return message;
    }

    public synchronized void write(String message) {
        while(!empty) {
        }
        empty = false;
        this.message = message;
    }
}

class Writer implements Runnable {
    private Message message;

    public Writer(Message message) {
        this.message = message;
    }

    public void run() {
        String messages[] = {
                "Humpty Dumpty sat on a wall",
                "Humpty Dumpty had a great fall",
                "All the king's horses and all the king's men",
                "Couldn't put Humpty together again"
        };

        for(int i=0; i<messages.length; i++) {
            message.write(messages[i]);
        }
        message.write("Finished");
    }
}

class Reader implements Runnable {
    private Message message;

    public Reader(Message message) {
        this.message = message;
    }

    public void run() {

        for(String latestMessage = message.read();
            !latestMessage.equals("Finished");
            latestMessage = message.read()) {

            System.out.println(latestMessage);
        }
    }
}