package ru.job4j.ood.lsp.homework.example2;

public class Test extends Exam {

    public Test(int normLessons, int markPerLesson) {
        super(normLessons, markPerLesson);
    }

    @Override
    public int autoExam(TrainingDays trainingDays) {
        int factLessons = 0;
        for (Integer lessonsPerDay : trainingDays) {
            factLessons += lessonsPerDay;
        }
        /**Постусловие: пропущено условие проверки получения автомата*/
        return factLessons * markPerLesson;
    }
}
