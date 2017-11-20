package de.tum.cs.i1.pse;

public class MusicRemote {

	public static void main(String[] args) {
		MusicPlayer player = new MusicPlayer();
		Event[] events = { Event.OnOffPressed, Event.PlayPausePressed };

		for (Event event : events) {
			player.handle(event);
		}
	}
}
