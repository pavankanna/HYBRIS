/**
 * 
 */
package de.hybris.platform.cuppy.services.impl;

import de.hybris.platform.cuppy.model.CompetitionModel;
import de.hybris.platform.cuppy.model.MatchModel;
import de.hybris.platform.cuppy.services.MatchService;
import de.hybris.platform.cuppy.services.MatchdayCalculationStrategy;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author andreas.thaler
 * 
 */
public class ByDateMatchdayCalculationStrategy implements MatchdayCalculationStrategy
{
	@Autowired
	private MatchService matchService;

	@Override
	public int getMatchday(final CompetitionModel competition, final int matchdayId, final Date date)
	{
		final MatchModel lastMatch = matchService.getMatchBefore(competition, date);
		if (lastMatch == null)
		{
			return 1;
		}
		if (lastMatch.getDate().equals(date))
		{
			return lastMatch.getMatchday();
		}
		else
		{
			return lastMatch.getMatchday() + 1;
		}
	}
}
