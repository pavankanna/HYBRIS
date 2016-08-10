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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.cuppy.enums.CompetitionType;
import de.hybris.platform.cuppy.model.CompetitionModel;
import de.hybris.platform.cuppy.model.GroupModel;
import de.hybris.platform.cuppy.model.MatchBetModel;
import de.hybris.platform.cuppy.model.MatchModel;
import de.hybris.platform.cuppy.model.PlayerModel;
import de.hybris.platform.cuppy.model.TeamModel;
import de.hybris.platform.cuppy.services.MatchService;
import de.hybris.platform.cuppytrail.attributehandlers.MatchBetScoreAttributeHandler;
import de.hybris.platform.servicelayer.ServicelayerBaseTest;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;


/**
 * Tests dynamic attribute {@link MatchBetModel#MATCHSCORE}.
 * 
 * @see MatchBetScoreAttributeHandler
 */
public class MatchBetScoreAttributeIntegrationTest extends ServicelayerBaseTest
{
	@Resource
	private ModelService modelService;
	@Resource
	private MatchService matchService;

	private MatchModel match;
	private MatchBetModel bet;

	@Before
	public void setUp()
	{
		final CompetitionModel comp = createCompetition("comp", CompetitionType.TOURNAMENT);
		final GroupModel group = createGroup(comp, "group");
		final TeamModel homeTeam = createTeam("team a");
		final TeamModel guestTeam = createTeam("team b");
		final CountryModel country = createCountry("country");
		final PlayerModel player = createPlayer("player", country, "foo@bar.com");
		match = createMatch(1, "somewhere", 1, group, homeTeam, guestTeam);
		bet = createBet(player, match, 1, 1);

		// test data setup
		assertFalse("data setup error: match should not be finished", matchService.isMatchFinished(match));
	}

	@Test
	public void testMatchScoreAttribute()
	{
		// must produce NULL since match is not finished
		assertNull("unfinished match should give NULL", bet.getMatchScore());

		// set result ( best score since 1:1 == 1:1 )
		match.setHomeGoals(Integer.valueOf(1));
		match.setGuestGoals(Integer.valueOf(1));
		modelService.save(match);
		final Integer expected = Integer.valueOf(matchService.getScore(bet));
		assertEquals("Score attribute did not produce expected result", expected, bet.getMatchScore());

		// change result ( worst result since 1:1 != 0:2)
		match.setHomeGoals(Integer.valueOf(0));
		match.setGuestGoals(Integer.valueOf(2));
		modelService.save(match);
		final Integer expectedNow = Integer.valueOf(matchService.getScore(bet));
		assertFalse("Scores did not change after result change", expectedNow.equals(expected));
		assertEquals("Score attribute did not produce expected result", expectedNow, bet.getMatchScore());

		// change match result to non-existent
		match.setHomeGoals(null);
		match.setGuestGoals(null);
		modelService.save(match);

		assertNull("unfinished match should give NULL", bet.getMatchScore());
	}

	private MatchBetModel createBet(final PlayerModel player, final MatchModel match, final int homeGoals, final int guestGoals)
	{
		final MatchBetModel bet = modelService.create(MatchBetModel.class);
		bet.setMatch(match);
		bet.setPlayer(player);
		bet.setHomeGoals(homeGoals);
		bet.setGuestGoals(guestGoals);
		modelService.save(bet);

		return bet;
	}

	private MatchModel createMatch(final int id, final String location, final int matchDay, final GroupModel group,
			final TeamModel homeTeam, final TeamModel guestTeam)
	{
		final MatchModel match = modelService.create(MatchModel.class);
		match.setId(id);
		match.setLocation(location);
		// date must be in past to make MatchService work correctly
		match.setDate(new Date(System.currentTimeMillis() - (60 * 1000)));
		match.setMatchday(matchDay);
		match.setGroup(group);
		match.setHomeTeam(homeTeam);
		match.setGuestTeam(guestTeam);
		modelService.save(match);

		return match;
	}

	private PlayerModel createPlayer(final String uid, final CountryModel country, final String email)
	{
		final PlayerModel player = modelService.create(PlayerModel.class);
		player.setUid(uid);
		player.setCountry(country);
		player.setConfirmed(true);
		player.setEMail(email);
		modelService.save(player);
		return player;
	}

	private CountryModel createCountry(final String isoCode)
	{
		final CountryModel country = modelService.create(CountryModel.class);
		country.setIsocode(isoCode);
		modelService.save(country);
		return country;
	}

	private TeamModel createTeam(final String code)
	{
		final TeamModel team = modelService.create(TeamModel.class);
		team.setIsocode(code);
		modelService.save(team);
		return team;
	}

	private GroupModel createGroup(final CompetitionModel comp, final String code)
	{
		final GroupModel group = modelService.create(GroupModel.class);
		group.setCode(code);
		group.setSequenceNumber(0);
		group.setCompetition(comp);
		group.setMultiplier(1.0f);
		modelService.save(group);
		return group;
	}

	private CompetitionModel createCompetition(final String code, final CompetitionType type)
	{
		final CompetitionModel comp = modelService.create(CompetitionModel.class);
		comp.setCode(code);
		comp.setType(type);
		modelService.save(comp);
		return comp;
	}
}
