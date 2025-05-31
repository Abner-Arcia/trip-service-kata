package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata_refactored.user.User;
import org.craftedsw.tripservicekata_refactored.trip.TripDAO;
import org.craftedsw.tripservicekata_refactored.trip.TripService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TripServiceTest {

    User user;

    @Mock
    TripDAO tripDAO;

    @BeforeEach
    public void beforeEach() {
        user = new User();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenUserIsFriend_ThenFindTripsByUserInDAO() {
        User loggedUser = new User() {
            @Override
            public boolean isFriendsWith(User user) {
                return true;
            }
        };

        TripService tripService = new TripService(tripDAO) {
            @Override
            protected User getLoggedUser() {
                return loggedUser;
            }
        };

        tripService.getTripsByUser(user);

        Mockito.verify(tripDAO).findTripsByUser(user);
    }

    @Test
    public void whenUserIsNotFriend_returnEmptyList() {
        User loggedUser = new User() {
            @Override
            public boolean isFriendsWith(User user) {
                return false;
            }
        };

        TripService tripService = new TripService(tripDAO) {
            @Override
            protected User getLoggedUser() {
                return loggedUser;
            }
        };

        List<Trip> trips = tripService.getTripsByUser(user);

        assertTrue(trips.isEmpty());
        Mockito.verify(tripDAO, Mockito.never()).findTripsByUser(user);
    }

    @Test
    public void whenUserIsNotLoggedIn_ThenThrowUserNotLoggedInException() {
        TripService tripService = new TripService(tripDAO) {
            @Override
            protected User getLoggedUser() {
                return null;
            }
        };

        assertThrows(UserNotLoggedInException.class, () -> {
            tripService.getTripsByUser(user);
        });
    }
}
