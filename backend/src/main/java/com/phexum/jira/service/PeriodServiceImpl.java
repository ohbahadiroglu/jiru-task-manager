package com.phexum.jira.service;

import com.phexum.jira.entity.Period;
import com.phexum.jira.entity.Site;
import com.phexum.jira.exception.NotFoundException;
import com.phexum.jira.repository.PeriodRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeriodServiceImpl implements PeriodService{

    private final PeriodRepository periodRepository;

    public PeriodServiceImpl(PeriodRepository periodRepository) {
        this.periodRepository = periodRepository;
    }

    public Period create(Period period) {
    return periodRepository.save(period);
    }


    public List<Period> findAll() {
        List<Period> period = periodRepository.findAll();
        return period;
    }


    public Optional<Period> findById(Long id) {
        return periodRepository.findById(id);
    }


    public void delete(Long id) {
        Optional<Period> op = periodRepository.findById(id);
        if (op.isEmpty()) {
            throw new NotFoundException(id);
        }

        periodRepository.deleteById(id);

    }
}
