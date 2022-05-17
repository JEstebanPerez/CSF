package pill_box.pill_box;

import org.junit.runner.RunWith;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class evitarRepetirCitasTest {

    @Given("^una cita ya creada$")
    public void una_cita_ya_creada() throws Throwable {
        throw new PendingException();
    }

    @When("^se intente crear una cita ya hecha$")
    public void se_intente_crear_una_cita_ya_hecha() throws Throwable {
        throw new PendingException();
    }

    @Then("^esta no se creara$")
    public void esta_no_se_creara() throws Throwable {
        throw new PendingException();
    }


}