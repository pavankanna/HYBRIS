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
package de.hybris.platform.cuppytrail.interceptors;

import de.hybris.platform.cuppytrail.events.CapacityEvent;
import de.hybris.platform.cuppytrail.model.StadiumModel;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;

import org.springframework.beans.factory.annotation.Required;



public class StadiumCapacityInterceptor implements ValidateInterceptor, PrepareInterceptor
{
	private static final int BIG_STADIUM = 50000;
	private static final int TOO_BIG_STADIUM = 100000;

	private EventService eventService;


	@Required
	public void setEventService(final EventService eventService)
	{
		this.eventService = eventService;
	}

	@Override
	public void onValidate(final Object model, final InterceptorContext ctx) throws InterceptorException
	{
		if (model instanceof StadiumModel)
		{
			final StadiumModel stadium = (StadiumModel) model;
			final Integer capacity = stadium.getCapacity();
			if (capacity != null && capacity.intValue() >= TOO_BIG_STADIUM)
			{
				throw new InterceptorException("Capactity is too high");
			}
		}
	}

	@Override
	public void onPrepare(final Object model, final InterceptorContext ctx) throws InterceptorException
	{
		if (model instanceof StadiumModel)
		{
			final StadiumModel stadium = (StadiumModel) model;
			if (hasBecomeBig(stadium, ctx))
			{
				eventService.publishEvent(new CapacityEvent(stadium.getCode(), stadium.getCapacity()));
			}
		}

	}

	private boolean hasBecomeBig(final StadiumModel stadium, final InterceptorContext ctx)
	{
		final Integer capacity = stadium.getCapacity();
		if (capacity != null && capacity.intValue() >= BIG_STADIUM && capacity.intValue() < TOO_BIG_STADIUM)
		{
			if (ctx.isNew(stadium))
			{
				return true;
			}
			else
			{
				final Integer oldValue = (Integer) stadium.getValueHistory().getOriginalValue(StadiumModel.CAPACITY);
				if (oldValue == null || oldValue.intValue() < 50000)
				{
					return true;
				}
			}
		}
		return false;
	}
}
