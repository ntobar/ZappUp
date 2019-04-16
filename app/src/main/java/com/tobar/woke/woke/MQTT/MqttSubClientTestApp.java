package com.tobar.woke.woke.MQTT;

import java.util.logging.Logger;


public class MqttSubClientTestApp {

    private static final Logger logger = Logger.getLogger(MqttSubClientTestApp.class.getName());
    private static MqttSubClientTestApp app;
    private MqttClientConnector mqttClient;
    public MqttSubClientTestApp()
    {
        super();
    }

    /*
     * Function used to initialize subscribe action
     * @param topicName: Topic to be subscribed
     */
    public void start(String topicName)
    {
        mqttClient = new MqttClientConnector();
        mqttClient.connect();
        mqttClient.subscribeToTopic(topicName);
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mqttClient.disconnect();
    }





    public static void main(String[] args) {

        app = new MqttSubClientTestApp();
        String topic = "Temperature Sensor";							// Topic name is set

        try
        {
            app.start(topic);
            String message = MqttClientConnector.getMessage();			//Retrieving Json Data
            logger.info("Received Json Data\n");
            System.out.println("Received Json Message: "+message+"\n");

            logger.info("Printing SensorData:\n");

            logger.info("Printing Json Data:\n");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }

}

