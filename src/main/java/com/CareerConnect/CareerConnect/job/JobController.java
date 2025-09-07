package com.CareerConnect.CareerConnect.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> findAll() {
        return ResponseEntity.ok(jobService.findAll());
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        jobService.createJob(job);
        return new ResponseEntity<>("Job added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        Job job = jobService.getJobById(id);
        if (job != null) {
            return ResponseEntity.ok(job); // 200 OK
        }
        // return 401 Unauthorized with no body
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
        // return dummy job if not found
        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteJob(@PathVariable Long id) {
            boolean deleted = jobService.deleteJobById(id);
            if (deleted) {
                return ResponseEntity.ok("Job deleted successfully"); // 200 OK
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Job not found");          // 404 Not Found
            }
        }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updatedJob) {
        boolean updated = jobService.updateJobById(id, updatedJob);

        if (updated) {
            return ResponseEntity.ok("Job updated successfully"); // 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Job not found"); // 404 Not Found
        }
    }


}
