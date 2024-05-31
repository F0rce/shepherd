package org.vaadin.addons.de.f0rce;

import org.vaadin.addons.de.f0rce.shepherd.config.step.StepButton;
import org.vaadin.addons.de.f0rce.shepherd.config.step.StepOptions;
import org.vaadin.addons.de.f0rce.shepherd.config.step.enums.Action;
import org.vaadin.addons.de.f0rce.shepherd.config.step.enums.DOMEvent;
import org.vaadin.addons.de.f0rce.shepherd.config.step.enums.Position;
import org.vaadin.addons.de.f0rce.shepherd.config.step.enums.ProgressIndicatorPosition;
import org.vaadin.addons.de.f0rce.shepherd.config.tour.TourOptions;
import org.vaadin.addons.de.f0rce.shepherd.exceptions.DifferentTourActiveException;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("")
public class View extends Div {

  public View() {

    VerticalLayout vL = new VerticalLayout();
    TextField tf = new TextField();
    tf.setLabel("Vorname");
    tf.setId("shepherd-tour1");

    Button b1 = new Button("Button");

    TextField tf2 = new TextField("Label 2");
    tf2.setId("shepherd-tour2");

    Shepherd shepherd = new Shepherd();

    TourOptions to = new TourOptions();
    to.useModalOverlay(true)
        .tourName("Textfields-Test")
        .keyboardNavigation(true)
        .useModalOverlay(true)
        .confirmCancel(true)
        .confirmCancelMessage("Möchten Sie das Tutorial wirklich beenden ?")
        .exitOnEsc(false)
        .defaultStepOptions(
            new StepOptions()
                .buttons(
                    new StepButton().action(Action.CANCLE).text("STOP"),
                    new StepButton().action(Action.NEXT).text("WEITER")));

    Tour t = shepherd.createTour(to);

    StepOptions step1 =
        new StepOptions()
            .arrow(true)
            .attachTo(tf, Position.RIGHT)
            .advanceOn(tf, DOMEvent.BLUR)
            .buttons(new StepButton().action(Action.NEXT).text("Weiter"))
            .text("Benutzen Sie das Textfeld um ihren Vornamen einzugeben")
            .cancelIcon(true, "Beenden")
            .title("Erste Anmeldung")
            .canClickTarget(true)
            .progressIndicator(ProgressIndicatorPosition.HEADER);

    StepOptions step2 = new StepOptions();
    step2
        .arrow(true)
        .attachTo(tf2, Position.BOTTOM)
        .text("Hier gibt es was cooleres zu sehen")
        .title("Schritt 2 - Personenbezogene Daten")
        .buttons(new StepButton().action(Action.NEXT).text("Weiter"))
        .cancelIcon(true, "Exit")
        .title("Step 2")
        .progressIndicator(ProgressIndicatorPosition.HEADER);

    StepOptions step3 = new StepOptions();
    step3
        .arrow(false)
        .buttons(
            new StepButton().action(Action.NEXT).text("Weiter"),
            new StepButton().action(Action.COMPLETE).text("BEENDEN"))
        .text("Das ist ein Knopf")
        .title("Scrhitt 4 - Drück mich")
        .cancelIcon(true, "Exit")
        .progressIndicator(ProgressIndicatorPosition.HEADER);

    t.addStep(step1);
    t.addStep(step2);
    t.addStep(step3);

    t.addCompleteListener(
        event -> {
          System.out.println("TOUR HAS BEEN COMPLETED");
        });

    t.addCancelListener(
        event -> {
          System.out.println("TOUR HAS BEEN CANCELED");
        });

    t.addShowListener(
        event -> {
          System.out.println("TOUR IS BEING SHOWN TO THE USER");
        });

    t.addStartListener(
        event -> {
          System.out.println("TOUR HAS BEEN STARTED");
        });

    Button startTour = new Button("Start TourOptions");
    startTour.addClickListener(
        evt -> {
          try {
            t.start();
          } catch (DifferentTourActiveException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
          }
        });

    vL.add(tf, b1, tf2, startTour);
    this.add(vL, shepherd);
  }
}
