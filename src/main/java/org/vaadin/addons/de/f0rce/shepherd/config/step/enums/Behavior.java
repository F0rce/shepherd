package org.vaadin.addons.de.f0rce.shepherd.config.step.enums;

/**
 * @author David "F0rce" Dodlek
 * @since v1.0.0
 */
public enum Behavior {
  AUTO("auto"),
  SMOOTH("smooth");

  private String behaviour;

  private Behavior(String behaviour) {
    this.behaviour = behaviour;
  }

  public String getBehaviour() {
    return this.behaviour;
  }
}
