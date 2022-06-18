package com.example.demo;

public class Question {
    private String question = "";
    private int numberOfAnswers;
    private String[] answer;
    private int[] point;

    public void setQuestion(String a){
        question = a;
    }

    public String getQuestion(){
        return question;
    }

    public int getPoint(int i){
        if(i < numberOfAnswers)
            return point[i];
        return -1;
    }

    public String getAnswer(int i){
        if(i < numberOfAnswers)
            return answer[i];
        return "ErRoR";
    }

    public void setPoint(int i, int a){
        if(i < numberOfAnswers)
            point[i] = a;
    }

    public void setAnswer(int i, String a){
        if(i < numberOfAnswers)
            answer[i] = a;
    }

    public void setNumberOfAnswers(int a){
        numberOfAnswers = a;
        answer = new String[numberOfAnswers];
        point = new int[numberOfAnswers];
    }

    public int getNumberOfAnswers(){
        return numberOfAnswers;
    }
}
