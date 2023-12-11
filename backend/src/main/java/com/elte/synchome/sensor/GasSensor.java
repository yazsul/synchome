package com.elte.synchome.sensor;

import com.elte.synchome.generator.GasSensorGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

@Getter @Setter
@ToString
public class GasSensor implements Sensor {
  private static final Logger logger = LoggerFactory.getLogger(GasSensor.class);

  public GasSensor(){
    this.id = UUID.randomUUID().toString();
  }
  private String id;
  private List<Double> values;
  private Double holder;

  @Override
  public void readGeneratedData() {
    GasSensorGenerator generator = new GasSensorGenerator();
    this.values = generator.generateRandomReadings();
    holder = this.values.get(0);
    logger.info("Gas Sensor Data - Sensor ID: " + id + ", Values: " + values);
  }

  @Override
  public void readStoredData() {
  }
}
