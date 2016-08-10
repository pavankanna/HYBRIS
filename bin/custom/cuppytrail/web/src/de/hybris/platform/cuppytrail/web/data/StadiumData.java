package de.hybris.platform.cuppytrail.web.data;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;


/**
 * This class belongs to the Source Code Trail documented at https://wiki.hybris.com/display/pm/Source+Code+Tutorial It
 * is the data transfer object for the Stadium Facade
 */
public class StadiumData
{
	private final static Logger LOG = Logger.getLogger(StadiumData.class);
	private final String capacity;
	private final String name;
	private final List<MatchSummaryData> matches;

	public StadiumData(final String name, final Integer capacity)
	{
		this(name, capacity, Collections.EMPTY_LIST);
	}

	public StadiumData(final String name, final Integer capacity, final List<MatchSummaryData> matches)
	{
		this.name = name;
		this.matches = matches;
		this.capacity = capacity == null ? "-" : capacity.toString();
	}

	public String getName()
	{
		return name;
	}

	public String getCapacity()
	{
		return capacity;
	}

	public List<MatchSummaryData> getMatches()
	{
		return matches;
	}

	public String getNameEncoded()
	{
		try
		{
			return URLEncoder.encode(this.name, "UTF-8");
		}
		catch (final UnsupportedEncodingException e)
		{
			LOG.error("Problems while encoding", e);
			return "";
		}
	}
}
