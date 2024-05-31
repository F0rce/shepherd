package org.vaadin.addons.de.f0rce.shepherd.config.step;

import java.util.ArrayList;
import java.util.UUID;

import org.vaadin.addons.de.f0rce.shepherd.config.step.enums.Behavior;
import org.vaadin.addons.de.f0rce.shepherd.config.step.enums.Block;
import org.vaadin.addons.de.f0rce.shepherd.config.step.enums.DOMEvent;
import org.vaadin.addons.de.f0rce.shepherd.config.step.enums.Position;
import org.vaadin.addons.de.f0rce.shepherd.config.step.enums.ProgressIndicatorPosition;

import com.google.gson.annotations.Expose;
import com.vaadin.flow.component.Component;

public class StepOptions {

  @Expose private boolean arrow = true;
  @Expose private StepAttachTo attachTo;
  @Expose private StepAdvanceOn advanceOn;
  @Expose private ArrayList<StepButton> buttons = new ArrayList<>();
  @Expose private Boolean canClickTarget;
  @Expose private StepCancelIcon cancelIcon;
  @Expose private String classes;
  @Expose private String highlightClass;
  @Expose private String id = UUID.randomUUID().toString();
  @Expose private Integer modalOverlayOpeningPadding;
  @Expose private Integer modalOverlayOpeningRadius;
  @Expose private Object scrollTo;
  @Expose private String text;
  @Expose private String title;
  @Expose private String progressIndicator;

  /**
   * Whether to display the arrow for the tooltip or not. Defaults to <code>true</code>.
   *
   * @param arrow
   * @return {@link StepOptions}
   */
  public StepOptions arrow(boolean arrow) {
    this.arrow = arrow;
    return this;
  }

  public StepOptions attachTo(Component element) {
    this.attachTo = new StepAttachTo(element);
    return this;
  }

  public StepOptions attachTo(Component element, Position position) {
    this.attachTo = new StepAttachTo(element, position);
    return this;
  }

  public StepOptions attachTo(String element) {
    this.attachTo = new StepAttachTo(element);
    return this;
  }

  public StepOptions attachTo(String element, Position position) {
    this.attachTo = new StepAttachTo(element, position);
    return this;
  }

  public StepOptions advanceOn(Component selector, DOMEvent event) {
    this.advanceOn = new StepAdvanceOn(selector, event);
    return this;
  }

  public StepOptions advanceOn(String selector, DOMEvent event) {
    this.advanceOn = new StepAdvanceOn(selector, event);
    return this;
  }

  public StepOptions buttons(StepButton... buttons) {
    for (int i = 0; i < buttons.length; i++) {
      this.buttons.add(buttons[i]);
    }
    return this;
  }

  public StepOptions canClickTarget(boolean canClickTarget) {
    this.canClickTarget = canClickTarget;
    return this;
  }

  public StepOptions cancelIcon(boolean enabled) {
    this.cancelIcon = new StepCancelIcon(enabled);
    return this;
  }

  public StepOptions cancelIcon(boolean enabled, String label) {
    this.cancelIcon = new StepCancelIcon(enabled, label);
    return this;
  }

  public StepOptions classes(String classes) {
    this.classes = classes;
    return this;
  }

  public StepOptions hightlightClass(String highlightClass) {
    this.highlightClass = highlightClass;
    return this;
  }

  public StepOptions id(String id) {
    this.id = id;
    return this;
  }

  public String getId() {
    return this.id;
  }

  public StepOptions modalOverlayOpeningPadding(int modalOverlayOpeningPadding) {
    this.modalOverlayOpeningPadding = modalOverlayOpeningPadding;
    return this;
  }

  public StepOptions modalOverlayOpeningRadius(int modalOverlayOpeningRadius) {
    this.modalOverlayOpeningRadius = modalOverlayOpeningRadius;
    return this;
  }

  public StepOptions scrollTo(boolean scrollTo) {
    this.scrollTo = scrollTo;
    return this;
  }

  public StepOptions scrollTo(Behavior behavior, Block block) {
    this.scrollTo = new StepScrollIntoViewOptions(behavior, block);
    return this;
  }

  public StepOptions text(String text) {
    this.text = text;
    return this;
  }

  public StepOptions text(Component element) {
    this.text = element.getElement().getOuterHTML();
    return this;
  }

  public StepOptions title(String title) {
    this.title = title;
    return this;
  }

  public StepOptions title(Component element) {
    this.title = element.getElement().getOuterHTML();
    return this;
  }

  public StepOptions progressIndicator(ProgressIndicatorPosition indicatorPosition) {
    this.progressIndicator = indicatorPosition.getIndicatorPosition();
    return this;
  }
}
