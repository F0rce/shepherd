package org.vaadin.addons.de.f0rce;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.vaadin.addons.de.f0rce.shepherd.config.step.StepOptions;
import org.vaadin.addons.de.f0rce.shepherd.config.tour.TourOptions;
import org.vaadin.addons.de.f0rce.shepherd.events.TourCancelEvent;
import org.vaadin.addons.de.f0rce.shepherd.events.TourCompleteEvent;
import org.vaadin.addons.de.f0rce.shepherd.events.TourShowEvent;
import org.vaadin.addons.de.f0rce.shepherd.events.TourStartEvent;
import org.vaadin.addons.de.f0rce.shepherd.exceptions.DifferentTourActiveException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.shared.Registration;

@Tag("tour")
public class Tour extends Component {

  private Shepherd parent;
  @Expose private final String id = UUID.randomUUID().toString();
  @Expose private TourOptions tourOptions = new TourOptions();
  private boolean active = false;
  private List<Step> steps = new ArrayList<>();
  private Step currentStep;

  protected Tour(Shepherd shepherd) {
    this.addListener(TourCompleteEvent.class, this::tourCompleted);
    this.addListener(TourCancelEvent.class, this::tourCanceled);
    this.addListener(TourShowEvent.class, this::tourShowed);
    this.addListener(TourStartEvent.class, this::tourStarted);

    this.parent = shepherd;
    this.setId(this.id);
  }

  protected Tour(Shepherd shepherd, TourOptions tourOptions) {
    this.addListener(TourCompleteEvent.class, this::tourCompleted);
    this.addListener(TourCancelEvent.class, this::tourCanceled);
    this.addListener(TourShowEvent.class, this::tourShowed);
    this.addListener(TourStartEvent.class, this::tourStarted);

    this.parent = shepherd;
    this.tourOptions = tourOptions;
    this.setId(this.id);
  }

  private void tourCompleted(TourCompleteEvent event) {
    this.active = false;
    this.getElement().removeAttribute("active");
  }

  private void tourCanceled(TourCancelEvent event) {
    this.active = false;
    this.getElement().removeAttribute("active");
  }

  private void tourShowed(TourShowEvent event) {
    for (int i = 0; i < this.steps.size(); i++) {
      Step s = this.steps.get(i);
      if (s.getStepOptions().getId().equals(event.getStepId())) {
        this.currentStep = s;
      }
    }
  }

  private void tourStarted(TourStartEvent event) {
    this.active = true;
    this.getElement().setAttribute("active", true);
  }

  /**
   * Adds a new {@link Step} to the {@link Tour}.
   *
   * @param stepOptions {@link StepOptions}
   * @return {@link Step}
   */
  public Step addStep(StepOptions stepOptions) {
    Step step = new Step(stepOptions);
    this.steps.add(step);

    Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    this.parent.getElement().callJsFunction("addStep", this.id, GSON.toJson(step));

    return step;
  }

  /**
   * Adds a new {@link Step} to the {@link Tour} with an index to add the step at the specified
   * position.
   *
   * @param stepOptions {@link StepOptions}
   * @return {@link Step}
   */
  public Step addStep(StepOptions stepOptions, int index) {
    Step step = new Step(stepOptions);
    this.steps.add(index, step);

    Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    this.parent.getElement().callJsFunction("addStep", this.id, GSON.toJson(step), index);

    return step;
  }

  public Step addStep(Step step) {
    this.steps.add(step);

    Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    this.parent.getElement().callJsFunction("addStep", this.id, GSON.toJson(step));

    return step;
  }

  public Step addStep(Step step, int index) {
    this.steps.add(index, step);

    Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    this.parent.getElement().callJsFunction("addStep", this.id, GSON.toJson(step), index);

    return step;
  }

  public void addSteps(Step... steps) {
    for (int i = 0; i < steps.length; i++) {
      Step current = steps[i];
      this.steps.add(current);

      Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
      this.parent.getElement().callJsFunction("addStep", this.id, GSON.toJson(current));
    }
  }

  public void back() {
    this.parent.getElement().callJsFunction("backTour", this.id);
  }

  public void cancel() {
    this.parent.getElement().callJsFunction("cancelTour", this.id);
  }

  public void complete() {
    this.parent.getElement().callJsFunction("completeTour", this.id);
  }

  public Step getById(String id) {
    for (int i = 0; i < this.steps.size(); i++) {
      Step curr = this.steps.get(i);

      if (curr.getStepOptions().getId().equals(id)) {
        return curr;
      }
    }
    return null;
  }

  public Step getCurrentStep() {
    return this.currentStep;
  }

  public void hide() {}

  public boolean isActive() {
    return this.active;
  }

  public void next() {}

  public void removeStep(Step step) {}

  public void show(int key, boolean forward) {}

  public void start() throws DifferentTourActiveException {
    if (this.parent.isAnyTourActive()) {
      throw new DifferentTourActiveException("Another Tour is already active.");
    }
    this.parent.getElement().callJsFunction("startTour", this.id);
  }

  public void setHeaderBackgroundColor(String headerColor) {
    this.getElement().getStyle().set("--shepherd-header-background", headerColor);
  }

  /**
   * Add a listener to the editor, which listens if a tour has been canceled.
   *
   * @param listener {@link ComponentEventListener}
   * @return {@link Registration}
   */
  public Registration addCancelListener(ComponentEventListener<TourCancelEvent> listener) {
    return this.addListener(TourCancelEvent.class, listener);
  }

  /**
   * Add a listener to the editor, which listens if a tour has been completed.
   *
   * @param listener {@link ComponentEventListener}
   * @return {@link Registration}
   */
  public Registration addCompleteListener(ComponentEventListener<TourCompleteEvent> listener) {
    return this.addListener(TourCompleteEvent.class, listener);
  }

  /**
   * Add a listener to the editor, which listens if a tour is beeing shown to the user.
   *
   * @param listener {@link ComponentEventListener}
   * @return {@link Registration}
   */
  public Registration addShowListener(ComponentEventListener<TourShowEvent> listener) {
    return this.addListener(TourShowEvent.class, listener);
  }

  /**
   * Add a listener to the editor, which listens if a tour has been started.
   *
   * @param listener {@link ComponentEventListener}
   * @return {@link Registration}
   */
  public Registration addStartListener(ComponentEventListener<TourStartEvent> listener) {
    return this.addListener(TourStartEvent.class, listener);
  }
}
