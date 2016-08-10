/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2011 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package de.hybris.platform.cuppytrail.attributehandlers;

import de.hybris.platform.cuppy.model.MatchBetModel;
import de.hybris.platform.cuppy.services.MatchService;
import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;

import org.springframework.beans.factory.annotation.Required;


/**
 * Implements the logic backing the dynamic attribute {@link MatchBetModel#MATCHSCORE}.
 */
//TODO use abstract class as soon as new snapshot is available "AbstractDynamicAttributeHandler<Integer, MatchBetModel>"
public class MatchBetScoreAttributeHandler implements DynamicAttributeHandler<Integer, MatchBetModel>
{
	private MatchService matchService;

	@Override
	public Integer get(final MatchBetModel model)
	{
		try
		{
			return Integer.valueOf(matchService.getScore(model));
		}
		catch (final IllegalStateException e) // no match result yet
		{
			return null;
		}
	}

	@Override
	public void set(final MatchBetModel model, final Integer value)
	{
		throw new UnsupportedOperationException();
	}

	@Required
	public void setMatchService(final MatchService matchService)
	{
		this.matchService = matchService;
	}
}
