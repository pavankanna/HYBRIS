package de.hybris.platform.cuppytrail;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.cuppytrail.enums.StadiumAccess;
import de.hybris.platform.cuppytrail.enums.StadiumType;

import org.junit.Test;


/**
 * This test relates to the Tutorial Trail Step found at https://wiki.hybris.com/display/pm/Trail+~+New+Data+Model
 * 
 * Its purpose is to illustrate the differences between dynamic and static enums in the hybris type system
 */
@UnitTest
public class DynamicStaticEnumTests
{
	/**
	 * StadiumType is defined as a static enum in cuppy-trails.xml. The build process generates a genuine enum for this.
	 */
	@Test
	public void testStaticEnums()
	{
		final StadiumType stadiumType1 = StadiumType.ENCLOSED;
		final StadiumType stadiumType2 = StadiumType.ENCLOSED;
		final StadiumType stadiumType3 = StadiumType.OPENAIR;
		final StadiumType stadiumType4 = StadiumType.OPENAIR;
		assertEquals(stadiumType1, stadiumType2);
		assertEquals(stadiumType3, stadiumType4);
		assertNotSame(stadiumType1, stadiumType4);

		final StadiumType enclosed = Enum.valueOf(StadiumType.class, "ENCLOSED");
		assertNotNull(enclosed);
	}

	/**
	 * StadiumAccess is defined as a dynamic enum in cuppy-trails.xml. The build process generates a class that behaves
	 * like an enum with some additional methods, most notably "valueOf" with which we can generate new enum values at
	 * run time.
	 */
	@Test
	public void testDynamicEnums()
	{

		final StadiumAccess rail1 = StadiumAccess.RAIL;
		final StadiumAccess rail2 = StadiumAccess.RAIL;
		assertEquals(rail1, rail2);

		assertEquals(StadiumAccess.valueOf("RAIL"), StadiumAccess.RAIL);
		assertEquals(StadiumAccess.valueOf("NEW_TYPE"), StadiumAccess.valueOf("NEW_TYPE"));
		assertNotSame(StadiumAccess.valueOf("NEW_TYPE"), StadiumAccess.valueOf("AND_ANOTHER_NEW_TYPE"));

		// This will not compile as StadiumAccess is not a real Java Enum
		// final StadiumAccess rail = Enum.valueOf(StadiumAccess.class, "RAIL");
		// assertNotNull(rail);

	}
}
