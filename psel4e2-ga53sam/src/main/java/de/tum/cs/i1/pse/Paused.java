package de.tum.cs.i1.pse;

//TODO: Paused State must conform to the State interface
public class Paused implements State{

	protected int computeVampirePower() {
		return 42;
	}

	private void pauseMusic() {
		System.out.println("Pause Music");
	}

	private void checkTimeout() {
		System.out.println("Check Timeout");
	}

	@Override
	public void entry() {
		pauseMusic();
		checkTimeout();
	}

	@Override
	public void exit() {
computeVampirePower();
	}

	@Override
	public void handle(MusicPlayer player, Event event) {
		switch (event){
			case PlayPausePressed:
				player.setCurrentState(new PlayingMusic());
				break;
			case OnOffPressed:
				player.setCurrentState(new Inactive());
				break;
			case SleepTimeExceeded:
				break;
		}
	}
}
