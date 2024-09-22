package com.dmm.task.data.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TaskForm {
	private String title;
	private LocalDateTime date;
	private String text;
}