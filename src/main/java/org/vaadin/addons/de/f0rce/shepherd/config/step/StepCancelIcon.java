package org.vaadin.addons.de.f0rce.shepherd.config.step;

import com.google.gson.annotations.Expose;

public class StepCancelIcon {
  @Expose private Boolean enabled;
  @Expose private String label;

  protected StepCancelIcon(boolean enabled) {
    this.enabled = enabled;
  }

  protected StepCancelIcon(boolean enabled, String label) {
    this.enabled = enabled;
    if (enabled) {
      this.label = label;
    }
  }
}
