package ru.job4j.ood.isp.homework1;

public interface FotoMessage extends Message {

    void sendFoto(String fileName);

    void receiveFoto(String fileName);
}
