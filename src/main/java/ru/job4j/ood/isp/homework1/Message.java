package ru.job4j.ood.isp.homework1;

/**
 * Было:
 * public interface Message {
 *     void send(String body, String subject, String toAddress, String fromAddress);
 *     void receive(String body, String subject, String toAddress, String fromAddress);
 *     void sendVoice(String fileName);
 *     void sendFotos(String fileName);
 * }
 */
public interface Message {

    void send(String toAddress, String fromAddress);

    void receive(String toAddress, String fromAddress);

}
