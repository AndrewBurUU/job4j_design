package ru.job4j.ood.isp.homework1;

public interface VoiceMessage extends Message {

    void sendVoice(String fileName);

    void receiveVoice(String fileName);
}
