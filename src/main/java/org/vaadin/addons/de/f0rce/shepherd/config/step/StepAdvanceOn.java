package org.vaadin.addons.de.f0rce.shepherd.config.step;

import java.util.UUID;

import org.vaadin.addons.de.f0rce.shepherd.config.step.enums.DOMEvent;
import org.vaadin.addons.de.f0rce.shepherd.config.step.enums.Position;

import com.google.gson.annotations.Expose;
import com.vaadin.flow.component.Component;

/**
 * @author David "F0rce" Dodlek
 * @since v1.0.0
 */
public class StepAdvanceOn {
  @Expose private String selector;
  @Expose private String event;

  protected StepAdvanceOn(Component selector, DOMEvent event) {
    String classId = StepAdvanceOn.generateClassName();

    selector.getElement().getClassList().add(classId);

    this.selector = "." + classId;
    this.event = event.getDOMEvent();
  }

  protected StepAdvanceOn(String selector, DOMEvent event) {
    this.selector = selector;
    this.event = event.getDOMEvent();
  }

  protected static String generateClassName() {
    String uuid = UUID.randomUUID().toString();
    return "shep-" + uuid.substring(0, 8);
  }
}
