package org.vaadin.addons.de.f0rce.shepherd.events;

import org.vaadin.addons.de.f0rce.Tour;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;

@DomEvent("tour-complete")
public class TourCompleteEvent extends ComponentEvent<Tour> {

  public TourCompleteEvent(Tour source, boolean fromClient) {
    super(source, fromClient);
  }
}
