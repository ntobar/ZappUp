package com.tobar.woke.woke.MQTT;

import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttClientConnector implements MqttCallback {

    /**
     * MqttClientConnector.java: Class for implementing MQTT protocol connector
     *
     * @var logger: logger class
     * @var protocol: type of protocol in String
     * @var host: MQTT host server name in String
     * @var port: MQTT port number in integer
     * @var clientID: ID of the MQTT client in String
     * @var brokerAddr: complete address of the host server in String
     * @var mqttClient: MQTT client class object instance
     * @var sensorData: Sensor data class object instance
     * @var dataUtil: Data utility class object instance
     */
    private static final Logger logger = Logger.getLogger(MqttClientConnector.class.getName());
    private String _protocol = "tcp";
    private String _host = "test.mosquitto.org";
    private int _port = 1883;

    private String _authToken;
    private String _clientID;
    private String _brokerAddr;
    private String _pemFileName;
    private boolean _isSecureConn = false;
    private MqttClient mqttClient;
    private static String msg;


    /**
     * MqttClientConnector default constructor
     */
    public MqttClientConnector() {
        if (_host != null && _host.trim().length() > 0) {

            this._clientID = mqttClient.generateClientId();
            logger.info("Using client id for broker connection: " + _clientID);
            this._brokerAddr = _protocol + "://" + _host + ":" + _port;
            logger.info("Using URL for broker connection: " + _brokerAddr);
        }
    }


    /**
     * Method to connect to the MQTT host server
     */
    public void connect() {
        if (mqttClient == null) {
            MemoryPersistence persistence = new MemoryPersistence();
            try {
                mqttClient = new MqttClient(_brokerAddr, _clientID, persistence);
                MqttConnectOptions connOpts = new MqttConnectOptions();
                connOpts.setCleanSession(true);
                System.out.println("Token: " + _authToken);
                if (_authToken != null)
                    connOpts.setUserName(_authToken);

                // if we are using a secure connection, there's a bunch of stuff we need to
                // do...
                if (_isSecureConn)
                    initSecureConnection(connOpts);

                logger.info("Connecting to broker: " + _brokerAddr);
                mqttClient.setCallback(this);
                mqttClient.connect(connOpts);
                logger.info("connected to broker: " + _brokerAddr);
            } catch (MqttException ex) {
                logger.log(Level.SEVERE, "Failed to connect to broker" + _brokerAddr, ex);
            }

        }
    }

    /**
     * Method to disconnect from the MQTT host server
     */
    public void disconnect() {
        try {
            mqttClient.disconnect();
            logger.info("Disconnect from broker: " + _brokerAddr);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Failed to disconnect from broker: " + _brokerAddr, ex);
        }
    }

    /**
     * Method to publish/send MQTT message
     *
     * @param topic:    name of the MQTT session topic in string
     * @param qosLevel: quality of service in integer 0,1,2
     * @param payload:  message that needs to be sent in byte array
     * @return msgSent: true - on success, false - on failure
     */
    public boolean publishMessage(String topic, int qosLevel, byte[] payload) {
        boolean msgSent = false;
        try {
            logger.info("Publishing message to topic: " + topic);
            MqttMessage msg = new MqttMessage(payload);
            msg.setQos(qosLevel);
            mqttClient.publish(topic, msg);

            msgSent = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Failed to publish Mqtt message " + ex.getMessage());
        }
        return msgSent;
    }

    /**
     * Method to subscribe and receive MQTT message
     *
     * @param topic: name of the MQTT session topic in string
     * @return success: true - on success, false - on failure
     */
    public boolean subscribeToTopic(String topic) {
        boolean success = false;
        try {
            logger.info("Subscribing to topic: " + topic);
            mqttClient.subscribe(topic);
            success = true;
        } catch (MqttException e) {
            e.printStackTrace();
        }
        return success;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.paho.client.mqttv3.MqttCallback#connectionLost(java.lang.
     * Throwable)
     */
    @Override
    public void connectionLost(Throwable cause) {

        logger.log(Level.WARNING, "Connection to broker lost. Will retry soon.", cause);
    }

    public static void setMsg(MqttMessage msg) {
        MqttClientConnector.msg = msg.toString();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.eclipse.paho.client.mqttv3.MqttCallback#messageArrived(java.lang.String,
     * org.eclipse.paho.client.mqttv3.MqttMessage)
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {

        MqttClientConnector.setMsg(message);


        logger.info("\n\nMessage arrived: " + topic + "," + message);
        //sensorData = dataUtil.JsonToSensorData(message.toString());
        //logger.info("\n\nReceived Sensor Data: \n" + sensorData);
        //logger.info("\n\nReceived Sensor Data to JSON: \n" + dataUtil.SensorDataToJson(sensorData));

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.eclipse.paho.client.mqttv3.MqttCallback#deliveryComplete(org.eclipse.paho
     * .client.mqttv3.IMqttDeliveryToken)
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

        logger.info("Delivery Complete: " + token.getMessageId() + "-" + token.getResponse());

    }

    private void initSecureConnection(MqttConnectOptions connOpts) {
        try {
            logger.info("Configuring TLS...");
            SSLContext sslContext = SSLContext.getInstance("SSL");
            KeyStore keyStore = readCertificate();
            TrustManagerFactory trustManagerFactory = TrustManagerFactory
                    .getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
            connOpts.setSocketFactory(sslContext.getSocketFactory());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to initialize secure MQTT connection.", e);
        }
    }

    private KeyStore readCertificate()
            throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        FileInputStream fis = new FileInputStream(_pemFileName);
        BufferedInputStream bis = new BufferedInputStream(fis);
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        ks.load(null);
        while (bis.available() > 0) {
            Certificate cert = cf.generateCertificate(bis);
            ks.setCertificateEntry("adk_store" + bis.available(), cert);
        }
        return ks;
    }

    public static String getMessage() {
        return msg;
    }

    public static String dataToJson(Boolean data) {
        String jsonD;
        Gson gson = new Gson();
        jsonD = gson.toJson(data);
        return jsonD;
    }

}


