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
package de.hybris.platform.cuppytrail.events;

import de.hybris.platform.servicelayer.event.ClusterAwareEvent;
import de.hybris.platform.servicelayer.event.events.AbstractEvent;


public class CapacityEvent extends AbstractEvent implements ClusterAwareEvent
{
	private final Integer capacity;
	private final String code;

	public CapacityEvent(final String code, final Integer capacity)
	{
		super();
		this.code = code;
		this.capacity = capacity;
	}

	public Integer getCapacity()
	{
		return capacity;
	}

	public String getCode()
	{
		return code;
	}

	@Override
	public String toString()
	{

		return this.code + "(" + this.capacity + ")";
	}

	@Override
	public boolean publish(final int sourceNodeId, final int targetNodeId)
	{
		return (sourceNodeId == targetNodeId);
	}


}