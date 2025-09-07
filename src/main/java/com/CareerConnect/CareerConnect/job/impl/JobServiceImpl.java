package com.CareerConnect.CareerConnect.job.impl;
import com.CareerConnect.CareerConnect.job.Job;
import com.CareerConnect.CareerConnect.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Service;
@Service
public class JobServiceImpl  implements JobService{
    private List<Job> jobs = new ArrayList<>();
    private  long nextId =1L;

    @Override
    public List<Job> findAll() {

        return jobs;
    }

    @Override
    public void createJob(Job job) {
        job.setId(nextId++);
        jobs.add(job);

    }

    @Override
    public Job getJobById(Long id) {
        for (Job job:
                jobs) {
            if(job.getId().equals(id)){
                return job;
            }

        }
        return null;
    }
    @Override
    public boolean deleteJobById(Long id) {
        Iterator<Job> iterator = jobs.iterator();
        while (iterator.hasNext()) {
            Job job = iterator.next();
            if (job.getId().equals(id)) {
                iterator.remove(); // remove the job from the list
                return true;       // deleted successfully
            }
        }
        return false; // job not found
    }

    @Override
    public boolean updateJobById(Long id, Job updatedJob) {
        for (Job job : jobs) {
            if (job.getId().equals(id)) {
                // Update fields
                job.setTitle(updatedJob.getTitle());
                job.setDescription(updatedJob.getDescription());
                job.setMinSalary(updatedJob.getMinSalary());
                job.setMaxSalary(updatedJob.getMaxSalary());
                job.setLocation(updatedJob.getLocation());
                return true; // updated successfully
            }
        }
        return false; // job not found
    }


}
