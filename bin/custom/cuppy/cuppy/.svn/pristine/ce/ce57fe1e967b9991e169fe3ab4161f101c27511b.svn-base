/**
 * 
 */
package de.hybris.platform.cuppy.services.impl;

import de.hybris.platform.cuppy.services.TenantScopedComponent;
import de.hybris.platform.cuppy.services.RandomizeStrategy;

import java.util.Random;


/**
 * @author andreas.thaler
 * 
 */
@TenantScopedComponent("randomizeStrategy")
public class DefaultRandomizeStrategy implements RandomizeStrategy
{
	private final Random rand;

	public DefaultRandomizeStrategy()
	{
		rand = new Random();
	}

	@Override
	public int getNext(final int start, final int end)
	{
		assert end > start;
		return start + rand.nextInt(end - start);
	}
}
