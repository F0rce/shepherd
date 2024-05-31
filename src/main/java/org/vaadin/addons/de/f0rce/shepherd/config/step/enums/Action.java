package org.vaadin.addons.de.f0rce.shepherd.config.step.enums;

/**
 * @author David "F0rce" Dodlek
 * @since v1.0.0
 */
public enum Action {
  BACK("back"),
  CANCLE("cancel"),
  COMPLETE("complete"),
  NEXT("next");

  private String action;

  private Action(String action) {
    this.action = action;
  }

  public String getAction() {
    return this.action;
  }
}
