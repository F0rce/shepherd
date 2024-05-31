package org.vaadin.addons.de.f0rce.shepherd.config.step.enums;

/**
 * @author David "F0rce" Dodlek
 * @since v1.0.0
 */
public enum DOMEvent {
  BLUR("blur"),
  CLICK("click"),
  COPY("copy"),
  CUT("cut"),
  DROP("drop"),
  FOCUS("focus"),
  PASTE("paste");

  private String domEvent;

  private DOMEvent(String domEvent) {
    this.domEvent = domEvent;
  }

  public String getDOMEvent() {
    return this.domEvent;
  }
}
