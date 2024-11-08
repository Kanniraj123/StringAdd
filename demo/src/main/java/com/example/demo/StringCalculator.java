package com.example.demo;


import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;

@Component
public class StringCalculator {

	public static int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        String delimiter = ",|\n";
        if (numbers.startsWith("//")) {
            int delimiterIndex = numbers.indexOf("\n");
            delimiter = numbers.substring(2, delimiterIndex);
            delimiter = escapeSpecialCharacters(delimiter);
            numbers = numbers.substring(delimiterIndex + 1);
        }

        String[] tokens = numbers.split(delimiter);
        return calculateSum(tokens);
    }

    private static String escapeSpecialCharacters(String delimiter) {
        return delimiter.replaceAll("([\\[\\]{}()*+?^$|])", "\\\\$1");
    }

    private static int calculateSum(String[] tokens) {
        int sum = 0;
        List<Integer> negatives = new ArrayList<>();

        for (String token : tokens) {
            int num = Integer.parseInt(token);
            if (num < 0) {
                negatives.add(num);
            } else {
                sum += num;
            }
        }

        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("Negative numbers not allowed: " + negatives);
        }

        return sum;
    }
    
    public static void main(String args[]) {
    	Scanner sc = new Scanner(System.in);
    	String input = sc.next();
    	
    	System.out.println(add(input));
    	   	
    }
    
}
