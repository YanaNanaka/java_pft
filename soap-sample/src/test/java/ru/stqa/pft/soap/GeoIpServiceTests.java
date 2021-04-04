package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {


    @Test
    public void testMyIp() {
        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("188.187.132.105");
        System.out.println(ipLocation);
        assertEquals(ipLocation,"<GeoIP><Country>RU</Country><State>45</State></GeoIP>");
    }
}

