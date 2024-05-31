package org.vaadin.addons.de.f0rce.shepherd.config.tour;

import org.vaadin.addons.de.f0rce.Step;
import org.vaadin.addons.de.f0rce.Tour;
import org.vaadin.addons.de.f0rce.shepherd.config.step.StepOptions;
import com.google.gson.annotations.Expose;

/**
 * Class representing the site tour.
 *
 * @author David "F0rce" Dodlek
 * @since v1.0.0
 */
public class TourOptions {
  @Expose private Boolean confirmCancel;
  @Expose private String confirmCancelMessage;
  @Expose private String classPrefix;
  @Expose private StepOptions defaultStepOptions;
  @Expose private Boolean exitOnEsc;
  @Expose private Boolean keyboardNavigation;
  @Expose private String tourName;
  @Expose private Boolean useModalOverlay;

  /**
   * If true, will issue a <code>window.confirm</code> before cancelling.
   *
   * @param confirmCancel boolean
   * @return {@link TourOptions}
   */
  public TourOptions confirmCancel(boolean confirmCancel) {
    this.confirmCancel = confirmCancel;
    return this;
  }

  /**
   * The message to display in the confirm dialog.
   *
   * @param confirmCancelMessage {@link String}
   * @return {@link TourOptions}
   */
  public TourOptions confirmCancelMessage(String confirmCancelMessage) {
    this.confirmCancelMessage = confirmCancelMessage;
    return this;
  }

  /**
   * The prefix to add to the <code>shepherd-enabled</code> and <code>shepherd-target</code> class
   * names as well as the <code>data-shepherd-step-id</code>.
   *
   * @param classPrefix {@link String}
   * @return {@link TourOptions}
   */
  public TourOptions classPrefix(String classPrefix) {
    this.classPrefix = classPrefix;
    return this;
  }

  /**
   * Default options for {@link Step}'s, created through {@link Step#Step(StepOptions)} or {@link
   * Tour#addStep(StepOptions)}.
   *
   * @param defaultStepOptions {@link StepOptions}
   * @return {@link TourOptions}
   */
  public TourOptions defaultStepOptions(StepOptions defaultStepOptions) {
    this.defaultStepOptions = defaultStepOptions;
    return this;
  }

  /**
   * Exiting the tour with the escape key will be enabled unless this is explicitly set to false.
   *
   * @param exitOnEsc boolean
   * @return {@link TourOptions}
   */
  public TourOptions exitOnEsc(boolean exitOnEsc) {
    this.exitOnEsc = exitOnEsc;
    return this;
  }

  /**
   * Navigating the tour via left and right arrow keys will be enabled unless this is explicitly set
   * to false.
   *
   * @param keyboardNavigation boolean
   * @return {@link TourOptions}
   */
  public TourOptions keyboardNavigation(boolean keyboardNavigation) {
    this.keyboardNavigation = keyboardNavigation;
    return this;
  }

  /**
   * An optional "name" for the tour. This will be appended to the the tour's dynamically generated
   * <code>id</code> property -- which is also set on the <code>body</code> element as the <code>
   * data-shepherd-active-tour</code> attribute whenever the tour becomes active.
   *
   * @param tourName {@link String}
   * @return {@link TourOptions}
   */
  public TourOptions tourName(String tourName) {
    this.tourName = tourName;
    return this;
  }

  /**
   * Whether or not steps should be placed above a darkened modal overlay. If true, the overlay will
   * create an opening around the target element so that it can remain interactive.
   *
   * @param useModalOverlay boolean
   * @return {@link TourOptions}
   */
  public TourOptions useModalOverlay(boolean useModalOverlay) {
    this.useModalOverlay = useModalOverlay;
    return this;
  }
}
