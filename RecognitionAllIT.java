/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sf.javaanpr.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;
import javax.xml.parsers.ParserConfigurationException;
import net.sf.javaanpr.imageanalysis.CarSnapshot;
import net.sf.javaanpr.intelligence.Intelligence;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import org.xml.sax.SAXException;

/**
 *
 * @author Dennis
 */
@RunWith(Parameterized.class)
public class RecognitionAllIT {
    
    private File plateFile;
    private String plateExpected;
    private CarSnapshot carsnap;
    private Intelligence intel;
    
    
    public RecognitionAllIT(File plateFile, String plateExpected) {
        this.plateFile = plateFile;
        this.plateExpected = plateExpected;
    }
    
    @Before
    public void init() throws FileNotFoundException, IOException, ParserConfigurationException, SAXException {
        carsnap = new CarSnapshot(new FileInputStream(plateFile));
        intel = new Intelligence();
    }
    
    
    @Parameterized.Parameters
    public static Collection<Object[]> testData() throws IOException {
        String snapshotDirPath = "src/test/resources/snapshots";
        String resultsPath = "src/test/resources/results.properties";
        InputStream resultsStream = new FileInputStream(new File(resultsPath));
        
        Properties properties = new Properties();
        properties.load(resultsStream);
        resultsStream.close();
        
        File snapshotDir = new File(snapshotDirPath);
        File[] snapshots = snapshotDir.listFiles();
        
        Collection<Object[]> dataForOneImage = new ArrayList();
        
        for (File file : snapshots) {
            String name = file.getName();
            String plateExpected = properties.getProperty(name);
            dataForOneImage.add(new Object[]{file, plateExpected });
        }
        return dataForOneImage;
    }
    
    @Test
    public void testAllImages() throws IllegalArgumentException, IOException {
         String numberPlate = intel.recognize(carsnap, false);
         
         assertThat(numberPlate, equalTo(plateExpected));
    }
}
