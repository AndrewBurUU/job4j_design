package ru.job4j.ood.lsp.homework.example2;

public class Exam {

    protected int normLessons;
    protected int markPerLesson;

    public Exam(int normLessons, int markPerLesson) {
        this.normLessons = normLessons;
        this.markPerLesson = markPerLesson;
    }

    public int autoExam(TrainingDays trainingDays) {
        int factLessons = 0;
        for (Integer lessonsPerDay : trainingDays) {
            factLessons += lessonsPerDay;
        }
        if (factLessons < normLessons) {
            throw new IllegalArgumentException("Student didn't study enough!");
        }
        return factLessons * markPerLesson;
    }
}
