Feature: As a User I want to put an expiration date on my pills
	Scenario: As a User I want to put any date as expiration date on my pills
		Given a expiration date
		When a pill is being created
		Then the expiration date will correspond to that pill

