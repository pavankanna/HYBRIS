/**
 * 
 */
package de.hybris.platform.cuppy.web.components;

import de.hybris.platform.cockpit.session.UISessionUtils;
import de.hybris.platform.cuppy.web.data.NewsData;
import de.hybris.platform.cuppy.web.facades.MatchFacade;

import java.text.DateFormat;
import java.util.List;

import org.zkoss.spring.SpringUtil;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Div;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Html;
import org.zkoss.zul.Label;


/**
 * @author andreas.thaler
 * 
 */
public class InfoComponent extends Div
{
	public InfoComponent()
	{
		super();

		final List<NewsData> latestNews = getMatchFacade().getLatestNews(10);
		if (latestNews.isEmpty())
		{
			this.appendChild(new Label(Labels.getLabel("info.nonews")));
		}
		else
		{
			for (final NewsData news : latestNews)
			{
				this.appendChild(createNewsComponent(news));
			}
		}

	}

	private HtmlBasedComponent createNewsComponent(final NewsData news)
	{
		final Groupbox gbox = new Groupbox();
		gbox.setMold("3d");
		gbox.setClosable(false);
		final Caption caption = new Caption(DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT,
				UISessionUtils.getCurrentSession().getLocale()).format(news.getCreationTime())
				+ " - " + news.getCompetitionName());
		gbox.appendChild(caption);
		gbox.appendChild(new Html(news.getContent()));
		return gbox;
	}

	private MatchFacade getMatchFacade()
	{
		return (MatchFacade) SpringUtil.getBean("matchFacade");
	}
}
