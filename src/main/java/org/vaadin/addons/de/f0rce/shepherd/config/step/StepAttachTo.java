package org.vaadin.addons.de.f0rce.shepherd.config.step;

import java.util.UUID;

import org.vaadin.addons.de.f0rce.shepherd.config.step.enums.Position;

import com.google.gson.annotations.Expose;
import com.vaadin.flow.component.Component;

/**
 * @author David "F0rce" Dodlek
 * @since v1.0.0
 */
public class StepAttachTo {
  @Expose private String element;
  @Expose private String on;

  protected StepAttachTo(Component element, Position on) {
    String classId = StepAdvanceOn.generateClassName();

    element.getElement().getClassList().add(classId);

    this.element = "." + classId;
    this.on = on.getPosition();
  }

  protected StepAttachTo(Component element) {
    String classId = StepAdvanceOn.generateClassName();

    element.getElement().getClassList().add(classId);
    this.element = "." + classId;
  }

  protected StepAttachTo(String element, Position on) {
    this.element = element;
    this.on = on.getPosition();
  }

  protected StepAttachTo(String element) {
    this.element = element;
  }
}
