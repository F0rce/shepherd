package org.vaadin.addons.de.f0rce.shepherd.config.step.enums;

/**
 * @author David "F0rce" Dodlek
 * @since v1.0.0
 */
public enum Block {
  START("start"),
  END("end");

  private String block;

  private Block(String block) {
    this.block = block;
  }

  public String getBlock() {
    return this.block;
  }
}
