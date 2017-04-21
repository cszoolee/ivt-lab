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

  @Test
  public void fireTorpedos_PrimaryEmpty_SecondSucces(){
        // Arrange
        when(primaryTorpedoStore.fire(1)).thenReturn(false);
        when(primaryTorpedoStore.isEmpty()).thenReturn(true);
        when(secondaryTorpedoStore.fire(1)).thenReturn(true);
        when(secondaryTorpedoStore.isEmpty()).thenReturn(false);
        // Act
        boolean result = ship.fireTorpedos(FiringMode.SINGLE);

        // Assert
        assertEquals(true, result);
        verify(secondaryTorpedoStore, times(1)).fire(1);
  }

  @Test
  public void fireTorpedos_PrimaryTwiceSucces_SecondEmpty(){
        // Arrange
        when(primaryTorpedoStore.fire(1)).thenReturn(true);
        when(primaryTorpedoStore.isEmpty()).thenReturn(false);
        when(secondaryTorpedoStore.fire(1)).thenReturn(false);
        when(secondaryTorpedoStore.isEmpty()).thenReturn(true);
        // Act
        boolean firstresult = ship.fireTorpedos(FiringMode.SINGLE);
        boolean secondresult = ship.fireTorpedos(FiringMode.SINGLE);

        // Assert
        assertEquals(true, firstresult);
        assertEquals(true, secondresult);
        verify(primaryTorpedoStore, times(2)).fire(1);
  }

  @Test
  public void fireTorpedos_Single_Fail_BothEmpty(){
        // Arrange
        when(primaryTorpedoStore.fire(1)).thenReturn(false);
        when(secondaryTorpedoStore.fire(1)).thenReturn(false);
        // Act
        boolean result = ship.fireTorpedos(FiringMode.SINGLE);

        // Assert
        assertEquals(false, result);
  }

  @Test
  public void fireTorpedos_All_Fail_BothEmpty(){
        // Arrange
        when(primaryTorpedoStore.fire(1)).thenReturn(false);
        when(secondaryTorpedoStore.fire(1)).thenReturn(false);
        // Act
        boolean result = ship.fireTorpedos(FiringMode.ALL);

        // Assert
        assertEquals(false, result);
  }

  @Test
  public void fireTorpedos_All_Fail_PrimaryFail(){
        // Arrange
        when(primaryTorpedoStore.fire(1)).thenReturn(false);
        when(secondaryTorpedoStore.fire(1)).thenReturn(true);
        // Act
        boolean result = ship.fireTorpedos(FiringMode.ALL);

        // Assert
        assertEquals(false, result);
        verify(primaryTorpedoStore, times(1)).fire(1);
        verify(secondaryTorpedoStore, times(1)).fire(1);
  }

  @Test
  public void fireTorpedos_All_Fail_SecondaryFail(){
        // Arrange
        when(primaryTorpedoStore.fire(1)).thenReturn(true);
        when(secondaryTorpedoStore.fire(1)).thenReturn(false);
        // Act
        boolean result = ship.fireTorpedos(FiringMode.ALL);

        // Assert
        assertEquals(false, result);
        verify(primaryTorpedoStore, times(1)).fire(1);
        verify(secondaryTorpedoStore, times(1)).fire(1);
  }

  @Test
  public void fireTorpedos_All_EmptyTorpedos(){
        // Arrange
        when(primaryTorpedoStore.fire(1)).thenReturn(true);
        when(primaryTorpedoStore.isEmpty()).thenReturn(true);
        when(secondaryTorpedoStore.fire(1)).thenReturn(false);
        when(secondaryTorpedoStore.isEmpty()).thenReturn(true);
        // Act
        boolean result = ship.fireTorpedos(FiringMode.ALL);

        // Assert
        assertEquals(false, result);
  }

  @Test
  public void fireTorpedos_Two_Succes(){
        // Arrange
        when(primaryTorpedoStore.fire(1)).thenReturn(true);
        when(primaryTorpedoStore.isEmpty()).thenReturn(false);
        when(secondaryTorpedoStore.fire(1)).thenReturn(true);
        when(secondaryTorpedoStore.isEmpty()).thenReturn(false);
        // Act
        boolean firstresult = ship.fireTorpedos(FiringMode.SINGLE);
        boolean secondresult = ship.fireTorpedos(FiringMode.SINGLE);

        // Assert
        assertEquals(true, firstresult);
        assertEquals(true, secondresult);
        verify(primaryTorpedoStore, times(1)).fire(1);
        verify(primaryTorpedoStore, times(1)).fire(1);
  }


}
