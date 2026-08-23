package com.example.courseenrollment.controller;

import com.example.courseenrollment.dto.CourseRosterDto;
import com.example.courseenrollment.dto.DepartmentSummaryDto;
import com.example.courseenrollment.dto.TopPerformerDto;
import com.example.courseenrollment.dto.TranscriptDto;
import com.example.courseenrollment.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/students/{id}/transcript")
    public ResponseEntity<?> getTranscript(@PathVariable Long id) {
        try {
            TranscriptDto transcript = reportService.getTranscript(id);
            return ResponseEntity.ok(transcript);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/courses/{id}/roster")
    public ResponseEntity<?> getCourseRoster(@PathVariable Long id) {
        try {
            CourseRosterDto roster = reportService.getCourseRoster(id);
            return ResponseEntity.ok(roster);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/departments/summary")
    public ResponseEntity<List<DepartmentSummaryDto>> getDepartmentSummary() {
        return ResponseEntity.ok(reportService.getDepartmentSummary());
    }

    @GetMapping("/top-performers")
    public ResponseEntity<List<TopPerformerDto>> getTopPerformers(
            @RequestParam(defaultValue = "5") int limit) {
        return ResponseEntity.ok(reportService.getTopPerformers(limit));
    }
}
