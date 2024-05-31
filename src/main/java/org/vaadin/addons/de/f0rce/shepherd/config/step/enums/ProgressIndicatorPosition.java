package org.vaadin.addons.de.f0rce.shepherd.config.step.enums;

/**
 * @author David "F0rce" Dodlek
 * @since v1.0.0
 */
public enum ProgressIndicatorPosition {
  HEADER("header");

  private String indicatorPosition;

  private ProgressIndicatorPosition(String indicatorPosition) {
    this.indicatorPosition = indicatorPosition;
  }

  public String getIndicatorPosition() {
    return this.indicatorPosition;
  }
}
