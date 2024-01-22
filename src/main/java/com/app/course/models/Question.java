package com.app.course.models;


import jakarta.persistence.*;

@Entity
@Table(name = "question")
public class Question {
    /*  PRIMARY KEY  */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /*  SET FOREIGN KEY  */
    @ManyToOne
    @JoinColumn(name = "test_id",nullable = false)
    private Test test;

    /*  SET COLUM  */
    private String question;
    private String answer;
    private String choose1;
    private String choose2;
    private String choose3;
    public Question(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getChoose1() {
        return choose1;
    }

    public void setChoose1(String choose1) {
        this.choose1 = choose1;
    }

    public String getChoose2() {
        return choose2;
    }

    public void setChoose2(String choose2) {
        this.choose2 = choose2;
    }

    public String getChoose3() {
        return choose3;
    }

    public void setChoose3(String choose3) {
        this.choose3 = choose3;
    }
}
