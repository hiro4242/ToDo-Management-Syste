package com.dmm.task.data.entity;

import java.time.LocalDate;
import lombok.Data;

@Data
public class TaskForm {
	private String title;
	private LocalDate date;
	private String text;
}