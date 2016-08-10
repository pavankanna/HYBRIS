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
package de.hybris.platform.cuppytrail.daos.impl;

import de.hybris.platform.cuppy.services.TenantScopedComponent;
import de.hybris.platform.cuppytrail.daos.ForgotPasswordProcessDAO;
import de.hybris.platform.cuppytrail.model.ForgotPasswordProcessModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


@TenantScopedComponent("forgotPasswordProcessDAO")
public class DefaultForgotPasswordProcessDAO implements ForgotPasswordProcessDAO
{

	@Autowired
	private FlexibleSearchService flexibleSearchService;

	@Override
	public List<ForgotPasswordProcessModel> findForgotPasswordModelByKey(final String key)
	{
		final String queryString = //
		"SELECT {p:" + ForgotPasswordProcessModel.PK + "}" //
				+ "FROM {" + ForgotPasswordProcessModel._TYPECODE + " AS p} "//
				+ "WHERE " + "{p:" + ForgotPasswordProcessModel.KEY + "}=?key ";

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		query.addQueryParameter("key", key);
		return flexibleSearchService.<ForgotPasswordProcessModel> search(query).getResult();
	}

}
