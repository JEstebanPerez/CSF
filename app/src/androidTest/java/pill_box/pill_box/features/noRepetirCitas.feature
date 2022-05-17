Feature: que no se repitan las citas creadas
	Scenario: que se cree una cita repetida
		Given una cita ya creada
		When se intente crear una cita ya hecha
		Then esta no se creara

