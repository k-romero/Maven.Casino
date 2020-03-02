package io.zipcoder.casino;


import io.zipcoder.casino.player.Player;
import io.zipcoder.casino.utilities.Menu;
import org.junit.Assert;
import org.junit.Test;

public class CasinoTest {

    @Test
    public void getMenuItemByInputTest(){
        Casino casino = new Casino();
        Player p = new Player(10, "Apollo", 100, false);
        casino.setUpSelection(p);
        Assert.assertEquals(Menu.PLAYERINFO ,casino.getMenuItemByInput("p"));
    }


    @Test
    public void getMenuItemByInputFailTest(){
        Casino casino = new Casino();
        Player p = new Player(10, "Apollo", 100, false);
        casino.setUpSelection(p);
        Assert.assertNull(casino.getMenuItemByInput("pppppp"));
    }

    @Test
    public void printMainMenuTest(){
        Casino casino = new Casino();
        casino.printMainMenu();
    }

    @Test
    public void printMainLogoTest(){
        Casino casino = new Casino();
        casino.printLogo();
    }

    @Test
    public void printLineTest(){
        Casino c = new Casino();
        Assert.assertEquals("/-----/",c.stringOfALine("/","-",5));

    }
}
