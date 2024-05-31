package org.vaadin.addons.de.f0rce.shepherd.config.step;

import org.vaadin.addons.de.f0rce.shepherd.config.step.enums.Behavior;
import org.vaadin.addons.de.f0rce.shepherd.config.step.enums.Block;

import com.google.gson.annotations.Expose;

public class StepScrollIntoViewOptions {

  @Expose private String behaviour;
  @Expose private String block;

  protected StepScrollIntoViewOptions(Behavior behaviour, Block block) {
    this.behaviour = behaviour.getBehaviour();
    this.block = block.getBlock();
  }
}
