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

import de.hybris.platform.cuppytrail.model.EmailMessageModel;
import de.hybris.platform.cuppytrail.model.ForgotPasswordProcessModel;
import de.hybris.platform.processengine.action.AbstractProceduralAction;
import de.hybris.platform.task.RetryLaterException;

import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;


/**
 * Sends the e-mail as per the values in the {@link EmailMessageModel} attached to the
 * {@link ForgotPasswordProcessModel}
 */
public class SendEmailAction extends AbstractProceduralAction<ForgotPasswordProcessModel>
{

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void executeAction(final ForgotPasswordProcessModel process) throws RetryLaterException, Exception
	{
		final EmailMessageModel emailMessage = process.getEmailMessage();
		final MimeMessagePreparator preparator = new MimeMessagePreparator()
		{

			@Override
			public void prepare(final MimeMessage message) throws Exception
			{
				message.setSubject(emailMessage.getSubject());
				message.setText(emailMessage.getBody());
				message.setRecipients(RecipientType.TO, emailMessage.getRecipientAddress());
			}

		};
		mailSender.send(preparator);
	}


}