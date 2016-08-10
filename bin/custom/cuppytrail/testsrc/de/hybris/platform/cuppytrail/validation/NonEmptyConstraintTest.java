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

import de.hybris.platform.cuppytrail.constraints.NotEmptyCuppyValidator;

import org.junit.Assert;
import org.junit.Test;


/**
 * Test to check de.hybris.platform.cuppytrail.NotEmptyValidator
 */
public class NonEmptyConstraintTest
{
	private final NotEmptyCuppyValidator notEmptyValidator = new NotEmptyCuppyValidator();

	@Test
	public void shoudRecognizeEmptyString()
	{
		Assert.assertFalse(notEmptyValidator.isValid("", null));
		Assert.assertFalse(notEmptyValidator.isValid("   ", null));
		Assert.assertFalse(notEmptyValidator.isValid(null, null));
		Assert.assertFalse(notEmptyValidator.isValid("  . ", null));
		Assert.assertTrue(notEmptyValidator.isValid("A", null));
	}
}
