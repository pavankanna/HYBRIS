/**
 * 
 */
package de.hybris.platform.cuppytrail.web.components;

import de.hybris.platform.cockpit.util.UITools;
import de.hybris.platform.cuppytrail.ForgotPasswordService;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Button;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.SimpleConstraint;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;


/**
 * @author andreas.thaler
 * 
 */
public class ResetPasswordWindow extends Window implements AfterCompose
{

	static private Log LOG = LogFactory.getLog(ResetPasswordWindow.class);
	private ForgotPasswordService forgotPasswordService;
	private final String resetKey;

	public ResetPasswordWindow() throws InterruptedException
	{
		super();

		this.resetKey = Executions.getCurrent().getParameter("key");

		this.setWidth("450px");
		this.setPosition("center");
		this.setMode("overlapped");
		this.setShadow(false);
		this.setBorder("none");
		this.setClosable(false);
		this.setSizable(false);

		final Groupbox box = new Groupbox();
		box.setMold("3d");
		box.setClosable(false);
		this.appendChild(box);

		box.appendChild(new Caption(Labels.getLabel("forget.title")));

		final Div formDiv = new Div();
		formDiv.setWidth("100%");
		box.appendChild(formDiv);

		final Grid grid = new Grid();
		formDiv.appendChild(grid);

		final Rows rows = new Rows();
		grid.appendChild(rows);

		final Row passwordRow = new Row();
		passwordRow.setSclass("registerRow");
		passwordRow.appendChild(new Label(Labels.getLabel("forget.param.password")));
		final Textbox passwordBox = new Textbox();
		passwordBox.setType("password");
		passwordBox.setConstraint(new SimpleConstraint(SimpleConstraint.NO_EMPTY, Labels.getLabel("forget.error.nopassword")));
		passwordRow.appendChild(passwordBox);
		rows.appendChild(passwordRow);

		final Row password2Row = new Row();
		password2Row.setSclass("registerRow");
		password2Row.appendChild(new Label(Labels.getLabel("forget.param.password2")));
		final Textbox password2Box = new Textbox();
		password2Box.setType("password");
		password2Box.setConstraint(new SimpleConstraint(SimpleConstraint.NO_EMPTY, Labels.getLabel("forget.error.nopassword")));
		password2Row.appendChild(password2Box);
		rows.appendChild(password2Row);

		final Hbox hBox = new Hbox();
		hBox.setPack("center");
		hBox.setWidth("100%");
		final Button submitButton = new Button(Labels.getLabel("forget.reset"));
		hBox.appendChild(submitButton);
		final Button backButton = new Button(Labels.getLabel("forget.backtologin"));
		backButton.setHref("/");
		hBox.appendChild(backButton);
		box.appendChild(hBox);

		final EventListener listener = new EventListener()
		{
			@Override
			public void onEvent(final Event event) throws InterruptedException
			{
				doForget(password2Box);
			}
		};

		UITools.addBusyListener(submitButton, Events.ON_CLICK, listener, null, null);
		UITools.addBusyListener(this, Events.ON_OK, listener, null, null);
	}

	private void doForget(final Textbox passwordBox) throws InterruptedException
	{
		passwordBox.addEventListener("onMessageLater", new EventListener()
		{
			@Override
			public void onEvent(final Event event) throws Exception //NOPMD
			{
				if ("success".equals(event.getData()))
				{
					Messagebox.show(Labels.getLabel("forgot.password.reset"));
					Executions.sendRedirect("/");
				}
				else if ("unknownMail".equals(event.getData()))
				{
					Messagebox.show(Labels.getLabel("forget.error.unknownmail", new Object[]
					{ passwordBox.getValue() }));
				}
				else
				{
					Messagebox.show((String) event.getData());
				}
			}
		});

		try
		{
			getForgotPasswordService().resetPassword(resetKey, passwordBox.getValue());
			Events.echoEvent("onMessageLater", passwordBox, "success");
		}
		catch (final UnknownIdentifierException e)
		{
			LOG.error(e.getMessage(), e);
			Events.echoEvent("onMessageLater", passwordBox, "unknownMail");
		}
		catch (final Exception e)
		{
			LOG.error(e.getMessage(), e);
			Events.echoEvent("onMessageLater", passwordBox, e.getMessage());
		}
	}

	@Override
	public void afterCompose()
	{
		if (!forgotPasswordService.isValidPasswordResetKey(resetKey))
		{
			LOG.error("Invalid key: " + resetKey);
		}
	}


	public void setForgotPasswordService(final ForgotPasswordService accountService)
	{
		this.forgotPasswordService = accountService;
	}

	protected ForgotPasswordService getForgotPasswordService()
	{
		return forgotPasswordService;
	}

}
