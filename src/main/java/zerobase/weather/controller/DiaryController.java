package zerobase.weather.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import zerobase.weather.domain.Diary;
import zerobase.weather.service.DiaryService;

import java.time.LocalDate;
import java.util.List;

@RestController
public class DiaryController {

    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @ApiOperation(value = "저장할 날짜에 일기 내용과 날씨를 DB에 저장")//한 줄 설명 입력
    @PostMapping("/create/diary")
    void createDiary(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @ApiParam(value = "저장할 날짜",example = "2020-02-02")
            LocalDate date,
            @RequestBody
            @ApiParam(value = "저장할 내용",example = "일기 내용")
            String text) {
        diaryService.createDiary(date, text);
    }

    @ApiOperation("선택한 날짜의 모든 일기 데이터를 DB에서 가져옵니다.")
    @GetMapping("/read/diary")
    List<Diary> readDiary(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @ApiParam(value = "조회할 날짜",example = "2020-02-02")
            LocalDate date) {
        return diaryService.readDiary(date);
    }

    @ApiOperation("선택한 기간중의 모든 일기 데이터를 DB에서 가져옵니다.")
    @GetMapping("/read/diaries")
    List<Diary> readDiaries(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @ApiParam(value = "조회할 기간의 첫번째날",example = "2020-02-02")
            LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @ApiParam(value = "조회할 기간의 마지막날",example = "2020-03-02")
            LocalDate endDate) {

        return diaryService.readDiaries(startDate, endDate);
    }

    @ApiOperation("해당 날짜에 일기 데이터를 DB에서 수정합니다.")
    @PutMapping("/update/diary")
    void updateDiary(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @ApiParam(value = "수정할 일기의 날짜", example = "2020-02-02")
            LocalDate date,
            @RequestBody
            @ApiParam(value = "저장할 일기 내용", example = "수정할 내용") String text) {
        diaryService.updateDiary(date, text);
    }


    @ApiOperation("해당 날짜의 일기 데이터를 DB에서 삭제합니다.")
    @DeleteMapping("/delete/diary")
    void deleteDiary(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @ApiParam(value = "삭제할 일기의 날짜", example = "2020-02-02")
            LocalDate date){
        diaryService.deleteDiary(date);
    }
}
