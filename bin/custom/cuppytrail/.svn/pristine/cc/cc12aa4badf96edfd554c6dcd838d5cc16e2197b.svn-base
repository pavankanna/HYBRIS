package de.hybris.platform.cuppytrail.events;

import de.hybris.platform.cuppy.model.NewsModel;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Locale;

import org.apache.log4j.Logger;



public class StadiumEventListener extends AbstractEventListener<CapacityEvent>
{

	static final private Logger LOG = Logger.getLogger(StadiumEventListener.class);

	@Override
	public void onEvent(final CapacityEvent event)
	{
		try
		{
			LOG.debug("### Entering event handler ###");
			Thread.sleep(2000); //long processing

			final NewsModel news = getModelService().create(NewsModel.class);
			final String content = "New big stadium. Code: " + event.getCode() + ", capacity: " + event.getCapacity();
			news.setContent(content, Locale.ENGLISH);
			getModelService().save(news);
			LOG.debug("### News created: " + content + " ###");
			LOG.debug("### Leaving event handler ###");

		}
		catch (final InterruptedException e)
		{
			// do nothing if thread is interrupted
		}
	}

	public ModelService getModelService()
	{
		throw new UnsupportedOperationException("this is lookup method - check your spring configuration!");
	}
}
