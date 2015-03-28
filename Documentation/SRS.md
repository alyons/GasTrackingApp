# Software Requirements Specification
## Gas Tracker

### Functional Requirements
1. Car Requirements
	1. The system shall store cars' information: carID*, year*, make*, model*, city miles per gallon, highway miles per gallon, name, and description (* are required fields).
	2. The system shall allow the user to modify a car's information.
	3. The system shall allow the user to delete a car's information.
2. Mileage Requirements
	1. The system shall store mileage records: recordID, time, mileage, price per gallon of gasoline, number of gallons purchased, if the mileage record is a graph break, and carID.
	2. The system shall allow the user to modify a mileage record's information.
	3. The system shall allow the user to delete a mileage record.
3. Graphing Requirements
	1. The system shall show a graph showing a car's miles per gallon.
	2. If the data exists, the system shall show a graph showing a car's miles per gallon against the car's advertised miles per gallon.
	3. The system shall show a graph showing a car's cost per mile.