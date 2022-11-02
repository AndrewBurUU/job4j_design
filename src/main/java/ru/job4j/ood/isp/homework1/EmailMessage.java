package ru.job4j.ood.isp.homework1;

public interface EmailMessage extends Message {

    void sendMessage(String subject, String message);

    void receiveMessage(String subject, String message);
}
