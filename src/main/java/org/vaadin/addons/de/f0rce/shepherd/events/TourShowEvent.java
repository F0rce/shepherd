package org.vaadin.addons.de.f0rce.shepherd.events;

import org.vaadin.addons.de.f0rce.Tour;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.EventData;

@DomEvent("tour-show")
public class TourShowEvent extends ComponentEvent<Tour> {

  private String stepId;

  public TourShowEvent(
      Tour source, boolean fromClient, @EventData("event.detail.current") String stepId) {
    super(source, fromClient);
    this.stepId = stepId;
  }

  public String getStepId() {
    return this.stepId;
  }
}
