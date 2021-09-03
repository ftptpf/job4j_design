package ru.job4j.ood.ocp.wrong;

public class Students {
    private static final int SCHOOL_SUBJECT_QUANTITY = 10;

    public void countAverageScoreStudent1() {
        String student1 = "Sergey";
        double sum = 450.40;
        System.out.println(student1 + " Average score is " + (sum / SCHOOL_SUBJECT_QUANTITY));
    }

    public void countAverageScoreStudent2() {
        String student2 = "Igor";
        double sum = 375.20;
        System.out.println(student2 + " Average score is " + (sum / SCHOOL_SUBJECT_QUANTITY));
    }

    public void countAverageScoreStudent3() {
        String student3 = "Viktor";
        double sum = 480.10;
        System.out.println(student3 + " Average score is " + (sum / SCHOOL_SUBJECT_QUANTITY));
    }
}
