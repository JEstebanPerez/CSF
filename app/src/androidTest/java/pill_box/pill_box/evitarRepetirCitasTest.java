package pill_box.pill_box;

import com.pastillasCreator.pill_box.almacenaje.AppointmentAccumulator;
import com.pastillasCreator.pill_box.creadorElementosCalendario.AppointmentCreator;
import com.pastillasCreator.pill_box.herramientas.DateManipulator;

import junit.framework.Assert;

import java.time.LocalDateTime;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class evitarRepetirCitasTest {
    private AppointmentAccumulator accumulator = AppointmentAccumulator.getAccumulator();
    private LocalDateTime time = DateManipulator.dateFromStringToLocalDateTime("10/05/2022", "23:30");
    @Given("^una cita ya creada$")
    public void una_cita_ya_creada()  {
        AppointmentCreator creator = new AppointmentCreator("creador", "descripcion", time);
        accumulator.saveElement(creator.createElement());

    }
    @When("^se intente crear una cita ya hecha$")
    public void se_intente_crear_una_cita_ya_hecha() {
        AppointmentCreator creator = new AppointmentCreator("creador", "descripcion", time);
        accumulator.saveElement(creator.createElement());
    }

    @Then("^esta no se creara$")
    public void esta_no_se_creara()  {
        Assert.assertEquals(1,accumulator.getAllElements().size());
    }


}