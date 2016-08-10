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

import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.cuppy.model.PlayerModel;
import de.hybris.platform.cuppy.services.MailService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.workflow.jobs.AutomatedWorkflowTemplateJob;
import de.hybris.platform.workflow.model.WorkflowActionModel;

import java.util.List;


/**
 * Base class for player registration related automated action jobs.
 */
public abstract class AbstractPlayerRegistrationActionJob implements AutomatedWorkflowTemplateJob
{
	private ModelService modelService;
	private MailService mailService;

	protected PlayerModel getAttachedPlayer(final WorkflowActionModel action)
	{
		final List<ItemModel> attachments = action.getAttachmentItems();
		if (attachments != null)
		{
			for (final ItemModel item : attachments)
			{
				if (item instanceof PlayerModel)
				{
					return (PlayerModel) item;
				}
			}
		}
		return null;
	}

	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	protected ModelService getModelService()
	{
		return this.modelService;
	}

	public void setMailService(final MailService mailService)
	{
		this.mailService = mailService;
	}

	protected MailService getMailService()
	{
		return this.mailService;
	}
}
