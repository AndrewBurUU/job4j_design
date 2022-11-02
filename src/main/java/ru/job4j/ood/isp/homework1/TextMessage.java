package ru.job4j.ood.isp.homework1;

public interface TextMessage extends Message {

    void sendText(String body);

    void receiveText(String body);
}
