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
package de.hybris.platform.cuppytrail;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.cuppy.model.NewsModel;
import de.hybris.platform.cuppytrail.events.CapacityEvent;
import de.hybris.platform.cuppytrail.events.StadiumEventListener;
import de.hybris.platform.servicelayer.model.ModelService;

import org.junit.Test;


@UnitTest
public class StadiumCapacityEventTest
{

	@Test
	public void testCapacityEventAction()
	{
		final ModelService modelService = mock(ModelService.class);
		when(modelService.create(NewsModel.class)).thenReturn(new NewsModel());
		final StadiumEventListener listener = new StadiumEventListener()
		{
			@Override
			public ModelService getModelService()
			{
				return modelService;
			}
		};

		listener.onEvent(new CapacityEvent("code", Integer.valueOf(60000)));

		verify(modelService, times(1)).save(any(NewsModel.class));
	}

}
