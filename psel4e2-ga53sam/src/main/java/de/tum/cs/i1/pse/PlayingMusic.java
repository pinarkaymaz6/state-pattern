package de.tum.cs.i1.pse;

//TODO: PlayingMusic State must conform to the State interface
public class PlayingMusic implements State {

	protected int computeVampirePower() {
		return 42;
	}

	private void playMusic() {
		System.out.println("Play Music");
	}

	@Override
	public void entry() {
		playMusic();
	}

	@Override
	public void exit() {
		computeVampirePower();
	}

	@Override
	public void handle(MusicPlayer player, Event event) {
		switch (event){
			case OnOffPressed:
				break;
			case PlayPausePressed:
				player.setCurrentState(new Paused());
				break;
			case SleepTimeExceeded:
				player.setCurrentState(new Paused());
				break;
		}
	}
}
