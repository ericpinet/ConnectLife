  Introduction
  ------------
  This sample Java application shows how to get and set common parameters of 
  the XBee device. Common parameters are split in cached and non-cached 
  parameters. For that reason, the application refreshes the cached parameters 
  before reading and displaying them. It then configures, reads and displays 
  the value of non-cached parameters.
  
  The application uses the specific setters and getters provided by the XBee 
  device object to configure and read the different parameters.
  
  NOTE: This example uses the generic XBee device (XBeeDevice) class, but it 
        can be applied to any other local or remote XBee device class.


  Files
  -----
    * com.digi.xbee.api.managecommonparameters.MainApp.java:
      Main application class. It instantiates an XBee device, establishes a 
      serial connection with it and configures and reads the different common 
      parameters printing the results in the output console.


  Requirements
  ------------
  To run this example you will need:
  
    * One XBee radio in API mode and its corresponding carrier board (XBIB 
      or XBee Development Board).
    * The XCTU application (available at www.digi.com/xctu).


  Example setup
  -------------
    1) Plug the XBee radio into the XBee adapter and connect it to your
       computer's USB or serial port.
       
    2) Ensure that the module is in API mode.
       For further information on how to perform this task, read the 
       'Configuring Your XBee Modules' topic of the Getting Started guide.
       
    3) Set the port and baud rate of the XBee radio in the MainApp class.
       If you configured the module in the previous step with the XCTU, you 
       will see the port number and baud rate in the 'Port' label of the device 
       on the left view.


  Running the example
  -------------------
  First, build and launch the application. To test the functionality, check 
  that the output console displays the value of all the common (cached and 
  non-cached) parameters. It should display something similar to this: 
  
    Cached parameters
    ----------------------
    [...]
    
    Non-Cached parameters
    ----------------------
    [...]
  