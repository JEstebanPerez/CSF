
public class AppointmentCreate{

    private AppointmentAccumulator accumulator = AppointmentAccumulator.getAccumulator();
    private Appointment a ;
    private LocalDateTime time = DateManipulator.dateFromStringToLocalDateTime("10/05/2022", "23:30");

    @Given("^$")
    public void nada() {
        //No es necesario nada
    }

    @When("^I enter the necessary fields$")
    public void enterfields(){
       AppointmentCreator ac= new AppointmentCreator("creador", "descripcion", time);
       a= ac.createElement();
    }

    @When("^I click add button$")
    public void button(){
        accumulator.saveElement(a);
    }

    @Then("^I should see the main page$")
    public void ishouldseethemainpage() {

        Assert.assertEquals(1,accumulator.getAllElements().size());
    } 
    


}