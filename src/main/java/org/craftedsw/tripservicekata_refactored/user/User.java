package org.craftedsw.tripservicekata_refactored.user;

import org.craftedsw.tripservicekata.trip.Trip;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {

	private List<Trip> trips = new ArrayList<>();
	private List<User> friends = new ArrayList<>();
	
	public List<User> getFriends() {
		return Collections.unmodifiableList(friends);
	}
	
	public void addFriend(User user) {
		friends.add(user);
		user.friends.add(this);
	}

	public void addTrip(Trip trip) {
		trips.add(trip);
	}
	
	public List<Trip> trips() {
		return Collections.unmodifiableList(trips);
	}

	public boolean isFriendsWith(User user) {
		return friends.contains(user);
	}

}
