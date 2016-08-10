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
package de.hybris.platform.cuppytrail.impl;

import de.hybris.platform.cuppy.model.PlayerModel;
import de.hybris.platform.cuppy.services.PlayerService;
import de.hybris.platform.cuppy.services.TenantScopedComponent;
import de.hybris.platform.cuppytrail.ForgotPasswordService;
import de.hybris.platform.cuppytrail.daos.ForgotPasswordProcessDAO;
import de.hybris.platform.cuppytrail.model.ForgotPasswordProcessModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


@TenantScopedComponent(value = "forgotPasswordService")
public class DefaultForgotPasswordService implements ForgotPasswordService
{

	static private String FORGOT_PASSWORD_PROCESS_DEFINITION_NAME = "ForgotPassword";
	@Autowired
	private BusinessProcessService businessProcessService;
	@Autowired
	private ModelService modelService;
	@Autowired
	private ForgotPasswordProcessDAO forgotPasswordProcessDAO;
	@Autowired
	private PlayerService playerService;

	@Override
	public boolean isValidPasswordResetKey(final String key)
	{
		final List<ForgotPasswordProcessModel> result = forgotPasswordProcessDAO.findForgotPasswordModelByKey(key);
		return !result.isEmpty();
	}

	@Override
	public void forgotPassword(final String emailAddress)
	{
		final ForgotPasswordProcessModel process = businessProcessService.createProcess(String.valueOf(System.currentTimeMillis()),
				FORGOT_PASSWORD_PROCESS_DEFINITION_NAME);
		final PlayerModel player = playerService.getPlayerForEmail(emailAddress);
		process.setEmailAddress(emailAddress);
		process.setUserUid(player.getUid());
		modelService.save(process);
		businessProcessService.startProcess(process);
	}

	@Override
	public void resetPassword(final String key, final String password)
	{
		final List<ForgotPasswordProcessModel> result = forgotPasswordProcessDAO.findForgotPasswordModelByKey(key);
		if (result.isEmpty())
		{
			throw new UnknownIdentifierException("ForgotPasswordProcess with key '" + key + "' not found!");
		}
		else if (result.size() > 1)
		{
			throw new AmbiguousIdentifierException("ForgotPasswordProcess with key '" + key + "' is not unique, " + result.size()
					+ " processes found!");
		}
		final ForgotPasswordProcessModel process = result.get(0);
		process.setNewPassword(password);
		modelService.save(process);
		businessProcessService.triggerEvent(key);
	}

}
