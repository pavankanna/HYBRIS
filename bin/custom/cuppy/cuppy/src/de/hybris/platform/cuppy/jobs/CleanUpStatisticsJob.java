/**
 * 
 */
package de.hybris.platform.cuppy.jobs;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.cuppy.services.TenantScopedComponent;
import de.hybris.platform.cuppy.services.StatisticsService;
import de.hybris.platform.servicelayer.cronjob.JobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * 
 */
@TenantScopedComponent(value = "cleanUpStatisticsJob")
public class CleanUpStatisticsJob implements JobPerformable<CronJobModel>
{
	@Autowired
	private StatisticsService statisticsService;

	@Override
	public PerformResult perform(final CronJobModel cronJob)
	{
		statisticsService.cleanUpTimepointStatistics();
		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}

	@Override
	public boolean isAbortable()
	{
		return false;
	}

	@Override
	public boolean isPerformable()
	{
		return true;
	}
}
