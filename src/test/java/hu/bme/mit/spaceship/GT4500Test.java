package hu.bme.mit.spaceship;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore primaryTorpedoStore;
  private TorpedoStore secondaryTorpedoStore;

  @Before
  public void init(){
    primaryTorpedoStore = mock(TorpedoStore.class);
    secondaryTorpedoStore = mock(TorpedoStore.class);
    this.ship = new GT4500(primaryTorpedoStore,secondaryTorpedoStore);
  }

  @Test
  public void fireTorpedos_Single_Success(){
    // Arrange
    when(primaryTorpedoStore.fire(1)).thenReturn(true);
    when(secondaryTorpedoStore.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedos(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(primaryTorpedoStore, times(1)).fire(1);
    verify(secondaryTorpedoStore, times(0)).fire(1);

  }

  @Test
  public void fireTorpedos_All_Success(){
    // Arrange
    when(primaryTorpedoStore.fire(1)).thenReturn(true);
    when(secondaryTorpedoStore.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedos(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(primaryTorpedoStore, times(1)).fire(1);
    verify(secondaryTorpedoStore, times(1)).fire(1);
  }

}
