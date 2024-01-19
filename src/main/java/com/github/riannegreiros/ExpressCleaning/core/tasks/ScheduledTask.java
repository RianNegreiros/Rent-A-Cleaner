package com.github.riannegreiros.ExpressCleaning.core.tasks;

import com.github.riannegreiros.ExpressCleaning.core.enums.DailyStatus;
import com.github.riannegreiros.ExpressCleaning.core.models.Daily;
import com.github.riannegreiros.ExpressCleaning.core.repositories.DailyRepository;
import com.github.riannegreiros.ExpressCleaning.core.services.cleanerIndex.adapters.CleanerIndexService;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTask.class);

    @Autowired
    private DailyRepository dailyRepository;

    @Autowired
    private CleanerIndexService cleanerIndexService;

    @Scheduled(cron = "0 0/5 * * * ?")
    @Transactional(readOnly = false)
    public void selectCleanerFromDaily() {
        log.info("Selection task for cleaners begins");

        var dailyAptForSelection = dailyRepository.getAppropriateForSelectionOfDailyCleaner();
        dailyAptForSelection.forEach(this::selectCleaner);

        log.info("Cleaner selection task finalized");
    }

    @Scheduled(cron = "0 3/5 * * * ?")
    @Transactional(readOnly = false)
    public void cancelDailyWithoutCandidates() {
        log.info("Task of canceling daily without candidates begins");

        var dailyAptForCancellation = dailyRepository.getAbleToCancel();
        dailyAptForCancellation.forEach(this::cancelDaily);

        log.info("Task of canceling daily without candidates finalized");
    }

    private void selectCleaner(Daily daily) {
        log.info("Selecting the best cleaner for id's daily " + daily.getId().toString());
        var bestCleaner = cleanerIndexService.selectBestCleaner(daily);
        daily.setCleaner(bestCleaner);
        daily.setStatus(DailyStatus.CONFIRMED);
        dailyRepository.save(daily);
        log.info("Selected the id cleaner " + bestCleaner.getId().toString());
    }

    private void cancelDaily(Daily daily) {
        log.info("Canceling the daily id " + daily.getId());
        if (daily.isPaid()) {
            log.info("Reimbursing id's daily " + daily.getId());
        }
        daily.setStatus(DailyStatus.CANCELLED);
        dailyRepository.save(daily);
        log.info("Daily id " + daily.getId() + " successfully canceled");
    }
}
