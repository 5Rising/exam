package com.exam.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AutomaticGrading implements Serializable {
    private String[] topicAnswer;

    private Integer[] questionId;

}
