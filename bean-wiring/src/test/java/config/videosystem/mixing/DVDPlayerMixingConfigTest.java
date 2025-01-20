package config.videosystem.mixing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import videosystem.DVDPlayer;
import videosystem.DVDPack;
import videosystem.DigitalVideoDisc;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { DVDPlayerConfig.class })
public class DVDPlayerMixingConfigTest {

	@Autowired
	private DVDPlayer dvdPlayer;

	@Test
	public void test() {
		assertEquals("Playing Movie Marvel's Avengers", dvdPlayer.play());
	}
}