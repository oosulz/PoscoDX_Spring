package config.videosystem.mixing;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import videosystem.Avengers;
import videosystem.BlankDisc;
import videosystem.DVDPlayer;
import videosystem.DigitalVideoDisc;
import videosystem.IronMan;

@Configuration
public class DVDConfig {

	@Bean
	public DigitalVideoDisc avengers() {
		return new Avengers();
	}
	@Bean
	public DigitalVideoDisc avengersInfinityWar() {
		BlankDisc dvd = new BlankDisc();
		dvd.setTitle("Avengers Infinity War");
		dvd.setStudio("Marvels");
		
		return dvd;
		
	}
}
