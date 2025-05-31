package org.craftedsw.tripservicekata_refactored.trip;

import org.craftedsw.tripservicekata.exception.CollaboratorCallException;
import org.craftedsw.tripservicekata.trip.Trip;
import org.craftedsw.tripservicekata_refactored.user.User;

import java.util.List;

public class TripDAO {

	public List<Trip> findTripsByUser(User user) {
		throw new CollaboratorCallException(
				"TripDAO should not be invoked on an unit test.");
	}
	
}