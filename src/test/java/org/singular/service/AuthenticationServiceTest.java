package org.singular.service;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.singular.entities.dto.person.PersonInfoDTO;
import org.singular.service.impl.AuthenticationServiceImpl;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationServiceTest {
    private PersonInfoDTO sven;
    private PersonInfoDTO alix;

    private AuthenticationService authenticationService = new AuthenticationServiceImpl();

    @Before
    public void before() {
        sven = new PersonInfoDTO(1, "Sven", "Schittecatte");
        alix = new PersonInfoDTO(2, "Alix", "Goossens");
    }

    @Test
    public void addMultipleUsers() {
        long timeForSven = new DateTime().getMillis();
        authenticationService.createNewSession(sven);
        long timeForAlix = new DateTime().getMillis();
        authenticationService.createNewSession(alix);
        assertTrue(isAcceptableDeviation(authenticationService.getToken(sven).getTime(), timeForSven));
        assertTrue(isAcceptableDeviation(authenticationService.getToken(alix).getTime(), timeForAlix));
    }

    @Ignore
    @Test
    public void updateUserToken() {
        authenticationService.createNewSession(sven);
        long userTokenTime = authenticationService.getToken(sven).getTime();
        authenticationService.updateSession(sven);
        assertTrue(authenticationService.getToken(sven).getTime() > userTokenTime);
    }

    private boolean isAcceptableDeviation(long tokenTime, long recordedTime) {
        if((tokenTime - recordedTime) < 600) {
            return true;
        } else
            return false;
    }
}
