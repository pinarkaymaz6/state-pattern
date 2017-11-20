package de.tum.cs.i1.pse;

import static org.junit.Assert.*;

import org.junit.Test;

public class BehaviorTest {
	
	@Test(timeout = 100)
	public void testPlayState() {
		MusicPlayer remote = new MusicPlayer();
		Event[] events = {Event.OnOffPressed, Event.PlayPausePressed};
		testevents(remote, events);
		assertTrue("The resulting state is wrong: PlayState is expected. Transitions should be: Off -> On -> Play for Button sequence: Power, Play.", remote.getCurrentState() instanceof PlayingMusic);
	}

	@Test(timeout = 100)
	public void testPauseState() {
		MusicPlayer remote = new MusicPlayer();
		Event[] events = {Event.OnOffPressed, Event.PlayPausePressed, Event.PlayPausePressed};
		testevents(remote, events);
		assertTrue("The resulting state is wrong: PauseState is expected. Transitions should be: Off -> On -> Play -> Pause for Button sequence: Power, Play, Play.", remote.getCurrentState() instanceof Paused);
	}
	
	@Test(timeout = 100)
	public void testOnState() {
		MusicPlayer remote = new MusicPlayer();
		Event[] events = {Event.OnOffPressed};
		testevents(remote, events);
		assertTrue("The resulting state is wrong: OnState is expected. Transitions should be: Off -> On  for Button sequence: Power.", remote.getCurrentState() instanceof On);
	}
	
	@Test(timeout = 100)
	public void testNoTransitionOffState() {
		MusicPlayer remote = new MusicPlayer();
		Event[] events = {Event.PlayPausePressed};
		testevents(remote, events);
		assertTrue("The resulting state is wrong: OffState is expected. Transitions should be: Off -> Off  for Button sequence: Play.", remote.getCurrentState() instanceof Inactive);
	}
	
	@Test
	public void testNoTransitionPlayState() {
		MusicPlayer remote = new MusicPlayer();
		remote.setCurrentState(new PlayingMusic());
		Event[] events = {Event.OnOffPressed};
		testevents(remote, events);
		assertTrue("The resulting state is wrong: PlayState is expected. Transitions should be: Play -> Play  for Button sequence: Power.", remote.getCurrentState() instanceof PlayingMusic);
	}
	
	@Test(timeout = 100)
	public void testInitialOffState() {
		MusicPlayer remote = new MusicPlayer();
		Event[] events = {};
		testevents(remote, events);
		assertTrue("The resulting state is wrong: OffState is expected. Transitions should be: - for Button sequence: -.", remote.getCurrentState() instanceof Inactive);
	}
	
	@Test(timeout = 100)
	public void testComplexState() {
		MusicPlayer remote = new MusicPlayer();
		Event[] events = {Event.OnOffPressed, // -> On
				Event.PlayPausePressed, 		// -> Play
				Event.PlayPausePressed,		// -> Pause
				Event.OnOffPressed,			// -> Off
				Event.OnOffPressed,			// -> On
				Event.PlayPausePressed,		// -> Play
				Event.OnOffPressed,			// -> Play
				Event.PlayPausePressed,		// -> Pause
				Event.OnOffPressed,			// -> Off
				Event.OnOffPressed,			// -> On
				Event.OnOffPressed,			// -> Off
				Event.PlayPausePressed};		// -> Off
		testeventAndValidate(remote, events);
		
	}
	
	private void testevents(MusicPlayer remote, Event[] events) {
		for (Event event : events) {
			remote.handle(event);
		}
	}
	
	private void testeventAndValidate(MusicPlayer remote, Event[] events) {
		for (Event event : events) {
			State initialState = remote.getCurrentState();
			if (event == Event.OnOffPressed) {
				remote.handle(event);
				assertTrue("The resulting state is wrong. Initial State: " + initialState.getClass().getSimpleName() + "event: PowerButton ; Actual State: " + remote.getCurrentState().getClass().getSimpleName(), validateExpectedState(initialState, event, remote.getCurrentState()));
			} else if (event == Event.PlayPausePressed) {
				remote.handle(event);
				assertTrue("The resulting state is wrong. Initial State: " + initialState.getClass().getSimpleName() + "event: PlayButton ;Actual State: " + remote.getCurrentState().getClass().getSimpleName(), validateExpectedState(initialState, event, remote.getCurrentState()));
			}
		}
	}
	
	
	private boolean validateExpectedState(State initialState, Event event, State expectedState) {
		if (initialState instanceof Inactive) {
			if (event == Event.OnOffPressed && expectedState instanceof On) {
				return true;
			} else if (event == Event.PlayPausePressed && expectedState instanceof Inactive) {
				return true;
			} else {
				return false;
			}
		}
		else if (initialState instanceof On) {
			if (event == Event.OnOffPressed && expectedState instanceof Inactive) {
				return true;
			} else if (event == Event.PlayPausePressed && expectedState instanceof PlayingMusic) {
				return true;
			} else {
				return false;
			}
		}
		else if (initialState instanceof PlayingMusic) {
			if (event == Event.OnOffPressed && expectedState instanceof PlayingMusic) {
				return true;
			} else if (event == Event.PlayPausePressed && expectedState instanceof Paused) {
				return true;
			} else {
				return false;
			}
		}
		else if (initialState instanceof Paused) {
			if (event == Event.OnOffPressed && expectedState instanceof Inactive) {
				return true;
			} else if (event == Event.PlayPausePressed && expectedState instanceof PlayingMusic) {
				return true;
			} else {
				return false;
			}
		}
		else {
			fail("Undefined State found");
			return false;
		}
	}
	
}
