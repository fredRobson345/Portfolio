This is a java software that will take inputs in the terminal for measurements and store them in a single CSV file.
Below is the scenario and details for this program. To run this program, Server.java must be run first and then run Client.java concurrently.

Scenario

As part of a research project studying environmental water pollution, a system is required to allow multiple researchers to record water quality readings in the field and submit these remotely to a central database for later analysis.

Clients will connect to the server and submit the following information:

  •	The unique User ID of the researcher,
  •	Postcode where the reading has been taken,
  •	Onsite measurements: 
    -	Temperature,
    -	Acidity level (pH), a numeric value from 0 meaning very acidic to 14 meaning very basic
    -	Oxido-Reduction Potential (ORP), a numeric value expressed in volts, or milli-volts mV.  An ORP of 300 – 500 mV is            considered good.

The server will timestamp the submissions and save the data and append it to the database, stored as a single CSV file.


System Analysis & Design Requirements

Students will work individually to design and implement the system in Java, following the below criteria:

  •	Use object-oriented programming principles for code organisation, including appropriate class structures and inheritance where applicable.
  •	Design appropriate UML models (e.g., use cases, and class diagrams) to illustrate the system's structure.
  •	Design and implement separate client and server applications, with socket programming for communication between them          (N.B. the system can be designed as a single standalone application; however, marks will be lost for failure to use           socket programming).
  •	Implement proper error handling and validation mechanisms to ensure data integrity.
  •	Document the system comprehensively, including the UML models, the design rationale, a user manual, and testing               documentation.



The expected functionality of the server and client components are given below.

1.1 Server:
  -	The server is initiated by establishing a server socket on a given port number. 
      o	The port number can be given as a command line argument.
  -	The server should host the logging system and for this prototype only needs to support 1 client at a time.
  -	Once a client is connected, the server performs the following:
      o	Greets the client with a welcome message 
      o	Collects the user’s input (i.e., User ID, postcode, temperature, pH and ORP values as floating-point values)
          	The server could send messages to the client requesting data and the client can reply with user inputs
  -	The server should record input and save it to the CSV file.  Data should be stored in the CSV file in the following order:
      o	Timestamp, Use ID, postcode, temperature, acidity (pH), Oxido-Reduction Potential (ORP)
  -	Finally, the server closes the connection.

1.2 Client:
  -	Once the server is initiated, clients can connect to the server using the server’s host IP address and port number. 
      o	These can be provided as command line arguments when initiating a client.
  -	Once connected to the server, each client gets a greeting message from the server, followed by requests to provide input.
  -	Prompts and results are displayed in the terminal standard output.
