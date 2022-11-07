package ru.job4j.ood.lsp.homework.example2;

import java.time.*;

/**
 * Постусловие
 * TrainingDays - мапа посещаемости студентами занятий
 * Exam - экзамен и возможность получения автомата, если были посещены все занятия
 * Test - зачет и возможность получения автомата. Кто-то решил схитрить и получить автомат без проверки посещаемости.
 * SecondRule - проверка работы хитреца, которому это удалось.
 */
public class SecondRule {

    public static void main(String[] args) {
        TrainingDays trainingDays = new TrainingDays();
        trainingDays.add(LocalDate.of(2022, Month.OCTOBER, 1), 8);
        trainingDays.add(LocalDate.of(2022, Month.OCTOBER, 2), 6);
        trainingDays.add(LocalDate.of(2022, Month.OCTOBER, 3), 7);
        Exam exam = new Test(3 * 8, 1);
        System.out.println(exam.autoExam(trainingDays));
    }
}
