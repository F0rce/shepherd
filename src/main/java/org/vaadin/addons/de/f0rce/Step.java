package org.vaadin.addons.de.f0rce;

import org.vaadin.addons.de.f0rce.shepherd.config.step.StepOptions;

import com.google.gson.annotations.Expose;

public class Step {
  private Tour parent;
  @Expose private StepOptions stepOptions;

  public Step(StepOptions stepOptions) {
    this.stepOptions = stepOptions;
  }

  public void cancle() {}

  public void complete() {}

  public void destroy() {}

  // public Component getElement() {}

  // public void getTarget

  // public Tour getTour

  public void hide() {}

  public void isCentered() {}

  public void isOpen() {}

  public void show() {}

  public void updateStepOptions(StepOptions stepOptions) {}

  protected StepOptions getStepOptions() {
    return this.stepOptions;
  }

  protected void setTourParent(Tour tour) {
    this.parent = tour;
  }
}
