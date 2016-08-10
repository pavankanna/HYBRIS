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
package de.hybris.platform.cuppytrail.workflow;

import de.hybris.platform.cuppy.model.PlayerModel;
import de.hybris.platform.workflow.model.WorkflowActionModel;
import de.hybris.platform.workflow.model.WorkflowDecisionModel;

import org.apache.log4j.Logger;


/**
 * Automated workflow job that deals with the declined cuppy player. Part of the player registration workflow.
 */
public class RegistrationDeclineActionJob extends AbstractPlayerRegistrationActionJob
{
	private static final Logger LOG = Logger.getLogger(RegistrationDeclineActionJob.class);

	@Override
	public WorkflowDecisionModel perform(final WorkflowActionModel action)
	{
		final PlayerModel player = getAttachedPlayer(action);

		LOG.info("Player " + player.getUid() + " declined and will be removed.");

		getModelService().remove(player);

		for (final WorkflowDecisionModel decision : action.getDecisions())
		{
			return decision;
		}
		return null;
	}

}
