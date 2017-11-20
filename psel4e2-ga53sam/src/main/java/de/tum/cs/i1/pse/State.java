package de.tum.cs.i1.pse;

public interface State {
	
	public void entry();
	public void exit();
	public void handle(MusicPlayer player, Event event);	
}
