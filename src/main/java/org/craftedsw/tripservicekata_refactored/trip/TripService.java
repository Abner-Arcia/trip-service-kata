package org.craftedsw.tripservicekata_refactored.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.trip.Trip;
import org.craftedsw.tripservicekata_refactored.user.User;
import org.craftedsw.tripservicekata_refactored.user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {

	TripDAO tripDAO;

	public TripService(TripDAO tripDAO) {
		this.tripDAO = tripDAO;
	}

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		User loggedUser = getLoggedUser();
		if (loggedUser == null) {
			throw new UserNotLoggedInException();
		}
		if (loggedUser.isFriendsWith(user)) {
			return tripDAO.findTripsByUser(user);
		}
		return new ArrayList<>();
	}

	protected User getLoggedUser() {
		return UserSession.getInstance().getLoggedUser();
	}
	
}
