package com.example.rxjava;

import androidx.annotation.Nullable;

public class QuestionInfo implements Comparable<QuestionInfo>{
    private int questionId;

    private String answerId;

    private String subQuestionId;

    private String result;

    public QuestionInfo(int questionId, String answerId, String subQuestionId, String result) {
        this.questionId = questionId;
        this.answerId = answerId;
        this.subQuestionId = subQuestionId;
        this.result = result;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getSubQuestionId() {
        return subQuestionId;
    }

    public void setSubQuestionId(String subQuestionId) {
        this.subQuestionId = subQuestionId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj instanceof QuestionInfo){
            QuestionInfo q = (QuestionInfo) obj;
             return this.questionId == q.questionId && this.answerId.equals(q.answerId) && this.result.equals(q.result)
                     && this.subQuestionId.equals(q.subQuestionId);
        }
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "QuestionInfo{" +
                "questionId='" + questionId + '\'' +
                ", answerId='" + answerId + '\'' +
                ", subQuestionId='" + subQuestionId + '\'' +
                ", result='" + result + '\'' +
                '}';
    }


    @Override
    public int compareTo(QuestionInfo o) {
       /* return this.getQuestionId() - o.getQuestionId();*///正序
        return  o.getQuestionId()-this.getQuestionId();//逆序
    }
}
