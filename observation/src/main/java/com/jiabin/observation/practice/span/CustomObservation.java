package com.jiabin.observation.practice.span;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;

@Component
public class CustomObservation {

  private final ObservationRegistry observationRegistry;

  CustomObservation(ObservationRegistry observationRegistry) {
    this.observationRegistry = observationRegistry;
  }

  public void query() {
    Observation observation = Observation.createNotStarted("patment", this.observationRegistry) ;
//    observation.lowCardinalityKeyValue("pack", "query");
    observation.observe(() -> {
      try {
        TimeUnit.MICROSECONDS.sleep(new Random().nextInt(600)) ;
      } catch (InterruptedException e) {
        e.printStackTrace() ;
      }
    });
  }

}