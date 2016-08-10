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

import de.hybris.platform.cuppy.model.PlayerModel;
import de.hybris.platform.cuppy.services.PlayerService;
import de.hybris.platform.cuppytrail.model.EmailMessageModel;
import de.hybris.platform.cuppytrail.model.ForgotPasswordProcessModel;
import de.hybris.platform.processengine.action.AbstractProceduralAction;
import de.hybris.platform.servicelayer.i18n.L10NService;
import de.hybris.platform.task.RetryLaterException;
import de.hybris.platform.util.Config;

import java.net.URLEncoder;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Generates the e-mail with the link to the password reset page. Subject, body, recipient etc are store in an instance
 * of {@link EmailMessageModel} and then attached to the {@link ForgotPasswordProcessModel}
 */
public abstract class GenerateEmailAction extends AbstractProceduralAction<ForgotPasswordProcessModel> implements
		InitializingBean
{

	@Autowired
	private PlayerService playerService;

	@Autowired
	private L10NService l10nService;

	private String domain;
	private String fromAddress;

	@Override
	public void executeAction(final ForgotPasswordProcessModel process) throws RetryLaterException, Exception
	{
		final PlayerModel player = playerService.getPlayerForEmail(process.getEmailAddress());
		final EmailMessageModel message = createEmailMessage();
		message.setRecipientAddress(player.getEMail());
		final String key = process.getKey();
		final String encodedKey = URLEncoder.encode(key, "UTF-8");
		final String url = "http://" + domain + "/resetpassword.zul?key=" + encodedKey;
		message.setSubject(l10nService.getLocalizedString("mail.forgotpassword.subject"));
		final String body = l10nService.getLocalizedString("mail.forgotpassword.body", new Object[]
		{ player.getName(), url });
		message.setBody(body);
		process.setEmailMessage(message);
		getModelService().save(process);
	}

	@Override
	public void afterPropertiesSet()
	{
		domain = Config.getParameter("cuppytrail.domain");
		fromAddress = Config.getParameter("mail.from");
		final String replyToAddress = Config.getParameter("mail.replyto");
		if (domain == null || domain.isEmpty() || fromAddress == null || fromAddress.isEmpty() || replyToAddress == null
				|| replyToAddress.isEmpty())
		{
			throw new IllegalStateException(
					"Can not initialize GenerateEmailAction, please configure properties 'cuppy.domain','mail.from' and 'mail.replyto'");
		}
	}

	public abstract EmailMessageModel createEmailMessage();

}
