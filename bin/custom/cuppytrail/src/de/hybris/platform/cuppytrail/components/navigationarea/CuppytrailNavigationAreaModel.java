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
package de.hybris.platform.cuppytrail.components.navigationarea;

import de.hybris.platform.cockpit.components.navigationarea.DefaultNavigationAreaModel;
import de.hybris.platform.cockpit.session.impl.AbstractUINavigationArea;

import de.hybris.platform.cuppytrail.session.impl.CuppytrailNavigationArea;


/**
 * Cuppytrail navigation area model.
 */
public class CuppytrailNavigationAreaModel extends DefaultNavigationAreaModel
{
	public CuppytrailNavigationAreaModel()
	{
		super();
	}

	public CuppytrailNavigationAreaModel(final AbstractUINavigationArea area)
	{
		super(area);
	}

	@Override
	public CuppytrailNavigationArea getNavigationArea()
	{
		return (CuppytrailNavigationArea) super.getNavigationArea();
	}
}
