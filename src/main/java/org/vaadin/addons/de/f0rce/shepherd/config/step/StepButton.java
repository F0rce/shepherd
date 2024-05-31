package org.vaadin.addons.de.f0rce.shepherd.config.step;

import org.vaadin.addons.de.f0rce.shepherd.config.step.enums.Action;

import com.google.gson.annotations.Expose;

public class StepButton {

  @Expose private String action;
  @Expose private String classes;
  @Expose private Boolean disabled;
  @Expose private String label;
  @Expose private Boolean secondary;
  @Expose private String text;

  public StepButton action(Action action) {
    this.action = action.getAction();
    return this;
  }

  public Action getAction() {
    return Action.valueOf(this.action);
  }

  public StepButton classes(String classes) {
    this.classes = classes;
    return this;
  }

  public StepButton disabled(Boolean disabled) {
    this.disabled = disabled;
    return this;
  }

  public StepButton label(String label) {
    this.label = label;
    return this;
  }

  public StepButton secondary(Boolean secondary) {
    this.secondary = secondary;
    return this;
  }

  public StepButton text(String text) {
    this.text = text;
    return this;
  }
}
