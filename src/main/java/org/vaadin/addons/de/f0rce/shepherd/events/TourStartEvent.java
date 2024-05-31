package org.vaadin.addons.de.f0rce.shepherd.events;

import org.vaadin.addons.de.f0rce.Tour;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;

@DomEvent("tour-start")
public class TourStartEvent extends ComponentEvent<Tour> {

  public TourStartEvent(Tour source, boolean fromClient) {
    super(source, fromClient);
    // TODO Auto-generated constructor stub
  }
}
