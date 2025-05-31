package org.craftedsw.tripservicekata.user;

import org.craftedsw.tripservicekata.trip.Trip;
import org.craftedsw.tripservicekata_refactored.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {

    User user1;
    User user2;

    @BeforeEach
    public void beforeEach() {
        user1 = new User();
        user2 = new User();
    }

    @Test
    public void whenFriendIsAdded_thenTheUsersAreAddedToEachOthersFriendList() {
        user1.addFriend(user2);

        assertTrue(user1.getFriends().contains(user2));
        assertTrue(user2.getFriends().contains(user1));
    }

    @Test
    public void whenUserIsFriendsWithOtherUser_thenReturnTrue() {
        user1.addFriend(user2);

        assertTrue(user1.isFriendsWith(user2));
    }

    @Test
    public void whenTripIsAdded_thenTripListContainsTrip() {
        Trip trip = new Trip();

        user1.addTrip(trip);

        assertTrue(user1.trips().contains(trip));
    }

}
