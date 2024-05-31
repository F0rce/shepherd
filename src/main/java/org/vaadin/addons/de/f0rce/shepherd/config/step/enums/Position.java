package org.vaadin.addons.de.f0rce.shepherd.config.step.enums;

/**
 * The optional direction to place the Popper tooltip relative to the element.
 *
 * @author David "F0rce" Dodlek
 * @since v1.0.0
 */
public enum Position {
  AUTO("auto"),
  AUTO_START("auto-start"),
  AUTO_END("auto-end"),
  TOP("top"),
  TOP_START("top-start"),
  TOP_END("top-end"),
  BOTTOM("bottom"),
  BOTTOM_START("bottom-start"),
  BOTTOM_END("bottom-end"),
  RIGHT("right"),
  RIGHT_START("right-start"),
  RIGHT_END("right-end"),
  LEFT("left"),
  LEFT_START("left-start"),
  LEFT_END("left-end");

  private String position;

  private Position(String position) {
    this.position = position;
  }

  public String getPosition() {
    return this.position;
  }
}
