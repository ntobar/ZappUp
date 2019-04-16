package com.tobar.woke.woke.MQTT;

import java.util.logging.Logger;

public class MqttPubClientTestApp {

    private static final Logger logger = Logger.getLogger(MqttPubClientTestApp.class.getName());
    private static MqttPubClientTestApp SubApp;
    private MqttClientConnector mqttClient;


    public MqttPubClientTestApp() {
        super();
    }






    /*
     * Function used to initialize publish action
     * @param topicName: Topic of the message
     */
    public void start(String topicName)
    {
        mqttClient = new MqttClientConnector();
        Boolean data = true;
        String sensor = MqttClientConnector.dataToJson(data);


        mqttClient.connect();
        logger.info("SensorData after converting into Json\n");
        mqttClient.publishMessage(topicName, 2 , sensor.getBytes());
    }

    public static void main(String[] args) {

        SubApp = new MqttPubClientTestApp();
        String topic = "IoT Alarm";
        try {
            SubApp.start(topic);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

