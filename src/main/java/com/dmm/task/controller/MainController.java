package com.dmm.task.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.dmm.task.data.entity.TaskForm;
import com.dmm.task.data.entity.Tasks;
import com.dmm.task.service.AccountUserDetails;

@Controller
public class MainController {
	
	@GetMapping("/login")
	public String login() {
			return "login";
		}
	
	@GetMapping("/loginForm")
	public String loginForm() {
			return "login";
		}

    // カレンダー表示
    @GetMapping("/main")
//	@PreAuthorize("hasRole('USER')")
	public String main(Model model) {
		//	週と日を格納する二次元配列を用意する
		List<List<LocalDate>> month = new ArrayList<>();
		// 1週間分のLocalDateを格納するリストを用意する
        List<LocalDate> week = new ArrayList<>();
        // その月の1日を取得する
        LocalDate today = LocalDate.now();  
        LocalDate firstDayOfMonth = LocalDate.of(today.getYear(), today.getMonthValue(), 1);
		//曜日を表すDayOfWeekを取得し、上で取得したLocalDateに曜日の値（DayOfWeek#getValue)をマイナスして前月分のLocalDateを求める
        LocalDate calendarDay = firstDayOfMonth.minusDays(firstDayOfMonth.getDayOfWeek().getValue() % 7) ;
        // 月末を求めるにはLocalDate#lengthOfMonth()を使う
        int daysInMonth = calendarDay.lengthOfMonth();
        
        // 1日ずつ増やしてLocalDateを求めていき、2．で作成したListへ格納していき、1週間分詰めたら1．のリストへ格納する
        while(true) {
        	week.add(calendarDay);
        	
        	if (calendarDay.getDayOfWeek() == DayOfWeek.SATURDAY) {
        		month.add(new ArrayList<>(week));
        		week.clear();
        	}
        	
        	calendarDay = calendarDay.plusDays(1);
        	
            if (calendarDay.isAfter(firstDayOfMonth.plusDays(daysInMonth - 1)) && calendarDay.getDayOfWeek() == DayOfWeek.SATURDAY) {
            	week.add(calendarDay);
            	month.add(new ArrayList<>(week)); // 最終週も追加
                break;
            }
        
        }
        
        // 日付とタスクを紐付けるコレクション（Lesson3 - Chapter22を参考に、エンティティ Tasksを用意ください）
        MultiValueMap<LocalDate, Tasks> tasks = new LinkedMultiValueMap<LocalDate, Tasks>();

        // コレクションのデータをHTMLに連携
        model.addAttribute("tasks", tasks);

        // カレンダーのデータをHTMLに連携
        model.addAttribute("matrix", month);

        // HTMLを表示
        return "main";

    }
    
 // タスク登録画面の表示用
    @GetMapping("/main/create/{date}")
    public String create() {
		return "create";
      
    }
    


    // タスク登録用
    @PostMapping("/main/create")
    public String createPost(Model model, TaskForm form, @AuthenticationPrincipal AccountUserDetails user) {
        Tasks task = new Tasks();
        task.setName(user.getName());
		task.setTitle(TaskForm.getTitle());
		task.setText(TaskForm.getText());

		return null;
    }
    
    
    
   
}