//package com.tobar.woke.woke.MQTT;
//
//import java.util.logging.Logger;
//
//public class MqttPubClientTestApp {
//
//    private static final Logger logger = Logger.getLogger(MqttPubClientTestApp.class.getName());
//    private static MqttPubClientTestApp SubApp;
//    private MqttClientConnector mqttClient;
//
//
//    public MqttPubClientTestApp() {
//        super();
//    }
//
//
//
//
//
//
//    /*
//     * Function used to initialize publish action
//     * @param topicName: Topic of the message
//     */
//    public void start(String topicName)
//    {
//        mqttClient = new MqttClientConnector();
//        Boolean data = true;
//        String sensor = MqttClientConnector.dataToJson(data);
//
//
//        mqttClient.connect();
//        logger.info("SensorData after converting into Json\n");
////        mqttClient.publishMessage(topicName, 1 , sensor.getBytes());
//        String a = "on";
//        String b = "undone";
//
//        byte[] payload = a.getBytes();
//        byte[] payload1 = b.getBytes();
//
//        mqttClient.publishMessage(topicName, 1 , payload);
//        mqttClient.publishMessage("useractivity", 1 , payload1);
//    }
//
//    public static void main(String[] args) {
//
//        SubApp = new MqttPubClientTestApp();
//        String topic = "matsensor";
//        String topic1 = "useractivity";
//        try {
//            SubApp.start(topic);
//            //SubApp.start(topic1);
//
//
//
//        }catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//}
//
