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

import de.hybris.platform.cuppytrail.SecureToken;
import de.hybris.platform.cuppytrail.SecureTokenService;
import de.hybris.platform.cuppytrail.model.ForgotPasswordProcessModel;
import de.hybris.platform.processengine.action.AbstractProceduralAction;
import de.hybris.platform.task.RetryLaterException;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * Generates the unique ID that identifies the forgot password process. The key is later added as a paramater of the URL
 * of the password.
 */
public class GenerateKeyAction extends AbstractProceduralAction<ForgotPasswordProcessModel>
{

	@Autowired
	private SecureTokenService secureTokenService;

	@Override
	public void executeAction(final ForgotPasswordProcessModel process) throws RetryLaterException, Exception
	{
		final SecureToken token = new SecureToken(process.getEmailAddress(), System.currentTimeMillis());
		final String data = secureTokenService.encryptData(token);
		process.setKey(data);
		getModelService().save(process);
	}

}
