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
package de.hybris.platform.cuppytrail.forgotpassword.actions;

import de.hybris.platform.cuppytrail.model.ForgotPasswordProcessModel;
import de.hybris.platform.processengine.action.AbstractProceduralAction;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.task.RetryLaterException;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * Sets the password of the user to the new value.
 */
public class UpdatePasswordAction extends AbstractProceduralAction<ForgotPasswordProcessModel>
{

	@Autowired
	private UserService userService;

	@Override
	public void executeAction(final ForgotPasswordProcessModel process) throws RetryLaterException, Exception
	{
		final String userUid = process.getUserUid();
		final String newPassword = process.getNewPassword();
		userService.setPassword(userUid, newPassword);
	}
}
