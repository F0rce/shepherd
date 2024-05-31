package org.vaadin.addons.de.f0rce;

import java.util.ArrayList;
import java.util.List;
import org.vaadin.addons.de.f0rce.shepherd.config.tour.TourOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;

@Tag("lit-shepherd")
@NpmPackage(value = "shepherd.js", version = "^10.0.0")
@NpmPackage(value = "lit", version = "2.2.7")
@JsModule("./@f0rce/lit-shepherd.js")
@CssImport("./@f0rce/shepherd.css")
public class Shepherd extends Component implements HasComponents, HasStyle {

  private List<Tour> tours = new ArrayList<>();

  public Shepherd() {}

  /**
   * Create a tour with default settings.
   *
   * @return {@link Tour}
   */
  public Tour createTour() {
    Tour tour = new Tour(this);
    this.add(tour);
    this.tours.add(tour);

    Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    this.getElement().callJsFunction("createTour", GSON.toJson(tour));

    return tour;
  }

  /**
   * Create a tour with customized settings.
   *
   * @param tourOptions {@link TourOptions}
   * @return {@link Tour}
   */
  public Tour createTour(TourOptions tourOptions) {
    Tour tour = new Tour(this, tourOptions);

    Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    this.getElement().callJsFunction("createTour", GSON.toJson(tour));

    this.add(tour);
    this.tours.add(tour);

    return tour;
  }

  /**
   * Remove a tour.
   *
   * @param tour {@link Tour}
   */
  public void removeTour(Tour tour) {
    if (this.tours.contains(tour)) {
      this.remove(tour);

      Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
      this.getElement().callJsFunction("removeTour", GSON.toJson(tour));
    }
  }

  protected boolean isAnyTourActive() {
    boolean active = false;
    for (int i = 0; i < this.tours.size(); i++) {
      Tour current = this.tours.get(i);
      if (current.isActive()) {
        active = true;
      }
    }
    return active;
  }
}
