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
package de.hybris.platform.cuppytrail.validation;

import de.hybris.platform.cuppy.model.MatchBetModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.validation.exceptions.HybrisConstraintViolation;
import de.hybris.platform.validation.services.ValidationService;

import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;


/**
 * It is part of cuppytrail validation. It shows how to use validation service.
 */
public class ValidationTest extends ServicelayerTransactionalTest
{

	@Resource
	private ModelService modelService;

	@Resource
	ValidationService validationService;

	@Test
	public void testStadiumInterceptor()
	{
		final MatchBetModel matchBetModel = modelService.create(MatchBetModel.class);
		matchBetModel.setHomeGoals(100);
		matchBetModel.setGuestGoals(200);

		final Set<HybrisConstraintViolation> validations = validationService.validate(matchBetModel);

		for (final HybrisConstraintViolation hybrisConstraintViolation : validations)
		{
			System.out.print(" " + hybrisConstraintViolation.getLocalizedMessage());
		}

	}
}
